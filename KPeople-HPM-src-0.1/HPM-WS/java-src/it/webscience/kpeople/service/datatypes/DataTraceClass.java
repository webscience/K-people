package it.webscience.kpeople.service.datatypes;

import java.util.Date;

/**
 * classe base estesa dai tipi di dati dell'HPM.
 * Contiene le informazioni su autore e data di creazione/modifica
 *
 */
public class DataTraceClass {
    /** Flag booleano utilizzato per effettuare una eliminazione
     * logica di un record.*/
    private boolean isDeleted;

    /** Campo numerico riferito allo user_id che effettua l'eliminazione
     * logica di un record. */
    private User deletedBy;

    /** Timestamp riferito alla data di eliminazione del record. */
    private Date deletedDate;

    /** Riferimento numerico all'identificativo dell'utente che effettua
     * la prima azione sul record. */
    private User firstActionPerformer;

    /** Timestamp riferito alla prima azione effettuata sul record. */
    private Date firstActionDate;

    /** Riferimento numerico all'identificativo dell'utente che effettua
     * l'ultima azione sul record. */
    private User lastActionPerformer;

    /** Timestamp riferito all'ultima azione effettuata sul record. */
    private Date lastActionDate;

    /**
     * @return the isDeleted
     */
    public final boolean isDeleted() {
        return isDeleted;
    }

    /**
     * @param in the isDeleted to set
     */
    public final void setDeleted(final boolean in) {
        this.isDeleted = in;
    }

    /**
     * @return the deletedBy
     */
    public final User getDeletedBy() {
        return deletedBy;
    }

    /**
     * @param in the deletedBy to set
     */
    public final void setDeletedBy(final User in) {
        this.deletedBy = in;
    }

    /**
     * @return the deletedDate
     */
    public final Date getDeletedDate() {
        return deletedDate;
    }

    /**
     * @param in the deletedDate to set
     */
    public final void setDeletedDate(final Date in) {
        this.deletedDate = in;
    }

    /**
     * @return the firstActionPerformer
     */
    public final User getFirstActionPerformer() {
        return firstActionPerformer;
    }

    /**
     * @param in the firstActionPerformer to set
     */
    public final void setFirstActionPerformer(final User in) {
        this.firstActionPerformer = in;
    }

    /**
     * @return the firstActionDate
     */
    public final Date getFirstActionDate() {
        return firstActionDate;
    }

    /**
     * @param in the firstActionDate to set
     */
    public final void setFirstActionDate(final Date in) {
        this.firstActionDate = in;
    }

    /**
     * @return the lastActionPerformer
     */
    public final User getLastActionPerformer() {
        return lastActionPerformer;
    }

    /**
     * @param in the lastActionPerformer to set
     */
    public final void setLastActionPerformer(final User in) {
        this.lastActionPerformer = in;
    }

    /**
     * @return the lastActionDate
     */
    public final Date getLastActionDate() {
        return lastActionDate;
    }

    /**
     * @param in the lastActionDate to set
     */
    public final void setLastActionDate(final Date in) {
        this.lastActionDate = in;
    }
}
