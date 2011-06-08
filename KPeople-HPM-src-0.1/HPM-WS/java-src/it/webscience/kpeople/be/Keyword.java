package it.webscience.kpeople.be;

/**
 * Tabella KEYWORD.
 */
public class Keyword extends DataTraceClass {

    /**
     * Id per la keyword di default da associare al processo.
     */
    public static final int DEFAULT_KEYWORD = 1;

    /**
     * Chiave primaria. Identificativo univoco per un record della tabella
     * Keyword
     */
    private int idKeyword;

    /**
     * Campo obbligatorio. Valore testuale in cui memorizzare la parola chiave.
     */
    private String keyword;

    /**
     * Valore testuale in cui memorizzare la descrizione.
     */
    private String description;

    /**
     * Valore testuale. Identificativo univoco per identificare una keyword in
     * tutte le componenti del sistema.
     */
    private String hpmKeywordId;

    /**
     * valore della keyword.
     */
    private String value;

    /**
     * Costruttore.
     * @param idKeywordIn
     *            campo idKeyword dell'oggetto
     * @param keywordIn
     *            campo keyword dell'oggetto
     * @param hpmKeywordIdIn
     *            campo hpmKeywordId dell'oggetto
     */
    public Keyword(final int idKeywordIn, final String keywordIn,
            final String hpmKeywordIdIn) {
        super();

        this.idKeyword = idKeywordIn;
        this.keyword = keywordIn;
        this.hpmKeywordId = hpmKeywordIdIn;
    }

    /** Costruttore. */
    public Keyword() {
        super();
    }

    /**
     * @return the idKeyword
     */
    public final int getIdKeyword() {
        return idKeyword;
    }

    /**
     * @param in
     *            the idKeyword to set
     */
    public final void setIdKeyword(final int in) {
        this.idKeyword = in;
    }

    /**
     * @return the keyword
     */
    public final String getKeyword() {
        return keyword;
    }

    /**
     * @param in
     *            the keyword to set
     */
    public final void setKeyword(final String in) {
        this.keyword = in;
    }

    /**
     * @return the hpmKeywordId
     */
    public final String getHpmKeywordId() {
        return hpmKeywordId;
    }

    /**
     * @param in
     *            the hpmKeywordId to set
     */
    public final void setHpmKeywordId(final String in) {
        this.hpmKeywordId = in;
    }

    /**
     * @return the value
     */
    public final String getValue() {
        return value;
    }

    /**
     * @param in
     *            the value to set
     */
    public final void setValue(final String in) {
        this.value = in;
    }

    /**
     * @return the description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param in
     *            the description to set
     */
    public final void setDescription(final String in) {
        this.description = in;
    }
}
