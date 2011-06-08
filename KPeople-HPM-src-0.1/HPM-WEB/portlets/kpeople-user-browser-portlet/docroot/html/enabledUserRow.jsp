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
	
	ProcessServiceStub.User userEnable = (ProcessServiceStub.User)row.getObject();
	//User owner = process.getOwner();
	String firstName = userEnable.getFirstName();
	String lastName = userEnable.getLastName();
	String mail = userEnable.getHpmUserId();
	
	
	WindowState state = WindowState.NORMAL;

	String processDetailsURL = null;
	
	
%>

<div id = "userEnable">
<div id="firstname"><b>Name:</b><%=firstName %></div><br/>
<div id="lastname"><b>Cognome:</b><%=lastName %></div><br/>
<div id="mail"><b>E-mail:</b><%=mail %></div><br/><br/><br/>


</div>