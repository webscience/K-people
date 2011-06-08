package it.webscience.kpeople.ega
.adapter.Postfix.actionDataProcessor;

import java.io.InputStream;
import java.util.concurrent.Callable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.domain.model.KpeopleEvent;
import it.webscience.kpeople.domain.service.interf.ActionStateManager;
import it.webscience.kpeople.ega.adapter.Postfix.KpeopleErrorServiceInstance;
import it.webscience.kpeople.ega.adapter.Postfix.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.Postfix.KpeopleLogger;
import it.webscience.kpeople.ega.adapter.Postfix
.actionDataProcessor.actionDataProcessorImpl.ActionDataFetcherImpl;
import it.webscience.kpeople.ega.adapter.Postfix
.actionDataProcessor.actionDataProcessorImpl.ActionDataParserImpl;
import it.webscience.kpeople.ega.adapter.Postfix
.actionDataProcessor.actionDataProcessorImpl.EventDataPackagerImpl;
import it.webscience.kpeople.ega.adapter.Postfix
.kpeopleeventmodel.KpeopleSimpleEvent;

/**
 * @author filieri
 */
public class ActionDataProcessor implements Callable<KpeopleEvent> {

    /**
     * Azione che deve essere elaborata.
     */
    private KpeopleAction actionToProcess;

    /**
     * istanza della classe fetcher.
     */
    private ActionDataFetcherImpl fetcherInstance = new ActionDataFetcherImpl();

    /**
     * istanza della classe parse.
     */
    private ActionDataParserImpl parserInstance = new ActionDataParserImpl();

    /**
     * istanza della classe packeger.
     */
    private EventDataPackagerImpl packagerInstance = new EventDataPackagerImpl();

   // private KpeopleEvent event = null;
    public BundleContext context;
    public ServiceReference reference;
    public ActionStateManager service;

    /**
     * @param actiontoProcess
     *            azione che deve essere elaborata.
     */
    public ActionDataProcessor(final KpeopleAction actiontoProcess) {
        this.actionToProcess = actiontoProcess;
        this.context = FrameworkUtil.getBundle(this.getClass())
                .getBundleContext();
        this.reference = context.getServiceReference(ActionStateManager.class
                .getName());
        this.service = (ActionStateManager) context.getService(reference);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.concurrent.Callable#call()
     */
    public KpeopleEvent call() throws Exception {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".call()"
                                + KpeopleLabel.getLogStart());

        //Long actionId = actionToProcess.getIdAction();

        String content = fetchAction(actionToProcess);

        KpeopleSimpleEvent unstructuredEvent = null;
        KpeopleEvent structuredEvent = null;

        if (content != null) {
            unstructuredEvent = parseAction(content, actionToProcess);
        }
        if (unstructuredEvent != null) {
            structuredEvent = packEvent(unstructuredEvent, actionToProcess);
        }

       
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(KpeopleLabel.getSystemId(),
                        this.getClass() + ".call()"
                        + KpeopleLabel.getLogStop());

        return structuredEvent;

    }

    /**
     * @param action azione che deve essere elaborata
     * @return un oggetto di tipo InputStream
     * che rappresenta l'azione intercettata
     */
    private String fetchAction(final KpeopleAction action) {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".fetchAction()"
                                + KpeopleLabel.getLogStart());

        String resource = null;
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

    /**
     * @param content rappresenta un oggetto
     * di tipo inputstream restituito dal fetcher
     * @param action azione che deve essere elaborata
     * @return un oggetto di tipo simpleEvent
     * rappresentativo dell'evento intercettato
     */
    private KpeopleSimpleEvent parseAction(final String content,
            final KpeopleAction action) {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".parseAction()"
                                + KpeopleLabel.getLogStart());

        KpeopleSimpleEvent metadata = null;
        try {
            metadata = parserInstance.parser(content, action);

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

        return metadata;

    }

    /**
     * @param unstructuredevent oggetto di tipo simpleEvent
     * che rappresenta l'oggetto restituito dal parser
     * @param action azione che è stata intercettata
     * @return un oggetto di tipo KpeopleEvent contenente le
     * informazioni strutturate relative all'evento intercettato
     */
    private KpeopleEvent packEvent(final KpeopleSimpleEvent unstructuredevent,
            final KpeopleAction action) {
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
            xmlPath = packagerInstance.pack(unstructuredevent);

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
