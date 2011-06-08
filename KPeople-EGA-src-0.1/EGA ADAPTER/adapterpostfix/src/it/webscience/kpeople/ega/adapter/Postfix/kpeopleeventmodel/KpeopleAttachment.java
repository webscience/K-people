package it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel;

import java.io.Serializable;

/**
 * @author XPMUser
 */
public class KpeopleAttachment implements Serializable {

    private static final long serialVersionUID = 7411436672600222575L;

    /**
     * Identificativo univoco per identificare univocamente
     * l'allegato.
     */
    private String idAttachment;

    /**
     * Valore testuale che identifica la tipologia di allegato.
     */
    private String attachmentType;

    /**
     * Valore testuale che rappresenta il il contenuto del file.
     */
    private String attachmentData;

    /**
     * Valore testuale che identifica il nome del contenuto.
     */
    private String attachmentName;

    /**
     * Valore testuale che identifica il codice hash associato all'allegato.
     */
    private String attachmentHashcode;

    /**
     * @return the idEvent
     */
    public String getIdAttachment() {
        return idAttachment;
    }

    /**
     * @param idEvent
     *            the idEvent to set
     */
    public void setIdAttachment(String idAttachment) {
        this.idAttachment = idAttachment;
    }

    /**
     * @return the attachmentType
     */
    public String getAttachmentType() {
        return attachmentType;
    }

    /**
     * @param attachmentType
     *            the attachmentType to set
     */
    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    /**
     * @return the attachmentData
     */
    public String getAttachmentData() {
        return attachmentData;
    }

    /**
     * @param attachmentData
     *            the attachmentData to set
     */
    public void setAttachmentData(String attachmentData) {
        this.attachmentData = attachmentData;
    }

    /**
     * @return the attachmentName
     */
    public String getAttachmentName() {
        return attachmentName;
    }

    /**
     * @param attachmentName
     *            the attachmentName to set
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    /**
     * @return the attachmentHashcode
     */
    public String getAttachmentHashcode() {
        return attachmentHashcode;
    }

    /**
     * @param attachmentHashcode
     *            the attachmentHashcode to set
     */
    public void setAttachmentHashcode(String attachmentHashcode) {
        this.attachmentHashcode = attachmentHashcode;
    }

}
