package it.webscience.kpeople.service.event;

import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.EventManager;
import it.webscience.kpeople.service.datatypes.Event;
import it.webscience.kpeople.service.datatypes.EventFilter;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.datatypes.converter.EventConverter;
import it.webscience.kpeople.service.datatypes.converter.EventFilterConverter;
import it.webscience.kpeople.service.datatypes.converter.UserConverter;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author depascalis
 * Servizio per la restituzione degli eventi associati al processo.
 */
public class EventService {

    /** logger. */
    private Logger logger;

    /**
     * Costruttore della classe.
     */
    public EventService() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * @param eventFilter business Entity contenente l'elenco dei
     * parametri di ricerca.
     * @param user utente che ha richiesto gli eventi.
     * @return un array di Event.
     * @throws KPeopleServiceException eccezione durante l'elaborazione.
     */
    public final Event[] getEvents(final EventFilter eventFilter,
            final User user)
        throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("getEvents - Begin");
        }
        EventManager mng = new EventManager();

        Event[] events = null;

        try {
             it.webscience.kpeople.be.User userBe = UserConverter.toBE(user);
             it.webscience.kpeople.be.EventFilter eventFilterBe =
                 EventFilterConverter.toBE(eventFilter);

            List<it.webscience.kpeople.be.Event> eventsBe =
                mng.getEvents(eventFilterBe, userBe);

            events = EventConverter.toService(eventsBe);

        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        return events;
    }
}
