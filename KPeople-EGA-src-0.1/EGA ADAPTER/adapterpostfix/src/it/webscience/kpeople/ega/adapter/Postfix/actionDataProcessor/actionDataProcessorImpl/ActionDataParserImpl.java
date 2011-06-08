package it.webscience.kpeople.ega.adapter.Postfix.actionDataProcessor.actionDataProcessorImpl;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.ega.adapter.Postfix.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.Postfix.KpeopleLogger;
import it.webscience.kpeople.ega.adapter.Postfix.actionDataProcessor.actionDataInterface.ActionDataParserInterface;
import it.webscience.kpeople.ega.adapter.Postfix.actionDataProcessorManager.ActionDataProcessorManager;
import it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel.Author;
import it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel.KpeopleAttachment;
import it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel.KpeopleCommunicationEvent;
import it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel.KpeopleSimpleEvent;
import it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel.PostfixEmailEvent;
import it.webscience.kpeople.ega.adapter.Postfix.util.*;
import it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel.*;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.LinkedHashMap;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMText;
import org.dom4j.*;
import org.dom4j.io.SAXReader;



/**
 * @author filieri
 */
public class ActionDataParserImpl implements ActionDataParserInterface {

	private static KpeopleLog logService = KpeopleLogger.getInstance()
	.getService();

	HashMap<String, Object> attacchments = new LinkedHashMap<String, Object>();
	//BundleContext cont = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
	private static KpeopleAction currentAction;
	private static Properties currentProperties = ActionDataProcessorManager
	.getAdapterproperties();

	private PostfixEmailEvent eventResult;

	public KpeopleSimpleEvent parser(final String contentpath,
			final KpeopleAction action) throws Exception {

		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".parser()"
				+ KpeopleLabel.getLogStart());

		currentAction = action;

		//String tmpPath = (String) currentProperties.get(KpeopleLabel.getEventTmpPath());
		//String checkedPath = ActionDataProcessorUtil.checkTmpPath(tmpPath);

		File tmpFilePath = new File(contentpath);
		FileInputStream content = null;
		if (tmpFilePath.exists()) {
			content = new FileInputStream(tmpFilePath);
		}

		Session session = Session.getDefaultInstance(System.getProperties());
		MimeMessage email = new MimeMessage(session, content);

		addActionParameters(action);
		addEventDataParameters(email);



		content.close();

