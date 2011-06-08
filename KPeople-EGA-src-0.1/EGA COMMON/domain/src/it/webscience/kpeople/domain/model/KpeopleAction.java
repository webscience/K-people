package it.webscience.kpeople.domain.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class KpeopleAction implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7119006447436563190L;

    private Long idAction;
    private String systemId;
    private String actionType;
    private String actionReference;
    private Timestamp timestamp;
    private KpeopleEvent event;
    private KpeopleState FKActionState;

    public KpeopleAction() {

    }

    public Long getIdAction() {
        return idAction;
    }

    public void setIdAction(Long idAction) {
        this.idAction = idAction;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionReference() {
        return actionReference;
    }

    public void setActionReference(String actionReference) {
        this.actionReference = actionReference;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public KpeopleEvent getEvent() {
        return event;
    }

    public void setEvent(KpeopleEvent event) {
        this.event = event;
    }

    public KpeopleState getFKActionState() {
        return FKActionState;
    }

    public void setFKActionState(KpeopleState fKActionState) {
        FKActionState = fKActionState;
    }

}
