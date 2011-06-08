package it.webscience.kpeople.ega.channel;

import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class KpeopleLogger {

    private KpeopleLog service;

    // Private constructor prevents instantiation from other classes
    private KpeopleLogger() {
        BundleContext context = FrameworkUtil.getBundle(KpeopleLogger.class)
                .getBundleContext();
        ServiceReference reference = context
                .getServiceReference(KpeopleLog.class.getName());
        service = (KpeopleLog) context.getService(reference);
    }

    /**
     * SingletonHolder is loaded on the first execution of
     * Singleton.getInstance() or the first access to SingletonHolder.INSTANCE,
     * not before.
     */
    private static class SingletonHolder {
        public static final KpeopleLogger INSTANCE = new KpeopleLogger();
    }

    public static KpeopleLogger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public KpeopleLog getService() {
        return service;
    }
}
