<%@page import="it.webscience.kpeople.web.portlet.document.util.DocumentConstants"%>
<%@page import="it.webscience.kpeople.service.document.DocumentServiceStub.Document"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/html/init.jsp" %>

<%
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	
	Document document = (Document)row.getObject();
	String guid = document.getGuid();
	int idAttachment = document.getIdAttachment();

	String url = "/kpeople-document-browser-portlet/downloadDocument?idAttachment=" + idAttachment + "&guid=" + guid;
%>

<a href="<%= url  %>"><%= document.getName()%></a>
