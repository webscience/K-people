package it.webscience.kpeople.bll.impl.activitihandler;

import org.apache.log4j.Logger;

import it.webscience.kpeople.be.Activity;

/**
 * Classe base per l'implementazione dell'interfaccia ActivityHandler.
 * @author gnoni
 *
 */
public class BaseActivityHandler {

    /** logger. */
    protected Logger logger;
    
    /**
     * Istanza dell'attività da processare.
     */
    protected Activity activity;
    
    /**
     * Costruttore.
     * @param pActivity attività da processare.
     */
    public BaseActivityHandler(Activity pActivity) {
        activity = pActivity;
    }
}
