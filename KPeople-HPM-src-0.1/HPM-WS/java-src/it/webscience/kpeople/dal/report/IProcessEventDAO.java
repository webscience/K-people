package it.webscience.kpeople.dal.report;

import it.webscience.kpeople.be.ProcessEvent;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.util.List;

/**
 * @author depascalis
 * Interfaccia per la classe DAO relativa a ProcessEvent.
 */
public interface IProcessEventDAO {

    /**
     * @return lista di ProcessEvent.
     * @throws KPeopleDAOException eccezione generata durante l'elaborazione.
     */
    List<ProcessEvent>getProcessEvents()throws KPeopleDAOException;

}
