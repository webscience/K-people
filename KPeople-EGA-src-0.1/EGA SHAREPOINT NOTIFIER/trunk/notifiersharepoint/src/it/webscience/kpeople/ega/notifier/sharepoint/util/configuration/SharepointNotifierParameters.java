package it.webscience.kpeople.ega.notifier.sharepoint.util.configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SharepointNotifierParameters {

    /*
     * private static Properties adapterproperties =
     * SharepointConfigurationManager.getProperties(); public static String
     * getEndpoint(){ return
     * adapterproperties.getProperty("sharepoint.webservice.endpoint"); }
     */
    public static String getSharepointServer() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("sharepoint.server").trim();
    }

    public static String getServiceName() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("serviceName").trim();
    }

    public static String getNumberList() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("numberList").trim();
    }

    public static String getEndpointAndList(int index) {
        String endpoint = SharepointConfigurationManager.getProperties()
                .getProperty("sharepoint.webservice.endpoint." + index);
        return endpoint.trim();
    }

    public static Date getSinceDate() {
        String siceDateString = SharepointConfigurationManager.getProperties()
                .getProperty("since.date");
        DateFormat formatter;
        Date date = null;
        formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            date = (Date) formatter.parse(siceDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @return un oggetto di tipo String che rappresenta l'identificativo
     *         univoco del sistema verticale di tipo Sharepoint.
     */
    public static String getSystemId() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("systemId").trim();
    }

    public static String getActionType() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("actionType").trim();
    }

    public static String getEndpointReference() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("EndpointReference").trim();
    }

    public static String getOMNamespace() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("OMNamespace").trim();
    }

    public static String getMethod() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("method").trim();
    }

    public static String getCertificationPath() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("certification.path").trim();
    }

    public static String getThreadSleep() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("SharepointWSClient.thread.sleep").trim();
    }

    public static String getUsername() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("sharepoint.server.username").trim();
    }

    public static String getPassword() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("sharepoint.server.password").trim();
    }

    public static String getDomain() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("sharepoint.server.domain").trim();
    }

    public static String getHost() {
        return SharepointConfigurationManager.getProperties()
                .getProperty("sharepoint.host").trim();
    }

}
