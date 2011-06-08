package it.webscience.kpeople.web.portlet.process.action;

import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.event.EventServiceStub;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.process.search.EventDisplayTerms;
import it.webscience.kpeople.web.portlet.process.util.EventBrowserConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

public class EventUpdateAction extends Action {

    /** logger. */
    private static Log logger =
        LogFactoryUtil.getLog(EventUpdateAction.class);

    @Override
    public final ActionForward execute(
            final ActionMapping mapping,
            final ActionForm form,
            final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("execute");
            }

            String hpmProcessId = ParamUtil.getString(
                    req, EventDisplayTerms.PROCESS_ID);

            EventServiceStub.EventFilter eventFilter =
                new EventServiceStub.EventFilter();
            eventFilter.setHpmProcessId(hpmProcessId);

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

            req.setAttribute(EventBrowserConstants.
                    LIST_EVENTS_UPDATE, arrayEvents);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("/events-update-browser/event-update");
    }
}
