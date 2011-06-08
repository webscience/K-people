package it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessor.actionDataProcessorImpl;

import java.io.File;


import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import java.util.Properties;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMText;

import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessor.actionDataInterface.EventDataPackagerInterface;
import it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessorManager.ActionDataProcessorManager;
import it.webscience.kpeople.ega.adapter.sharepoint.kpeopleeventmodel.Author;
import it.webscience.kpeople.ega.adapter.sharepoint.kpeopleeventmodel.KpeopleAttachment;
import it.webscience.kpeople.ega.adapter.sharepoint.kpeopleeventmodel.KpeopleSimpleEvent;
import it.webscience.kpeople.ega.adapter.sharepoint.kpeopleeventmodel.SharepointDocumentEvent;
import it.webscience.kpeople.ega.adapter.sharepoint.util.ActionDataProcessorUtil;


/**
 * @author filieri
 */
public class EventDataPackagerImpl implements EventDataPackagerInterface {

    /**
     * @param unstructuredEvent
     *            evento intercettato.
     * @throws Exception
     *             generica.
     * @return il percorso del file.
     */
    public String pack(final KpeopleSimpleEvent unstructuredEvent)
            throws Exception {

        Document xmlEvent = DocumentHelper.createDocument();
        SharepointDocumentEvent event = (SharepointDocumentEvent)unstructuredEvent;
        String path = null;
        //
        // String fileid = (String) unstructuredEvent.get("idAction");

        Element root = xmlEvent.addElement("event");
        root.addAttribute("xmlns:xsi",
                "http://www.w3.org/2001/XMLSchema-instance");
        root.addAttribute("id", (String) unstructuredEvent.getIdEvent());

        // blocco per sezione relativa all'azione
        Element action = root.addElement("action");

        Element actionType = action.addElement("action-type");
        actionType.addText(unstructuredEvent.getActionType());

        Element actionReference = action.addElement("action-reference");
        actionReference.addText(unstructuredEvent.getActionReference());

        Element system = action.addElement("system");
        Element systemId = system.addElement("system-id");
        systemId.addText(unstructuredEvent.getSystemId());
        Element systemType = system.addElement("system-type");
        systemType.addText(unstructuredEvent.getSystemType());

        // blocco per sezione XML relativa all'evento
        Element eventData = root.addElement("event-data");

        if (unstructuredEvent.getCreationDate() != null) {
            Element eventCreationDate = eventData.addElement("creation-date");
            eventCreationDate.addText(unstructuredEvent.getCreationDate()
                    .toString());
        }

        if (unstructuredEvent.getTitle() != null) {
            Element eventTitle = eventData.addElement("title");
            eventTitle.addText(unstructuredEvent.getTitle());
        }

        if (unstructuredEvent.getAuthor() != null) {
            Author author = unstructuredEvent.getAuthor();
            Element eventAuthor = eventData.addElement("author");
            if (author.getUsername() != null) {
                Element authorUserId = eventAuthor.addElement("username");
                authorUserId.setText(author.getUsername());
            }
            if (author.getName() != null) {
                Element authorName = eventAuthor.addElement("name");
                authorName.setText(author.getName());
            }
            if (author.getEmail() != null) {
                Element authorEmail = eventAuthor.addElement("email");
                authorEmail.setText(author.getEmail());
            }
            // eventAuthor.addText(unstructuredEvent.getAuthor());
        }
        if(event.getProperties() != null)
        {
            HashMap<String, String> hm = event.getProperties();
            Element properties = eventData.addElement("properties");
            Set set = hm.entrySet();
            Iterator it = set.iterator();
            while(it.hasNext()) {
                     Map.Entry mapEntry = (Map.Entry)it.next();
                     Element property = properties.addElement("property");
                     Element key = property.addElement("key");
                     key.setText((String) mapEntry.getKey());
                     Element value = property.addElement("value");
                     value.setText((String)mapEntry.getValue());
        }
        }

        if (unstructuredEvent.getDetails() != null) {
            Element details = eventData.addElement("details");
            CDATA det = DocumentHelper.createCDATA(unstructuredEvent
                    .getDetails());
            details.add(det);

        }

        if (unstructuredEvent.getAttachments() != null) {
            ArrayList<KpeopleAttachment> attachmentsList = unstructuredEvent
                    .getAttachments();
            Element attachments = eventData.addElement("attachments");
            for (KpeopleAttachment attachment : attachmentsList) {
                Element attachmentsElement = attachments
                        .addElement("attachment");
                attachmentsElement.addAttribute("id",
                        attachment.getIdAttachment());

                Element attachementType = attachmentsElement
                        .addElement("attachment-type");
                attachementType.setText(attachment.getAttachmentType());

                Element attachementData = attachmentsElement
                        .addElement("attachment-data");
                //String contentData = generateData(attachment
                //        .getAttachmentData());
                //attachementData.setText(contentData);

                generateData(attachment.getAttachmentData(), attachementData);
//                        attachementData.setText(contentData);

                Element attachementName = attachmentsElement
                        .addElement("attachment-name");
                attachementName.setText(attachment.getAttachmentName());

                Element attachementHashcode = attachmentsElement
                        .addElement("hashcode");
                attachementHashcode.setText(attachment.getAttachmentHashcode());
            }
        }

        try {
            Properties currProperties = ActionDataProcessorManager
                    .getAdapterproperties();
            path = (String) currProperties.get(KpeopleLabel.getEventXMLPath())
                    + "event" + "-" + unstructuredEvent.getIdAction() + ".xml";
            File newFile = new File(path);
            FileWriter fileWriter = new FileWriter(newFile);
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter output = new XMLWriter(fileWriter, format);
            output.write(xmlEvent);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * @param path percorso relativo all'attachment.
     * @param attachementData 
     * @return un oggetto di tipo String che
     * rappresenta il contenuto dell'attachment
     */
    private void generateData(final String path, final Element attachementData) {

        File file = new File(path);
        OMFactory factory = OMAbstractFactory.getOMFactory();

        FileDataSource fileDataSource = new FileDataSource(file);
        DataHandler dataHandler = new DataHandler(fileDataSource);
        OMText textData = factory.createOMText(dataHandler, true);
        attachementData.setText(textData.getText());
        ActionDataProcessorUtil.deleteEventFile(file);
        
    }

}
