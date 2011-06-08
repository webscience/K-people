
<%@page import="it.webscience.kpeople.service.pattern.PatternServiceStub"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="/html/init.jsp" %>
<portlet:defineObjects />


<!-- spostare nel css -->
<style type="text/css">
       
</style>
<!-- fine spostare nel css -->

<% 
PatternServiceStub.PatternType[] patternTypes = (PatternServiceStub.PatternType[]) request.getAttribute("patternTypes");
String hpmProcessId = (String) request.getAttribute(KpeoplePortalConstants.HPM_PROCESS_ID);
String redirectDettaglio = (String) request.getParameter("redirectDettaglio");
if (Validator.isNull(redirectDettaglio)) {
	redirectDettaglio = (String) request.getAttribute("redirectDettaglio");
}
%>
<div>
	<div><h1><liferay-ui:message key="pattern.new" /></h1></div>
	<br><br>

	<% 
	if (patternTypes != null) {
		for (int i = 0; i < patternTypes.length; i++) {
    		String patternDescription = LanguageUtil.get(pageContext, patternTypes[i].getDescription());
    		String patternName = LanguageUtil.get(pageContext, patternTypes[i].getName());
    		int idPatternType = patternTypes[i].getIdPatternType();
	%> 

		<portlet:actionURL var="createPatternUrl" windowState="<%=LiferayWindowState.MAXIMIZED.toString() %>">
        	<portlet:param name="_spage" value="/portlet_action/pattern-browser/create-pattern" />
         	<portlet:param name="idPatternType" value="<%=String.valueOf(idPatternType) %>" />
         	<portlet:param name="patternName" value="<%=patternName %>" />
         	<portlet:param name="<%=Constants.CMD %>" value="<%=Constants.ADD %>" />
         	<portlet:param name="<%=KpeoplePortalConstants.HPM_PROCESS_ID %>" value="<%=hpmProcessId %>" />
			<portlet:param name="redirectDettaglio" value="<%=redirectDettaglio %>" />
		</portlet:actionURL>

		<div class="pattern-list-top" >
			<div class="pattern-list-button">
				<input id="create-pattern" type="button" value="<%=patternName%>" 
					onclick="location.href='<%=createPatternUrl %>'" href="#" />
			</div>
			<div class="pattern-list-description">
				<%= patternDescription%>
			</div>
		</div>

<% 	} 
}%>
<div class="pattern-list-command" >
	<input type="button" value="<liferay-ui:message key="cancel" />" 
				onclick="location.href ='<%=redirectDettaglio%>'">
</div>