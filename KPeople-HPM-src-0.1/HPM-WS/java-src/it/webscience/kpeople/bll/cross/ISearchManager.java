package it.webscience.kpeople.bll.cross;

import java.util.List;

import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.DocumentFilter;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.ProcessFilter;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

/**
 * Interfaccia del manager delle ricerche.
 */
public interface ISearchManager {

    /**
     * Ricerca dei documenti.
     * @param filter criteri di ricerca
     * @param user utente che effettua la ricerca
     * @return lista dei documenti trovati
     * @throws KPeopleBLLException eccezione durante l'elaborazione
     */
    List<Document> findDocuments(
            final DocumentFilter filter,
            final User user)
        throws KPeopleBLLException;

    /**
     * Metodo per la ricerca dei processi.
     * @param filter parametri di ricerca
     * @param user utente che effettua la ricerca
     * @return lista di processi
     * @throws KPeopleBLLException eccezione durante l'elaborazione
     */
    List<Process> findProcesses(
            final ProcessFilter filter,
            final User user)
        throws KPeopleBLLException;

    /**
     * Metodo per la ricerca full text globale.
     * @param freeText testo da cercare
     * @return lista di BE trovate
     * @throws KPeopleBLLException eccezione nel servizio
     */
    List<Object> findEverything(final String freeText)
        throws KPeopleBLLException;
}
