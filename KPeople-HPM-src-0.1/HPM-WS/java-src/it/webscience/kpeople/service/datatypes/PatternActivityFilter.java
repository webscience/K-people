package it.webscience.kpeople.service.datatypes;

/**
 * Business Entity PatternActivityFilter.
 * @author gnoni
 *
 */
public class PatternActivityFilter {
	
	/**
	 *  Identificativo dei processi di cui si richiedono le attività.
	 */
	private String hpmProcessId;
	
	/**
	 *  Identificativo del pattern di cui si richiedono le attività.
	 */
	private String hpmPatternId;
	
	/**
	 *  Identificativo dello user loggato, utilizzato per estrarre 
	 *  “Le mie attività”.
	 */
	private String hpmUserId;
	
	/**
	 *  Campo boolean che serve per specificare le attività da fare.
	 */
	private String todo;
	
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
     * Metodo getter per il membro hpmProcessId.
     * @return hpmProcessId
     */
	public final String getHpmPatternId() {
		return hpmPatternId;
	}

	/**
     * Metodo setter per il membro hpmPatternId.
     * @param pHpmPatternId parametro formale per hpmPatternId.
     */
	public final void setHpmPatternId(final String pHpmPatternId) {
		this.hpmPatternId = pHpmPatternId;
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
     * Metodo getter per il membro todo.
     * @return todo
     */
	public final String getTodo() {
		return todo;
	}

	/**
     * Metodo setter per il membro todo.
     * @param pTodo parametro formale per todo.
     */
	public final void setTodo(final String pTodo) {
		this.todo = pTodo;
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
