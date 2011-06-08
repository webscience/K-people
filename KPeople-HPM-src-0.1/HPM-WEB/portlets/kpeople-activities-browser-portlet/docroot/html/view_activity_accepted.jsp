
<%@page import="it.webscience.kpeople.web.portlet.activities.search.ActivitiesSearch"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.Activity"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.User"%>


<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<%@ include file="/html/init.jsp" %>
<portlet:defineObjects />
<%
String redirectDettaglio = (String) request.getAttribute(ActivitiesBrowserConstants.REDIRECT_URL);
Activity currActivity = (Activity) request.getAttribute(ActivitiesBrowserConstants.CURR_ACTIVITY);
if (currActivity != null) {
    String patternTypeName = LanguageUtil.get(pageContext, currActivity.getPattern().getPatternType().getName());
    String patternHpmId =currActivity.getPattern().getHpmPatternId();
    String activityTypeName = LanguageUtil.get(pageContext, currActivity.getActivityType().getDescription());
    
    String activityDate = dateFormatDateTime.format(currActivity.getCreateDate());
    String requestor = currActivity.getPattern().getPatternRequestor().getScreenName();


%>

<div id="activities-browser-portlet">
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
		<liferay-ui:message key="activity.accepted.message" />
	</div>
	
	
	<input type="button" value="<liferay-ui:message key="continue" />" 
			onclick="location.href ='<%=redirectDettaglio%>'">
</div>
<%} %>