<%@page import="it.webscience.kpeople.web.portlet.pattern.util.PatternBrowserConstants"%>
<%@page import="it.webscience.kpeople.service.event.EventServiceStub"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="it.webscience.kpeople.web.portlet.pattern.search.EventSearchTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.pattern.search.EventDisplayTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.pattern.search.EventSearch"%>
    
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/html/init.jsp" %>
<%
	int risultatiPerPagina = 10;
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);
	portletURL.setParameter("_spage", "/portlet_action/events-update-browser/event-update");

	EventSearch searchContainer = new EventSearch(renderRequest, portletURL);
	searchContainer.setDelta(risultatiPerPagina);
	searchContainer.setDeltaConfigurable(false);
	searchContainer.setOrderByCol("");
	searchContainer.setOrderByType("");
	
	if(renderRequest.getAttribute(PatternBrowserConstants.LIST_EVENTS_UPDATE) != null) {
	    EventServiceStub.Event[] results = (EventServiceStub.Event[])renderRequest.
	    	getAttribute(PatternBrowserConstants.LIST_EVENTS_UPDATE);
	    
	    EventSearchTerms searchTerms = (EventSearchTerms)searchContainer.getSearchTerms();
	     
	     //imposto totale risultati ed elenco
	     
	     List<EventServiceStub.Event> resultList = Arrays.asList(results);
	     searchContainer.setTotal(resultList.size());
	     searchContainer.setResults(resultList);
	     
	    
	    List<ResultRow> resultRows = searchContainer.getResultRows();
	    String eventType = null;
	    for (int i = searchContainer.getStart(); i < Math.min(searchContainer.getEnd(), resultList.size()) ; i++) {
	        EventServiceStub.Event event = results[i];
	        
	        for(int j = 0; j < event.getEventMetadata().length; j++) {
	            if (event.getEventMetadata()[j].getKeyname().equalsIgnoreCase("action-type")) {
	                eventType = event.getEventMetadata()[j].getValue();
	                break;
	            }
	        }
	         
	        ResultRow row = new ResultRow(event, event.getHpmEventId(), i);
	        row.setClassName("yy");
			row.setClassHoverName("yy");

			if (eventType.equalsIgnoreCase("COMMUNICATION")) {
				row.addJSP("/html/events_per_pattern-mail.jsp");
				resultRows.add(row);
			} else if (eventType.equalsIgnoreCase("DOCUMENT/MODIFIED")){
			    row.addJSP("/html/events_per_pattern-document.jsp");
			    resultRows.add(row);
			} else if (eventType.equalsIgnoreCase("PATTERN")){
			    row.addJSP("/html/events_per_pattern-pattern.jsp");
			    resultRows.add(row);
			} else if (eventType.equalsIgnoreCase("PROCESS.PATTERN.RICHIESTACONTRIBUTO.INVIO")){
			    row.addJSP("/html/events_per_pattern-richiestaContributoInvio.jsp");
			    resultRows.add(row);
			} else if (eventType.equalsIgnoreCase("PROCESS.PATTERN.RICHIESTACONTRIBUTO.ACCETTA")){
			    row.addJSP("/html/events_per_pattern-richiestaContributoAccetta.jsp");
			    resultRows.add(row);
			} else if (eventType.equalsIgnoreCase("PROCESS.PATTERN.RICHIESTACONTRIBUTO.RIFIUTATA")){
				    row.addJSP("/html/events_per_pattern-richiestaContributoRifiutata.jsp");
				    resultRows.add(row);
				}
	    }
	    request.setAttribute("liferay-ui:page-iterator:url", null);
	    %>
	    <webscience-ui:asynchronous-search-iterator
	    	formId="<portlet:namespace/>eventsPerProcessFm"
	    	divId="update-events-associated-at-the-pattern"
	    	searchContainer="<%= searchContainer %>" />
<%
	}
	
%>