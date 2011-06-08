package it.webscience.kpeople.bll.impl.activitihandler;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.User;

/**
 * Classe factory per classi che implementano l'interfaccia ActivityHandler.
 * 
 * @author gnoni
 * 
 */
public class ActivityHandlerFactory {

    /**
     * 
     */
    public static final String ACTIVITYTYPE_RICH_CONTRIBUTO_CONTRIBUTE  
        = "richiestacontributo_contribute";
    
    /**
     * 
     */
    public static final String ACTIVITYTYPE_RICH_CONTRIBUTO_ACCEPT 
        = "richiestacontributo_accept";
   
    /**
     * 
     */
    public static final String ACTIVITYTYPE_RICH_CONTRIBUTO_REQUEST
        = "richiestacontributo_request";

    /**
     * @param pActivity attività
     * @return ActivityHandler handler
     */
    public final static ActivityHandler createActivityHandler(
            final Activity pActivity) {

        //Lettura parametri formali
        Activity activity = pActivity;
        ActivityHandler handler = null;
        String activityType = activity.getActivityType().getName();
        if (activityType.equals(ACTIVITYTYPE_RICH_CONTRIBUTO_CONTRIBUTE)) {
           handler = new RichiestaContributoContributeActivityHandler(activity);
        } else if (activityType.equals(ACTIVITYTYPE_RICH_CONTRIBUTO_ACCEPT)) {
           handler = new RichiestaContributoAcceptActivityHandler(activity);
        } else if (activityType.equals(ACTIVITYTYPE_RICH_CONTRIBUTO_REQUEST)) {
           handler = new RichiestaContributoRequestActivityHandler(activity);
        } else {
            handler = new DefaultActivityHandler(activity);
        }

        return handler;
    }
}
