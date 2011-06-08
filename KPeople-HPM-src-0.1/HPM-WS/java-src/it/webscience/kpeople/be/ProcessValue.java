package it.webscience.kpeople.be;

import java.util.ArrayList;
import java.util.List;

/**
 * @author depascalis
 * Classe rappresentante il ProcessValue.
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
     * id indicante lo stato del processo
     */
    private int zidState;

    /**
     * Lista dei ProcessExtraData.
     */
    private List<ProcessExtraData> listProcessExtraData =
        new ArrayList<ProcessExtraData>();;

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
     * @param in lista dei ProcessExtraData da settare ad un value.
     */
    public final void setListProcessExtraData(final List<ProcessExtraData> in) {
        this.listProcessExtraData = in;
    }

    /**
     * @return lista dei ProcessExtraData relativi ad un Value.
     */
    public final List<ProcessExtraData> getListProcessExtraData() {
        return listProcessExtraData;
    }

    /**
     * @return the idState.
     */
    public final int getZidState() {
        return zidState;
    }

    /**
     * @param in the idState to set.
     */
    public final void setZidState(final int in) {
        this.zidState = in;
    }

}
