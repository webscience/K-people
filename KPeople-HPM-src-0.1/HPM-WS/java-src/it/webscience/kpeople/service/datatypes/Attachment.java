package it.webscience.kpeople.service.datatypes;

/**
 * tabella ATTACHMENT.
 * @author dellanna
 *
 */
public class Attachment  {
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
     * Campo popolato nel caso di Attachment di tipo CommunicationEvent.
     */
    private CommunicationEvent communicationEvent;

    /**
     * Campo popolato nel caso di Attachment di tipo Document.
     */
    private Document document;

    /**
     * Campo popolato nel caso di Attachment di tipo Pattern.
     */
    private Pattern pattern;
    
    /**
     * Valore testuale per una descrizione associata all'allegato.
     */
    private String description;
    
    /**
     * @return the idProcess
     */
    protected int idProcess;
    
    /**
     * @return the hpmProcessId
     */
    protected String hpmProcessId;
    
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
     * @return the communicationEvent
     */
    public final CommunicationEvent getCommunicationEvent() {
        return communicationEvent;
    }

    /**
     * @param in the communicationEvent to set
     */
    public final void setCommunicationEvent(final CommunicationEvent in) {
        this.communicationEvent = in;
    }

    /**
     * @return the document
     */
    public final Document getDocument() {
        return document;
    }

    /**
     * @param in the document to set
     */
    public final void setDocument(final Document in) {
        this.document = in;
    }
    
    /**
     * @return the document
     */
    public final Pattern getPattern() {
        return pattern;
    }

    /**
     * @param in the document to set
     */
    public final void setPattern(final Pattern in) {
        this.pattern = in;
    }
    
    /**
     * @return hpmProcessId
     */
	public final String getHpmProcessId() {
		return hpmProcessId;
	}

	/**
     * @param in the hpmProcessId to set
     */
	public final void setHpmProcessId(final String pHpmProcessId) {
		this.hpmProcessId = pHpmProcessId;
	}
	
	/**
     * @return idProcess
     */
	public final int getIdProcess() {
		return idProcess;
	}

	/**
     * @param in the idProcess to set
     */
	public final void setIdProcess(final int pIdProcess) {
		this.idProcess = pIdProcess;
	}
}
