package it.webscience.kpeople.service.datatypes;

/**
 * Classe che definisce l'ordinamento di un campo.
 */
public class SortCriteria {

    /** Ordinamento per ultimo aggiornamento. */
    public static final String ORDER_BY_LAST_UPDATE = "ORDER_BY_LAST_UPDATE";
    /** Ordinamento per data di creazione. */
    public static final String ORDER_BY_CREATION_DATE =
                                                    "ORDER_BY_CREATION_DATE";
    /** Ordinamento per data di scadenza. */
    public static final String ORDER_BY_DUE_DATE = "ORDER_BY_DUE_DATE";

    /**
     * Costante che definisce l'ordinamento ascendente.
     */
    public static final String ASC = "ASC";

    /**
     * Costante che definisce l'ordinamento discendente.
     */
    public static final String DESC = "DESC";

    /**
     * Nome del campo da ordinare.
     */
    private String fieldName;

    /**
     * Direzione dell'ordinamento.
     */
    private String order;

    /**
     * Ritorna il campo sul quale ordinare.
     * @return nome del campo
     */
    public final String getFieldName() {
        return fieldName;
    }

    /**
     * Imposta il campo sul quale ordinare.
     * @param in nome del campo
     */
    public final void setFieldName(final String in) {
        this.fieldName = in;
    }

    /**
     * Ritorna la direzione dell'ordinamento.
     * @return direzione ordinamento
     */
    public final String getOrder() {
        return order;
    }

    /**
     * Imposta la direzione dell'ordinamento. Se non è correttamente definito
     * viene impostato l'ordinamento ascendente.
     * @param in direzione ordinamento
     */
    public final void setOrder(final String in) {
        if (ASC.equalsIgnoreCase(in)) {
            this.order = in;
        } else if (DESC.equalsIgnoreCase(in)) {
            this.order = in;
        } else {
            this.order = ASC;
        }
    }
}
