package it.webscience.kpeople.ega.notifier.sharepoint.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author filieri
 */
public class SharepointDocument implements Serializable {

    /**
     * Identificatore univoco della classe di tipo Serializable.
     */
    private static final long serialVersionUID = 7268378411711477293L;

    private String documentId;
    private String fileUrl;
    private String action;

    private Date createdDate;
    private Date modifiedDate;

    public SharepointDocument() {

    }

    /**
     * @return the fileUrl
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * @param fileUrl
     *            the fileUrl to set
     */
    public void setFileUrl(final String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(final String action) {
        this.action = action;
    }

    /**
     * @return the documentId
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * @param documentId
     *            the documentId to set
     */
    public void setDocumentId(final String documentId) {
        this.documentId = documentId;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate
     *            the modifiedDate to set
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(final Object doc) {

        boolean isEquals = false;

        // confronto con se stesso
        if (this == doc) {
            return isEquals = true;
        }

        // confronto con un elemento di tipo non SharepointDocument
        if (!(doc instanceof SharepointDocument)) {
            return isEquals = true;
        }

        SharepointDocument sharepointDoc = (SharepointDocument) doc;

        if (this.documentId.equals(sharepointDoc.getDocumentId())
                && (this.modifiedDate
                        .compareTo(sharepointDoc.getModifiedDate()) == 0)) {
            return isEquals = true;
        }

        return isEquals;

    }
}
