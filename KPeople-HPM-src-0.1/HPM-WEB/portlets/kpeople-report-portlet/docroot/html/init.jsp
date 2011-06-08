<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
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
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>

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
<%@ taglib uri="http://webscience.it/tld/ui" prefix="webscience-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String src = preferences.getValue("src", StringPool.BLANK);
boolean relative = GetterUtil.getBoolean(preferences.getValue("relative", StringPool.BLANK));

//boolean auth = GetterUtil.getBoolean(preferences.getValue("auth", StringPool.BLANK));
//String authType = preferences.getValue("auth-type", StringPool.BLANK);
//String formMethod = preferences.getValue("form-method", StringPool.BLANK);
//String userName = preferences.getValue("user-name", StringPool.BLANK);
//String userNameField = preferences.getValue("user-name-field", StringPool.BLANK);
//String password = preferences.getValue("password", StringPool.BLANK);
//String passwordField = preferences.getValue("password-field", StringPool.BLANK);
//String hiddenVariables = preferences.getValue("hidden-variables", StringPool.BLANK);

String reportType = ParamUtil.getString(request, "reportType");

String alt = preferences.getValue("alt", StringPool.BLANK);
String border = preferences.getValue("border", "0");
String bordercolor = preferences.getValue("bordercolor", "#000000");
String frameborder = preferences.getValue("frameborder", "0");
String heightMaximized = preferences.getValue("height-maximized", "600");
String heightNormal = preferences.getValue("height-normal", "300");
String hspace = preferences.getValue("hspace", "0");
String longdesc = preferences.getValue("longdesc", StringPool.BLANK);
String scrolling = preferences.getValue("scrolling", "auto");
String vspace = preferences.getValue("vspace", "0");
String width = preferences.getValue("width", "100%");

String type = preferences.getValue("reportType", StringPool.BLANK);
String style = preferences.getValue("style", StringPool.BLANK);

List<String> iframeVariables = new ArrayList<String>();

Enumeration<String> enu = request.getParameterNames();

while (enu.hasMoreElements()) {
	String name = enu.nextElement();

	if (name.startsWith(_IFRAME_PREFIX)) {
		iframeVariables.add(name.substring(_IFRAME_PREFIX.length()).concat(StringPool.EQUAL).concat(request.getParameter(name)));
	}
}


PortletURL reportViewURL = renderResponse.createRenderURL();
reportViewURL.setParameter("_spage", "/portlet_action/report/view");
reportViewURL.setWindowState(LiferayWindowState.EXCLUSIVE);
%>

<%!
private static final String _IFRAME_PREFIX = "iframe_";
%>