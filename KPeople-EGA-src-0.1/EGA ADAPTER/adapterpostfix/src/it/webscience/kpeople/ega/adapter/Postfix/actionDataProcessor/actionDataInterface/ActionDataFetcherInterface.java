package it.webscience.kpeople.ega.adapter
.Postfix.actionDataProcessor.actionDataInterface;

import it.webscience.kpeople.domain.model.KpeopleAction;

/**
 * @author filieri
 */
public interface ActionDataFetcherInterface {

    /**
     * @param action azione che è stata intercettata
     * @return un'istanza della classe Object che rappresenta l'azione
     *         intercettata
     * @throws Exception eccezione generata
     */
    public Object fetch(KpeopleAction action) throws Exception;

}
