package it.webscience.kpeople.dal.pattern;

import static org.junit.Assert.assertEquals;
import it.webscience.kpeople.be.PatternState;

import org.junit.Test;

public class PatternStateTest {

	@Test
	public final void getPatternStateByIdTest() throws Exception {
		PatternStateDAO psDao = new PatternStateDAO();
		PatternState ps= psDao.getPatternStateById(1);
		assertEquals(ps.getState(), "Inviata");
		assertEquals(ps.getIdPatternType(), 1);
		assertEquals(ps.getDescription(), "Richiesta di contributo inviata");
	}
}
