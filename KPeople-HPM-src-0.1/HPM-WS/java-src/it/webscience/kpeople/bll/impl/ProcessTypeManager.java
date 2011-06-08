package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.be.ProcessType;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.IProcessTypeManager;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.proxy.ProcessTypeDAOProxy;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Classe di business per effettuare le ricerche.
 */
public class ProcessTypeManager implements IProcessTypeManager {
    /** logger. */
    private Logger logger;

    /**
     * Costruttore di default.
     */
    public ProcessTypeManager() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Restituisce gli l'elenco completo dei ProcessType.
     * @param user user che esegue la chiamata
     * @return elenco di ProcessType
     * @throws KPeopleBLLException eccezione durante l'elaborazione
     */
    public final List<ProcessType> getProcessTypes(final User user)
            throws KPeopleBLLException {
        if (logger.isDebugEnabled()) {
            logger.debug("start getProcessTypes");
        }

        ProcessTypeDAOProxy dao = new ProcessTypeDAOProxy();
        List<ProcessType> daoResult = null;
        try {
            daoResult = dao.getProcessTypes(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end getProcessTypes");
        }

        return daoResult;
    }

}
