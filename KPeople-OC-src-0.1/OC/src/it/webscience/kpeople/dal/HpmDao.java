package it.webscience.kpeople.dal;

import it.webscience.kpeople.be.Attachment;
import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.Email;
import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.EventMetadata;
import it.webscience.kpeople.be.Keyword;
import it.webscience.kpeople.be.ObjectKeyword;
import it.webscience.kpeople.be.ObjectType;
import it.webscience.kpeople.be.User;
import it.webscience.uima.Singleton;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;


/**
 * DAO per l'accesso su db alle classi HPM.
 * @author dellanna
 *
 */
public class HpmDao {
    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public HpmDao() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * estrae l'oggetto User avente l'username indicato.
     * @param username username da cercare
     * @param conn connessione al db
     * @return oggetto User
     * @throws SQLException if a database access error occurs
     */
    public final User getUserByUsername(
            final String username,
            final Connection conn)
            throws SQLException {
        String query = "SELECT * FROM user WHERE username = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = HpmFactory.createUser(rs);
        } else {
            logger.info("User " + username + " non trovato");
        }

        return user;
    }

    /**
     * recupera l'id attachment del documento avente l'hashcode cercato.
     * @param hashcode hashcode da cercare
     * @param conn connessione al db
     * @return id attachment del document cercato. 0 se non presente
     * @throws SQLException if a database access error occurs
     */
    public final int getIdAttachmentByHashcode(
            final String hashcode,
            final Connection conn)
            throws SQLException {

        logger.debug("Ricerca del document con barcode: " + hashcode);

        String query = "SELECT ID_ATTACHMENT FROM document WHERE hashcode = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, hashcode);

        ResultSet rs = ps.executeQuery();

        int idAttachment = 0;
        if (rs.next()) {
            idAttachment = rs.getInt("ID_ATTACHMENT");
        }

        rs.close();
        ps.close();

        logger.debug("id attachment restituito: " + idAttachment);

        return idAttachment;
    }

    /**
     * manager per il salvataggio di un evento.
     * Vengono salvati, in transazione, l'oggetto Event
     * e tutti gli attachment (email+document) contenuti
     * @param event oggetto da salvare
     * @throws SQLException if a database access error occurs
     * @return id dell'oggetto evento
     */
    public final int saveEventManager(final Event event)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEventManager - BEGIN");
        }

        int idEvent = 0;
        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();
            conn.setAutoCommit(false);

//          dati per il salvataggio delle informazioni di DataTraceClass
            User user = getUserByUsername("ontologycontroller", conn);
            java.util.Date now = new java.util.Date();

//          salvataggio dell'Event e recupero dell'id
            idEvent = saveEvent(event, user, now, conn);
            event.setIdEvent(idEvent);

//          salvataggio dei metadati
            for (EventMetadata em : event.getEventMetadata()) {
                if (em.getValue() == null) {
                    logger.warn(
                            "Metadato non salvato: keyName: "
                            + em.getKeyname());
                } else {
                    saveEventMetadata(em, user, now, conn);
                }
            }

