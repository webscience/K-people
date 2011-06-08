package it.webscience.kpeople.service.datatypes;

/**
 * @author depascalis
 * Classe per la rappresentazione dei ProcessExtraData
 */
public class ProcessExtraData {

    /**
     * Chiave del ProcessExtraData.
     */
    private String key;

    /**
     * Valore del ProcessExtraData.
     */
    private String value;

    /**
     * Costruttore della classe.
     */
    public ProcessExtraData() {
    }

    /**
     * @param in chiave da settare al ProcessExtradata.
     */
    public final void setKey(final String in) {
        this.key = in;
    }

    /**
     * @param in Valore da settare al ProcessExtraData.
     */
    public final void setValue(final String in) {
        this.value = in;
    }
    /**
     * @return chiave del ProcessExtraData.
     */
    public final String getKey() {
        return key;
    }
    /**
     * @return valore del ProcessExtraData.
     */
    public final String getValue() {
        return value;
    }

}
