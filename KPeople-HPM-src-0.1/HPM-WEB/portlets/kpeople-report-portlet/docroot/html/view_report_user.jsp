<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="it.webscience.kpeople.web.portlet.report.action.ReportBrowserConstants"%>
<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>
<%@page import="it.webscience.kpeople.service.processType.ProcessTypeServiceStub"%>
<%@page import="it.webscience.kpeople.service.process.ProcessServiceStub"%>
<%@page import="it.webscience.kpeople.service.cross.UserServiceStub"%>


<%
ProcessServiceStub.Process[] processes = (ProcessServiceStub.Process[]) request.getSession().getAttribute(ReportBrowserConstants.ATTR_PROCESSES_FOUND);
ProcessTypeServiceStub.ProcessType[] processTypes = (ProcessTypeServiceStub.ProcessType[]) request.getSession().getAttribute("processTypes");
UserServiceStub.User[] users = (UserServiceStub.User[]) request.getSession().getAttribute(ReportBrowserConstants.ALL_USERS);

String currUser =  themeDisplay.getUser().getEmailAddress();

String processId = ParamUtil.getString(request, ReportBrowserConstants.PROCESS);
String filterType = ParamUtil.getString(request, "ordinaPerRadio");
String typeId = ParamUtil.getString(request, ReportBrowserConstants.TYPE);
String userId = ParamUtil.getString(request, ReportBrowserConstants.USER);
if (Validator.isNull(userId)) {
    userId = currUser;
}

String showFilter = preferences.getValue(ReportBrowserConstants.SHOW_FILTER, StringPool.BLANK); 

%>

<script type="text/javascript">

jQuery(
		function() {
			
			jQuery('#<portlet:namespace />process_radio').attr('checked', "checked");
			jQuery('#<portlet:namespace />type_select').attr('disabled', "disabled");
			<%
			if (filterType!=null) {
				if (filterType.equals(ReportBrowserConstants.FILTER_BY_TYPE)) { %>
					jQuery('#<portlet:namespace />process_radio').attr('checked', "");
					jQuery('#<portlet:namespace />process_select').attr('disabled', "disabled");
					jQuery('#<portlet:namespace />type_radio').attr('checked', "checked");
					jQuery("#<portlet:namespace />type_select").removeAttr('disabled');
				<%} 

			}%>
		}
	);

</script>


<portlet:renderURL var="filterReportURL">
    <portlet:param name="_spage" value="/portlet_action/report/view" />
</portlet:renderURL>

<div id="<portlet:namespace/>report-portlet-results-box" class="report">
	<div  id="<portlet:namespace/>actContainerRpt">
	
	<%if (showFilter.equals(ReportBrowserConstants.SHOW_FILTER_TRUE)) { %>
		<div class="ordinaPerFilter">
			<form action="<%= filterReportURL %>" method="post" name="<portlet:namespace />fm" id="<portlet:namespace />fm" >
				<div class="reportFilter">  
					<div class="report_radio">              	
                  		<input onchange="KPeople.ReportSearch.checkRadio('<portlet:namespace />', this);" id="<portlet:namespace />process_radio"  type='radio' name='<%=ReportBrowserConstants.ORDINA_PER_RADIO%>' 
                  			value='<%=ReportBrowserConstants.FILTER_BY_PROCESS%>'> <liferay-ui:message key="process-name" />
					</div>
					<div class="report_select">    
               			<select id="<portlet:namespace/>process_select" name="<%=ReportBrowserConstants.PROCESS %>" >
                	    
                	    	<option value='<%=ReportBrowserConstants.ALL%>'><liferay-ui:message key="all" /></option>
	                   		 	<%

	                        		for (int i = 0; i < processes.length; i++) {
	                        	   		String selected = "";
				                   		if (processId.equals(processes[i].getHpmProcessId()+"")) {
				                                       selected = "selected=\"selected\"";
				                       	}	          
	                            		out.println(
	                                		"<option  " + selected + " value=\"" + processes[i].getHpmProcessId() + "\">" + 
	                                    		processes[i].getName() +
	                                		"</option>"); 
	                       		 	}
	                    		%>
               			</select>
					</div>
				</div>
                
                <div class="reportFilter">
                	
					<span><liferay-ui:message key="user" /></span>
        		    <select id="<portlet:namespace />user_select" name="<%=ReportBrowserConstants.USER %>" >
	                	    
	               		 
		                   		 <%
		                        	for (int i = 0; i < users.length; i++) {
		                            	String selected = "";
		                            	String selectedUser = users[i].getEmail();
		                            	if (userId.equals(selectedUser)) {
		                                       selected = "selected=\"selected\"";
		                       			}	 
		                            
		                            	out.println(
		                                	"<option " + selected + " value=\"" + users[i].getEmail() + "\">" + 
		                                    	users[i].getScreenName() +
		                                	"</option>"); 
		                       		 }
		                    	%>
	                	    
                	</select>
                </div>				
				<div style="clear: both;"></div>
				<div class="reportFilter">
					<div class="report_radio">  
                		<input onchange="KPeople.ReportSearch.checkRadio('<portlet:namespace />', this);" id="<portlet:namespace />type_radio"  type='radio' name='<%=ReportBrowserConstants.ORDINA_PER_RADIO%>' 
                			value='<%=ReportBrowserConstants.FILTER_BY_TYPE%>'> <liferay-ui:message key="process-type" />   
					</div>
					
					<div class="report_select"> 
        		    	<select id="<portlet:namespace/>type_select" name="<%=ReportBrowserConstants.TYPE %>" >
	                	    
	               		 	<option value='<%=ReportBrowserConstants.ALL%>'><liferay-ui:message key="all" /></option>
		                   		 <%
		                        	for (int i = 0; i < processTypes.length; i++) {
		                            	String selected = "";
		                            	if (typeId.equals(processTypes[i].getName()+"")) {
		                                       selected = "selected=\"selected\"";
		                       }	 
		                            
		                            	out.println(
		                                	"<option " + selected + " value=\"" + processTypes[i].getName() + "\">" + 
		                                    	processTypes[i].getName() +
		                                	"</option>"); 
		                       		 }
		                    	%>
	                	    
                		</select>
                	</div>
                </div>
                <div style="clear: both;"></div>
                <div class="reportFilter">
     				<input type="submit"  value="<liferay-ui:message key="filter" />" >
        		</div>
        	</form>
        </div>
	<%} %>
		
		<iframe alt="<%= alt %>" border="<%= border %>" bordercolor="<%= bordercolor %>" 
			frameborder="<%= frameborder %>" height="<%= iframeHeight %>" hspace="<%= hspace %>" 
			id="reportIframe" longdesc="<%= longdesc%>" name="<portlet:namespace />iframe" 
			onload="<portlet:namespace />monitorIframe(); <portlet:namespace />resizeIframe();" 
			scrolling="<%= scrolling %>" src="<%= iframeSrc %>" vspace="<%= vspace %>" width="<%= width %>">
				
			<%= LanguageUtil.format(pageContext, "your-browser-does-not-support-inline-frames-or-is-currently-configured-not-to-display-inline-frames.-content-can-be-viewed-at-actual-source-page-x", iframeSrc) %>
		</iframe>
	</div>


</div>