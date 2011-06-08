package it.webscience.kpeople.dal.event.util;

import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.EventMetadata;

/**
 * Classe util per la business entity Event.
 */
public final class EventDAOUtil {

    /**
     * Default constructor.
     */
    private EventDAOUtil() {
    }

    /**
     * Legge un metadato dell'evento.
     * @param event business entity di tipo Event
     * @param key nome del metadato richiesto
     * @return valore del metadato se presente
     */
    public static String getMetadataValue(final Event event, final String key) {
        for (EventMetadata em : event.getEventMetadata()) {
            if (em.getKeyname().equalsIgnoreCase(key)) {
                return em.getValue();
            }
        }

        return "";
    }
}
