<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/html/init.jsp" %>

<liferay-portlet:renderURL portletConfiguration="true" varImpl="portletURL" />

<%
	String cur = ParamUtil.getString(request, "cur");
	
	PortletPreferences pp = renderRequest.getPreferences();
	String processFilter = preferences.getValue("process-filter", StringPool.BLANK);
	
	String onlyMyProcesses = "checked=\"checked\"";
	String allProcesses = "";
	if (processFilter.equals("allProcesses")) {
	    onlyMyProcesses = "";
		allProcesses = "checked=\"checked\"";
	}
%>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" id="<portlet:namespace />fm1" method="post" name="<portlet:namespace />fm1">
	<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= portletURL.toString() %>&<portlet:namespace />cur=<%= cur %>" />

	<table class="lfr-table" width="100%">
		<tr>
			<td>
			<liferay-ui:message key="portlet-id" />:
			</td>
			<td>
				<%=portletResource%>
			</td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="action.VIEW" />
			</td>
			<td>
				<input checked="checked" id="processListView" type='radio' name='<portlet:namespace />selectedView' value='processListView'>
				<liferay-ui:message key="process-list-view " />
				
				<input id="processEditView" type='radio' name='<portlet:namespace />selectedView' value='processEditView'>
				<liferay-ui:message key="process-edit-view" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="find-processes.preferences.my-all" />
			</td>
			<td>
				<input <%= onlyMyProcesses %> type='radio' name='<portlet:namespace />processFilter' value='onlyMyProcesses'>
				<liferay-ui:message key="find-processes.preferences.my-all.show-my-process" />
				
				<input <%= allProcesses %> type='radio' name='<portlet:namespace />processFilter' value='allProcesses'>
				<liferay-ui:message key="find-processes.preferences.my-all.show-all-processes" />
			</td>
		</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm1);" />

</form>