package it.webscience.kpeople.dal.activity;

import it.webscience.kpeople.be.ActivityType;

import java.sql.SQLException;

/**
 * 
 * @author javano
 *
 */
public interface IActivityTypeDAO {

	/**
	 * 
	 * @param pActivityType
	 * @return
	 * @throws SQLException
	 */
	ActivityType getActivityTypeById(
			final ActivityType pActivityType)
			throws SQLException;
}
