<%@page import="it.webscience.kpeople.service.event.EventServiceStub"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.EventSearchTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.EventDisplayTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.EventSearch"%>
<%@page import="it.webscience.kpeople.web.portlet.process.util.EventBrowserConstants"%>
    
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
	//String hpmProcessId = (String)renderRequest.getAttribute(EventDisplayTerms.PROCESS_ID);
	//portletURL.setParameter(EventDisplayTerms.PROCESS_ID, hpmProcessId);
	
	EventSearch searchContainer = new EventSearch(renderRequest, portletURL);
	//EventDisplayTerms displayTerms = (EventDisplayTerms)searchContainer.getDisplayTerms();
	
	searchContainer.setDelta(risultatiPerPagina);
	searchContainer.setDeltaConfigurable(false);
	searchContainer.setOrderByCol("");
	searchContainer.setOrderByType("");
	
	if(renderRequest.getAttribute(EventBrowserConstants.LIST_EVENTS_UPDATE) != null) {
	    EventServiceStub.Event[] results = (EventServiceStub.Event[])renderRequest.
	    	getAttribute(EventBrowserConstants.LIST_EVENTS_UPDATE);
	    
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
				row.addJSP("/html/row_event-update-mail.jsp");
				resultRows.add(row);
			} else if (eventType.equalsIgnoreCase("DOCUMENT/MODIFIED")){
			    row.addJSP("/html/row_event-update-document.jsp");
			    resultRows.add(row);
			} else if (eventType.equalsIgnoreCase("PATTERN")){
			    row.addJSP("/html/row_event-update-pattern.jsp");
			    resultRows.add(row);
			} else if (eventType.equalsIgnoreCase("DOCUMENT.DOWNLOAD")){
			    row.addJSP("/html/row_event-update-document-download.jsp");
			    resultRows.add(row);
			}
	    }
	    request.setAttribute("liferay-ui:page-iterator:url", null);
	    %>
	    <webscience-ui:asynchronous-search-iterator
	    	formId="<portlet:namespace/>eventsPerProcessFm"
	    	divId="update-events-associated-at-the-process"
	    	searchContainer="<%= searchContainer %>" />
<%
	}
	
%>