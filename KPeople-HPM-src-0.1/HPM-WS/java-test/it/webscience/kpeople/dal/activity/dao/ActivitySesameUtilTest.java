package it.webscience.kpeople.dal.activity.dao;


import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.PatternActivityFilter;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.cross.UserDAO;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ActivitySesameUtilTest {

	
	String pHpmProcessid = "app12081111111";
	String pHpmPatternid = "app1208-RCONTR1628";
	int numPattern = 1;
	
	@Test
	public void getHpmPatternIdFromHpmProjectIdTest () throws Exception {
		ActivitySesameUtil aSes = new ActivitySesameUtil();
		
		List<String> ret = aSes.getHpmPatternIdFromHpmProjectId(pHpmProcessid);
		
		Assert.assertEquals(numPattern, ret.size());
		Assert.assertEquals(pHpmPatternid, ret.get(0));
	}
}
