package it.webscience.kpeople.service.cross;

import it.webscience.kpeople.bll.cross.ISearchManager;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.SearchManager;
import it.webscience.kpeople.bll.proxy.UserDAOProxy;
import it.webscience.kpeople.dal.cross.IUserDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.datatypes.converter.GenericConverter;
import it.webscience.kpeople.service.datatypes.converter.UserConverter;
import it.webscience.kpeople.service.datatypes.dto.KPeopleGenericDTO;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

/**
 * Servizio per la ricerca globale full text.
 */
public class GlobalSearchService {

    /** logger. */
    private static final Logger LOGGER =
        Logger.getLogger(GlobalSearchService.class);

    /**
     * Metodo per la ricerca full text globale.
     * @param freeText testo da cercare
     * @param user utente della richiesta
     * @return elenco di business entity trovate
     * @throws KPeopleServiceException eccezione nel servizio
     */
    public final KPeopleGenericDTO[] find(
            final String freeText, final User user)
        throws KPeopleServiceException {

        List<Object> genericBE = null;

        LOGGER.debug("find - begin");

        final ISearchManager mng = new SearchManager();

        IUserDAO udp = new UserDAOProxy();

        it.webscience.kpeople.be.User userBE = UserConverter.toBE(user);

        try {
            udp.getUserByHpmUserId(userBE.getHpmUserId());
        } catch (KPeopleDAOException e) {
            throw new KPeopleServiceException(e.getMessage());
        } catch (NamingException e) {
            throw new KPeopleServiceException("Utente non trovato");
        }

        try {
            genericBE = mng.findEverything(freeText);
        } catch (KPeopleBLLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new KPeopleServiceException(e);
        }

        final KPeopleGenericDTO[] results =
            GenericConverter.toService(genericBE);

        LOGGER.debug("find - end");
        LOGGER.debug("Elementi trovati: " + results.length);

        return results;
    }
}
