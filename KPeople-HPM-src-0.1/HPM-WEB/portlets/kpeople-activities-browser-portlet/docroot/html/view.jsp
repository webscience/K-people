
<%@page import="it.webscience.kpeople.service.pattern.PatternServiceStub"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="/html/init.jsp" %>
<portlet:defineObjects />

<%
WindowState state = LiferayWindowState.EXCLUSIVE;
%>



<div id="activities-browser-portlet">

	<div class="actContainerTop">
		<div class="lbl"><liferay-ui:message key="activity" /></div>
		<div class="arrow arrowUp" onclick="ActivitiesPortlet.close();"></div>
		<div class="arrow arrowDown"  onclick="ActivitiesPortlet.open();"></div>
	</div>
	<div class="actContainerRpt">
	    <form id="<portlet:namespace/>fm" name="<portlet:namespace/>fm">
    		<input type="hidden" id="<portlet:namespace/><%=ActivitiesBrowserConstants.HPM_PROCESS_ID %>" 
    			name="<portlet:namespace/>processId">
    		<input type="hidden" id="<portlet:namespace/><%=ActivitiesBrowserConstants.HPM_ACTIVITIES_STATE %>" 
    			name="<portlet:namespace/>activitiesState"
    			value="<%=ActivitiesBrowserConstants.HPM_ACTIVITIES_TODO%>">
    		<div class="menu-activities">
    			<div id="menu-todo" class="menu-todo" onclick="<portlet:namespace />filterCommand('<%= ActivitiesBrowserConstants.HPM_ACTIVITIES_TODO%>');">
					<liferay-ui:message key="activity.todo" />
				</div>
				<div id="menu-pending" class="menu-pending" onclick="<portlet:namespace />filterCommand('<%= ActivitiesBrowserConstants.HPM_ACTIVITIES_PENDING%>');">
					<liferay-ui:message key="activity.pending" />
				</div>
    		</div>
    	</form>
	    
	    <div id="activities-browser-portlet-results-box"></div>
	</div>
	<div class="actContainerBottom"></div>



</div>

<script type="text/javascript" charset="utf-8">
	Liferay.on("search-process-for-activities", function(data) {
		
		if ($('#<portlet:namespace/><%=ActivitiesBrowserConstants.HPM_PROCESS_ID %>') != null) {
	        $('#<portlet:namespace/><%=ActivitiesBrowserConstants.HPM_PROCESS_ID %>').val(data.hpmProcessId);

	        KPeople.Common.load('activities-browser-portlet-results-box', '<%= activitiesViewURL.toString() %>', '<portlet:namespace/>fm');
	    }
	});
</script>

<script type="text/javascript">

function <portlet:namespace />filterCommand(cmd) {

	document.<portlet:namespace />fm.<portlet:namespace /><%= ActivitiesBrowserConstants.HPM_ACTIVITIES_STATE %>.value = cmd;
	//submitForm(document.<portlet:namespace />fm);
	KPeople.Common.load('activities-browser-portlet-results-box', '<%= activitiesViewURL.toString() %>', '<portlet:namespace/>fm');
	return false;
}
</script>

