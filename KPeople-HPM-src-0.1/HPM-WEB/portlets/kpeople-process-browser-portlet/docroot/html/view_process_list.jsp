<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="it.webscience.kpeople.service.processType.ProcessTypeServiceStub"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessDisplayTerms"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessSearchTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessSearch"%>

<portlet:renderURL var="searchURL" windowState="<%=LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="_spage" value="/portlet_action/process-browser/begin-search" />
</portlet:renderURL>

<div id="process-browser-portlet" class="kpeople-process-browser-portlet">

    <div id="process-browser-portlet-process-box">
    <!-- questo div viene caricato tramite ajax -->
    <img src="<%= themeDisplay.getPathThemeImages() %>/application/loading_indicator.gif" alt="loading" title="loading..."/>
    </div>
</div>

<script type="text/javascript" charset="utf-8">
	function search_process () {
	    KPeople.Common.load('process-browser-portlet-process-box',
	    '<%= searchURL %>', '<portlet:namespace />fm');
	};

	jQuery("document").ready(function() {
		KPeople.Common.load('process-browser-portlet-process-box',
	    '<%= processViewURL.toString() %>');	
	});
</script>

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

		if(	portletId.indexOf('kpeopleactivitiesbrowser_WAR_kpeopleactivitiesbrowserportlet') > -1) {
			fire_communication("search-process-for-activities" , '');
		}

		
	});
	
</script>