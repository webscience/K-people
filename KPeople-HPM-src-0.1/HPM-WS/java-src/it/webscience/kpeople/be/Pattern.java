package it.webscience.kpeople.be;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Business Entity Pattern
 * @author gnoni
 *
 */
public class Pattern extends Attachment {

	/**
     * Costruttore.
     */
    public Pattern() {
        super();
         patternMetadatas = new ArrayList<PatternMetadata>();
         ccUsers = new ArrayList<User>();
    }
    
	/**
	 * Identificativo ruolo Pattern requestor.
	 */
	public static final int PATTERN_ROLE_REQUESTOR = 1;
	
	/**
	 * Identificativo ruolo Pattern provider.
	 */
	public static final int PATTERN_ROLE_PROVIDER = 2;
	
	/**
	 * Identificativo ruolo Pattern ccusers.
	 */
	public static final int PATTERN_ROLE_CCUSERS = 3;
	

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
	private List<PatternMetadata> patternMetadatas;
	
	
	
	
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
	private List<User> ccUsers;
	
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
	 * Flag che indica se sono previste attività in attesa.
	 */
	private boolean waitingActivity;
	
	/**
	 * Data di chiusura dell'ultima attività in attesa.
	 */
	private Date waitingActivityDate;
	
	/** id del processo a cui è associato il pattern. */
	private String hpmProcessRefId;

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
	public final List<PatternMetadata> getPatternMetadatas() {
		return patternMetadatas;
	}

	/**
     * Metodo setter per il membro patternMetadatas.
     * @param pPatternMetadatas parametro formale per patternMetadatas.
     */
	public final void setPatternMetadatas(
			final List<PatternMetadata> pPatternMetadatas) {
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
	public final List<User> getCcUsers() {
		return ccUsers;
	}

	/**
     * Metodo setter per il membro ccUsers.
     * @param pCcUsers parametro formale per ccUsers.
     */
	public final void setCcUsers(final List<User> pCcUsers) {
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
	 * Genera un HpmPatternId a partire da HpmPatternId e ActivityTaskId.
	 * @param pHpmPatternId identificativo HPM di un pattern.
	 * @param pActivityTaskId identificativo in Activiti di un task.
	 * @return HpmActivityId generato
	 */
	public static final String generateHpmPatternId(
			final String pHpmProcessId , 
			final String pPatternTypeCode ,
			final String pActivitiProcessInstanceId) {
	
		//Lettura parametri formali 
		String hpmProcessId = pHpmProcessId;
		String patternTypeCode = pPatternTypeCode;
		String activitiProcessInstanceId = pActivitiProcessInstanceId;
		
		String hpmPattern = "";
		
		//Composizione HpmPatternId
		StringBuffer sb = new StringBuffer();
		sb.append(pHpmProcessId);
		sb.append("-");
		sb.append(pPatternTypeCode);
		sb.append(pActivitiProcessInstanceId);
		
		hpmPattern = sb.toString();
		return hpmPattern;
		
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
			for (int i = 0; i < patternMetadatas.size(); i++) {
				PatternMetadata meta = patternMetadatas.get(i);
				if (meta.getKeyname().equals(keyName)) {
					keyValue = meta.getValue();
					break;
				}
			}
		}
		
		return keyValue;
		
	}

    /**
     * Ritorna l'id del processo a cui è associato il pattern.
     * @return hpmProcessRefId
     */
    public String getHpmProcessRefId() {
        return hpmProcessRefId;
    }

    /**
     * Setta l'id del processo a cui è associato il pattern.
     * @param in hpmProcessRefId
     */
    public void setHpmProcessRefId(String in) {
        this.hpmProcessRefId = in;
    }
}