//          salvataggio degli attachments e della tabella event_attachment
            for (Attachment attachment : event.getAttachments()) {
                int idAttachment = saveAttachment(attachment, user, now, conn);
                saveEventAttachment(idAttachment, idEvent, conn);
            }

            saveEmailDocumentManager(event, conn);
            saveProperties(event, user, now, conn);

            conn.commit();
            if (logger.isDebugEnabled()) {
                logger.debug("Transaction commit...");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollbackTransaction(conn, e);
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachmentType - END. id = " + idEvent);
        }

        return idEvent;
    }

    /**
     * ritorna l'oggetto Keyword associato alla chiave desiderata.
     * @param k chiave
     * @param conn connection per la gestione della transazione
     * @throws SQLException eccezioni db
     * @return oggetto Keyword
     */
    private Keyword getKeyword(
            final String k,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getKeyword: " + k);
        }

        String query = "SELECT * FROM keyword WHERE keyword = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, k);

        ResultSet rs = ps.executeQuery();

        Keyword keyword = null;
        if (rs.next()) {
            keyword = HpmFactory.createKeyword(rs);
        }

        return keyword;
    }

    /**
     * ritorna l'oggetto ObjectType associato al name.
     * @param name name da cercare
     * @param conn connection per la gestione della transazione
     * @throws SQLException eccezioni db
     * @return oggetto Keyword
     */
    private ObjectType getObjectType(
            final String name,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getObjectType: " + name);
        }

        String query = "SELECT * FROM object_type WHERE name = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();

        ObjectType ot = null;
        if (rs.next()) {
            ot = HpmFactory.createObjectType(rs);
        }

        return ot;
    }

    /**
     * salva le proprietà associate all'evento.
     * @param event evento
     * @param user first action performer
     * @param now first action date
     * @param conn connection per la gestione della transazione
     * @throws SQLException eccezione db
     */
    private void saveProperties(
            final Event event,
            final User user,
            final java.util.Date now,
            final Connection conn) throws SQLException {
        Enumeration<String> keys = event.getProperties().keys();
        while (keys.hasMoreElements()) {
            String k = (String) keys.nextElement();
            String value = event.getProperties().get(k);

//          1. cerco l'oggetto Keyword associato alla keyword
            Keyword keyword = getKeyword(k, conn);

//          2. cerco l'oggetto ObjectType relativo a Event
            ObjectType objectType = getObjectType("Event", conn);

//          3. creo e salvo l'oggetto ObjectKeyword
            ObjectKeyword objectKeyword = new ObjectKeyword();
            objectKeyword.setKeyword(keyword);
            objectKeyword.setObjectType(objectType);
            objectKeyword.setIdObject(event.getIdEvent());
            objectKeyword.setValue(value);

            saveObjectKeyword(objectKeyword, user, now, conn);
        }
    }

    /**
     * scrive una riga nella tabellla object_keyword.
     * @param ok oggetto da salvare
     * @param user first action performer
     * @param now first action date
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    private void saveObjectKeyword(
            final ObjectKeyword ok,
            final User user,
            final java.util.Date now,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveObjectKeyword - BEGIN");
        }

        String sql =
            "INSERT INTO object_keyword"
            + "(ID_KEYWORD, ID_OBJECT_TYPE, ID_OBJECT, VALUE,"
            + " IS_DELETED, FIRST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
            + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE"
            + ") VALUES "
            + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, ok.getKeyword().getIdKeyword());
        pst.setInt(2, ok.getObjectType().getIdObjectType());
        pst.setInt(3, ok.getIdObject());
        pst.setString(4, ok.getValue());
        pst.setBoolean(5, false);
        pst.setInt(6, user.getIdUser());
        pst.setTimestamp(7, new Timestamp(now.getTime()));
        pst.setInt(8, user.getIdUser());
        pst.setTimestamp(9, new Timestamp(now.getTime()));

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveObjectKeyword - END");
        }
    }

    /**
     * creazione del legame email-document.
     * @param event oggetto da salvare
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    private void saveEmailDocumentManager(
            final Event event,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailDocumentManager - BEGIN");
        }

        for (Attachment attachment : event.getAttachments()) {
            if (attachment instanceof Email) {
                Email email = (Email) attachment;
                for (Document document : email.getDocuments()) {
                    saveAttachmentChild(
                            email.getIdAttachment(),
                            document.getIdAttachment(),
                            conn);
                }
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailDocumentManager - END");
        }
    }

    /**
     * scrive una riga nella tabellla event_attachment.
     * @param idAttachment idAttachment
     * @param idEvent idEvent
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    private void saveEventAttachment(
            final int idAttachment,
            final int idEvent,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEventAttachment - BEGIN");
        }

        String sql =
            "INSERT INTO event_attachment"
            + "(ID_EVENT, ID_ATTACHMENT)"
            + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, idEvent);
        pst.setInt(2, idAttachment);
        
        logger.debug("Associazione dell'evento " + idEvent
                + " con l'attachment " + idAttachment);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEventAttachment - END");
        }
    }

    /**
     * scrive una riga nella tabellla attachment_children.
     * @param idParent campo id_email
     * @param idChild campo id_document
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    private void saveAttachmentChild(
            final int idParent,
            final int idChild,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachmentChild - BEGIN");
        }

        String sql =
            "INSERT INTO attachment_children"
            + "(ID_ATTACHMENT_PARENT, ID_ATTACHMENT_CHILD)"
            + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, idParent);
        pst.setInt(2, idChild);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachmentChild - END");
        }
    }

    /**
     * salva un oggetto di tipo Event.
     * @param event oggetto da salvare su db
     * @param conn connection per la gestione della transazione
     * @param user first action performer
     * @param now first action date
     * @return id autogenerato
     * @throws SQLException if a database access error occurs
     */
    private int saveEvent(
            final Event event,
            final User user,
            final java.util.Date now,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEvent - BEGIN");
        }

        String sql =
            "INSERT INTO event"
            + "(NAME, HPM_EVENT_ID, HPM_SYSTEM_ID,"
            + " IS_DELETED, FIRST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
            + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);

        pst.setString(1, event.getName());
        pst.setString(2, event.getHpmEventId());
        pst.setString(3, event.getHpmSystemId());

        pst.setBoolean(4, false);
        pst.setInt(5, user.getIdUser());
        pst.setTimestamp(6, new Timestamp(now.getTime()));
        pst.setInt(7, user.getIdUser());
        pst.setTimestamp(8, new Timestamp(now.getTime()));

        pst.execute();

        ResultSet keys = pst.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        if (logger.isDebugEnabled()) {
            logger.debug("saveEvent - END. id = " + id);
        }

        return id;
    }

    /**
     * salva un oggetto di tipo AttachmentType.
     * @param at oggetto da salvare su db
     * @param user first action performer
     * @param now first action date
     * @return id autogenerato
     * @throws SQLException if a database access error occurs
     */
    public final int saveAttachmentType(
            final AttachmentType at,
            final User user,
            final java.util.Date now)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachmentType - BEGIN");
        }

        int id = 0;
        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            String sql =
                "INSERT INTO attachment_type"
                + "(NAME,"
                + " IS_DELETED, FIRST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
                + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE"
                + ") VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, at.getName());

            pst.setBoolean(2, false);
            pst.setInt(3, user.getIdUser());
            pst.setTimestamp(4, new Timestamp(now.getTime()));
            pst.setInt(5, user.getIdUser());
            pst.setTimestamp(6, new Timestamp(now.getTime()));

            pst.execute();

            ResultSet keys = pst.getGeneratedKeys();
            keys.next();
            id = keys.getInt(1);

            conn.commit();

            if (logger.isDebugEnabled()) {
                logger.debug("Transaction commit...");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollbackTransaction(conn, e);
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachmentType - END. id = " + id);
        }

        return id;
    }

    /**
     * Metodo utilizzato per il salvataggio dei documenti associati
     * ad un pattern.
     * @param docs documenti associati al pattern
     * @param username utente che ha avviato il pattern
     * @param kpeopleTagPattern hpmPatternId associato al pattern
     * @return lista dei documenti, con gli idAttachment settati
     * @throws SQLException eccezione durante la transazione
     */
    public final List<Document> savePatternDocument(
            final List<Document> docs,
            final String username,
            final String kpeopleTagPattern)
        throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("savePatternDocument - BEGIN");
        }

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            int idPattern = getIdAttachmentByHpmPatternId(
                    kpeopleTagPattern,
                    conn);

            int idEvent = getIdEventByIdAttachment(idPattern, conn);

            Date now = new Date();

