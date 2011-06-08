<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>
<%@page import="it.webscience.kpeople.KpeoplePortalConstants"%>
<%@page import="it.webscience.kpeople.util.KpeoplePortletConfigurationUtil"%>
<%@ include file="/html/taglib/ui/breadcrumb/init.jsp" %>

<%

StringBundler sb = new StringBundler();

//if (showGuestGroup) {
//	_buildGuestGroupBreadcrumb(themeDisplay, sb);
//}

//if (showParentGroups) {
//	_buildParentGroupsBreadcrumb(selLayout.getLayoutSet(), portletURL, themeDisplay, sb);
//}

if (showLayout) {
	_buildLayoutBreadcrumb(selLayout, selLayoutParam, portletURL, themeDisplay, true, sb);
}

//if (showPortletBreadcrumb) {
//	_buildPortletBreadcrumb(request, sb);
//}

String breadCrumbString = sb.toString();

if (Validator.isNotNull(breadCrumbString)) {
	String listToken = "<li";
	int tokenLength = listToken.length();

	int pos = breadCrumbString.indexOf(listToken);

	breadCrumbString = StringUtil.insert(breadCrumbString, " class=\"first\"", pos + tokenLength);

	pos = breadCrumbString.lastIndexOf(listToken);

	breadCrumbString = StringUtil.insert(breadCrumbString, " class=\"last\"", pos + tokenLength);
}
%>

<ul class="breadcrumbs lfr-component">
	<%= breadCrumbString %>
</ul>

<%!
private void _buildLayoutBreadcrumb(Layout selLayout, String selLayoutParam, PortletURL portletURL, ThemeDisplay themeDisplay, boolean selectedLayout, StringBundler sb) throws Exception {
    String url = themeDisplay.getURLCurrent();

    String processDetailsURL = null;
    if (url.contains("hpmProcessId")) {
        String[] currUrlSplitted = url.split("&");
        String id = null;
        for (int i = 0; i < currUrlSplitted.length; i++) {
            if (currUrlSplitted[i].contains("hpmProcessId")) {
                String idProcess = currUrlSplitted[i];
                id = idProcess.split("=")[1];
                System.out.println("id " + id);
                break;
            }
        }
        String friendlyUrl = new KpeoplePortletConfigurationUtil().getFriendlyUrlDettaglioProcesso();
        String destinationPageParam = new KpeoplePortletConfigurationUtil().getPaginaDettaglioProcesso();
        if (Validator.isNotNull(destinationPageParam) && Validator.isNotNull(friendlyUrl)) {
    		processDetailsURL = destinationPageParam + "-/process_browser" + friendlyUrl + id;
        }
        
    }

    if (url.contains("processId")) {
        String[] splitted = url.split("processId");
        String[] splitted2 = splitted[1].split("=");
        
        if (Validator.isNotNull(splitted2)) {
            String id = splitted2[1];
        	String friendlyUrl = new KpeoplePortletConfigurationUtil().getFriendlyUrlDettaglioProcesso();
        	String destinationPageParam = new KpeoplePortletConfigurationUtil().getPaginaDettaglioProcesso();
        	if (Validator.isNotNull(destinationPageParam) && Validator.isNotNull(friendlyUrl)) {
    			processDetailsURL = destinationPageParam + "-/process_browser" + friendlyUrl + id;
        	}
        }
        
    }
    if (url.contains("redirectDettaglio") && Validator.isNull(processDetailsURL)) {
        String[] splitted = url.split("redirectDettaglio");
        String urlSplitted = splitted[1];
        String[] splitted2 = urlSplitted.split("=");
        processDetailsURL = splitted2[1];
    } 
    
    String layoutURL = _getBreadcrumbLayoutURL(selLayout, selLayoutParam, portletURL, themeDisplay);
	String target = PortalUtil.getLayoutTarget(selLayout);

	StringBundler breadCrumbSB = new StringBundler(7);

	//breadCrumbSB.append("<li><span>");
	
	if (url.trim().startsWith(layoutURL.trim())) {
	    breadCrumbSB.append("<li><span>");
		breadCrumbSB.append(HtmlUtil.escape(selLayout.getName(themeDisplay.getLocale())));
		breadCrumbSB.append("</span></li>");
	}else{
	    if (layoutURL.contains("dettaglioprocesso")) {
		    breadCrumbSB.append("<li><span><a href=\"");
		    if (Validator.isNotNull(processDetailsURL)) {
		    	breadCrumbSB.append(processDetailsURL);
		    } else {
		        breadCrumbSB.append(themeDisplay.getURLHome());
		    }
			breadCrumbSB.append("\" ");
		} else {
		    breadCrumbSB.append("<li><span><a href=\"");
			breadCrumbSB.append(layoutURL);
			breadCrumbSB.append("\" ");
			breadCrumbSB.append(target);
		}
		breadCrumbSB.append(">");

		breadCrumbSB.append(HtmlUtil.escape(selLayout.getName(themeDisplay.getLocale())));
		
		breadCrumbSB.append("</a></span><span class=\"breadcrumb-separator\">»</span></li>");

	}
	
	
	Layout layoutParent = null;
	long layoutParentId = selLayout.getParentLayoutId();

	if (layoutParentId != LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
		layoutParent = LayoutLocalServiceUtil.getLayout(selLayout.getGroupId(), selLayout.isPrivateLayout(), layoutParentId);

		_buildLayoutBreadcrumb(layoutParent, selLayoutParam, portletURL, themeDisplay, false, sb);

		sb.append(breadCrumbSB.toString());
	}
	else {
		sb.append(breadCrumbSB.toString());
	}
}

private String _getBreadcrumbLayoutURL(Layout selLayout, String selLayoutParam, PortletURL portletURL, ThemeDisplay themeDisplay) throws Exception {
	if (portletURL == null) {
		return PortalUtil.getLayoutURL(selLayout, themeDisplay);
	}
	else {
		portletURL.setParameter(selLayoutParam, String.valueOf(selLayout.getPlid()));

		if (selLayout.isTypeControlPanel()) {
			if (themeDisplay.getDoAsGroupId() > 0) {
				portletURL.setParameter("doAsGroupId", String.valueOf(themeDisplay.getDoAsGroupId()));
			}

			if (themeDisplay.getRefererPlid() != LayoutConstants.DEFAULT_PLID) {
				portletURL.setParameter("refererPlid", String.valueOf(themeDisplay.getRefererPlid()));
			}
		}

		return portletURL.toString();
	}
}
%>