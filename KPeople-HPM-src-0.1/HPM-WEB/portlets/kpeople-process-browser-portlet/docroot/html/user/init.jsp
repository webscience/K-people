<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="it.webscience.kpeople.service.process.*"%>
<%@page import="it.webscience.kpeople.service.process.ProcessServiceStub.*"%>
<%@page import="it.webscience.kpeople.web.portlet.process.util.ProcessBrowserConstants"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@page import="com.liferay.portal.kernel.language.LanguageWrapper" %>
<%@page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>
<%@page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@page import="com.liferay.portal.kernel.util.CalendarUtil" %>
<%@page import="com.liferay.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.DateFormatFactoryUtil" %>
<%@page import="com.liferay.portal.kernel.util.DateUtil" %>
<%@page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.LocaleUtil" %>
<%@page import="com.liferay.portal.kernel.util.Time" %>

<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="javax.portlet.PortletMode"%>
<%@page import="javax.portlet.WindowState"%>

<%@page import="com.liferay.portlet.PortletURLUtil"%>

<%@ page import="java.util.TimeZone" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
WindowState windowState = null;
PortletMode portletMode = null;

PortletURL currentURLObj = null;

if (renderRequest != null) {
    windowState = renderRequest.getWindowState();
    portletMode = renderRequest.getPortletMode();

    currentURLObj = PortletURLUtil.getCurrent(renderRequest, renderResponse);
}
else if (resourceRequest != null) {
    windowState = resourceRequest.getWindowState();
    portletMode = resourceRequest.getPortletMode();

    currentURLObj = PortletURLUtil.getCurrent(resourceRequest, resourceResponse);
}

String currentURL = currentURLObj.toString();

PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, 
    "portletResource");

if (Validator.isNotNull(portletResource)) {
    preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

PortletURL userViewURL = renderResponse.createRenderURL();
userViewURL.setParameter("_spage", 
            "/portlet_action/user-browser/view-user");
userViewURL.setWindowState(LiferayWindowState.EXCLUSIVE);
%>