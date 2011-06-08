package it.webscience.kpeople.bll.impl.activitihandler;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.bll.exception.KPeopleActivityHandlerException;
import it.webscience.kpeople.bll.impl.SendEventManager;

/**
 * 
 * @author gnoni
 *
 */
public class RichiestaContributoContributeActivityHandler 
    extends BaseActivityHandler implements ActivityHandler {

    /**
     * costruttore.
     * @param pActivity
     */
    public RichiestaContributoContributeActivityHandler(Activity pActivity) {
        super(pActivity);
    }

    @Override
    public void execute()
            throws KPeopleActivityHandlerException {

        SendEventManager sem = new SendEventManager();
        try {
            sem.sendEventRichiestaContributoContribute(activity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new KPeopleActivityHandlerException(e.getMessage());
        }
    }
}
