package it.webscience.kpeople.ega.core;

import it.webscience.kpeople.ega.core
.configurationService.configurationServiceImpl.AdapterConfigurationImpl;
import it.webscience.kpeople.ega.core
.configurationService.configurationServiceInterface.AdapterConfiguration;
import it.webscience.kpeople.ega.core
.errorService.errorServiceImpl.ErrorServiceImpl;
import it.webscience.kpeople.ega.core
.errorService.errorServiceInterface.ErrorService;

import it.webscience.kpeople.ega.core.startupManager.StartupManager;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author XPMUser
 */
public class EGACoreActivator implements BundleActivator {

    /**
     * oggetto indicante il servizio di logging.
     */
    private static KpeopleLog service;

    /**
     * @see
     * org.osgi.framework.BundleActivator
     * #start(org.osgi.framework.BundleContext)
     * @param context contesto di riferimento del bundle.
     * @throws Exception eccezione.
     */
    public void start(final BundleContext context) throws Exception {

        // System.out.println("Start Bundle : CoreActivator" );

        AdapterConfigurationImpl configurationImpl =
            new AdapterConfigurationImpl();
        ServiceRegistration configurationService = context.registerService(
                AdapterConfiguration.class.getName(), configurationImpl, null);
        // System.out.println(" Registrato il servizio " +
        // configurationService.getReference());

        ErrorServiceImpl errorServiceImpl = new ErrorServiceImpl();
        ServiceRegistration errorService = context.registerService(
                ErrorService.class.getName(), errorServiceImpl, null);

        // to start EGA and Channel modules
        StartupManager start = new StartupManager();
        start.startupComponents(context);

        // to be implemented
        shutdownManager();
    }


    /**
     * @see
     * org.osgi.framework.BundleActivator
     * #stop(org.osgi.framework.BundleContext).
     * @param context contesto di riferimento del bundle.
     * @throws Exception eccezione.
     */
    public void stop(final BundleContext context) throws Exception {
        System.out.println("Stop Bundle : CoreActivator");
    }

    /**
     * Call this method to stop Adapter and Channel modules.
     */
    private void shutdownManager() {

    }
}

