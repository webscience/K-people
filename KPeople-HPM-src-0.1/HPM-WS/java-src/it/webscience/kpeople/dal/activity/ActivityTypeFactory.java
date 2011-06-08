package it.webscience.kpeople.dal.activity;

import it.webscience.kpeople.be.ActivityType;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * factory per la classe ActivityType.
 * @author gnoni
 */
public class ActivityTypeFactory {

	/**
     * factory per l'oggetto ActivityType.
     * @param rs resultset
     * @return istanza dell'oggetto ActivityType
     * @throws SQLException label colonne non valido
     */
	public static ActivityType createActivityType(final ResultSet rs) 
		throws SQLException {
			
			ActivityType at = new ActivityType();
			
			//Popolamento dei campi e gestione delle eccezioni
			at.setIdActivityType(rs.getInt("ID_ACTIVITY_TYPE"));
		    at.setName(rs.getString("NAME"));
		    at.setDescription(rs.getString("DESCRIPTION"));
			at.setRelatedForm(rs.getString("RELATED_FORM"));
		
			
			DataTraceClassFactory.createDataTraceClass(at, rs);

			return at;
		}
}
