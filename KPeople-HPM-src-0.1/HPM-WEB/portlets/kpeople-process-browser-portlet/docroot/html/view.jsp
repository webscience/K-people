<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="it.webscience.kpeople.service.processType.ProcessTypeServiceStub"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessDisplayTerms"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessSearchTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessSearch"%>
<%@ include file="/html/init.jsp" %>



<% if (selectedView.equals(ProcessBrowserConstants.PROCESS_EDIT_VIEW)) { %>
		
	<%@ include file="/html/view_edit.jsp" %>
	
<% } else { %>
	<%@ include file="/html/view_process_list.jsp" %>
<% } %>
