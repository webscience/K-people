<%@page import="it.webscience.kpeople.web.portlet.report.action.ReportBrowserConstants"%>
<%@ include file="/html/init.jsp" %>
<%
PortletPreferences pp = renderRequest.getPreferences();
String widthChart = preferences.getValue("widthChart", StringPool.BLANK);
String heightChart = preferences.getValue("heightChart", StringPool.BLANK);
type = preferences.getValue("reportType", StringPool.BLANK);
style = preferences.getValue("style", StringPool.BLANK);

String redirect = ParamUtil.getString(request, "redirect");
long groupId = themeDisplay.getLayout().getGroupId();

String showFilter = preferences.getValue(ReportBrowserConstants.SHOW_FILTER, StringPool.BLANK); 
if (Validator.isNull(showFilter)) {
    showFilter = ReportBrowserConstants.SHOW_FILTER_FALSE;
}
String htmlAttributes =
	"alt=" + alt + "\n" +
	"border=" + border + "\n" +
	"bordercolor=" + bordercolor + "\n" +
	"frameborder=" + frameborder + "\n" +
	"height-maximized=" + heightMaximized + "\n" +
	"height-normal=" + heightNormal + "\n" +
	"hspace=" + hspace + "\n" +
	"longdesc=" + longdesc + "\n" +
	"scrolling=" + scrolling + "\n" +
	"vspace=" + vspace + "\n" +
	"width=" + width + "\n";
%>



<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	
	<aui:select label="Tipo di report" name="reportType">
		<aui:option id="mittente" label="<%=LanguageUtil.get(locale, "report.comunicazioniDa")%>" 
			selected='<%= type.equals(ReportBrowserConstants.CHART_COMMUNICAZIONI_MITTENTE)%>' 
			value='<%= ReportBrowserConstants.CHART_COMMUNICAZIONI_MITTENTE%>'/>
		<aui:option id="destinatario" label="<%=LanguageUtil.get(locale, "report.comunicazioniA")%>" 
			selected='<%= type.equals(ReportBrowserConstants.CHART_COMMUNICAZIONI_DESTINATARIO)%>' 
			value='<%= ReportBrowserConstants.CHART_COMMUNICAZIONI_DESTINATARIO%>'/>
		<aui:option id="KPI" label="<%=LanguageUtil.get(locale, "report.KPI")%>" 
			selected='<%= type.equals(ReportBrowserConstants.KPI_PROCESS_XACTION)%>' 
			value='<%= ReportBrowserConstants.KPI_PROCESS_XACTION%>'/>
		<aui:option id="pivot" label="<%=LanguageUtil.get(locale, "report.pivot.process")%>" 
			selected='<%= type.equals(ReportBrowserConstants.PIVOT_PROCESS_XACTION)%>' 
			value='<%=ReportBrowserConstants.PIVOT_PROCESS_XACTION%>'/>
		<aui:option id="pivotCommunication" label="<%=LanguageUtil.get(locale, "report.pivot.communication")%>" 
			selected='<%= type.equals(ReportBrowserConstants.PIVOT_COMMUNICATION_XACTION)%>' 
			value='<%=ReportBrowserConstants.PIVOT_COMMUNICATION_XACTION%>'/>

	</aui:select>

	

	<c:if test="<%= permissionChecker.isCommunityOwner(groupId)%>">
		<div >
			<liferay-ui:message key="widthChart" />
		</div>
		<input label="widthChart" name="widthChart" type="text" value="<%=widthChart %>" />
		<div >
			<liferay-ui:message key="heightChart" />
		</div>
		<input label="heightChart" name="heightChart" type="text" value="<%=heightChart %>" />
			
			
		<aui:select label="Style" name="style">
			<aui:option id="normal" label="Normal " 
				selected='<%= style.equals("normal") %>' value='normal'/>
			<aui:option id="big" label="Big " 
				selected='<%= style.equals("big") %>' value='big'/>
		</aui:select>
	</c:if>

	<aui:field-wrapper label="<%=LanguageUtil.get(locale, "report.showFilter") %>" name="<%=ReportBrowserConstants.SHOW_FILTER %>">
		
		<aui:input checked="<%= showFilter.equals(ReportBrowserConstants.SHOW_FILTER_TRUE) %>"
			inlineLabel="right" name="<%=ReportBrowserConstants.SHOW_FILTER %>" 
			type="radio" value="<%=ReportBrowserConstants.SHOW_FILTER_TRUE %>" label='<%=LanguageUtil.get(locale, "true") %>' />
		
		<aui:input checked="<%= showFilter.equals(ReportBrowserConstants.SHOW_FILTER_FALSE) %>" 
			inlineLabel="right" name="<%=ReportBrowserConstants.SHOW_FILTER %>" 
			type="radio" value="<%=ReportBrowserConstants.SHOW_FILTER_FALSE %>" label='<%=LanguageUtil.get(locale, "false") %>'  />
	</aui:field-wrapper>
	

	<aui:fieldset label="advanced">
		<aui:input cssClass="lfr-textarea-container" name="htmlAttributes" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" value="<%= htmlAttributes %>" wrap="soft" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>




</aui:form>



<aui:script>
	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.POP_UP) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />src);
	</c:if>
</aui:script>