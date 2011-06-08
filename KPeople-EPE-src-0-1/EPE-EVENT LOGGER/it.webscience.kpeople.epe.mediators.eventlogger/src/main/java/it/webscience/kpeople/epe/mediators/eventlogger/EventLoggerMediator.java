package it.webscience.kpeople.epe.mediators.eventlogger;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPBody;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import javax.xml.namespace.QName;

public class EventLoggerMediator extends AbstractMediator{

	
	public boolean mediate(MessageContext synCtx) {
		// TODO Auto-generated method stub
		boolean traceOn = isTraceOn(synCtx);
	    boolean traceOrDebugOn = isTraceOrDebugOn(traceOn);
	    
	    // write log messages
	    if (traceOrDebugOn) {
	      traceOrDebug(traceOn, "Start : EventLogger mediator");

	      if (traceOn && trace.isTraceEnabled()) {
	        trace.trace("Message : " + synCtx.getEnvelope());
	      }
	    }

	    // get symbol, last elements of SOAP envelope
	    SOAPBody body = synCtx.getEnvelope().getBody();
	    OMElement event = body.getFirstElement();
	    OMElement action = event.getFirstElement();
	    
	    
	    String eventId="";
	    
	    
	    
	    String actionType="";
	    String actionReference="";
	    String systemId="";
	    String eventTitle="";
	    
	    try
	    {
	    	//actionType=action.getFirstChildWithName(new QName("action-type")).getText();
	    	
	    	eventId=event.getAttributeValue(new QName("id"));
	    }
	    catch(Exception e)
	    {
	    	log.debug(e.getMessage(),e);
	    }
	    
	    
	    
	    
	    
	    try
	    {
	    	//actionType=action.getFirstChildWithName(new QName("action-type")).getText();
	    	
	    	actionType=action.getFirstChildWithName(new QName("action-type")).getText();
	    }
	    catch(Exception e)
	    {
	    	log.debug(e.getMessage(),e);
	    }
	    
	    try
	    {
	    	actionReference=action.getFirstChildWithName(new QName("action-reference")).getText();
	    }
	    catch(Exception e)
	    {
	    	log.debug(e.getMessage(),e);
	    }
	    
	    try
	    {
	    	OMElement system=action.getFirstChildWithName(new QName("system"));
	    	systemId=system.getFirstChildWithName(new QName("system-id")).getText();
	    }
	    catch(Exception e)
	    {
	    	log.debug(e.getMessage(),e);
	    }
	    
	    try
	    {
	    	OMElement eventData=event.getFirstChildWithName(new QName("event-data"));
	    	eventTitle=eventData.getFirstChildWithName(new QName("title")).getText();
	    }
	    catch(Exception e)
	    {
	    	log.debug(e.getMessage(),e);
	    }
	    
	    StringBuffer sb=new StringBuffer();
	    sb.append(" Event id:" + eventId + " -");
	    sb.append(" Action: type=" + actionType + " -");
	    sb.append(" reference=" + actionReference + " -");
	    sb.append(" source system=" + systemId + " -");
	    sb.append(" event title=" + eventTitle );
	    
	    KpeopleLogger.getInstance().getService().logInfo("EPE" , sb.toString());
	    // write log messages
	    if (traceOrDebugOn) {
	      traceOrDebug(traceOn, "End : EventLogger mediator");
	    }

	    // proceed with next mediator
	    return true;
	}

}
