package it.webscience.kpeople.ega.adapter
.Postfix.actionDataProcessor.actionDataInterface;

import java.io.InputStream;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.ega.adapter
.Postfix.kpeopleeventmodel.KpeopleSimpleEvent;

/**
 * @author filieri
 */
public interface ActionDataParserInterface {

    /**
     * @param content
     *            è un inputstream che rappresenta l'oggetto email
     * @param action
     *            azione che è stata intercettata
     * @return un oggetto di tipo simpleevent che modella l'evento che è stato
     *         generato
     * @throws Exception eccezione generata
     */
    public KpeopleSimpleEvent parser(String content, KpeopleAction action)
            throws Exception;

}
