package it.webscience.kpeople.web.portlet.pattern.action;

import it.webscience.kpeople.KpeoplePortalConstants;
import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.pattern.PatternServiceStub;
import it.webscience.kpeople.service.pattern.PatternServiceStub.PatternType;
import it.webscience.kpeople.service.process.ProcessServiceStub;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * Struts action per la ricerca dei processi.
 */
public class ViewAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil.getLog(ViewAction.class);

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

        if (logger.isDebugEnabled()) {
            logger.debug("processAction - begin");
        }

        String redirectDettaglio = ParamUtil.getString(req, "redirectDettaglio");
        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
        aReq.setAttribute("redirectDettaglio", redirectDettaglio);
        
        String targetEndpoint =
            new StubUtil()
                    .buildTarget(PropsKeys.WSO2SERVER_SERVICE_PATTERN_ENDPOINT);
        PatternServiceStub.User psUser = new PatternServiceStub.User();
        psUser.setIdUser(KpeopleUserUtil.getCurrUserId(req));

        PatternServiceStub stub = new PatternServiceStub(targetEndpoint);

        PatternServiceStub.GetPatternTypes patternTypes =
                new PatternServiceStub.GetPatternTypes();

        patternTypes.setPIgnoreShowInList(false);
        patternTypes.setPLoggedUser(psUser);

        PatternServiceStub.GetPatternTypesResponse response =
                stub.getPatternTypes(patternTypes);

        PatternType[] patternTypeResults = response.get_return();

        aReq.setAttribute("patternTypes", patternTypeResults);

        String hpmProcessId =
                req.getParameter(KpeoplePortalConstants.HPM_PROCESS_ID);

        aReq.setAttribute(KpeoplePortalConstants.HPM_PROCESS_ID, hpmProcessId);

        if (logger.isDebugEnabled()) {
            logger.debug("processAction - end");
        }

        return mapping.findForward("/pattern-browser/view");
    }
}
