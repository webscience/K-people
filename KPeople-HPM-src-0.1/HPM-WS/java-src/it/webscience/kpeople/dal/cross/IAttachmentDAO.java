package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.be.Attachment;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.sql.SQLException;
import java.util.List;

/**
 * @author depascalis
 * Interfaccia la classe AttachmentDAO.
 *
 */
public interface IAttachmentDAO {

    /** Attachment di tipo email. */
    int EMAIL = 1;
    /** Attachment di tipo document. */
    int DOCUMENT = 2;
    /** Attachment di tipo pattern. */
    int PATTERN = 3;

    /**
     * Ritorna la lista di attachment legati ad un evento.
     * @param idEvent chiave dell'evento
     * @return oggetto lista di Attachment
     * @throws SQLException eccezioni db
     */
    List<Attachment> getAttachmentsByIdEvent(
            final int idEvent) throws SQLException;

    /**
     * @param string id dell'evento
     * @return lista degli Attachment.
     * @throws KPeopleDAOException eccezione db.
     */
    Attachment getAttachmentsByHpmId(
            final String string) throws KPeopleDAOException;
}
