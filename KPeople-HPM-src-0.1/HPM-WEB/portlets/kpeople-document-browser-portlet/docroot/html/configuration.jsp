<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

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
			</td>
			<td>
				Inserire nel friendly url di dettaglio solo l'id della <br> 
				portlet di destinazione (ultimi 4 caratteri).<br>
				E' necessario per entrambi i campi inserire all'inizio il carattere /
			</td>
		</tr>
		
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>

		<tr>
			<td>
				<liferay-ui:message key="destination-page" />
			</td>
			<td>
				<input type="text" class="lfr-input-text" name="<portlet:namespace />destinationPage" value="<%= destinationPageParam %>" />
				<liferay-ui:icon-help message="friendly-url-help-msg" />
			</td>
		</tr>


		<tr>
			<td>
				<liferay-ui:message key="friendly-url-dettaglio" />
			</td>
			<td>
				<input type="text" class="lfr-input-text" name="<portlet:namespace />friendlyUrlDettaglio" value="<%= friendlyUrl %>" />
				<liferay-ui:icon-help message="friendly-url-help-msg" />
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
				<input  checked="checked" id="processListView" type='radio' name='<portlet:namespace />selectedView' value='processListView'>
				<liferay-ui:message key="process-list-view " />
				
				<input  checked="checked" id="processEditView" type='radio' name='<portlet:namespace />selectedView' value='processEditView'>
				<liferay-ui:message key="process-edit-view" />
			
				
			</td>
		</tr>
		
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm1);" />

</form>