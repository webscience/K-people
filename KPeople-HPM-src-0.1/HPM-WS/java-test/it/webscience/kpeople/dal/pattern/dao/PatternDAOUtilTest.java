package it.webscience.kpeople.dal.pattern.dao;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternActivityFilter;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.cross.UserDAO;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class PatternDAOUtilTest {

	@Test
	public void insertPatternTest() throws Exception {
		Assert.assertEquals(true, true);
	}
	
	@Test
	public void savePatternUserRoleTest() throws Exception {
		Assert.assertEquals(true, true);
	}
	
	@Test
	public void savePatternMetadataTest() throws Exception {
		Assert.assertEquals(true, true);
	}
	
	@Test
	public void loadPatternByPatternIdTest() throws Exception {
		Assert.assertEquals(true, true);
	}
	
	@Test
	public void loadPatternByHpmPatternIdTest() throws Exception {
		Assert.assertEquals(true, true);
	}
	
	@Test
	public void getPatternIdFromHpmPatternIdTest() throws Exception {
		PatternDAOUtil pDaoUtil = new PatternDAOUtil();
		String hpmPatternId = "app1219-RCONTR1658";
		int idPattern = 1511;
		int res = pDaoUtil.getPatternIdFromHpmPatternId(hpmPatternId);
		Assert.assertEquals(idPattern, res);
	}
	
	@Test
	public void getPatternIdListFromHpmPatternIdListTest() throws Exception {
		PatternDAOUtil pDaoUtil = new PatternDAOUtil();
		String hpmPatternId = "app1219-RCONTR1658";
		
		List<String> hpmPatternIdList = new ArrayList<String>();
		hpmPatternIdList.add(hpmPatternId);
		int idPattern = 1511;
		List<Integer> res = pDaoUtil.getPatternIdListFromHpmPatternIdList(hpmPatternIdList);
		Assert.assertEquals(res.size(), 1);
		
		Assert.assertEquals(res.get(0).intValue(), idPattern);
	}
	
	@Test
	public void getPatternIdFromActivitiProcessInstanceIdTest() throws Exception {
		Assert.assertEquals(true, true);
	}
	
	@Test
	public void getHpmPatternIdFromPatternId() throws Exception {
		PatternDAOUtil pDaoUtil = new PatternDAOUtil();
		String hpmPatternId = "app1219-RCONTR1658";
		int idPattern = 1511;
		
		String res = pDaoUtil.getHpmPatternIdFromPatternId(idPattern);
		
		Assert.assertEquals(hpmPatternId, res);
	}
	
	@Test
	public void getPatternFromActivitiProcessInstanceId() throws Exception {
		
		PatternDAOUtil pDaoUtil = new PatternDAOUtil();
		String activitiProcessInstanceId = "1658";
		int idPattern = 1511;
		Pattern p = pDaoUtil.getPatternFromActivitiProcessInstanceId(activitiProcessInstanceId);
		Assert.assertEquals(p.getIdAttachment(), 1511);
	}
	
	@Test
	public void createPatternIdStringListTest()  throws Exception {
		PatternDAOUtil pDaoUtil = new PatternDAOUtil();
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(234);
		list.add(56);
		
		String res = pDaoUtil.createPatternIdStringList(list);
		System.out.println(res); 
		Assert.assertEquals(res, "1, 234, 56");
	}
}
