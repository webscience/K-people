<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="it.webscience.kpeople.service.processType.ProcessTypeServiceStub"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessDisplayTerms"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessSearchTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessSearch"%>
<%@ include file="/html/user/init.jsp" %>

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
    
</div>

<script type="text/javascript" charset="utf-8">
/*
jQuery("document").ready(function() {
	KPeople.Common.load('user-browser-portlet-user-box',
    '<%//= userViewURL.toString() %>');	
});
*/

Liferay.on("search-process", function() {
	jQuery("#user-browser-portlet-user-box").html("Processi trovati: 0");
});
</script>