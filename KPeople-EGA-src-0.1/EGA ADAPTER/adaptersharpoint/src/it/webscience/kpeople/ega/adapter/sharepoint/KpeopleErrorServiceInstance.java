package it.webscience.kpeople.ega.adapter.sharepoint;

import it.webscience.kpeople.ega.core.errorService.errorServiceInterface.ErrorService;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author XPMUser
 */
public class KpeopleErrorServiceInstance {
    private ErrorService service_;

    /**
     * Private constructor prevents instantiation from other classes.
     */
    private KpeopleErrorServiceInstance() {
	BundleContext context = FrameworkUtil.getBundle(
		KpeopleErrorServiceInstance.class).getBundleContext();
	ServiceReference reference = context
		.getServiceReference(ErrorService.class.getName());
	service_ = (ErrorService) context.getService(reference);
    }

    /**
     * SingletonHolder is loaded on the first execution of
     * Singleton.getInstance() or the first access to SingletonHolder.INSTANCE,
     * not before.
     */
    private static class SingletonHolder {
	public static final KpeopleErrorServiceInstance INSTANCE = new KpeopleErrorServiceInstance();
    }

    /**
     * @return l'istanza della classe.
     */
    public static KpeopleErrorServiceInstance getInstance() {
	return SingletonHolder.INSTANCE;
    }

    /**
     * @return il servizio associato alla istanza della classe KpeopleErrorServiceInstance.
     */
    public ErrorService getService() {
	return service_;
    }
}
