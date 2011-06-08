package it.webscience.kpeople.dal.cross;

import static org.junit.Assert.assertTrue;

import java.util.List;

import it.webscience.kpeople.be.User;

import org.junit.Test;

/**
 * JUnit del dao user.
 * @author bolognese
 */
public class SearchDAOTest {

    /**
     * test del metodo DAO getUserByHpmUserId passando un tente presente in
     * LDAP.
     * @throws Exception
     *             eccezione generica
     */
    @Test
    public final void testFind() throws Exception {
        SearchDAO searchDao = new SearchDAO();
        String freeText = "Di seguito vengono elencate tutte i punti";
        List<Object> results = searchDao.find(freeText);
        assertTrue(results != null);
        assertTrue(results.size() > 0);
    }

}
