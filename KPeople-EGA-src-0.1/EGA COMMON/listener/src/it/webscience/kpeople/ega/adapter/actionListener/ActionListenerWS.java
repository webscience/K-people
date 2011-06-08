package it.webscience.kpeople.ega.adapter.actionListener;

import it.webscience.kpeople.domain.service.interf.ActionStateManager;
import it.webscience.kpeople.ega.adapter.KpeopleLabel;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.apache.axis2.context.MessageContext;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author filieri Classe per la definizione del web service da utilizzare per
 *         l'invio al componente EGA dell'azione intercettata. Espone una unica
 *         operation, getNewAction, che prevede in ingresso tre parametri
 *         testuali.
 */
public class ActionListenerWS {


    /**
     * Istanza della classe KpeopleLog appartenente modulo logging.
     */
    private KpeopleLog logService;

    /**
     * @param systemId
     *            - identifica univocamente il sistema verticale che ha generato
     *            l’azione.
     * @param actionType
     *            - identifica la tipologia di azione (COMMUNICATION, DOCUMENT e
     *            PROCESS).
     * @param actionReference
     *            - identifica univocamente l’azione.
     * @return un oggetto di tipo String indicante l'esito della chiamata al
     *         webservice.
     */
    public String getNewAction(final String systemId, final String actionType,
            final String actionReference) {

        // recupera il servizio di logging presente all'interno del framework
        BundleContext context = FrameworkUtil.getBundle(this.getClass())
                .getBundleContext();
        ServiceReference reference = context
                .getServiceReference(KpeopleLog.class.getName());
        logService = (KpeopleLog) context.getService(reference);

        logService.logInfo(KpeopleLabel.getModuleName(), this.getClass()
                + KpeopleLabel.getLogStart());

        // ascolta i messaggi di invocazione del servizio all'interno del
        // framework.
        MessageContext.getCurrentMessageContext().getConfigurationContext()
                .setProperty(KpeopleLabel.getConfigurationContextPropertyKey(),
                        KpeopleLabel.getConfigurationContextPropertyValue());

        logService.logInfo(KpeopleLabel.getModuleName(), this.getClass()
                + " : Ricevuto " + systemId + " " + actionType + " "
                + actionReference);

        if (systemId != null && actionType != null && actionReference != null) {
            insertNewAction(systemId, actionType, actionReference);
        }

        logService.logInfo(KpeopleLabel.getModuleName(), this.getClass()
                + KpeopleLabel.getLogStop());

        return KpeopleLabel.getReturnMessage();
    }

    /**
     * @param systemId
     *            - identifica univocamente il sistema verticale che ha generato
     *            l’azione.
     * @param actionType
     *            - identifica la tipologia di azione (COMMUNICATION, DOCUMENT e
     *            PROCESS).
     * @param actionReference
     *            - identifica univocamente l’azione.
     */
    public void insertNewAction(final String systemId, final String actionType,
            final String actionReference) {

        // recupera il servizio esposto dal bundle domain per inserire
        // all'interno del db l'azione che è stata intercettata.
        BundleContext context = FrameworkUtil.getBundle(this.getClass())
                .getBundleContext();
        ServiceReference reference = context
                .getServiceReference(ActionStateManager.class.getName());
        ActionStateManager service = (ActionStateManager) context
                .getService(reference);

        String result = service.addNewAction(systemId, actionType,
                actionReference);

        logService.logInfo(KpeopleLabel.getModuleName(), this.getClass()
                + " : inserita una nuova azione " + result);
    }
}
