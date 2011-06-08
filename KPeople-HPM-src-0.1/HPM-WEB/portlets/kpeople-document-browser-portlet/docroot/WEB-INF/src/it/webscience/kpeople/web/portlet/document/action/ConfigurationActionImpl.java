package it.webscience.kpeople.web.portlet.document.action;

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

/**
 * Configurazione della Document Portlet.
 *
 */
public class ConfigurationActionImpl extends BaseConfigurationAction {

    /**
     * Process action della configuration.
     * @param portletConfig portlet config
     * @param actionRequest action request
     * @param actionResponse action response
     * @throws Exception eccezione durante la render
     */
    public final void processAction(
            final PortletConfig portletConfig,
            final ActionRequest actionRequest,
            final ActionResponse actionResponse)
            throws Exception {
        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        if (!cmd.equals(Constants.UPDATE)) {
            return;
        }

        String pageURL = ParamUtil.getString(actionRequest, "pageURL");

        String friendlyUrlDettaglio =
                ParamUtil.getString(actionRequest, "friendlyUrlDettaglio");
        String destionationPage =
                ParamUtil.getString(actionRequest, "destinationPage");
        String selectedView =
            ParamUtil.getString(actionRequest, "selectedView");


        String portletResource =
                ParamUtil.getString(actionRequest, "portletResource");

        PortletPreferences preferences =
                PortletPreferencesFactoryUtil.getPortletSetup(actionRequest,
                        portletResource);

        preferences.setValue("friendly-url-dettaglio", friendlyUrlDettaglio);
        preferences.setValue("destination-page", destionationPage);
        preferences.setValue("selected-view", selectedView);

        preferences.store();

        actionResponse.sendRedirect(ParamUtil.getString(actionRequest,
                "redirect"));
    }

    /**
     * render della action.
     * @param portletConfig portlet config
     * @param renderRequest render request
     * @param renderResponse render response
     * @throws Exception eccezione durante la render
     * @return forward
     */
    public final String render(
            final PortletConfig portletConfig,
            final RenderRequest renderRequest,
            final RenderResponse renderResponse)
            throws Exception {

        return "/html/configuration.jsp";
    }
}
