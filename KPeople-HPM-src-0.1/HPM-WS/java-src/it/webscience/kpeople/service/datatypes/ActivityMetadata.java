package it.webscience.kpeople.service.datatypes;



/**
 * Business Entity ActivityMetadata.
 * @author gnoni
 *
 */
public class ActivityMetadata extends DataTraceClass {

	/**
	 *  Identificativo univoco di un metadato relativo ad una attività.
	 */
	private int idActivityMetadata;
	
	/**
	 *  Chiave del metadato.
	 *  
	 */
	private String keyname;
	
	/**
	 *  Valore del metadato.
	 *  
	 */
	private String value;
	
	
	/**
	 * Valore che specifica se tale metadato va propagato verso il sistema 
	 * Activiti nell’esecuzione di un pattern.
	 */
	private boolean activitiProcessMetadata;
	
	/**
	 * Metodo getter per il membro idActivityMetadata.
	 * @return idActivityMetadata
	 */
	public final int getIdActivityMetadata() {
		return idActivityMetadata;
	}
	
	/**
     * Metodo setter per il membro pIdActivityMetadata.
     * @param pIdActivityMetadata parametro formale per pIdActivityMetadata.
     */
	public final void setIdActivityMetadata(final int pIdActivityMetadata) {
		this.idActivityMetadata = pIdActivityMetadata;
	}
	
	/**
	 * Metodo getter per il membro keyname.
	 * @return keyname
	 */
	public final String getKeyname() {
		return keyname;
	}
	
	/**
     * Metodo setter per il membro keyname.
     * @param pKeyname parametro formale per keyname.
     */
	public final void setKeyname(final String pKeyname) {
		this.keyname = pKeyname;
	}
	
	/**
	 * Metodo getter per il membro value.
	 * @return value
	 */
	public final String getValue() {
		return value;
	}
	
	/**
     * Metodo setter per il membro value.
     * @param pValue parametro formale per value.
     */
	public final void setValue(final String pValue) {
		this.value = pValue;
	}

	/**
	 * 
	 * @return
	 */
	public final boolean isActivitiProcessMetadata() {
		return activitiProcessMetadata;
	}

	/**
	 * 
	 * @param activitiProcessMetadata
	 */
	public final void setActivitiProcessMetadata(
			final boolean activitiProcessMetadata) {
		this.activitiProcessMetadata = activitiProcessMetadata;
	}
	
	
}
