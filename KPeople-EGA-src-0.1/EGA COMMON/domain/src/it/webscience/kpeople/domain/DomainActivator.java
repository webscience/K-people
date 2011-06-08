package it.webscience.kpeople.domain;

import java.util.Set;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.domain.service.impl.ActionStateManagerImpl;
import it.webscience.kpeople.domain.service.interf.ActionStateManager;
import it.webscience.kpeople.logging.serviceInterface.*;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.osgi.framework.ServiceRegistration;


public class DomainActivator implements BundleActivator{

	public static KpeopleLog service;
	
	public void start(BundleContext context) throws Exception {

		KpeopleLogger.getInstance().getService().logInfo(KpeopleLabel.getSystemId() , this.getClass()+ KpeopleLabel.getLogStart());
		
		
		
		
		try {
			//Create an instance of ActionStateManagerImpl 
			ActionStateManagerImpl asm_impl = new ActionStateManagerImpl();
			 Set<KpeopleAction> result = asm_impl.getActionByState(2);
			//Save the service ActionStateManager in the framework context  
			ServiceRegistration sr = context.registerService(ActionStateManager.class.getName(), asm_impl, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void stop(BundleContext context) throws Exception {
		
		KpeopleLogger.getInstance().getService().logInfo(KpeopleLabel.getSystemId() , " "+ this.getClass()+ KpeopleLabel.getLogStop());
		
	
	}

	
}

