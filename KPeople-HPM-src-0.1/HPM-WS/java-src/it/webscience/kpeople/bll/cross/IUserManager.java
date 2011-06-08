/**
 * 
 */
package it.webscience.kpeople.bll.cross;

import java.util.List;

import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

/**
 * interfaccia dello user manager.
 * @author bolognese
 */
public interface IUserManager {

    /**
     * ritorna l'oggetto User associato alla chiave desiderata.
     * @param hpmUserId chiave univoca
     * @return User user
     * @throws KPeopleBLLException
     *           eccezione durante l'elaborazione
     */
    User getUserByHpmUserId(final String hpmUserId)
            throws KPeopleBLLException;

    /**
     * ritorna l'oggetto User associato allo username desiderato o una lista
     * completa di tutto gli utenti.
     * @param username username da ricercare.
     *          null se si vuole estrarre la lista completa
     * @return user user che esegue la chiamata
     * @throws KPeopleServiceException eccezione durante l'elaborazione
     */
    List<User> getUserByUsername(String username, User user)
            throws KPeopleBLLException;
}
