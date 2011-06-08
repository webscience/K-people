package it.webscience.kpeople.be;

import java.util.Date;

/**
 * Definisce i parametri di ricerca dei processi.
 */
public class ProcessFilter {

    /**
     * Identificativo dell'utente a cui è associato il processo come owner o
     * come user.
     */
    private String userId;

    /**
     * Visibilità del processo (ALL, PRIVATE o PUBLIC).
     */
    private String visibility;

    /**
     * Indica se filtrare i processi cancellati (ALL, DELETED, NOT-DELETED).
     */
    private String deleted;

    /**
     * Indica gli id degli stati dei processi selezionati nel form.
     * (CLOSED e/o OPEN)
     */
    private int[] state;

    /** filtro sui processi riservati. */
    private boolean showReserved;

    /** filtro sui processi 'in tempo'. */
    private boolean showInTime;

    /** filtro sui processi 'in ritardo'. */
    private boolean showLate;

    /**
     * Indica il tipo di processo (definito nella tabella PROCESS_TYPE).
     */
    private String type;

    /**
     * Valore testuale per la ricerca libera.
     */
    private String freeText;

    /**
     * Valore Date per la data di creazione iniziale.
     */
    private Date creationDateFrom;

    /**
     * Valore Date per la data di creazione finale.
     */
    private Date creationDateTo;

    /**
     * Valore Date per la data di scadenza iniziale.
     */
    private Date dueDateFrom;

    /**
     * Valore Date per la data di scadenza finale.
     */
    private Date dueDateTo;

    /**
     * Classe per definire l'ordinamento dei processi in uscita.
     */
    private SortCriteria sort;

    /**
     * @return userId
     */
    public final String getUserId() {
        return userId;
    }

    /**
     * @param in userId sul quale effettuare la ricerca
     */
    public final void setUserId(final String in) {
        this.userId = in;
    }

    /**
     * @return visibilità dei processi da cercare
     */
    public final String getVisibility() {
        return visibility;
    }

    /**
     * @param in visibilità dei processi da cercare
     */
    public final void setVisibility(final String in) {
        this.visibility = in;
    }

    /**
     * @return parametro per la ricerca dei processi cancellati
     */
    public final String getDeleted() {
        return deleted;
    }

    /**
     * @param in imposta la ricerca dei processi cancellati
     */
    public final void setDeleted(final String in) {
        this.deleted = in;
    }

    /**
     * @return il tipo dei processi da cercare
     */
    public final String getType() {
        return type;
    }

    /**
     * @param in imposta lo stato dei processi da cercare
     */
    public final void setType(final String in) {
        this.type = in;
    }

    /**
     * @return campo testuale per la ricerca libera
     */
    public final String getFreeText() {
        return freeText;
    }

    /**
     * @param in imposta il campo testuale per la ricerca libera
     */
    public final void setFreeText(final String in) {
        this.freeText = in;
    }

    /**
     * @return data di creazione iniziale
     */
    public final Date getCreationDateFrom() {
        return creationDateFrom;
    }

    /**
     * @param in imposta la data di creazione iniziale
     */
    public final void setCreationDateFrom(final Date in) {
        this.creationDateFrom = in;
    }

    /**
     * @return data di creazione finale
     */
    public final Date getCreationDateTo() {
        return creationDateTo;
    }

    /**
     * @param in imposta la data di creazione finale
     */
    public final void setCreationDateTo(final Date in) {
        this.creationDateTo = in;
    }

    /**
     * @return data di scadenza iniziale
     */
    public final Date getDueDateFrom() {
        return dueDateFrom;
    }

    /**
     * @param in imposta la data di scadenza iniziale
     */
    public final void setDueDateFrom(final Date in) {
        this.dueDateFrom = in;
    }

    /**
     * @return la data di scadenza finale
     */
    public final Date getDueDateTo() {
        return dueDateTo;
    }

    /**
     * @param in imposta la data di scadenza finale
     */
    public final void setDueDateTo(final Date in) {
        this.dueDateTo = in;
    }

    /**
     * @return il criterio di ordinamento dei processi
     */
    public final SortCriteria getSort() {
        return sort;
    }

    /**
     * @param in imposta il critero di ordinamento dei processi
     */
    public final void setSort(final SortCriteria in) {
        this.sort = in;
    }

    /**
     * @return the state
     */
    public final int[] getState() {
        return state;
    }

    /**
     * @param in the state to set
     */
    public final void setState(final int[] in) {
        this.state = in;
    }

    /**
     * @return the showReserved
     */
    public final boolean isShowReserved() {
        return showReserved;
    }

    /**
     * @param in the showReserved to set
     */
    public final void setShowReserved(final boolean in) {
        this.showReserved = in;
    }

    /**
     * @return the showInTime
     */
    public final boolean isShowInTime() {
        return showInTime;
    }

    /**
     * @param in the showInTime to set
     */
    public final void setShowInTime(final boolean in) {
        this.showInTime = in;
    }

    /**
     * @return the showLate
     */
    public final boolean isShowLate() {
        return showLate;
    }

    /**
     * @param in the showLate to set
     */
    public final void setShowLate(final boolean in) {
        this.showLate = in;
    }
}
