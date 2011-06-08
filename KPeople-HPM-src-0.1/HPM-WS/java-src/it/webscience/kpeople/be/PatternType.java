package it.webscience.kpeople.be;

/**
 * Business Entity PatternType.
 * @author gnoni
 *
 */
public class PatternType extends DataTraceClass {

	/**
	 * Identificativo della tipologia della tipologia del pattern.
	 */
	private int idPatternType;
	
	/**
	 * Nome della tipologia di un pattern.
	 */
	private String name;
	
	/**
	 * Descrizione della tiplogia di un pattern.
	 */
	private String description;
	
	/**
	 * Valore booleano che specifica se un pattern è attivo.
	 */
	private boolean active;
	
	/**
	 * Valore booleano che discrimina i pattern che compaiono nella lista dei 
	 * pattern avviabili.
	 */
	private boolean showInList;
	
	/**
	 * Versione del pattern.
	 */
	private String version;
	
	
	/**
	 * Form associato al pattern per la fase di avvio.
	 */
	private String relatedForm;
	
	/**
	 * Codice associato al pattern.
	 */
	private String patternTypeCode;
	
	/**
	 * Campo che specifica l'ordinamento dei pattern.
	 */
	private int ordering;
	
	
	/**
	 * 
	 */
	private boolean waitingActivity;
	
	/**
	 * Identificativo della definizione di un pattern nel domionio del 
	 * sistema Activiti.
	 */
	private String activitiProcessDefinitionId;
	
	/**
	 * Identificativo della tipologia di pattern nel dominio del sistema 
	 * KPeople.
	 */
	private String hpmPatternTypeId;

	

	/**
     * Metodo getter per il membro idPatternType.
     * @return idPatternType
     */
	public final int getIdPatternType() {
		return idPatternType;
	}
	
	/**
     * Metodo setter per il membro idPatternType.
     * @param pIdPatternType parametro formale per idPatternType.
     */
	public final void setIdPatternType(final int pIdPatternType) {
		this.idPatternType = pIdPatternType;
	}

	/**
     * Metodo getter per il membro name.
     * @return name
     */
	public final String getName() {
		return name;
	}

	/**
     * Metodo setter per il membro name.
     * @param pName parametro formale per name.
     */
	public final void setName(final String pName) {
		this.name = pName;
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
     * Metodo getter per il membro active.
     * @return active
     */
	public final boolean isActive() {
		return active;
	}

	/**
     * Metodo setter per il membro active.
     * @param pActive parametro formale per active.
     */
	public final void setActive(final boolean pActive) {
		this.active = pActive;
	}

	/**
     * Metodo getter per il membro showInList.
     * @return showInList
     */
	public final boolean isShowInList() {
		return showInList;
	}

	/**
     * Metodo setter per il membro showInList.
     * @param pShowInList parametro formale per showInList.
     */
	public final void setShowInList(final boolean pShowInList) {
		this.showInList = pShowInList;
	}

	/**
     * Metodo getter per il membro version.
     * @return version
     */
	public final String getVersion() {
		return version;
	}

	/**
     * Metodo setter per il membro version.
     * @param pVersion parametro formale per version.
     */
	public final void setVersion(final String pVersion) {
		this.version = version;
	}

	
	/**
     * Metodo getter per il membro version.
     * @return version
     */
	public final String getRelatedForm() {
		return relatedForm;
	}

	/**
     * Metodo setter per il membro version.
     * @param pVersion parametro formale per version.
     */
	public final void setRelatedForm(final String pRelatedForm) {
		this.relatedForm = pRelatedForm;
	}
	
	
	/**
     * Metodo getter per il membro activitiProcessDefinitionId.
     * @return activitiProcessDefinitionId
     */
	public final String getActivitiProcessDefinitionId() {
		return activitiProcessDefinitionId;
	}

	/**
     * Metodo setter per il membro activitiProcessDefinitionId.
     * @param pActivitiProcessDefinitionId parametro formale per 
     * activitiProcessDefinitionId.
     */
	public final void setActivitiProcessDefinitionId(
			final String pActivitiProcessDefinitionId) {
		this.activitiProcessDefinitionId = pActivitiProcessDefinitionId;
	}

	/**
     * Metodo getter per il membro hpmPatternTypeId.
     * @return hpmPatternTypeId
     */
	public final String getHpmPatternTypeId() {
		return hpmPatternTypeId;
	}

	/**
     * Metodo setter per il membro hpmPatternTypeId.
     * @param pHpmPatternTypeId parametro formale per hpmPatternTypeId.
     */
	public final void setHpmPatternTypeId(final String pHpmPatternTypeId) {
		this.hpmPatternTypeId = pHpmPatternTypeId;
	}

	/**
	 * Metodo getter per il membro patternTypeCode.
	 * @return patternTypeCode
	 */
	public final String getPatternTypeCode() {
		return patternTypeCode;
	}

	/**
	 *  Metodo setter per il membro patternTypeCode.
	 * @param pPatternTypeCode parametro formale
	 */
	public final void setPatternTypeCode(final String pPatternTypeCode) {
		this.patternTypeCode = pPatternTypeCode;
	}

	/**
	 * 
	 * @return
	 */
	public final int getOrdering() {
		return ordering;
	}

	/**
	 * 
	 * @param ordering
	 */
	public final void setOrdering(final int ordering) {
		this.ordering = ordering;
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
	
	
	
}
