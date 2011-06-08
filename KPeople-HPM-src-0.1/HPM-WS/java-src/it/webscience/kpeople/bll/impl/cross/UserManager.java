package it.webscience.kpeople.bll.impl.cross;

import java.util.List;

import org.apache.log4j.Logger;

import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.cross.IUserManager;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.proxy.UserDAOProxy;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

/**
 * classe di business per lo user.
 * @author bolognese
 */
public class UserManager implements IUserManager {

    /** logger. */
    private Logger logger;

    /**
     * costruttore std.
     */
    public UserManager() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * @see
     * it.webscience.kpeople.bll.cross.IUserManager#getUserByHpmUserId(java.
     * lang.String)
     */
    @Override
    public final User getUserByHpmUserId(final String hpmUserId)
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getUserByHpmUserId - BEGIN");
        }

        User user = null;
        UserDAOProxy daoP = new UserDAOProxy();
        try {
            user = daoP.getUserByHpmUserId(hpmUserId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getUserByHpmUserId - OVER");
        }

        return user;
    }

    /**
     * ritorna l'oggetto User associato allo username desiderato o una lista
     * completa di tutto gli utenti.
     * @param username username da ricercare.
     *          null se si vuole estrarre la lista completa
     * @return user user che esegue la chiamata
     * @throws KPeopleServiceException eccezione durante l'elaborazione
     */
    @Override
    public final List<User> getUserByUsername(
            final String username,
            final User user) throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getUserByUsername - BEGIN");
        }

        List<User> userList = null;
        UserDAOProxy daoP = new UserDAOProxy();
        try {
            userList = daoP.getUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getUserByUsername - OVER");
        }

        return userList;
    }
}
