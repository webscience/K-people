package it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessorManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.domain.model.KpeopleEvent;
import it.webscience.kpeople.domain.service.interf.ActionStateManager;

import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleErrorServiceInstance;
import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLogger;
import it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessor.ActionDataProcessor;
import it.webscience.kpeople.ega.core.configurationService.configurationServiceInterface.AdapterConfiguration;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author XPMUser
 */
public class ActionDataProcessorManager implements Runnable {

    /**
     * Variabile booleano di controllo del thread.
     */
    private boolean stopped;
    /**
     * Istanza del servizio per l'accesso ai metodi di gestione del domanin.
     */
    private ActionStateManager service;
    /**
     * Insieme delle azioni in attesa di essere processate.
     */
    private Set<KpeopleAction> actionsToProcess =
            new LinkedHashSet<KpeopleAction>();
    /**
     * Istanza di executorService necessaria a gestire il threadPool.
     */
    private ExecutorService executorService;
    /**
     * Istanza del servizio per l'accesso ai metodi di gestione della
     * configurazione.
     */
    private static AdapterConfiguration serviceConfiguration;
    /**
     * Variabile indicate l'intervallo di polling.
     */
    private long pollingTime;
    /**
     * Oggetto di classe Properties necessario a contenere le proprietà di
     * configurazione del componente adapter.
     */
    private static Properties adapterproperties;

    private static int counter;

    
    /**
     * Metodo costruttore.
     */
    public ActionDataProcessorManager() {
        ActionDataProcessorManager.counter = 0;
    }

    /**
     * @see java.lang.Runnable#run().
     */
    public final void run() {
        while (true) {
            // read configuration parameters
            KpeopleLogger
                    .getInstance()
                    .getService()
                    .logInfo(
                            KpeopleLabel.getSystemId(),
                            this.getClass() + ".run()"
                                    + KpeopleLabel.getLogStart());

            Properties currProperties = readConfiguration();

            int poolsize =
                    Integer.parseInt((String) currProperties.get(KpeopleLabel
                            .getThreadPoolSize()));
            pollingTime =
                    Long.parseLong((String) currProperties.get(KpeopleLabel
                            .getPollingTime()));
            executorService = Executors.newFixedThreadPool(poolsize);

            try {
                // polling db to retrieve the actions in state one
                actionsToProcess.addAll(pollingDB());

                Iterator<KpeopleAction> it = actionsToProcess.iterator();

                List<Future<KpeopleEvent>> futures =
                        new ArrayList<Future<KpeopleEvent>>();

                while (it.hasNext()) {
                    KpeopleAction curr = (KpeopleAction) it.next();
                    futures.add(executorService.submit(new ActionDataProcessor(
                            curr)));
                }
                actionsToProcess.clear();

                Thread.sleep(pollingTime);
                synchronized (this) {
                    if (stopped) {
                        break;
                    }
                }
            } catch (Exception e) {
                if (counter != 3) {
                    KpeopleErrorServiceInstance.getInstance().getService()
                        .sendErrorNotification(e);
                    counter = counter + 1;
                }
                Thread.currentThread().stop();
                e.printStackTrace();
            }
        }

    }

    /**
     * Metodo per stoppare il thread in esecuzione.
     */
    public final synchronized void stop() {
        stopped = true;

        notify();
    }

    /**
     * Metodo per stoppare il thread in esecuzione.
     */
    /*
     * public final void kill() { stop(); }
     */

    /**
     * @return the set of actions in state one.
     * @throws Exception
     *             eccezione generica.
     */
    public final List<KpeopleAction> pollingDB() throws Exception {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".pollingdb()"
                                + KpeopleLabel.getLogStart());

        BundleContext context =
                FrameworkUtil.getBundle(this.getClass()).getBundleContext();
        ServiceReference reference =
                context.getServiceReference(ActionStateManager.class.getName());
        service = (ActionStateManager) context.getService(reference);

        String systemId =
                (String) adapterproperties.get(KpeopleLabel.getSystemId());

        List<KpeopleAction> newActions =
                service.getActionByState(KpeopleLabel.toProcess(), systemId);
        
        KpeopleLogger
                .getInstance()
                .getService()
                .logDebug(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".pollingdb()"
                                + newActions.size());

        if (!newActions.isEmpty()) {
            for (KpeopleAction action : newActions) {
                service.updateActionState(action, KpeopleLabel.readyToProcess());
            }

        }
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".pollingdb()"
                                + KpeopleLabel.getLogStop());
        return newActions;
    }

    /**
     * @return adapterproperties, l'insieme delle proprietà di configurazione.
     */
    public static Properties readConfiguration() {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        ActionDataProcessorManager.class
                                + ".readConfiguration()"
                                + KpeopleLabel.getLogStart());

        if (adapterproperties == null) {
            adapterproperties = new Properties();
        }

        BundleContext context =
                FrameworkUtil.getBundle(ActionDataProcessorManager.class)
                        .getBundleContext();
        ServiceReference reference =
                context.getServiceReference(AdapterConfiguration.class
                        .getName());
        serviceConfiguration =
                (AdapterConfiguration) context.getService(reference);

        try {
            adapterproperties = serviceConfiguration.getAllConfigurationItem();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        ActionDataProcessorManager.class
                                + ".readConfiguration()"
                                + KpeopleLabel.getLogStop());

        return adapterproperties;
    }

    /**
     * @return the adapterproperties
     **/
    public static Properties getAdapterproperties() {
        return adapterproperties;
    }

}
