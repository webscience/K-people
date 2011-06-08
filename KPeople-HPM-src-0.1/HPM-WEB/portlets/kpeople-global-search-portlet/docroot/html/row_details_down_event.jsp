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
	
	GlobalSearchServiceStub.KPeopleGenericDTO event = 
	    (GlobalSearchServiceStub.KPeopleGenericDTO)row.getObject();
	
	
	DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	String diffDaysGg = "";
	String statusDay2Class = "statusDays2Negativo";
	
	WindowState state = WindowState.NORMAL;

	String processDetailsURL = "#";
	if (Validator.isNotNull(destinationPageParam) 
            && Validator.isNotNull(friendlyUrl)
            && Validator.isNotNull(event.getHpmProcessRefId())) {
        processDetailsURL = destinationPageParam 
        + "-/process_browser" + friendlyUrl + event.getHpmProcessRefId();
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

<div class="resultsSeparator"></div>

<div class="rowData">
    <div class="event-type down_event">
    	<div class="event-type-name">
			<liferay-ui:message key="process.pattern.download"></liferay-ui:message>
		</div>    
    </div>
    
    
    <div class="processData">
        <div class="processName" >
            <span onclick="showHideMail('event_details_<%= row.getPos() %>')"
                style="cursor:pointer">
                <%= event.getName() %>
            </span>
        </div>
        
        
        <div class="clear"></div>
        <div id="id-eventInfo" class="eventInfo">
            <b>
                <liferay-ui:message key="event.date" />
                <%=dateFormatter.format(event.getCreationDate().getTime())%>,
                <liferay-ui:message key="process.event-details.from" />: <%= event.getCreator() %>
                <liferay-ui:message key="process.event-details.to" />: <%= event.getProvider() %>,
                <liferay-ui:message key="process.assoc" />
	            <a href="<%= processDetailsURL %>">
	                <span><%= event.getHpmProcessRefId() %></span>
	            </a>
             </b>
        </div>
        <div class="clear"></div>
    </div>
    <div id="event_details_<%= row.getPos() %>" class="eventDetails">
        <table class="tableEventdetails" id="table_event_details_<%= row.getPos() %>">
            <tr>
                <td class="col1"><b><liferay-ui:message key="process.event-details.from.upper" />:</b></td>
                <td><%= event.getCreator() %></td>
            </tr>
            <tr>
                <td class="col1"><b><liferay-ui:message key="process.event-details.to.upper" />:</b></td>
                <td><%= event.getProvider() %></td>
            </tr>
            <tr>
                <td class="col1"><b>Cc:</b></td>
                <td><%= event.getOtherProvider() %></td>
            </tr>
            <tr>
                <td class="col1"><b><liferay-ui:message key="process.event-details.object.upper" />:</b></td>
                <td><%= event.getName() %></td>
            </tr>
            <tr>
                <td class="col1"><b><liferay-ui:message key="process.event-details.body.upper" />:</b></td>
                <td>
                    <div class="emailBody">
                        <%= event.getDescription() %>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="col1"><b><liferay-ui:message key="process.event-details.attachments.upper" />:</b> </td>
                <td>
                
                </td>
            </tr>
        </table>
    </div>
</div>
