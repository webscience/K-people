package it.webscience.kpeople.datatypes;

import org.alfresco.service.cmr.repository.NodeRef;

/**
 * Tipo di dato utilizzato nel web script StoreEventWebScript.
 * Consente di mantenere il mapping tra id attachment 
 * (associato all'evento) e nodeRef.
 * @author dellanna
 *
 */
public class AlfrescoAttachment {

    /** id dell'attachment.*/
    private String id;
    
    /** nome (opzionale) del file. */
    private String fileName;
    
    /** noderef dell'attachment creato.*/
    private NodeRef nodeRef;

    /** costruttore default. */
    public AlfrescoAttachment() {
        super();
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
     * @return the nodeRef
     */
    public final NodeRef getNodeRef() {
        return nodeRef;
    }

    /**
     * @param in the nodeRef to set
     */
    public final void setNodeRef(final NodeRef in) {
        this.nodeRef = in;
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
}