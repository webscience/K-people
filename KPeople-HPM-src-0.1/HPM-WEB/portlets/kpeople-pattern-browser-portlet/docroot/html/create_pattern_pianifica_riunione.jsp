
<%@page import="it.webscience.kpeople.service.pattern.PatternServiceStub"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ include file="/html/init.jsp" %>
<portlet:defineObjects />


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
int idPatternType = ParamUtil.getInteger(request, "idPatternType");
String patternName = (String) request.getAttribute("patternName");


Date currentDate = new Date();
String data = dateFormatDateTime.format(currentDate);
%>

<script type="text/javascript">
function <portlet:namespace />createPattern(cmd) {

	document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;
	submitForm(document.<portlet:namespace />fm);
}
</script>


<div><h2><%=patternName %></h2></div>


