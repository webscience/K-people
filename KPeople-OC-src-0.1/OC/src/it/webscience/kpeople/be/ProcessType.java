package it.webscience.kpeople.be;

/**
 * Tabella PROCESS_TYPE.
 */
public class ProcessType extends DataTraceClass {

    /**
     * Chiave primaria.
     * Identificativo univoco per un record della tabella PROCESS_TYPE.
     */
    private int idProcessType;

    /** Campo obbligatorio.
     * Valore testuale in cui memorizzare il nome della
     * titplogia di un processo.
     */
    private String name;

    /**
     * @return the idProcessType
     */
    public final int getIdProcessType() {
        return idProcessType;
    }

    /**
     * @param in the idProcessType to set
     */
    public final void setIdProcessType(final int in) {
        this.idProcessType = in;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param in the name to set
     */
    public final void setName(final String in) {
        this.name = in;
    }
}
