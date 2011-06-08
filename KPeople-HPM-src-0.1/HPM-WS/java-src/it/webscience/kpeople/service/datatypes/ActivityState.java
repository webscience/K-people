package it.webscience.kpeople.service.datatypes;



/**
 * Business Entity ActivityState.
 * @author gnoni
 *
 */
public class ActivityState extends DataTraceClass {

	/**
	 *  Identificativo univoco per lo stato di una attività.
	 */
	private int idActivityState;
	
	/**
	 *  Nome relativo allo stato di una attività.
	 */
	private String name;
	
	/**
	 *  Descrizione dello stato di una attività.
	 */
	private String description;
	
	
	/**
	 * Metodo getter per il membro value.
	 * @return idActivityState
	 */
	public final int getIdActivityState() {
		return idActivityState;
	}
	
	
	/**
     * Metodo setter per il membro pIdActivityState.
     * @param pIdActivityState parametro formale per pIdActivityState.
     */
	public final void setIdActivityState(final int pIdActivityState) {
		this.idActivityState = pIdActivityState;
	}
	
	
	/**
	 * Metodo getter per il membro value.
	 * @return idActivityState
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
	 * Metodo getter per il membro value.
	 * @return idActivityState
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
	
}
