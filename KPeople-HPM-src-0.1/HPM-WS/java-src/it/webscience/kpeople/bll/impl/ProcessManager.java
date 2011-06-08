package it.webscience.kpeople.bll.impl;
 
import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.Keyword;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.Process.ProcessVisibility;
import it.webscience.kpeople.be.ProcessState;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.IProcessManager;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.proxy.ProcessDAOProxy;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.keyword.KeywordDAO;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMText;
import org.apache.log4j.Logger;
import org.dom4j.Element;

/**
 * Classe di business per repativa a PRocess.
 */
public class ProcessManager implements IProcessManager {
    /** logger. */
    private Logger logger;

    /**
     * Costruttore di default.
     */
    public ProcessManager() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Restituisce gli utenti proprietari di almeno un processo.
     * @param user
     *            user che esegue la chiamata
     * @return elenco di User
     * @throws KPeopleBLLException
     *             eccezione durante l'elaborazione
     */
    public final List<User> getOwners(final User user)
            throws KPeopleBLLException {
        if (logger.isDebugEnabled()) {
            logger.debug("start getOwners");
        }

        ProcessDAOProxy dao = new ProcessDAOProxy();
        List<User> daoResult = null;
        try {
            daoResult = dao.getOwners(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end getOwners");
        }

        return daoResult;
    }

    /**
     * @param hpmProcessId
     *            identificativo univoco del processo all'interno del sistema.
     * @param user
     *            riferimento all'utente che richiama il servizio.
     * @return istanza di classe Process corrispondente al processo avente l'id
     *         specificato (hpmProcessId).
     * @throws KPeopleBLLException
     *             eccezione genenerata durante l'esecuzione della logica di
     *             business.
     */
    public final Process getProcessByHpmId(final String hpmProcessId,
            final User user) throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getProcessByHpmId - begin");
        }

        ProcessDAOProxy dao = new ProcessDAOProxy();
        Process result = null;

        try {
            result = dao.getProcessByHpmId(hpmProcessId);

            // calcolo della visibilità
            if (result.getOwner().equals(user)) {
                result.setVisibility(ProcessVisibility.OWNER);

            } else if (!result.isPrivate()) {
                result.setVisibility(ProcessVisibility.PUBLIC);

            } else {
                // nel caso di processo privato, si verifica se l'utente è
                // abilitato
                boolean abilitato = false;

                List<User> utentiAbilitati;

                utentiAbilitati = dao.findEnabledUsers(result);
                for (User ua : utentiAbilitati) {
                    if (ua.equals(user)) {
                        abilitato = true;
                        break;
                    }
                }

                if (abilitato) {
                    result.setVisibility(ProcessVisibility.ENABLED);
                } else {
                    result.setVisibility(ProcessVisibility.PRIVATE);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        } catch (KPeopleDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getProcessByHpmId - end");
        }
        return result;
    }

    /**
     * @param process
     *            riferimento al processo in fase di inserimento.
     * @param user
     *            riferimento all'utente che richiama il servizio.
     * @throws KPeopleBLLException
     *             eccezione genenerata durante l'esecuzione della logica di
     *             business.
     * @return il processo creato.
     */
    public final Process addProcess(final Process process, final User user)
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getProcessByHpmId - begin");
        }

        ProcessDAOProxy dao = new ProcessDAOProxy();
        Process result = null;

      

        try {
            result = dao.setProcess(process, user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getProcessByHpmId - end");
        }

        return result;
    }

