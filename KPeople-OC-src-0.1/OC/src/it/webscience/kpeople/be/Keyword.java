package it.webscience.kpeople.be;

/**
 * Tabella KEYWORD.
 *
 */
public class Keyword extends DataTraceClass {
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

    
    /** costruttore. */
    public Keyword(
            final int idKeyword,
            final String keyword,
            final String hpmKeywordId) {
        super();
        this.idKeyword = idKeyword;
        this.keyword = keyword;
        this.hpmKeywordId = hpmKeywordId;
    }
    
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
}
