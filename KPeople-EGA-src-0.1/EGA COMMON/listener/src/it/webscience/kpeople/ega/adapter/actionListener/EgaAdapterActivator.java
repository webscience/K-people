package it.webscience.kpeople.ega.adapter.actionListener;

import it.webscience.kpeople.ega.adapter.KpeopleLabel;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.osgi.framework.ServiceReference;

/**
 * Classe Activator per il bundle Listener.
 * @author filieri
 */
public class EgaAdapterActivator implements BundleActivator {

    /**
     * Istanza della classe KpeopleLog del bundle logging.
     */
    private static KpeopleLog service;

    /**
     * Metodo richiamato all'avvio del bundle per svolgere eventuali attività di
     * inizializzazione del bundle.
     * @param context
     *            - contesto di esecuzione del bundle da avviare
     * @throws Exception
     *             - il verificarsi di un'eccezione all'avvio comporta la
     *             cancellazione dei servizi registrati ed i rilascio di tutti i
     *             servizi utilizzati dal bundle.
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework
     * .BundleContext)
     */
    public void start(final BundleContext context) throws Exception {

        ServiceReference reference = context
        .getServiceReference(KpeopleLog.class.getName());

        service = (KpeopleLog) context.getService(reference);

        service.logInfo(KpeopleLabel.getModuleName(), this.getClass()
                + KpeopleLabel.getLogStart());
    }

    /**
     * Metodo richiamato per svolgere le attività necessarie
     * a stoppare l'esecuzione del bundle.
     * @param context - contesto di esecuzione del bundle da avviare
     * @throws Exception - il verificarsi di un'eccezione all'avvio comporta
     * la cancellazione dei servizi registrati ed i rilascio di tutti i servizi
     * utilizzati dal bundle.
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     * )
     */
    public void stop(final BundleContext context) throws Exception {
        service.logInfo(KpeopleLabel.getModuleName(), this.getClass()
                + KpeopleLabel.getLogStop());
    }

}
