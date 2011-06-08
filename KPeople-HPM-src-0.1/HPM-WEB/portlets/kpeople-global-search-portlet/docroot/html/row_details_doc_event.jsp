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
	
	GlobalSearchServiceStub.KPeopleGenericDTO event = 
	    (GlobalSearchServiceStub.KPeopleGenericDTO)row.getObject();
	
	
	DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	String diffDaysGg = "";
	String statusDay2Class = "statusDays2Negativo";
	
	WindowState state = WindowState.NORMAL;
	int attachmentId = event.getAttachmentId();
	String url = "/kpeople-document-browser-portlet/downloadDocument?idAttachment="+ attachmentId + "&guid=" + event.getGuid();
	
	String processDetailsURL = "#";
    if (Validator.isNotNull(destinationPageParam) 
            && Validator.isNotNull(friendlyUrl)
            && Validator.isNotNull(event.getHpmProcessRefId())) {
        processDetailsURL = destinationPageParam 
        + "-/process_browser" + friendlyUrl + event.getHpmProcessRefId();
    }
%>

<div class="resultsSeparator"></div>
<div class="resultRowA">
	<div class="event-type upload">
        
    </div>
	<div class="processData">
		<div class="processName">
			
			<a href="<%= url %>"><%= event.getName()%></a>
			
		</div>
		<div class="processInfo">
			<liferay-ui:message key="event.date" />
            <%= dateFormatter.format(event.getCreationDate().getTime()) %>,
            <% if (Validator.isNotNull(event.getCreator())) { %>
			<liferay-ui:message key="event.creator" />
			<span class="processOwner"><%= event.getCreator() %></span>,
			<% } %>
			<liferay-ui:message key="process.assoc" />
            <a href="<%= processDetailsURL %>">
                <span><%= event.getHpmProcessRefId() %></span>
            </a>
		</div>
	</div>
</div>
<div class="resultRowB">
	<div class="boxEsclamativo"></div>
</div>

