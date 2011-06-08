package it.webscience.kpeople.service.datatypes;



import java.util.Date;

/**
 * Business Entity Pattern.
 * @author gnoni
 *
 */
public class Pattern {
    
	/**
     * Costruttore.
     */
    public Pattern() {
        super();

    }
    
	/**
     * Chiave primaria.
     * Identificativo univoco per un record della tabella ATTACHMENT
     */
    private int idAttachment;

    /**
     * Lista di attachment associati alla mail.
     */
    private Document[] docList;
    
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
     * nome dell'attachment.
     */
    private String name;
    
	
	/**
	 * Business entity relativa alla tipologia di un pattern.
	 */
	private PatternType patternType;


	/**
	 * Business entity relativa allo stato di un pattern.
	 */
	private PatternState patternState;


	/**
	 * Medatati associati al pattern.
	 */
	private PatternMetadata[] patternMetadatas;




	/**
	 * Business entity relativa all'utente che ha aperto e avviato il pattern.
	 */
	private User patternRequestor;


	/**
	 * Business entity relativa all'utente che deve contribuire all'esecuzione 
	 * delle attività di un pattern.
	 */
	private User patternProvider;


	/**
	 * Lista di business entity di tipo User che sono coinvolti nel pattern 
	 * senza averne parte attiva.
	 */
	private User[] ccUsers;

	/**
	 * Data di inizio del pattern.
	 */
	private Date startDate;

	/**
	 * Data di chiusura del pattern.
	 */
	private Date endDate;

	/**
	 * Data di scadenza del pattern.
	 */
	private Date dueDate;

	/**
	 * Identificativo relativo alla istanza di un flow nel dominio del sotto 
	 * componente Activiti.
	 */
	private String activitiProcessInstanceId;

	/**
	 * Identificativo univoco di un pattern nel dominio del sistema KPeople.
	 */
	private String hpmPatternId;

	/**
	 * 
	 */
	private boolean waitingActivity;
	
	/**
	 * 
	 */
	private Date waitingActivityDate;
	
	/**
     * Metodo getter per il membro patternType.
     * @return idPattern
     */
	public final PatternType getPatternType() {
		return patternType;
	}

	/**
     * Metodo setter per il membro patternType.
     * @param pPatternType parametro formale per patternType.
     */
	public final void setPatternType(final PatternType pPatternType) {
		this.patternType = pPatternType;
	}


	/**
     * Metodo getter per il membro patternState.
     * @return patternState
     */
	public final PatternState getPatternState() {
		return patternState;
	}

	/**
     * Metodo setter per il membro patternState.
     * @param pPatternState parametro formale per patternState.
     */
	public final void setPatternState(final PatternState pPatternState) {
		this.patternState = pPatternState;
	}

	/**
     * Metodo getter per il membro patternMetadatas.
     * @return patternMetadatas
     */
	public final PatternMetadata[] getPatternMetadatas() {
		return patternMetadatas;
	}

	/**
     * Metodo setter per il membro patternMetadatas.
     * @param pPatternMetadatas parametro formale per patternMetadatas.
     */
	public final void setPatternMetadatas(
			final PatternMetadata[] pPatternMetadatas) {
		this.patternMetadatas = pPatternMetadatas;
	}

	/**
     * Metodo getter per il membro patternRequestor.
     * @return patternRequestor
     */
	public final User getPatternRequestor() {
		return patternRequestor;
	}

	/**
     * Metodo setter per il membro patternRequestor.
     * @param pPatternRequestor parametro formale per patternRequestor.
     */
	public final void setPatternRequestor(final User pPatternRequestor) {
		this.patternRequestor = pPatternRequestor;
	}

	/**
     * Metodo getter per il membro patternProvider.
     * @return patternProvider
     */
	public final User getPatternProvider() {
		return patternProvider;
	}

	/**
     * Metodo setter per il membro patternProvider.
     * @param pPatternProvider parametro formale per patternProvider.
     */
	public final void setPatternProvider(final User pPatternProvider) {
		this.patternProvider = pPatternProvider;
	}

