package it.webscience.kpeople.ega.notifier.sharepoint.util.parser;

import it.webscience.kpeople.ega.notifier.sharepoint.model.SharepointDocument;

import it.webscience.kpeople.ega.notifier.sharepoint.util.cache.CacheService;
import it.webscience.kpeople.ega.notifier.sharepoint.util.configuration.SharepointNotifierParameters;
import it.webscience.kpeople.ega.notifier.sharepoint.webserviceclient.SharepointNotifierWSClient;

import java.text.ParseException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;

public class DocumentDeletedParser {

    public static void parse(final OMElement allItemsInList,
            final String endpoint) throws ParseException {

        List<SharepointDocument> documents = new LinkedList<SharepointDocument>();

        Iterator<?> it = allItemsInList.getChildElements();

        while (it.hasNext()) {

            OMElement row = (OMElement) it.next();

            SharepointDocument doc = new SharepointDocument();

            QName fileRef = new QName("ows_FileRef");
            String fileUrl = row.getAttributeValue(fileRef);
            fileUrl = fileUrl.split(";#")[1];
            String serviceReference = SharepointNotifierParameters
                    .getServiceName();
            String endpointReference = endpoint.replace(serviceReference, "");
            doc.setFileUrl(endpointReference + fileUrl);

            QName fileId = new QName("ows_UniqueId");
            String documentId = row.getAttributeValue(fileId);
            doc.setDocumentId(documentId);

            if (doc != null) {
                documents.add(doc);
            }

        }

        try {
            checkCache(documents);
        } catch (AxisFault e) {
            e.printStackTrace();
        }

    }

    private static void checkCache(final List<SharepointDocument> documents)
            throws AxisFault {

        Cache cache = CacheService.getInstance().getCacheDelete();
        List<String> documentsKeys = new LinkedList<String>();

        SharepointNotifierWSClient client = new SharepointNotifierWSClient();

        for (SharepointDocument doc : documents) {
            documentsKeys.add(doc.getDocumentId());
        }

        if (cache.getKeys().isEmpty()) {
            for (SharepointDocument doc : documents) {
                String documentId = doc.getDocumentId();
                Element cachedDoc = new Element(documentId, doc);
                cache.put(cachedDoc);
            }
        } else {
            Iterator<?> it = cache.getKeys().iterator();
            while (it.hasNext()) {
                String currentKey = (String) it.next();
                if (!documentsKeys.contains(currentKey)) {
                    Element rr = cache.get(currentKey);
                    SharepointDocument doc = (SharepointDocument) rr.getValue();
                    doc.setAction("DELETED");
                    cache.remove(currentKey);
                    client.send(doc);
                }
            }

            /*
             * for (SharepointDocument doc : documents) { String documentId =
             * doc.getDocumentId(); if (!cache.getKeys().contains(documentId)) {
             * doc.setAction("DELETED"); cache.remove(documentId);
             * client.send(doc); } }
             */

        }

    }

}
