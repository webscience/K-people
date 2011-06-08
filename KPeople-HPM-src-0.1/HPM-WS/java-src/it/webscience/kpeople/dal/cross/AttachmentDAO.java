package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.be.Attachment;
import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.pattern.PatternDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Classe dao per l'accesso agli attachment.
 */
public class AttachmentDAO implements IAttachmentDAO {

    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public AttachmentDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Aggiunge il document all'attachment associato.
     * @param listAttachment lista di attachments (email+pattern)
     *        associati all'evento
     * @param idParentAttachment id del padre del documento
     * @param document documento da associare alla mail
     */
    private void appendDocumentToAttachment(
            final List<Attachment> listAttachment,
            final int idParentAttachment,
            final Document document) {

        if (logger.isDebugEnabled()) {
            logger.debug("appendDocumentToAttachment: begin");
        }

        for (Attachment attachment : listAttachment) {
            if (attachment.getIdAttachment() == idParentAttachment) {

//              aggiungo l'attachment se non è stato già presente nella lista
                boolean docPresente = false;
                for (Document doc : attachment.getDocList()) {
                    if (doc.getIdAttachment() == document.getIdAttachment()) {
                        docPresente = true;
                    }
                }

                if (!docPresente) {
                    attachment.getDocList().add(document);
                }
            }
        }
    }

