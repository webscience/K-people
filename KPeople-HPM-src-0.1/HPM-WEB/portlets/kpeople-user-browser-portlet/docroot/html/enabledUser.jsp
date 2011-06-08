<%@page import="java.util.ArrayList"%>
<%@page import="it.webscience.kpeople.web.portlet.user.search.UserSearchTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.user.search.UserDisplayTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.user.search.UserSearch"%>
<%@page import="it.webscience.kpeople.web.portlet.user.util.UserBrowserConstants"%>
    
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/html/init.jsp" %>



<%
	int risultatiPerPagina = 2;
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setWindowState(WindowState.NORMAL);
	portletURL.setParameter("_spage", "/portlet_action/process-browser/manage-user");
	
	UserSearch searchContainer = new UserSearch(renderRequest, portletURL);
	UserDisplayTerms displayTerms = (UserDisplayTerms)searchContainer.getDisplayTerms();
	
	searchContainer.setDelta(risultatiPerPagina);
	searchContainer.setDeltaConfigurable(false);
	searchContainer.setOrderByCol("");
	searchContainer.setOrderByType("");
%>


<div id="process-browser-portlet-user-result">
<%
String hpmProcessId = ParamUtil.getString(request,"processid");
%>
	<portlet:renderURL var="orderURL">
		<portlet:param name="struts_action" value="/portlet_action/process-browser/manage-user" />
	</portlet:renderURL>
	<form id="choose-type-order-user" method ="post" name="<portlet:namespace />fm" action=
		"<portlet:actionURL>
			<portlet:param name="_spage" value="/portlet_action/process-browser/manage-user" />
			<portlet:param name="processid" value="<%= hpmProcessId %>"/>
		</portlet:actionURL>">
		<b>Order by:</b>
		<select id="<portlet:namespace/>select-order-user" name="<portlet:namespace />select">
		   	<option selected value="Cognome">Cognome</option>
		   	<option value = "Nome">Nome</option>
	   	</select>
	   	<input id="submit-order" type="submit" value="ORDER"/>
	</form>
</div>
<%
 if(renderRequest.getAttribute(UserBrowserConstants.ENABLED_USERS) != null){
     List<ProcessServiceStub.User> results = (List<ProcessServiceStub.User>)renderRequest.getAttribute(UserBrowserConstants.ENABLED_USERS);
     
     if (results == null) 
     {
    	 results = new ArrayList<ProcessServiceStub.User>();
     }
     UserSearchTerms searchTerms = (UserSearchTerms)searchContainer.getSearchTerms();
     
     //imposto totale risultati ed elenco
     searchContainer.setTotal(results.size());
     searchContainer.setResults(results);

     List<ResultRow> resultRows = searchContainer.getResultRows();
     
     for (int i = searchContainer.getStart(); i < Math.min(searchContainer.getEnd(), results.size()) ; i++) {
	    ProcessServiceStub.User userEnable = (ProcessServiceStub.User)results.get(i);

		
	
		ResultRow row = new ResultRow(userEnable, userEnable.getIdUser(), i);
		row.setClassName("yy");
		row.setClassHoverName("yy");
		row.addJSP("/html/enabledUserRow.jsp");

		resultRows.add(row);	
	}
     request.setAttribute("liferay-ui:page-iterator:url", null);
     %>
     <liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
     <%
 }
%>
</body>
</html>