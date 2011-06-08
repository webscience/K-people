package it.webscience.kpeople.dal.report;

import it.webscience.kpeople.be.ProcessEvent;
import it.webscience.kpeople.be.UserEvent;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.util.List;

/**
 * @author depascalis.
 */
public interface IUserEventDAO {

    /**
     * Utenti creatori degli eventi.
     * @return lista di UserEvent.
     * @throws KPeopleDAOException eccezione generata durante l'elaborazione.
     */
    List<UserEvent>getUserEventsCreator()throws KPeopleDAOException;

    /**
     * Utenti destinatari di comunicazioni (TO).
     * @return lista di UserEvent.
     * @throws KPeopleDAOException eccezione durante l'elaborazione.
     */
    List<UserEvent>getUserEventsCommunicationTo()throws KPeopleDAOException;

    /**
     * Uetnti destinatari di comunicazioni(CC).
     * @return lista di UserEvent.
     * @throws KPeopleDAOException eccezione generata durante l'elaborazione.
     */
    List<UserEvent>getUserEventsCommunicationCC()throws KPeopleDAOException;


}
