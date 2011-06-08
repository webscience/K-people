package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.be.AttachmentType;

import java.sql.SQLException;

/**
 * @author depascalis
 * Interfaccia relativa alla classe AttachmentTypeDAO.
 */
public interface IAttachmentTypeDAO {

    /**
     * Ritorna l'oggetto AttachmentType associato all'evento.
     * @param idAttachmentType id dell'AttachmentType
     * @return oggetto AttachmentType
     * @throws SQLException eccezione db
     */
    AttachmentType getAttachmentTypeByIdAttachmentType(
            final int idAttachmentType) throws SQLException;

}
