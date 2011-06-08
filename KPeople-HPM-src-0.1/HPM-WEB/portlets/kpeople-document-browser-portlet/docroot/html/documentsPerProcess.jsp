<%@page import="it.webscience.kpeople.web.portlet.document.search.DocumentSearch"%>
<%@page import="it.webscience.kpeople.service.document.DocumentServiceStub.Document"%>
<%@page import="com.liferay.portal.model.Lock"%>
<%@page import="com.liferay.portlet.documentlibrary.util.DLUtil"%>
<%@page import="com.liferay.portal.service.LockLocalServiceUtil"%>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFileEntryConstants"%>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFileVersion"%>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFolder"%>
<%@page import="com.liferay.portal.kernel.util.DocumentConversionUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFileEntry"%>
<%@page import="com.liferay.portal.kernel.bean.BeanParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PropsKeys"%>
<%@page import="com.liferay.portal.kernel.util.PrefsPropsUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="/html/init.jsp" %>
<%
	int risultatiPerPagina = 4;
	List<Document> documents = (List<Document>) request.getAttribute("documents");

	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);
	portletURL.setParameter("_spage", "/portlet_action/document-browser/begin-search");

	DocumentSearch searchContainer = new DocumentSearch(renderRequest, portletURL);
    searchContainer.setDelta(risultatiPerPagina);
    searchContainer.setDeltaConfigurable(false);
    searchContainer.setOrderByCol("");
    searchContainer.setOrderByType("");
	
//   imposto totale risultati ed elenco
    searchContainer.setTotal(documents.size());
    searchContainer.setResults(documents);
    
    List<ResultRow> resultRows = searchContainer.getResultRows();
    for (int i = searchContainer.getStart(); i < Math.min(searchContainer.getEnd(), documents.size()) ; i++) {
        Document document = (Document) documents.get(i);

        ResultRow row = new ResultRow(document, document.getHpmAttachmentId(), i);
        row.setClassName("yy");
        row.setClassHoverName("yy");
        row.addJSP("/html/row_details.jsp");

        resultRows.add(row);
    }

    request.setAttribute("liferay-ui:page-iterator:url", null);
%>

<webscience-ui:asynchronous-search-iterator 
  		divId="document-browser-portlet-results-box"
  		formId="<%= renderResponse.getNamespace() + "fm"%>"
  		searchContainer="<%= searchContainer %>"/>
<div class="document-portlet-list-box"></div>