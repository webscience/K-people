package it.webscience.kpeople.be;

/**
 * @author depascalis
 * Definisce i parametri di ricerca degli eventi.
 */
public class EventFilter {

    /**
     * Identificativo del processo di cui si vogliono estrarre gli eventi.
     */
    private String hpmProcessId;

    /**
     * Identificativo del pattern type.
     */
    private String patternType;

    /**
     * Identificativo comune a tutti i sottosistemi di KPeople del
     * processo di cui si vogliono estrarre gli eventi.
     */
    private String hpmPatternId;

    /**
     * Indentificativo dell’utente che ha scatenato l’evento.
     */
    private String hpmUserId;

    /**
     * Valore testuale per la ricerca libera.
     */
    private String freeText;

    /**
     * Classe per definire l'ordinamento degli eventi in uscita.
     */
    private SortCriteria sort;

    /**
     * @param in imposta l'hpmProcessId.
     */
    public final void setHpmProcessId(final String in) {
        this.hpmProcessId = in;
    }

    /**
     * @param in imposta il patternType.
     */
    public final void setPatternType(final String in) {
        this.patternType = in;
    }

    /**
     * @param in imposta l'hpmPatternId.
     */
    public final void setHpmPatternId(final String in) {
        this.hpmPatternId = in;
    }

    /**
     * @param in imposta l'hpmUserId.
     */
    public final void setHpmUserId(final String in) {
        this.freeText = in;
    }

    /**
     * @param in imposta il campo di ricerca libera.
     */
    public final void setFreeText(final String in) {
        this.freeText = in;
    }

    /**
     * @param in imposta il criterio di ordinamento degli eventi.
     */
    public final void setSort(final SortCriteria in) {
        this.sort = in;
    }

    /**
     * @return l'hpmProcessId.
     */
    public final String getHpmProcessId() {
        return this.hpmProcessId;
    }

    /**
     * @return il patternType.
     */
    public final String getPatternType() {
        return this.patternType;
    }

    /**
     * @return l'hpmPatternId.
     */
    public final String getHpmPatternId() {
        return this.hpmPatternId;
    }

    /**
     * @return l'hpmUserId.
     */
    public final String getHpmUserId() {
        return this.freeText;
    }

    /**
     * @return il campo di ricerca libera.
     */
    public final String getFreeText() {
        return this.freeText;
    }

    /**
     * @return il criterio di ordinamento degli eventi.
     */
    public final SortCriteria getSort() {
        return this.sort;
    }


}
