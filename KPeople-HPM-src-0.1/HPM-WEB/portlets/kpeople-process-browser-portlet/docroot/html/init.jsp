<%@page import="it.webscience.kpeople.util.KpeoplePortletConfigurationUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="it.webscience.kpeople.service.process.*"%>
<%@page import="it.webscience.kpeople.service.process.ProcessServiceStub.*"%>
<%@page import="it.webscience.kpeople.service.processType.ProcessTypeServiceStub"%>
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
<%@ taglib uri="http://webscience.it/tld/ui" prefix="webscience-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
WindowState windowState = null;
PortletMode portletMode = null;
ProcessServiceStub.Process p;

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

PortletURL processViewURL = renderResponse.createRenderURL();
processViewURL.setParameter("_spage", 
            "/portlet_action/process-browser/view-process");
processViewURL.setWindowState(LiferayWindowState.EXCLUSIVE);

String friendlyUrl = new KpeoplePortletConfigurationUtil().getFriendlyUrlDettaglioProcesso();
String destinationPageParam = new KpeoplePortletConfigurationUtil().getPaginaDettaglioProcesso();

//String destinationPageParam = preferences.getValue("destination-page", StringPool.BLANK);
//String friendlyUrl = preferences.getValue("friendly-url-dettaglio", StringPool.BLANK);


String friendlyUrlPattern = new KpeoplePortletConfigurationUtil().getFriendlyUrlNuovoPattern();
String destinationPagePattern = new KpeoplePortletConfigurationUtil().getPaginaNuovoPattern();

//String destinationPagePattern = preferences.getValue("destination-page-pattern", StringPool.BLANK);
//String friendlyUrlPattern = preferences.getValue("friendly-url-lista-pattern", StringPool.BLANK);

String destinationPageViewPattern = new KpeoplePortletConfigurationUtil().getPaginaDettaglioPattern();
String friendlyUrlViewPattern = new KpeoplePortletConfigurationUtil().getFriendlyUrlDettaglioPattern();

//String destinationPageViewPattern = preferences.getValue("destination-page-view-pattern", StringPool.BLANK);
//String friendlyUrlViewPattern = preferences.getValue("friendly-url-view-pattern", StringPool.BLANK);


String selectedView = preferences.getValue("selected-view", StringPool.BLANK);
DateFormat dateFormatDateTime = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy",locale);




//ProcessServiceStub.User[] users1 = null;
//if(renderRequest.getAttribute(ProcessBrowserConstants.ENABLED_USERS)!= null){
  //  users1 = (ProcessServiceStub.User[])renderRequest.getAttribute(ProcessBrowserConstants.ENABLED_USERS);
//PortletURL mioIndirizzo = renderResponse.createActionURL();
//mioIndirizzo.setParameter("_spage","");
//} 
%>

