
<%@page import="it.webscience.kpeople.web.portlet.activities.search.ActivitiesSearch"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.Activity"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.User"%>
<%@page import="it.webscience.kpeople.web.portlet.activities.util.ActivityUtil"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.Document"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="/html/init.jsp" %>
<portlet:defineObjects />


<% 
//String redirectDettaglio = (String) request.getAttribute("redirectDettaglio");

String redirectDettaglio = (String) request.getAttribute(ActivitiesBrowserConstants.REDIRECT_URL);
Activity currActivity = (Activity) request.getAttribute(ActivitiesBrowserConstants.CURR_ACTIVITY);

if (currActivity != null) {
    
String currActivityHpmId = currActivity.getHpmActivityId();
String patternName = currActivity.getPattern().getName();
String activityCreationDate = dateFormatDateTime.format(currActivity.getPattern().getStartDate());
String activityDueDate = null;
if (Validator.isNotNull(currActivity.getPattern().getDueDate())) {
	activityDueDate = dateFormatDateTime.format(currActivity.getPattern().getDueDate());
}

String requestType = ActivityUtil.getActivityMetadataValueByKeyname(currActivity.getPattern().getPatternMetadatas(), ActivitiesBrowserConstants.PATTERN_REQUEST_TYPE);
String currActivityTaskId = currActivity.getActivitiProcessTaskId();
String activityTypeName = LanguageUtil.get(pageContext, currActivity.getActivityType().getDescription());

String curr_user = themeDisplay.getUser().getScreenName();
String curr_userHpmId = themeDisplay.getUser().getEmailAddress();

User requestor = currActivity.getPattern().getPatternRequestor();
User provider = currActivity.getPattern().getPatternProvider();

User[] userCc = currActivity.getPattern().getCcUsers(); 

Document[] docs = currActivity.getPattern().getDocList();
%>

<script type="text/javascript">
function <portlet:namespace />executeCommandOnActivity(cmd) {

	document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;
	submitForm(document.<portlet:namespace />fm);
}
</script>

<div id="activities-browser-portlet">

<div><h2><%=activityTypeName%>  -  <%=patternName %> </h2></div>

<portlet:actionURL var="sendPatternURL">
		<portlet:param name="_spage" value="/portlet_action/activities-browser/activity-state" />
		<portlet:param name="<%= ActivitiesBrowserConstants.REDIRECT_URL %>" value="<%=redirectDettaglio %>" />
</portlet:actionURL>


<form action="<%= sendPatternURL %>" method="post" name="<portlet:namespace />fm" id="<portlet:namespace />fm" >
	<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
	<input name="<portlet:namespace /><%= ActivitiesBrowserConstants.CURR_ACTIVITY_HPM_ID %>" type="hidden" value="<%=currActivityHpmId %>" />
	<input name="<portlet:namespace /><%= ActivitiesBrowserConstants.CURR_ACTIVITY_TASK_ID %>" type="hidden" value="<%=currActivityTaskId %>" />
	
	
	<div class="activity-details">
		<div class="date-details details">
			<span><liferay-ui:message key="process-open" /></span> 
			<span><%=activityCreationDate%></span> 
			
			<%if (Validator.isNotNull(activityDueDate)) {%>
				<span id="label"><liferay-ui:message key="process-creation-due" /></span>
				<span><%=activityDueDate%></span>
			<%} %>
		</div>
	</div>

	<div class="activity-details">	
		<div class="user-details details">
			<span><liferay-ui:message key="process-created-by" /></span>
			<span><%=requestor.getScreenName()%></span>
			
			<span id="label"><liferay-ui:message key="pattern.user.to" /></span>
			<span> 
				<%=provider.getScreenName() %>
			</span>
			 	
			<% if (userCc != null) { %>
			<span id="label"><liferay-ui:message key="pattern.user.cc" /></span>
					<%
			   		for (int i = 0; i < userCc.length; i++) {
			   		    String usercc = userCc[i].getScreenName();
			   		%>
						<span><%=usercc %></span>								       	
			       	<%	
			   		} 
				} 
			%>
			
		</div>
				
	</div>


	<div class="activity-details">
		<div class="title-details details"> 
			
			<span><liferay-ui:message key="title" /></span>
			<span id="text-content">
				<% String patternTitle = "";
				   if (currActivity != null) { 
				       patternTitle = currActivity.getPattern().getName(); 
				   }
				%> 	
				<span><%=patternTitle %></span>
			</span>
		</div>
	</div>
	
	<div class="activity-details">
		<div class="description-details details"> 
			
			<span><liferay-ui:message key="pattern.request.type" /></span>
			<span><%= requestType %>
			</span>
		</div>
	</div>

	<div class="activity-details">
		<div class="type-details details"> 
			<span><liferay-ui:message key="description" /></span>
			<div id="description"> 
				<% String description = "";
				   if (currActivity != null) { 
				    	description = currActivity.getPattern().getDescription(); 
				   }
				%>
				<span><%=description%></span>	
			</div>
		</div>
	</div>

	<div class="clear"></div>
	<div style="width: 130px;">
		<div>
			<liferay-ui:message key="process.pattern.attachments" />
		</div>
		<div>
		<%if (docs != null) { 
		    String fileName;
			for (Document doc: docs) {
				fileName = doc.getName();
				int idAttachment = doc.getIdAttachment();
				String url = "/kpeople-document-browser-portlet/downloadDocument?idAttachment=" + idAttachment + "&guid=" + doc.getGuid();

		%>	
			<a href="<%= url  %>"><%= doc.getName()%></a><br>
		<%  }
		  } %>
		  </div>
	</div>
	<div style="float: left;">
		<div id="filesContainer">
			
		
		
		</div>
		<div>
			
		</div>
	</div>
	<div class="clear"></div>
	
	<div id="button-control">
<!-- 
	<%if (curr_userHpmId.equalsIgnoreCase(requestor.getHpmUserId())) { %>
		
			<input type="button" value="<liferay-ui:message key="pattern.annulla.richiesta" />" onclick="">
			<input type="button" value="<liferay-ui:message key="pattern.contributo.ricevuto" />" onclick="">
		
	<%} %>
-->
	
	<%if (curr_userHpmId.equalsIgnoreCase(provider.getHpmUserId())) { %>
		
			<input type="button" value="<liferay-ui:message key="pattern.accetta" />" 
				onclick="<portlet:namespace />executeCommandOnActivity('<%=ActivitiesBrowserConstants.ACTIVITY_ACCEPTED%>');">
			<input type="button" value="<liferay-ui:message key="pattern.rifiuta" />" 
				onclick="<portlet:namespace />executeCommandOnActivity('<%=ActivitiesBrowserConstants.ACTIVITY_REJECTED %>');">
			
		
	<%} %>

	<input type="button" value="<liferay-ui:message key="cancel" />" 
				onclick="location.href ='<%=redirectDettaglio%>'">
				
	</div>
</form>
</div>
<% 
}
%>