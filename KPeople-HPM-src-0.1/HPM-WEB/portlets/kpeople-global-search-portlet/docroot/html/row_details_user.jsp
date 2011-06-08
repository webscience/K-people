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
	
	GlobalSearchServiceStub.KPeopleGenericDTO hpmUser = 
	    (GlobalSearchServiceStub.KPeopleGenericDTO)row.getObject();
	
	WindowState state = WindowState.NORMAL;

	String userDetailsURL = "#";
	
%>

<div class="resultsSeparator"></div>

<div class="processData">
    <div class="event-type user">
        <div class="event-type-name">
            <liferay-ui:message key="user" />
        </div>
        <div class="event-type-id">
            
        </div>
    </div>
	<div class="processName">
		<a href="<%= userDetailsURL %>">
		    <span><%= hpmUser.getName() %></span>
		</a>
		
	</div>
</div>