    /**
     * @param idEvent id dell'evento
     * @return lista degli Attachment.
     * @throws SQLException eccezione db.
     */
    public final List<Attachment> getAttachmentsByIdEvent(
            final int idEvent) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getAttachmentsByIdEvent: " + idEvent);
        }

        List<Attachment> listAttachment = new ArrayList<Attachment>();
        AttachmentTypeDAO attachTypeDao = new AttachmentTypeDAO();
        Connection conn = null;

        try {
            conn = Singleton.getInstance().getConnection();

            String query =
                "SELECT "
                + "   a.ID_ATTACHMENT, "
                + "   a.DESCRIPTION,"
                + "   a.IS_DELETED ,"
                + "   a.DELETED_BY ,"
                + "   a.DELETED_DATE ,"
                + "   a.FIRST_ACTION_PERFORMER ,"
                + "   a.FIRST_ACTION_DATE ,"
                + "   a.LAST_ACTION_PERFORMER ,"
                + "   a.LAST_ACTION_DATE ,"
                + "   a.ID_ATTACHMENT_TYPE ,"
                + "   a.HPM_ATTACHMENT_ID ,"
                + "   a.NAME,"

                + "   em.EMAIL_OBJECT ,"
                + "   em.EMAIL_BODY ,"

                + "   p.DATE_START,"
                + "   p.DATE_END, "
                + "   p.STARTED_BY, "
                + "   p.CLOSED_BY, "
                + "   p.ID_PATTERN_TYPE, "
                + "   p.ACTIVITI_PROCESS_INSTANCE_ID, "
                + "   p.DATE_DUE, "
                + "   p.ID_PATTERN_STATE, "
                + "   p.HPM_PATTERN_ID, "

                + "   d.AUTHOR ,"
                + "   d.IS_TEMPLATE ,"
                + "   d.ID_DOCUMENT_TYPE ,"
                + "   d.GUID ,"
                + "   d.HASHCODE ,"

                + "   ed.ID_ATTACHMENT_PARENT ,"
                + "   ed.ID_ATTACHMENT_CHILD "

                + "FROM event_attachment ea "
                + "INNER JOIN attachment a ON a.ID_ATTACHMENT = ea.ID_ATTACHMENT "
                + "LEFT OUTER JOIN email em ON a.ID_ATTACHMENT = em.ID_ATTACHMENT "
                + "LEFT OUTER JOIN document d ON a.ID_ATTACHMENT = d.ID_ATTACHMENT "
                + "LEFT OUTER JOIN attachment_children ed ON d.ID_ATTACHMENT = ed.ID_ATTACHMENT_CHILD "
                + "LEFT OUTER JOIN pattern p ON a.ID_ATTACHMENT = p.ID_ATTACHMENT "
                + "WHERE ea.ID_EVENT = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idEvent);
            ResultSet rs = ps.executeQuery();

//          primo ciclo: estrazione di tutti i CommunicationEvent e Pattern
            while (rs.next()) {
                int idAttachment = rs.getInt("ID_ATTACHMENT");
                String hpmAttachmentId = rs.getString("HPM_ATTACHMENT_ID");
                int idAttachmentType = rs.getInt("ID_ATTACHMENT_TYPE");

                logger.debug("id:" + idAttachment);

//              estraggo tutte le mail
                if (idAttachmentType == EMAIL) {
                    Attachment email =
                        AttachmentFactory.createAttachment(rs);
                    email.setAttachmentType(
                          attachTypeDao.getAttachmentTypeByIdAttachmentType(
                                    idAttachmentType));

                    listAttachment.add(email);
                }

//              estraggo tutti i pattern
                if (idAttachmentType == PATTERN) {
                    PatternDAO patternDAO = new PatternDAO();
                    Attachment pattern =
                        patternDAO.getPatternByHpmPatternId(hpmAttachmentId);

                    pattern.setName(rs.getString("NAME"));

                    listAttachment.add(pattern);
                }
            }

//          secondo ciclo: estraggo tutti i Document
//          se presente, lo associo al legame padre/figlio attachment
            boolean first = rs.first();

            while (first || rs.next()) {
                logger.debug("id:" + rs.getInt("ID_ATTACHMENT"));

                int idAttachmentType = rs.getInt("ID_ATTACHMENT_TYPE");
                if (idAttachmentType == DOCUMENT) {

                    Attachment document =
                        AttachmentFactory.createAttachment(rs);

                    document.setName(rs.getString("NAME"));

                    document.setAttachmentType(
                          attachTypeDao.getAttachmentTypeByIdAttachmentType(
                                    idAttachmentType));

//                  verifico se il Document è associato ad un parent
                    int idParent = rs.getInt("ID_ATTACHMENT_PARENT");

                    /** ********* PATCH PER ESTRAZIONE DI ATTACHMENT ASSOCIATI A DOCUMENTI E MAIL ***********
                     * Da modificare la logica di associazione tra attachments e parent
                     * da una relazione 1-N ad una relazione N-M
                     */
                    if (idParent != 0 && !listAttachment.isEmpty()) {
                        appendDocumentToAttachment(
                                listAttachment,
                                idParent,
                                (Document) document);
                    } else {
//                      il documento è associato direttamente all'evento
                        listAttachment.add(document);
                    }
                }

//              nel primo ciclo si utilizza il 'first', successivamente si
//              procede con il next del rs
                first = false;
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return listAttachment;
    }

    /**
     * @param string id dell'evento
     * @return lista degli Attachment.
     * @throws KPeopleDAOException eccezione db.
     */
    public final Attachment getAttachmentsByHpmId(
            final String string) throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("getAttachmentsByIdEvent: " + string);
        }

        Attachment result = new Attachment();
        IAttachmentTypeDAO attachTypeDao = new AttachmentTypeDAO();
        IUserDAO userDao = new UserDAO();
        Connection conn = null;

        try {
            conn = Singleton.getInstance().getConnection();

            String query =
                "SELECT "
                + "   a.ID_ATTACHMENT, "
                + "   a.DESCRIPTION,"
                + "   a.IS_DELETED ,"
                + "   a.DELETED_BY ,"
                + "   a.DELETED_DATE ,"
                + "   a.FIRST_ACTION_PERFORMER ,"
                + "   a.FIRST_ACTION_DATE ,"
                + "   a.LAST_ACTION_PERFORMER ,"
                + "   a.LAST_ACTION_DATE ,"
                + "   a.ID_ATTACHMENT_TYPE ,"
                + "   a.HPM_ATTACHMENT_ID ,"
                + "   a.NAME,"

                + "   em.EMAIL_OBJECT ,"
                + "   em.EMAIL_BODY ,"

                + "   p.DATE_START,"
                + "   p.DATE_END, "
                + "   p.STARTED_BY, "
                + "   p.CLOSED_BY, "
                + "   p.ID_PATTERN_TYPE, "
                + "   p.ACTIVITI_PROCESS_INSTANCE_ID, "
                + "   p.DATE_DUE, "
                + "   p.ID_PATTERN_STATE, "
                + "   p.HPM_PATTERN_ID, "

                + "   d.AUTHOR ,"
                + "   d.IS_TEMPLATE ,"
                + "   d.ID_DOCUMENT_TYPE ,"
                + "   d.GUID ,"
                + "   d.HASHCODE ,"

                + "   ed.ID_ATTACHMENT_PARENT ,"
                + "   ed.ID_ATTACHMENT_CHILD "

                + "FROM event_attachment ea "
                + "INNER JOIN attachment a ON a.ID_ATTACHMENT = ea.ID_ATTACHMENT "
                + "LEFT OUTER JOIN email em ON a.ID_ATTACHMENT = em.ID_ATTACHMENT "
                + "LEFT OUTER JOIN document d ON a.ID_ATTACHMENT = d.ID_ATTACHMENT "
                + "LEFT OUTER JOIN attachment_children ed ON d.ID_ATTACHMENT = ed.ID_ATTACHMENT_CHILD "
                + "LEFT OUTER JOIN pattern p ON a.ID_ATTACHMENT = p.ID_ATTACHMENT "
                + "WHERE a.HPM_ATTACHMENT_ID = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, string);
            ResultSet rs = ps.executeQuery();

//          primo ciclo: estrazione di tutti i CommunicationEvent e Pattern
            while (rs.next()) {
                int idAttachment = rs.getInt("ID_ATTACHMENT");
                String hpmAttachmentId = rs.getString("HPM_ATTACHMENT_ID");
                int idAttachmentType = rs.getInt("ID_ATTACHMENT_TYPE");

                User creator = userDao.getUserByIdUser(
                        rs.getInt("FIRST_ACTION_PERFORMER"));

                result.setFirstActionPerformer(creator);

                logger.debug("id:" + idAttachment);

//              estraggo tutte le mail
                if (idAttachmentType == EMAIL) {
                    Attachment email =
                        AttachmentFactory.createAttachment(rs);
                    email.setAttachmentType(
                          attachTypeDao.getAttachmentTypeByIdAttachmentType(
                                    idAttachmentType));

                    result = email;
                }

//              estraggo tutti i pattern
                if (idAttachmentType == PATTERN) {
                    PatternDAO patternDAO = new PatternDAO();
                    Attachment pattern =
                        patternDAO.getPatternByHpmPatternId(hpmAttachmentId);

                    pattern.setName(rs.getString("NAME"));

                    result = pattern;
                }
            }

//          secondo ciclo: estraggo tutti i Document
//          se presente, lo associo al legame padre/figlio attachment
            boolean first = rs.first();

            while (first || rs.next()) {
                logger.debug("id:" + rs.getInt("ID_ATTACHMENT"));

                User creator = userDao.getUserByIdUser(
                        rs.getInt("FIRST_ACTION_PERFORMER"));

                result.setFirstActionPerformer(creator);

                int idAttachmentType = rs.getInt("ID_ATTACHMENT_TYPE");
                if (idAttachmentType == DOCUMENT) {

                    Attachment document =
                        AttachmentFactory.createAttachment(rs);

                    document.setName(rs.getString("NAME"));

                    document.setAttachmentType(
                          attachTypeDao.getAttachmentTypeByIdAttachmentType(
                                    idAttachmentType));

//                  il documento è associato direttamente all'evento
                    result = document;
                }

//              nel primo ciclo si utilizza il 'first', successivamente si
//              procede con il next del rs
                first = false;
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            throw new KPeopleDAOException(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new KPeopleDAOException(e.getMessage());
            }
        }

        return result;
    }
}
