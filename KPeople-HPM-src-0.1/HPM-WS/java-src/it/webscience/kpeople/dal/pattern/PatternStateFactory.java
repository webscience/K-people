package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * factory per la classe PatternType.
 * @author gnoni
 */
public class PatternStateFactory {

	/**
     * factory per l'oggetto PatternState.
     * @param rs resultset
     * @return istanza dell'oggetto PatternState
     * @throws SQLException label colonne non valido
     */
	public static PatternState createPatternState(final ResultSet rs) 
		throws SQLException {
			
			PatternState ps = new PatternState();
			
			//Popolamento dei campi e gestione delle eccezioni
			ps.setIdPatternState(rs.getInt("ID_PATTERN_STATE"));
			ps.setIdPatternType(rs.getInt("ID_PATTERN_TYPE"));
			ps.setState(rs.getString("STATE"));
			ps.setDescription(rs.getString("DESCRIPTION"));
			
			DataTraceClassFactory.createDataTraceClass(ps, rs);

			return ps;
		}
}
