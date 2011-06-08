<%@page import="it.webscience.kpeople.event.login.KpeopleUserUtil"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="it.webscience.kpeople.service.processType.ProcessTypeServiceStub"%>
<%@page import="it.webscience.kpeople.service.process.ProcessServiceStub"%>
<%@page import="it.webscience.kpeople.service.process.KpeopleProcessNameException"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@page import="java.util.Date"%>

<%@ include file="/html/init.jsp" %>

<%
ProcessTypeServiceStub.ProcessType[] processTypes = (ProcessTypeServiceStub.ProcessType[]) request.getAttribute("processTypes");
//Date currentDate = new Date();

// get a calendar instance, which defaults to "now"
Calendar calendar = Calendar.getInstance();
    
// get a date to represent "today"
Date currentDate = calendar.getTime();

// add one day to the date/calendar
calendar.add(Calendar.DAY_OF_YEAR, 1);
    

    
String curr_user = themeDisplay.getUser().getFullName();


ProcessServiceStub.Process currProcess = (ProcessServiceStub.Process) request.getAttribute(ProcessBrowserConstants.CURR_PROCESS);
String operation = null;
String currentDateString = "";
String dueDateString = "";

if (currProcess!=null) {
    currentDateString = dateFormatDateTime.format(currProcess.getDateCreated());
    if (currProcess.getDateDue()!=null) {
        dueDateString = dateFormatDateTime.format(currProcess.getDateDue());
    }
} else {
    currentDateString = dateFormatDateTime.format(currentDate);
    dueDateString = "";
}





String returnUrl = null;
if (!Validator.isNull(destinationPageParam) && !Validator.isNull(friendlyUrl) && Validator.isNotNull(currProcess)) {
    returnUrl = destinationPageParam + "-/process_browser" + friendlyUrl + currProcess.getHpmProcessId();
} else {
    PortletURL portletURL = renderResponse.createRenderURL();
    portletURL.setWindowState(LiferayWindowState.NORMAL);
    returnUrl = portletURL.toString();
}

%>

<script type="text/javascript">

function <portlet:namespace />executeCommand(cmd, message) {

	document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;
	if (message != null) {
		close=confirm(message);
		  if(close){
			  submitForm(document.<portlet:namespace />fm);
		  }

	} else {
		submitForm(document.<portlet:namespace />fm);
	}
}


jQuery(
		function() {
			//$("#dueDateString").datepicker({minDate: 1, dateFormat: 'dd/mm/yy'});
			$("#dueDateString").datepicker({dateFormat: 'dd/mm/yy'});
			
			jQuery('#public').attr('checked', "checked");
			<%
			if (currProcess!=null) {
				if (currProcess.get_private()==true) { %>
					jQuery('#public').attr('checked', "");
					jQuery('#private').attr('checked', "checked");
				<%}

			}%>
		}
	);

</script>

<!-- <div  type="text" id="datepicker"></div> -->




<div class="process-browser-portlet" id="insert-process">
<h1>
	<div>
		<%if (currProcess == null) { %>
			<liferay-ui:message key="new-process-created" />
		<%} else { %>
			<liferay-ui:message key="modify-process-view" />
		<%} %>
	</div>
</h1>

<portlet:actionURL var="insertProcessURL">
		<portlet:param name="_spage" value="/portlet_action/process-browser/manage-process" />
		<%if (currProcess != null) { %>
		<portlet:param name="hpmProcessId" value="<%=currProcess.getHpmProcessId() %>" />
		<%} %>
