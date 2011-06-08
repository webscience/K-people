<%@page import="it.webscience.kpeople.event.login.KpeopleUserUtil"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
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
	String dateFormatted = null;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");

	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	EventServiceStub.Event event = (EventServiceStub.Event)row.getObject();
	
	int userId = KpeopleUserUtil.getCurrUserId(request);
	
	int id_event = event.getIdEvent();
	
	EventServiceStub.CommunicationEvent email = event.getAttachments()[0].getCommunicationEvent();
	
	String object = LanguageUtil.get(pageContext, email.getObject());
	String body = StringEscapeUtils.unescapeXml(email.getBody());
	Date data = event.getFirstActionDate();

	String hpmSystemId = event.getHpmSystemId();

//	mittente
	String from = "";
	if (email.getUserFrom() != null) {
	    if (email.getUserFrom().getIdUser() == userId) {
	    	from = LanguageUtil.get(pageContext,"process.tu");
	    } else if (email.getUserFrom().getScreenName() != null) {
		    from = email.getUserFrom().getScreenName();
		} else {
		    from = email.getUserFrom().getEmail();
		}
	}
	
//	recupero i destinatari della mail (CC)
	String receiversCc = "";
	EventServiceStub.User[] ccUsers = email.getCcUser();
	if (ccUsers != null) {
	    for (EventServiceStub.User ccUser : ccUsers) {
	        if (! receiversCc.isEmpty()) {
	            receiversCc += ", ";
	        }

	        String screenName = ccUser.getScreenName();
	        if (screenName != null) {
	            receiversCc += screenName;
	        } else {
	            receiversCc += ccUser.getEmail();
	        }
	    }
	}
	
//	recupero i destinatari della mail (TO)
	String receivers = "";
	EventServiceStub.User[] toUsers = email.getToUser();
	if (toUsers != null) {
	    for (EventServiceStub.User toUser : toUsers) {
	        if (! receivers.isEmpty()) {
	            receivers += ", ";
	        }

	        String screenName = toUser.getScreenName();
	        if (screenName != null) {
	            receivers += screenName;
	        } else {
	            receivers += toUser.getEmail();
	        }
	    }
	}

%>
<script type="text/javascript">
	function showHideMail(data){
	
		var visible = $('#'+data).is(':visible');
		
		if (visible) {
			$('#'+data).hide();
		} else {
			$('#'+data).show();
		}
	}
	
	function closeDiv(data) {
		$('#'+data).hide("slow");
	}
</script>

<div class ="row-event-update" id="event-update">
	<div class="event-type" id ="event-type-div">
		<img alt="" src="/kpeople-pattern-browser-portlet/images/mail.gif">
	</div>
	<div class="eventData">
		<div id ="id-eventName" class="eventName" >
			<span onclick="showHideMail('event_details_<%= id_event %>')">
				<%= object %>
			</span>
		</div>
		
		<%
			if (email.getDocList() != null && email.getDocList().length > 0) {
		%>	    
			 <div class="graffetta"></div> 
		<%	    
			}
		%>
		<div class="clear"></div>
		<div id="id-eventInfo" class="eventInfo">
			<b>
				<%= formatter.format(data)%> 
				<liferay-ui:message key="process.event-details.from" />: <%= from %>
			 	<liferay-ui:message key="process.event-details.to" />: <%= receivers %>
			 </b>
		</div>
		<div class="clear"></div>
	</div>
	<div id="event_details_<%= id_event %>" class="eventDetails">
		<table class="tableEventdetails" id="table_event_details_<%= id_event %>">
			<tr>
				<td class="col1"><b><liferay-ui:message key="process.event-details.from.upper" />:</b></td>
				<td><%= from %></td>
			</tr>
			<tr>
				<td class="col1"><b><liferay-ui:message key="process.event-details.to.upper" />:</b></td>
				<td><%= receivers %></td>
			</tr>
			<tr>
				<td class="col1"><b>Cc:</b></td>
				<td><%= receiversCc %></td>
			</tr>
			<tr>
				<td class="col1"><b><liferay-ui:message key="process.event-details.object.upper" />:</b></td>
				<td><%= object %></td>
			</tr>
			<tr>
				<td class="col1"><b><liferay-ui:message key="process.event-details.body.upper" />:</b></td>
				<td>
					<div class="emailBody">
						<%= body%>
					</div>
				</td>
			</tr>
			<tr>
				<td class="col1"><b><liferay-ui:message key="process.event-details.attachments.upper" />:</b> </td>
				<td>
				<%
					if (email.getDocList() != null) {
					    for (EventServiceStub.Document doc : email.getDocList()) {
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
