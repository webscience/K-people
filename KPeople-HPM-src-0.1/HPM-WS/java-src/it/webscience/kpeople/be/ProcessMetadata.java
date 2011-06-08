package it.webscience.kpeople.be;

import java.util.ArrayList;
import java.util.List;

/**
 * @author depascalis
 * Classe relativa al ProcessMetadada.
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
    public List<ProcessValue> valueList;

    /**
     * Costruttore della classe.
     */
    public ProcessMetadata() {
        valueList = new ArrayList<ProcessValue>();
    }

    /**
     * @param in la chiave da settare
     */
    public final void setKey(final String in) {
        this.key = in;
    }

    /**
     * @param in descrizione da settare.
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
     * @return la descrizione del ProcessMetadata.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param in lista dei valori relativi al processo.
     */
    public final void setValueList(final List<ProcessValue> in) {
        this.valueList = in;
    }

    /**
     * @return lista dei valori relativi al processo.
     */
    public final List<ProcessValue> getValueList() {
        return valueList;
    }

}
