package it.webscience.uima.ocStorageAbstract;

import java.io.Serializable;

/** Attachment associata all'evento.
 * Oggetto serializzato ed inviato allo storage agent
 * @author dellanna
 */
public class OcStorageAttachmentRequest implements Serializable {
    /** serialVersionUID. */
    private static final long serialVersionUID = -2728556804425388916L;

    /** tipo MIME di documento. */
    private String type;

    /** ID associato al documento. */
    private String id;

    /** binario. */
    private String data;

    /** nome del documento. */
    private String name;

    /** hashcode. */
    private String hashcode;

    /**
     * @return the type
     */
    public final String getType() {
        return type;
    }

    /**
     * @param in the type to set
     */
    public final void setType(final String in) {
        this.type = in;
    }

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param in the id to set
     */
    public final void setId(final String in) {
        this.id = in;
    }

    /**
     * @return the data
     */
    public final String getData() {
        return data;
    }

    /**
     * @param in the data to set
     */
    public final void setData(final String in) {
        this.data = in;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param in the name to set
     */
    public final void setName(final String in) {
        this.name = in;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the hashcode
     */
    public final String getHashcode() {
        return hashcode;
    }

    /**
     * @param in the hashcode to set
     */
    public final void setHashcode(final String in) {
        this.hashcode = in;
    }
}
