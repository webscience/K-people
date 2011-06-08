package it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessor;

import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.Callable;

import org.apache.axiom.om.OMElement;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.microsoft.schemas.sharepoint.soap.GetItemResponse;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.domain.model.KpeopleEvent;
import it.webscience.kpeople.domain.service.interf.ActionStateManager;
import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleErrorServiceInstance;
import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLogger;
import it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessor.actionDataProcessorImpl.ActionDataFetcherImpl;
import it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessor.actionDataProcessorImpl.ActionDataParserImpl;
import it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessor.actionDataProcessorImpl.EventDataPackagerImpl;
import it.webscience.kpeople.ega.adapter.sharepoint.kpeopleeventmodel.KpeopleSimpleEvent;

/**
 * @author XPMUser
 */
public class ActionDataProcessor implements Callable<KpeopleEvent> {

    /**
     * Rappresenta l'azione da processare.
     */
    private KpeopleAction actionToProcess;
    /**
     * Istanza del modulo fetcher.
     */
    private ActionDataFetcherImpl fetcherInstance = new ActionDataFetcherImpl();
    /**
     * Istanza del modulo parser.
     */
    private ActionDataParserImpl parserInstance = new ActionDataParserImpl();
    /**
     * Istanza del modulo packager.
     */
    private EventDataPackagerImpl packagerInstance = new EventDataPackagerImpl();
    /**
     * Istanza di tipo KpeopleEvent.
     */
    private KpeopleEvent event = null;
    /**
     * Oggetto per il context di riferimento del bundle.
     */
    private BundleContext context;
    /**
     * Riferimento al servizio esposto dal componente domain.
     */
    private ServiceReference reference;
    /**
     * Servizio esposto dal componente domain.
     */
    private ActionStateManager service;

    /**
     * @param actiontoProcess
     *            insieme di azioni da elaborare.
     */
    public ActionDataProcessor(final KpeopleAction actiontoProcess) {
        this.actionToProcess = actiontoProcess;
        this.context = FrameworkUtil.getBundle(this.getClass())
                .getBundleContext();
        this.reference = context.getServiceReference(ActionStateManager.class
                .getName());
        this.service = (ActionStateManager) context.getService(reference);
    }

    /**
     * @see java.util.concurrent.Callable#call().
     * @return KpeopleEvent oggetto di tipo evento.
     */
    public KpeopleEvent call() throws Exception {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".call()"
                                + KpeopleLabel.getLogStart());

        Long actionId = actionToProcess.getIdAction();
        
        KpeopleLogger
        .getInstance()
        .getService()
        .logInfo(
                KpeopleLabel.getSystemId(),
                this.getClass() + ".call()"
                        + "start process action ..." + actionId);

        String operation = actionToProcess.getActionType().split("/")[1];
        GetItemResponse content = null;
        KpeopleSimpleEvent unstructuredEvent = null;
        KpeopleEvent structuredEvent = null;

        if (operation.equalsIgnoreCase("deleted")) {
            
            unstructuredEvent = parserInstance.parserDeleted(actionToProcess);
            
            if (unstructuredEvent != null) {
                structuredEvent = packEvent(unstructuredEvent, actionToProcess);
            } 
            
        } else {
            content = fetchAction(actionToProcess);

            unstructuredEvent = parseAction(content, actionToProcess);

            if (unstructuredEvent != null) {
                structuredEvent = packEvent(unstructuredEvent, actionToProcess);
            } 

        }
        
       
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(KpeopleLabel.getSystemId(),
                        this.getClass() + ".call()" + KpeopleLabel.getLogStop());

        return structuredEvent;

    }

    /**
     * @param action
     *            azione da processare.
     * @return un OMElement rappresentativo dell'evento generato.
     */
    private GetItemResponse fetchAction(final KpeopleAction action) {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".fetchAction()"
                                + KpeopleLabel.getLogStart());

        GetItemResponse resource = null;
        try {
            resource = fetcherInstance.fetch(action);
            if (resource != null) {
                service.updateActionState(action, KpeopleLabel.stateFetched());
            }
        } catch (Exception e) {
            KpeopleErrorServiceInstance.getInstance().getService()
                    .sendErrorNotification(e);
        }
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".fetchAction()"
                                + KpeopleLabel.getLogStop());

        return resource;
    }

    private KpeopleSimpleEvent parseAction(final GetItemResponse content,
            final KpeopleAction action) {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".parseAction()"
                                + KpeopleLabel.getLogStart());

        KpeopleSimpleEvent simpleEvent = null;
        try {
            simpleEvent = parserInstance.parser(content, action);
            
        } catch (Exception e) {
            service.updateActionState(action, KpeopleLabel.stateNotDelivered());
            KpeopleErrorServiceInstance.getInstance().getService()
                    .sendErrorNotification(e);

            e.printStackTrace();
        }
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".parseAction()"
                                + KpeopleLabel.getLogStop());

        return simpleEvent;

    }

    private KpeopleEvent packEvent(KpeopleSimpleEvent unstructuredEvent,
            KpeopleAction action) {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".packEvent()"
                                + KpeopleLabel.getLogStart());

        String xmlPath = null;
        KpeopleEvent newEvent = null;
        try {
            xmlPath = packagerInstance.pack(unstructuredEvent);

        } catch (Exception e) {

            e.printStackTrace();
        }
        if (xmlPath != null) {

            newEvent = service.addNewEvent(xmlPath, "0", "0", action);
            if (newEvent != null) {
                service.updateActionState(action, KpeopleLabel.statePacked());
            }
        }
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".packEvent()"
                                + KpeopleLabel.getLogStop());

        return newEvent;

    }

}
