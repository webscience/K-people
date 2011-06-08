<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="it.webscience.kpeople.service.processType.ProcessTypeServiceStub"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/html/init.jsp" %>

<%
String processi = renderRequest.getParameter("processi");
if (processi != null) {
    processi = "";
}
%>
<div id="user-browser-portlet">

    <div id="user-browser-portlet-user-box">
    <!-- questo div viene caricato tramite ajax -->
    <img src="<%= themeDisplay.getPathThemeImages() %>/application/loading_indicator.gif" alt="loading" title="loading..."/>
    </div>
    
    <form id="<portlet:namespace/>fm" name="<portlet:namespace/>fm">
    	<input type="hidden" id="<portlet:namespace/>processid" name="<portlet:namespace/>processid">
    	<input type="hidden" id="<portlet:namespace/>select" name="<portlet:namespace/>select" value="Cogname">
    </form>
</div>

<script type="text/javascript" charset="utf-8">
/*$(document).ready(function() {
	KPeople.Common.load('user-browser-portlet-user-box',
    '<%= userViewURL.toString() %>', '<portlet:namespace/>fm');	
});*/

Liferay.on("search-process-for-users", function(data) {
	
	if ($('#<portlet:namespace/>processid') != null) {
        $('#<portlet:namespace/>processid').val(data.hpmProcessId);
        
        KPeople.Common.load('user-browser-portlet-user-box', '<%= userViewURL.toString() %>', '<portlet:namespace/>fm');
    }
});

var user_initialized = true;
</script>