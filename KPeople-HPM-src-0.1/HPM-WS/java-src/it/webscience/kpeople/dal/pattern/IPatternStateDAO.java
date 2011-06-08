package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.be.PatternType;

import java.sql.SQLException;

/**
 * Interfaccia per la classe PatternStateDAO.
 * @author gnoni
 *
 */
public interface IPatternStateDAO {

	/**
	 *  .
	 * @param pPatternTypeId
	 * @return
	 * @throws SQLException
	 */
	public PatternState getPatternStateById(int pPatternTypeId) 
		throws SQLException;
}
