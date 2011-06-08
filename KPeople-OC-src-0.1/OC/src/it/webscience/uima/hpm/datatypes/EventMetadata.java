package it.webscience.uima.hpm.datatypes;


/**
 * Tabella EVENT_METADATA.
 * @author dellanna
 *
 */
public class EventMetadata {
    /**
     * Chiave primaria.
     * Identificativo univoco per un record della tabella EVENT_METADATA
     */
    private int idEventMetadata;

    /**
     * Chiave esterna che punta ad un record della tabella EVENT.
     * Mediante questo campo ogni metadata può essere associato
     * obbligatoriamente ad un solo evento.
     */
    private Event event;

    /**
     * Valore testuale relativo al nome del metadato.
     */
    private String keyname;

    /**
     * Valore testuale relativo al valore che assume il metadato.
     */
    private String value;


    /**
     * @return the idEventMetadata
     */
    public final int getIdEventMetadata() {
        return idEventMetadata;
    }

    /**
     * @param in the idEventMetadata to set
     */
    public final void setIdEventMetadata(final int in) {
        this.idEventMetadata = in;
    }

    /**
     * @return the event
     */
    public final Event getEvent() {
        return event;
    }

    /**
     * @param in the event to set
     */
    public final void setEvent(final Event in) {
        this.event = in;
    }

    /**
     * @return the keyname
     */
    public final String getKeyname() {
        return keyname;
    }

    /**
     * @param in the keyname to set
     */
    public final void setKeyname(final String in) {
        this.keyname = in;
    }

    /**
     * @return the value
     */
    public final String getValue() {
        return value;
    }

    /**
     * @param in the value to set
     */
    public final void setValue(final String in) {
        this.value = in;
    }
}
