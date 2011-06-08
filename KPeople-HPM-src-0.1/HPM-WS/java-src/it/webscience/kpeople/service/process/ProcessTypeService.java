package it.webscience.kpeople.service.process;

import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.ProcessTypeManager;
import it.webscience.kpeople.service.datatypes.ProcessType;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.datatypes.converter.ProcessTypeConverter;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * Servizio per la gestione dei tipi di processi.
 */
public class ProcessTypeService {
    /** logger. */
    private Logger logger;

    /**
     * Costruttore di default.
     */
    public ProcessTypeService() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Restituisce l'elenco completo di oggetti ProcessType.
     * @param user user che esegue la chiamata
     * @return elenco di ProcessType
     * @throws KPeopleServiceException eccezione durante l'elaborazione
     */
    public final ProcessType[] getProcessTypes(final User user)
            throws KPeopleServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("getProcessTypes - Begin");
        }

        ProcessTypeManager mng = new ProcessTypeManager();

        ProcessType[] types = null;
        try {
//            it.webscience.kpeople.be.User userBe = UserConverter.toBE(user);

            List<it.webscience.kpeople.be.ProcessType> typesBe =
                mng.getProcessTypes(null);
            types = ProcessTypeConverter.toService(typesBe);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getProcessTypes - End. Length: " + types.length);
        }

        return types;
    }
}