		ActionDataProcessorUtil.deleteEventFile(tmpFilePath);

		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".parser()"
				+ KpeopleLabel.getLogStop());

		return eventResult;
	}

	private void addActionParameters(KpeopleAction action) {
		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".addActionParameters()"
				+ KpeopleLabel.getLogStart());

		String idEvent = (String) (action.getSystemId() + "@"
				+ action.getIdAction().toString() + "@" + action.getTimestamp());

		String systemtype = null;
		try {
			systemtype = ActionDataProcessorUtil.getSystemType(action.getSystemId());

		} catch (Exception e) {
			e.printStackTrace();
		}

		eventResult = new PostfixEmailEvent(idEvent, action.getActionType(), systemtype, action.getIdAction(), action.getSystemId(), action.getActionReference());
		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".addActionParameters()"
				+ KpeopleLabel.getLogStop());

	}

	private void addEventDataParameters(MimeMessage email)
	throws MessagingException, IOException, NoSuchAlgorithmException {
		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".addEventDataParameters()"
				+ KpeopleLabel.getLogStart());

		Date creationDate = null;
		String title = null;
		try {
			creationDate = email.getSentDate();
			if (creationDate == null) {
			    creationDate = new Date();
			}
			
			SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss.S'Z'");
			String formattedDate = df.format(creationDate);

			eventResult.setCreationDate(formattedDate);

			title = email.getSubject();
			eventResult.setTitle(title);

		} catch (MessagingException e) {
			e.printStackTrace();
			throw e;
		}

		InternetAddress[] sender = (InternetAddress[]) email.getFrom();
		if (sender != null) {
			InternetAddress addressFrom = sender[0];
			Author author = new Author();
			author.setEmail(addressFrom.getAddress());
			eventResult.setAuthor(author);

		}

		retrieveContent(email);
		String details = retrieveDetails(email);

		eventResult.setDetails(details);


		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".addEventDataParameters()"
				+ KpeopleLabel.getLogStop());

	}

	private String retrieveDetails(MimeMessage email)
	throws MessagingException, IOException {
		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".retrieveDetails()"
				+ KpeopleLabel.getLogStart());

		Document details = DocumentHelper.createDocument();
		Element root = details.addElement("email-details");

		Element receivers = root.addElement("receivers");

		InternetAddress[] receiverTo = (InternetAddress[]) email
		.getRecipients(Message.RecipientType.TO);
		if (receiverTo != null) {
			for (InternetAddress address : receiverTo) {
				Element receiver = receivers.addElement("receiver-to");
				receiver.setText(address.getAddress());
			}
		}

		InternetAddress[] receiverCc = (InternetAddress[]) email
		.getRecipients(Message.RecipientType.CC);
		if (receiverCc != null) {
			for (InternetAddress address : receiverCc) {
				Element receiver = receivers.addElement("receiver-cc");
				receiver.setText(address.getAddress());
			}
		}

		InternetAddress[] receiverBcc = (InternetAddress[]) email
		.getRecipients(Message.RecipientType.BCC);
		if (receiverBcc != null) {
			for (InternetAddress address : receiverBcc) {
				Element receiver = receivers.addElement("receiver-bcc");
				receiver.setText(address.getAddress());
			}
		}

		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".retrieveDetails()"
				+ KpeopleLabel.getLogStop());

		return details.asXML();
		// email.getRecipients(MimeMessage.RecipientType.NEWSGROUPS);
	}

	private void retrieveContent(MimeMessage email)
	throws MessagingException, IOException, NoSuchAlgorithmException {

		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".retrieveContent()"
				+ KpeopleLabel.getLogStart());

		Object emailcontent = null;
		Object bodyContent = null;
		
		Part messagePart = email;
		Object content = messagePart.getContent();
	        if (content instanceof Multipart) {
	            boolean hasHtml = false;
	            boolean hasText = false;
	            System.out.println(((Multipart) content).getCount());
	            for (int i = 0; i < ((Multipart) content).getCount(); i++) {
	                BodyPart currentBodyPart = ((Multipart) content).getBodyPart(i);
	                String disposition = currentBodyPart.getDisposition();

	                if (disposition != null
	                        && (disposition.equals(BodyPart.ATTACHMENT))) {
	                    try {
                                manageAttachments(currentBodyPart);
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                                throw e;
                            }
	                } else {

	                    if (currentBodyPart.getContentType().contains("multipart")) {

	                        Object subcontent = currentBodyPart.getContent();
	                        System.out.println(((Multipart) subcontent).getCount());
	                        for (int y = 0; y < ((Multipart) subcontent).getCount(); y++) {
	                            
	                            BodyPart subBodyPart =
	                                    ((Multipart) subcontent).getBodyPart(y);
	                            String contentype = null;
	                            if (subBodyPart.getContentType().contains("related") ||
	                                    subBodyPart.getContentType().contains("alternative")) {
	                                Object subcontent2 = subBodyPart.getContent();
	                                System.out.println(((Multipart) subcontent2).getCount());
	                                for (int z = 0; z < ((Multipart) subcontent2).getCount(); z++) {
	                                    BodyPart subBodyPart3 = ((Multipart) subcontent2)
	                                    .getBodyPart(z);
	                                    contentype = subBodyPart3.getContentType();
	                                    emailcontent = subBodyPart3.getContent();
	                                    if (emailcontent != null) {
	                                            if (contentype.contains("text/html")) {
	                                                    hasHtml = true;
	                                                    bodyContent = emailcontent;
	                                            }
	                                            if (contentype.contains("text/plain")) {
	                                                    hasText = true;
	                                                    bodyContent = emailcontent;
	                                            }
	                                    }
	                                }
	                                break;
	                            } else {
	                                contentype = subBodyPart.getContentType();
	                                emailcontent = subBodyPart.getContent();  
	                                if (emailcontent != null) {
                                            if (contentype.contains("text/html")) {
                                                    hasHtml = true;
                                                    bodyContent = emailcontent;
                                            }
                                            if (contentype.contains("text/plain")) {
                                                    hasText = true;
                                                    bodyContent = emailcontent;
                                            }
                                        }
	                            }

	                        }

	                    } else if (currentBodyPart.getContentType().contains("text")) {
	                        emailcontent = (String) currentBodyPart.getContent();
	                        if (emailcontent != null) {
	                            if (currentBodyPart.getContentType().contains(
	                                    "text/html")) {
	                                bodyContent = emailcontent;
	                                hasHtml = true;
	                            } else {
	                                hasText = true;
	                                bodyContent = emailcontent;
	                            }
	                        }
	                    }

	                }
	            }
			if (hasHtml) {
				eventResult.setBody((String)bodyContent);
				eventResult.setBodyType("html");
			} else if (hasText) {
				eventResult.setBody((String)bodyContent);
				eventResult.setBodyType("text");
			}
		} else {
			emailcontent = (String) email.getContent();

			if (email.getContentType().contains("text/html")) {
				eventResult.setBody((String)emailcontent);
				eventResult.setBodyType("html");
			} else {
				eventResult.setBody((String)emailcontent);
				eventResult.setBodyType("text");
			}

		}
		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".retrieveContent()"
				+ KpeopleLabel.getLogStop());

	}

	private void manageAttachments(BodyPart bodyPart) throws IOException,
	NoSuchAlgorithmException, MessagingException {
		logService.logInfo(
				KpeopleLabel.getSystemId(),
				this.getClass() + ".manageAttachments()"
				+ KpeopleLabel.getLogStart());

		//String disp = bodyPart.getDisposition();

		//String type = bodyPart.getContentType();



		DataHandler handler = bodyPart.getDataHandler();
		String attachmentType = handler.getContentType();
		String[] attachmentTypeSplitted = attachmentType.split(";");
		String fileName = handler.getName();
		if(fileName.equalsIgnoreCase("KPeople.xml")) {

			InputStream attacchedFile;		
			try {
				attacchedFile = handler.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			
			String path = (String) currentProperties.get(KpeopleLabel
					.getEventXMLPath());
			String tmpFilePath = path + fileName;
			File tmpFile = new File(tmpFilePath);
			FileOutputStream out = null;

			try {
				out = new FileOutputStream(tmpFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = attacchedFile.read(buf)) != -1) {

				out.write(buf, 0, bytesRead);
			}
			out.close();
			try {
				HashMap<String, String> hm = parseAttachment(tmpFilePath);
				if (!hm.isEmpty())
				{
					eventResult.setProperties(hm);
				}
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ActionDataProcessorUtil.deleteEventFile(tmpFile);
		}
		else
		{
			if (attachmentTypeSplitted[0] != null) {
				attachmentType = attachmentTypeSplitted[0];
			}


			InputStream attacchedFile;

			String hashcode = null;
			try {
				attacchedFile = handler.getInputStream();
				hashcode = Hashcode.getHashcode(attacchedFile, Hashcode.SHA512);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}

			KpeopleAttachment attachment = new KpeopleAttachment();

			String attachmentId = Long.toString(currentAction.getIdAction()) + "-"
			+ hashcode.substring(0, 6);
			attachment.setIdAttachment(attachmentId);
			attachment.setAttachmentType(attachmentType);

			attacchments.put("eventdata.attachments.attachment@id=" + attachmentId
					+ ".attachment-type", attachmentType);

			//String nameFile = handler.getName();
			String path = (String) currentProperties.get(KpeopleLabel
					.getEventXMLPath());
			String tmpFilePath = path + fileName;
			File tmpFile = new File(tmpFilePath);
			FileOutputStream out = null;

			try {
				out = new FileOutputStream(tmpFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = attacchedFile.read(buf)) != -1) {

				out.write(buf, 0, bytesRead);
			}
			out.close();

			OMFactory factory = OMAbstractFactory.getOMFactory();

			FileDataSource fileDataSource = new FileDataSource(tmpFile);
			DataHandler dataHandler = new DataHandler(fileDataSource);
			OMText textData = factory.createOMText(dataHandler, true);

			attachment.setAttachmentData(textData.getText());
			attacchments.put("eventdata.attachments.attachment@id=" + attachmentId
					+ ".attachment-data", textData.getText());

			attachment.setAttachmentName(fileName);
			attacchments.put("eventdata.attachments.attachment@id=" + attachmentId
					+ ".attachment-name", fileName);

			attachment.setAttachmentHashcode(hashcode);
			attacchments.put("eventdata.attachments.attachment@id=" + attachmentId
					+ ".hashcode", hashcode);


			eventResult.addAttachment(attachment);
			
			ActionDataProcessorUtil.deleteEventFile(tmpFile);
			logService.logInfo(
					KpeopleLabel.getSystemId(),
					this.getClass() + ".manageAttachments()"
					+ KpeopleLabel.getLogStop());

		}
	}

	private HashMap parseAttachment(String url) throws DocumentException {
		HashMap<String, String> hm = new HashMap<String, String>();
		String separatore = null;
		String key = null;
		String value = null;
		String xPath = KpeopleLabel.getXPath();
		Document document = getDocument(url);
		List<Node> nodes = document.selectNodes(xPath);
		for (Node node : nodes) {
			 Element nodeElement = (Element) node;
			 List<Element> childElements = nodeElement.elements();
			 if (childElements.size() == 0) {
				 continue;
			  }
			 else {
				 if (childElements.size() == 1)
				 {
					 //key = (childElements.get(0)).valueOf("@value");
				 key = (nodeElement.valueOf("@Key"));
			         value = (childElements.get(0)).valueOf("@value");
			         hm.put(key, value);
				 }
				 if (childElements.size() > 1) {   
					 for (int i=0; i<childElements.size(); i++)
					 {
						 if ((((childElements.get(i)).valueOf("@value")).toLowerCase().indexOf(KpeopleLabel.getSeparatore1().toLowerCase().substring(0)))>0)
								 {
							 		separatore=KpeopleLabel.getSeparatore2();
								 }
						 else{
						 if ((((childElements.get(i)).valueOf("@value")).toLowerCase().indexOf(KpeopleLabel.getSeparatore2().toLowerCase().substring(0)))>0)
						 {
					 		separatore=KpeopleLabel.getSeparatore1();
						 }
						 else {
							 separatore = KpeopleLabel.getSeparatore1();
						 }
						 }
					 }
					 value ="multi["+separatore+"]:";
					 //key = (childElements.get(0)).valueOf("@value");
					 key = (nodeElement.valueOf("@Key"));
					 for (int j=0; j< childElements.size(); j++)
					 {   
						 if (j == 0)
						 {
							 value += (childElements.get(j)).valueOf("@value");
						 }
						 else
						 {
						 value += separatore+(childElements.get(j)).valueOf("@value");
						 }
					 }
					 hm.put(key, value);
				 }
				 
			 }     
	      }
		return hm;
	}
	private Document getDocument(final String xmlFileName ) {
		Document document = null;
	      SAXReader reader = new SAXReader();
	      try
	      {
	         document = reader.read( xmlFileName );
	      }
	      catch (DocumentException e)
	      {
	         e.printStackTrace();
	      }
	      return document;
	}
	

}
