package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.DocumentFilter;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.Process.ProcessVisibility;
import it.webscience.kpeople.be.ProcessFilter;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.cross.ISearchManager;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.proxy.DocumentDAOProxy;
import it.webscience.kpeople.bll.proxy.ProcessDAOProxy;
import it.webscience.kpeople.bll.proxy.SearchDAOProxy;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * Classe di business per effettuare le ricerche.
 */
public class SearchManager implements ISearchManager {
    /** logger. */
    private Logger logger;

    /**
     * Costruttore di default.
     */
    public SearchManager() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Ricerca dei documenti.
     * @param filter criteri di ricerca
     * @param user utente che effettua la ricerca
     * @return lista dei documenti trovati
     * @throws KPeopleBLLException eccezione durante l'elaborazione
     */
    @Override
    public final List<Document> findDocuments(
            final DocumentFilter filter,
            final User user)
        throws KPeopleBLLException {

        logger.debug("start findDocuments");

        DocumentDAOProxy dao = new DocumentDAOProxy();
        List<Document> result = null;
        try {
            result = dao.findDocuments(filter);
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("end findDocuments");

        return result;
    }

    /**
     * Ricerca dei processi.
     * @param filter criteri di ricerca
     * @param user utente che effettua la ricerca
     * @return lista dei processi trovati
     * @throws KPeopleBLLException eccezione durante l'elaborazione
     */
    @Override
    public final List<Process> findProcesses(
            final ProcessFilter filter,
            final User user)
        throws KPeopleBLLException {

        logger.debug("start findProcesses");

        ProcessDAOProxy dao = new ProcessDAOProxy();        List<Process> result = null;
        try {
//          interrogazione del dao (solr + hpm).
            result = dao.findProcesses(filter);

//          calcolo della visibilità
            for (Process process : result) {
                if (process.getOwner().equals(user)) {
                    process.setVisibility(ProcessVisibility.OWNER);

                } else if (!process.isPrivate()) {
                    process.setVisibility(ProcessVisibility.PUBLIC);

                } else {
//                  nel caso di processo privato, si verifica se l'utente è
//                  abilitato
                    boolean abilitato = false;

                    List<User> utentiAbilitati = dao.findEnabledUsers(process);
                    for (User ua : utentiAbilitati) {
                        if (ua.equals(user)) {
                            abilitato = true;
                            break;
                        }
                    }

                    if (abilitato) {
                        process.setVisibility(ProcessVisibility.ENABLED);
                    } else {
                        process.setVisibility(ProcessVisibility.PRIVATE);
                    }
                }
            }
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("end findProcesses");

        return result;
    }

    /**
     * Ricerca degli utenti abilitati.
     * @param process processo per il quale si vuole trovare l'insieme degli
     * utenti abilitati.
     * @return lista degli utenti abilitati.
     * @throws KPeopleBLLException eccezione durante l'elaborazione.
     */
    public final List<it.webscience.kpeople.be.User> findEnabledUsers(
            final Process process) throws KPeopleBLLException {

        logger.debug("start findEnabledUsers");

        ProcessDAOProxy dao = new ProcessDAOProxy();

        List<it.webscience.kpeople.be.User> listUserBE = null;

        try {
            listUserBE = dao.findEnabledUsers(process);
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        logger.debug("end findEnabledUsers");

        return listUserBE;
    }

    @Override
    /**
     * Ricerca full text globale
     * @param freeText testo da cerare
     * @return lista di oggetto BE generici
     * @throws KPeopleBLLException eccezione nel servizio
     */
    public final List<Object> findEverything(final String freeText)
        throws KPeopleBLLException {

        logger.debug("start findEverything");

        SearchDAOProxy dao = new SearchDAOProxy();

        List<Object> result = null;

        try {
            result = dao.find(freeText);
        } catch (KPeopleDAOException e) {
            throw new KPeopleBLLException(e.getMessage());
        }

        logger.debug("end findEverything");

        return result;
    }
}
