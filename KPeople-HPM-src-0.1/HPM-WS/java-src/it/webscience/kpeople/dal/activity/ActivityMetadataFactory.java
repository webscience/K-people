package it.webscience.kpeople.dal.activity;

import it.webscience.kpeople.be.PatternMetadata;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityMetadataFactory {

	/**
     * factory per l'oggetto ActivityMetadata.
     * @param rs resultset
     * @return istanza dell'oggetto ActivityMetadata
     * @throws SQLException label colonne non valido
     */
	public static PatternMetadata createActivityMetadata(final ResultSet rs) 
		throws SQLException {
			
		PatternMetadata pm = new PatternMetadata();
			
		//Popolamento dei campi e gestione delle eccezioni
		pm.setIdPatternMetadata(rs.getInt("ID_ACTIVITY_METADATA"));
		pm.setIdPattern(rs.getInt("ID_ACTIVITY"));
		pm.setKeyname(rs.getString("KEYNAME"));
		pm.setValue(rs.getString("VALUE"));
		//TODO AGGIUNGERE NELLA BASE DATI IL CAMPO SOTTO RIPORTATO
		//pm.setActivitiProcessMetadata(
			//	rs.getBoolean("ACTIVITI_PROCESS_METADATA"));
		
		DataTraceClassFactory.createDataTraceClass(pm, rs);

		return pm;
	}
}
