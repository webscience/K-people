<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<div id="<portlet:namespace/>switchAdvBtn" class="switchBtn">
    <a class="button-style-viola button-style-viola-piu" onclick="location.href = '<portlet:actionURL windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
        <portlet:param name="_spage" value="/portlet_action/process-browser/manage-process" />
        </portlet:actionURL>'; "href="#">
        <span><liferay-ui:message key="new-process" /></span> 
    </a>
</div>
<div style="clear: both;"></div>