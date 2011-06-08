package it.webscience.kpeople.be;

/**
 * Business Entity PatternState.
 * @author gnoni
 *
 */
public class PatternState extends DataTraceClass {

	/**
	 * Identificativo univoco per lo stato di un pattern.
	 */
	private int idPatternState;
	
	/**
	 * Identificativo tipologia pattern.
	 */
	private int idPatternType;
	/**
	 * Nome relativo allo stato di un pattern.
	 */
	private String state;
	
	/**
	 * Descrizione dello stato di un pattern.
	 */
	private String description;
	
	/**
	 * Metodo getter per il membro idPatternState.
	 * @return idPatternState
	 */
	public final int getIdPatternState() {
		return idPatternState;
	}
	
	/**
     * Metodo setter per il membro idPatternState.
     * @param pIdPatternState parametro formale per idPatternState.
     */
	public final void setIdPatternState(final int pIdPatternState) {
		this.idPatternState = pIdPatternState;
	}
	
	/**
	 * Metodo getter per il membro state.
	 * @return state
	 */
	public final String getState() {
		return state;
	}
	
	/**
     * Metodo setter per il membro state.
     * @param pState parametro formale per state.
     */
	public final void setState(final String pState) {
		this.state = pState;
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
	 * Metodo getter per il membro idPatternType.
	 * @return
	 */
	public final int getIdPatternType() {
		return idPatternType;
	}

	/**
	 * Metodo setter per il membro idPaternType.
	 * @param idPatternType
	 */
	public final void setIdPatternType(int idPatternType) {
		this.idPatternType = idPatternType;
	}
	
	
}
