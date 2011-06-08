package it.webscience.uima.ocStorageAbstract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Oggetto inviato allo store agent.
 * @author dellanna
 */
public abstract class OcStorageRequest implements Serializable {
    /** serialVersionUID. */
    private static final long serialVersionUID = -7876978726663579394L;

    /** document text contenuto nella CAS associato all'evento. */
    private String documentText;

    /** id associato all'evento. */
    private String eventId;

    /** proprietà associate all'evento. */
    private List<OcStoragePropertyRequest> properties;

    /** attachment associati all'evento. */
    private List<OcStorageAttachmentRequest> attachments;

    /** Costruttore. */
    public OcStorageRequest() {
        super();

        properties = new ArrayList<OcStoragePropertyRequest>();
        attachments = new ArrayList<OcStorageAttachmentRequest>();
    }

    /**
     * @return the eventId
     */
    public final String getEventId() {
        return eventId;
    }

    /**
     * @param in the eventId to set
     */
    public final void setEventId(final String in) {
        this.eventId = in;
    }

    /**
     * @return the properties
     */
    public final List<OcStoragePropertyRequest> getProperties() {
        return properties;
    }

    /**
     * @param in the properties to set
     */
    public final void setProperties(
            final List<OcStoragePropertyRequest> in) {
        this.properties = in;
    }

    /**
     * @return the attachments
     */
    public final List<OcStorageAttachmentRequest> getAttachments() {
        return attachments;
    }

    /**
     * @param in the attachments to set
     */
    public final void setAttachments(
            final List<OcStorageAttachmentRequest> in) {
        this.attachments = in;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the documentText
     */
    public final String getDocumentText() {
        return documentText;
    }

    /**
     * @param in the documentText to set
     */
    public final void setDocumentText(final String in) {
        this.documentText = in;
    }
}
