package it.webscience.kpeople.ega.channel.egaChannelSender;

import java.io.File;
import java.util.Properties;

import it.webscience.kpeople.ega.channel.EgaChannelLabel;
import it.webscience.kpeople.ega.channel.KpeopleLogger;
import it.webscience.kpeople.ega.channel.egaChannelManager.EgaChannelManager;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.util.AXIOMUtil;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.deployment.FileSystemConfigurator;
import org.apache.axis2.engine.AxisConfigurator;

/**
 * Classe per la costruzione del client Axis2 per l'invio dell'evento all'EPE.
 * @author filieri
 */
public class EgaChannelSender {

    /**
     * Url del proxy esposto dal componente EGA.
     */
    private String addUrl = "";
    /**
     * Contenitore per i parametri di configurazione del client.
     * @see org.apache.axis2.client.Options
     */
    private Options options;
    /**
     * Riferimento all'urn del servizio.
     */
    private String action = "";

    /**
     * Riferimento al topic namespace.
     */
    private String topicns = "";

    /**
     * Contiene informazioni sulle impostazioni globali a livello di runtime.
     */
    private ConfigurationContext configContext = null;

    /**
     * Istanza della classe ServiceClient per la realizzazione del client per il
     * servizio.
     */
    private ServiceClient serviceClient;

    /**
     * Variabile booleana che indica l'esito dell'operazione di invio
     * dell'evento all'EPE.
     */
    private Boolean isSent = true;

    /**
     * Costruttore del client Axis2 per l'invio dell'evento.
     * @throws AxisFault
     *             - eccezione generata in caso di problemi di connessione con
     *             il servizio.
     */
    public EgaChannelSender() throws AxisFault {
        options = new Options();
        configContext = null;

        Properties channelProperties = EgaChannelManager.getChannelProperties();
        addUrl =
                channelProperties
                        .getProperty(EgaChannelLabel.getProxyAddress());

        topicns =
                channelProperties
                        .getProperty(EgaChannelLabel.getEventTopicNS());
        // topicns = EgaChannelLabel.getEventTopicNS();
        action = EgaChannelLabel.getEventUrn();

        String repoPath =
                channelProperties.getProperty(EgaChannelLabel
                        .getRepositoryAxis2Path());
        // String repoAxis2xml = EgaChannelLabel.getRepositoryAxis2XML();
        String repoAxis2xml =
                repoPath + File.separator + "conf" + File.separator
                        + "axis2.xml";

        AxisConfigurator configurator = null;
        try {
            configurator = new FileSystemConfigurator(repoPath, repoAxis2xml);
        } catch (AxisFault e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        ConfigurationContext configuratorContext = null;
        try {
            configuratorContext =
                    ConfigurationContextFactory
                            .createConfigurationContext(configurator);
        } catch (AxisFault e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            throw e1;
        }

        try {

            // ConfigurationContext configuration = ConfigurationContextFactory
            // .createDefaultConfigurationContext();

            if (serviceClient == null) {

                serviceClient = new ServiceClient(configuratorContext, null);
                // serviceClient = new ServiceClient(configuration, null);
            }
            serviceClient.engageModule("addressing");

        } catch (AxisFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo specifico per la spedizione dell'evento.
     * @param xmlEvent
     *            - oggetto che rappresenta l'evento che deve essere spedito.
     * @param topic
     *            - indicazione del topic assegnato all'evento in base al quale
     *            effettuare il routing sui proxy presenti nel modulo EPE.
     * @return isSent - variabile booleana indicativa dell'esito delle
     *         operazioni di invio dell'evento.
     * @throws Exception
     *             - le eccezioni riguardano problemi inerenti l'esecuzione del
     *             Thread (InterruptedException) oppure la connessione del
     *             client al servizio esposto dal modulo EPE (AxisFault).
     */
    public Boolean sendEvent(final String xmlEvent, final String topic)
            throws Exception {

        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace nsaip = factory.createOMNamespace(topicns, "aip");
        // set the target topic
        OMElement topicOm = factory.createOMElement("Topic", nsaip);
        factory.createOMText(topicOm, topic);

        // set addressing, transport and proxy url

        options.setTo(new EndpointReference(addUrl));
        options.setAction(action);

        OMNamespace ns =
                factory.createOMNamespace("http://services.samples", "m0");

        OMElement payload = AXIOMUtil.stringToOM(xmlEvent);

        serviceClient.setOptions(options);
        serviceClient.addHeader(topicOm);

        OMElement response = null;

        try {
            KpeopleLogger
            .getInstance()
            .getService()
            .logInfo("---call-----");
           
            serviceClient.fireAndForget(payload);
            
            //response = serviceClient.sendReceive(payload);
           

        } catch (AxisFault e1) {
            isSent = false;
            e1.printStackTrace();
        }

        if (configContext != null) {
            try {
                configContext.terminate();
            } catch (AxisFault e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        KpeopleLogger
        .getInstance()
        .getService()
        .logInfo("value of sent : " + isSent);

        return isSent;

    }

}
