package it.webscience.kpeople.dal.activity;

import it.webscience.kpeople.be.ActivityType;
import it.webscience.kpeople.dal.activity.dao.ActivityTypeDAOUtil;

import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 
 * @author gnoni
 *
 */
public class ActivityTypeDAO implements IActivityTypeDAO {

	/** logger. */
    private Logger logger;

    /** Costruttore. */
    public ActivityTypeDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }
    
	@Override
	public ActivityType getActivityTypeById(
			final ActivityType pActivityType)
			throws SQLException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("getActivityTypeById: "
                    + pActivityType);
        }
		
		//Lettura parametri formali
		ActivityType activityType = pActivityType;
		
		ActivityTypeDAOUtil actTypeUtil = new ActivityTypeDAOUtil();
		activityType = actTypeUtil
			.loadActivityTypeByIdActivityType(activityType);
		
		if (logger.isDebugEnabled()) {
            logger.debug("getActivityTypeById: "
                    + pActivityType);
        }
		
		return activityType;
	}

}
