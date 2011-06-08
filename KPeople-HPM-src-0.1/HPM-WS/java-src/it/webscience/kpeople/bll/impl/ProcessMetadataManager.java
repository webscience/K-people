package it.webscience.kpeople.bll.impl;

import java.sql.SQLException;

import it.webscience.kpeople.be.ProcessMetadataSet;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.proxy.ProcessMetadataDAOProxy;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import org.apache.log4j.Logger;

public class ProcessMetadataManager {

    /**
     * Logger.
     */
    private Logger logger;

    /**
     * Costruttore della classe.
     */
    public ProcessMetadataManager() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * @param user user che effettua la chiamata
     * @return elenco dei metadati associati al processo
     * @throws KPeopleBLLException eccezione durante l'elaborazione
     * @throws SQLException 
     */
    public final ProcessMetadataSet getMetadataProcess(final User user)
            throws KPeopleBLLException {
        if (logger.isDebugEnabled()) {
            logger.debug("start getMetadataProcess");
        }
        ProcessMetadataDAOProxy dao = new ProcessMetadataDAOProxy();
        ProcessMetadataSet daoResult = null;
        try {
            daoResult = dao.getMetadataProcess(null);
            }
        catch(SQLException e){
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }
        return daoResult;
    }

}
