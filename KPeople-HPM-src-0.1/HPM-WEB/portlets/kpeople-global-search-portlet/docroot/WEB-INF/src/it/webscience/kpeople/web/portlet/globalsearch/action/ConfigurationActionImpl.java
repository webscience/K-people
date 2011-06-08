package it.webscience.kpeople.web.portlet.globalsearch.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

public class ConfigurationActionImpl extends BaseConfigurationAction {

    public void processAction(PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {
        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        if (!cmd.equals(Constants.UPDATE)) {
            return;
        }

        String friendlyUrlDettaglio =
                ParamUtil.getString(actionRequest, "friendlyUrlDettaglio");
        String destionationPage =
                ParamUtil.getString(actionRequest, "destinationPage");
        
        String friendlyUrlPattern =
            ParamUtil.getString(actionRequest, "friendlyUrlPattern");
        String destionationPagePattern =
            ParamUtil.getString(actionRequest, "destinationPagePattern");
        
        String portletResource =
                ParamUtil.getString(actionRequest, "portletResource");

        PortletPreferences preferences =
                PortletPreferencesFactoryUtil.getPortletSetup(actionRequest,
                        portletResource);

        preferences.setValue("friendly-url-dettaglio", friendlyUrlDettaglio);
        preferences.setValue("destination-page", destionationPage);
//      parametri per friendly url pattern
        preferences.setValue(
                "friendly-url-lista-pattern", friendlyUrlPattern);
        preferences.setValue(
                "destination-page-pattern", destionationPagePattern);
        
        preferences.store();

        actionResponse.sendRedirect(ParamUtil.getString(actionRequest,
                "redirect"));
    }

    public String render(PortletConfig portletConfig,
            RenderRequest renderRequest, RenderResponse renderResponse)
            throws Exception {

        return "/html/configuration.jsp";
    }
}
