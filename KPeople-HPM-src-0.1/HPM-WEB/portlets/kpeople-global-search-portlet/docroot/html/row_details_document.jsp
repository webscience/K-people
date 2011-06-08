<%@page import="it.webscience.kpeople.service.cross.GlobalSearchServiceStub"%>
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
	
	GlobalSearchServiceStub.KPeopleGenericDTO document = 
	    (GlobalSearchServiceStub.KPeopleGenericDTO)row.getObject();
	
	
	DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	
	String diffDaysGg = "";
	String statusDay2Class = "statusDays2Negativo";
	
	WindowState state = WindowState.NORMAL;

	int attachmentId = document.getAttachmentId();
	String url = "/kpeople-document-browser-portlet/downloadDocument?idAttachment="+ attachmentId + "&guid=" + document.getGuid();
%>

<div class="resultsSeparator"></div>
<div class="resultRowA">
	<div class="event-type upload">
        <img alt="" src="/kpeople-global-search-portlet/images/upload.gif">
    </div>
	<div class="processData">
		<div class="processName">
			<a href="<%= url %>">
			     <%= document.getName() %>
			</a>
			
		</div>
		<div class="processInfo">
			
			
		</div>
	</div>
</div>
<div class="resultRowB">
	<div class="boxEsclamativo"></div>
</div>

