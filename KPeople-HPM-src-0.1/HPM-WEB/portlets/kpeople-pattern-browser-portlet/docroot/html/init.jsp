<%@page import="it.webscience.kpeople.util.KpeoplePortletConfigurationUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="it.webscience.kpeople.service.process.*"%>
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
<%@page import="it.webscience.kpeople.KpeoplePortalConstants"%>

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

DateFormat dateFormatDateTime = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy",locale);


String friendlyUrl = new KpeoplePortletConfigurationUtil().getFriendlyUrlDettaglioProcesso();
String destinationPageParam = new KpeoplePortletConfigurationUtil().getPaginaDettaglioProcesso();


String friendlyUrlPattern = new KpeoplePortletConfigurationUtil().getFriendlyUrlNuovoPattern();
String destinationPagePattern = new KpeoplePortletConfigurationUtil().getPaginaNuovoPattern();


String destinationPageViewPattern = new KpeoplePortletConfigurationUtil().getPaginaDettaglioPattern();
String friendlyUrlViewPattern = new KpeoplePortletConfigurationUtil().getFriendlyUrlDettaglioPattern();



%>

