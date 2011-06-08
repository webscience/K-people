/**
 * 
 */
package it.webscience.kpeople.service.cross;

import java.util.List;

import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.cross.UserManager;
import it.webscience.kpeople.service.datatypes.converter.UserConverter;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import org.apache.log4j.Logger;

/**
 * Servizio per la gestione degli utenti.
 * @author bolognese
 */
public class UserService {

    /** logger. */
    private Logger logger;

    /**
     * costruttore std.
     */
    public UserService() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * ritorna l'oggetto User datatype associato alla chiave desiderata.
     * @param hpmUserId
     *            chiave user
     * @return user
     * @throws KPeopleServiceException
     *             eccezione durante l'elaborazione
     */
    public final it.webscience.kpeople.service.datatypes.User
            getUserByHpmUserId(final String hpmUserId)
                    throws KPeopleServiceException {
        it.webscience.kpeople.service.datatypes.User userData = null;
        User user = null;

        if (logger.isDebugEnabled()) {
            logger.debug("Service getUserByHpmUserId START: " + hpmUserId);
        }

        UserManager userManager = new UserManager();

        try {
            user = userManager.getUserByHpmUserId(hpmUserId);
            userData = UserConverter.toService(user);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Service getUserByHpmUserId END: " + hpmUserId);
        }
        return userData;
    }

    /**
     * ritorna l'oggetto User associato allo username desiderato o una lista
     * completa di tutto gli utenti.
     * @param username username da ricercare.
     *          null se si vuole estrarre la lista completa
     * @return user user che esegue la chiamata
     * @throws KPeopleServiceException eccezione durante l'elaborazione
     */
    public final it.webscience.kpeople.service.datatypes.User[]
            getUserByUsername(
                final String username,
                final it.webscience.kpeople.service.datatypes.User user)
            throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("Service getUserByUsername START: " + username);
        }

        it.webscience.kpeople.service.datatypes.User[] userList = null;

        UserManager userManager = new UserManager();

        try {
//          esegue la chiamata all'user manager
            List<User> userListBe =
                    userManager.getUserByUsername(
                            username,
                            UserConverter.toBE(user));
            userList = UserConverter.toService(userListBe);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Service getUserByUsername END.");
        }

        return userList;
    }
}
