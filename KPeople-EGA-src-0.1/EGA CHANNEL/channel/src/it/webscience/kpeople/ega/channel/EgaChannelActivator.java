package it.webscience.kpeople.ega.channel;

import it.webscience.kpeople.ega.channel.egaChannelManager.EgaChannelManager;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**Classe per la gestione dello
 * start e stop del componente EGA-Channel.
 * @author filieri
 */
public class EgaChannelActivator implements BundleActivator {

    /**
     * Istanza della classe KpeopleLog.
     */
    public static KpeopleLog service;
    /**
     * Istanza della classe Thread.
     */
    private volatile Thread channelManagerThread;
    /**
     * Istanza della classe EgaChannelManager.
     */
    private static EgaChannelManager channelManager;

    /**
     * Metodo richiamato all'avvio del bundle per svolgere
     * eventuali attività di inizializzazione del bundle.
     * @param context - contesto di esecuzione del bundle da avviare
     * @throws Exception - il verificarsi di un'eccezione all'avvio comporta
     * la cancellazione dei servizi registrati ed i rilascio di tutti i servizi
     * utilizzati dal bundle.
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     * )
     */
    public void start(final BundleContext context) throws Exception {
        // TODO Auto-generated method stub
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(EgaChannelLabel.getSystemId(),
                        this.getClass() + EgaChannelLabel.getLogStart());
        channelManager = new EgaChannelManager();
        channelManagerThread = new Thread(channelManager);
        channelManagerThread.start();

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
        // TODO Auto-generated method stub
        channelManager.stop();
        System.out.println("Stop Bundle : AdpmActivator");
    }
}

