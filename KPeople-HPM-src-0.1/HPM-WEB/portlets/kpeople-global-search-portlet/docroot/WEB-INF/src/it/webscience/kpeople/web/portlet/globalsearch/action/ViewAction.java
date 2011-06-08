package it.webscience.kpeople.web.portlet.globalsearch.action;

import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.cross.GlobalSearchServiceStub;
import it.webscience.kpeople.service.cross.GlobalSearchServiceStub.KPeopleGenericDTO;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.globalsearch.action.converter.GenericConverter;
import it.webscience.kpeople.web.portlet.globalsearch.search.GlobalSearchDisplayTerms;
import it.webscience.kpeople.web.portlet.globalsearch.util.GlobalSearchConstants;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * Struts action per la ricerca dei processi.
 */
public class ViewAction extends Action {

    /** logger. */
    private static Log logger =
        LogFactoryUtil.getLog(ViewAction.class);

    /**
     * <p>Process the specified non-HTTP request, and create the
     * corresponding non-HTTP response (or forward to another web
     * component that will create it), with provision for handling
     * exceptions thrown by the business logic.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param req The non-HTTP request we are processing
     * @param res The non-HTTP response we are creating
     *
     * @exception Exception if the application business logic throws
     * an exception.
     * @return action forward
     */
    @Override
    public final ActionForward execute(
            final ActionMapping mapping,
            final ActionForm form,
            final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        if (req.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST)
                instanceof RenderRequest) {
            PortletRequest aReq = (RenderRequest) req.getAttribute(
                    JavaConstants.JAVAX_PORTLET_REQUEST);

            if (aReq.getWindowState().equals(WindowState.MAXIMIZED)) {
                return processAction(mapping, form, req, res);
            }
        }
        return mapping.findForward("/global-search/view");
    }

    /**
     * Restituisce la view della portlet.
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param req The non-HTTP request we are processing
     * @param res The non-HTTP response we are creating
     * @return action forward
     * @throws Exception if the application business logic throws
     * an exception.
     */
    private ActionForward processAction(
            final ActionMapping mapping,
            final ActionForm form,
            final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("processAction");
        }

        PortletRequest aReq = (PortletRequest)
            req.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

        String freeText = ParamUtil.get(req,
                GlobalSearchDisplayTerms.FREE_TEXT, "");

        if (logger.isDebugEnabled()) {
            logger.debug("freeText: " + freeText);
        }

        String cur = ParamUtil.getString(req, "cur");

        KPeopleGenericDTO[] results = null;

        if (!cur.isEmpty()) {
//          recupero della ricerca dalla sessione

            results = (KPeopleGenericDTO[]) aReq.getPortletSession().getAttribute(
                    GlobalSearchConstants.ATTR_OBJECTS_FOUND);

        } else if (Validator.isNotNull(freeText)) {
            String targetEndpoint = new StubUtil().buildTarget(
                    PropsKeys.WSO2SERVER_SERVICE_GLOBAL_SEARCH_ENDPOINT);
            
            GlobalSearchServiceStub stub = new GlobalSearchServiceStub(
                    targetEndpoint);
            GlobalSearchServiceStub.Find finder = 
                new GlobalSearchServiceStub.Find();
            
            GlobalSearchServiceStub.User user = getCurrentHpmUser(req);
            
            finder.setFreeText(freeText);
            finder.setUser(user);
            
            GlobalSearchServiceStub.FindResponse response = stub.find(finder);
            
            if (logger.isDebugEnabled()) {
                logger.debug(
                      "Elementi trovati: " + response.get_return().length);
            }
    
            results = response.get_return();
    
            // copia in sessione i risultati della ricerca
            aReq.getPortletSession().setAttribute(
                    GlobalSearchConstants.ATTR_OBJECTS_FOUND,
                    results);
            
        }
        
        aReq.setAttribute(
                GlobalSearchConstants.ATTR_OBJECTS_FOUND,
                GenericConverter.toBE(results));
        
        return mapping.findForward("/global-search/view");
    }
    
    /**
     * Estrae lo user hpm corrente
     * @param req The non-HTTP request we are processing
     * @return GlobalSearchServiceStub.User corrente
     * @throws PortalException eccezione del portale
     * @throws SystemException eccezione di sistema
     */
    private GlobalSearchServiceStub.User getCurrentHpmUser(
            final HttpServletRequest req) throws PortalException,
            SystemException {
        try {
        GlobalSearchServiceStub.User user = new GlobalSearchServiceStub.User();
        user.setIdUser(KpeopleUserUtil.getCurrUserId(req));
        
        user.setHpmUserId(KpeopleUserUtil.getCurrUserHpmId(req));
        return user;
        } catch (Exception e) {
            
        }
        return null;
    }
}
