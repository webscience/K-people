package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.ActivityMetadata;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;

import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMText;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * manager per l'invio di un evento. Es. 'Avvio di un pattern'.
 * @author dellanna
 */
public class SendEventManager implements ISendEventManager {

    /** tipo evento associato all'upload del documento. */
    private static final String DOCUMENT_UPLOAD_ACTION_TYPE =
        "PROCESS.DOCUMENT.UPLOAD";

    /** tipo evento associato all'accettaione di una richiesta di contributo.*/
    private static final String PROCESS_PATTERN_RICHIESTACONTRIBUTO_ACCETTA =
        "PROCESS.PATTERN.RICHIESTACONTRIBUTO.ACCETTA";

    /** tipo evento associato al rifiuto di una richiesta di contributo.*/
    private static final String PROCESS_PATTERN_RICHIESTACONTRIBUTO_RIFIUTATA =
        "PROCESS.PATTERN.RICHIESTACONTRIBUTO.RIFIUTATA";

    /** tipo evento associato all'invio del contributo.*/
    private static final String PROCESS_PATTERN_RICHIESTACONTRIBUTO_INVIO =
        "PROCESS.PATTERN.RICHIESTACONTRIBUTO.INVIO";

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Invia un evento.
     * @param pattern pattern
     * @param pUser user
     * @param pProcess process
     * @throws Exception eccezione durante l'invio dell'evento all'ega
     */
    @Override
    public final void sendEventCreatePattern(
            final Pattern pattern,
            final User pUser,
            final Process pProcess) throws Exception {

        try {
            String xml = buildXml(pattern, pUser, pProcess);

            logger.debug("Xml : " + xml);

            EgaChannelSender egaCs = new EgaChannelSender();
            egaCs.sendEvent(
                    xml,
                    "/kpeople/event/" + DOCUMENT_UPLOAD_ACTION_TYPE);
        } catch (Exception e) {
            logger.fatal(e.getMessage());
            throw e;
        }
    }

