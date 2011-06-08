package it.webscience.kpeople.dal.cross;

import static org.junit.Assert.assertTrue;
import it.webscience.kpeople.be.User;

import org.junit.Test;

/**
 * JUnit del dao user.
 * @author bolognese
 */
public class UserDAOTest {

    /**
     * test del metodo DAO getUserByHpmUserId passando un tente presente in
     * LDAP.
     * @throws Exception
     *             eccezione generica
     */
    @Test
    public final void testGoodGetUserByHpmUserId() throws Exception {
        UserDAO userDao = new UserDAO();
        String hpmUserId = "bolognese@kpeople.webscience.it";
        User user = userDao.getUserByHpmUserId(hpmUserId);
        assertTrue(user != null);
    }

    /**
     * test del metodo DAO getUserByHpmUserId passando un tente non presente in
     * LDAP.
     * @throws Exception
     *             eccezione generica
     */
    @Test(expected = javax.naming.NamingException.class)
    public final void testBadGetUserByHpmUserId() throws Exception {
        UserDAO userDao = new UserDAO();
        String hpmUserId = null;
        userDao.getUserByHpmUserId(hpmUserId);
    }

}
