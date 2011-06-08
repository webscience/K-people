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
String kpiActionType = ParamUtil.getString(request, "kpiActionType");

%>




<div id="<portlet:namespace/>report-portlet-results-box" class="report">
	<div  id="<portlet:namespace/>actContainerRpt">
		
		
		<iframe alt="<%= alt %>" border="<%= border %>" bordercolor="<%= bordercolor %>" 
			frameborder="<%= frameborder %>" height="<%= iframeHeight %>" hspace="<%= hspace %>" 
			id="reportIframe" longdesc="<%= longdesc%>" name="<portlet:namespace />iframe" 
			onload="<portlet:namespace />monitorIframe(); <portlet:namespace />resizeIframe();" 
			scrolling="<%= scrolling %>" src="<%= iframeSrc %>" vspace="<%= vspace %>" width="<%= width %>">
				
			<%= LanguageUtil.format(pageContext, "your-browser-does-not-support-inline-frames-or-is-currently-configured-not-to-display-inline-frames.-content-can-be-viewed-at-actual-source-page-x", iframeSrc) %>
		</iframe>
	</div>


</div>

