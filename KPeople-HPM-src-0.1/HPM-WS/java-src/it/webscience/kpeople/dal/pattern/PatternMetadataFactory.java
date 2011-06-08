package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.PatternMetadata;
import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatternMetadataFactory {

	/**
     * factory per l'oggetto PatternMetadata.
     * @param rs resultset
     * @return istanza dell'oggetto PatternMetadata
     * @throws SQLException label colonne non valido
     */
	public static PatternMetadata createPatternMetadata(final ResultSet rs) 
		throws SQLException {
			
		PatternMetadata pm = new PatternMetadata();
			
		//Popolamento dei campi e gestione delle eccezioni
		pm.setIdPatternMetadata(rs.getInt("ID_PATTERN_METADATA"));
		pm.setIdPattern(rs.getInt("ID_PATTERN"));
		pm.setKeyname(rs.getString("KEYNAME"));
		pm.setValue(rs.getString("VALUE"));
		pm.setActivitiProcessMetadata(
				rs.getBoolean("ACTIVITI_PROCESS_METADATA"));
		
		DataTraceClassFactory.createDataTraceClass(pm, rs);

		return pm;
	}
}
