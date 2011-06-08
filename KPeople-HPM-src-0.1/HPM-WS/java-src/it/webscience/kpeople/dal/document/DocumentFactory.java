package it.webscience.kpeople.dal.document;

import it.webscience.kpeople.be.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * factory per le classi HPM.
 * @author dellanna
 */
public class DocumentFactory {

    /**
     * factory per l'oggetto Document.
     * @param rs resultset
     * @return istanza dell'oggetto Document
     * @throws SQLException label colonne non valido
     */
    public static Document createDocument(final ResultSet rs)
            throws SQLException {
        Document document = new Document();

        document.setAuthor(rs.getString("AUTHOR"));
        document.setTemplate(rs.getBoolean("IS_TEMPLATE"));
        document.setDocumentType(rs.getBoolean("ID_DOCUMENT_TYPE"));
        document.setIdAttachment(rs.getInt("ID_ATTACHMENT"));
        document.setGuid(rs.getString("GUID"));
        document.setHashcode(rs.getString("HASHCODE"));

        return document;
    }
}
