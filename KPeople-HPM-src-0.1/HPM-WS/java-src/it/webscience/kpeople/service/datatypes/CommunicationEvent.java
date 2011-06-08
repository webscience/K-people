package it.webscience.kpeople.service.datatypes;

import java.util.Date;

/**
 * @author depascalis
 */
public class CommunicationEvent {

    /**
     * Oggetto della mail.
     */
    private String object;

    /**
     * Body della mail.
     */
    private String body = null;

    /**
     * Lista di Business entity relativa agli utenti che sono in CC
     * alla mail.
     */
    private User[] ccUser;

    /**
     * Lista di Business entity relativa agli utenti che sono in CCn.
     */
    private User[] ccnUser;

    /**
     * Business entity relativa all'utente che ha spedito la mail.
     */
    private User userFrom;

    /**
     *  Business Entity relativa all'utente a cui � diretta la mail.
     */
    private User[] toUser;

    /**
     * Lista di attachment associati alla mail.
     */
    private Document[] docList;

    /**
     * Data di creazione della mail.
     */
    private Date creationDate;

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
     * ***** campi ereditati da Attachment BEGIN ******
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
     * Costruttore della classe.
     */
    public CommunicationEvent() {
        super();
    }

    /**
     * @return body della mail.
     */
    public final String getBody() {
        return body;
    }

    /**
     * @param in body della mail.
     */
    public final void setBody(final String in) {
        this.body = in;
    }

    /**
     * @return data di creazione della mail
     */
    public final Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param in data di creazione della mail.
     */
    public final void setCreationDate(final Date in) {
        this.creationDate = in;
    }

    /**
     * @param in utente che ha inviato la mail.
     */
    public final void setUserFrom(final User in) {
        this.userFrom = in;
    }

    /**
     * @return utente che ha inviato la mail.
     */
    public final User getUserFrom() {
        return userFrom;
    }

    /**
     * @param in oggetto della mail.
     */
    public final void setObject(final String in) {
        this.object = in;
    }

    /**
     * @return oggetto della mail.
     */
    public final String getObject() {
        return object;
    }

    /**
     * @return the ccUser
     */
    public final User[] getCcUser() {
        return ccUser;
    }

    /**
     * @param in the ccUser to set
     */
    public final void setCcUser(final User[] in) {
        this.ccUser = in;
    }

    /**
     * @return the ccnUser
     */
    public final User[] getCcnUser() {
        return ccnUser;
    }

    /**
     * @param in the ccnUser to set
     */
    public final void setCcnUser(final User[] in) {
        this.ccnUser = in;
    }

    /**
     * @return the toUser
     */
    public final User[] getToUser() {
        return toUser;
    }

    /**
     * @param in the toUser to set
     */
    public final void setToUser(final User[] in) {
        this.toUser = in;
    }

    /**
     * @return the docList
     */
    public final Document[] getDocList() {
        return docList;
    }

    /**
     * @param in the docList to set
     */
    public final void setDocList(final Document[] in) {
        this.docList = in;
    }
}
