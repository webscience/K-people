package it.webscience.kpeople.bll.proxy;

import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.cross.IUserDAO;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

/**
 * Proxy per la classe UserDAO.
 * @author bolognese
 */
public class UserDAOProxy implements IUserDAO {

    @Override
    public final User getUserByIdUser(final int idUser) throws SQLException {
        UserDAO userDao = new UserDAO();

        return userDao.getUserByIdUser(idUser);
    }

    @Override
    public final User getUserByHpmUserId(final String hpmUserId)
            throws NamingException, KPeopleDAOException {

        UserDAO userDao = new UserDAO();

        return userDao.getUserByHpmUserId(hpmUserId);
    }

    @Override
    public List<User> getUserByUsername(String username) {
        UserDAO userDao = new UserDAO();

        return userDao.getUserByUsername(username);
    }
}
