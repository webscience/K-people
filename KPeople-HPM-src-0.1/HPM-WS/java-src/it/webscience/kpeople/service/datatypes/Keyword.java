package it.webscience.kpeople.service.datatypes;

/**
 * Tabella KEYWORD.
 *
 */
public class Keyword  {
    /**
     * Chiave primaria.
     * Identificativo univoco per un record della tabella Keyword
     */
    private int idKeyword;

    /**
     * Campo obbligatorio.
     * Valore testuale in cui memorizzare la parola chiave.
     */
    private String keyword;

    /**
     * Valore testuale.
     * Identificativo univoco per identificare una keyword in tutte le
     * componenti del sistema.
     */
    private String hpmKeywordId;

    /**
     * Valore testuale in cui memorizzare la descrizione.
     */
    private String description;

    /**
     * valore della keyword.
     */
    private String value;


    /** Costruttore. */
    public Keyword() {
        super();
    }

    /**
     * Costruttore.
     * @param in1 idKeyword
     * @param in2 keyword
     * @param in3 hpmKeywordId
     */
    public Keyword(
            final int in1,
            final String in2,
            final String in3) {
        super();
        this.idKeyword = in1;
        this.keyword = in2;
        this.hpmKeywordId = in3;
    }

    /**
     * @return the idKeyword
     */
    public final int getIdKeyword() {
        return idKeyword;
    }

    /**
     * @param in the idKeyword to set
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
     * @param in the keyword to set
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
     * @param in the hpmKeywordId to set
     */
    public final void setHpmKeywordId(final String in) {
        this.hpmKeywordId = in;
    }

    /**
     * @param in description to set.
     */
    public final void setDescription(final String in) {
        this.description = in;
    }
    /**
     * @return the description
     */
    public final String getDescription() {
        return description;
    }
    /**
     * @param in the value to set.
     */
    public final void setValue(final String in) {
        this.value = in;
    }

    /**
     * @return the value
     */
    public final String getValue() {
        return value;
    }
}
