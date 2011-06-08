package it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessor.actionDataProcessorImpl;

import com.microsoft.schemas.sharepoint.soap.GetItemResponse;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLogger;
import it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessor.actionDataInterface.ActionDataFetcherInterface;
import it.webscience.kpeople.ega.adapter.sharepoint.copyservice.SharepointWSClient;

/**
 * @author XPMUser
 */
public class ActionDataFetcherImpl implements ActionDataFetcherInterface {

    /**
     * @return OMElement che rappresenta il documento sharepoint.
     * @throws Exception
     *             generica.
     * @param action
     *            rappresenta l'azione che è stata intercettata.
     */
    public final GetItemResponse fetch(final KpeopleAction action)
            throws Exception {

        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".fetch()"
                                + KpeopleLabel.getLogStart());

        GetItemResponse document = null;

        String actionReference = action.getActionReference();
       

        document = SharepointWSClient.call(actionReference);

        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".fetch()"
                                + KpeopleLabel.getLogStop());
        return document;
    }

}
