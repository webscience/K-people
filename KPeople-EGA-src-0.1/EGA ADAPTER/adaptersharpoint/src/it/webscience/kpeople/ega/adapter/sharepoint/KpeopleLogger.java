package it.webscience.kpeople.ega.adapter.sharepoint;

import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author XPMUser
 */
public final class KpeopleLogger {

    /**
     * dfjut hjk.
     */
    private KpeopleLog service_;

    /**
     * Private constructor prevents instantiation from other classes.
     */
    private KpeopleLogger() {
	BundleContext context = FrameworkUtil.getBundle(KpeopleLogger.class)
		.getBundleContext();
	ServiceReference reference = context
		.getServiceReference(KpeopleLog.class.getName());
	service_ = (KpeopleLog) context.getService(reference);
    }

    /**
     * SingletonHolder is loaded on the first execution of
     * Singleton.getInstance() or the first access to SingletonHolder.INSTANCE,
     * not before.
     */
    private static class SingletonHolder {
	public static final KpeopleLogger INSTANCE = new KpeopleLogger();
    }

    /**
     * @return una istanza della classe KpeopleLogger
     */
    public static KpeopleLogger getInstance() {
	return SingletonHolder.INSTANCE;
    }

    /**
     * @return il servizio associato alla istanza della classe KpeopleLogger
     */
    public KpeopleLog getService() {
	return service_;
    }
}
