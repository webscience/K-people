/**
 * 
 */
package it.webscience.kpeople.service.cross;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.webscience.kpeople.service.datatypes.User;

import org.junit.Test;

/**
 * @author bolognese
 */
public class UserServiceTest {

    /**
     * test del servizio getUserByHpmUserId passando un tente presente in LDAP.
     * @throws Exception
     *             eccezione generica
     */
    @Test
    public final void testGoodUserService() throws Exception {

        UserService us = new UserService();
        String hpmUserId = "utente1@aicanet.it";
        it.webscience.kpeople.service.datatypes.User usData =
                us.getUserByHpmUserId(hpmUserId);
        assertTrue(usData != null);

    }

    /**
     * test del servizio getUserByUsername.
     * @throws Exception
     *             eccezione generica
     */
    @Test
    public final void testGetUserByUsername() throws Exception {


        
//        1. test di estrazione dell'account di Dino
        UserService us = new UserService();
        
        User u = us.getUserByHpmUserId("utente1@aicanet.it");
        
        String email = "utente1@aicanet.it";
//        us.getUserByUsername(email, null);
        
//        2. test di estrazione di tutti gli account
        us.getUserByUsername(null, u);
    } 

    /**
     * test del servizio getUserByHpmUserId passando un tente non presente in
     * LDAP.
     * @throws Exception
     *             eccezione generica
     */
    @Test(expected = it.webscience.kpeople.service.exception.KPeopleServiceException.class)
    public final
            void testBadUserService() throws Exception {

        UserService us = new UserService();
        String hpmUserId = null;
        us.getUserByHpmUserId(hpmUserId);

    }

}
