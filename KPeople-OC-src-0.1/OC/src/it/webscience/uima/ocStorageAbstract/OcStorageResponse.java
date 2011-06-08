package it.webscience.uima.ocStorageAbstract;

import java.io.Serializable;
import java.util.Hashtable;

/** risposta generata dallo store agent.
 * @author dellanna
 */
public abstract class OcStorageResponse implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -9048551169824388610L;

    /** valore di uscita.<br>
     * <ul>
     *  <li><b>0:</b> evento registrato correttamente nello store<br></li>
     *  <li><b>&gt; 0:</b> lo store ha generato un errore.
     *          Il messaggio è presente nel campo <i>errorMessage</i></li>
     * </ul>
     */
    protected int exitCode;

    /** messaggio presente se il salvataggio ha avuto esito negativo. */
    protected String errorMessage;

    /** nodeRefId della cartella associata all'evento. */
    protected String eventNodeRefId;

    /** url relativo all'attachment contenente l'xml associato all'evento. */
    protected String eventUrl;

    /** url associati agli attachments salvati.
     *  <b>key:</b> id dell'attachment
     *  <b>value:</b> url per il download dell'attachment
     */
    protected Hashtable<String, String> urlMap;


    /** costruttore. */
    public OcStorageResponse() {
        super();
        urlMap = new Hashtable<String, String>();
    }

    /**
     * @return the urlMap
     */
    public final Hashtable<String, String> getUrlMap() {
        return urlMap;
    }

    /**
     * @param in the urlMap to set
     */
    public final void setUrlMap(final Hashtable<String, String> in) {
        this.urlMap = in;
    }

    /**
     * @return the eventNodeRefId
     */
    public final String getEventNodeRefId() {
        return eventNodeRefId;
    }

    /**
     * @param in the eventNodeRefId to set
     */
    public final void setEventNodeRefId(final String in) {
        this.eventNodeRefId = in;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the exitCode
     */
    public final int getExitCode() {
        return exitCode;
    }

    /**
     * @param in the exitCode to set
     */
    public final void setExitCode(final int in) {
        this.exitCode = in;
    }

    /**
     * @return the errorMessage
     */
    public final String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param in the errorMessage to set
     */
    public final void setErrorMessage(final String in) {
        this.errorMessage = in;
    }

    /**
     * @return the eventUrl
     */
    public final String getEventUrl() {
        return eventUrl;
    }

    /**
     * @param in the eventUrl to set
     */
    public final void setEventUrl(final String in) {
        this.eventUrl = in;
    }
}