//          recupero l'user associato alla creazione del pattern
            User user = getUserByUsername(username, conn);

            for (Document doc : docs) {
               int idAttachment = saveAttachment(doc, user, now, conn);

                doc.setIdAttachment(idAttachment);

//              salvo il legame padre-figlio tra pattern e document
                saveAttachmentChild(idPattern, idAttachment, conn);

//              lego il nuovo attachment all'evento associato al pattern
                saveEventAttachment(idAttachment, idEvent, conn);
            }

            conn.commit();

            if (logger.isDebugEnabled()) {
                logger.debug("Transaction commit...");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            rollbackTransaction(conn, e);
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("savePatternDocument - END");
        }

        return docs;
    }

    /**
     * Recupera l'idEvent a partire da idAttachment.
     * Il legame viene cercato nella tabella event_attachment.
     * @param idAttachment idAttachment da cercare
     * @param conn connessione
     * @return id dell'evento associato
     * @throws SQLException eccezione durante l'elaborazione della query
     */
    private int getIdEventByIdAttachment(
            final int idAttachment,
            final Connection conn)
            throws SQLException {
        String query =
            "SELECT ID_EVENT FROM event_attachment WHERE ID_ATTACHMENT = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, idAttachment);

        ResultSet rs = ps.executeQuery();
        int idEvent = 0;
        if (rs.next()) {
            idEvent = rs.getInt("ID_EVENT");
        }

        rs.close();
        ps.close();

        return idEvent;
    }

    /**
     * salva un oggetto di tipo Attachment.
     * Nel caso di attachment di tipo Document, viene effettuato il salvataggio
     * se non è ancora presente un attachment con lo stesso barcode.
     * @param attachment oggetto da salvare su db
     * @param user first action performer
     * @param now first action date
     * @param conn connection per la gestione della transazione
     * @return id autogenerato
     * @throws SQLException if a database access error occurs
     */
    private int saveAttachment(
            final Attachment attachment,
            final User user,
            final java.util.Date now,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachment - BEGIN");
        }

        if (attachment instanceof Document) {
//          cerco il Document con lo stesso hashcode
            int idAttachment = getIdAttachmentByHashcode(
                    ((Document) attachment).getHashcode(), conn);

            if (idAttachment > 0) {
//              non è necessario creare un dcumento con lo stesso attachment.
//              ritorno l'id del documento già inserito

                attachment.setIdAttachment(idAttachment);
                return idAttachment;
            }
        }

        String sql =
            "INSERT INTO attachment"
            + "(NAME, DESCRIPTION, ID_ATTACHMENT_TYPE, HPM_ATTACHMENT_ID,"
            + " IS_DELETED, FIRST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
            + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);

        pst.setString(1, attachment.getName());
        pst.setString(2, attachment.getDescription());
        if (attachment.getAttachmentType() == null) {
            pst.setNull(3, java.sql.Types.INTEGER);
        } else {
            pst.setInt(3, attachment.getAttachmentType().getIdAttachmentType());
        }
        pst.setString(4, attachment.getHpmAttachmentId());

        pst.setBoolean(5, false);
        pst.setInt(6, user.getIdUser());
        pst.setTimestamp(7, new Timestamp(now.getTime()));
        pst.setInt(8, user.getIdUser());
        pst.setTimestamp(9, new Timestamp(now.getTime()));

        pst.execute();

        ResultSet keys = pst.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        attachment.setIdAttachment(id);

        if (attachment instanceof Email) {
            saveEmail((Email) attachment, conn);
        } else if (attachment instanceof Document) {
            saveDocument((Document) attachment, conn);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachment - END. id = " + id);
        }

        return id;
    }

    /**
     * salva un oggetto di tipo Document.
     * @param document oggetto da salvare su db
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    private void saveDocument(
            final Document document,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveDocument - BEGIN");
        }

        String sql =
            "INSERT INTO document"
            + "(ID_ATTACHMENT, AUTHOR, GUID, HASHCODE, IS_TEMPLATE,"
            + "ID_DOCUMENT_TYPE) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, document.getIdAttachment());
        pst.setString(2, document.getAuthor());
        pst.setString(3, document.getGuid());
        pst.setString(4, document.getHashcode());
        pst.setBoolean(5, document.isTemplate());
        
//        TODO: ID_DOCUMENT_TYPE temporaneo
        pst.setInt(6, 1);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveDocument - END.");
        }
    }

    /**
     * salva un oggetto di tipo EventMetadata.
     * @param em oggetto da salvare su db
     * @param user first action performer
     * @param now first action date
     * @param conn connection per la gestione della transazione
     * @return id autogenerato
     * @throws SQLException if a database access error occurs
     */
    private int saveEventMetadata(
            final EventMetadata em,
            final User user,
            final java.util.Date now,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEventMetadata - BEGIN");
            logger.debug("keyname: " + em.getKeyname());
            logger.debug("value: " + em.getValue());
        }

