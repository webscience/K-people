<%@page import="com.liferay.portal.model.Lock"%>
<%@page import="com.liferay.portlet.documentlibrary.util.DLUtil"%>
<%@page import="com.liferay.portal.service.LockLocalServiceUtil"%>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFileEntryConstants"%>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFileVersion"%>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFolder"%>
<%@page import="com.liferay.portal.kernel.util.DocumentConversionUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFileEntry"%>
<%@page import="com.liferay.portal.kernel.bean.BeanParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PropsKeys"%>
<%@page import="com.liferay.portal.kernel.util.PrefsPropsUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="it.webscience.kpeople.web.portlet.document.search.DocumentDisplayTerms"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/html/init.jsp" %>

<div id="document-browser-portlet">

	<div class="docContainerTop">
		<div class="lbl"><liferay-ui:message key="documents" /></div>
		<div class="arrow arrowUp" onclick="DocumentPortlet.close();"></div>
		<div class="arrow arrowDown"  onclick="DocumentPortlet.open();"></div>
	</div>
	<div class="docContainerRpt">
	    <div id="document-browser-portlet-results-box"></div>
	</div>
	<div class="docContainerBottom"></div>


    <form id="<portlet:namespace/>fm" name="<portlet:namespace/>fm">
    	<input type="hidden" id="<portlet:namespace/><%=DocumentDisplayTerms.HPM_PROCESS_ID %>" name="<portlet:namespace/>processId">
    </form>
</div>

<script type="text/javascript" charset="utf-8">
	Liferay.on("search-process-for-document", function(data) {
		
		if ($('#<portlet:namespace/><%=DocumentDisplayTerms.HPM_PROCESS_ID %>') != null) {
	        $('#<portlet:namespace/><%=DocumentDisplayTerms.HPM_PROCESS_ID %>').val(data.hpmProcessId);

	        KPeople.Common.load('document-browser-portlet-results-box', '<%= userViewURL.toString() %>', '<portlet:namespace/>fm');
	    }
	});
</script>

<%
if (uploadEnabled) {
	String hpmProcessId = "testHpmProcessId";
	String uploadProgressId = "dlFileEntryUploadProgress";
%>

	<portlet:actionURL var="editFileEntryURL">
	    <portlet:param name="_spage" value="/portlet_action/document-browser/edit" />
	</portlet:actionURL>
	
	<form action="<%= editFileEntryURL %>" enctype="multipart/form-data" method="post" name="<portlet:namespace />fm" onsubmit="<%= HtmlUtil.escape(uploadProgressId) %>.startProgress();">
		<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>"/>
		<aui:input name="uploadProgressId" type="hidden" value="<%= uploadProgressId %>" />
		<input name="<portlet:namespace />hpmProcessId" type="hidden" value="<%= hpmProcessId %>" />
		<aui:input name="file" type="file" />
		<input type="submit" value="Invia">
	</form>

	<liferay-ui:upload-progress
		id="<%= uploadProgressId %>"
		message="uploading"
		redirect=""
	/>
<%
}
%>