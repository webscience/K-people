package it.webscience.kpeople.be;

/**
 * Tabella ACTIVITY_TYPE.
 * @author gnoni
 *
 */
public class ActivityType extends DataTraceClass {
    /**
     * Chiave primaria.
     * Identificativo univoco per un record della tabella ACTIVITY_TYPE
     */
    private int idActivityType;

    /**
     * Campo obbligatorio.
     * Valore testuale in cui memorizzare il nome della tipologia di
     * una attività.
     */
    private String name;

   



	/**
     * Valore testuale in cui memorizzare il nome della tipologia di
     * una attività.
     */
    private String description;
    
    /**
     * Valore testuale in cui memorizzare il form da utilizzare per l'esecuzione 
     * di una attività.
     */
    private String relatedForm;
    
    
    
    /** Costruttore. */
    public ActivityType() {
        super();
    }

    /**
     *  .
     * @return
     */
    public final int getIdActivityType() {
		return idActivityType;
	}


    /**
     *  .
     * @param pIdActivityType
     */
	public final void setIdActivityType(final int pIdActivityType) {
		this.idActivityType = pIdActivityType;
	}


	/**
	 *  .
	 * @return
	 */
	public final String getName() {
		return name;
	}


	/**
	 *  .
	 * @param pName
	 */
	public final void setName(String pName) {
		this.name = pName;
	}


	/**
	 *  .
	 * @return
	 */
	public final String getDescription() {
		return description;
	}


	/**
	 *  .
	 * @param pDescription
	 */
	public final void setDescription(final String pDescription) {
		this.description = pDescription;
	}


	/**
	 * 
	 * @return
	 */
	public final String getRelatedForm() {
		return relatedForm;
	}


	/**
	 * 
	 * @param pRelatedForm
	 */
	public void setRelatedForm(final String pRelatedForm) {
		this.relatedForm = pRelatedForm;
	}
    

   
}
