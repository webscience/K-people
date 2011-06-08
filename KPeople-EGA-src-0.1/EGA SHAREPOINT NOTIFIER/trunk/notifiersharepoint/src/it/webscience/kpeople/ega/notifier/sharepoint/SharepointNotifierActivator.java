package it.webscience.kpeople.ega.notifier.sharepoint;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import it.webscience.kpeople.ega.notifier.sharepoint.util.configuration.SharepointConfigurationManager;
import it.webscience.kpeople.ega.notifier.sharepoint.util.configuration.SharepointNotifierParameters;
import it.webscience.kpeople.ega.notifier.sharepoint.webserviceclient.SharepointWSClient;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

/**
 * Classe per l'attivazione del bundle osgi relativo al modulo Notifier
 * dell'adapter per Sharepoint.
 * 
 * @author filieri
 */
public class SharepointNotifierActivator implements BundleActivator {

    /**
     * Istanza della classe Thread.
     */
    private static volatile Thread notifierThread;

    /**
     * Istanza della classe KpeopleLog appartenente modulo logging.
     */
    private KpeopleLog logService;

    /**
     * Istanza della classe SharepointWSClient.
     */
    private static SharepointWSClient client;

    /**
     * Metodo richiamato all'avvio del bundle per svolgere eventuali attività di
     * inizializzazione del bundle.
     * 
     * @param context
     *            - contesto di esecuzione del bundle da avviare
     * @throws Exception
     *             - il verificarsi di un'eccezione all'avvio comporta la
     *             cancellazione dei servizi registrati ed i rilascio di tutti i
     *             servizi utilizzati dal bundle.
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     *      )
     */
    public void start(final BundleContext context) throws Exception {
        // recupera il servizio di logging presente all'interno del framework
        
        ServiceReference reference = context
                .getServiceReference(KpeopleLog.class.getName());
        logService = (KpeopleLog) context.getService(reference);

        logService.logInfo(SharepointNotifierParameters.getSystemId(), " Start Bundle SharepointNotifier");

        client = new SharepointWSClient();
        notifierThread = new Thread(client);
        notifierThread.start();
    }

    /**
     * Metodo richiamato per svolgere le attività necessarie a stoppare
     * l'esecuzione del bundle.
     * 
     * @param context
     *            - contesto di esecuzione del bundle da avviare
     * @throws Exception
     *             - il verificarsi di un'eccezione all'avvio comporta la
     *             cancellazione dei servizi registrati ed i rilascio di tutti i
     *             servizi utilizzati dal bundle.
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     *      )
     */
    public void stop(final BundleContext context) throws Exception {
        client.kill();
        logService.logInfo(SharepointNotifierParameters.getSystemId(), " Stop Bundle SharepointNotifier");
    }

}
