package it.webscience.kpeople.service.process;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.webscience.kpeople.service.datatypes.ProcessType;
import it.webscience.kpeople.service.datatypes.User;

import org.junit.Test;

/**
 * Classe di test del ProcessTypeService.
 *
 */
public class ProcessTypeServiceTest {

    /**
     * Test del metodo getProcessTypes.
     */
    @Test
    public final void getProcessTypes() {

        try {
            ProcessTypeService service = new ProcessTypeService();

            ProcessType[] pt = service.getProcessTypes(new User());
            
            assertTrue(pt != null);
            assertTrue(pt.length == 3);

            assertTrue(pt[0].getIdProcessType() == 1);
            assertTrue(pt[0].getName().equals("Process Type 1"));
            assertTrue(!pt[0].isDeleted());

            assertTrue(pt[1].getIdProcessType() == 2);
            assertTrue(pt[1].getName().equals("Process Type 2"));
            assertTrue(pt[1].isDeleted());
            assertTrue(pt[1].getDeletedBy().getIdUser() == 1);
            assertTrue(pt[1].getFirstActionPerformer().getIdUser() == 2);
            assertTrue(pt[1].getFirstActionDate().getTime() == 1295478000000L);
            assertTrue(pt[1].getLastActionPerformer().getIdUser() == 2);
            assertTrue(pt[1].getLastActionDate().getTime() == 1295564400000L);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
