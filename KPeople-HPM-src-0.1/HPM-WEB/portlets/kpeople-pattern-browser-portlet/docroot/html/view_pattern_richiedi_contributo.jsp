<%@page import="it.webscience.kpeople.web.portlet.pattern.util.EventUtil"%>
<%@page import="it.webscience.kpeople.event.login.KpeopleUserUtil"%>
<%@page import="it.webscience.kpeople.service.pattern.PatternServiceStub"%>
<%@page import="it.webscience.kpeople.KpeoplePortalConstants"%>
<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/html/init.jsp"%>

<%
	EventUtil evUtil = new EventUtil();

	int userId = KpeopleUserUtil.getCurrUserId(request);

    String row = request.getParameter("hpmPatternId");
	PatternServiceStub.Pattern patternResult = null;
    String hpmProcessId = request.getParameter("hpmProcessId");
    String returnURL = null;
    
    PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);
	portletURL.setParameter("_spage", "/portlet_action/pattern-browser/view-events-per-pattern");
%>

<form id="<portlet:namespace/>eventsPerPatternFm" name="<portlet:namespace/>eventsPerPatternFm">
   	<input type="hidden"
   		   value="<%=row %>"
   		   id="<portlet:namespace/>patternId"
   		   name="<portlet:namespace/>patternId">
</form>

<div class="pattern-browser-portlet">
	<div class="pattern-details">
	<%
	    if (renderRequest.getAttribute("patternResult") != null)  {

	        patternResult =
                (PatternServiceStub.Pattern) renderRequest
                        .getAttribute("patternResult");     
	               
	        String patternName = patternResult.getName();

//			destinatario
			String userTo = "";
			if (patternResult.getPatternProvider() != null) {
			    PatternServiceStub.User userStub = patternResult.getPatternProvider();
			    
			    if (userStub.getIdUser() == userId) {
			        userTo = LanguageUtil.get(pageContext,"Pattern");
			    } else if (userStub.getScreenName() != null) {
			        userTo = userStub.getScreenName();
				} else {
				    userTo = userStub.getEmail();
				}
			}	

//			cc user
			String userCc = "";
			if (patternResult.getCcUsers() != null) {
			    PatternServiceStub.User[] users = patternResult.getCcUsers();
			    for (PatternServiceStub.User tmp : users) {
				    if (tmp.getScreenName() != null) {
				        userCc = tmp.getScreenName();
					} else {
					    userCc = tmp.getEmail();
					}
			    }
			}

//			mittente
			String userFrom = "";
			if (patternResult.getPatternRequestor() != null) {
			    PatternServiceStub.User userStub = patternResult.getPatternRequestor();
			    
			    if (userStub.getIdUser() == userId) {
			        userFrom = LanguageUtil.get(pageContext,"process.tu");
			    } else if (userStub.getScreenName() != null) {
			        userFrom = userStub.getScreenName();
				} else {
				    userFrom = userStub.getEmail();
				}
			}

	        String patternDescription = patternResult.getDescription();
	
	        Date patternDateCreated = patternResult.getStartDate();
	        Date patternDateDue = patternResult.getDueDate();
	
	        String state = LanguageUtil.get(pageContext, patternResult.getPatternState().getState());
	        String type = LanguageUtil.get(pageContext, patternResult.getPatternType().getName());

	        String data = dateFormatDateTime.format(patternDateCreated);

	        String diffDaysGg = "";
	        String statusDay2Class = "statusDays2Negativo";
	        if (patternDateDue != null) {
	            long diffDays = (Long) renderRequest.getAttribute("diffDays");
	            if (diffDays <= 0) {
	                statusDay2Class = "statusDays2Positivo";
	            }
	
	            diffDaysGg = diffDays + " g";
	        }
	%>
		<div id="header">
			<div class="pattern-title"><%=patternName%></div>
			
			<div class="statusDays">
				<div class="statusDays1"><%=state%></div> 
				<div class="statusDays2 <%=statusDay2Class%>"><%=diffDaysGg%></div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="clear"></div>
		<div id="details">
			<table>
				<tr>
					<td class="desc"><liferay-ui:message key="process.pattern.tipo" /></td>
					<td><%=type %></td>
				</tr>
				<tr>
					<td class="desc"><liferay-ui:message key="pattern.request.type" /></td>
					<td><%= (String) request.getAttribute("requestType") %></td>
				</tr>
				<tr>
					<td class="desc"><liferay-ui:message key="process.pattern.aperto-il" /></td>
					<td><%=data%></td>
				</tr>

				<%
					if (patternResult.getDueDate() != null) {
				%>
						<tr>
							<td class="desc"><liferay-ui:message key="process.pattern.date-due" /></td>
							<td><%=evUtil.formatDate(patternResult.getDueDate())%></td>
						</tr>
				<%
					}

					if (patternResult.getEndDate() != null) {
				%>
					<tr>
						<td class="desc"><liferay-ui:message key="process.pattern.date-end" /></td>
						<td><%=evUtil.formatDate(patternResult.getEndDate())%></td>
					</tr>
				<%
					}
				%>
				<tr>
					<td class="desc"><liferay-ui:message key="process.event-details.from.upper" /></td>
					<td><%= userFrom %></td>
				</tr>
				<tr>
					<td class="desc"><liferay-ui:message key="process.event-details.to.upper" /></td>
					<td><%= userTo %></td>
				</tr>
				<tr>
					<td class="desc"><liferay-ui:message key="pattern.user.cc" /></td>
					<td><%= userCc %></td>
				</tr>
				<tr>
					<td class="desc"><liferay-ui:message key="process.pattern.description" /></td>
					<td><%=patternDescription%></td>
				</tr>
				
				
				<tr>
					<td class="col1"><b><liferay-ui:message key="process.event-details.attachments.upper" />:</b> </td>
					<td>
					<%
						if (patternResult.getDocList() != null) {
						    for (PatternServiceStub.Document doc : patternResult.getDocList()) {
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
	<%
	    }
	%>
	
	</div>
	
</div>

<!-- Questo div verrà caricato tramite ajax -->
<div id="update-events-associated-at-the-pattern">
    	<!-- questo div viene caricato tramite ajax -->
    	<img src="<%= themeDisplay.getPathThemeImages() %>/application/loading_indicator.gif" alt="loading" title="loading..."/>
</div>



	<%if (!Validator.isNull(destinationPageParam) && !Validator.isNull(friendlyUrl)) {
	    returnURL = destinationPageParam + "-/process_browser" + friendlyUrl + hpmProcessId;
			%>
		<div>
			<input type="button" value="<liferay-ui:message key="cancel" />" onclick="location.href='<%=returnURL%>'">
		</div>
	<%} %>


<script type="text/javascript" charset="utf-8">
	Liferay.on("portletReady", function(data) {
		var portletId = data.portletId;
		var portlet = data.portlet;
	});
	
    $(document).ready(function () {
    	KPeople.Common.load('update-events-associated-at-the-pattern',
    	'<%= portletURL %>', '<portlet:namespace/>eventsPerPatternFm');
    });
</script>