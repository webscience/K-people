package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.be.ProcessEvent;
import it.webscience.kpeople.be.UserEvent;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.util.List;

public interface IUserEventManager {
    /**
     * @return la lista di UserEvent
     * @throws KPeopleBLLException eccezione generata durante l'esecuzione.
     */
    List<UserEvent> getUserEventsCreator() throws KPeopleBLLException;
    
    List<UserEvent> getUserEventsCommunicationTo() throws KPeopleBLLException;
    
    List<UserEvent> getUserEventsCommunicationCC() throws KPeopleBLLException;
    
    List<UserEvent> getUserEventsCommunicationFrom()throws KPeopleBLLException;
    
    


}
