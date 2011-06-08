package it.webscience.kpeople.ega.adapter
.sharepoint.actionDataProcessor.actionDataProcessorImpl;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLogger;
import it.webscience.kpeople.ega.adapter.sharepoint
.actionDataProcessor.actionDataInterface.ActionDataParserInterface;
import it.webscience.kpeople.ega.adapter.sharepoint
.actionDataProcessorManager.ActionDataProcessorManager;

import it.webscience.kpeople.ega.adapter.sharepoint.kpeopleeventmodel.Author;
import it.webscience.kpeople.ega.adapter.sharepoint
.kpeopleeventmodel.KpeopleAttachment;
import it.webscience.kpeople.ega.adapter.sharepoint
.kpeopleeventmodel.KpeopleSimpleEvent;
import it.webscience.kpeople.ega.adapter.sharepoint
.kpeopleeventmodel.SharepointDocumentEvent;
import it.webscience.kpeople.ega.adapter.sharepoint
.util.ActionDataProcessorUtil;
import it.webscience.kpeople.ega.adapter.sharepoint.util.Hashcode;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.security.NoSuchAlgorithmException;

import java.util.HashMap;
import java.util.Properties;

import javax.activation.DataHandler;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.microsoft.schemas.sharepoint.soap.FieldInformation;
import com.microsoft.schemas.sharepoint.soap.FieldInformationCollection;
import com.microsoft.schemas.sharepoint.soap.GetItemResponse;

/**
 * @author XPMUser
 */
public class ActionDataParserImpl implements ActionDataParserInterface {

    private SharepointDocumentEvent eventResult = null;
    
    private String fileName = null;

    // BundleContext context =
    // FrameworkUtil.getBundle(this.getClass()).getBundleContext();

    private static Properties currentProperties = ActionDataProcessorManager
            .getAdapterproperties();

    /**
     * @see it.webscience.kpeople.ega.adapter.sharepoint
     *      .actionDataProcessor.actionDataInterface.ActionDataParserInterface
     *      #parser(org.apache.axiom.om.OMElement,
     *      it.webscience.kpeople.domain.model.KpeopleAction).
     * @param action
     *            azione che è stata intercettata.
     * @param response
     *            indica il valore di ritorno della chiamata al webservice di
     *            sharepoint contenente le informazioni sulle liste monitorate
     * @return hashmap da sostituire.
     * @throws Exception
     *             eccezione.
     */
    public KpeopleSimpleEvent parser(final GetItemResponse response,
            final KpeopleAction action) throws Exception {

        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".parser()"
                                + KpeopleLabel.getLogStart());

        manageActionInfo(action);

        String operation = action.getActionType().split("/")[1];
        eventResult.setOperation(operation);

        manageEventInfo(response, operation);

        DataHandler stream = response.getStream();

        manageDetails(response, stream);

        manageAttachments(action, stream);

        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(
                        KpeopleLabel.getSystemId(),
                        this.getClass() + ".parser()"
                                + KpeopleLabel.getLogStop());

