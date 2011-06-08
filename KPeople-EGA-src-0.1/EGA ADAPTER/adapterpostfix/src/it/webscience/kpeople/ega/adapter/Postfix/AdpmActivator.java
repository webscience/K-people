package it.webscience.kpeople.ega.adapter.Postfix;

import it.webscience.kpeople.ega.adapter
.Postfix.actionDataProcessorManager.ActionDataProcessorManager;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author filieri
 */
public class AdpmActivator implements BundleActivator {

    /**
     * Istanza del servizio di logging.
     */
    public static KpeopleLog service;
    
    private volatile Thread adpmThread;
    private static ActionDataProcessorManager adpm;

    public void start(final BundleContext context) throws Exception {

        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(KpeopleLabel.getSystemId(),
                        this.getClass() + KpeopleLabel.getLogStart());

        adpm = new ActionDataProcessorManager();
        adpmThread = new Thread(adpm);
        adpmThread.start();
    }

    public void stop(final BundleContext context) throws Exception {

        // KpeopleLogger.getInstance().getService().logInfo(KpeopleLabel.getSystemId()
        // , " "+ this.getClass()+ KpeopleLabel.getLogStop());
        adpm.kill();
    }

}