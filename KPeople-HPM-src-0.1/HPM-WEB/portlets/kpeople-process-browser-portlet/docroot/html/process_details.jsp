<%@page import="it.webscience.kpeople.KpeoplePortalConstants"%>
<%@page import="it.webscience.kpeople.web.portlet.process.util.EventBrowserConstants"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.EventDisplayTerms"%>
<%@ include file="/html/init.jsp"%>

<%
    String row = request.getParameter(KpeoplePortalConstants.HPM_PROCESS_ID);
    ProcessServiceStub.Process processResult = null;
    String hpmProcessId = null;
    //String profileURL= PrefsPropsUtil.getString(company.getCompanyId(), "default.profile.path");
    
    PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);
	portletURL.setParameter("_spage", "/portlet_action/events-update-browser/event-update");
	
	String patternListURL = null;
	String curr_userHpmId = themeDisplay.getUser().getEmailAddress();
	
	long groupId = themeDisplay.getLayout().getGroupId();
	
%>

<form id="<portlet:namespace/>eventsPerProcessFm" name="<portlet:namespace/>eventsPerProcessFm">
   	<input type="hidden"
   		   value="<%=row %>"
   		   id="<portlet:namespace/><%=EventDisplayTerms.PROCESS_ID %>"
   		   name="<portlet:namespace/><%=EventDisplayTerms.PROCESS_ID %>">
</form>

<div class="process-browser-portlet">
	<div class="process-details">
		<%
		    if (renderRequest.getAttribute(ProcessBrowserConstants.ATTR_PROCESS_FOUND) != null) 
		    {
	
	        	processResult =
	                (ProcessServiceStub.Process) renderRequest
	                        .getAttribute(ProcessBrowserConstants.ATTR_PROCESS_FOUND);     
		               
		        String processName = processResult.getName();
		        hpmProcessId = processResult.getHpmProcessId();
		
		        int ownerId = processResult.getOwner().getIdUser();
		        
		
		        String processDescription = processResult.getDescription();
		
		        Date processDateCreated = processResult.getDateCreated();
		        Date processDateDue = processResult.getDateDue();
		
		        Boolean isActive = processResult.getActive();
		
		        ProcessServiceStub.Keyword[] keywords =
		                processResult.getKeywords();
		
		        String data = dateFormatDateTime.format(processDateCreated);
		
		        String imgVisibility = "";
		        if (processResult.getVisibility() == 0)
		            imgVisibility = "visibility_public.png";
		        else if (processResult.getVisibility() == 1)
		            imgVisibility = "visibility_owner.png";
		        else if (processResult.getVisibility() == 2)
		            imgVisibility = "visibility_enabled.png";
		        else if (processResult.getVisibility() == 3)
		            imgVisibility = "visibility_private.png";
		
		        String active =
		                LanguageUtil.get(pageContext,
		                        "find-processes.state.open");
		        if (!processResult.getActive()) {
		            active =
		                    LanguageUtil.get(pageContext,
		                            "find-processes.state.closed");
		        }

		        String diffDaysGg = "";
		        String statusDay2Class = "statusDays2Negativo";
		        if (processResult.getDateDue() != null) {
		            long diffDays =
		                    (Long) renderRequest
		                            .getAttribute(ProcessBrowserConstants.DIFF_DAYS);
		            if (diffDays <= 0) {
		                statusDay2Class = "statusDays2Positivo";
		            }
		
		            diffDaysGg = diffDays + " g";
		        }
		        
		        String processOwner = "";
			if (processResult.getOwner() != null) {
			    if (processResult.getOwner().getScreenName() != null) {
			        processOwner = processResult.getOwner().getScreenName();
			    } else if (processResult.getOwner().getHpmUserId() != null) {
			        processOwner = processResult.getOwner().getHpmUserId();
			    }
			}

			if (processResult.getVisibility() == 1) {
			    processOwner += "<b>&nbsp;(" + LanguageUtil.get(pageContext,"process.tu") + ")</b>";
			}
		%>

		<div id="header">
	
			<div class="visibilityDiv"><img alt=""
				src="/kpeople-process-browser-portlet/images/<%=imgVisibility%>">
			</div>
			
			<div class="process-title"><%=processName%></div>
			
			<div class="statusDays">
				<div class="statusDays1"><%=active%></div> 
				<div class="statusDays2 <%=statusDay2Class%>"><%=diffDaysGg%></div>
			</div>
		</div>

		<div id="details">
			<span><liferay-ui:message key="process-open" /> <%=data%></span>,  
			<span><liferay-ui:message key="process-created-by" /> <%=processOwner%></span>,
			
			<%
			if (processDateDue != null) {
			%>
				<span><liferay-ui:message key="process-creation-due" /> <%=dateFormatDateTime.format(processDateDue)%></span>
			<%
			}
			%>
			
			<div><%=processDescription%></div>
		</div>
		
		<%
		    }
		%>
	</div>
