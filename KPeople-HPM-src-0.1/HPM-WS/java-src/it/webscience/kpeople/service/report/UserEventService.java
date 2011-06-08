package it.webscience.kpeople.service.report;

import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.ProcessEventManager;
import it.webscience.kpeople.bll.impl.UserEventManager;
import it.webscience.kpeople.service.datatypes.ProcessEvent;
import it.webscience.kpeople.service.datatypes.UserEvent;
import it.webscience.kpeople.service.datatypes.converter.ProcessEventConverter;
import it.webscience.kpeople.service.datatypes.converter.UserEventConverter;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author depascalis.
 */
public class UserEventService {

    /** logger. */
    private Logger logger;

    /**
     * Costruttore della classe.
     */
    public UserEventService() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Recupera gli utenti creatori degli eventi.
     * @return lista di UserEvent.
     * @throws KPeopleServiceException
     *             eccezione durante l'elaborazione.
     */
    public final UserEvent[] getUserEventsCreator()
            throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("getUserEventsCreator - Begin");
        }
        UserEventManager mng = new UserEventManager();

        UserEvent[] userEvents = null;

        try {

            List<it.webscience.kpeople.be.UserEvent> userEventsBe = mng
                    .getUserEventsCreator();

            userEvents = UserEventConverter.toService(userEventsBe);

        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }
        return userEvents;
    }

    /**
     * Recupera gli utenti destinatari di comunicazioni(TO).
     * @return lista di UserEvent.
     * @throws KPeopleServiceException
     *             eccezione durante l'elaborazione.
     */
    public final UserEvent[] getUserEventsCommunicationTo()
            throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("getUserEventsCommunicationTo - Begin");
        }
        UserEventManager mng = new UserEventManager();

        UserEvent[] userEvents = null;

        try {

            List<it.webscience.kpeople.be.UserEvent> userEventsBe = mng
                    .getUserEventsCommunicationTo();

            userEvents = UserEventConverter.toService(userEventsBe);

        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }
        return userEvents;
    }

    /**
     * Recupera gli utenti destinatari di comunicazioni(CC).
     * @return lista di UserEvent.
     * @throws KPeopleServiceException
     *             eccezione durante l'elaborazione.
     */
    public final UserEvent[] getUserEventsCommunicationCC()
            throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("getUserEventsCommunicationCC - Begin");
        }
        UserEventManager mng = new UserEventManager();

        UserEvent[] userEvents = null;

        try {

            List<it.webscience.kpeople.be.UserEvent> userEventsBe = mng
                    .getUserEventsCommunicationCC();

            userEvents = UserEventConverter.toService(userEventsBe);

        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }
        return userEvents;
    }

    /**
     * Recupera gli utenti destinatari di comunicazioni(From).
     * @return lista di UserEvent.
     * @throws KPeopleServiceException
     *             eccezione durante l'elaborazione.
     */
    public final UserEvent[] getUserEventsCommunicationFrom()
            throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("getUserEventsCommunicationFrom - Begin");
        }
        UserEventManager mng = new UserEventManager();

        UserEvent[] userEvents = null;

        try {

            List<it.webscience.kpeople.be.UserEvent> userEventsBe = mng
                    .getUserEventsCommunicationFrom();

            userEvents = UserEventConverter.toService(userEventsBe);

        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }
        return userEvents;
    }


}
