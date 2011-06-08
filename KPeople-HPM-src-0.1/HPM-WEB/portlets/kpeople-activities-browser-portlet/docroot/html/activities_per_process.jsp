
<%@page import="it.webscience.kpeople.web.portlet.activities.search.ActivitiesSearch"%>
<%@page import="it.webscience.kpeople.service.activity.ActivityServiceStub.Activity"%>

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
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="/html/init.jsp" %>
<portlet:defineObjects />

<%
	int risultatiPerPagina = 2;
	List<Activity> activities = (List<Activity>) request.getAttribute("activities");

	String state = (String) request.getAttribute("activitiesState");
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);
	portletURL.setParameter("_spage", "	/portlet_action/activities-browser/default-search");
	portletURL.setParameter("activitiesState", state);
	

	ActivitiesSearch searchContainer = new ActivitiesSearch(renderRequest, portletURL);
    searchContainer.setDelta(risultatiPerPagina);
    searchContainer.setDeltaConfigurable(false);
    searchContainer.setOrderByCol("");
    searchContainer.setOrderByType("");
	
//   imposto totale risultati ed elenco
    searchContainer.setTotal(activities.size());
    searchContainer.setResults(activities);
    
    List<ResultRow> resultRows = searchContainer.getResultRows();
    for (int i = searchContainer.getStart(); i < Math.min(searchContainer.getEnd(), activities.size()) ; i++) {
        Activity activity = (Activity) activities.get(i);

        ResultRow row = new ResultRow(activity, activity.getHpmActivityId(), i);
        row.setClassName("yy");
        row.setClassHoverName("yy");
        row.addJSP("/html/row_details.jsp");

        resultRows.add(row);
    }

    request.setAttribute("liferay-ui:page-iterator:url", null);
	
%>

<script type="text/javascript">
jQuery(
		function() {
			<%
			if (state!=null) {
				if (state.equalsIgnoreCase(ActivitiesBrowserConstants.HPM_ACTIVITIES_TODO)) { %>
					jQuery('#menu-todo').addClass("menu-activities-selected");
					jQuery('#menu-pending').removeClass("menu-activities-selected");
				<%}
				if (state.equalsIgnoreCase(ActivitiesBrowserConstants.HPM_ACTIVITIES_PENDING)) { %>
					jQuery('#menu-pending').addClass("menu-activities-selected");
					jQuery('#menu-todo').removeClass("menu-activities-selected");
				<%}
			}%>
		}
	);

</script>
<webscience-ui:asynchronous-search-iterator 
  		divId="activities-browser-portlet-results-box"
  		formId="<%= renderResponse.getNamespace() + "fm"%>"
  		searchContainer="<%= searchContainer %>"/>
<div class="activities-portlet-list-box"></div>
