package it.webscience.kpeople.ega.adapter.Postfix;


import it.webscience.kpeople.ega.core.errorService.errorServiceInterface.ErrorService;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class KpeopleErrorServiceInstance {
	private ErrorService service;
	
		   // Private constructor prevents instantiation from other classes
		   private KpeopleErrorServiceInstance() {
			   BundleContext context = FrameworkUtil.getBundle(KpeopleErrorServiceInstance.class).getBundleContext();
			   ServiceReference reference = context.getServiceReference(ErrorService.class.getName());
			   service = (ErrorService) context.getService(reference);
		   }
		 
		   /**
		    * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
		    * or the first access to SingletonHolder.INSTANCE, not before.
		    */
		   private static class SingletonHolder { 
		     public static final KpeopleErrorServiceInstance INSTANCE = new KpeopleErrorServiceInstance();
		   }
		 
		   public static KpeopleErrorServiceInstance getInstance() {
		     return SingletonHolder.INSTANCE;
		   }
		 
 public ErrorService getService(){
	 return service;
 }
}
