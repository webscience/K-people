package it.webscience.kpeople.web.portlet.activities.action;

import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.activity.ActivityServiceStub;
import it.webscience.kpeople.service.activity.ActivityServiceStub.Activity;
import it.webscience.kpeople.service.activity.ActivityServiceStub.Pattern;
import it.webscience.kpeople.service.activity.ActivityServiceStub.PatternState;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.activities.util.ActivitiesBrowserConstants;
import it.webscience.kpeople.web.portlet.activities.util.Hashcode;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * Struts action per la ricerca dei processi.
 */
public class InviaContributo extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil
            .getLog(InviaContributo.class);

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

        return inviaContributo(mapping, form, req, res);
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
    @SuppressWarnings("unchecked")
    private ActionForward inviaContributo(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("inviaContributo - begin");
        }

        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
        String redirectUrl = ParamUtil.getString(req, ActivitiesBrowserConstants.REDIRECT_URL);
        req.setAttribute(ActivitiesBrowserConstants.REDIRECT_URL, redirectUrl);

        UploadPortletRequest uploadRequest =
            PortalUtil.getUploadPortletRequest(aReq);

        Pattern currPattern =
                (Pattern) aReq.getPortletSession().getAttribute(
                        ActivitiesBrowserConstants.CURR_PATTERN);
        Activity currActivity =
                (Activity) aReq.getPortletSession().getAttribute(
                        ActivitiesBrowserConstants.CURR_ACTIVITY);

        PatternState pState = null;
        if (currPattern.getPatternState() != null) {
            pState = currPattern.getPatternState();
        } else {
            pState = new PatternState();
        }
        pState.setIdPatternState(
                ActivitiesBrowserConstants.RICHIESTA_CONTRIBUTO_STATE_RICEVUTO);
        currPattern.setPatternState(pState);

//        String currActivityHpmId =
//                ParamUtil.getString(uploadRequest,
//                        ActivitiesBrowserConstants.CURR_ACTIVITY_HPM_ID);
//
//        String currActivityTaskId =
//                ParamUtil.getString(uploadRequest,
//                        ActivitiesBrowserConstants.CURR_ACTIVITY_TASK_ID);

//      recupero gli attachments
        List<ActivityServiceStub.Document> docList =
            new ArrayList<ActivityServiceStub.Document>();

        Enumeration<String> en = uploadRequest.getParameterNames();
        while (en.hasMoreElements()) {
            String parameter = en.nextElement();

            if (parameter.indexOf("pattern_newFile") != -1) {
                File file = uploadRequest.getFile(parameter);
                if (file.length() > 0) {
                    ActivityServiceStub.Document doc =
                        new ActivityServiceStub.Document();

                    String hashcode = Hashcode.getHashcode(
                            new FileInputStream(file),
                            Hashcode.SHA512);

                    doc.setData(new DataHandler(new FileDataSource(file)));
                    doc.setHashcode(hashcode);
                    doc.setFileName(uploadRequest.getFileName(parameter));

                    docList.add(doc);
                }
            }
        }

        ActivityServiceStub.User psUser = new ActivityServiceStub.User();
        psUser.setIdUser(KpeopleUserUtil.getCurrUserId(req));
        psUser.setHpmUserId(KpeopleUserUtil.getCurrUserHpmId(req));

//        ActivityServiceStub.Activity psActivity =
//                new ActivityServiceStub.Activity();
//        psActivity.setActivitiProcessTaskId(currActivityTaskId);
//        psActivity.setHpmActivityId(currActivityHpmId);
        currActivity.setDocList((ActivityServiceStub.Document[]) docList
                .toArray(new ActivityServiceStub.Document[0]));

        ActivityServiceStub.ActivityMetadata[] arrayMetadata =
                new ActivityServiceStub.ActivityMetadata[1];

        ActivityServiceStub.ActivityMetadata metadata =
                new ActivityServiceStub.ActivityMetadata();
        metadata.setKeyname(
               ActivitiesBrowserConstants.RICHIESTA_CONTRIBUTO_DESCRIZIONE_INVIO);
        String patternDescription = "";
        if (ParamUtil.getString(uploadRequest, "patternDescription") != null) {
            patternDescription =
                    ParamUtil.getString(uploadRequest, "patternDescription");
        }
        metadata.setValue(patternDescription);
        metadata.setActivitiProcessMetadata(true);
        arrayMetadata[0] = metadata;

        currActivity.setActivityMetadata(arrayMetadata);
        currActivity.setPattern(currPattern);
        currActivity.setActivityType(currActivity.getActivityType());

        String targetEndpoint = new StubUtil().buildTarget(
                PropsKeys.WSO2SERVER_SERVICE_ACTIVITY_ENDPOINT);

        ActivityServiceStub stub = new ActivityServiceStub(targetEndpoint);

        ActivityServiceStub.ExecuteActivity excuteActivities =
                new ActivityServiceStub.ExecuteActivity();

        excuteActivities.setPLoggedUser(psUser);
        excuteActivities.setPActivity(currActivity);

        ActivityServiceStub.ExecuteActivityResponse response =
                stub.executeActivity(excuteActivities);

        Activity activity = response.get_return();

        uploadRequest.setAttribute(ActivitiesBrowserConstants.CURR_ACTIVITY,
                currActivity);
        if (logger.isDebugEnabled()) {
            logger.debug("inviaContributo - end");
        }


        
        return mapping
        .findForward("/activities-browser/view-activity-details/richiestacontributo_inviata");
    }
}
