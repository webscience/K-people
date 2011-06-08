package it.webscience.kpeople.bll.impl.activitihandler;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.bll.exception.KPeopleActivityHandlerException;

/**
 * Interfaccia per ActivityHandler.
 * @author gnoni
 *
 */
public interface ActivityHandler {

    /**
     * 
     * @param pActivity
     * @throws KPeopleActivityHandlerException
     */
    void execute() 
        throws KPeopleActivityHandlerException;
}
