package it.webscience.kpeople.epe.mediators.eventlogger;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.Mediator;
import org.apache.synapse.config.xml.AbstractMediatorFactory;
import org.apache.synapse.config.xml.XMLConfigConstants;


public class EventLoggerMediatorFactory extends AbstractMediatorFactory{

	static final QName EVENT_LOGGER = new QName(
		      XMLConfigConstants.SYNAPSE_NAMESPACE, "eventLogger");
	
	public Mediator createMediator(OMElement elem) {
		// TODO Auto-generated method stub
		EventLoggerMediator newMediator = new EventLoggerMediator();

	    // setup initial settings 
	    processTraceState(newMediator, elem);

	    return newMediator;
	}

	
	public QName getTagQName() {
		// TODO Auto-generated method stub
		return EVENT_LOGGER;
	}

}
