package it.webscience.kpeople.web.portlet.pattern.action;

import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.event.EventServiceStub;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.pattern.util.PatternBrowserConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * Struts action di eventi associati al processo.
 */
public class EventsPerPatternAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil
            .getLog(EventsPerPatternAction.class);

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

        return processAction(mapping, form, req, res);
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
    private ActionForward processAction(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        String hpmPatternId = ParamUtil.getString(req, "patternId");

        if (logger.isDebugEnabled()) {
            logger.debug("processAction - begin");
            logger.debug("execute");
            logger.debug("PatternId = " + hpmPatternId);
        }

        try {
            EventServiceStub.EventFilter eventFilter =
                new EventServiceStub.EventFilter();
            eventFilter.setHpmPatternId(hpmPatternId);

            String targetEndpoint = new StubUtil().buildTarget(
                    PropsKeys.WSO2SERVER_SERVICE_EVENT_ENDPOINT);

            EventServiceStub stub = new EventServiceStub(targetEndpoint);
            EventServiceStub.GetEvents getEvents =
                new EventServiceStub.GetEvents();
            getEvents.setEventFilter(eventFilter);
            EventServiceStub.User user = new EventServiceStub.User();
            user.setIdUser(KpeopleUserUtil.getCurrUserId(req));

            getEvents.setUser(user);
            EventServiceStub.GetEventsResponse response =
                stub.getEvents(getEvents);

            EventServiceStub.Event[] arrayEvents = null;
            if (response.get_return() != null) {
                arrayEvents = response.get_return();
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Eventi associati: " + arrayEvents.length);
            }

            req.setAttribute(
                    PatternBrowserConstants.LIST_EVENTS_UPDATE,
                    arrayEvents);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("/pattern-browser/events-per-pattern");
    }
}
