package it.webscience.kpeople.bll.proxy;

import java.util.List;

import it.webscience.kpeople.be.ProcessEvent;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.report.IProcessEventDAO;
import it.webscience.kpeople.dal.report.ProcessEventDAO;

/**
 * @author depascalis
 * Proxy per la classe ProcessEventDAO
 *
 */
public class ProcessEventDAOProxy implements IProcessEventDAO{

    public final List<ProcessEvent>getProcessEvents()
    throws KPeopleDAOException {
        ProcessEventDAO dao = new ProcessEventDAO();

        return dao.getProcessEvents();
    }

}
