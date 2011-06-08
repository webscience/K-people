package it.webscience.kpeople.web.portlet.activities.action;

import java.util.ArrayList;
import java.util.List;

import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.activity.ActivityServiceStub;
import it.webscience.kpeople.service.activity.ActivityServiceStub.Activity;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.activities.action.converter.ActivityConverter;
import it.webscience.kpeople.web.portlet.activities.util.ActivitiesBrowserConstants;


import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * Struts action per la ricerca dei processi.
 */
public class ActivitiesDefaultSearchAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil
            .getLog(ActivitiesDefaultSearchAction.class);

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

        return viewActivities(mapping, form, req, res);
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
    private ActionForward viewActivities(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("viewActivities - begin");
        }

        String hpmProcessId = ParamUtil.getString(req, "processId");
        String activitiesState = ParamUtil.getString(req, "activitiesState");

        if (activitiesState
                .equalsIgnoreCase(ActivitiesBrowserConstants.HPM_ACTIVITIES_TODO)) {
            return viewActivitiesToDo(mapping, form, req, res);
        }

        if (activitiesState
                .equalsIgnoreCase(ActivitiesBrowserConstants.HPM_ACTIVITIES_PENDING)) {
            return viewActivitiesPending(mapping, form, req, res);
        }

        logger.debug("Ricerca attivita todo associate al processo "
                + hpmProcessId);

        if (logger.isDebugEnabled()) {
            logger.debug("viewActivities - end");
        }

        return null;
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
    private ActionForward viewActivitiesToDo(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("viewActivitiesToDo - begin");
        }

        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

        String hpmProcessId = ParamUtil.getString(req, "processId");

        ActivityServiceStub.User psUser = new ActivityServiceStub.User();
        psUser.setIdUser(KpeopleUserUtil.getCurrUserId(req));
        psUser.setHpmUserId(KpeopleUserUtil.getCurrUserHpmId(req));

        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_ACTIVITY_ENDPOINT);

        ActivityServiceStub stub = new ActivityServiceStub(targetEndpoint);

        ActivityServiceStub.SearchToDoActivities activitiesToDo =
                new ActivityServiceStub.SearchToDoActivities();

        ActivityServiceStub.PatternActivityFilter filter =
                new ActivityServiceStub.PatternActivityFilter();
        filter.setHpmUserId(KpeopleUserUtil.getCurrUserHpmId(req));
        // filtrare opportunamente!!!!!
        filter.setHpmPatternId("");
        filter.setHpmProcessId(hpmProcessId);

        activitiesToDo.setPLoggedUser(psUser);
        activitiesToDo.setPFilter(filter);

        ActivityServiceStub.SearchToDoActivitiesResponse response =
                stub.searchToDoActivities(activitiesToDo);

        Activity[] activities = response.get_return();

        if (activities != null) {
            List<Activity> currActivitiesTodo =
                    ActivityConverter.toBE(activities);
            req.setAttribute("activities", currActivitiesTodo);
            aReq.getPortletSession().setAttribute(
                    ActivitiesBrowserConstants.CURR_ACTIVITIES_TODO,
                    currActivitiesTodo);
        } else {
            req.setAttribute("activities",
                    new ArrayList<ActivityServiceStub.Activity>());

            logger.debug("Nessuna attivita to do trovata");
        }

        req.setAttribute("activitiesState",
                ParamUtil.getString(req, "activitiesState"));

        logger.debug("Ricerca attivita pending associate al processo "
                + hpmProcessId);

        if (logger.isDebugEnabled()) {
            logger.debug("viewActivitiesToDo - end");
        }

        return mapping
                .findForward("/activities-browser/activities-per-process");
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
    private ActionForward viewActivitiesPending(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("viewActivitiesPending - begin");
        }

        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

        String hpmProcessId = ParamUtil.getString(req, "processId");

        ActivityServiceStub.User psUser = new ActivityServiceStub.User();
        psUser.setIdUser(KpeopleUserUtil.getCurrUserId(req));
        psUser.setHpmUserId(KpeopleUserUtil.getCurrUserHpmId(req));

        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_ACTIVITY_ENDPOINT);

        ActivityServiceStub stub = new ActivityServiceStub(targetEndpoint);

        ActivityServiceStub.SearchPendingActivities activitiesPending =
                new ActivityServiceStub.SearchPendingActivities();

        ActivityServiceStub.PatternActivityFilter filter =
                new ActivityServiceStub.PatternActivityFilter();
        filter.setHpmUserId(KpeopleUserUtil.getCurrUserHpmId(req));
        // filtrare opportunamente!!!!!
        filter.setHpmPatternId("");
        filter.setHpmProcessId(hpmProcessId);

        activitiesPending.setPLoggedUser(psUser);
        activitiesPending.setPFilter(filter);

        ActivityServiceStub.SearchPendingActivitiesResponse response =
                stub.searchPendingActivities(activitiesPending);

        Activity[] activities = response.get_return();

        if (activities != null) {
            List<Activity> currActivitiesPending =
                    ActivityConverter.toBE(activities);
            req.setAttribute("activities", currActivitiesPending);
            req.setAttribute("activitiesState",
                    ParamUtil.getString(req, "activitiesState"));
            aReq.getPortletSession().setAttribute(
                    ActivitiesBrowserConstants.CURR_ACTIVITIES_PENDING,
                    currActivitiesPending);
        } else {
            req.setAttribute("activities",
                    new ArrayList<ActivityServiceStub.Activity>());

            logger.debug("Nessuna attivita pending trovata");
        }

        req.setAttribute("activitiesState",
                ParamUtil.getString(req, "activitiesState"));

        logger.debug("Ricerca attivita associate al processo " + hpmProcessId);

        if (logger.isDebugEnabled()) {
            logger.debug("viewActivitiesPending - end");
        }

        return mapping
                .findForward("/activities-browser/activities-per-process");
    }
}
