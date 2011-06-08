package it.webscience.kpeople.ega.channel.egaChannelManager;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.domain.model.KpeopleEvent;
import it.webscience.kpeople.domain.service.interf.ActionStateManager;

import it.webscience.kpeople.ega.channel.EgaChannelLabel;
import it.webscience.kpeople.ega.channel.KpeopleLogger;
import it.webscience.kpeople.ega.core.configurationService
.configurationServiceInterface.AdapterConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * Classe per polling sulla base dati al fine di individuare gli eventi in
 * attesa di invio. Implementa l'interfaccia Runnable.
 * @author filieri
 */
public class EgaChannelManager implements Runnable {
    /**
     * variabile boolean per il controllo dello stop del thread in esecuzione.
     */
    boolean stopped;
    /**
     * Istanza del servizio ActionStateManager esposto dal bundle domain per la
     * connessione al database.
     */
    private static ActionStateManager service;
    /**
     * Istanza della classe Java Properties all'interno della quale sono
     * memorizzati tutti i parametri di configurazione del bundle channel.
     */
    private static Properties channelProperties;

    /**
     * Riferimento al servizio esposto dal bundle core per il recupero dei
     * parametri di configurazione.
     */
    private static AdapterConfiguration serviceConfiguration;
    /**
     * Istanza di executorService necessaria a gestire il threadPool.
     */
    private ExecutorService executorService;

    /**
     * Lista di oggetti di tipo KpeopleEvent indicante l'insieme di eventi,
     * recuperati dal database, in attesa di invio al modulo EPE.
     */
    private Set<KpeopleEvent> eventsToSend = new LinkedHashSet<KpeopleEvent>();

    /**
     * Costruttore della classe EgaChannelManager. Viene recuperato il
     * riferimento al servizio esposto dal bundle domain per la connessione al
     * db contenente azioni ed eventi generati.
     */
    public EgaChannelManager() {
        BundleContext context = FrameworkUtil.getBundle(this.getClass())
                .getBundleContext();
        ServiceReference reference = context
                .getServiceReference(ActionStateManager.class.getName());
        service = (ActionStateManager) context.getService(reference);
    }

    /**
     * @see java.lang.Runnable#run().
     */
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            Properties currChannelProperties = readConfiguration();
            int numThreadChannels = Integer
                    .parseInt((String) currChannelProperties
                            .get(EgaChannelLabel.getNumThreadChannels()));
            long channelPollingTime = Long
                    .parseLong((String) currChannelProperties
                            .get(EgaChannelLabel.getChannelPollingTime()));
            eventsToSend.addAll(pollingDB());

            executorService = Executors.newFixedThreadPool(numThreadChannels);
           

            Iterator it = eventsToSend.iterator();
            List<Future<KpeopleEvent>> futures =
                new ArrayList<Future<KpeopleEvent>>();

            while (it.hasNext()) {
                KpeopleEvent currEvent = (KpeopleEvent) it.next();
                futures.add(executorService.submit(new EgaChannel(currEvent)));
            }
            eventsToSend.clear();

            try {
                Thread.sleep(channelPollingTime);
                synchronized (this) {
                    if (stopped) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /**
     * @return l'insieme degli eventi in stato "statePacked" ovvero pronti per
     *         l'invio.
     */
    private Set<KpeopleEvent> pollingDB() {
        // TODO Auto-generated method stub
        Set<KpeopleAction> actionsPacked = service
                .getActionByState(EgaChannelLabel.statePacked());
        
        Set<KpeopleEvent> events = new LinkedHashSet<KpeopleEvent>();
        if (!actionsPacked.isEmpty()) {
            for (KpeopleAction selectedAction : actionsPacked) {
                KpeopleEvent event = selectedAction.getEvent();
                events.add(event);
                service.updateActionState(selectedAction,
                        EgaChannelLabel.readyToSent());
            }
        }

        return events;
    }

    /**
     * @param action
     *            - riferimento all'azione il cui stato deve essere aggiornato
     *            allo stato sent.
     */
    public static void updateDB(final KpeopleAction action) {
        if (action != null) {
            service.updateActionState(action, EgaChannelLabel.eventSent());
            deleteEventFile(action.getIdAction());
        }

    }

    /**
     * @return Properties - insieme delle proprietà di configurazione del bundle
     *         channel.
     */
    private Properties readConfiguration() {
        // TODO Auto-generated method stub
        if (channelProperties == null) {
            channelProperties = new Properties();
        }
        BundleContext context = FrameworkUtil.getBundle(this.getClass())
                .getBundleContext();
        ServiceReference reference = context
                .getServiceReference(AdapterConfiguration.class.getName());
        serviceConfiguration = (AdapterConfiguration) context
                .getService(reference);

        try {
            channelProperties = serviceConfiguration.getAllConfigurationItem();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return channelProperties;
    }

    /**
     * Metodo per stoppare il thread in esecuzione.
     */
    public final synchronized void stop() {
        stopped = true;
        notify();
    }

    /**
     * @return channelProperties - insieme delle proprietà di configurazione del
     *         bundle channel.
     */
    public static Properties getChannelProperties() {
        return channelProperties;
    }

    /**
     * @param idAction
     *            - id dell'azione utilizzato come puntatore al file temporaneo,
     *            relativo all'evento, che deve essere cancellato dal
     *            filesystem.
     */
    public static void deleteEventFile(final Long idAction) {

        String fileName = channelProperties.getProperty(EgaChannelLabel
                .getEventXMLPath()) + "event" + "-" + idAction + ".xml";
        // A File object to represent the filename
        File f = new File(fileName);

        // Make sure the file or directory exists and isn't write protected
        if (!f.exists()) {
            KpeopleLogger
            .getInstance()
            .getService()
            .logInfo("Delete: no such file or directory: ",
                    EgaChannelManager.class + "deleteEventFile");
            throw new IllegalArgumentException(
                    "Delete: no such file or directory: " + fileName);
            
        }

        if (!f.canWrite()) {
            throw new IllegalArgumentException("Delete: write protected: "
                    + fileName);
        }

        // Attempt to delete it
        boolean success = f.delete();
        if (success) {
            KpeopleLogger
            .getInstance()
            .getService()
            .logInfo(fileName,
                    EgaChannelManager.class + "deleteEventFile");
        }

        if (!success) {
            throw new IllegalArgumentException("Delete: deletion failed");
        }
    }

}
