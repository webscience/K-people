<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="it.webscience.kpeople.web.portlet.globalsearch.search.GlobalSearchTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.globalsearch.util.GlobalSearchConstants"%>
<%@page import="it.webscience.kpeople.service.cross.GlobalSearchServiceStub"%>
<%@page import="it.webscience.kpeople.web.portlet.globalsearch.search.GlobalSearchDisplayTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.globalsearch.search.GlobalSearch"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.List"%>
<%@ include file="/html/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setWindowState(WindowState.MAXIMIZED);
portletURL.setParameter("_spage", "/portlet_action/global-search/view");

int risultatiPerPagina = 10;

%>

<form onsubmit="GlobalSearchPortlet.submit('<portlet:namespace/>');return false;" id="<portlet:namespace/>fm" name="<portlet:namespace/>fm" action="<%= portletURL.toString() %>" method="POST">
<%
GlobalSearch searchContainer = new GlobalSearch(renderRequest, portletURL);
GlobalSearchDisplayTerms displayTerms = (GlobalSearchDisplayTerms)searchContainer.getDisplayTerms();

searchContainer.setDelta(risultatiPerPagina);
searchContainer.setDeltaConfigurable(false);
searchContainer.setOrderByCol("");
searchContainer.setOrderByType("");

String keywords_defaults = LanguageUtil.get(pageContext, "global-search.search-in-documents");

String keywords = keywords_defaults;
String search_box_class = "gray-text";

if (displayTerms != null && windowState.equals(WindowState.MAXIMIZED)) {
    if (Validator.isNotNull(displayTerms.getFreeText())) {
	    keywords = displayTerms.getFreeText();
	    search_box_class = "normal-text";
	}
}

%>
    <div style="float: left;">
        <input type="text" class="search-box <%= search_box_class %>" 
               onfocus="if (this.value == '<%= keywords_defaults %>' && !GlobalSearchPortlet.submitEnabled) { this.className = 'normal-text'; this.value = ''; }" 
               onkeyup="GlobalSearchPortlet.onkeyup(this, '<portlet:namespace/>','<%=GlobalSearchDisplayTerms.EMPTY_FREE_TEXT %>','<%= keywords_defaults %>');"
               onblur="GlobalSearchPortlet.onblur(this, '<portlet:namespace/>','<%=GlobalSearchDisplayTerms.EMPTY_FREE_TEXT %>','<%= keywords_defaults %>');"
               value="<%= HtmlUtil.escapeAttribute(keywords) %>" 
               name="<portlet:namespace/><%=GlobalSearchDisplayTerms.FREE_TEXT %>" 
               id="<portlet:namespace/><%=GlobalSearchDisplayTerms.FREE_TEXT %>"/>
    </div>
    
    <script type="text/javascript">
    <%
    	if (!keywords.equals(keywords_defaults)) {
    %>
		    $(document).ready(function () {
		    	GlobalSearchPortlet.submitEnabled = true;
		    	$("#<portlet:namespace/><%=GlobalSearchDisplayTerms.FREE_TEXT %>").addClass('normal-text');
		    	$("#<portlet:namespace/>sbm").removeClass('submitDisabled');
		    });	
	<%  
    	}
	%>
    </script>
    
    <div id="<portlet:namespace/>sbm" 
    	 class="smplSubmit submitDisabled" 
    	 title="<liferay-ui:message key="global-search.search-disabled" />"
    	 onclick ="GlobalSearchPortlet.submit('<portlet:namespace/>');"></div>
    <div style="clear:both"></div>
</form>

<input type="hidden" 
	   id="<portlet:namespace/>btnDisabledTitle"
	   value="<liferay-ui:message key="global-search.search-disabled" />">

<div id="global-search-result">
<%
if (renderRequest.getAttribute(GlobalSearchConstants.ATTR_OBJECTS_FOUND) != null)
{
List<GlobalSearchServiceStub.KPeopleGenericDTO> results =
    (List<GlobalSearchServiceStub.KPeopleGenericDTO>)renderRequest.
    	getAttribute(GlobalSearchConstants.ATTR_OBJECTS_FOUND);

if (results != null) {
    searchContainer.setTotal(results.size());
    searchContainer.setResults(results);

    List<ResultRow> resultRows = searchContainer.getResultRows();
    for (int i = searchContainer.getStart(); i < Math.min(searchContainer.getEnd(), results.size()) ; i++) {
    
        GlobalSearchServiceStub.KPeopleGenericDTO dto = (GlobalSearchServiceStub.KPeopleGenericDTO)results.get(i);

        if (dto != null) {
        	System.out.println("[global-search-portlet:html/view.jsp] type: " + dto.getType() + " " + dto.getHpmId());
        } else {
            System.out.println("[global-search-portlet:html/view.jsp] null dto");
        }
        
        String rowJsp = null;
        ResultRow row = null;
        
        if (dto != null && "process".equals(dto.getType())
            || dto != null && "comm_event".equals(dto.getType())
            || dto != null && "doc_event".equals(dto.getType())
            || dto != null && "pattern".equals(dto.getType())
            || dto != null && "document".equals(dto.getType())
            || dto != null && "user".equals(dto.getType())
            || dto != null && "down_event".equals(dto.getType())) 
        {
            rowJsp = "/html/row_details_" + dto.getType() + ".jsp";
            row = new ResultRow(dto, dto.getHpmId(), i);
        } else {
            rowJsp = "/html/row_details_null.jsp";
            row = new ResultRow(null, null, i);
        }
        
        row.setClassName("yy");
        row.setClassHoverName("yy");
        row.addJSP(rowJsp);
        resultRows.add(row);
    }
%>
<liferay-ui:search-iterator searchContainer="<%= searchContainer %>"/>
<%            
}
}
%>

</div>