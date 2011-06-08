<%@page import="it.webscience.kpeople.web.portlet.report.action.ReportBrowserConstants"%>
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

<%@ include file="/html/init.jsp" %>

<%


String actionValue = preferences.getValue("reportType", ReportBrowserConstants.CHART_COMMUNICAZIONI_DESTINATARIO);

style = preferences.getValue("style", StringPool.BLANK);
String iframeSrc = StringPool.BLANK;

if (relative) {
	iframeSrc = themeDisplay.getPathContext();
}

iframeSrc += (String)request.getAttribute("REPORT_SRC");

if (Validator.isNotNull(iframeVariables)) {
	if (iframeSrc.indexOf(StringPool.QUESTION) != -1) {
		iframeSrc = iframeSrc.concat(StringPool.AMPERSAND).concat(StringUtil.merge(iframeVariables, StringPool.AMPERSAND));
	}
	else {
		iframeSrc = iframeSrc.concat(StringPool.QUESTION).concat(StringUtil.merge(iframeVariables, StringPool.AMPERSAND));
	}
}

String baseSrc = iframeSrc;

int lastSlashPos = iframeSrc.substring(7).lastIndexOf(StringPool.SLASH);

if (lastSlashPos != -1) {
	baseSrc = iframeSrc.substring(0, lastSlashPos + 8);
}

String iframeHeight = heightNormal;

if (windowState.equals(WindowState.MAXIMIZED)) {
	iframeHeight = heightMaximized;
}

%>

<script type="text/javascript">
jQuery(
		function() {
			<%
			if (style!=null) {
				if (style.equalsIgnoreCase(ReportBrowserConstants.STYLE_BIG)) { %>			
					jQuery('#<portlet:namespace/>actContainerTop').addClass("actContainerTop_big");
					jQuery('#<portlet:namespace/>actContainerRpt').addClass("actContainerRpt_big");
					jQuery('#<portlet:namespace/>actContainerBottom').addClass("actContainerBottom_big");
				<%} else { %>
					jQuery('#<portlet:namespace/>actContainerTop').addClass("actContainerTop");
					jQuery('#<portlet:namespace/>actContainerRpt').addClass("actContainerRpt");
					jQuery('#<portlet:namespace/>actContainerBottom').addClass("actContainerBottom");
				<%}
			}%>
		}
	);

</script>



<portlet:actionURL var="sendReportURL">
</portlet:actionURL>

<form id="<portlet:namespace/>fm" name="<portlet:namespace/>fm">
    		<input type="hidden" id="<portlet:namespace/>processHpmId" 
    			name="<portlet:namespace/>processId">
</form>    			

<script type="text/javascript">
Liferay.on("search-process-for-report", function(data) {
	
	if ($('#<portlet:namespace/>processHpmId') != null) {
        $('#<portlet:namespace/>processHpmId').val(data.hpmProcessId);
        KPeople.Common.load('<portlet:namespace/>report-portlet-results-box', '<%= reportViewURL.toString() %>', '<portlet:namespace/>fm');
    }
});
</script>



<div id="<portlet:namespace/>report-portlet-results-box" class="report">
<c:choose>
	<c:when test="<%= !themeDisplay.isSignedIn() %>">
		<div class="portlet-msg-info">
			<a href="<%= themeDisplay.getURLSignIn() %>" target="_top"><liferay-ui:message key="please-sign-in-to-access-this-application" /></a>
		</div>
	</c:when>
	<c:otherwise>
	
		
		<div  id="<portlet:namespace/>actContainerTop">
		</div>
	
		<%if (actionValue.equals(ReportBrowserConstants.CHART_COMMUNICAZIONI_DESTINATARIO) ||
		        actionValue.equals(ReportBrowserConstants.CHART_COMMUNICAZIONI_MITTENTE)) {%>
			<%@ include file="/html/view_report_user.jsp" %>
		<%} if (actionValue.equals(ReportBrowserConstants.KPI_PROCESS_XACTION)) {%>
			<%@ include file="/html/view_report_process.jsp" %>	
		<%} if (actionValue.equals(ReportBrowserConstants.PIVOT_PROCESS_XACTION) ||
		        actionValue.equals(ReportBrowserConstants.PIVOT_COMMUNICATION_XACTION)) {%>
			<%@ include file="/html/view_report_pivot.jsp" %>
		<%} %>	
		<div  id ="<portlet:namespace/>actContainerBottom"></div>
		
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />maximizeIframe(iframe) {
		var winHeight = 0;

		if (typeof(window.innerWidth) == 'number') {

			// Non-IE

			winHeight = window.innerHeight;
		}
		else if ((document.documentElement) &&
				 (document.documentElement.clientWidth || document.documentElement.clientHeight)) {

			// IE 6+

			winHeight = document.documentElement.clientHeight;
		}
		else if ((document.body) &&
				 (document.body.clientWidth || document.body.clientHeight)) {

			// IE 4 compatible

			winHeight = document.body.clientHeight;
		}

		// The value 139 here is derived (tab_height * num_tab_levels) +
		// height_of_banner + bottom_spacer. 139 just happend to work in
		// this instance in IE and Firefox at the time.

		iframe.height = (winHeight - 139);
	}

	function <portlet:namespace />monitorIframe() {
		var url = null;

		try {
			var iframe = document.getElementById('<portlet:namespace />iframe');

			url = iframe.contentWindow.document.location.href;
		}
		catch (e) {
			return true;
		}

		var baseSrc = '<%= baseSrc %>';
		var iframeSrc = '<%= iframeSrc %>';

		if ((url == iframeSrc) || (url == iframeSrc + '/')) {
		}
		else if (Liferay.Util.startsWith(url, baseSrc)) {
			url = url.substring(baseSrc.length);

			<portlet:namespace />updateHash(url);
		}
		else {
			<portlet:namespace />updateHash(url);
		}

		return true;
	}

	function <portlet:namespace />resizeIframe() {
		var iframe = document.getElementById('<portlet:namespace />iframe');

		var height = null;

		try {
			height = iframe.contentWindow.document.body.scrollHeight;
		}
		catch (e) {
			if (themeDisplay.isStateMaximized()) {
				<portlet:namespace />maximizeIframe(iframe);
			}
			else {
				iframe.height = <%= heightNormal %>;
			}

			return true;
		}

		iframe.height = height + 50;

		return true;
	}

	Liferay.provide(
		window,
		'<portlet:namespace />init',
		function() {
			var A = AUI();

			var hash = document.location.hash;

			if ((hash != '#') && (hash != '')) {
				var src = '';

				var path = hash.substring(1);

				if (path.indexOf('http://') != 0) {
					src = '<%= baseSrc %>';
				}

				src += path;

				var iframe = A.one('#<portlet:namespace />iframe');

				if (iframe) {
					iframe.attr('src', src);
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateHash',
		function(url) {
			var A = AUI();

			document.location.hash = url;

			var maximize = A.one('#p_p_id<portlet:namespace /> .portlet-maximize-icon a');

			if (maximize) {
				var href = maximize.attr('href');

				if (href.indexOf('#') != -1) {
					href = href.substring(0, href.indexOf('#'));
				}

				maximize.attr('href', href + '#' + url);
			}

			var restore = A.one('#p_p_id<portlet:namespace /> a.portlet-icon-back');

			if (restore) {
				var href = restore.attr('href');

				if (href.indexOf('#') != -1) {
					href = href.substring(0, href.indexOf('#'));
				}

				restore.attr('href', href + '#' + url);
			}
		},
		['aui-base']
	);

	<portlet:namespace />init();
</aui:script>

</div>