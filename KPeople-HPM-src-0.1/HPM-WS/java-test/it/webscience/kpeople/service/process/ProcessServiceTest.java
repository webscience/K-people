package it.webscience.kpeople.service.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.webscience.kpeople.service.datatypes.Process;
import it.webscience.kpeople.service.datatypes.ProcessFilter;
import it.webscience.kpeople.service.datatypes.SortCriteria;
import it.webscience.kpeople.service.datatypes.User;

import java.util.Date;

import org.junit.Test;

/**
 * Classe di test del ProcessService.
 *
 */
public class ProcessServiceTest {

    /**
     * Test del metodo getOwners.
     */
    @Test
    public final void testGetOwners() {

        try {
            ProcessService service = new ProcessService();

            User[] owners = service.getOwners(new User());

            assertTrue(owners != null);
            assertTrue(owners.length > 0);

//          controllo del primo risultato
            assertTrue(owners[0].getAccount().equals("dellannaAcc"));
            assertTrue(owners[0].getUsername().equals("dellanna"));
            assertTrue(owners[0].getHpmUserId().equals("202"));
            assertTrue(owners[0].getIdUser() == 2);

            assertTrue(owners[1].getAccount().equals("filieriAcc"));
            assertTrue(owners[1].getUsername().equals("filieri"));
            assertTrue(owners[1].getHpmUserId().equals("203"));
            assertTrue(owners[1].getIdUser() == 3);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFindProcessesByFreeText() {

        try {
            ProcessFilter filter = new ProcessFilter();

            ProcessService service = new ProcessService();

            SortCriteria sort = new SortCriteria();
            sort.setFieldName(SortCriteria.ORDER_BY_CREATION_DATE);
            sort.setOrder(SortCriteria.ASC);

            filter.setFreeText("");
            filter.setSort(sort);

            it.webscience.kpeople.service.datatypes.Process[] processes =
                service.findProcesses(filter, new User());

            if (processes.length > 0) {
                for (int i = 0; i < processes.length; i++) {
                    Process p = processes[i];

                    assertTrue(p.getName().contains("Test") ||
                            p.getDescription().contains("Test"));
                }
            } else {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFindProcessesByDeleted() {

        try {
            ProcessFilter filter = new ProcessFilter();

            ProcessService service = new ProcessService();

            filter.setDeleted("FALSE");

            it.webscience.kpeople.service.datatypes.Process[] processes = service
                    .findProcesses(filter, new User());

            if (processes.length > 0) {
                for (int i = 0; i < processes.length; i++) {
                    Process p = processes[i];

                    assertTrue(!p.isDeleted());
                }
            } else {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testFindProcessesByVisibility() {

        try {
            ProcessFilter filter = new ProcessFilter();

            ProcessService service = new ProcessService();

            filter.setVisibility("TRUE");

            it.webscience.kpeople.service.datatypes.Process[] processes = service
                    .findProcesses(filter, new User());

            if (processes.length > 0) {
                for (int i = 0; i < processes.length; i++) {
                    Process p = processes[i];

                    assertTrue(!p.isPrivate());
                }
            } else {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testFindProcessesByState() {

        try {
            ProcessFilter filter = new ProcessFilter();

            ProcessService service = new ProcessService();

//            filter.setState("OPEN");

            it.webscience.kpeople.service.datatypes.Process[] processes = service
                    .findProcesses(filter, new User());

            if (processes.length > 0) {
                for (int i = 0; i < processes.length; i++) {
                    Process p = processes[i];

                    assertTrue(p.isActive());
                }
            } else {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testFindProcessesByType() {

        try {
            ProcessFilter filter = new ProcessFilter();

            ProcessService service = new ProcessService();

            filter.setType("DELEGA");

            it.webscience.kpeople.service.datatypes.Process[] processes = service
                    .findProcesses(filter, new User());

            if (processes.length > 0) {
                for (int i = 0; i < processes.length; i++) {
                    Process p = processes[i];

                    assertEquals(1, p.getProcessType().getIdProcessType());
                }
            } else {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFindProcessesByUserId() {

        try {
            ProcessFilter filter = new ProcessFilter();

            ProcessService service = new ProcessService();
            filter.setFreeText("Elton");
            filter.setSort(new SortCriteria());
            filter.getSort().setFieldName("ORDER_BY_LAST_UPDATE");
            filter.setUserId("4");

            it.webscience.kpeople.service.datatypes.Process[] processes = service
                    .findProcesses(filter, new User());

            if (processes.length > 0) {
                for (int i = 0; i < processes.length; i++) {
                    Process p = processes[i];

                    assertEquals(1, p.getOwner().getIdUser());
                }
            } else {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testFindProcessesByDateCreated() {

        try {
            ProcessFilter filter = new ProcessFilter();

            ProcessService service = new ProcessService();

            SortCriteria sort = new SortCriteria();
            sort.setFieldName(SortCriteria.ORDER_BY_CREATION_DATE);
            sort.setOrder(SortCriteria.ASC);

            filter.setCreationDateFrom(new Date(110, 0, 1));
            filter.setCreationDateTo(new Date(112, 0, 1));

            filter.setSort(sort);

            it.webscience.kpeople.service.datatypes.Process[] processes =
                service.findProcesses(filter, new User());

            if (processes.length > 0) {
                for (int i = 0; i < processes.length; i++) {
                    Process p = processes[i];

                    assertTrue(p.getDateCreated().after(new Date(110, 0, 1))
                            && p.getDateCreated().before(new Date(112, 0, 1)));
                }
            } else {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testFindProcessesByDateDue() {

        try {
            ProcessFilter filter = new ProcessFilter();

            ProcessService service = new ProcessService();

            SortCriteria sort = new SortCriteria();
            sort.setFieldName(SortCriteria.ORDER_BY_DUE_DATE);
            sort.setOrder(SortCriteria.ASC);

            filter.setDueDateFrom(new Date(110, 0, 1));
            filter.setDueDateTo(new Date(112, 0, 1));

            filter.setSort(sort);

            it.webscience.kpeople.service.datatypes.Process[] processes =
                service.findProcesses(filter, new User());

            if (processes.length > 0) {
                for (int i = 0; i < processes.length; i++) {
                    Process p = processes[i];

                    assertTrue(p.getDateDue().after(new Date(110, 0, 1))
                            && p.getDateDue().before(new Date(112, 0, 1)));
                }
            } else {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testFindEnabledUsers() {
        ProcessService service = new ProcessService();
        try {
            User[] users = service.findEnabledUsers(new Process());
            assertTrue(users != null);
            assertTrue(users.length > 0);
            assertTrue(users[0].getAccount().equalsIgnoreCase("dellanna"));
            assertTrue(users[0].getHpmUserId().equalsIgnoreCase("dellanna@ws.local"));
            assertEquals(3, users[0].getIdUser());
            assertTrue(users[0].getUsername().equalsIgnoreCase("dellanna"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        
    }
    

    

}
