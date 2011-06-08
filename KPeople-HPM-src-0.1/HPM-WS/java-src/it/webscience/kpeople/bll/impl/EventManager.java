package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.IEventManager;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.proxy.EventDAOProxy;
import it.webscience.kpeople.be.EventFilter;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author depascalis
 * Classe di business per effettuare le ricerche.
 */

public class EventManager implements IEventManager {
    /**
     * logger.
     */
    private Logger logger;

    /**
     * Costruttore della classe.
     */
    public EventManager() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * @param eventFilter business entity be contenente info di ricerca.
     * @param user utente che ha richiesto il servizio.
     * @return lista di Eventi di tipo be.
     * @throws KPeopleBLLException eccezione durante l'elaborazione.
     */
    public final List<Event> getEvents(
            final EventFilter eventFilter, final User user)
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getEvents");
        }

        EventDAOProxy dao = new EventDAOProxy();
        List<Event> daoResult = null;
        try {
            daoResult = dao.getEvents(eventFilter, user);

        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }
        return daoResult;

    }

}
