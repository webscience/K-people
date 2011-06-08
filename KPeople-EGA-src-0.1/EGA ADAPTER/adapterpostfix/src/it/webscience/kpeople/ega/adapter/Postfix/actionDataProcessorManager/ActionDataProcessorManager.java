package it.webscience.kpeople.ega.adapter.Postfix.actionDataProcessorManager;


import java.util.ArrayList;
import java.util.HashMap;
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

import it.webscience.kpeople.ega.adapter.Postfix.KpeopleErrorServiceInstance;
import it.webscience.kpeople.ega.adapter.Postfix.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.Postfix.KpeopleLogger;
import it.webscience.kpeople.ega.adapter.Postfix.actionDataProcessor.ActionDataProcessor;
import it.webscience.kpeople.ega.adapter.Postfix.util.ActionDataProcessorUtil;
import it.webscience.kpeople.ega.core.configurationService.configurationServiceInterface.AdapterConfiguration;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class ActionDataProcessorManager implements Runnable{

	boolean stopped;
	private ActionStateManager service;
	private Set<KpeopleAction> actionsToProcess = new LinkedHashSet<KpeopleAction>();
	private ExecutorService executorService; 
	private static AdapterConfiguration serviceConfiguration;
	private long pollingTime;
	
	private static Properties adapterproperties; 
	
	private static int counter;

	public ActionDataProcessorManager() {	    
	    ActionDataProcessorManager.counter = 0;
	}

	public void run() {
		while (true){
			//read configuration parameters
			KpeopleLog logger = KpeopleLogger.getInstance().getService();
			logger.logInfo(KpeopleLabel.getSystemId() , this.getClass()+".run()"+ KpeopleLabel.getLogStart());

			Properties curr_properties = readConfiguration();
			
			int poolsize = 	Integer.parseInt((String)curr_properties.get(KpeopleLabel.getThreadPoolSize()));
	                pollingTime = Long.parseLong((String)curr_properties.get(KpeopleLabel.getPollingTime()));
			executorService = Executors.newFixedThreadPool(poolsize);		
			
			try {	
				//polling db to retrieve the actions in state one
				actionsToProcess.addAll(pollingDB());
				logger.logInfo(KpeopleLabel.getSystemId(), this.getClass()+".run()" + " polling db:  " + pollingTime + " "+ actionsToProcess);

				Iterator<?> it = actionsToProcess.iterator();

				List<Future<KpeopleEvent>> futures = new ArrayList<Future<KpeopleEvent>>();

				while (it.hasNext()){
					KpeopleAction curr = (KpeopleAction) it.next();
					futures.add(executorService.submit(new ActionDataProcessor(curr)));
				}			
				actionsToProcess.clear();

				if (!actionsToProcess.isEmpty()){

				}

				Thread.sleep(pollingTime);
				synchronized (this) {
					if (stopped)
						break;
				}
			} catch (Exception e) {
			    if (counter != 3) {
				KpeopleErrorServiceInstance.getInstance().getService().sendErrorNotification(e);
				counter = counter + 1;
				Thread.currentThread().stop();
				e.printStackTrace();
			    }
			}
		}

	}

	synchronized void stop() {
		stopped = true;

		notify();
	}

	public void kill(){

		stop();
	}
	
	/**
	 * @return the set of actions in state one
	 */
	public List<KpeopleAction> pollingDB() throws Exception{
		KpeopleLogger.getInstance().getService().logInfo(KpeopleLabel.getSystemId() , this.getClass()+".pollingdb()"+ KpeopleLabel.getLogStart());

		BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		ServiceReference reference = context.getServiceReference(ActionStateManager.class.getName());
		service = (ActionStateManager) context.getService(reference);

		String systemId = (String) adapterproperties.get(KpeopleLabel.getSystemId());
		
		List<KpeopleAction> new_actions = service.getActionByState(KpeopleLabel.toProcess(), systemId);

		if (!new_actions.isEmpty()){
			for (KpeopleAction action : new_actions){			
				service.updateActionState(action, KpeopleLabel.readyToProcess());
			}

		}
		KpeopleLogger.getInstance().getService().logInfo(KpeopleLabel.getSystemId() , this.getClass()+".pollingdb()"+ KpeopleLabel.getLogStop());
		return new_actions;	
	}
	
	public static Properties readConfiguration() {
		KpeopleLogger.getInstance().getService().logInfo(KpeopleLabel.getSystemId() , ActionDataProcessorManager.class+".readConfiguration()"+ KpeopleLabel.getLogStart());

		if (adapterproperties == null){
			adapterproperties = new Properties();
		} 
		
		BundleContext context = FrameworkUtil.getBundle(ActionDataProcessorManager.class).getBundleContext();
		ServiceReference reference = context.getServiceReference(AdapterConfiguration.class.getName());
		serviceConfiguration = (AdapterConfiguration) context.getService(reference);
		
		try {
			adapterproperties = serviceConfiguration.getAllConfigurationItem();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		KpeopleLogger.getInstance().getService().logInfo(KpeopleLabel.getSystemId() , ActionDataProcessorManager.class+".readConfiguration()"+ KpeopleLabel.getLogStop());

		return adapterproperties;
	}

	/**
	* @return the adapterproperties
	**/
	public static Properties getAdapterproperties() {
		return adapterproperties;
	}
	
	

}
