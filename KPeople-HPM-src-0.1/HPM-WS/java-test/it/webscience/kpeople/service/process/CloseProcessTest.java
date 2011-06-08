package it.webscience.kpeople.service.process;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import it.webscience.kpeople.service.datatypes.Process;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.exception.KPeopleServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class CloseProcessTest {

    private String hpmProcessId = "";

    @Parameters
    public static List<Object[]> param() {
//        String a = "rda1130";
//        String b = "rda1131";
//        String c = "rda1131";
//        String d = "rda1131";
//        String e = "rda1131";
//        String f = "rda1131";
//        String g = "rda1131";
//        String h = "rda1131";
//        return Arrays.asList(new Object[][] { { a }, { b } , { c }, { d }, { e }, { f }, { g }, { h } });
        
        String a = "prg1154";
       
        return Arrays.asList(new Object[][] { { a }});
        
    }

    public CloseProcessTest(String hpmProcessId) {
        this.hpmProcessId = hpmProcessId;
    }

    /**
     * test del metodo closeProcess.
     */
    @Test
    public void closeProcessTest() {
        ProcessService service = new ProcessService();

        User user = new User();
        user.setIdUser(4);
        user.setHpmUserId("filieri@local.it");

        Process current = null;
        try {
            current = service.getProcessByHpmId(hpmProcessId, user);
        } catch (KPeopleServiceException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        boolean result;
        try {
            result = service.closeProcess(current, user);
            current = service.getProcessByHpmId(hpmProcessId, user);
            assertEquals(false, current.isActive());
        } catch (KPeopleServiceException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

}
