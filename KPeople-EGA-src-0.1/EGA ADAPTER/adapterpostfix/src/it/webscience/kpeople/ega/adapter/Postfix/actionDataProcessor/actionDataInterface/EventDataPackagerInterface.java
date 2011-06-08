package it.webscience.kpeople.ega.adapter
.Postfix.actionDataProcessor.actionDataInterface;

import it.webscience.kpeople.ega.adapter.Postfix
.kpeopleeventmodel.KpeopleSimpleEvent;

/**
 * @author filieri
 */
public interface EventDataPackagerInterface {

    /**
     * @param unstructured_event
     *            oggetto che modella l'evento
     * @return un oggetto di tipo String che individua il percorso dove è stato
     *         salvato il file xml relativo all'evento
     * @throws Exception
     *             eccezione generata
     */
    public String pack(KpeopleSimpleEvent unstructured_event) throws Exception;

}
