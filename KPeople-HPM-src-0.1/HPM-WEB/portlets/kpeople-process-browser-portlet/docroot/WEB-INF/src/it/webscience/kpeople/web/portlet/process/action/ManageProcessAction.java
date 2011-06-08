package it.webscience.kpeople.web.portlet.process.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import it.webscience.kpeople.KpeoplePortalConstants;
import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.exception.KpeopleProcessNameException;
import it.webscience.kpeople.service.cross.UserServiceStub;
import it.webscience.kpeople.service.process.ProcessServiceStub;
import it.webscience.kpeople.service.process.ProcessServiceStub.Process;
import it.webscience.kpeople.service.process.ProcessServiceStub.User;
import it.webscience.kpeople.service.processType.ProcessTypeServiceStub;
import it.webscience.kpeople.util.KpeoplePortletConfigurationUtil;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.process.util.ProcessBrowserConstants;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.PortletPreferencesFactoryUtil;

/**
 * Classe per la gestione CRUD del processo.
 * @author filieri
 */
public class ManageProcessAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil
            .getLog(ViewProcessDetailsAction.class);

    private String currUserHpmId;

    private int currUserId;

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

        if (logger.isDebugEnabled()) {
            logger.debug("execute");
        }

        return this.processAction(mapping, form, req, res);
    }

    /**
     * Restituisce la view della portlet in funzione del parametro command
     * presente nella request.
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

        currUserHpmId = KpeopleUserUtil.getCurrUserHpmId(req);
        currUserId = KpeopleUserUtil.getCurrUserId(req);

        setProcessTypes(req);

        String command = ParamUtil.getString(req, Constants.CMD);
        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
        SessionErrors.clear(aReq);

        // ActionRequest portletRequest =
        // (ActionRequest) req
        // .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

        if (command.equals(Constants.ADD)) {

            try {
                return this.processActionInsertProcess(mapping, form, req, res);
            } catch (Exception e) {
                if (e instanceof KpeopleProcessNameException) {
                    SessionErrors.add(aReq, e.getClass().getName(), e);
                    return mapping
                            .findForward("/process-browser/manage-process");
                }
            }
        }

        if (command.equals(Constants.UPDATE)) {
            try {
                return this.processActionUpdateProcess(mapping, form, req, res);
            } catch (Exception e) {
                SessionErrors.add(aReq, e.getClass().getName(), e);
                if (e instanceof KpeopleProcessNameException) {
                    SessionErrors.add(aReq, e.getClass().getName(), e);
                    return mapping
                            .findForward("/process-browser/manage-process");
                }
            }
        }

        if (command.equals(Constants.EDIT)) {
            return this.processActionModifyProcess(mapping, form, req, res);
        }

        if (command.equals(ProcessBrowserConstants.CLOSE_PROCESS)) {
            return this.processActionCloseProcess(mapping, form, req, res);
        }

        return mapping.findForward("/process-browser/manage-process");
    }

    /**
     * Azione per effettuare la chiusura del processo.
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
     * @return actionForward.
     */
    private ActionForward processActionCloseProcess(
            final ActionMapping mapping, final ActionForm form,
            final HttpServletRequest req, final HttpServletResponse res)
            throws Exception {

        String hpmProcessId = ParamUtil.getString(req, "hpmProcessId");
        Process currProcess = getProcess(hpmProcessId);

        ProcessServiceStub.User user = currProcess.getOwner();

        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_PROCESS_ENDPOINT);

        ProcessServiceStub stub = new ProcessServiceStub(targetEndpoint);
        ProcessServiceStub.CloseProcess processClose =
                new ProcessServiceStub.CloseProcess();

        processClose.setProcess(currProcess);
        processClose.setUser(user);

        stub.closeProcess(processClose);

        // PortletPreferences preferences =
        // PortletPreferencesFactoryUtil.getPreferences(req);

        String destinationPageParam =
                new KpeoplePortletConfigurationUtil()
                        .getPaginaDettaglioProcesso();

        // String destinationPageParam =
        // preferences.getValue("destination-page", StringPool.BLANK);

        String friendlyUrl =
                new KpeoplePortletConfigurationUtil()
                        .getFriendlyUrlDettaglioProcesso();
        // String friendlyUrl = preferences.getValue("friendly-url-dettaglio",
        // StringPool.BLANK);

        res.sendRedirect(destinationPageParam + "-/process_browser"
                + friendlyUrl + currProcess.getHpmProcessId());

        return null;
    }

    private ActionForward processActionUpdateProcess(ActionMapping mapping,
            ActionForm form, HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_PROCESS_ENDPOINT);

        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

        Process currProcess =
                (Process) aReq.getPortletSession().getAttribute(
                        ProcessBrowserConstants.CURR_PROCESS);
        // Process currProcess =
        // (Process) req.getAttribute(ProcessBrowserConstants.CURR_PROCESS);
        req.setAttribute(ProcessBrowserConstants.CURR_PROCESS, currProcess);

        ProcessServiceStub stub = new ProcessServiceStub(targetEndpoint);

        ProcessServiceStub.UpdateProcess processUpdate =
                new ProcessServiceStub.UpdateProcess();

        ProcessServiceStub.Process process = currProcess;

        // da sostituire
        ProcessServiceStub.User user = new ProcessServiceStub.User();
        user.setIdUser(currUserId);
        user.setHpmUserId(currUserHpmId);
        process.setOwner(user);

        String processName = ParamUtil.getString(req, "processName");
        if (Validator.isNull(processName)) {
            throw new KpeopleProcessNameException();
        }

        process.setName(processName);

        String processId = ParamUtil.getString(req, "processId");
        process.setIdProcess(Integer.valueOf(processId));

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Date processDueDate =
                ParamUtil.getDate(req, "dueDateString", format, null);

        if (Validator.isNotNull(processDueDate)
                && processDueDate.before(currProcess.getDateCreated())) {
            throw new KpeopleProcessNameException();
        }
        process.setDateDue(processDueDate);

        String processDescription = ParamUtil.getString(req, "description");
        process.setDescription(processDescription);

        ProcessServiceStub.ProcessType processType =
                new ProcessServiceStub.ProcessType();

        String processTypeId = ParamUtil.getString(req, "selectType");
        processType.setIdProcessType(Integer.valueOf(processTypeId));
        process.setProcessType(processType);

        String processVisibility = ParamUtil.getString(req, "visibilityRadio");
        if (processVisibility.equalsIgnoreCase("privato")) {
            process.set_private(true);
        } else {
            process.set_private(false);
        }
        String hpmProcessId = ParamUtil.getString(req, "hpmProcessId");
        process.setHpmProcessId(hpmProcessId);

        processUpdate.setProcess(process);
        processUpdate.setUser(user);

        stub.updateProcess(processUpdate);

        // PortletPreferences preferences =
        // PortletPreferencesFactoryUtil.getPreferences(req);
        //
        // String destinationPageParam =
        // preferences.getValue("destination-page", StringPool.BLANK);
        // String friendlyUrl =
        // preferences
        // .getValue("friendly-url-dettaglio", StringPool.BLANK);

        String destinationPageParam =
                new KpeoplePortletConfigurationUtil()
                        .getPaginaDettaglioProcesso();

        String friendlyUrl =
                new KpeoplePortletConfigurationUtil()
                        .getFriendlyUrlDettaglioProcesso();

        res.sendRedirect(destinationPageParam + "-/process_browser"
                + friendlyUrl + process.getHpmProcessId());

        return null;
    }

    private ActionForward processActionModifyProcess(ActionMapping mapping,
            ActionForm form, HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String hpmProcessId = ParamUtil.getString(req, "hpmProcessId");
        Process currProcess = getProcess(hpmProcessId);

        req.setAttribute(ProcessBrowserConstants.CURR_PROCESS, currProcess);
        // setProcessTypes(req);

        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
        aReq.getPortletSession().setAttribute(
                ProcessBrowserConstants.CURR_PROCESS, currProcess);

        return mapping.findForward("/process-browser/manage-process");
    }

    private ActionForward processActionInsertProcess(
            final ActionMapping mapping, final ActionForm form,
            final HttpServletRequest req, final HttpServletResponse res)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("processActionInsertProcess");
        }

        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_PROCESS_ENDPOINT);

        ProcessServiceStub stub = new ProcessServiceStub(targetEndpoint);

        ProcessServiceStub.SaveProcessInfo processAdd =
                new ProcessServiceStub.SaveProcessInfo();

        ProcessServiceStub.User user = new ProcessServiceStub.User();
        user.setIdUser(currUserId);
        user.setHpmUserId(currUserHpmId);

        ProcessServiceStub.Process process = new ProcessServiceStub.Process();
        process.setOwner(user);

        processAdd.setUser(user);

        String processName = ParamUtil.getString(req, "processName");
        process.setName(processName);

        if (Validator.isNull(processName)) {
            throw new KpeopleProcessNameException();
        }

        Calendar calendar = Calendar.getInstance();
        // get a date to represent "today"
        Date currentDate = calendar.getTime();

        // add one day to the date/calendar
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        // now get "tomorrow"
        Date dueDate = calendar.getTime();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date processDueDate =
                ParamUtil.getDate(req, "dueDateString", format, null);

        if (Validator.isNotNull(processDueDate)
                && processDueDate.before(currentDate)) {
            throw new KpeopleProcessNameException();
        }

        process.setDateDue(processDueDate);

        process.setDateCreated(currentDate);

        String processDescription = ParamUtil.getString(req, "description");
        process.setDescription(processDescription);

        ProcessServiceStub.ProcessType processType =
                new ProcessServiceStub.ProcessType();

        String processTypeId = ParamUtil.getString(req, "selectType");
        processType.setIdProcessType(Integer.valueOf(processTypeId));
        process.setProcessType(processType);

        // process.set_private(false);

        String processVisibility = ParamUtil.getString(req, "visibilityRadio");
        if (processVisibility.equalsIgnoreCase("privato")) {
            process.set_private(true);
        } else {
            process.set_private(false);
        }

        processAdd.setProcess(process);

        ProcessServiceStub.SaveProcessInfoResponse response =
                stub.saveProcessInfo(processAdd);

        // setProcessTypes(req);

        // PortletPreferences preferences =
        // PortletPreferencesFactoryUtil.getPreferences(req);
        //
        // String destinationPageParam =
        // preferences.getValue("destination-page", StringPool.BLANK);
        // String friendlyUrl =
        // preferences
        // .getValue("friendly-url-dettaglio", StringPool.BLANK);

        String destinationPageParam =
                new KpeoplePortletConfigurationUtil()
                        .getPaginaDettaglioProcesso();

        String friendlyUrl =
                new KpeoplePortletConfigurationUtil()
                        .getFriendlyUrlDettaglioProcesso();

        Process newProcess = response.get_return();
        req.setAttribute(ProcessBrowserConstants.CURR_PROCESS, newProcess);
        res.sendRedirect(destinationPageParam + "-/process_browser"
                + friendlyUrl + newProcess.getHpmProcessId());

        return null;
        // return mapping.findForward("/process-browser/view-process");
    }

    private void setProcessTypes(final HttpServletRequest req) throws Exception {
        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_PROCESS_TYPE_ENDPOINT);

        // chiamata al web service: recupero dei tipi di processo
        int idUser = currUserId;
        ProcessTypeServiceStub.User ptUser = new ProcessTypeServiceStub.User();
        ptUser.setIdUser(idUser);

        ProcessTypeServiceStub.GetProcessTypes processTypeSend =
                new ProcessTypeServiceStub.GetProcessTypes();
        processTypeSend.setUser(ptUser);
        ProcessTypeServiceStub.ProcessType[] processTypes =
                new ProcessTypeServiceStub(targetEndpoint).getProcessTypes(
                        processTypeSend).get_return();
        req.setAttribute("processTypes", processTypes);
    }

    private Process getProcess(final String hpmProcessId) throws Exception {
        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_PROCESS_ENDPOINT);

        ProcessServiceStub stub = new ProcessServiceStub(targetEndpoint);

        ProcessServiceStub.GetProcessByHpmId process =
                new ProcessServiceStub.GetProcessByHpmId();

        ProcessServiceStub.User user = new ProcessServiceStub.User();
        user.setIdUser(currUserId);

        process.setHpmProcessId(hpmProcessId);
        process.setUser(user);

        ProcessServiceStub.GetProcessByHpmIdResponse response =
                stub.getProcessByHpmId(process);

        Process processResult = response.get_return();

        // long diffDays =
        // ProcessUtils.getDaysToProcessDue(processResult.getDateDue());

        // req.setAttribute(ProcessBrowserConstants.DIFF_DAYS, diffDays);

        // req.setAttribute(ProcessBrowserConstants.ATTR_PROCESS_FOUND,
        // processResult);

        return processResult;

    }
}
