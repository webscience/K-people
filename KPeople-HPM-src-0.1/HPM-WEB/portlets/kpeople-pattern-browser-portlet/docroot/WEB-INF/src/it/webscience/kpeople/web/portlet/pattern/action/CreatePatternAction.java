package it.webscience.kpeople.web.portlet.pattern.action;

import it.webscience.kpeople.KpeoplePortalConstants;
import it.webscience.kpeople.service.cross.UserServiceStub;
import it.webscience.kpeople.service.document.DocumentServiceStub.User;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.pattern.util.PatternBrowserConstants;

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
public class CreatePatternAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil
            .getLog(CreatePatternAction.class);

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

        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

        String hpmProcessId =
            ParamUtil.getString(req, KpeoplePortalConstants.HPM_PROCESS_ID);
        int idPatternType = ParamUtil.getInteger(req, "idPatternType");
        String patternName = ParamUtil.getString(req, "patternName");
        String redirectDettaglio = ParamUtil.getString(req, "redirectDettaglio");
        
        aReq.setAttribute(KpeoplePortalConstants.HPM_PROCESS_ID, hpmProcessId);
        aReq.setAttribute("idPatternType", idPatternType);
        aReq.setAttribute("patternName", patternName);
        aReq.setAttribute("redirectDettaglio", redirectDettaglio);
        
//      recupero degli utenti dal servizio (utilizzato per gli autocomplete)
        req.setAttribute("allUsers", getAllUsers());

        if (idPatternType == PatternBrowserConstants.PATTERN_TYPE_RICHIEDI_CONTRIBUTO) {
            return mapping.findForward(
                    "/pattern-browser/create-pattern-richiedi-contributo");
        }
        if (idPatternType == PatternBrowserConstants.PATTERN_TYPE_DELEGA) {
            return mapping.findForward(
                    "/pattern-browser/create-pattern-delega");
        }
        if (idPatternType == PatternBrowserConstants.PATTERN_TYPE_ESCALATION) {
            return mapping.findForward(
                    "/pattern-browser/create-pattern-esclation");
        }
        if (idPatternType ==
            PatternBrowserConstants.PATTERN_TYPE_PIANIFICA_RIUNIONE) {
            return mapping.findForward(
                    "/pattern-browser/create-pattern-pianifica-riunione");
        }
        if (idPatternType ==
            PatternBrowserConstants.PATTERN_TYPE_RICHIEDI_AUTORIZZAZIONE) {
            return mapping.findForward(
                    "/pattern-browser/create-pattern-richiedi-autorizzazione");
        }
        if (idPatternType == PatternBrowserConstants.PATTERN_TYPE_SOLLECITO) {
            return mapping
                    .findForward("/pattern-browser/create-pattern-sollecito");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("processAction - end");
        }

        return mapping.findForward("/pattern-browser/view");
    }

    /**
     * Chiama il servizio per il recupero degli utenti da LDAP.
     * @return elenco utenti
     * @throws Exception eccezione durante la chiamata del servizio
     */
    private UserServiceStub.User[] getAllUsers() throws Exception {
        String targetEndpoint = new StubUtil().buildTarget(
                PropsKeys.WSO2SERVER_SERVICE_USER_ENDPOINT);

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
