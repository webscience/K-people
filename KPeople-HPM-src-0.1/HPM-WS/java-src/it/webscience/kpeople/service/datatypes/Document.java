package it.webscience.kpeople.service.datatypes;

/**
 * Tabella DOCUMENT.
 *
 */
public class Document {
    /**
     * Riferimento all'autore del documento.
     */
    private String author;

    /**
     * Indica se il documento è un template.
     */
    private boolean isTemplate;

    /**
     * indica se il documento è un document type.
     */
    private boolean isDocumentType;

    /**
     * Campo obbligatorio.
     * Valore testuale, rappresenta una URL associata al documento che viene
     * memorizzato nel sistema di gestione documentale (Alfresco).
     */
    private String guid;

    /**
     * Campo obbligatorio.
     * Hashcode associata al documento.
     */
    private String hashcode;

    /**
     * Contiene il contenuto del documento.
     * Utilizzato in MailService.
     */
    private byte[] data;

    /**
     * Nome del file.
     * Utilizzato in MailService.
     */
    private String fileName;

    /**
     * ***** campi ereditati da Attachment BEGIN ******
     */

    /**
     * Chiave primaria.
     * Identificativo univoco per un record della tabella ATTACHMENT
     */
    private int idAttachment;

    /**
     * Campo obbligatorio.
     * Chiave esterna che punta ad un record della tabella ATTACHMENT_TYPE.
     * Determina la tiplogia dell'allegato.
     */
    private AttachmentType attachmentType;

    /**
     * Valore testuale per una descrizione associata all'allegato.
     */
    private String description;

    /**
     * Valore testuale.
     * Identificativo univoco per identificare un allegato
     * in tutte le componenti del sistema.
     */
    private String hpmAttachmentId;

    /**
     * nome dell'attachment.
     */
    private String name;

    /**
     * ***** campi ereditati da Attachment END ******
     */

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
     * @return the author
     */
    public final String getAuthor() {
        return author;
    }
    /**
     * @param in the author to set
     */
    public final void setAuthor(final String in) {
        this.author = in;
    }
    /**
     * @return the guid
     */
    public final String getGuid() {
        return guid;
    }
    /**
     * @param in the guid to set
     */
    public final void setGuid(final String in) {
        this.guid = in;
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
    /**
     * @return the data
     */
    public final byte[] getData() {
        return data;
    }
    /**
     * @param in the data to set
     */
    public final void setData(final byte[] in) {
        this.data = in;
    }
    /**
     * @return the fileName
     */
    public final String getFileName() {
        return fileName;
    }
    /**
     * @param in the fileName to set
     */
    public final void setFileName(final String in) {
        this.fileName = in;
    }
    /**
     * @return the isTemplate
     */
    public final boolean isTemplate() {
        return isTemplate;
    }
    /**
     * @param in the isTemplate to set
     */
    public final void setTemplate(final boolean in) {
        this.isTemplate = in;
    }
    /**
     * @return the isDocumentType
     */
    public final boolean isDocumentType() {
        return isDocumentType;
    }
    /**
     * @param in the isDocumentType to set
     */
    public final void setDocumentType(final boolean in) {
        this.isDocumentType = in;
    }
}
