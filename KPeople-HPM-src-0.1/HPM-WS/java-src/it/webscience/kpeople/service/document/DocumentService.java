package it.webscience.kpeople.service.document;

import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.DocumentManager;
import it.webscience.kpeople.bll.impl.SearchManager;
import it.webscience.kpeople.service.datatypes.Document;
import it.webscience.kpeople.service.datatypes.Process;
import it.webscience.kpeople.service.datatypes.DocumentFilter;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.datatypes.converter.DocumentConverter;
import it.webscience.kpeople.service.datatypes.converter.DocumentFilterConverter;
import it.webscience.kpeople.service.datatypes.converter.ProcessConverter;
import it.webscience.kpeople.service.datatypes.converter.UserConverter;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * Servizio per la gestione dei documenti.
 */
public class DocumentService {
    /** logger. */
    private Logger logger;

    /**
     * Costruttore di default.
     */
    public DocumentService() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * salva nella tabella dello storico download l'evento di download corrente.
     * @param document documento scaricato
     * @param process processo associato
     * @param user utente che ha generato l'evento
     * @throws KPeopleServiceException eccezione durante l'elaborazione
     */
    public final void saveDocumentDownloadHistory(
            final Document document,
            final Process process,
            final User user) throws KPeopleServiceException {
        logger.debug("saveDocumentDownloadHistory - begin");

        it.webscience.kpeople.be.Document documentBE = DocumentConverter.toBE(
                document);
        it.webscience.kpeople.be.User userBE = UserConverter.toBE(user);

        it.webscience.kpeople.be.Process processBE =
            ProcessConverter.toBE(process);

        try {
            DocumentManager mng = new DocumentManager();
            mng.saveDocumentDownloadHistory(documentBE, processBE, userBE);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        logger.debug("saveDocumentDownloadHistory - end");
    }

    /**
     * Metodo per la ricerca dei documenti.
     * @param filter criteri di ricerca
     * @param user utente che effettua la richiesta
     * @return lista dei documenti che soddisfano i criteri di ricerca
     * @throws KPeopleServiceException eccezione durante l'elaborazione
     */
    public final Document[] findDocuments(
            final DocumentFilter filter,
            final User user) throws KPeopleServiceException {
        List<it.webscience.kpeople.be.Document> documentBE = null;

        logger.debug("findDocuments - begin");

        it.webscience.kpeople.be.DocumentFilter filterBE =
                DocumentFilterConverter.toBE(filter);

        it.webscience.kpeople.be.User userBE = UserConverter.toBE(user);

        try {
            SearchManager mng = new SearchManager();
            documentBE = mng.findDocuments(filterBE, userBE);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        Document[] documents = DocumentConverter.toService(documentBE);

        logger.debug("findDocuments - end");
        logger.debug("Documenti trovati: " + documents.length);

        return documents;
    }
}
