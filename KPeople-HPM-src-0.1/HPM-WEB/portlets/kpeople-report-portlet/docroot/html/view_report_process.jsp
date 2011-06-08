<%@page import="it.webscience.kpeople.web.portlet.report.action.ReportBrowserConstants"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>


<%
String kpiActionType = ParamUtil.getString(request, ReportBrowserConstants.FILTER_BY_KPI);
if (Validator.isNull(kpiActionType)) {
    kpiActionType = ReportBrowserConstants.KPI_PROCESS_XACTION;
}

%>

<script type="text/javascript">

jQuery(
		function() {
			
			<%
			if (kpiActionType!=null) {
				if (kpiActionType.equals(ReportBrowserConstants.KPI_PROCESS_XACTION_APP)) { %>
					jQuery('#<portlet:namespace />app').attr('selected', "selected");
				<%} 

				if (kpiActionType.equals(ReportBrowserConstants.KPI_PROCESS_XACTION_BDG)) { %>
					jQuery('#<portlet:namespace />bdg').attr('selected', "selected");
				<%} 
				
				if (kpiActionType.equals(ReportBrowserConstants.KPI_PROCESS_XACTION_PRG)) { %>
					jQuery('#<portlet:namespace />prg').attr('selected', "selected");
				<%} 
				
				
			}%>
		}
	);

</script>


<portlet:renderURL var="filterReportURL2">
    <portlet:param name="_spage" value="/portlet_action/report/view" />
</portlet:renderURL>



<div id="<portlet:namespace/>report-portlet-results-box" class="report">
	<div  id="<portlet:namespace/>actContainerRpt">
		<div class="ordinaPerFilter">
			<form action="<%=filterReportURL2 %>" method="post" name="<portlet:namespace />fm" id="<portlet:namespace />fm" >
				 <div class="reportFilter">
				 	<div class="report_radio">
				 		<liferay-ui:message key="process-type" /> 
                	</div>	
                	<div class="report_select"> 				
						<select label="Tipo di report" name="<%=ReportBrowserConstants.FILTER_BY_KPI%>">
							<option 
								id="<portlet:namespace />all" 
								value='<%= ReportBrowserConstants.KPI_PROCESS_XACTION %>'> KPI All </option>
							<option 
								id="<portlet:namespace />app" 
								value='<%= ReportBrowserConstants.KPI_PROCESS_XACTION_APP %>'> KPI Approvigionamento </option> 
							
							<option  
								id="<portlet:namespace />prg" 
								value='<%= ReportBrowserConstants.KPI_PROCESS_XACTION_PRG %>'> KPI Progetto </option>
			
						</select>
					</div>
                </div>				
				
                <div style="clear: both;"></div>
                <div class="reportFilter">
     				<input type="submit"  value="<liferay-ui:message key="filter" />" >
        		</div>
        	</form>
        </div>

		
		<iframe alt="<%= alt %>" border="<%= border %>" bordercolor="<%= bordercolor %>" 
			frameborder="<%= frameborder %>" height="<%= iframeHeight %>" hspace="<%= hspace %>" 
			id="reportIframe" longdesc="<%= longdesc%>" name="<portlet:namespace />iframe" 
			onload="<portlet:namespace />monitorIframe(); <portlet:namespace />resizeIframe();" 
			scrolling="<%= scrolling %>" src="<%= iframeSrc %>" vspace="<%= vspace %>" width="<%= width %>">
				
			<%= LanguageUtil.format(pageContext, "your-browser-does-not-support-inline-frames-or-is-currently-configured-not-to-display-inline-frames.-content-can-be-viewed-at-actual-source-page-x", iframeSrc) %>
		</iframe>
	</div>


</div>