</portlet:actionURL>

	 <liferay-ui:error exception="<%=KpeopleProcessNameException.class %>" message="please-enter-a-valid-name"/>
	    	
	 		
	
	 <form action="<%= insertProcessURL %>" method="post" name="<portlet:namespace />fm" id="<portlet:namespace />fm" >
	    <input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
	    
	    <div class="insert-row">
			<div class="insert-columnSx">
				<liferay-ui:message key="process-name" />
			</div>
			<div>
			<%  String name ="";
				if (currProcess != null) { name = currProcess.getName(); } 
			%>
				<input label="processName" name="processName" type="text" 
					value="<%=name %>" />
			</div>
		</div>
		
		
		
		<div class="insert-row">
			<div class="insert-columnSx">
				<liferay-ui:message key="process-type" />
			</div>
			<div class="">
				<select name="selectType">
				        <%
				        		String processType = "";
								if (currProcess != null) { processType = currProcess.getProcessType().getIdProcessType()+""; } 
				        		
								for (int i = 0; i < processTypes.length; i++) {
								    String selected = "";
					                   if (processType.equals(processTypes[i].getIdProcessType()+"")) {
					                                       selected = "selected=\"selected\"";
					                       }
								    out.println(
							            "<option " + selected + " value=\"" + processTypes[i].getIdProcessType() + "\">" + 
							            	LanguageUtil.get(pageContext, processTypes[i].getName()) +
							            "</option>" ); 
								}
							%>
				</select>
			</div>
		</div>
		

		<div class="insert-row">
			<div class="insert-columnSx">
				<liferay-ui:message key="process-managed-by" />
				
			</div>
			
			<div >
				<!-- <input label="" name="" type="text" value="" /> -->
				<%  String owner ="";
					if (currProcess != null) { 
					    //owner = currProcess.getOwner().getScreenName();
					    owner = curr_user;
					    if (currProcess.getVisibility() == 1) {
					        owner += "<b>&nbsp;(" + "Tu" + ")</b>";
						}
					} else {
					    owner = curr_user;
					}
				%>
				<a href="">
					<%=owner %>
				</a>
			</div>
		</div>
		
	<!-- Sezione per la visibilità del processo
		<div class="insert-row">
			<div class="insert-columnSx">
				<liferay-ui:message key="process-restriction" />
				
			</div>
			<div >
					
				<input id="public" type='radio' name='visibilityRadio' 
					value='public'>
				<liferay-ui:message key="process-all-visibility" />
				<input id="private" type='radio' name='visibilityRadio' 
					value='privato'>
				<liferay-ui:message key="process-private" />
				
			</div>
		</div>
 	-->
		<div class="insert-row">
			<div class="insert-columnSx">
				<liferay-ui:message key="process-creation-date" />
			</div>
			<div >
				<%= currentDateString %>
				<input label="" name="" type="hidden" value="<%= currentDateString %>" readonly="readonly"/>
			</div>
		</div>

		<div class="insert-row">
			<div class="insert-columnSx">
				<liferay-ui:message key="process-creation-due" />
			</div>
			
			<div >
				
				<input label="" onblur="NewProcess.checkDueDate();" name="dueDateString" id="dueDateString" type="text" value="<%= dueDateString %>" />
			</div>
		</div>


		<div class="insert-row">
			<div class="insert-columnSx">
				<liferay-ui:message key="description" />
			</div>
			<div>
				<% String description = "";
				   if (currProcess != null) { 
				    	description = currProcess.getDescription(); 
				   }
				%>
					<textarea cols="60" rows="6" name="description" id="description"><%=description%></textarea>
			</div>
		</div>
		
		<div>
		    <%if (currProcess == null) { %>
		    	<div id="<portlet:namespace/>switchAdvBtn" class="switchBtn">
				
					<input type="button" value="<liferay-ui:message key="save" />" 
							onclick="<portlet:namespace />executeCommand('<%= Constants.ADD%>', null);">
					 
			    
			<%} else { %>
				<div id="update-button" class="button-holder">
		       	   <input type="hidden" 
		       	   name="processId"
		       	   value="<%= currProcess.getIdProcess() %>"/>
		       	   
		       	   
					<input type="button" value="<liferay-ui:message key="modify-process-view" />" 
							onclick="<portlet:namespace />executeCommand('<%= Constants.UPDATE%>', null);">
					

					<input type="button" value="<liferay-ui:message key="process-close" />" 
							onclick="<portlet:namespace />executeCommand('<%= ProcessBrowserConstants.CLOSE_PROCESS%>', '<liferay-ui:message key="process.close.confirm" />');">
					

				
				
			<%} %>
			
					<input type="button" value="<liferay-ui:message key="cancel" />" 
							onclick="location.href='<%=returnUrl %>'">

			</div>
			
		</div>
	</form>

</div>