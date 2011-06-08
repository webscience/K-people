package it.webscience.kpeople.bll.proxy;

import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.DocumentFilter;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.document.DocumentDAO;
import it.webscience.kpeople.dal.document.IDocumentDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.util.List;

/**
 * Proxy per la classe DocumentDao.
 */
public class DocumentDAOProxy implements IDocumentDAO {

    @Override
    public final List<Document> findDocuments(final DocumentFilter filter)
            throws KPeopleDAOException {

        DocumentDAO dao = new DocumentDAO();

        return dao.findDocuments(filter);
    }

    @Override
    public Document getDocumentByHpmId(
            final String id) throws KPeopleDAOException {
        DocumentDAO dao = new DocumentDAO();

        return dao.getDocumentByHpmId(id);
    }

    /**
     * salva nella tabella dello storico download l'evento di download corrente.
     * @param document documento scaricato
     * @param user utente che effettua la chiamata
     * @throws KPeopleDAOException eccezione durante l'elaborazione
     */
    public final void saveDocumentDownloadHistory(
            final Document document,
            final User user) throws KPeopleDAOException {

        DocumentDAO dao = new DocumentDAO();

        dao.saveDocumentDownloadHistory(document, user);
    }
}