//      eseguo l'escape nel caso di 'details' e 'body value'
        String value = "";
        if (em.getKeyname().equals("details")) {
            value = StringEscapeUtils.unescapeXml(em.getValue());
        } else if (em.getKeyname().equals("body-value")) {
            value = StringEscapeUtils.unescapeHtml(em.getValue());
        } else {
            value = em.getValue();
        }

        String sql =
            "INSERT INTO event_metadata"
            + "(KEYNAME, VALUE, ID_EVENT,"
            + " IS_DELETED, FIRST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
            + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);

        pst.setString(1, em.getKeyname());
        pst.setString(2, value);
        pst.setInt(3, em.getEvent().getIdEvent());

        pst.setBoolean(4, false);
        pst.setInt(5, user.getIdUser());
        pst.setTimestamp(6, new Timestamp(now.getTime()));
        pst.setInt(7, user.getIdUser());
        pst.setTimestamp(8, new Timestamp(now.getTime()));

        pst.execute();

        ResultSet keys = pst.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        if (logger.isDebugEnabled()) {
            logger.debug("saveEventMetadata - END. id = " + id);
        }

        return id;
    }

    /**
     * salva un oggetto di tipo Email.
     * @param email oggetto da salvare su db
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    private void saveEmail(
            final Email email,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmail - BEGIN");
        }

        String sql =
            "INSERT INTO email"
            + "(ID_ATTACHMENT, EMAIL_OBJECT, EMAIL_BODY)"
            + "VALUES (?, ?, ?)";

//      escape del body
        String body = StringEscapeUtils.unescapeXml(email.getEmailBody());

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, email.getIdAttachment());
        pst.setString(2, email.getEmailObject());
        pst.setString(3, body);
        pst.execute();

        for (String cc : email.getEmailCc()) {
            saveEmailCc(cc, email.getIdAttachment(), conn);
        }

        for (String ccn : email.getEmailCcn()) {
            saveEmailCcn(ccn, email.getIdAttachment(), conn);
        }

        for (String from : email.getEmailFrom()) {
            saveEmailFrom(from, email.getIdAttachment(), conn);
        }

        for (String to : email.getEmailTo()) {
            saveEmailTo(to, email.getIdAttachment(), conn);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmail - END.");
        }
    }

    /**
     * salva una riga nella tabella EMAIL_CC.
     * @param email email
     * @param idEmail id della mail
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    private void saveEmailCc(
            final String email,
            final int idEmail,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailCc - BEGIN");
        }

        String sql =
            "INSERT INTO EMAIL_CC"
            + "(EMAIL, ID_ATTACHMENT)"
            + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, email);
        pst.setInt(2, idEmail);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailCc - END.");
        }
    }

    /**
     * salva una riga nella tabella EMAIL_CCN.
     * @param email email
     * @param idEmail id della mail
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    private void saveEmailCcn(
            final String email,
            final int idEmail,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailCcn - BEGIN");
        }

        String sql =
            "INSERT INTO EMAIL_CCN"
            + "(EMAIL, ID_ATTACHMENT)"
            + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, email);
        pst.setInt(2, idEmail);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailCcn - END.");
        }
    }

    /**
     * salva una riga nella tabella EMAIL_FROM.
     * @param email email
     * @param idEmail id della mail
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    private void saveEmailFrom(
            final String email,
            final int idEmail,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailFrom - BEGIN");
        }

        String sql =
            "INSERT INTO EMAIL_FROM"
            + "(EMAIL, ID_ATTACHMENT)"
            + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, email);
        pst.setInt(2, idEmail);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailFrom - END.");
        }
    }

    /**
     * salva una riga nella tabella EMAIL_TO.
     * @param email email
     * @param idEmail id della mail
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    private void saveEmailTo(
            final String email,
            final int idEmail,
            final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailTo - BEGIN");
        }

        String sql =
            "INSERT INTO EMAIL_TO"
            + "(EMAIL, ID_ATTACHMENT)"
            + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, email);
        pst.setInt(2, idEmail);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailTo - END.");
        }
    }

    /**
     * Esegue un rollback della transazione.
     * @param conn connessione al db
     * @param e eccezione lanciata
     * @throws SQLException if a database access error occurs
     */
    private void rollbackTransaction(
            final Connection conn,
            final SQLException e)
            throws SQLException {
        if (conn != null) {
            conn.rollback();
            logger.fatal("Connection rollback...");
            logger.fatal(e.getStackTrace());

            StringWriter sWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(sWriter));
            logger.fatal(sWriter.getBuffer().toString());
        }
    }

    /**
     * Ritorna l'id attachment a partire dall'hpmPatternId di un pattern.
     * @param hpmPatternId  dall'hpmPatternId da cercare
     * @return id trovato
     * @throws SQLException eccezione durante l'estrazione dei dati
     */
    public final int getIdAttachmentByHpmPatternId(
            final String hpmPatternId,
            final Connection con) throws SQLException {

        int idAttachment = 0;

        String query =
            "SELECT ID_ATTACHMENT FROM pattern WHERE HPM_PATTERN_ID = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, hpmPatternId);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            idAttachment = rs.getInt("ID_ATTACHMENT");
        }

        rs.close();
        ps.close();

        return idAttachment;
    }
}
