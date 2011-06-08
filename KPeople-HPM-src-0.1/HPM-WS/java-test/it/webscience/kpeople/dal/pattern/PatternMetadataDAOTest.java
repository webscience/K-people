package it.webscience.kpeople.dal.pattern;

import static org.junit.Assert.assertEquals;
import it.webscience.kpeople.be.PatternMetadata;

import java.util.List;

import org.junit.Test;

/**
 * Classe di test per la classe DAO PatternMetadataDAO
 * @author gnoni
 *
 */
public class PatternMetadataDAOTest {

	@Test 
	public final void getPatternMetadataByIdTest() throws Exception {
		PatternMetadataDAO pmDao = new PatternMetadataDAO();
		PatternMetadata pm = pmDao.getPatternMetadataById(2);
		assertEquals(pm.getValue(), "informazioni");
		assertEquals(pm.getKeyname(), "TipoDiRichiesta");
		assertEquals(pm.isActivitiProcessMetadata(), false);
	}
	
	@Test 
	public final void getPatternMetadatasByPatternIdTest() throws Exception {
		PatternMetadataDAO pmDao = new PatternMetadataDAO();
		List<PatternMetadata> listMeta 
			= pmDao.getPatternMetadatasByPatternId(1);
		assertEquals(listMeta.size(), 1);
		
	}
	
}
