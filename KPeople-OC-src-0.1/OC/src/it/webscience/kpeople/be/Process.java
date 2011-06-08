package it.webscience.kpeople.be;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Tabella PROCESS.
 */
public class Process extends DataTraceClass {
    /**
     * Chiave primaria.
     * Identificativo univoco per un record della tabella PROCESS
     */
    private int idProcess;

    /** Valore testuale utilizzato per una breve descrizione del processo. */
    private String name;

    /** Valore testuale utilizzato per una breve descrizione del processo. */
    private String description;

    /** Campo obbligatorio. Timestamp relativo alla creazione del processo. */
    private Date dateCreated;

    /** Timestamp relativo alla data di scadenza di un processo. */
    private Date dateDue;

    /** Valore booleano che determina se un processo è publico o privato. */
    private boolean isPrivate;

    /** Valore boolean che determina se un processo è attivo. */
    private boolean isActive;

    /**
     * Campo obbligatorio. Chiave esterna.
     * Punta ad un record della tabella user e rappresenta l'owner del processo.
     */
    private User owner;

    /** Identificativo del tipo di processo. */
    private ProcessType processType;

    /** Identificativo univoco per identificare un processo
     * in tutte le componenti del sistema. */
    private String hpmProcessId;

    /** Keywords associate al processo. */
    private List<Keyword> keywords;


    /** Costruttore. */
    public Process() {
        super();
        keywords = new ArrayList<Keyword>();
    }

    /**
     * @return the idProcess
     */
    public final int getIdProcess() {
        return idProcess;
    }

    /**
     * @param in the idProcess to set
     */
    public final void setIdProcess(final int in) {
        this.idProcess = in;
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

    /**
     * @return the dateCreated
     */
    public final Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param in the dateCreated to set
     */
    public final void setDateCreated(final Date in) {
        this.dateCreated = in;
    }

    /**
     * @return the dateDue
     */
    public final Date getDateDue() {
        return dateDue;
    }

    /**
     * @param in the dateDue to set
     */
    public final void setDateDue(final Date in) {
        this.dateDue = in;
    }

    /**
     * @return the isPrivate
     */
    public final boolean isPrivate() {
        return isPrivate;
    }

    /**
     * @param in the isPrivate to set
     */
    public final void setPrivate(final boolean in) {
        this.isPrivate = in;
    }

    /**
     * @return the isActive
     */
    public final boolean isActive() {
        return isActive;
    }

    /**
     * @param in the isActive to set
     */
    public final void setActive(final boolean in) {
        this.isActive = in;
    }

    /**
     * @return the owner
     */
    public final User getOwner() {
        return owner;
    }

    /**
     * @param in the owner to set
     */
    public final void setOwner(final User in) {
        this.owner = in;
    }

    /**
     * @return the processType
     */
    public final ProcessType getProcessType() {
        return processType;
    }

    /**
     * @param in the processType to set
     */
    public final void setProcessType(final ProcessType in) {
        this.processType = in;
    }

    /**
     * @return the hpmProcessId
     */
    public final String getHpmProcessId() {
        return hpmProcessId;
    }

    /**
     * @param in the hpmProcessId to set
     */
    public final void setHpmProcessId(final String in) {
        this.hpmProcessId = in;
    }

    /**
     * @return the keywords
     */
    public final List<Keyword> getKeywords() {
        return keywords;
    }

    /**
     * @param in the keywords to set
     */
    public final void setKeywords(final List<Keyword> in) {
        this.keywords = in;
    }
}
