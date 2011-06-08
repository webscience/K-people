package it.webscience.kpeople.service.datatypes;

/**
 * Tabella PROCESS_STATE.
 */
public class ProcessState {
    /**
     * Chiave primaria.
     * Identificativo univoco per un record della tabella PROCESS_STATE.
     */
    private int idProcessState;

    /**
     * Nome dello stato.
     */
    private String processState;

    /**
     * descrizione dello stato.
     */
    private String description;

    /**
     * @return the idProcessState
     */
    public final int getIdProcessState() {
        return idProcessState;
    }

    /**
     * @param in the idProcessState to set
     */
    public final void setIdProcessState(final int in) {
        this.idProcessState = in;
    }

    /**
     * @return the processState
     */
    public final String getProcessState() {
        return processState;
    }

    /**
     * @param in the processState to set
     */
    public final void setProcessState(final String in) {
        this.processState = in;
    }

    /**
     * @return the description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param in the description to set
     */
    public final void setDescription(final String in) {
        this.description = in;
    }
}