	/**
     * Metodo getter per il membro patternType.
     * @return idPattern
     */
	public final User[] getCcUsers() {
		return ccUsers;
	}

	/**
     * Metodo setter per il membro ccUsers.
     * @param pCcUsers parametro formale per ccUsers.
     */
	public final void setCcUsers(final User[] pCcUsers) {
		this.ccUsers = pCcUsers;
	}

	/**
     * Metodo getter per il membro patternType.
     * @return idPattern
     */
	public final Date getStartDate() {
		return startDate;
	}

	/**
     * Metodo setter per il membro startDate.
     * @param pStartDate parametro formale per startDate.
     */
	public final void setStartDate(final Date pStartDate) {
		this.startDate = pStartDate;
	}

	/**
     * Metodo getter per il membro endDate.
     * @return endDate
     */
	public final Date getEndDate() {
		return endDate;
	}

	/**
     * Metodo setter per il membro endDate.
     * @param pEndDate parametro formale per endDate.
     */
	public final void setEndDate(final Date pEndDate) {
		this.endDate = pEndDate;
	}


	/**
     * Metodo getter per il membro dueDate.
     * @return dueDate
     */
	public final Date getDueDate() {
		return dueDate;
	}

	/**
     * Metodo setter per il membro dueDate.
     * @param pDueDate parametro formale per dueDate.
     */
	public final void setDueDate(final Date pDueDate) {
		this.dueDate = pDueDate;
	}

	/**
     * Metodo getter per il membro activitiProcessInstanceId.
     * @return activitiProcessInstanceId
     */
	public final String getActivitiProcessInstanceId() {
		return activitiProcessInstanceId;
	}

	/**
     * Metodo setter per il membro activitiProcessInstanceId.
     * @param pActivitiProcessInstanceId parametro formale per 
     * activitiProcessInstanceId.
     */
	public final void setActivitiProcessInstanceId(
			final String pActivitiProcessInstanceId) {
		this.activitiProcessInstanceId = pActivitiProcessInstanceId;
	}

	/**
     * Metodo getter per il membro hpmPatternId.
     * @return hpmPatternId
     */
	public final String getHpmPatternId() {
		return hpmPatternId;
	}

	/**
     * Metodo setter per il membro pHpmPatternId.
     * @param pHpmPatternId parametro formale per hpmPatternId.
     */
	public final void setHpmPatternId(final String pHpmPatternId) {
		this.hpmPatternId = pHpmPatternId;
	}

    /**
     * @return the idAttachment
     */
    public int getIdAttachment() {
        return idAttachment;
    }

    /**
     * @param idAttachment the idAttachment to set
     */
    public void setIdAttachment(int idAttachment) {
        this.idAttachment = idAttachment;
    }

    /**
     * @return the attachmentType
     */
    public AttachmentType getAttachmentType() {
        return attachmentType;
    }

    /**
     * @param attachmentType the attachmentType to set
     */
    public void setAttachmentType(AttachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * 
     * @return
     */
	public final boolean isWaitingActivity() {
		return waitingActivity;
	}

	/**
	 * 
	 * @param waitingActivity
	 */
	public final void setWaitingActivity(final boolean waitingActivity) {
		this.waitingActivity = waitingActivity;
	}

	/**
	 * 
	 * @return
	 */
	public final Date getWaitingActivityDate() {
		return waitingActivityDate;
	}

	/**
	 * 
	 * @param waitingActivityDate
	 */
	public final void setWaitingActivityDate(final Date waitingActivityDate) {
		this.waitingActivityDate = waitingActivityDate;
	}
    
	/**
	 * 
	 * @param pKeyname
	 * @return
	 */
	public String getPatternMetadataValueByKeyname(String pKeyname) {
		
		String keyName = pKeyname;
		String keyValue = null;
		
		if (this.patternMetadatas!=null) {
			for (int i = 0; i < patternMetadatas.length; i++) {
				PatternMetadata meta = patternMetadatas[i];
				if (meta.getKeyname().equals(keyName)) {
					keyValue = meta.getValue();
					break;
				}
			}
		}
		
		return keyValue;
		
	}
}
