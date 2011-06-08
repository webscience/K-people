package it.webscience.kpeople.service.process;

import java.sql.SQLException;

import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.ProcessMetadataManager;
import it.webscience.kpeople.service.datatypes.ProcessMetadataSet;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.datatypes.converter.ProcessMetadataSetConverter;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

/**
 * @author depascalis Servizio per ottenere la lista dei metadati relativi ai
 *         processi.
 */

public class ProcessMetadataService {

    /**
     * Logger.
     */
    private Logger logger;

    /**
     * Costruttore della classe.
     */
    public ProcessMetadataService() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * restituisce l'elenco dei metadati associati al processo.
     * @param user user che esegue la chiamata.
     * @return oggetto di tipo OMElement contenente l'elenco dei metadati.
     * @throws KPeopleServiceException eccezione durante l'elaborazione.
     * @throws SQLException 
     */
    public final ProcessMetadataSet getMetadataProcessV1(final User user)
            throws KPeopleServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("getMetadataProcessV1 - Begin");
        }

        ProcessMetadataManager mng = new ProcessMetadataManager();
        ProcessMetadataSet metadataSet = null;
        try {
            it.webscience.kpeople.be.ProcessMetadataSet metadatasetBe = mng
                    .getMetadataProcess(null);
            metadataSet = ProcessMetadataSetConverter.toService(metadatasetBe);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }
        if (logger.isDebugEnabled()) {
            logger.debug("getMetadataProcessV1 - End. ");
        }
        return metadataSet;
    }
    
    /*private OMElement convertProcessMetadataSet(ProcessMetadataSet metadataSet) {
        String xml = null;
        xml += "<?xml version='1.0' encoding='UTF-8'?>" + "\n";
        xml += "<MetaDataSet xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance"
        
        return null;
        
    }*/
}
