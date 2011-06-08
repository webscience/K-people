package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.PatternType;

import java.sql.SQLException;
/**
 * Interfaccia della classe PatternTypeDAO.
 * @author gnoni
 *
 */
public interface IPatternTypeDAO {

	 /**
	  * 
	  * @param pPatternTypeId identificativo pattern type da estrarre
	  * @return patterntype BE
	  * @throws SQLException eccezione
	  */
	 PatternType getPatternTypeById(int pPatternTypeId) throws SQLException;
	 
}
