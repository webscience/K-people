package it.webscience.kpeople.dal.pattern;

import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.activity.ActivityDAO;
import it.webscience.kpeople.dal.activity.dao.ActivityDAOUtil;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.pattern.dao.PatternDAOUtil;
import it.webscience.kpeople.dal.process.ProcessDAO;


public class RoundTripTest {

	/**
	 * Prerequisiti: I due utenti non hanno attività
	 * 
	 * Requestor Starts Pattern 
	 * Provider richiede le attività (presente solo una)
	 * Provider responses
	 * Requestor richiede le attività (presente solo una)
	 * Requestor approves
	 * 
	 * Provider sees
	 */
	String nTest = "1";
	
	String patternRequestorHpmId = "dellanna@kpeople.webscience.it";
	String patternProviderHpmId = "filieri@kpeople.webscience.it";
	String patternCCUserHpmId = "bolognese@kpeople.webscience.it";
	
	String patternName = "Pattern Test n." + nTest;
	String patternDescription = "Descriptio " + patternName;
	
	int idAttachmentType = 3;
	int idPatternType = 7;
	
	
	User patternProvider = null;
	User patternRequestor = null;	
	User patterbCCUser = null;
	
	String hpmPatternId = "";
	String hpmProcessId = "";
	
	
	Process process = null;
	Pattern pattern = null;
	
	public void roundTrip1Test() throws Exception {
		PatternDAOUtil patDaoUtil = new PatternDAOUtil();
		ActivityDAOUtil actDaoUtil = new ActivityDAOUtil();
		
		PatternDAO patDao = new PatternDAO();
		ActivityDAO actDao = new ActivityDAO();
		
		init();
		pattern = patDao.startPattern(
				pattern , 
				patternRequestor, 
				process);
		assertNotSame("", pattern.getActivitiProcessInstanceId());
		
		//PatternActivityFilter pattFilter = new PatternActivityFilter();
		//actDao.searchToDoActivities(pFilter, pLoggedUser);
		
	}
	
	
	private void init() throws Exception {
		
		pattern = new Pattern();
		UserDAO userDao=new UserDAO();
		
		patternProvider = userDao.
			getUserByHpmUserId(patternProviderHpmId);
		patternRequestor = userDao.
			getUserByHpmUserId(patternRequestorHpmId);
		patterbCCUser = userDao.
			getUserByHpmUserId(patternCCUserHpmId);
		
		List<User> userCC = new ArrayList<User>();
		userCC.add(patterbCCUser);
		pattern.setCcUsers(userCC);
		
		ProcessDAO processDao = new ProcessDAO();
		process = processDao.getProcessByHpmId(hpmProcessId);
		
		pattern.setPatternProvider(patternProvider);
		pattern.setPatternRequestor(patternRequestor);
		
		PatternTypeDAO ptDao = new PatternTypeDAO();
		PatternType pt = ptDao.getPatternTypeById(idPatternType); 
		pattern.setPatternType(pt);
		
		PatternStateDAO psDao = new PatternStateDAO();
		
		PatternState ps=psDao.getPatternStateById(1);
		pattern.setPatternState(ps);
		
		AttachmentType attType = new AttachmentType();
		attType.setIdAttachmentType(idAttachmentType);
		attType.setName("pattern");
		pattern.setAttachmentType(attType);
		
		pattern.setName(patternName);
		pattern.setDescription(patternDescription);
		pattern.setStartDate(new Date());
	}
	
		
	
	
}
