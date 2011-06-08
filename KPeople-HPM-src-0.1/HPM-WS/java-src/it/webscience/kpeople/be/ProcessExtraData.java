package it.webscience.kpeople.be;

/**
 * @author depascalis
 * Classe relativa al ProcessExtraData be.
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

    /**
     * @param in chiave da settare per il ProcessExtraData.
     */
    public final void setKey(final String in) {
        this.key = in;
    }

    /**
     * @param in valore da setatre per il ProcessExtraData.
     */
    public final void setValue(final String in) {
        this.value = in;
    }

}
