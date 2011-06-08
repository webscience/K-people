package it.webscience.kpeople.bll;

import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.be.EventFilter;

import java.util.List;

public interface IEventManager {

    /**
     * Restituisce gli l'elenco completo degli eventi associati.
     * ad un processo.
     * @param eventFilter oggetto contenente i parametri di ricerca.
     * @param user user che esegue la chiamata
     * @return elenco di ProcessType
     * @throws KPeopleBLLException eccezione durante l'elaborazione
     */
    List<Event> getEvents(final EventFilter eventFilter, final User user)
            throws KPeopleBLLException;

}
