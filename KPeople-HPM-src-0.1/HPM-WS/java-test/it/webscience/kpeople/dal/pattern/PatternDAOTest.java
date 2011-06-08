package it.webscience.kpeople.dal.pattern;

import static org.junit.Assert.assertEquals;
import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.process.ProcessDAO;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;



/**
 * JUnit del dao PatternDao.
 * @author gnoni
 */
public class PatternDAOTest {

	@Parameters
    public static List<Object[]> param() {
        boolean a = true;
        boolean b = false;
        
        return Arrays.asList(new Object[][] { { a }, { b } });
           
    }
	
	/**
     * test del metodo DAO getPatternTypes. 
     * @throws Exception
     *             eccezione generica
     */
    @Test
    public final void getPatternTypesTest() throws Exception {
        PatternDAO patternDao = new PatternDAO();
        List<PatternType> patternTypes = null;
        UserDAO userDao=new UserDAO();
        User user=userDao.getUserByHpmUserId("bolognese@kpeople.webscience.it");
        patternTypes = patternDao.getPatternTypes(true,user);
        assertEquals(6, patternTypes.size());
    }
    
    @Test
    public final void startPatternTest() throws Exception {
    	PatternDAO patternDao = new PatternDAO();
    	Pattern p=new Pattern();
    	UserDAO userDao=new UserDAO();
    	User provider = userDao.
    		getUserByHpmUserId("nicoli@kpeople.webscience.it");
    	User requestor = userDao.
    		getUserByHpmUserId("bolognese@kpeople.webscience.it");
    	
    	List<User> userCC = new ArrayList<User>();
    	userCC.add(provider);
    	userCC.add(requestor);
    	p.setCcUsers(userCC);
    	
    	ProcessDAO processDao = new ProcessDAO();
    	Process process = processDao.getProcessByHpmId("rda1185");
    	
    	p.setPatternProvider(provider);
    	p.setPatternRequestor(requestor);
    	PatternType pt=new PatternType();
    	pt.setIdPatternType(1);
    	pt.setName("Pattern di test");
    	pt.setActivitiProcessDefinitionId("vacationRequest:1:38");
    	p.setPatternType(pt);
    	
    	PatternState ps=new PatternState();
    	ps.setIdPatternState(1);
    	ps.setState("Accettata");
    	p.setPatternState(ps);
    	AttachmentType attType = new AttachmentType();
    	attType.setIdAttachmentType(3);
    	attType.setName("pattern");
    	p.setAttachmentType(attType);
    	
    	p.setName("nome di prova");
    	p.setDescription("descrizione di prova");
    	p.setStartDate(new Date());
    	
    	Pattern p1=patternDao.startPattern(p, requestor, process);
    	
    	assertNotSame("", p1.getActivitiProcessInstanceId());
    }
    
    @Test 
    public final void getPatternTypeByPatternTypeIdTest() {
    	PatternDAO patternDao = new PatternDAO();
    	PatternType pt = new PatternType();
    	pt.setIdPatternType(1);
    	User user;
		try {
			user = (new UserDAO()).getUserByHpmUserId(
					"bolognese@kpeople.webscience.it");
			pt = patternDao.getPatternTypeByPatternTypeId(
	    			pt, user);
		} catch (KPeopleDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertEquals(1, pt.getIdPatternType());
		assertEquals("Richiedi contributo", pt.getName());
		assertEquals("RCONTR", pt.getPatternTypeCode());
		assertEquals("richiestaContributoFlow:1:115", pt.getActivitiProcessDefinitionId());
		assertEquals("richiestaContributoFlow:1:115", pt.getHpmPatternTypeId());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void closePatternFromActivitiTest() throws Exception {
    	
        String userHpmId = "filieri@kpeople.webscience.it"; 
        String processInstanceId = "2470"; 
        
        PatternDAO pDao = new PatternDAO();
    	boolean ret = pDao.closePatternFromActiviti(processInstanceId, userHpmId);
    	Assert.assertEquals(false, ret);
    }
    
}
