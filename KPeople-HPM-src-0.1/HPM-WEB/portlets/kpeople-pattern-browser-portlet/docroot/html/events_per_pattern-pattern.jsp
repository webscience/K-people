<%@page import="it.webscience.kpeople.event.login.KpeopleUserUtil"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="it.webscience.kpeople.service.event.EventServiceStub"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.webscience.kpeople.web.portlet.process.util.EventUtil"%>    
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
	
	int id_event = event.getIdEvent();
	
	EventServiceStub.Pattern pattern = event.getAttachments()[0].getPattern();
	
	String object = LanguageUtil.get(pageContext, pattern.getPatternType().getName()) + " - " + pattern.getName();
	Date data = event.getFirstActionDate();

	String hpmSystemId = event.getHpmSystemId();

//	destinatario
	String userTo = "";
	if (pattern.getPatternRequestor() != null) {
	    EventServiceStub.User userStub = pattern.getPatternRequestor();
	    
	    if (userStub.getIdUser() == userId) {
	        userTo = LanguageUtil.get(pageContext,"process.tu");
	    } else if (userStub.getScreenName() != null) {
	        userTo = userStub.getScreenName();
		} else {
		    userTo = userStub.getEmail();
		}
	}	

//	mittente
	String userFrom = "";
	if (pattern.getPatternProvider() != null) {
	    EventServiceStub.User userStub = pattern.getPatternProvider();
	    
	    if (userStub.getIdUser() == userId) {
	        userFrom = LanguageUtil.get(pageContext,"process.tu");
	    } else if (userStub.getScreenName() != null) {
	        userFrom = userStub.getScreenName();
		} else {
		    userFrom = userStub.getEmail();
		}
	}
%>

<script type="text/javascript">
	function showHidePattern(data){
	
		var visible = $('#'+data).is(':visible');
		
		if (visible) {
			$('#'+data).hide();
		} else {
			$('#'+data).show();
		}
	}
</script>

<div class ="row-event-update" id="event-update">
	<div class="event-type" id ="event-type-div">
		<div class="event-type-name">
			<liferay-ui:message key="process.pattern.img"></liferay-ui:message>
		</div>
		<div class="event-type-id">
			<%= event.getHpmEventId() %>
		</div>
		<img alt="" src="/kpeople-process-browser-portlet/images/pattern.png">
	</div>
	<div class="eventData">
		<div id ="id-eventName" class="eventName" >
			<span onclick="showHidePattern('event_details_<%= id_event %>')">
				<%= object %>
			</span>
		</div>

		<%
			if (!Validator.isNull(destinationPagePattern) && !Validator.isNull(friendlyUrlPattern)) {
		    	String patternDetailsUrl = destinationPagePattern + "-/pattern_browser" + friendlyUrlPattern + pattern.getHpmPatternId();
		%>

				<div class="goDettagli">
					<a href="<%=patternDetailsUrl %>" >
						<img alt="" src="<%=request.getContextPath()%>/images/info.png" title="<liferay-ui:message key="process.pattern.dettagli" />">
					</a>
				</div>
		<% }  

			if (pattern.getDocList() != null && pattern.getDocList().length > 0) {
		%>	    
			 	<div class="graffetta"></div> 
		<%	    
			}
		%>
		<div class="clear"></div>
		<div id="id-eventInfo" class="eventInfo">
			<b>
				<%=eventUtil.formatDate(data)%> 
				<liferay-ui:message key="process.event-details.from" />: <%= userFrom %>
			 	<liferay-ui:message key="process.event-details.to" />: <%= userTo %>
			 </b>
		</div>
		<div class="clear"></div>
	</div>
	<div id="event_details_<%= id_event %>" class="eventDetails">
		<table class="tableEventdetails" id="table_event_details_<%= id_event %>">
			<tr>
				<td class="col1"><b><liferay-ui:message key="process.event-details.from.upper" />:</b></td>
				<td><%= userFrom %></td>
			</tr>
			<tr>
				<td class="col1"><b><liferay-ui:message key="process.event-details.to.upper" />:</b></td>
				<td><%= userTo %></td>
			</tr>
			<tr>
				<td class="col1"><b><liferay-ui:message key="process.event-details.object.upper" />:</b></td>
				<td><%= object %></td>
			</tr>
			<tr>
				<td class="col1"><b><liferay-ui:message key="process.event-details.attachments.upper" />:</b> </td>
				<td>
				<%
					if (pattern.getDocList() != null) {
					    for (EventServiceStub.Document doc : pattern.getDocList()) {
							int idAttachment = doc.getIdAttachment();

							String url = "/kpeople-document-browser-portlet/downloadDocument?idAttachment=" + idAttachment + "&guid=" + doc.getGuid();
				%>	
							<a href="<%= url  %>"><%= doc.getName()%></a><br>
				<%   
					    }
					}
				%>
				</td>
			</tr>
		</table>
	</div>
</div>
