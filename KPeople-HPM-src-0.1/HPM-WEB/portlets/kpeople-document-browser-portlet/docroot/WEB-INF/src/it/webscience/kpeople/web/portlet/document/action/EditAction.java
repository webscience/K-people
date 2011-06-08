package it.webscience.kpeople.web.portlet.document.action;

import it.webscience.kpeople.web.portlet.document.util.Hashcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * Classe per l'upload di un documento
 * da associare ad un processo.
 */
public class EditAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil.getLog(ViewAction.class);

    /** tipo evento associato all'upload del documento. */
    private static final String DOCUMENT_UPLOAD_ACTION_TYPE =
        "PROCESS.DOCUMENT.UPLOAD";

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
        if (logger.isDebugEnabled()) {
            logger.debug("execute - begin");
        }

        if (req.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST)
                instanceof ActionRequest) {
            return processAction(mapping, form, req, res);
           } else {
            return render(mapping, form, req, res);
           }
    }

    /**
     * metodo render.
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param req The non-HTTP request we are processing
     * @param res The non-HTTP response we are creating
     * @return action forward
     */
    private ActionForward render(
            final ActionMapping mapping,
            final ActionForm form,
            final HttpServletRequest req,
            final HttpServletResponse res) {

        req.setAttribute("test", "1");

        return mapping.findForward("/document-browser/success");
    }

    /**
     * Restituisce la view della portlet.
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param req The non-HTTP request we are processing
     * @param res The non-HTTP response we are creating
     * @return action forward an exception.
     * @throws IOException eccezione generica
     */
    private ActionForward processAction(
            final ActionMapping mapping,
            final ActionForm form,
            final HttpServletRequest req,
            final HttpServletResponse res) throws IOException {

        ActionRequest portletRequest = (ActionRequest) req.getAttribute(
                JavaConstants.JAVAX_PORTLET_REQUEST);

        UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
                portletRequest);

        String cmd = ParamUtil.getString(uploadRequest, Constants.CMD);
        if (cmd.equals(Constants.ADD)) {
            addFile(uploadRequest, req);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("cmd: " + cmd);
            logger.debug("execute - end");
        }

        return render(mapping, form, uploadRequest, res);
    }

    /**
     * invia la richiesta di upload file.
     * @param uploadRequest request di tipo UploadPortletRequest
     * @param req The non-HTTP request we are processing
     * @throws IOException eccezione generica
     */
    private void addFile(
            final UploadPortletRequest uploadRequest,
            final HttpServletRequest req)
            throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("addFile - begin");
        }

//      recupero i dati dalla request
        String hpmProcessId =
            ParamUtil.getString(uploadRequest, "hpmProcessId");
        File file = uploadRequest.getFile("file");


        
        String actionReference = req.getRequestURI();
        
        hpmProcessId = "rda1101";

//      costruisco l'xml e lo invio all'ega channel
//        String xml = buildXml(hpmProcessId, file, req);
//        callEgaChannelSender(xml);

        if (logger.isDebugEnabled()) {
            logger.debug("upload del file " + file.getName());
            logger.debug("addFile - end");
        }
    }

    /**
     * restituisce lo screen name dell'utente connesso.
     * @return screen name
     * @param req The non-HTTP request we are processing
     * @throws IOException eccezione legata al recupero dello screen name
     */
    private String getScreenName(final HttpServletRequest req)
        throws IOException {
        String remoteUserId = req.getRemoteUser();
        User user = null;
        try {
            user = UserLocalServiceUtil.getUserById(
                    Long.parseLong(remoteUserId));
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }

        return user.getScreenName();
    }

    /**
     * costruisce l'id dell'evento da inviare nell'xml.
     * @return id dell'evento
     */
    private String getEventId() {
        String id = "hpm.process.document.upload.";

        id += new Date().getTime();

        return id;
    }


    /**
     * genera l'hashcode di un file.
     * @param file file da analizzare
     * @return hashcode
     * @throws IOException eccezione durante la generazione dell'hashcode
     */
    private String generateHashcode(
            final File file) throws IOException {
        String hashcode = null;
        try {
            hashcode = Hashcode.getHashcode(
                    new FileInputStream(file),
                    Hashcode.SHA512);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }

        return hashcode;
    }

    /**
     * estrae il tipo di attachment dal file.
     * @param file file
     * @return tipo di attachment
     */
    private String getAttachmentType(final File file) {
        String[] split = file.getName().split("\\.");

        return split[split.length - 1];
    }

    /**
     * costruisce l'id associato all'upload del documento da caricare.
     * Formato da:
     * - prefisso: hpm.process.document.upload.
     * - timestamp
     * - hashcode del file : primi 5 caratteri
     * @param hashcode del documento
     * @return id dell'evento
     */
    private String getAttachmentId(final String hashcode) {
        String eventId = "hpm.process.document.upload.";
        eventId += new Date().getTime();
        eventId += "-" + hashcode.substring(0, 5);

        return eventId;
    }

}
