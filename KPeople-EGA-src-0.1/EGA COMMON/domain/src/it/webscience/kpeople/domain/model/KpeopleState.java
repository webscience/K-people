package it.webscience.kpeople.domain.model;

import java.util.Set;
import java.io.Serializable;

public class KpeopleState implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7686537873819661836L;

    private Long idState;
    private String name;
    private String description;
    private Set<KpeopleAction> actions;

    public KpeopleState() {

    }

    public Long getIdState() {
        return idState;
    }

    public void setIdState(Long idState) {
        this.idState = idState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<KpeopleAction> getActions() {
        return actions;
    }

    public void setActions(Set<KpeopleAction> actions) {
        this.actions = actions;
    }

}