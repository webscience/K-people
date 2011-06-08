package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * factory per la classe PatternType.
 * @author gnoni
 */
public class PatternTypeFactory {

	/**
     * factory per l'oggetto PatternType.
     * @param rs resultset
     * @return istanza dell'oggetto PatternType
     * @throws SQLException label colonne non valido
     */
	public static PatternType createPatternType(final ResultSet rs) 
		throws SQLException {
			
			PatternType pt = new PatternType();
			
			//Popolamento dei campi 
			pt.setIdPatternType(rs.getInt("ID_PATTERN_TYPE"));
			pt.setName(rs.getString("NAME"));
			pt.setDescription(rs.getString("DESCRIPTION"));
		    
			pt.setActive(rs.getBoolean("IS_ACTIVE"));
			pt.setShowInList(rs.getBoolean("SHOW_IN_LIST"));
			pt.setVersion(rs.getString("VERSION"));
			pt.setRelatedForm(rs.getString("RELATED_FORM"));
			pt.setPatternTypeCode(rs.getString("PATTERN_TYPE_CODE"));
			pt.setActivitiProcessDefinitionId(
					rs.getString("ACTIVITI_PROCESS_DEFINITION_ID"));
			pt.setHpmPatternTypeId(rs.getString("HPM_PATTERN_TYPE_ID"));
			pt.setOrdering(rs.getInt("ORDERING"));
			pt.setWaitingActivity(rs.getBoolean("WAITING_ACTIVITY"));
			DataTraceClassFactory.createDataTraceClass(pt, rs);

			return pt;
		}
}
