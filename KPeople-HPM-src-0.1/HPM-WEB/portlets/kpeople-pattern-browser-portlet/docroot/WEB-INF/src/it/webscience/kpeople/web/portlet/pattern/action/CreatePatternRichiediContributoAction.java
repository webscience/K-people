package it.webscience.kpeople.web.portlet.pattern.action;

import it.webscience.kpeople.KpeoplePortalConstants;
import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.exception.KpeopleProcessNameException;
import it.webscience.kpeople.service.pattern.PatternServiceStub;
import it.webscience.kpeople.service.pattern.PatternServiceStub.Pattern;
import it.webscience.kpeople.service.pattern.PatternServiceStub.PatternType;
import it.webscience.kpeople.service.process.ProcessServiceStub;
import it.webscience.kpeople.service.process.ProcessServiceStub.Process;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.pattern.util.Hashcode;
import it.webscience.kpeople.web.portlet.pattern.util.PatternBrowserConstants;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;

/**
 * Struts action per la ricerca dei processi.
 */
public class CreatePatternRichiediContributoAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil
            .getLog(CreatePatternRichiediContributoAction.class);

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

        if (req.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST) instanceof ActionRequest) {
            ActionRequest portletRequest =
                    (ActionRequest) req
                            .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
            UploadPortletRequest uploadRequest =
                    PortalUtil.getUploadPortletRequest(portletRequest);


            String redirectDettaglio =
                    ParamUtil.getString(uploadRequest, "redirectDettaglio");
            PortletRequest aReq =
                    (PortletRequest) req
                            .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
            aReq.setAttribute("redirectDettaglio", redirectDettaglio);
            
            
            String command = ParamUtil.getString(uploadRequest, Constants.CMD);
            if (command.equalsIgnoreCase(Constants.ADD)) {
                try {
                    return processActionRichiestaContributo(mapping, form, req,
                            res, uploadRequest);
                } catch (Exception e) {
                    if (e instanceof KpeopleProcessNameException) {
                        SessionErrors.add(portletRequest, e.getClass()
                                .getName(), e);
                        return mapping.findForward("/pattern-browser/view");
                    }
                }
            }
        }

        return processAction(mapping, form, req, res);
    }

    /**
     * processa la richiesta di creazione pattern.
     * @param mapping
     *            mapping
     * @param form
     *            form
     * @param req
     *            http servlet request
     * @param res
     *            http servlet reseponse
     * @param uploadRequest
     *            uploadRequest
     * @return forward
     * @throws Exception
     *             eccezione durante l'elaborazione
     */
    private ActionForward processActionRichiestaContributo(
            final ActionMapping mapping, final ActionForm form,
            final HttpServletRequest req, final HttpServletResponse res,
            final UploadPortletRequest uploadRequest) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("processAction - begin");
        }

        String redirectDettaglio =
                ParamUtil.getString(uploadRequest, "redirectDettaglio");
        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
        aReq.setAttribute("redirectDettaglio", redirectDettaglio);
        
        
        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_PATTERN_ENDPOINT);

        PatternServiceStub stub = new PatternServiceStub(targetEndpoint);

        PatternServiceStub.StartPattern startPattern =
                new PatternServiceStub.StartPattern();

        PatternServiceStub.Pattern psPattern = new PatternServiceStub.Pattern();

        // create patternType
        String patternTypeId =
                ParamUtil.getString(uploadRequest, "idPatternType");
        PatternType patternType =
                getPatternType(mapping, form, uploadRequest, res, patternTypeId);
        psPattern.setPatternType(patternType);

        // set requestor
        PatternServiceStub.User psUserRequestor = new PatternServiceStub.User();
        psUserRequestor.setIdUser(KpeopleUserUtil.getCurrUserId(uploadRequest));
        psUserRequestor.setHpmUserId(KpeopleUserUtil
                .getCurrUserHpmId(uploadRequest));
        psPattern.setPatternRequestor(psUserRequestor);

        // set user to
        PatternServiceStub.User psUserProvider = new PatternServiceStub.User();
        String patternProvider =
                ParamUtil.getString(uploadRequest, "patternProvider");

        if (Validator.isNull(patternProvider)) {
            throw new KpeopleProcessNameException();
        }
        psUserProvider.setHpmUserId(patternProvider);
        psPattern.setPatternProvider(psUserProvider);

        // set user cc
        String userCcMail = ParamUtil.getString(uploadRequest, "patternUserCc");
        if (!userCcMail.equals("")) {
            PatternServiceStub.User psUserCc = new PatternServiceStub.User();
            String patternCc = userCcMail;
            psUserCc.setHpmUserId(patternCc);

            PatternServiceStub.User[] ccUser = new PatternServiceStub.User[1];
            ccUser[0] = psUserCc;
            psPattern.setCcUsers(ccUser);
        }

        // create process
        String hpmProcessId =
                ParamUtil.getString(uploadRequest, "hpmProcessId");
        PatternServiceStub.Process psProcess = new PatternServiceStub.Process();
        psProcess.setHpmProcessId(hpmProcessId);

        // create attachmentType
        PatternServiceStub.AttachmentType psAttType =
                new PatternServiceStub.AttachmentType();
        psAttType.setIdAttachmentType(3);
        psAttType.setName("pattern");
        psPattern.setAttachmentType(psAttType);

        // create PatternState
        PatternServiceStub.PatternState psPatternState =
                new PatternServiceStub.PatternState();
        psPatternState.setIdPatternState(1);
        psPatternState.setState("Inviata");
        psPattern.setPatternState(psPatternState);

        PatternServiceStub.PatternMetadata[] patternMetadata =
                new PatternServiceStub.PatternMetadata[1];
        PatternServiceStub.PatternMetadata metadata =
                new PatternServiceStub.PatternMetadata();
        String requestTypeValue =
                ParamUtil.getString(uploadRequest, "requestType");
        metadata.setKeyname(PatternBrowserConstants.PATTERN_REQUEST_TYPE);
        metadata.setValue(requestTypeValue);
        metadata.setActivitiProcessMetadata(false);
        patternMetadata[0] = metadata;
        psPattern.setPatternMetadatas(patternMetadata);

        if (ParamUtil.getString(uploadRequest, "patternTitle") != null) {
            String patternName =
                    ParamUtil.getString(uploadRequest, "patternTitle");
            psPattern.setName(patternName);
        }
        if (ParamUtil.getString(uploadRequest, "patternDescription") != null) {
            String patternDescription =
                    ParamUtil.getString(uploadRequest, "patternDescription");
            psPattern.setDescription(patternDescription);
        }

        Calendar calendar = Calendar.getInstance();
        // get a date to represent "today"
        Date currentDate = calendar.getTime();
        String dueDate = ParamUtil.getString(uploadRequest, "dueDateString");
        if (Validator.isNotNull(dueDate)) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date patternDueDate =
                    ParamUtil.getDate(uploadRequest, "dueDateString", format,
                            null);
            if (patternDueDate.compareTo(currentDate) <= 0) {
                throw new KpeopleProcessNameException();
            } else {
                psPattern.setDueDate(patternDueDate);
            }
        }

        psPattern.setStartDate(currentDate);

        // recupero gli attachments
        setPatternDoclist(psPattern, uploadRequest);

        startPattern.setPLoggedUser(psUserRequestor);
        startPattern.setPPattern(psPattern);
        startPattern.setPProcess(psProcess);

        PatternServiceStub.StartPatternResponse response =
                stub.startPattern(startPattern);

        response.get_return();

        if (logger.isDebugEnabled()) {
            logger.debug("processAction - end");
        }

        // PortletPreferences preferences =
        // PortletPreferencesFactoryUtil.getPreferences(req);
        //
        // String destinationPageParam =
        // preferences.getValue("destination-page", StringPool.BLANK);
        // String friendlyUrl =
        // preferences
        // .getValue("friendly-url-dettaglio", StringPool.BLANK);
        //

        res.sendRedirect(redirectDettaglio);

        return null;
    }

    /**
     * Recupero degli attachments per un pattern.
     * @param psPattern
     *            pattern
     * @param uploadRequest
     *            request
     * @throws Exception
     *             eccezione durante il set della lista documenti
     */
    @SuppressWarnings("unchecked")
    private void setPatternDoclist(final Pattern psPattern,
            final UploadPortletRequest uploadRequest) throws Exception {

        List<PatternServiceStub.Document> docList =
                new ArrayList<PatternServiceStub.Document>();

        Enumeration<String> en = uploadRequest.getParameterNames();
        while (en.hasMoreElements()) {
            String parameter = en.nextElement();

            if (parameter.indexOf("pattern_newFile") != -1) {
                File file = uploadRequest.getFile(parameter);
                if (file.length() > 0) {
                    PatternServiceStub.Document doc =
                            new PatternServiceStub.Document();

                    String hashcode =
                            Hashcode.getHashcode(new FileInputStream(file),
                                    Hashcode.SHA512);

                    doc.setData(new DataHandler(new FileDataSource(file)));
                    doc.setHashcode(hashcode);
                    doc.setFileName(uploadRequest.getFileName(parameter));

                    docList.add(doc);
                }
            }
        }

        psPattern.setDocList((PatternServiceStub.Document[]) docList
                .toArray(new PatternServiceStub.Document[0]));
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

        return mapping.findForward("/pattern-browser/create-pattern");
    }

    private PatternType getPatternType(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res, final String patternTypeId)
            throws Exception {
        PatternType[] patternTypes = getPatternTypes(mapping, form, req, res);

        PatternType result = null;
        for (PatternType pt : patternTypes) {
            int id = pt.getIdPatternType();
            if (String.valueOf(id).equals(patternTypeId)) {
                result = pt;
            }
        }
        return result;
    }

    private PatternType[] getPatternTypes(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("processAction - begin");
        }

        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
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
        return patternTypeResults;

    }

    private Process getProcess(String hpmProcessId, HttpServletRequest req)
            throws Exception {

        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys.WSO2SERVER_SERVICE_PROCESS_ENDPOINT);

        ProcessServiceStub stub = new ProcessServiceStub(targetEndpoint);

        ProcessServiceStub.GetProcessByHpmId process =
                new ProcessServiceStub.GetProcessByHpmId();

        ProcessServiceStub.User user = new ProcessServiceStub.User();
        user.setIdUser(KpeopleUserUtil.getCurrUserId(req));

        process.setHpmProcessId(hpmProcessId);
        process.setUser(user);

        ProcessServiceStub.GetProcessByHpmIdResponse response =
                stub.getProcessByHpmId(process);

        Process processResult = response.get_return();
        return processResult;
    }
}
