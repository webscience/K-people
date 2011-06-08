package it.webscience.kpeople.service.datatypes;

import java.util.Date;

/**
 * Business Entity Activity.
 * @author gnoni
 *
 */
public class Activity extends DataTraceClass {
	
	/**
	 *  Identificativo univoco di un’attività.
	 */
	private int idActivity;
	
	/**
	  * Lista di attachment associati alla mail.
	  */
	private Document[] docList;
	    
	/**
	 *  Tiplogia dell’attività.
	 */
	private ActivityType activityType;
	
	/**
	 * Identificativo del pattern associato.
	 */
	private int idPattern;
	
	/**
	 * .
	 */
	private Pattern pattern;
	/**
	 *  Titolo dell’attività.
	 */
	private String title;
	
	/**
	 *  Descrizione dell’attività.
	 */
	private String description;
	
	/**
	 *  Utente che ha in carico l’attività.
	 */
	private User activityOwner;
	
	/**
	 *  Utente che ha richiesto l’attività.
	 */
	private User activityRequestor;
	
	/**
	 *  Data di creazione dell’attività.
	 */
	private Date createDate;
	
	/**
	 *  Data di scadenza dell’attività.
	 */
	private Date dueDate;
	
	/**
	 *  Booleano che specifica se un’attività è chiusa.
	 */
	private boolean closed;
	
	/**
	 *  Data di chiusura dell’attività.
	 */
	private Date closedDate;
	
	/**
	 *  Stato del pattern.
	 */
	private ActivityState activityState;
	
	/**
	 *  Identificativo di un’attività nel sottosistema Activiti.
	 */
	private String activitiProcessTaskId;
	
	/**
	 *  Identificativo univoco di un’attività nel dominio KPeople.
	 */
	private String hpmActivityId;
	
	/**
	 *  Metadati associati all’attività.
	 */
	private ActivityMetadata[] activityMetadata;

	
	
	
	
	/**
	 * Metodo getter per il membro idActivity.
	 * @return idActivity
	 */
	public final int getIdActivity() {
		return idActivity;
	}

	/**
     * Metodo setter per il membro idActivity.
     * @param pIdActivity parametro formale per idActivity.
     */
	public final void setIdActivity(final int pIdActivity) {
		this.idActivity = pIdActivity;
	}

	/**
	 * Metodo getter per il membro title.
	 * @return title
	 */
	public final String getTitle() {
		return title;
	}

	/**
     * Metodo setter per il membro title.
     * @param pTitle parametro formale per title.
     */
	public final void setTitle(final String pTitle) {
		this.title = pTitle;
	}

	/**
	 * Metodo getter per il membro description.
	 * @return description
	 */
	public final String getDescription() {
		return description;
	}

	/**
     * Metodo setter per il membro description.
     * @param pDescription parametro formale per description.
     */
	public final void setDescription(final String pDescription) {
		this.description = pDescription;
	}
	
	/**
	 * Metodo getter per il membro activityOwner.
	 * @return activityOwner
	 */
	public final User getActivityOwner() {
		return activityOwner;
	}
	
	/**
     * Metodo setter per il membro activityOwner.
     * @param pActivityOwner parametro formale per activityOwner.
     */
	public final void setActivityOwner(final User pActivityOwner) {
		this.activityOwner = pActivityOwner;
	}

	/**
	 * Metodo getter per il membro createDate.
	 * @return createDate
	 */
	public final Date getCreateDate() {
		return createDate;
	}

