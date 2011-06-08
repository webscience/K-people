package it.webscience.kpeople.ega
.adapter.sharepoint.actionDataProcessor.actionDataInterface;



import com.microsoft.schemas.sharepoint.soap.GetItemResponse;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.ega.adapter
.sharepoint.kpeopleeventmodel.KpeopleSimpleEvent;

/**
 * @author XPMUser
 */
public interface ActionDataParserInterface {

    /**
     * @param content
     *            contenuto.
     * @param action
     *            azione.
     * @return un oggetto.
     * @throws Exception
     *             eccezione.
     */
    KpeopleSimpleEvent parser(GetItemResponse content,
            KpeopleAction action) throws Exception;

}
