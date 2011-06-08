package it.webscience.kpeople.service.datatypes;



/**
 * Business Entity PatternMetadata.
 * @author gnoni
 *
 */
public class PatternMetadata extends DataTraceClass{

	/**
	 * Identificativo univoco relativo ad un metadato associato ad un pattern.
	 */
	private int idPatternMetadata;
	
	/**
	 * Identificativo univoco relativo ad al pattern associato.
	 */
	private int idPattern;
	
	/**
	 * Chiave relativa al metadato.
	 */
	private String keyname;
	
	/**
	 * Valore che assume il metadato.
	 */
	private String value;
	
	/**
	 * Valore che specifica se tale metadato va propagato verso il sistema 
	 * Activiti nell’esecuzione di un pattern.
	 */
	private boolean activitiProcessMetadata;

	
	
	/**
	 * Metodo getter per il membro idPatternMetadata.
	 * @return idPatternMetadata
	 */
	public final int getIdPatternMetadata() {
		return idPatternMetadata;
	}

	/**
     * Metodo setter per il membro idPatternMetadata.
     * @param pIdPatternMetadata parametro formale per idPatternMetadata.
     */
	public final void setIdPatternMetadata(final int pIdPatternMetadata) {
		this.idPatternMetadata = pIdPatternMetadata;
	}

	/**
	 * Metodo getter per il membro idPatternMetadata.
	 * @return idPatternMetadata
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
	 * Metodo getter per il membro activitiProcessMetadata.
	 * @return activitiProcessMetadata
	 */
	public final boolean isActivitiProcessMetadata() {
		return activitiProcessMetadata;
	}

	/**
     * Metodo setter per il membro activitiProcessMetadata.
     * @param pActivitiProcessMetadata parametro formale 
     * per activitiProcessMetadata.
     */
	public final void setActivitiProcessMetadata(
			final boolean pActivitiProcessMetadata) {
		this.activitiProcessMetadata = pActivitiProcessMetadata;
	}
	
	
	/**
	 * Metodo getter per il membro idPattern.
	 * @return idPattern identificativo del pattern.
	 */
	public final int getIdPattern() {
		return idPattern;
	}

	/**
     * Metodo setter per il membro idPattern.
     * @param pIdPattern parametro formale 
     */
	public final void setIdPattern(final int pIdPattern) {
		this.idPattern = pIdPattern;
	}
	
}
