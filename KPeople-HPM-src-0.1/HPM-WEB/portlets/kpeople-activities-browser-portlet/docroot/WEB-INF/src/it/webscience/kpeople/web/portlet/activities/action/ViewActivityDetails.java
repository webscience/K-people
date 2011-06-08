package it.webscience.kpeople.web.portlet.activities.action;

import java.util.ArrayList;
import java.util.List;

import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.activity.ActivityServiceStub;
import it.webscience.kpeople.service.activity.ActivityServiceStub.Activity;
import it.webscience.kpeople.service.activity.ActivityServiceStub.User;
import it.webscience.kpeople.service.cross.UserServiceStub;
import it.webscience.kpeople.service.pattern.PatternServiceStub;
import it.webscience.kpeople.util.KpeoplePortletConfigurationUtil;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.activities.action.converter.ActivityConverter;
import it.webscience.kpeople.web.portlet.activities.util.ActivitiesBrowserConstants;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.AxisFault;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * Struts action per la ricerca dei processi.
 */
public class ViewActivityDetails extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil
            .getLog(ViewActivityDetails.class);

    /**
     * <p>
     * Process the specified non-HTTP request, and create the corresponding
     * non-HTTP response (or forward to another web component that will create
     * it), with provision for handling exceptions thrown by the business logic.
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param req
     *            The non-HTTP request we are processing
     * @param res
     *            The non-HTTP response we are creating
     * @exception Exception
     *                if the application business logic throws an exception.
     * @return action forward
     */
    @Override
    public final ActionForward execute(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        return viewActivityDetails(mapping, form, req, res);
    }

    /**
     * Restituisce la view della portlet.
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param req
     *            The non-HTTP request we are processing
     * @param res
     *            The non-HTTP response we are creating
     * @return action forward
     * @throws Exception
     *             if the application business logic throws an exception.
     */
    private ActionForward viewActivityDetails(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("viewActivities - begin");
        }
       

        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);


        String currActivityId = ParamUtil.getString(req, "hpmActivityId");
        String currProcessId = ParamUtil.getString(req, "processId");

        ActivityServiceStub.User user = new ActivityServiceStub.User();
        user.setHpmUserId(KpeopleUserUtil.getCurrUserHpmId(req));

        Activity currActivity = getActivityById(currActivityId, user);

        String formType = currActivity.getActivityType().getName();

        String redirectUrl = null;
        if (Validator.isNotNull(currProcessId)) {
            String friendlyUrl = new
                KpeoplePortletConfigurationUtil()
                    .getFriendlyUrlDettaglioProcesso();
            String destinationPageParam = new
                KpeoplePortletConfigurationUtil().getPaginaDettaglioProcesso();
            redirectUrl = destinationPageParam + "-/process_browser"
                + friendlyUrl + currProcessId;
        } else {
            ThemeDisplay themeDisplay =
                (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
            redirectUrl = themeDisplay.getURLHome();
        }

        // List<Activity> currActivities = null;
        //
        // if (activitiesState
        // .equalsIgnoreCase(ActivitiesBrowserConstants.HPM_ACTIVITIES_TODO)) {
        // currActivities =
        // (List<Activity>) aReq.getPortletSession().getAttribute(
        // ActivitiesBrowserConstants.CURR_ACTIVITIES_TODO);
        // }
        //
        // if (activitiesState
        // .equalsIgnoreCase(ActivitiesBrowserConstants.HPM_ACTIVITIES_PENDING))
        // {
        // currActivities =
        // (List<Activity>) aReq.getPortletSession().getAttribute(
        // ActivitiesBrowserConstants.CURR_ACTIVITIES_PENDING);
        // }
        //
        // Activity currActivity = null;
        // String formType = null;
        // for (Activity act : currActivities) {
        // if (act.getHpmActivityId().equalsIgnoreCase(currActivityId)) {
        // currActivity = act;
        // formType = currActivity.getActivityType().getName();
        // break;
        // }
        // }

        if (currActivity != null || formType != null) {
            req.setAttribute(ActivitiesBrowserConstants.CURR_ACTIVITY,
                    currActivity);
            aReq.getPortletSession().setAttribute(
                    ActivitiesBrowserConstants.CURR_ACTIVITY, currActivity);
            aReq.getPortletSession().setAttribute(
                    ActivitiesBrowserConstants.CURR_PATTERN,
                    currActivity.getPattern());
            req.setAttribute(
                    ActivitiesBrowserConstants.REDIRECT_URL, redirectUrl);
            // recupero degli utenti dal servizio (utilizzato per gli
            // autocomplete)
            req.setAttribute("allUsers", getAllUsers());
            return mapping
                    .findForward("/activities-browser/view-activity-details/"
                            + formType);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("viewActivities - end");
        }

        // String idForm = currActivity.
        return mapping.findForward("/activities-browser/default-search");

    }

    private Activity getActivityById(String currActivityId, User user)
            throws Exception {

        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_ACTIVITY_ENDPOINT);
        ActivityServiceStub service = new ActivityServiceStub(targetEndpoint);

        ActivityServiceStub.GetActivityByHpmId obj =
                new ActivityServiceStub.GetActivityByHpmId();
        obj.setUser(user);
        obj.setHpmActivityId(currActivityId);

        ActivityServiceStub.GetActivityByHpmIdResponse response =
                service.getActivityByHpmId(obj);
        return response.get_return();
    }

    /**
     * Chiama il servizio per il recupero degli utenti da LDAP.
     * @return elenco utenti
     * @throws Exception
     *             eccezione durante la chiamata del servizio
     */
    private UserServiceStub.User[] getAllUsers() throws Exception {
        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_USER_ENDPOINT);

        UserServiceStub userStub = new UserServiceStub(targetEndpoint);

        UserServiceStub.GetUserByUsername sendParameter =
                new UserServiceStub.GetUserByUsername();
        sendParameter.setUser(new UserServiceStub.User());

        UserServiceStub.GetUserByUsernameResponse response =
                userStub.getUserByUsername(sendParameter);
        UserServiceStub.User[] users = response.get_return();

        return users;
    }
}
