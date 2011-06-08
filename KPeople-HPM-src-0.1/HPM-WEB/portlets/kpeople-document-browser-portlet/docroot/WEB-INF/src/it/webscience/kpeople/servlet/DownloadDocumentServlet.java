package it.webscience.kpeople.servlet;

import it.webscience.kpeople.service.document.DocumentServiceStub;
import it.webscience.kpeople.service.document.DocumentServiceStub.SaveDocumentDownloadHistory;
import it.webscience.kpeople.storage.OcStorageUtility;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.document.util.DocumentConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet di download di un documento.
 *
 */
public class DownloadDocumentServlet extends HttpServlet  {

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * serial.
     */
    private static final long serialVersionUID = 6927894170917533223L;

    /**
     * risposta alla chiamata della servlet.
     * @param req http servlet request
     * @param res http servlet response
     * @throws IOException eccezione durante l'elaborazione
     */
    @Override
    protected final void doGet(
            final HttpServletRequest req,
            final HttpServletResponse res)
            throws IOException {

//      invoca il document service per il salvataggio dello storico download
        try {
            saveDownloadHistory(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String guid = req.getParameter(
                DocumentConstants.DOWNLOAD_DOCUMENT_GUID);

//      recupero il nome del file per inviarlo nell'header
        String[] split = guid.split("/");
        String fileName = URLEncoder.encode(split[split.length - 1], "UTF-8");

        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        logger.debug("download del file: " + fileName);

//      recupero l'input stream del documento da inviare
        InputStream in = OcStorageUtility.getEventAttacchment(guid);

//      scrivo in out il binario
        OutputStream out = res.getOutputStream();
        int nextChar;
        while ((nextChar = in.read()) != -1) {
            out.write(nextChar);
        }

//      chiudo le connessioni e gli streams
        in.close();
        out.flush();
        out.close();
    }

    /**
     * @param req request
     * @throws Exception eccezione durante l'invocazione dello stub
     */
    private void saveDownloadHistory(
            final HttpServletRequest req)
            throws Exception {
        int idAttachment = Integer.parseInt(req.getParameter(
                DocumentConstants.DOCUMENT_ID_ATTACHMENT));

        try {
//          recupero utente e hpmProcessId dalla sessione
            Integer idUser = (Integer) req.getSession().getAttribute(
                    DocumentConstants.DOCUMENT_PORTLET_SESSION_USER);

            String hpmProcessId = (String) req.getSession().getAttribute(
                    DocumentConstants.DOCUMENT_PORTLET_SESSION_HPMPROCESSID);

            String targetEndpoint = new StubUtil().buildTarget(
                    PropsKeys.WSO2SERVER_SERVICE_DOCUMENT_ENDPOIINT);
            DocumentServiceStub stub = new DocumentServiceStub(targetEndpoint);

            DocumentServiceStub.User user = new DocumentServiceStub.User();
            user.setIdUser(idUser);

            DocumentServiceStub.Document doc =
                new DocumentServiceStub.Document();
            doc.setIdAttachment(idAttachment);

            DocumentServiceStub.Process process =
                new DocumentServiceStub.Process();
            process.setHpmProcessId(hpmProcessId);

            SaveDocumentDownloadHistory sdh = new SaveDocumentDownloadHistory();
            sdh.setUser(user);
            sdh.setDocument(doc);
            sdh.setProcess(process);

            stub.saveDocumentDownloadHistory(sdh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
