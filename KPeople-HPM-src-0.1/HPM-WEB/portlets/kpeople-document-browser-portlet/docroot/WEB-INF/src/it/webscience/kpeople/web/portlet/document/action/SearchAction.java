package it.webscience.kpeople.web.portlet.document.action;

import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.document.DocumentServiceStub;
import it.webscience.kpeople.service.document.DocumentServiceStub.Document;
import it.webscience.kpeople.service.document.DocumentServiceStub.DocumentFilter;
import it.webscience.kpeople.service.document.DocumentServiceStub.FindDocuments;
import it.webscience.kpeople.service.document.DocumentServiceStub.User;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.document.action.converter.DocumentConverter;
import it.webscience.kpeople.web.portlet.document.util.DocumentConstants;

import java.util.ArrayList;

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
 * Struts action per la ricerca dei documenti.
 */
public class SearchAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil.getLog(SearchAction.class);

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

        return viewDocumentsPerProcess(mapping, req);
    }

    /**
     * genera la tabella dei documenti associati ad un processo.
     * @param mapping The ActionMapping used to select this instance
     * @param req The non-HTTP request we are processing
     * @return generazione della tabella dei documenti associati a processo
     * @throws Exception eccezione legata all'estrazione dei dati
     */
    private ActionForward viewDocumentsPerProcess(
            final ActionMapping mapping,
            final HttpServletRequest req) throws Exception {
        String hpmProcessId = ParamUtil.getString(
                req, "processId");

//      inserisco in sessione i valori di User ed hpmProcessId
        req.getSession().setAttribute(
                DocumentConstants.DOCUMENT_PORTLET_SESSION_HPMPROCESSID,
                hpmProcessId);

        req.getSession().setAttribute(
                DocumentConstants.DOCUMENT_PORTLET_SESSION_USER,
                new Integer(KpeopleUserUtil.getCurrUserId(req)));

        logger.debug("Ricerca dei documenti associato al processo "
                + hpmProcessId);

        FindDocuments findDocuments = new FindDocuments();
        findDocuments.setFilter(new DocumentFilter());
        findDocuments.setUser(new User());
        findDocuments.getFilter().setHpmProcessId(hpmProcessId);

        String targetEndpoint = new StubUtil().buildTarget(
                PropsKeys.WSO2SERVER_SERVICE_DOCUMENT_ENDPOIINT);

        DocumentServiceStub stub = new DocumentServiceStub(targetEndpoint);
        Document[] documents = stub.findDocuments(findDocuments).get_return();
        if (documents != null) {
//            patch per nome documento: se non presente, lo estraggo dall'url
            for (Document document : documents) {
                if (document.getGuid() != null && document.getName() == null) {
                    int index = document.getGuid().lastIndexOf("/");
                    document.setName(document.getGuid().substring(index + 1));
                }
            }

            req.setAttribute("documents", DocumentConverter.toBE(documents));
            logger.debug("N. documenti trovati: " + documents.length);
        } else {
            req.setAttribute(
                    "documents",
                    new ArrayList<DocumentServiceStub.Document>());
            logger.debug("Nessun documento trovato");
        }

        return mapping.findForward("/document-browser/documentsPerProcess");
    }
}
