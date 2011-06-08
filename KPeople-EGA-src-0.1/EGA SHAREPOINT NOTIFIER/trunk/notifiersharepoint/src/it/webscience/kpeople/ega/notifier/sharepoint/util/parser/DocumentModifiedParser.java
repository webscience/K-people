package it.webscience.kpeople.ega.notifier.sharepoint.util.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;

import it.webscience.kpeople.ega.notifier.sharepoint.KpeopleLogger;
import it.webscience.kpeople.ega.notifier.sharepoint.model.SharepointDocument;

import it.webscience.kpeople.ega.notifier.sharepoint.util.cache.CacheService;
import it.webscience.kpeople.ega.notifier.sharepoint.util.configuration.SharepointNotifierParameters;
import it.webscience.kpeople.ega.notifier.sharepoint.webserviceclient.SharepointNotifierWSClient;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;

public class DocumentModifiedParser {

    private static List<SharepointDocument> documents = new LinkedList<SharepointDocument>();

    private static String methodName;

    private static KpeopleLog logService = KpeopleLogger.getInstance()
            .getService();

    public static void parse(final OMElement lastChangedItems,
            final String endpoint, final String sharepointServer) throws ParseException {

        DateFormat formatter;
        Date modifiedDate = null;
        Date createdDate = null;

        formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss'Z'");

        Iterator<?> it = lastChangedItems.getChildElements();

        documents.clear();

        while (it.hasNext()) {

            OMElement row = (OMElement) it.next();

            SharepointDocument doc = new SharepointDocument();
            QName fileRef = new QName("ows_FileRef");
            String fileUrl = row.getAttributeValue(fileRef);
            fileUrl = fileUrl.split(";#")[1];
            String serviceReference = SharepointNotifierParameters
                    .getServiceName();
            String endpointReference = endpoint.replace(serviceReference, "");
            doc.setFileUrl(sharepointServer + "@" + endpointReference +"@"+ fileUrl);

            QName fileId = new QName("ows_UniqueId");
            String documentId = row.getAttributeValue(fileId);
            doc.setDocumentId(documentId);

            QName modified = new QName("ows_Last_x0020_Modified");
            String modifiedDateString = row.getAttributeValue(modified);
            modifiedDateString = modifiedDateString.split(";#")[1];
            modifiedDate = (Date) formatter.parse(modifiedDateString);
            doc.setModifiedDate(modifiedDate);

            QName created = new QName("ows_Created_x0020_Date");
            String createdDateString = row.getAttributeValue(created);
            createdDateString = createdDateString.split(";#")[1];
            createdDate = (Date) formatter.parse(createdDateString);
            doc.setCreatedDate(createdDate);

            if (createdDate.compareTo(modifiedDate) == 0) {
                logService.logInfo(DocumentModifiedParser.class.getName(),
                        " il documento " + documentId + " e' nuovo");
                doc.setAction("CREATED");
            } else {
                logService.logInfo(DocumentModifiedParser.class.getName(),
                        " il documento " + documentId + " e' stato modificato");
                doc.setAction("MODIFIED");
            }
            if (doc != null) {
                documents.add(doc);
            }
        }

        try {
            checkCache();
        } catch (AxisFault e) {
            e.printStackTrace();
        }

    }

    private static void checkCache() throws AxisFault {
        Cache cache = CacheService.getInstance().getCacheModify();
        methodName = DocumentModifiedParser.class.getName() + ".checkCache";
        SharepointNotifierWSClient client = new SharepointNotifierWSClient();

        for (SharepointDocument doc : documents) {
            String documentId = doc.getDocumentId();

            if (cache.getKeys().contains(documentId)) {
                Element selected = cache.get(documentId);
                SharepointDocument selectedDoc = (SharepointDocument) selected
                        .getObjectValue();
                if (!doc.equals(selectedDoc)) {

                    try {
                        client.send(doc);
                        Element cachedDoc = new Element(documentId, doc);
                        cache.remove(selectedDoc);
                        cache.put(cachedDoc);
                    } catch (AxisFault e) {
                        e.printStackTrace();
                        throw e;
                    }

                    logService
                            .logInfo(
                                    SharepointNotifierParameters.getSystemId(),
                                    methodName
                                            + " il documento "
                                            + documentId
                                            + " e' diverso dalla versione in cache invio.... ");
                   
                } else {
                    logService
                    .logInfo(
                            SharepointNotifierParameters.getSystemId(),
                            methodName
                                    + " il documento "
                                    + documentId
                                    + " e' gia' stato inviato ");
                }

            } else {
                Element cachedDoc = new Element(documentId, doc);
                cache.put(cachedDoc);
                logService
                .logInfo(
                        SharepointNotifierParameters.getSystemId(),
                        methodName + documentId + " invio ");
                try {
                    client.send(doc);
                } catch (AxisFault e) {
                    e.printStackTrace();
                    throw e;
                }

            }
        }
    }

}