    /**
     * @param process
     *            riferimento al processo in fase di inserimento.
     * @param user
     *            riferimento all'utente che richiama il servizio.
     * @throws KPeopleBLLException
     *             eccezione genenerata durante l'esecuzione della logica di
     *             business.
     */
    public final void updateProcess(final Process process, final User user)
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("updateProcess - begin");
        }

        ProcessDAOProxy dao = new ProcessDAOProxy();
        Process result = null;

        try {
            dao.updateProcess(process, user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("updateProcess - begin");
        }
    }

    /**
     * @param process
     *            processo che deve essere chiuso
     * @param user
     *            utente che richiede la chiusura del processo
     * @return un oggetto di tipo boolean (true se l'operazione di chiusura ha
     *         avuto esito positivo false altrimenti).
     * @throws KPeopleBLLException
     *             eccezione legata all'esecuzione del servizio.
     */
    public final boolean closeProcess(final Process process, final User user)
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("closeProcess - begin");
        }

        ProcessDAOProxy dao = new ProcessDAOProxy();
        Boolean result = false;

        try {
            result = dao.closeProcess(process, user);
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("closeProcess - end");
        }

        return result;
    }

    /**
     * restituisce la data attuale nel formato yyyy-MM-dd'T'HH:mm:ss'.0Z'.
     * @return data formattata
     */
    private String getFormattedDate() {
        return DateFormat.getTimeInstance(DateFormat.LONG).format(new Date());
    }

    /**
     * ritorna il titolo legato al file. Per titolo si intende: nome del file
     * senza estensione.
     * @param file
     *            file associato
     * @return titolo del file
     */
    private String getFileTitle(final File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");

        String title = name;
        if (index != -1) {
            title = name.substring(0, index);
        }

        return title;
    }

    /**
     * genera l'OM dal file.
     * @param file
     *            file da analizzare
     * @return testo OM
     */
    private String generateOmText(final File file) {
        OMFactory factory = OMAbstractFactory.getOMFactory();

        FileDataSource fileDataSource = new FileDataSource(file);
        DataHandler dataHandler = new DataHandler(fileDataSource);
        OMText textData = factory.createOMText(dataHandler, true);

        return textData.getText();
    }

    /**
     * costruisce l'elemento action dell'xml.
     * @param event
     *            elemento Event
     * @param actionReference
     *            action reference
     */
    private void buildActionElement(final Element event,
            final String actionReference) {
        Element action = event.addElement("action");
        action.addElement("action-type").addText("PROCESS.DOCUMENT.UPLOAD");

        action.addElement("action-reference").addAttribute("type", "url")
                .addText(actionReference);

        Element system = action.addElement("system");
        system.addElement("system-id").addText("hpm");
        system.addElement("system-type").addText("hpm");
    }

    /**
     * costruisce l'elemento event-data dell'xml.
     * @param event
     *            elemento Event
     * @param file
     *            file da inviare
     * @param hpmProcessId
     *            identificativo del processo
     * @throws IOException
     *             eccezione generica
     */
    private void buildEventData(final Element event, final File file,
            final String hpmProcessId) throws IOException {
        /*
         * Element eventData = event.addElement("event-data"); // formatto i
         * dati prima di utilizzarli String date = getFormattedDate(); String
         * hashcode = generateHashcode(file); String omText =
         * generateOmText(file); String attachmentType =
         * getAttachmentType(file); String attachmentName = file.getName();
         * String title = getFileTitle(file); String attachmentId =
         * getAttachmentId(hashcode); String screenName = getScreenName(req); //
         * costruisco gli elementi xml
         * eventData.addElement("creation-date").addText(date);
         * eventData.addElement("title").addText(title); Element author =
         * eventData.addElement("author");
         * author.addElement("username").addText(screenName); Element properties
         * = eventData.addElement("properties"); Element property =
         * properties.addElement("property");
         * property.addElement("key").addText("kpeopletag");
         * property.addElement("value").addText(hpmProcessId); Element
         * attachments = eventData.addElement("attachments"); Element attachment
         * = attachments.addElement("attachment") .addAttribute("id",
         * attachmentId);
         * attachment.addElement("attachment-type").addText(attachmentType);
         * attachment.addElement("attachment-data").addText(omText);
         * attachment.addElement("attachment-name").addText(attachmentName);
         * attachment.addElement("hashcode").addText(hashcode);
         */
    }

    /**
     * Costruisce l'xml legato all'evento.
     * @param hpmProcessId
     *            id del processo
     * @param file
     *            file uploadato
     * @throws IOException
     *             eccezione generica
     * @return stringa xml
     */
    private String buildXml(final String hpmProcessId, final File file)
            throws IOException {
        /*
         * // costruzione del Document DOM4J Document document =
         * DocumentHelper.createDocument(); // creazione del figlio 'event'
         * Element event = document.addElement("event") .addAttribute(
         * "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
         * .addAttribute("id", getEventId()); buildActionElement(event, req);
         * buildEventData(event, file, hpmProcessId, req); // pretty printing
         * dell'xml su String StringWriter stringWriter = new StringWriter();
         * XMLWriter xmlWriter = new XMLWriter( stringWriter,
         * OutputFormat.createPrettyPrint()); xmlWriter.write(document); String
         * xml = stringWriter.toString(); if (logger.isDebugEnabled()) {
         * logger.debug("Upload documento: XML da inviare:\n" + xml); }
         */
        return null;
    }

    /**
     * Invia un documento all'ega associandolo ad un processo.
     * @param documentBE
     *            documento da inviare all'EGA
     * @param processBE
     *            processo associato al documento
     * @param actionReference
     *            action reference dell'evento
     * @param user
     *            utente che effettua la chiamata
     * @throws KPeopleBLLException
     *             eccezione duranta l'elaborazione
     */
    public final void callEgaChannelSender(final Document documentBE,
            final Process processBE, final String actionReference,
            final User user) throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("callEgaChannelSender - begin");
        }

        try {

            EgaChannelSender sender = new EgaChannelSender();
            // logger.info("xml:\n" + xml);
            try {
                // sender.sendEvent(
                // xml,
                // "/kpeople/event/" + DOCUMENT_UPLOAD_ACTION_TYPE);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IOException(e.getMessage());
            }

        } catch (Exception e) {
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("callEgaChannelSender - begin");
        }
    }

}
