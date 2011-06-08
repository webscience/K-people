<%@page import="it.webscience.kpeople.service.cross.UserServiceStub"%>
<%@page import="it.webscience.kpeople.service.pattern.PatternServiceStub"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ include file="/html/init.jsp" %>
<portlet:defineObjects />

<%
//	popolamento degli elementi per l'autocomplete
	String autoCompleteEl = "";
	UserServiceStub.User[] usersStub = (UserServiceStub.User[]) request.getAttribute("allUsers");
	for (UserServiceStub.User userStub : usersStub) {
	    if (userStub != null && userStub.getEmail() != null) {
	        if (!autoCompleteEl.isEmpty()) {
	            autoCompleteEl += ",";
	        }
	        autoCompleteEl +=
	        	"{value: \"" + userStub.getEmail() + "\",label: \"" + userStub.getScreenName() + "\",}";
	    }
	}
%>

<script>
	var projects = [<%= autoCompleteEl %>];

	$(function() {
		NewPattern.initPatternAutocomplete("#patternProvider");
		NewPattern.initPatternAutocomplete("#patternUserCc");

		//$("#dueDateString").datepicker({minDate: 1, dateFormat: 'dd/mm/yy'});
		$("#dueDateString").datepicker({dateFormat: 'dd/mm/yy'});
		NewPattern.addAttachment();
	});
</script>

<!-- spostare nel css -->
<style type="text/css">

		.pattern-creation-details{
			padding-bottom: 30px;
		}
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
        }       
</style>
<!-- fine spostare nel css -->

<% 
String command = ParamUtil.getString(request, Constants.CMD);
String hpmProcessId = (String) request.getAttribute(KpeoplePortalConstants.HPM_PROCESS_ID);
String idPatternType = String.valueOf(request.getAttribute("idPatternType"));
String patternName = (String) request.getAttribute("patternName");
String redirectDettaglio = (String) request.getAttribute("redirectDettaglio");


Date currentDate = new Date();
String data = dateFormatDateTime.format(currentDate);
String dueDate = "";

String curr_user = themeDisplay.getUser().getFullName();
String curr_userHpmId = themeDisplay.getUser().getEmailAddress();

%>

<script type="text/javascript">
var createPatternRichiediContributoNs = '<portlet:namespace />';

function <portlet:namespace />createPattern(cmd) {
	document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;
	submitForm(document.<portlet:namespace />fm);
}
</script>


<div><h2><%=patternName %></h2></div>

<portlet:actionURL var="sendPatternURL">
		<portlet:param name="_spage" value="/portlet_action/pattern-browser/create-pattern/richiedi-contributo" />
		<portlet:param name="<%=KpeoplePortalConstants.HPM_PROCESS_ID %>" value="<%=hpmProcessId %>" />
		<portlet:param name="idPatternType" value="<%=idPatternType %>" />
		<portlet:param name="redirectDettaglio" value="<%=redirectDettaglio %>" />

</portlet:actionURL>
  <liferay-ui:error exception="<%=KpeopleProcessNameException.class %>" message="please-enter-a-valid-name"/>
	    	
<form enctype="multipart/form-data" action="<%= sendPatternURL %>" method="post" name="<portlet:namespace />fm" id="<portlet:namespace />fm" >
	<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
	
	<div class="pattern-creation-details">
		<div class="date-details details">
			<span><liferay-ui:message key="process-open" /></span> 
			<span><%=data%></span> 
			
			<span id="label"><liferay-ui:message key="process-creation-due" /></span>
			<span> 	
				<input id="dueDateString" onblur="NewPattern.checkDueDate();" name="<portlet:namespace />dueDateString" type="text" value="<%= dueDate %>" />
			</span>
		</div>
	</div>

	<div class="pattern-creation-details">	
		<div class="user-details details">
			<span><liferay-ui:message key="process-created-by" /></span>
			<span><%=curr_user %></span>
			
			<span id="label"><liferay-ui:message key="pattern.user.to" /></span>
			<span> 
				<input id="patternProvider-lbl" onblur="NewPattern.checkSelectedItem(this)"/>
				<input type="hidden" id="patternProvider" name="<portlet:namespace />patternProvider"/>
			</span>

			<span id="label"><liferay-ui:message key="pattern.user.cc" /></span>
			<span> 	
				<input id="patternUserCc-lbl" onblur="NewPattern.checkSelectedItem(this)"/>
				<input type="hidden" id="patternUserCc" name="<portlet:namespace />patternUserCc"/>
			</span>
		</div>
				
	</div>


	<div class="pattern-creation-details">
		<div class="title-details details"> 
			
			<span><liferay-ui:message key="title" /></span>
			<span id="text-content"> 	
				<input size="100" name="<portlet:namespace />patternTitle" type="text" value="" maxlength="100"/>
			</span>
		</div>
	</div>
	
	<div class="pattern-creation-details">
		<div class="description-details details"> 
			
			<span><liferay-ui:message key="pattern.request.type" /></span>
			<span>
				<select name="<portlet:namespace />requestType"> 	
					<option value=""></option>
						<%
        		            List<String> requestType = new ArrayList<String>();
							requestType.add(LanguageUtil.get(pageContext, "document"));
							requestType.add(LanguageUtil.get(pageContext, "activity.request.type.informazioni"));
							requestType.add(LanguageUtil.get(pageContext, "Altro"));
                        	String idRequestType = "";

                        	for (int i = 0; i < requestType.size(); i++) {
                           
                           		out.println(
                                	"<option value=\"" + requestType.get(i) + "\">" + 
                                		requestType.get(i) +
                                	"</option>"); 
                        	}
                    	%>
                </select>
			</span>
		</div>
	</div>

	<div class="pattern-creation-details">
		<div class="type-details details"> 
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

	<div>
		<input type="button" value="<liferay-ui:message key="send" />" onclick="<portlet:namespace />createPattern('<%=Constants.ADD %>');">
		<input type="button" value="<liferay-ui:message key="cancel" />" onclick="location.href='<%=redirectDettaglio%>'">
	</div>
</form> 