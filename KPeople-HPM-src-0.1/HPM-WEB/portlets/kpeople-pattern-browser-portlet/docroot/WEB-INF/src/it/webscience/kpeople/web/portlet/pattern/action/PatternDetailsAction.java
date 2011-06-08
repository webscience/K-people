package it.webscience.kpeople.web.portlet.pattern.action;

import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.pattern.PatternServiceStub;
import it.webscience.kpeople.service.pattern.PatternServiceStub.PatternMetadata;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.pattern.util.PatternUtils;

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
 * Struts action di dettaglio processo.
 */
public class PatternDetailsAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil
            .getLog(PatternDetailsAction.class);

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

//      recupero l'hpm pattern id dalla request
        String hpmPatternId = ParamUtil.getString(req, "hpmPatternId");

        if (logger.isDebugEnabled()) {
            logger.debug("processAction - begin");
            logger.debug("hpmPatternId: " + hpmPatternId);
        }

//      costruisco l'oggetto da passare al web service
        PatternServiceStub.PatternDetailByHpmPatternId obj =
            new PatternServiceStub.PatternDetailByHpmPatternId();

        PatternServiceStub.User user = new PatternServiceStub.User();
        user.setIdUser(KpeopleUserUtil.getCurrUserId(req));
        obj.setPLoggedUser(user);

        PatternServiceStub.Pattern pattern = new PatternServiceStub.Pattern();
        pattern.setHpmPatternId(hpmPatternId);
        obj.setPPattern(pattern);

//      eseguo la chiamata al servizio
        String targetEndpoint = new StubUtil().buildTarget(
                PropsKeys.WSO2SERVER_SERVICE_PATTERN_ENDPOINT);

        PatternServiceStub service = new PatternServiceStub(targetEndpoint);
        PatternServiceStub.PatternDetailByHpmPatternIdResponse response =
            service.patternDetailByHpmPatternId(obj);

        PatternServiceStub.Pattern patternResult = response.get_return();

//      calcolo del numero di giorni alla scadenza del pattern
        if (patternResult.getDueDate() != null) {
            long diffDays = PatternUtils.getDaysToPatternDue(
                    patternResult.getDueDate());
            req.setAttribute("diffDays", diffDays);
        }

//      estraggo il request type
        String requestType = "";
        for (PatternMetadata pm : patternResult.getPatternMetadatas()) {
            if (pm.getKeyname().equals("TipoDiRichiesta")) {
                requestType = pm.getValue();
                break;
            }
        }
        req.setAttribute("requestType", requestType);

//      inserisco nella request il pattern
        req.setAttribute(
                "patternResult",
                patternResult);

        String forward = "/pattern-browser/view-pattern-richiedi-contributo";

        return mapping.findForward(forward);
    }
}
