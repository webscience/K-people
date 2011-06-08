package it.webscience.kpeople.web.portlet.process.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;



public class EventUtil {

    /**
     * @param details Stringa realtiva ai dettagli di un eventMetadata
     * @return lista di Stringhe relative ai receivers della mail.
     */
    public static List<String> getReceivers(final String details) {
        List<String> listReceivers = new ArrayList<String>();
        String xml = getDetails(details);
        String xPath = "//email-details/receivers";

        Document doc = trasformStringToDocument(xml);
        List<Node> nodes = doc.selectNodes(xPath);


        for (Node node : nodes) {
            Element nodeElement = (Element) node;
            List<Element>childElements = nodeElement.elements();
            String name = "";
            for (int i = 0; i < childElements.size(); i++) {
                 name = childElements.get(i).getText();
                 listReceivers.add(name);
            }
        }
        return listReceivers;
    }

    /**
     * Metodo privato per una stringa in un documento XML.
     * @param xml Stringa rappresentativa dell'xml.
     * @return oggetto di tipo Document
     */
    private static Document trasformStringToDocument(final String xml) {
        Document document = null;

        try {
             document = DocumentHelper.parseText(xml);
          } catch (DocumentException e) {
             e.printStackTrace();
          }
          return document;
    }

    /**
     * @param html stringa contenente html.
     * @return stringa contenente html ben formato.
     */
    public static String getBodyMail(final String html) {
        String htmlResponse = StringEscapeUtils.unescapeHtml(html);
        //String htmlResponse = html.replaceAll("&lt;", "<");
        return htmlResponse;
    }

    /**
     * @param xml non ben formato.
     * @return xml ben formato.
     */
    private static String getDetails(String xml) {
        String xmlResponse = StringEscapeUtils.unescapeXml(xml);
        return xmlResponse;
    }
    
    public String formatDate(Date in) {
        String dateFormatted = null;
        Format formatter = new SimpleDateFormat("dd/MM/yy");
        dateFormatted = formatter.format(in);
        
        return dateFormatted;
        
    }
}
