package it.webscience.kpeople.service.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.keyword.KeywordFactory;
import it.webscience.kpeople.service.datatypes.Keyword;
import it.webscience.kpeople.service.datatypes.Process;
import it.webscience.kpeople.service.datatypes.ProcessFilter;
import it.webscience.kpeople.service.datatypes.ProcessState;
import it.webscience.kpeople.service.datatypes.ProcessType;
import it.webscience.kpeople.service.datatypes.SortCriteria;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import org.junit.Test;

import com.mchange.util.AssertException;

public class FindEnabledUserTest {

    /**
     * test del metodo getProcessByHpmId.
     */
    @Test
    public void testFindEnabledUser() {

        ProcessService service = new ProcessService();

        User user = new User();
        user.setIdUser(4);
        user.setHpmUserId("filieri@kpeople.webscience.it");

        Process current = null;
        try {
            current = service.getProcessByHpmId("prg1154", user);
            User[] enabledUser = service.findEnabledUsers(current);
            assertEquals(1, enabledUser.length);
        } catch (KPeopleServiceException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

}
