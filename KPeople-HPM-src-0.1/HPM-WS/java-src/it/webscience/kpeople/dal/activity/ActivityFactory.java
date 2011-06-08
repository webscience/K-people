package it.webscience.kpeople.dal.activity;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.ActivityType;
import it.webscience.kpeople.be.PatternMetadata;
import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityFactory {

	/**
     * factory per l'oggetto PatternState.
     * @param rs resultset
     * @return istanza dell'oggetto PatternState
     * @throws SQLException label colonne non valido
     */
	public static Activity createActivity(final ResultSet rs) 
		throws SQLException {
			
		Activity activity = new Activity();
			
		//Popolamento dei campi e gestione delle eccezioni
		
		//activity.setIdActivity(rs.getInt("ID_ACTIVITY"));
		activity.setIdPattern(rs.getInt("ID_PATTERN"));
		activity.setTitle(rs.getString("TITLE"));
		activity.setDescription(rs.getString("DESCRIPTION"));
		activity.setCreateDate(rs.getDate("DATE_CREATE"));
		activity.setDueDate(rs.getDate("DATE_DUE"));
		activity.setClosed(rs.getBoolean("IS_CLOSED"));
		activity.setActivitiProcessTaskId(
				rs.getString("ACTIVITI_PROCESS_TASK_ID"));
		
		UserDAO uDao = new UserDAO();
		User userOwner = uDao.getUserByIdUser(
				rs.getInt("ACTIVITY_OWNER"));
		User userRequestor = uDao.getUserByIdUser(
				rs.getInt("ACTIVITY_REQUESTOR"));
		activity.setActivityOwner(userOwner);
		activity.setActivityRequestor(userRequestor);
		ActivityType at = new ActivityType();
		at.setIdActivityType(1);
		at.setName("defaultactivity");
		activity.setActivityType(at);
		
		DataTraceClassFactory.createDataTraceClass(activity, rs);

		return activity;
	}
}
