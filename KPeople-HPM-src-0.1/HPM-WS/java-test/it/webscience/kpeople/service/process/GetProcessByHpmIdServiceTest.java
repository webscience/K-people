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

public class GetProcessByHpmIdServiceTest {

    /**
     * test del metodo getProcessByHpmId.
     */
    @Test
    public void testGetProcessByHpmId() {
        try {

            String hpmProcessId = "rda1177";

            ProcessService service = new ProcessService();

            User prova = new User();
            prova.setIdUser(4);

            Keyword[] key = new Keyword[10];

            Process process = service.getProcessByHpmId(hpmProcessId, prova);

            if (process != null) {

                assertEquals("Teca", process.getName());
                assertEquals("Ivano Gnoni", process.getOwner().getScreenName());
                assertEquals("Progetto", process.getProcessType().getName());
                assertEquals("Progetto", process.getKeywords());

            } else {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }



}
