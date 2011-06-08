package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

/**
 * Interfaccia per la classe DAO relativa agli utenti.
 */
public interface IUserDAO {
    /**
     * ritorna l'oggetto User associato alla chiave desiderata.
     * @param idUser
     *            chiave
     * @throws SQLException
     *             eccezioni db
     * @return oggetto Keyword
     */
    User getUserByIdUser(final int idUser) throws SQLException;

    /**
     * ritorna l'oggetto User associato alla chiave desiderata.
     * @param hpmUserId
     *            chiave univoca
     * @return oggetto User
     * @throws NamingException
     *            eccezioni ldap
     * @throws KPeopleDAOException errore nel dao
     */
    User getUserByHpmUserId(final String hpmUserId) throws KPeopleDAOException,
        NamingException;

    /**
     * ritorna l'oggetto User associato allo username desiderato o una lista
     * completa di tutto gli utenti.
     * @param username username da ricercare.
     *          null se si vuole estrarre la lista completa
     * @throws KPeopleServiceException eccezione durante l'elaborazione
     */
    List<User> getUserByUsername(String username);
}
