package it.webscience.kpeople.bll.impl.activitihandler;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.bll.exception.KPeopleActivityHandlerException;

public class DefaultActivityHandler 
    extends BaseActivityHandler 
    implements ActivityHandler {

    /**
     * Costruttore.
     * @param pActivity
     */
    public DefaultActivityHandler(Activity pActivity) {
        super(pActivity);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute()
            throws KPeopleActivityHandlerException {
        
        logger.warn(
                "Process activity " + activity.getActivitiProcessTaskId());
        logger.warn(
                " Activity type : " + activity.getActivityType().getName());
    }
    
    

}
