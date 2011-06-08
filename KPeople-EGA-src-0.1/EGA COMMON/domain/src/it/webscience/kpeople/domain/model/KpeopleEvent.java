package it.webscience.kpeople.domain.model;

import java.io.Serializable;

public class KpeopleEvent implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 8222606826990251291L;

    private Long idEvent;
    private String XMLevent;
    private String NumRetries;
    private String LastRetry;
    private KpeopleAction FKEventAction;

    public KpeopleEvent() {

    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public String getXMLevent() {
        return XMLevent;
    }

    public void setXMLevent(String xMLevent) {
        XMLevent = xMLevent;
    }

    public String getNumRetries() {
        return NumRetries;
    }

    public void setNumRetries(String numRetries) {
        NumRetries = numRetries;
    }

    public String getLastRetry() {
        return LastRetry;
    }

    public void setLastRetry(String lastRetry) {
        LastRetry = lastRetry;
    }

    public KpeopleAction getFKEventAction() {
        return FKEventAction;
    }

    public void setFKEventAction(KpeopleAction fKEventAction) {
        FKEventAction = fKEventAction;
    }

}