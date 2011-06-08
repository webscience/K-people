package it.webscience.kpeople.web.portlet.process.action;

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

//      recupero le preferenze della portlet
        String portletResource =
            ParamUtil.getString(actionRequest, "portletResource");

        PortletPreferences preferences =
            PortletPreferencesFactoryUtil.getPortletSetup(
                    actionRequest,
                    portletResource);

//      Parametro 'Visualizza'
        String selectedView = ParamUtil.getString(
                actionRequest, "selectedView");
        preferences.setValue("selected-view", selectedView);

//      Parametro 'Configura ricerca'
        String processFilter = ParamUtil.getString(
                actionRequest, "processFilter");
        preferences.setValue("process-filter", processFilter);

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
