package it.webscience.kpeople.logging;



import it.webscience.kpeople.logging.serviceImpl.KpeopleLogImpl;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class LoggingActivator implements BundleActivator{
    
	
	
	public void start(BundleContext context) throws Exception {
		KpeopleLogImpl log_impl = new KpeopleLogImpl();
		ServiceRegistration log_service = context.registerService(KpeopleLog.class.getName(), log_impl, null);
		System.out.println(" Registrato il servizio " + log_service.getReference());
		
		
	}

	
	public void stop(BundleContext arg0) throws Exception {
		
	}
	
	
	
	
}