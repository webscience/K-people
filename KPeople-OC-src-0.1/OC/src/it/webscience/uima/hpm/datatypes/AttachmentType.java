package it.webscience.uima.hpm.datatypes;

/**
 * Tabella ATTACHMENT_TYPE.
 * @author dellanna
 *
 */
public class AttachmentType {
    /**
     * Chiave primaria.
     * Identificativo univoco per un record della tabella ATTACHMENT_TYPE
     */
    private int idAttachmentType;

    /**
     * Campo obbligatorio.
     * Valore testuale in cui memorizzare il nome della titplogia di
     * un allegato.
     */
    private String name;

    /** Costruttore.
     * @param in id
     */
    public AttachmentType(final int in) {
        super();
        this.idAttachmentType = in;
    }

    /**
     * @return the idAttachmentType
     */
    public final int getIdAttachmentType() {
        return idAttachmentType;
    }

    /**
     * @param in the idAttachmentType to set
     */
    public final void setIdAttachmentType(final int in) {
        this.idAttachmentType = in;
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
}
