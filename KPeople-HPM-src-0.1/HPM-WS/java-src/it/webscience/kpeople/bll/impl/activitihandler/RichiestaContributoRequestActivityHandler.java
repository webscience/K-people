package it.webscience.kpeople.bll.impl.activitihandler;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.bll.exception.KPeopleActivityHandlerException;

/**
 * 
 * @author gnoni
 *
 */
public class RichiestaContributoRequestActivityHandler 
    extends BaseActivityHandler
    implements ActivityHandler {

    /**
     * 
     * @param pActivity
     */
    public RichiestaContributoRequestActivityHandler(Activity pActivity) {
        super(pActivity);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute()
            throws KPeopleActivityHandlerException {
        
      //Processare il membro della classe madre 'super.activity'
        
    }

}
