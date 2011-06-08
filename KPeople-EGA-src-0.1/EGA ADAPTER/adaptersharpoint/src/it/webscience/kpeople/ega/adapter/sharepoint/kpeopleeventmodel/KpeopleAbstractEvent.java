package it.webscience.kpeople.ega.adapter.sharepoint.kpeopleeventmodel;


/**
 * @author XPMUser
 *
 */
public abstract class KpeopleAbstractEvent {

    /**
     * Identificativo univoco generato
     * dal sistema per identificare univocamente l’evento.
     */
    protected String idEvent;

    /**
     * Valore testuale che identifica la tipologia di evento.
     */
    protected String actionType;

    /**
     * Valore testuale che identifica il
     * sistema verticale che ha generato l’evento.
     */
    protected String systemType;

    /**
     * Valore che rappresenta l'identificativo
     * univoco dell'azione.
     */
    protected Long idAction;

    /**
     * @param idevent Identificativo univoco dell'evento.
     * @param actiontype Identificativo della tipologia di evento.
     * @param systemtype Riferimento al sistema verticale.
     */
    public KpeopleAbstractEvent(final String idevent, final String actiontype,
            final String systemtype, final Long idaction) {
        this.idEvent = idevent;
        this.actionType = actiontype;
        this.systemType = systemtype;
        this.idAction = idaction;
    }




    /**
     * @return the idEvent
     */
    public String getIdEvent() {
        return idEvent;
    }

    /**
     * @return the actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @return the systemType
     */
    public String getSystemType() {
        return systemType;
    }





    /**
     * @param idEvent the idEvent to set
     */
    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }





    /**
     * @param actionType the actionType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }




 
    /**
     * @param systemType the systemType to set
     */
    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }




    /**
     * @return the idAction
     */
    public Long getIdAction() {
        return idAction;
    }




    /**
     * @param idAction the idAction to set
     */
    public void setIdAction(Long idAction) {
        this.idAction = idAction;
    }

    
}
