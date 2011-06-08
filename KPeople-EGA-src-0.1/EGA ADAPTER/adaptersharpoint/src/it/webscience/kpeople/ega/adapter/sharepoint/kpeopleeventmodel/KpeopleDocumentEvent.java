package it.webscience.kpeople.ega.adapter.sharepoint.kpeopleeventmodel;

import java.util.Date;

/**
 * @author XPMUser
 *
 */
public class KpeopleDocumentEvent extends KpeopleSimpleEvent {


    /**
     * @param idevent
     *            Identificativo univoco dell'evento.
     * @param actiontype
     *            Identificativo della tipologia di evento.
     * @param systemtype
     *            Riferimento al sistema verticale.
     * @param idaction
     *            Riferimento all'azione associata all'evento.
     */
    public KpeopleDocumentEvent(final String idevent, final String actiontype,
            final String systemtype, final Long idaction) {
        super(idevent, actiontype, systemtype, idaction);

    }


    public KpeopleDocumentEvent(String idevent, String actiontype,
            String systemtype, Long idaction, String systemId,
            String actionReference) {
        super(idevent, actiontype, systemtype, idaction, systemId, actionReference);
    }


    protected String operation;
    
   
    
    
    
    
    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }


    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }


    /**
     * @see it.webscience.kpeople.ega.adapter
     * .sharepoint.kpeopleeventmodel.KpeopleAbstractEvent#generateXml().
     * @return un oggetto di tipo String
     * che rappresenta il frammento xml relativo all'evento.
     */
    public String generateXml() {
        return null;
    }

}
