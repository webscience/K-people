<%@page import="it.webscience.kpeople.service.cross.UserServiceStub"%>
<%@page import="it.webscience.kpeople.web.portlet.activities.search.ActivitiesSearch"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.Activity"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.User"%>
<%@page import="it.webscience.kpeople.web.portlet.activities.util.ActivityUtil"%>

<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="/html/init.jsp" %>
<portlet:defineObjects />




<!-- spostare nel css -->
<style type="text/css">


        .details span{
            padding-right: 20px;
 			
        }
        .date-details input{
            padding-left: 10px;
            margin-left: 10px;
        }
        span#label{
        	margin-left: 50px;
        }
        span#description{
        	valign: center;
        }
        #text-content{
        	padding-left: 50px;
        }
        #description{
        	padding-top: 20px;
        	padding-bottom: 20px;
        }
        .inviaContributoButton {
        	padding-top: 40px;
        }       
</style>
<!-- fine spostare nel css -->

<% 


%>

<script type="text/javascript">
var createPatternRichiediContributoNs = '<portlet:namespace />';

function <portlet:namespace />inviaContributo() {
	
	submitForm(document.<portlet:namespace />fm);
}
</script>

<%
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
    User provider = currActivity.getPattern().getPatternProvider();

    String curr_user = themeDisplay.getUser().getFullName();
    String curr_userHpmId = themeDisplay.getUser().getEmailAddress();
    User[] userCc = currActivity.getPattern().getCcUsers(); 

%>



<portlet:actionURL var="sendPatternURL">
		<portlet:param name="_spage" value="/portlet_action/activities-browser/invia-contributo" />
		<portlet:param name="<%= ActivitiesBrowserConstants.REDIRECT_URL %>" value="<%=redirectDettaglio %>" />
</portlet:actionURL>
 
<div id="activities-browser-portlet">
<form enctype="multipart/form-data" action="<%= sendPatternURL %>" method="post" name="<portlet:namespace />fm" id="<portlet:namespace />fm" >
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
						<b><liferay-ui:message key="activity.richiesto.da" /> <%= requestor %></b>
		
				
			</div>
	</div>
	
	
	
	<%if (curr_userHpmId.equalsIgnoreCase(provider.getHpmUserId())) { %>

		<div class="invia-contributo-details">	
			<div class="user-details details">
				<span><liferay-ui:message key="from" /></span>
				<span><%=curr_user %></span>
				
				<span id="label"><liferay-ui:message key="pattern.user.to" /></span>
				<span> 
					<%=requestor %>
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
	
	
		<div>
			<div> 
				<span><liferay-ui:message key="description" /></span>
				<div id="description"> 
					<textarea cols="160" rows="3" name="<portlet:namespace />patternDescription" id="patternDescription"></textarea>	
				</div>
			</div>
		</div>

	
		<div style="clear: both;"></div>
		<div style="float: left;width: 130px;">
			<liferay-ui:message key="process.pattern.attachments" />
		</div>
		<div style="float: left;">
			<div id="filesContainer"></div>
			<div>
				<span style="cursor: pointer;" onclick="NewPattern.addAttachment();">
					<liferay-ui:message key="process.pattern.attachments.add" />
					<img alt="" src="/kpeople-pattern-browser-portlet/images/list-add.gif">
				</span>
			</div>
		</div>
		<div style="clear: both;"></div>
	
		<div class="inviaContributoButton">
			<input type="button" value="<liferay-ui:message key="send" />" onclick="<portlet:namespace />inviaContributo();">
			<input type="button" value="<liferay-ui:message key="cancel" />" onclick="location.href='<%=redirectDettaglio%>'">
		</div>
	<%} else { %>
	    <div class="activityAcceptedRejected">
			<liferay-ui:message key="wait.invio.contributo" />
			
					<%=provider.getScreenName() %>
			
		</div>
	    
	    
	  	<div class="inviaContributoButton">
			<input type="button" value="<liferay-ui:message key="continue" />" onclick="location.href='<%=redirectDettaglio%>'">
		</div>  
	    
	<%} %>
	
	

</form> 
</div>



<%} %>





















