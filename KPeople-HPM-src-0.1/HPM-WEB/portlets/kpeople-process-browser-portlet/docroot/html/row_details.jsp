<%@page import="it.webscience.kpeople.web.portlet.process.util.ProcessUtils"%>
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
	
	ProcessServiceStub.Process process = (ProcessServiceStub.Process)row.getObject();
	User owner = process.getOwner();
	
	DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	
	String diffDaysGg = "";
	String statusDay2Class = "statusDays2Negativo";
	if (process.getDateDue() != null) {
		long diffDays = ProcessUtils.getDaysToProcessDue(process.getDateDue());
		if (diffDays <= 0) {
		    statusDay2Class = "statusDays2Positivo"; 
		}
		
		diffDaysGg = diffDays + " g";
	}

	WindowState state = WindowState.NORMAL;

	String processDetailsURL = null;
	
	String imgVisibility = "";
	if (process.getVisibility() == 0)
	    imgVisibility = "visibility_public.png";
	else if (process.getVisibility() == 1)
	    imgVisibility = "visibility_owner.png";
	else if (process.getVisibility() == 2)
	    imgVisibility = "visibility_enabled.png";
	else if (process.getVisibility() == 3)
	    imgVisibility = "visibility_private.png";
	
	String active = LanguageUtil.get(pageContext,"find-processes.state.open");
	if (process.getProcessState().getIdProcessState() == 2) {
	    active = LanguageUtil.get(pageContext,"find-processes.state.closed");
	}
	
	String processOwner = "";
	if (process.getOwner() != null) {
	    if (process.getOwner().getScreenName() != null) {
	        processOwner = process.getOwner().getScreenName();
	    } else if (process.getOwner().getHpmUserId() != null) {
	        processOwner = process.getOwner().getHpmUserId();
	    }
	}

	if (process.getVisibility() == 1) {
	    processOwner += "<b>&nbsp;(" + LanguageUtil.get(pageContext,"process.tu") + ")</b>";
	}
%>

<div class="resultsSeparator"></div>
<div class="resultRowA">
	<div class="visibilityDiv">
		<img alt="" src="/kpeople-process-browser-portlet/images/<%=imgVisibility %>">
	</div>
	<div class="processData">
		<div class="processName">
			<!-- 
			<a onclick="location.href = '<portlet:actionURL windowState="<%= state.toString() %>">
				<portlet:param name="_spage" value="/portlet_action/process-browser/view-process-details" />
				</portlet:actionURL>'; "href="#"> 
			</a>
			-->
			<%if (!Validator.isNull(destinationPageParam) && !Validator.isNull(friendlyUrl)) {
				processDetailsURL = destinationPageParam + "-/process_browser" + friendlyUrl + process.getHpmProcessId();
			%>
				<a href="<%=processDetailsURL %>" >
					<%= process.getName() %>
				</a>
			<% } else { %>

			<span><%= process.getName() %></span>
			
			<% } %>
		</div>
		<div class="processInfo">
			<liferay-ui:message key="find-processes.results.open-since" />
			<%= dateFormatter.format(process.getDateCreated()) %>, 
			<liferay-ui:message key="find-processes.results.owned-by" />
			<span class="processOwner"><%= processOwner %></span>
		</div>
	</div>
	<div class="statusDays">
		<div class="statusDays1"><%= active %></div>
		<div class="statusDays2 <%=statusDay2Class %>"><%=diffDaysGg %></div>
	</div>
</div>
<div class="resultRowB">
	<div class="boxEsclamativo"></div>
</div>

