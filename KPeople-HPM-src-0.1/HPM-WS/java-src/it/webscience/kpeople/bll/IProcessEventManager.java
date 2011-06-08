package it.webscience.kpeople.bll;

import it.webscience.kpeople.be.ProcessEvent;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;

import java.util.List;

/**
 * @author depascalis
 */
public interface IProcessEventManager {

    /**
     * @return la lista di ProcessEventResult
     * @throws KPeopleBLLException eccezione generata durante l'esecuzione.
     */
    List<ProcessEvent> getProcessEvents() throws KPeopleBLLException;

}