        return eventResult;
    }

    private void manageAttachments(KpeopleAction action, DataHandler stream)
            throws IOException {

        InputStream is = null;
        String hashcode = null;
        try {
            is = stream.getInputStream();
            hashcode = Hashcode.getHashcode(is, Hashcode.SHA512);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String path = (String) currentProperties.get(KpeopleLabel
                .getEventXMLPath());
        try {
            path = ActionDataProcessorUtil.checkEventXMLPath(path);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        FileOutputStream outputStream = null;
        File tmpFilePath = null;
        try {
            path = path + action.getIdAction() + "-" + fileName;
            tmpFilePath = new File(path);
            outputStream = new FileOutputStream(tmpFilePath);
            stream.writeTo(outputStream);

            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ActionDataProcessorUtil.deleteEventFile(tmpFilePath);

        String idAttachmentId = Long.toString(action.getIdAction()) + "-"
                + hashcode.substring(0, 6);

        KpeopleAttachment attachment = new KpeopleAttachment();
        attachment.setAttachmentName(fileName);
        attachment.setAttachmentHashcode(hashcode);
        attachment.setAttachmentData(path);
        attachment.setIdAttachment(idAttachmentId);
        attachment.setAttachmentType(eventResult.getContentType());

        eventResult.addAttachment(attachment);
    }

    private void manageDetails(final GetItemResponse response,
            final DataHandler stream) {

        InputStream is = null;
        ContentHandler contenthandler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        // metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
        Parser parser = new AutoDetectParser();
        ParseContext context = new ParseContext();

        try {
            is = stream.getInputStream();
           
            parser.parse(is, contenthandler, metadata, context);
            is.close();
            is.reset();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

        String contentAuthorValue = metadata.get(Metadata.AUTHOR);
        String contentAuthorKey = currentProperties.getProperty(KpeopleLabel
                .getCorePropertiesAuthor());
        if (contentAuthorValue != null) {
            eventResult.setDetail(contentAuthorKey, contentAuthorValue);
        }

        String contentCreationDateValue = metadata.get(Metadata.CREATION_DATE);
        String contentCreationDateKey = currentProperties
                .getProperty(KpeopleLabel.getCorePropertiesCreationDate());
        if (contentCreationDateValue != null) {
            eventResult.setDetail(contentCreationDateKey,
                    contentCreationDateValue);
        }

        String contentKeywordsValue = metadata.get(Metadata.KEYWORDS);
        String contentKeywordsKey = currentProperties.getProperty(KpeopleLabel
                .getCorePropertiesKeywords());
        if (contentKeywordsValue != null) {
            eventResult.setDetail(contentKeywordsKey, contentKeywordsValue);
        }

        String[] names = metadata.names();

        /*
         * for (int i = 0; i < names.length; i++) {
         * System.out.println(names[i]); }
         */

    }

    private void manageEventInfo(GetItemResponse response, String operation) {
        FieldInformationCollection fields = response.getFields();

        FieldInformation[] fieldsInformation = fields.getFieldInformation();

        String createdBy = null;
        String modifiedBy = null;
        String created = null;
        
        String modified = null;
        String contentType = null;
        String eventCreationDate = null;
        String eventAuthor = null;
        HashMap<String, String> hm = new HashMap<String, String>();

        for (int i = 0; i < fieldsInformation.length; i++) {
            FieldInformation field = fieldsInformation[i];
            String name = field.getInternalName();
            if (name.equalsIgnoreCase("BaseName")) {
                fileName = field.getValue();
                eventResult.setTitle(fileName);
            }
            if (name.equalsIgnoreCase("Created_x0020_By")) {
                createdBy = field.getValue();
            }
            if (name.equalsIgnoreCase("Modified_x0020_By")) {
                modifiedBy = field.getValue();
            }
            if (name.equalsIgnoreCase("Created_x0020_Date")) {
                created = field.getValue();
            }
            if (name.equalsIgnoreCase("Last_x0020_Modified")) {
                modified = field.getValue();
            }
            if (name.equalsIgnoreCase("File_x0020_Type")) {
                contentType = field.getValue();
            }
            if (name.equalsIgnoreCase("Process")) {
                String processValue[] = field.getValue().split("-");
                hm.put("kpeopletag", processValue[0]);
            }
            
        }

        if (operation.equalsIgnoreCase("modified")) {
            eventCreationDate = modified;
            eventAuthor = modifiedBy;
        }
        if (operation.equalsIgnoreCase("created")) {
            eventCreationDate = created;
            eventAuthor = createdBy;
        }

        eventResult.setContentType(contentType);

        eventResult.setCreationDate(ActionDataProcessorUtil
                .getDateString(eventCreationDate));
        Author author = new Author();
        author.setUsername(eventAuthor);
        eventResult.setAuthor(author);
        eventResult.setProperties(hm);
        // eventResult.setAuthor(eventAuthor);

    }

    private void manageActionInfo(KpeopleAction action) {
        String idEvent = action.getSystemId() + action.getIdAction().toString()
                + action.getTimestamp();

        String actionType = action.getActionType();

        String systemType = null;
        try {
            systemType = ActionDataProcessorUtil.getSystemType(action
                    .getSystemId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String systemId = action.getSystemId();

        String actionReference = action.getActionReference();

        long idAction = action.getIdAction();

        eventResult = new SharepointDocumentEvent(idEvent, actionType,
                systemType, idAction, systemId, actionReference);

    }

    public KpeopleSimpleEvent parserDeleted(final KpeopleAction actionToProcess) {

        manageActionInfo(actionToProcess);
        return eventResult;

    }

}
