package it.webscience.kpeople.service.datatypes;

/**
 * Business Entity PatternFilter.
 * @author gnoni
 *
 */
public class PatternFilter {

	/**
	 *  Identificativo del processo di cui si vogliono ottenere i pattern.
	 */
	private String hpmProcessId;
	
	/**
	 *  Identificativo della tipologia di pattern dei pattern che i 
	 *  desidera estrarre.
	 */
	private String patternTypeId;
	
	/**
	 *  Può assumere quattro valori: active - closed – cancelled - all. 
	 *  Consente di filtrarre i pattern in base al loro stato nel sistema..
	 */
	private String patternStateId;
	
	/**
	 *  Identificativo relativo all’owner dei processi dei quali si desidera 
	 *  estrarre i pattern..
	 */
	private String hpmOwnerUserId;
	
	/**
	 *  Identificativo dello user loggato che effettua la richiesta di ricerca.
	 */
	private String hpmUserId;
	
	/**
	 *  Testo per la ricerca libera tra il titolo e la descrizione dei pattern.
	 */
	private String freeText;
	
	/**
	 *  Classe per definire il criterio di ordinamento.
	 */
	private SortCriteria sort;

	/**
     * Metodo getter per il membro hpmProcessId.
     * @return hpmProcessId
     */
	public final String getHpmProcessId() {
		return hpmProcessId;
	}

	/**
     * Metodo setter per il membro hpmProcessId.
     * @param pHpmProcessId parametro formale per hpmProcessId.
     */
	public final void setHpmProcessId(final String pHpmProcessId) {
		this.hpmProcessId = pHpmProcessId;
	}

	/**
     * Metodo getter per il membro patternTypeId.
     * @return patternTypeId
     */
	public final String getPatternTypeId() {
		return patternTypeId;
	}

	/**
     * Metodo setter per il membro patternTypeId.
     * @param pPatternTypeId parametro formale per patternTypeId.
     */
	public final void setPatternTypeId(final String pPatternTypeId) {
		this.patternTypeId = pPatternTypeId;
	}

	/**
     * Metodo getter per il membro patternStateId.
     * @return patternStateId
     */
	public final String getPatternStateId() {
		return patternStateId;
	}

	/**
     * Metodo setter per il membro patternStateId.
     * @param pPatternStateId parametro formale per patternStateId.
     */
	public final void setPatternStateId(final String pPatternStateId) {
		this.patternStateId = pPatternStateId;
	}

	/**
     * Metodo getter per il membro hpmOwnerUserId.
     * @return hpmOwnerUserId
     */
	public final String getHpmOwnerUserId() {
		return hpmOwnerUserId;
	}

	/**
     * Metodo setter per il membro hpmOwnerUserId.
     * @param pHpmOwnerUserId parametro formale per hpmOwnerUserId.
     */
	public final void setHpmOwnerUserId(final String pHpmOwnerUserId) {
		this.hpmOwnerUserId = pHpmOwnerUserId;
	}

	/**
     * Metodo getter per il membro hpmUserId.
     * @return hpmUserId
     */
	public final String getHpmUserId() {
		return hpmUserId;
	}

	/**
     * Metodo setter per il membro hpmUserId.
     * @param pHpmUserId parametro formale per hpmUserId.
     */
	public final void setHpmUserId(final String pHpmUserId) {
		this.hpmUserId = pHpmUserId;
	}

	/**
     * Metodo getter per il membro freeText.
     * @return freeText
     */
	public final String getFreeText() {
		return freeText;
	}

	/**
     * Metodo setter per il membro freeText.
     * @param pFreeText parametro formale per freeText.
     */
	public final void setFreeText(final String pFreeText) {
		this.freeText = pFreeText;
	}

	/**
     * Metodo getter per il membro sort.
     * @return sort
     */
	public final SortCriteria getSort() {
		return sort;
	}

	/**
     * Metodo setter per il membro sort.
     * @param pSort parametro formale per sort.
     */
	public final void setSort(final SortCriteria pSort) {
		this.sort = pSort;
	}
	
	
}
