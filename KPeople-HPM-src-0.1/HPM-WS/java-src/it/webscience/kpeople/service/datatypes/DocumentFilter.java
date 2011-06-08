package it.webscience.kpeople.service.datatypes;

/**
 * Definisce i parametri di ricerca dei documenti.
 */
public class DocumentFilter {

    /**
     * identificativo del processo di cui si vogliono estrarre i documenti.
     */
    private String hpmProcessId;

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
}
