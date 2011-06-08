package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.be.Attachment;
import it.webscience.kpeople.dal.document.DocumentFactory;
import it.webscience.kpeople.dal.pattern.PatternFactory;

import java.sql.ResultSet;

/**
 * Factory per la generazione dell'oggetto Attachment.
 */
public class AttachmentFactory {

    /**
     * Costruisce un oggetto di tipo Attachment.
     * In funzione dell'attachment type, viene instanziato un oggetto
     * CommunicationEvent oppure Document
     * @param rs resultset
     * @return oggetto Attachment
     * @throws Exception eccezione durante la creazione dell'oggetto
     */
    public static Attachment createAttachment(
            final ResultSet rs) throws Exception {

        Attachment attachment = null;

        int idAttachType = rs.getInt("ID_ATTACHMENT_TYPE");

        if (idAttachType == IAttachmentDAO.EMAIL) {
//          si tratta di una mail
            attachment = CommunicationFactory.createCommunicationEvent(rs);
        } else if (idAttachType == IAttachmentDAO.DOCUMENT) {
//          si tratta di un documenti
            attachment = DocumentFactory.createDocument(rs);
        } else if (idAttachType == IAttachmentDAO.PATTERN) {
//          si tratta di un pattern
            attachment = PatternFactory.createPattern(rs);
        }

        if (attachment != null) {
            attachment.setIdAttachment(rs.getInt("ID_ATTACHMENT"));
            attachment.setDescription(rs.getString("DESCRIPTION"));
            attachment.setHpmAttachmentId(rs.getString("HPM_ATTACHMENT_ID"));
            attachment.setFirstActionDate(rs.getTimestamp("FIRST_ACTION_DATE"));
        }

        return attachment;
    }
}
