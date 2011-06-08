package it.webscience.kpeople.epe.mediators.eventlogger;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.Mediator;
import org.apache.synapse.config.xml.AbstractMediatorSerializer;



public class EventLoggerMediatorSerializer extends AbstractMediatorSerializer{

	
	
	public String getMediatorClassName() {
		// TODO Auto-generated method stub
		return EventLoggerMediator.class.getName();
	}

	
	public OMElement serializeMediator(OMElement parent, Mediator m) {
		// TODO Auto-generated method stub
		if (!(m instanceof EventLoggerMediator)) {
		      handleException("Unsupported mediator passed in for serialization : "
		          + m.getType());
		    }

		EventLoggerMediator mediator = (EventLoggerMediator) m;
		    OMElement eventLoggerElement = fac
		        .createOMElement(EventLoggerMediatorFactory.EVENT_LOGGER);
		    parent.addChild(eventLoggerElement);
		    
		    saveTracingState(eventLoggerElement, mediator);

		    return eventLoggerElement;
	}

}
