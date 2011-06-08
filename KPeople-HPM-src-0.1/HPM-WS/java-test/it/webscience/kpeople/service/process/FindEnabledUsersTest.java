package it.webscience.kpeople.service.process;

import static org.junit.Assert.*;

import org.junit.Test;
import it.webscience.kpeople.service.datatypes.Process;
import it.webscience.kpeople.service.datatypes.User;

public class FindEnabledUsersTest {

   @Test
   public void findEnabledUsers() {

       ProcessService  service = new ProcessService();

       Process process = new Process();

       process.setHpmProcessId("RDA_Prova");
       try {
       User [] users = service.findEnabledUsers(process);
           assertEquals("Antonio Spalluto", users[0].getScreenName());
           assertEquals("Giorgio Chiriaco", users[1].getScreenName());
           assertEquals("Fabio Dell'Anna", users[2].getScreenName());
       } catch (Exception e) {
           e.printStackTrace();
           fail();
       }
   }

}
