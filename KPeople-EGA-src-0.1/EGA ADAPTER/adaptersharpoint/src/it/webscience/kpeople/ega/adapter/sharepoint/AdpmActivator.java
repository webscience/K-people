package it.webscience.kpeople.ega.adapter.sharepoint;

import it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessorManager.ActionDataProcessorManager;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author XPMUser
 */
public class AdpmActivator implements BundleActivator {

    /**
     * Istanza della classe KpeopleLog necessaria per effettuare tutte le
     * operazioni di logging.
     */
    public static KpeopleLog service;
    /**
     * Istanza della classe Thread.
     */
    private volatile Thread adpmThread;

    /**
     * Istanza della classe ActionDataProcessorManager.
     */
    private static ActionDataProcessorManager adpm;

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
     *      BundleContext.
     * @param context
     *            rappresenta il contesto di riferimento del bundle.
     * @throws Exception
     *             eccezione generica.
     */
    public void start(final BundleContext context) throws Exception {

        // logging
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(KpeopleLabel.getSystemId(),
                        this.getClass() + KpeopleLabel.getLogStart());

        adpm = new ActionDataProcessorManager();
        adpmThread = new Thread(adpm);
        adpmThread.start();
    }

    /**
     * @see org.osgi.framework.BundleActivator
     *      #stop(org.osgi.framework.BundleContext).
     * @param context
     *            rappresenta il contesto di riferimento del bundle.
     * @throws Exception
     *             generic exception;
     */
    public void stop(final BundleContext context) throws Exception {
        adpm.stop();
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(KpeopleLabel.getSystemId(),
                        this.getClass() + "Stop Bundle : AdpmActivator");

    }

}
