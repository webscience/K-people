package it.webscience.kpeople.be;

import java.util.ArrayList;
import java.util.List;

/**
 * tabella ATTACHMENT.
 * @author dellanna
 *
 */
public class Attachment extends DataTraceClass {
    /**
     * Chiave primaria.
     * Identificativo univoco per un record della tabella ATTACHMENT
     */
    protected int idAttachment;

    /**
     * Lista di attachment associati alla mail.
     */
    private List<Document> docList = new ArrayList<Document>();

    /**
     * Campo obbligatorio.
     * Chiave esterna che punta ad un record della tabella ATTACHMENT_TYPE.
     * Determina la tiplogia dell'allegato.
     */
    protected AttachmentType attachmentType;

    /**
     * Valore testuale per una descrizione associata all'allegato.
     */
    protected String description;

    /**
     * Valore testuale.
     * Identificativo univoco per identificare un allegato
     * in tutte le componenti del sistema.
     */
    protected String hpmAttachmentId;

    /**
     * nome dell'attachment.
     */
    protected String name;

    /**
     * @return the idAttachment
     */
    public final int getIdAttachment() {
        return idAttachment;
    }

    /**
     * @param in the idAttachment to set
     */
    public final void setIdAttachment(final int in) {
        this.idAttachment = in;
    }

    /**
     * @return the attachmentType
     */
    public final AttachmentType getAttachmentType() {
        return attachmentType;
    }

    /**
     * @param in the attachmentType to set
     */
    public final void setAttachmentType(final AttachmentType in) {
        this.attachmentType = in;
    }

    /**
     * @return the description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param in the description to set
     */
    public final void setDescription(final String in) {
        this.description = in;
    }

    /**
     * @return the hpmAttachmentId
     */
    public final String getHpmAttachmentId() {
        return hpmAttachmentId;
    }

    /**
     * @param in the hpmAttachmentId to set
     */
    public final void setHpmAttachmentId(final String in) {
        this.hpmAttachmentId = in;
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
     * @return the docList
     */
    public List<Document> getDocList() {
        return docList;
    }

    /**
     * @param docList the docList to set
     */
    public void setDocList(List<Document> docList) {
        this.docList = docList;
    }
}