    /**
     * Invia un evento.
     * @param activity attività
     * @throws Exception eccezione durante l'invio dell'evento all'ega
     */
    @Override
    public final void sendEventAcceptContribute(
            final Activity activity) throws Exception {

        try {
            String esitoAccettazione = activity.
                getActivityMetadataValueByKeyname("contributeAccepted");
            String actionType = "";

            logger.debug("esitoAccettazione : " + esitoAccettazione);

            if ("true".equals(esitoAccettazione)) {
                actionType = PROCESS_PATTERN_RICHIESTACONTRIBUTO_ACCETTA;
            } else {
                actionType = PROCESS_PATTERN_RICHIESTACONTRIBUTO_RIFIUTATA;
            }
            String xml = buildXml(activity, actionType,
                    activity.getActivityOwner(), activity.getPattern());

            logger.debug("Xml : " + xml);

            EgaChannelSender egaCs = new EgaChannelSender();
            egaCs.sendEvent(xml, "/kpeople/event/" + actionType);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Crea l'xml per l'accettazione di richiesta/invio contributo.
     * @param activity attività
     * @param user contributor
     * @param pattern pattern dell'attività
     * @return xml da inviare all'epe
     * @throws Exception errore nella crazione dell'xml
     */
    private String buildXml(final Activity activity, final String actionType,
            final User user, final Pattern pattern) throws Exception {
        logger.debug("build xml - begin");

//      costruzione del Document DOM4J
        Document document = DocumentHelper.createDocument();

//      creazione del figlio 'event'
        Element event = document.addElement("event")
            .addAttribute(
                "xmlns:xsi",
                "http://www.w3.org/2001/XMLSchema-instance")
            .addAttribute("id", actionType + "." + activity.getHpmActivityId());

        buildActionElement(event, activity, actionType, pattern);
        buildEventData(
                event,
                activity,
                actionType,
                pattern,
                user,
                activity.getActivityRequestor());

//      pretty printing dell'xml su String
        StringWriter stringWriter = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(
                stringWriter,
                OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        String xml = stringWriter.toString();

        logger.debug("build xml - begin");
        logger.debug("Xml: " + xml);

        return xml;
    }

    /**
     * Estrae il valore del metadato 'descrizioneInvio'.
     * @param activity acrivity
     * @return descrizione del rifiuto
     */
    private String getDescrizioneInvio(final Activity activity) {
        logger.debug("getDescrizioneInvio - begin");

        String descrizioneInvio = "";

        for (ActivityMetadata am : activity.getActivityMetadata()) {
            if (am.getKeyname().equals("descrizioneInvio")) {
                descrizioneInvio = am.getValue();
                break;
            }
        }

        logger.debug("getDescrizioneInvio - end");
        logger.debug("descrizioneInvio: " + descrizioneInvio);

        return descrizioneInvio;
    }

    /**
     * Estrae il valore del metadato 'descrizioneRifiuto'.
     * @param activity acrivity
     * @return descrizione del rifiuto
     */
    private String getDescrizioneRifiuto(final Activity activity) {
        logger.debug("getDescrizioneRifiuto - begin");

        String descrizioneRifiuto = "";

        for (ActivityMetadata am : activity.getActivityMetadata()) {
            if (am.getKeyname().equals("descrizioneRifiuto")) {
                descrizioneRifiuto = am.getValue();
                break;
            }
        }

        logger.debug("getDescrizioneRifiuto - end");
        logger.debug("descrizioneRifiuto: " + descrizioneRifiuto);

        return descrizioneRifiuto;
    }

    private void buildEventData(
            final Element event,
            final Activity activity,
            final String actionType,
            final  Pattern pattern,
            final User user,
            final User requestor) {
        logger.debug("buildEventData - begin");

        Element eventData = event.addElement("event-data");

//      costruisco gli elementi xml
        eventData.addElement("creation-date").addText(getFormattedDate());

        String title = "";
        if (actionType.equals(PROCESS_PATTERN_RICHIESTACONTRIBUTO_ACCETTA)) {
            title = "Richiesta contributo accettata";
        } else if (actionType.equals(PROCESS_PATTERN_RICHIESTACONTRIBUTO_RIFIUTATA)) {
            title = "Richiesta contributo rifiutata";
        } else if (actionType.equals(PROCESS_PATTERN_RICHIESTACONTRIBUTO_INVIO)) {
            title = "Invio contributo";
        }
        eventData.addElement("title").addText(title);

        logger.debug(
                "buildEventData -"
                + getFormattedDate()
                + " - " + pattern.getName());
        logger.debug("email: " + user.getHpmUserId());

        Element author = eventData.addElement("author");
        author.addElement("email").addText(user.getHpmUserId());

        logger.debug("buildEventData - " + user.getScreenName());

//      gestione del metadato 'descrizioneRifiuto' nel caso di rifiuto richiesta
        if (actionType.equals(PROCESS_PATTERN_RICHIESTACONTRIBUTO_RIFIUTATA)) {
            Element body = eventData.addElement("body");
            body.addAttribute("type", "html");
            body.addText(getDescrizioneRifiuto(activity));
        } else if (actionType.equals(PROCESS_PATTERN_RICHIESTACONTRIBUTO_INVIO)) {
            Element body = eventData.addElement("body");
            body.addAttribute("type", "html");
            body.addText(getDescrizioneInvio(activity));
        }

        Element properties = eventData.addElement("properties");

//      aggiunta elemento 'kpeopletagpattern'
        Element property2 = properties.addElement("property");
        property2.addElement("key").addText("kpeopletagpattern");
        property2.addElement("value").addText(pattern.getHpmPatternId());

        List<String> receivers = new ArrayList<String>();
        receivers.add(requestor.getHpmUserId());
        List<String> receiverCc = new ArrayList<String>();
        receiverCc.add(user.getHpmUserId());

        Element details = eventData.addElement("details");
        details.addText(createDetails(receivers, receiverCc, null));

//      aggiungo l'xml relativo agli attachments... se presenti
        if (activity.getDocList() != null && activity.getDocList().size() > 0) {
            Element attachments = eventData.addElement("attachments");

            List<it.webscience.kpeople.be.Document> docList =
                activity.getDocList();
            for (it.webscience.kpeople.be.Document doc : docList) {
                logger.debug("buildEventData - " + doc.getHashcode());
                logger.debug("buildEventData - " + doc.getFileName());
                logger.debug("buildEventData - " + doc.getData().length);
                logger.debug("buildEventData - " + doc.getHashcode());

                Element attachment =
                    attachments.addElement("attachment")
                    .addAttribute("id", getAttachmentId(doc.getHashcode()));

                attachment.addElement("attachment-type").addText(
                        getAttachmentType(doc.getFileName()));
                attachment.addElement("attachment-data").addText(
                        generateOmText(doc.getData()));
                attachment.addElement("attachment-name").addText(
                        doc.getFileName());
                attachment.addElement("hashcode").addText(
                        doc.getHashcode());
            }
        }

        logger.debug("buildEventData - " + pattern.getHpmPatternId());

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
        Document details = DocumentHelper.createDocument();
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
     * @param event elemento event
     * @param activity attività
     * @param pattern pattern
     */
    private void buildActionElement(final Element event,
            final Activity activity, final String actionType,
            final Pattern pattern) {
        logger.debug("buildActionElement - begin");

        Element action = event.addElement("action");
        action.addElement("action-type")
            .addText(actionType);

        String text =
            "http://hpm/" + pattern.getHpmPatternId()
            + "/" + activity.getHpmActivityId();

        action.addElement("action-reference")
            .addAttribute("type", "url")
            .addText(text);

        Element system = action.addElement("system");
        system.addElement("system-id").addText("hpm");
        system.addElement("system-type").addText("hpm");

        logger.debug("buildActionElement - end");
    }

    /**
     * costruisce l'elemento action dell'xml.
     * @param event elemento Event
     * @param pPattern pattern
     * @param pProcess process
     */
    private void buildActionElement(
            final Element event,
            final Pattern pPattern,
            final Process pProcess) {
        logger.debug("buildActionElement - begin");

        Element action = event.addElement("action");
        action.addElement("action-type")
            .addText("PROCESS.PATTERN.CREATE.ATTACHDOCUMENT");

        String text =
            "http://hpm/" + pProcess.getHpmProcessId()
            + "/" + pPattern.getHpmPatternId();

        action.addElement("action-reference")
            .addAttribute("type", "url")
            .addText(text);

        Element system = action.addElement("system");
        system.addElement("system-id").addText("hpm");
        system.addElement("system-type").addText("hpm");

        logger.debug("buildActionElement - end");
    }

    /**
     * Costruisce l'xml associato all'evento.
     * @param pPattern pattern
     * @param pUser user
     * @param pProcess process
     * @return xml da inviare
     * @throws Exception eccezione durante l'elaborazione
     */
    private String buildXml(
            final Pattern pPattern,
            final User pUser,
            final Process pProcess) throws Exception {

        logger.debug("build xml - begin");

//      costruzione del Document DOM4J
        Document document = DocumentHelper.createDocument();

//      creazione del figlio 'event'
        Element event = document.addElement("event")
            .addAttribute(
                "xmlns:xsi",
                "http://www.w3.org/2001/XMLSchema-instance")
            .addAttribute(
                "id",
                "PROCESS.PATTERN.CREATE.ATTACHDOCUMENT."
                    + pPattern.getHpmPatternId());

        buildActionElement(event, pPattern, pProcess);
        buildEventData(event, pPattern, pProcess, pUser);

//      pretty printing dell'xml su String
        StringWriter stringWriter = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(
                stringWriter,
                OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        String xml = stringWriter.toString();

        logger.debug("build xml - begin");
        logger.debug("Xml: " + xml);

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
     * genera l'OM dal file.
     * @param data file da analizzare
     * @return testo OM
     */
    private String generateOmText(final byte[] data) {
        OMFactory factory = OMAbstractFactory.getOMFactory();

        ByteArrayDataSource ds = new ByteArrayDataSource(data);
        DataHandler dataHandler = new DataHandler(ds);
        OMText textData = factory.createOMText(dataHandler, true);

        return textData.getText();
    }

    /**
     * estrae il tipo di attachment dal file.
     * @param filename nome del file
     * @return tipo di attachment
     */
    private String getAttachmentType(final String filename) {
        String[] split = filename.split("\\.");

        return split[split.length - 1];
    }

    /**
     * ritorna il titolo legato al file.
     * Per titolo si intende: nome del file senza estensione.
     * @param filename nome del file
     * @return titolo del file
     */
    private String getFileTitle(final String filename) {
        int index = filename.lastIndexOf(".");

        String title = filename;
        if (index != -1) {
            title = filename.substring(0, index);
        }

        return title;
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

    /**
     * costruisce l'elemento event-data dell'xml.
     * @param event elemento Event
     * @param pattern file da inviare
     * @param pProcess process
     * @param user user
     * @throws Exception eccezione generica
     */
    private void buildEventData(
            final Element event,
            final Pattern pattern,
            final Process pProcess,
            final User user) throws Exception {
        logger.debug("buildEventData - begin");

        Element eventData = event.addElement("event-data");

//      costruisco gli elementi xml
        eventData.addElement("creation-date").addText(getFormattedDate());
        eventData.addElement("title").addText(pattern.getName());

        logger.debug(
                "buildEventData -"
                + getFormattedDate()
                + " - " + pattern.getName());
        logger.debug("email: " + user.getHpmUserId());

        Element author = eventData.addElement("author");
        author.addElement("email").addText(user.getHpmUserId());

        logger.debug("buildEventData - " + user.getScreenName());

        Element properties = eventData.addElement("properties");

//      aggiunta elemento 'kpeopletag'
        Element property = properties.addElement("property");
        property.addElement("key").addText("kpeopletag");
        property.addElement("value").addText(pProcess.getHpmProcessId());

        logger.debug("buildEventData - " + pProcess.getHpmProcessId());

//      aggiunta elemento 'kpeopletagpattern'
        Element property2 = properties.addElement("property");
        property2.addElement("key").addText("kpeopletagpattern");
        property2.addElement("value").addText(pattern.getHpmPatternId());

        logger.debug("buildEventData - " + pattern.getHpmPatternId());

        Element attachments = eventData.addElement("attachments");

        List<it.webscience.kpeople.be.Document> docList = pattern.getDocList();
        for (it.webscience.kpeople.be.Document doc : docList) {
            logger.debug("buildEventData - " + doc.getHashcode());
            logger.debug("buildEventData - " + doc.getFileName());
            logger.debug("buildEventData - " + doc.getData().length);
            logger.debug("buildEventData - " + doc.getHashcode());

            Element attachment =
                attachments.addElement("attachment")
                .addAttribute("id", getAttachmentId(doc.getHashcode()));

            attachment.addElement("attachment-type").addText(
                    getAttachmentType(doc.getFileName()));
            attachment.addElement("attachment-data").addText(
                    generateOmText(doc.getData()));
            attachment.addElement("attachment-name").addText(
                    doc.getFileName());
            attachment.addElement("hashcode").addText(
                    doc.getHashcode());
        }

        logger.debug("buildEventData - end");
    }

    /**
     * invia l'evento di richiesta contributo.
     * @param activity activity
     * @throws Exception eccezione durante l'eleborazione
     */
    public final void sendEventRichiestaContributoContribute(
            final Activity activity)
            throws Exception {
        try {
            logger.debug("sendEventRichiestaContributoContribute - BEGIN");

            String actionType = PROCESS_PATTERN_RICHIESTACONTRIBUTO_INVIO;

            String xml = buildXml(activity, actionType,
                    activity.getActivityOwner(), activity.getPattern());

            logger.debug("Xml : " + xml);

            EgaChannelSender egaCs = new EgaChannelSender();
            egaCs.sendEvent(xml, "/kpeople/event/" + actionType);

            logger.debug("sendEventRichiestaContributoContribute - END");
        } catch (Exception e) {
            logger.fatal(e.getMessage());
            throw e;
        }
    }
}
