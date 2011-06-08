package it.webscience.kpeople.ega.adapter.sharepoint.copyservice;


import it.webscience.kpeople.ega.adapter.sharepoint.KpeopleLabel;
import it.webscience.kpeople.ega.adapter.sharepoint.actionDataProcessorManager.ActionDataProcessorManager;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;

import java.util.List;


import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;

import org.apache.axis2.AxisFault;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.databinding.ADBException;

import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;

import com.microsoft.schemas.sharepoint.soap.GetItem;
import com.microsoft.schemas.sharepoint.soap.GetItemResponse;

public class SharepointWSClient {

    /**
     * Oggetto di classe Properties necessario a
     * contenere le proprietà di configurazione del componente adapter.
     */
    private static Properties currProperties;
    
    public static GetItemResponse call(final String actionReference) {

        currProperties = ActionDataProcessorManager.getAdapterproperties();
        ConfigurationContext configuration = null;
        try {
            configuration = setConfiguration();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String[] splittedReference = actionReference.split("@");
        String host = splittedReference[0];
        String site = splittedReference[1];
        String fileUrl = host + splittedReference[2];

        String endpoint = site + "_vti_bin/copy.asmx";
        // String[] listsToMonitor =
        // SharepointNotifierParameters.getListsToMonitorName();

        CopyStub service = null;


        try {
            service = new CopyStub(configuration, endpoint);
        } catch (AxisFault e) {
            e.printStackTrace();
        }
        GetItem item = new GetItem();

        item.setUrl(fileUrl.trim());
        GetItemResponse response = null;
        try {
            response = service.getItem(item);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        
        
       /* OMElement omElement = null;
        try {
            omElement = response.getOMElement(response.MY_QNAME,
                    OMAbstractFactory.getOMFactory());

            DataHandler stream = response.getStream();

            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(new File("ppp.properties"));
                stream.writeTo(outputStream);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            String omElementString = omElement.toStringWithConsume();
            System.out.println("response " + omElementString);
        } catch (ADBException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (XMLStreamException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
        
        return response;

    }

    /**
     * @param service
     * @param listName
     * @param since
     * @throws RemoteException
     */

    private static ConfigurationContext setConfiguration() throws Exception {
        HttpTransportProperties.Authenticator authenticator = new HttpTransportProperties.Authenticator();
        List<String> auth = new ArrayList<String>();

        auth.add(HttpTransportProperties.Authenticator.NTLM);
        authenticator.setAuthSchemes(auth);

        String username = currProperties.getProperty(KpeopleLabel.getUserName());
        authenticator.setUsername(username);
        
        String password = currProperties.getProperty(KpeopleLabel.getPassword());
        authenticator.setPassword(password);
        
        String domain = currProperties.getProperty(KpeopleLabel.getDomain());
        authenticator.setDomain(domain);
        
        String host = currProperties.getProperty(KpeopleLabel.getHost());
        authenticator.setHost(host);
        
        authenticator.setPreemptiveAuthentication(true);

        ConfigurationContext configuration;
        try {
            configuration = ConfigurationContextFactory
                    .createDefaultConfigurationContext();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        configuration.setProperty(HTTPConstants.AUTHENTICATE, authenticator);
        return configuration;

    }

}
