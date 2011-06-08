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
	
	GlobalSearchServiceStub.KPeopleGenericDTO pattern = 
	    (GlobalSearchServiceStub.KPeopleGenericDTO)row.getObject();
	
	
	DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	String diffDaysGg = "";
	String statusDay2Class = "statusDays2Negativo";
	
	WindowState state = WindowState.NORMAL;

	String patternDetailsUrl = "#";
	String processDetailsURL = "#";
	if (Validator.isNotNull(destinationPagePattern) 
	        && Validator.isNotNull(friendlyUrlPattern)
	        && Validator.isNotNull(pattern.getHpmId())) {
        patternDetailsUrl = destinationPagePattern 
        + "-/pattern_browser" + friendlyUrlViewPattern + pattern.getHpmId();
	}
	
	if (Validator.isNotNull(destinationPageParam) 
            && Validator.isNotNull(friendlyUrl)
            && Validator.isNotNull(pattern.getHpmProcessRefId())) {
        processDetailsURL = destinationPageParam 
        + "-/process_browser" + friendlyUrl + pattern.getHpmProcessRefId();
    }
%>

<div class="resultsSeparator"></div>
<div class="resultRowA">
	<div class="event-type contributo">
	    <div class="event-type-name">
            <liferay-ui:message key="process.pattern.img"></liferay-ui:message>
        </div>
        <div class="event-type-id">
            <%= pattern.getHpmId() %>
        </div>
    </div>
	<div class="processData">
		<div class="processName">
			<a href="<%=patternDetailsUrl %>" >
			    <span><%= pattern.getName() %></span>
			</a> 
			
		</div>
		<div class="processInfo">
			<liferay-ui:message key="find-processes.results.open-since" />
			<%= dateFormatter.format(pattern.getCreationDate().getTime()) %>, 
			<liferay-ui:message key="process.pattern.creator" />
			<span class="processOwner"><%= pattern.getCreator() %>,</span>
			<liferay-ui:message key="process.pattern.provider" />
            <span class="processOwner"><%= pattern.getProvider() %>,</span>
            <liferay-ui:message key="process.assoc" />
            <a href="<%= processDetailsURL %>">
	            <span><%= pattern.getHpmProcessRefId() %></span>
	        </a>
		</div>
	</div>
</div>
<div class="resultRowB">
	<div class="boxEsclamativo"></div>
</div>

