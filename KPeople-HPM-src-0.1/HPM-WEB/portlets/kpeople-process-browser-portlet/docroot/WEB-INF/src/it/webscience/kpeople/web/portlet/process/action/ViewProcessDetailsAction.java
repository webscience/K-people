package it.webscience.kpeople.web.portlet.process.action;

import it.webscience.kpeople.service.process.ProcessServiceStub;
import it.webscience.kpeople.service.process.ProcessServiceStub.Process;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;

import it.webscience.kpeople.web.portlet.process.util.ProcessBrowserConstants;
import it.webscience.kpeople.web.portlet.process.util.ProcessUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * Struts action per la ricerca dei processi.
 */
public class ViewProcessDetailsAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil
            .getLog(ViewProcessDetailsAction.class);

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

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("execute");
            }

            String command = ParamUtil.getString(req, Constants.CMD);

            return this.processAction(mapping, form, req, res);
        } catch (Exception e) {
            e.printStackTrace();
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
    private ActionForward processAction(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("processAction");
        }

        ThemeDisplay themeDisplay =
                (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
        int userId = (int) themeDisplay.getUserId();
        // User user = themeDisplay.getUser();

        String hpmProcessId = req.getParameter("hpmProcessId");

        String targetEndpoint = new StubUtil().buildTarget(
                PropsKeys.WSO2SERVER_SERVICE_PROCESS_ENDPOINT);

        ProcessServiceStub stub = new ProcessServiceStub(targetEndpoint);

        ProcessServiceStub.GetProcessByHpmId process =
                new ProcessServiceStub.GetProcessByHpmId();

        ProcessServiceStub.User user = new ProcessServiceStub.User();
        user.setIdUser(userId);

        process.setHpmProcessId(hpmProcessId);
        process.setUser(user);

        ProcessServiceStub.GetProcessByHpmIdResponse response =
                stub.getProcessByHpmId(process);

        Process processResult = response.get_return();

        if (processResult.getDateDue() != null) {
            long diffDays = ProcessUtils.getDaysToProcessDue(
                    processResult.getDateDue());
            req.setAttribute(ProcessBrowserConstants.DIFF_DAYS,
                    diffDays);
        }

        req.setAttribute(ProcessBrowserConstants.ATTR_PROCESS_FOUND,
                processResult);

        return mapping.findForward("/process-browser/view-process-details");
    }

}
