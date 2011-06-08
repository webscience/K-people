package it.webscience.kpeople.service.datatypes;

/**
 * @author depascalis Classe relativa al ProcessMetadada
 */
public class ProcessMetadata {

    /**
     * Chiave del ProcessMetadata.
     */
    private String key;

    /**
     * Descrizione del ProcessMetadata.
     */
    private String description;

    /**
     * Insieme dei value associati al ProcessMetadata.
     */
    private ProcessValue[] valueList;

    /**
     * Costruttore della classe.
     */
    public ProcessMetadata() {
        super();
    }

    /**
     * @param in la chiave da settare.
     */
    public final void setKey(final String in) {
        this.key = in;
    }

    /**
     * @param in a descrizione da settare.
     */
    public final void setDescription(final String in) {
        this.description = in;
    }

    /**
     * @return la key
     */
    public final String getKey() {
        return key;
    }

    /**
     * @return la descrizione del ProcessMetadata
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param in ProcessValue da settare.
     */
    public final void setValueList(final ProcessValue[] in) {
        this.valueList = in;
    }

    /**
     * @return valueList.
     */
    public final ProcessValue[] getValueList() {
        return valueList;
    }

}