<portlet:actionURL var="editProcessUrl" windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>">
        <portlet:param name="_spage" value="/portlet_action/process-browser/manage-process" />
         <portlet:param name="<%=KpeoplePortalConstants.HPM_PROCESS_ID %>" value="<%=hpmProcessId %>" />
         <portlet:param name="<%=Constants.CMD %>" value="<%=Constants.EDIT %>" />
</portlet:actionURL>


	<%if (processResult.getActive()) {%>
		<%if (processResult.getOwner().getHpmUserId().equals(curr_userHpmId) || permissionChecker.isCommunityOwner(groupId)) { %>
			<input type="button" value="<liferay-ui:message key="modify-process-view" />"
					onclick="location.href = '<%= editProcessUrl%>'" href="#" /> 
			
   			<input type="button" value="<liferay-ui:message key="process-close" />"
					onclick="location.href = '<%= editProcessUrl%>'" href="#" /> 
		<%} %>
		<!-- aggiunge il link alla creazione nuovo pattern -->	
		<%if (!Validator.isNull(destinationPagePattern) && !Validator.isNull(friendlyUrlPattern)) {
		    patternListURL = destinationPagePattern + "-/pattern_browser" + friendlyUrlPattern + processResult.getHpmProcessId();
		%>
		
			<input type="button" value="<liferay-ui:message key="pattern.new" />"
					onclick="location.href = '<%=patternListURL %>'+'?redirectDettaglio=' + window.location.href" href="#" /> 		
			<liferay-ui:icon image="help" message="pattern.help.creation.message" />
	   	<%}
	} %>

	

	<!-- input type="button" value ="Utenti" onclick="javascript:search_users('<%//= hpmProcessId %>')"/-->
</div>

<!-- Questo div verrà caricato tramite ajax -->
<div id="update-events-associated-at-the-process">
    	<!-- questo div viene caricato tramite ajax -->
    	<img src="<%= themeDisplay.getPathThemeImages() %>/application/loading_indicator.gif" alt="loading" title="loading..."/>
</div>

<script type="text/javascript" charset="utf-8">
	function fire_communication(type, hpmProcessId) {
		Liferay.fire(type, {
	    		'hpmProcessId': hpmProcessId
	    	}
	    );
	}
	
	Liferay.on("portletReady", function(data) {
		var portletId = data.portletId;
		var portlet = data.portlet;

		if(portletId.indexOf('kpeopleuserbrowser_WAR_kpeopleuserbrowserportlet') > -1 ) {
			fire_communication("search-process-for-users" , '<%= hpmProcessId %>');
		}
		if(	portletId.indexOf('kpeopledocumentbrowser_WAR_kpeopledocumentbrowserportlet') > -1 ) {
			fire_communication("search-process-for-document" , '<%= hpmProcessId %>');
		}
		if(	portletId.indexOf('kpeopleactivitiesbrowser_WAR_kpeopleactivitiesbrowserportlet') > -1) {
			fire_communication("search-process-for-activities" , '<%= hpmProcessId %>');
		}
		if(	portletId.indexOf('kpeoplereport_WAR_kpeoplereportportlet') > -1) {
			fire_communication("search-process-for-report" , '<%= hpmProcessId %>');
		}
		
	});
	
        $(document).ready(function () {
        	KPeople.Common.load('update-events-associated-at-the-process',
        	'<%= portletURL %>', '<portlet:namespace/>eventsPerProcessFm');
        });
	
	
</script>