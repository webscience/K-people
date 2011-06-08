<%@page import="it.webscience.kpeople.event.login.KpeopleUserUtil"%>
<%@page import="it.webscience.kpeople.web.portlet.process.util.EventUtil"%>
<%@page import="it.webscience.kpeople.service.event.EventServiceStub"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/html/init.jsp"%>

<%
	EventUtil eventUtil = new EventUtil();
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	EventServiceStub.Event event = (EventServiceStub.Event)row.getObject();
	
	int userId = KpeopleUserUtil.getCurrUserId(request);
	
	EventServiceStub.Document document = event.getAttachments()[0].getDocument();
	
	int id_event = event.getIdEvent();
	String hpmEventId = event.getHpmEventId();
	String hpmSystemId = event.getHpmSystemId();
	
	WindowState state = WindowState.NORMAL;
	
	String author = "";
//  recupero il creatore dell'evento
    for (EventServiceStub.EventMetadata em : event.getEventMetadata()) {
        if (em.getKeyname().equalsIgnoreCase("author")) {
          author = em.getValue();
        }
    }
	int idAttachment = document.getIdAttachment();
	String url = "/kpeople-document-browser-portlet/downloadDocument?idAttachment=" + idAttachment + "&guid=" + document.getGuid();
%>

<div class ="row-event-update" id="event-update">
	<div class="event-type" id ="event-type-div">
		<img alt="" src="/kpeople-process-browser-portlet/images/upload.gif">
		
		<div class="eventData">
		<div id ="id-eventName" class ="eventName">
			<span>
				<a href="<%= url  %>"><%= document.getName()%></a>
			</span>
		</div>
		<div class="clear"></div>
		<div id="id-eventInfo" class="eventInfo">
			<%=eventUtil.formatDate(event.getFirstActionDate())%> 
			<liferay-ui:message key="process.event-details.from" />: <%=author%>
		</div>
	</div>
	</div>
	
</div>