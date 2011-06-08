package it.webscience.kpeople.dal.document;

import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.DocumentFilter;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.util.List;

/**
 * Interfaccia per la classe DAO relativa ai documenti.
 */
public interface IDocumentDAO {
    /**
     * Ricerca dei processi.
     * @param filter
     *            parametri di ricerca
     * @return lista dei processi trovati
     * @throws KPeopleDAOException
     *             eccezione di livello DAL
     */
    List<Document> findDocuments(final DocumentFilter filter)
            throws KPeopleDAOException;

    /**
     * Cariaca un documento in base all'id.
     * @param id hashcode del documento
     * @return documento
     * @throws KPeopleDAOException eccezione in caso di errori
     */
    Document getDocumentByHpmId(final String id)
    throws KPeopleDAOException;
}
