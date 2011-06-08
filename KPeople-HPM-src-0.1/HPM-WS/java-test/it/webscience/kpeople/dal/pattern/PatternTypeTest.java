package it.webscience.kpeople.dal.pattern;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternType;

import org.junit.Test;

public class PatternTypeTest {

	@Test
	public final void test() {
		PatternTypeDAO ptDao = new PatternTypeDAO();
		try {
			PatternType pt = ptDao.getPatternTypeById(1);
			assertEquals(1, pt.getIdPatternType() );
			assertEquals("Richiedi contributo", pt.getName());
			assertEquals("richiestaContributoFlow:1:115", pt.getActivitiProcessDefinitionId());
			assertEquals("richiestaContributoFlow:1:115", pt.getHpmPatternTypeId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
