
<%@page import="it.webscience.kpeople.web.portlet.activities.search.ActivitiesSearch"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.Activity"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.User"%>
<%@page import="it.webscience.kpeople.web.portlet.activities.util.ActivitiesBrowserConstants"%>

<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="/html/init.jsp" %>
<portlet:defineObjects />

<script type="text/javascript">
function <portlet:namespace />executeCommandOnActivity(cmd) {

	document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;
	submitForm(document.<portlet:namespace />fm);
}
</script>


<%
//String redirectDettaglio = (String) request.getAttribute("redirectDettaglio");
String redirectDettaglio = (String) request.getAttribute(ActivitiesBrowserConstants.REDIRECT_URL);

Activity currActivity = (Activity) request.getAttribute(ActivitiesBrowserConstants.CURR_ACTIVITY);
if (currActivity != null) {
    String currActivityHpmId = currActivity.getHpmActivityId();
    String patternTypeName = LanguageUtil.get(pageContext, currActivity.getPattern().getPatternType().getName());
    String patternHpmId =currActivity.getPattern().getHpmPatternId();
    String activityTypeName = LanguageUtil.get(pageContext, currActivity.getActivityType().getDescription());
    String currActivityTaskId = currActivity.getActivitiProcessTaskId();
    String activityDate = dateFormatDateTime.format(currActivity.getCreateDate());
    String requestor = currActivity.getPattern().getPatternRequestor().getScreenName();

    String curr_user = themeDisplay.getUser().getFullName();
    String curr_userHpmId = themeDisplay.getUser().getEmailAddress();
%>

<portlet:actionURL var="sendPatternURL">
		<portlet:param name="_spage" value="/portlet_action/activities-browser/activity-state" />
		<portlet:param name="<%= ActivitiesBrowserConstants.REDIRECT_URL %>" value="<%=redirectDettaglio %>" />
		
</portlet:actionURL>

<div id="activities-browser-portlet">

	<form action="<%= sendPatternURL %>" method="post" name="<portlet:namespace />fm" id="<portlet:namespace />fm" >
		<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
		<input name="<portlet:namespace /><%= ActivitiesBrowserConstants.CURR_ACTIVITY_HPM_ID %>" type="hidden" value="<%=currActivityHpmId %>" />
		<input name="<portlet:namespace /><%= ActivitiesBrowserConstants.CURR_ACTIVITY_TASK_ID %>" type="hidden" value="<%=currActivityTaskId %>" />
		
	
		<div class="row-activity-update" id="activity-update">
			<div class="action-type" id="event-type-div"> 
					<div class="action-type-name"> 
						<%= patternTypeName%>
					</div> 
					<div class="action-type-id"> <%= patternHpmId%></div>
						<img src="/kpeople-process-browser-portlet/images/pattern.png" alt="">
					</div>
			
			</div>
			<div class="activityData">
				<div id="id-activityInfo-accepted" class="activityInfo">
					<div id ="id-activityName" class="activityName" >
						<%=activityTypeName%>
					</div>
					<%=activityDate%>
						<b><liferay-ui:message key="process.event-details.from" />: <%= requestor %></b>
		
				
			</div>
		</div>
				
		<div class="activityAcceptedRejected">
			<liferay-ui:message key="activity.reject.message" />
		</div>
		
		<div class="activity-details">
			<div class="type-details details"> 
				<span><liferay-ui:message key="description" /></span>
				<div id="description"> 
					<% String description = "";
					%>
					<textarea cols="160" rows="3" name="patternDescription" id="patternDescription"><%=description%></textarea>	
				</div>
			</div>
		</div>
		
		<div>
			<input type="button" value="<liferay-ui:message key="pattern.rifiuta" />" 
					onclick="<portlet:namespace />executeCommandOnActivity('<%=ActivitiesBrowserConstants.ACTIVITY_REJECTED_CONFIRM%>');">
			<input type="button" value="<liferay-ui:message key="cancel" />" 
					onclick="location.href ='<%=redirectDettaglio%>'">
		</div>
	</form>
	
</div>
<%} %>