	/**
     * Metodo setter per il membro createDate.
     * @param pCreateDate parametro formale per createDate.
     */
	public final void setCreateDate(final Date pCreateDate) {
		this.createDate = pCreateDate;
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
	 * Metodo getter per il membro closed.
	 * @return closed
	 */
	public final boolean isClosed() {
		return closed;
	}

	/**
     * Metodo setter per il membro closed.
     * @param pClosed parametro formale per closed.
     */
	public final void setClosed(final boolean pClosed) {
		this.closed = pClosed;
	}

	/**
	 * Metodo getter per il membro closedDate.
	 * @return closedDate
	 */
	public final Date getClosedDate() {
		return closedDate;
	}

	/**
     * Metodo setter per il membro closedDate.
     * @param pClosedDate parametro formale per closedDate.
     */
	public final void setClosedDate(final Date pClosedDate) {
		this.closedDate = pClosedDate;
	}

	/**
	 * Metodo getter per il membro activitiProcessTaskId.
	 * @return activitiProcessTaskId
	 */
	public final String getActivitiProcessTaskId() {
		return activitiProcessTaskId;
	}

	/**
     * Metodo setter per il membro activitiProcessTaskId.
     * @param pActivitiProcessTaskId parametro formale per 
     * activitiProcessTaskId.
     */
	public final void setActivitiProcessTaskId(
			final String pActivitiProcessTaskId) {
		this.activitiProcessTaskId = pActivitiProcessTaskId;
	}

	/**
	 * Metodo getter per il membro hpmActivityId.
	 * @return hpmActivityId
	 */
	public final String getHpmActivityId() {
		return hpmActivityId;
	}

	/**
     * Metodo setter per il membro hpmActivityId.
     * @param pHpmActivityId parametro formale per hpmActivityId.
     */
	public final void setHpmActivityId(final String pHpmActivityId) {
		this.hpmActivityId = pHpmActivityId;
	}

	/**
	 * Metodo getter per il membro activityMetadata.
	 * @return activityMetadata
	 */
	public final ActivityMetadata[] getActivityMetadata() {
		return activityMetadata;
	}

	/**
     * Metodo setter per il membro activityMetadata.
     * @param pActivityMetadata parametro formale per activityMetadata.
     */
	public final void setActivityMetadata(
			final ActivityMetadata[] pActivityMetadata) {
		this.activityMetadata = pActivityMetadata;
	}

	/**
	 * Metodo getter per il membro activityState.
	 * @return activityState
	 */
	public final ActivityState getActivityState() {
		return activityState;
	}

	/**
     * Metodo setter per il membro activityState.
     * @param pActivityState parametro formale per activityState.
     */
	public final void setActivityState(final ActivityState pActivityState) {
		this.activityState = pActivityState;
	}

	/**
	 * Metodo getter per la tipologia dell'attività.
	 * @return activityType
	 */
	public final ActivityType getActivityType() {
		return activityType;
	}

	/**
	 * Metodo setter per la tipologia dell'attività.
	 * @param pActivityType parametro formale
	 */
	public final void setActivityType(final ActivityType pActivityType) {
		this.activityType = pActivityType;
	}
	
	/**
	 * metodo get per il membro idPattern.
	 * @return identificativo pattern associato
	 */
	public final int getIdPattern() {
		return idPattern;
	}

	/**
	 * metodo set per il membro idPattern.
	 * @param pIdPattern identificativo pattern associato
	 */
	public final void setIdPattern(final int pIdPattern) {
		this.idPattern = pIdPattern;
	}

	/**
	 * metodo get per il membro activityRequestor.
	 * @return identificativo pattern associato
	 */
	public final User getActivityRequestor() {
		return activityRequestor;
	}

	/**
	 * metodo set per il membro activityRequestor.
	 * @param pActivityRequestor utente che richiede l'attività
	 */
	public final void setActivityRequestor(final User pActivityRequestor) {
		this.activityRequestor = pActivityRequestor;
	}

	public final Pattern getPattern() {
		return pattern;
	}

	public final void setPattern(final Pattern pPattern) {
		this.pattern = pPattern;
	}
	
	/**
	 * 
	 * @param pKeyname
	 * @return
	 */
	public String getActivityMetadataValueByKeyname(String pKeyname) {
		
		String keyName = pKeyname;
		String keyValue = null;
		
		if (activityMetadata!=null) {
			for (int i = 0; i < activityMetadata.length; i++) {
				ActivityMetadata meta = activityMetadata[i];
				if (meta.getKeyname().equals(keyName)) {
					keyValue = meta.getValue();
					break;
				}
			}
		}
		
		return keyValue;	
	}

    /**
     * @return the docList
     */
    public Document[] getDocList() {
        return docList;
    }

    /**
     * @param docList the docList to set
     */
    public void setDocList(Document[] docList) {
        this.docList = docList;
    }
}
