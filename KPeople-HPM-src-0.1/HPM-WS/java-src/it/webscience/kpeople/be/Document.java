package it.webscience.kpeople.be;

/**
 * Tabella DOCUMENT.
 *
 */
public class Document extends Attachment {
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
