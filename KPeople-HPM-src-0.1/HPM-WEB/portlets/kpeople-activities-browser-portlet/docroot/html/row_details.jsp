<%@page import="it.webscience.kpeople.web.portlet.activities.util.ActivitiesBrowserConstants"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.Activity"%>
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
	String hpmProcessId	= ParamUtil.getString(request, "processId");
	Activity activity = (Activity)row.getObject();
	String state = (String) request.getAttribute("activitiesState");
	String activityTitle = LanguageUtil.get(pageContext, activity.getActivityType().getDescription());
	String activityDate = dateFormatDateTime.format(activity.getCreateDate());
	
	String redirectForm = activity.getActivityType().getName();
	
	String activityDetailsURL = destinationPageDettaglioAttivita + "-/activities_browser" + friendlyUrlDettaglioAttivita
		+ activity.getActivitiProcessTaskId() + "?processId=" + hpmProcessId;
%>

				
<portlet:renderURL var="viewActivityDetailsURL" windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>">
    <portlet:param name="_spage" value="/portlet_action/activities-browser/view-activity-details" />
    <portlet:param name="activitiesState" value="<%=state %>" />
    <portlet:param name="hpmActivityId" value="<%=activity.getHpmActivityId() %>" />
   	<portlet:param name="<%=ActivitiesBrowserConstants.REDIRECT_FORM %>" value="<%=redirectForm %>" />
</portlet:renderURL>

<div class="resultsSeparator"></div>
<div class="resultRowA">

<div class="row-activity-update" id="activity-update">
	<div class="action-type" id="event-type-div"> 
			<div class="action-type-name"> 
				<%= LanguageUtil.get(pageContext, activity.getPattern().getPatternType().getName())%>
			</div> 
			<div class="action-type-id"> <%= activity.getPattern().getHpmPatternId()%></div>
				<img src="/kpeople-process-browser-portlet/images/pattern.png" alt="">
			</div>
	
	</div>
	<div class="activityData">
		<div id="id-activityInfo" class="activityInfo" onclick="location.href = '<%= activityDetailsURL%>'" >
			<div id ="id-eventName" class="activityName" >
				<%=activityTitle%>
			</div>
		
			<% String provider = activity.getPattern().getPatternProvider().getScreenName();
				String requestor = activity.getPattern().getPatternRequestor().getScreenName();
			%>
			<%=activityDate%>
				<% if (state.equalsIgnoreCase(ActivitiesBrowserConstants.HPM_ACTIVITIES_PENDING)) {
				%> 
					<b><liferay-ui:message key="process.event-details.to" />: <%= provider %></b>
				
				<%
					} else {
				%>
					<b><liferay-ui:message key="process.event-details.from" />: <%= requestor %></b>
				<%
					}
				%>

		
		</div>
	</div>
</div>

