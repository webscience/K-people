<%@page import="it.webscience.kpeople.service.cross.GlobalSearchServiceStub"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/html/init.jsp" %>

<%
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	
	GlobalSearchServiceStub.KPeopleGenericDTO process = 
	    (GlobalSearchServiceStub.KPeopleGenericDTO)row.getObject();
	
	DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	
	String diffDaysGg = "";
	String statusDay2Class = "statusDays2Negativo";
	
	WindowState state = WindowState.NORMAL;

	String processDetailsURL = null;
	if (!Validator.isNull(destinationPageParam) 
	        && !Validator.isNull(friendlyUrl)) {
        processDetailsURL = destinationPageParam 
        + "-/process_browser" + friendlyUrl + process.getHpmId();
	}
%>

<div class="resultsSeparator"></div>

<div class="processData">
    <div class="event-type process">
        
        <div class="event-type-name">
            PROCESS
        </div>
        <div class="event-type-id">
            <%= process.getHpmId() %>
        </div>
    </div>
	<div class="processName">
		<a href="<%= processDetailsURL %>">
		    <span><%= process.getName() %></span>
		</a>
		
	</div>
	<div class="processInfo">
	    <span><%= process.getDescription() %></span><br/>
		<liferay-ui:message key="find-processes.results.open-since" />
		<%= dateFormatter.format(process.getCreationDate().getTime()) %>, 
		<liferay-ui:message key="find-processes.results.owned-by" />
		<span class="processOwner"><%= process.getCreator() %></span>
	</div>
</div>


