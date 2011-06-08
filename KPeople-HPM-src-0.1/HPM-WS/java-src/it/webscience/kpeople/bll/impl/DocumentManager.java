package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.proxy.DocumentDAOProxy;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.document.DocumentDAO;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * Classe di business per la gestione dei documenti.
 */
public class DocumentManager implements IDocumentManager {
    /** logger. */
    private Logger logger;

    /** tipo evento associato al download del documenti.*/
    private static final String DOCUMENT_DOWNLOAD_ACTION_TYPE =
        "DOCUMENT.DOWNLOAD";

    /**
     * Costruttore di default.
     */
    public DocumentManager() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * salva nella tabella dello storico download l'evento di download corrente.
     * @param document documento scaricato
     * @param process processo a cui è associato il documento
     * @param user utente che effettua il download
     * @throws KPeopleBLLException eccezione durante l'elaborazione
     */
    @Override
    public final void saveDocumentDownloadHistory(
            final Document document,
            final Process process,
            final User user) throws KPeopleBLLException {
        logger.debug("start saveDocumentDownloadHistory");

        DocumentDAO documentDao = new DocumentDAO();
        UserDAO userDao = new UserDAO();

        try {
//          chiamata all'ega channel
            String xml = buildDocumentDownloadXml(
                  documentDao.getDocumentByIdAttachment(
                          document.getIdAttachment()),
                  process,
                  userDao.getUserByIdUser(user.getIdUser()));
            EgaChannelSender egaCs = new EgaChannelSender();
            egaCs.sendEvent(
                    xml,
                    "/kpeople/event/" + DOCUMENT_DOWNLOAD_ACTION_TYPE);

//          salvataggio sull'hpm
            DocumentDAOProxy dao = new DocumentDAOProxy();
            dao.saveDocumentDownloadHistory(document, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        logger.debug("end saveDocumentDownloadHistory");
    }

    /**
     * Costruisce l'xml da inviare all'ega channel per la notifica del download
     * del file.
     * @param document documento scaricato
     * @param process processo a cui è associato il documento
     * @param user utente che effettua il download
     * @return stringa xml
     * @throws Exception eccezione
     */
    private String buildDocumentDownloadXml(
            final Document document,
            final Process process,
            final User user) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("saveDocumentDownloadHistory - BEGIN");
        }

//      costruzione del Document DOM4J
        org.dom4j.Document dom4jDocument = DocumentHelper.createDocument();

//      creazione del figlio 'event'
        Element event = dom4jDocument.addElement("event")
            .addAttribute(
                "xmlns:xsi",
                "http://www.w3.org/2001/XMLSchema-instance")
            .addAttribute(
                 "id",
                 DOCUMENT_DOWNLOAD_ACTION_TYPE + "." + process.getHpmProcessId()
                     + "_" + new Date().getTime());

        buildActionElement(event, document, process, user);
        buildEventData(event, document, process, user);

//      pretty printing dell'xml su String
        StringWriter stringWriter = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(
                stringWriter,
                OutputFormat.createPrettyPrint());
        xmlWriter.write(dom4jDocument);
        String xml = stringWriter.toString();

        if (logger.isDebugEnabled()) {
            logger.debug("saveDocumentDownloadHistory - END. Xml:\n" + xml);
        }

        return xml;
    }

    /**
     * restituisce la data attuale nel formato yyyy-MM-dd'T'HH:mm:ss'.0Z'.
     * @return data formattata
     */
    private String getFormattedDate() {
        SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.0Z'");
        return formatter.format(new Date());
    }

    /**
     * costruisce l'elemento event-data dell'xml.
     * @param event elemento Event
     * @param document file da inviare
     * @param process processo associato
     * @param user user
     * @throws Exception eccezione generica
     */
    private void buildEventData(
            final Element event,
            final Document document,
            final Process process,
            final User user) throws Exception {
        logger.debug("buildEventData - begin");

        DocumentDAO documentDAO = new DocumentDAO();

        String title = "Download " + document.getName();

        User firstUploader = documentDAO.findFirstUploader(
                process.getHpmProcessId(),
                document.getHashcode());

        String bodyText =
            "L'utente " + user.getScreenName() + " ha effettuato il download"
            + " del file " + document.getName() + " inviato da "
            + firstUploader.getScreenName();

//      costruisco gli elementi xml
        Element eventData = event.addElement("event-data");
        eventData.addElement("creation-date").addText(getFormattedDate());
        eventData.addElement("title").addText(title);

        Element author = eventData.addElement("author");
        author.addElement("email").addText(firstUploader.getHpmUserId());

        Element body = eventData.addElement("body");
        body.addAttribute("type", "text");
        body.setText(bodyText);

//      aggiunta elemento 'kpeopletag' in 'properties'
        Element properties = eventData.addElement("properties");
        Element property = properties.addElement("property");
        property.addElement("key").addText("kpeopletag");
        property.addElement("value").addText(process.getHpmProcessId());

//      aggiungo i dettagli
        List<String> receiver = new ArrayList<String>();
        receiver.add(user.getHpmUserId());
        Element details = eventData.addElement("details");
        details.addText(createDetails(receiver, null, null));

        logger.debug("buildEventData - " + user.getScreenName());

        logger.debug("buildEventData - end");
    }

    /**
     * Crea il tag details contenente i destinatari della comunicazione.
     * @param rec destinatari in to
     * @param recCc destinatari in cc
     * @param recBcc destainatari in bcc
     * @return xml contenente i destinatari
     */
    private String createDetails(final List<String> rec,
            final List<String> recCc, final  List<String> recBcc) {
        org.dom4j.Document details = DocumentHelper.createDocument();
        Element root = details.addElement("email-details");

        Element receivers = root.addElement("receivers");

        createRecipientElement(rec, receivers, "receiver-to");
        createRecipientElement(recCc, receivers, "receiver-cc");
        createRecipientElement(recBcc, receivers, "receiver-bcc");

        return details.asXML();
    }

    /**
     * Crea un elemento di tipo "type" e inserisce i destinatari.
     * @param rec lista di destinatari
     * @param receivers elemento xml di tipo "receiver"
     * @param type tipo di recipient
     */
    private void createRecipientElement(final List<String> rec,
            final Element receivers, final String type) {
        if (rec != null) {
                for (String address : rec) {
                        Element receiver = receivers.addElement(type);
                        receiver.setText(address);
                }
        }
    }

    /**
     * Costruisce l'elemento action.
     * @param event elemento del documento dom4j
     * @param document documento scaricato
     * @param process processo associato al documento
     * @param user utente che effettua il download
     */
    private void buildActionElement(
            final Element event,
            final Document document,
            final Process process,
            final User user) {
        logger.debug("buildActionElement - begin");

        Element action = event.addElement("action");
        action.addElement("action-type")
            .addText(DOCUMENT_DOWNLOAD_ACTION_TYPE);

        String text =
            "http://hpm/" + process.getHpmProcessId()
            + "/" + document.getIdAttachment();

        action.addElement("action-reference")
            .addAttribute("type", "url")
            .addText(text);

        Element system = action.addElement("system");
        system.addElement("system-id").addText("hpm");
        system.addElement("system-type").addText("hpm");

        logger.debug("buildActionElement - end");
    }
}
