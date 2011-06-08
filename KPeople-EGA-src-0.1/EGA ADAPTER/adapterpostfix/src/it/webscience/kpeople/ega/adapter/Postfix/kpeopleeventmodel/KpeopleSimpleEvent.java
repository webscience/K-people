package it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel;

import java.util.ArrayList;
import java.util.Date;


/**
 * @author XPMUser
 */
public class KpeopleSimpleEvent extends KpeopleAbstractEvent {

    protected String details;
    
    protected ArrayList<KpeopleAttachment> attachments;

    /**
     * Identificativo univoco del sistema verticale.
     */
    protected String systemId;

    /**
     * Fornisce un riferimento fisico al “dato” associato all’azione.
     */
    protected String actionReference;

    /**
     * Identifica l’autore del contenuto associato all’evento.
     */
    protected Author author;

    /**
     * Rappresenta il titolo dell’evento.
     */
    protected String title;

    /**
     * Rappresenta il contenuto dell’evento.
     */
    protected String contentAbstract;

    /**
     * Corpo del contenuto associato all’evento.
     */
    protected String body;

    /**
     * Rappresenta il momento di generazione dell’evento.
     */
    protected String creationDate;
    
    /**
     * Indica la tipologia di documento.
     */
    protected String contentType;

    /**
     * @param idevent
     *            Identificativo univoco dell'evento.
     * @param actiontype
     *            Identificativo della tipologia di evento.
     * @param systemtype
     *            Riferimento al sistema verticale.
     * @param idaction
     *            Riferimento all'azione associata all'evento.
     */

    public KpeopleSimpleEvent(final String idevent, final String actiontype,
            final String systemtype, final Long idaction) {
        super(idevent, actiontype, systemtype, idaction);
    }






    public KpeopleSimpleEvent(String idevent, String actiontype,
            String systemtype, Long idaction, String systemId,
            String actionReference) {
        super(idevent, actiontype, systemtype, idaction);
        this.systemId = systemId;
        this.actionReference = actionReference;
    }






    /**
     * @return un oggetto di tipo String che rappresenta
     * l'identificativo univoco del sistema verticale.
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @return the actionReference
     */
    public String getActionReference() {
        return actionReference;
    }

    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the contentAbstract
     */
    public String getContentAbstract() {
        return contentAbstract;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

  
    /**
     * @param systemId the systemId to set
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }


    /**
     * @param actionReference the actionReference to set
     */
    public void setActionReference(String actionReference) {
        this.actionReference = actionReference;
    }


    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }


    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * @param contentAbstract the contentAbstract to set
     */
    public void setContentAbstract(String contentAbstract) {
        this.contentAbstract = contentAbstract;
    }


    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }


    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * @return un oggetto di tipo String che memorizza il frammento XML relativo
     *         all’evento.
     */
    public String getDetails() {
        return this.details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }


    /**
     * @return the attachments
     */
    public ArrayList<KpeopleAttachment> getAttachments() {
        return attachments;
    }


    /**
     * @param attachments the attachments to set
     */
    public void setAttachments(ArrayList<KpeopleAttachment> attachments) {
        this.attachments = attachments;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    

}
