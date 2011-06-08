package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.be.ProcessEvent;
import it.webscience.kpeople.bll.IProcessEventManager;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.proxy.ProcessEventDAOProxy;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author depascalis
 */
public class ProcessEventManager implements IProcessEventManager {

    /**
     * logger.
     */
    private Logger logger;

    /**
     * Costruttore della classe.
     */
    public ProcessEventManager() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    
    public final List<ProcessEvent> getProcessEvents()
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getProcessEvents");
        }
        ProcessEventDAOProxy dao = new ProcessEventDAOProxy();
        List<ProcessEvent> daoResult = null;
        try {
            daoResult = dao.getProcessEvents();
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }
        return daoResult;

    }

}
