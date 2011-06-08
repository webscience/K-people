package it.webscience.kpeople.ega.notifier.sharepoint.webserviceclient;

import it.webscience.kpeople.ega.notifier.sharepoint.model.SharepointDocument;
import it.webscience.kpeople.ega.notifier.sharepoint.util.configuration.SharepointNotifierParameters;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class SharepointNotifierWSClient {

    private static ServiceClient serviceClient;

    public void send(final SharepointDocument doc) throws AxisFault  {

        // BundleContext context =
        // FrameworkUtil.getBundle(SharepointConfigurationManager.class).getBundleContext();
        // String certification =
        // context.getBundle().getResource("/configuration/jssecacerts").getFile();
        // System.out.println(filepath);

        int counter = 3;
        
        String certification = SharepointNotifierParameters
                .getCertificationPath();
        System.setProperty("javax.net.ssl.trustStore", certification);
        System.setProperty("javax.net.ssl.trustStorePassword", "");

        if (serviceClient == null) {
            try {
                serviceClient = new ServiceClient();
            } catch (AxisFault e) {
                e.printStackTrace();
            }
        }

        Options opts = new Options();

        String endpointReference = (String) SharepointNotifierParameters
                .getEndpointReference();
        opts.setTo(new EndpointReference(endpointReference));
        opts.setAction("urn:aSimpleOperation");
        serviceClient.setOptions(opts);

        OMFactory fac = OMAbstractFactory.getOMFactory();

        String omnNamespace = (String) SharepointNotifierParameters
                .getOMNamespace();
        OMNamespace omNs = fac.createOMNamespace(omnNamespace, "ns1");

        String methodname = (String) SharepointNotifierParameters.getMethod();
        OMElement method = fac.createOMElement(methodname, omNs);

        OMElement par1 = fac.createOMElement("systemId", omNs);
        String systemId = (String) SharepointNotifierParameters.getSystemId();
        par1.setText(systemId);

        OMElement par2 = fac.createOMElement("actionType", omNs);
        String actiontype = (String) SharepointNotifierParameters
                .getActionType() + "/" + doc.getAction();
        par2.setText(actiontype);

        OMElement par3 = fac.createOMElement("actionReference", omNs);
        par3.setText(doc.getFileUrl());

        method.addChild(par1);
        method.addChild(par2);
        method.addChild(par3);

        try {
            //OMElement res = serviceClient.sendReceive(method);
            serviceClient.fireAndForget(method);
        } catch (AxisFault e) {
            while (counter > 0) {
                serviceClient.fireAndForget(method);
                counter = counter - 1;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();

        }

      
        //serviceClient.fireAndForget(method);

        // System.out.println(res);
    }

}
