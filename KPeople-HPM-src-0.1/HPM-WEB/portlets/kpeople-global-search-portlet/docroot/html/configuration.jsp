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
            </td>
        </tr>


        <tr>
            <td>
                <liferay-ui:message key="friendly-url-dettaglio" />
            </td>
            <td>
                <input type="text" class="lfr-input-text" name="<portlet:namespace />friendlyUrlDettaglio" value="<%= friendlyUrl %>" />
            </td>
        </tr>
        
        <tr>
            <td colspan="2">&nbsp;</td>
        </tr>
        
        <tr>
            <td>
                <liferay-ui:message key="destination-page-pattern" />
            </td>
            <td>
                <input type="text" class="lfr-input-text" name="<portlet:namespace />destinationPagePattern" value="<%= destinationPagePattern %>" />
            </td>
        </tr>


        <tr>
            <td>
                <liferay-ui:message key="friendly-url-lista-pattern" />
            </td>
            <td>
                <input type="text" class="lfr-input-text" name="<portlet:namespace />friendlyUrlPattern" value="<%= friendlyUrlPattern %>" />
            </td>
        </tr>
		
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm1);" />

</form>