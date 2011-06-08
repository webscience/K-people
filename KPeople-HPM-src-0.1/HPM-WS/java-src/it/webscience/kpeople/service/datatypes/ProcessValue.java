package it.webscience.kpeople.service.datatypes;

/**
 * @author depascalis Classe rappresentante il ProcessValue.
 */
/**
 * @author depascalis
 *
 */
public class ProcessValue {

    /**
     * Valore del ProcessValue.
     */
    private String value;

    /**
     * Descrizione del ProcessValue.
     */
    private String description;

    /**
     * id indicante lo stato del processo.
     */
    private int zidState;

    /**
     * Elenco dei ProcessExtraData.
     */
    private ProcessExtraData[] listProcessExtraData;

    /**
     * Costruttore della classe.
     */
    public ProcessValue() {

    }

    /**
     * @param in value da settare.
     */
    public final void setValue(final String in) {
        this.value = in;
    }

    /**
     * @param in descrizione da settare.
     */
    public final void setDescription(final String in) {
        this.description = in;
    }

    /**
     * @return il value del ProcessValue.
     */
    public final String getValue() {
        return value;
    }

    /**
     * @return la descrizione del ProcessValue.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param in elenco dei ProcessExtraData da settare.
     */
    public final void setListProcessExtraData(final ProcessExtraData[] in) {
        this.listProcessExtraData = in;
    }

    /**
     * @return elenco dei ProcessExtraData
     */
    public final ProcessExtraData[] getListProcessExtraData() {
        return listProcessExtraData;
    }

    /**
     * @return the idState
     */
    public final int getZidState() {
        return zidState;
    }

    /**
     * @param in the idState to set
     */
    public final void setZidState(final int in) {
        this.zidState = in;
    }
}
