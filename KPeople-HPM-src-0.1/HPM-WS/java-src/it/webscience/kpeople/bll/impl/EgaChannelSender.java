package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.dal.Singleton;

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
import org.apache.log4j.Logger;


/**
 * Classe per la costruzione del client Axis2 per l'invio dell'evento all'EPE.
 */
public class EgaChannelSender {

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

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
    private Boolean isSent = false;

    /**
     * Costruttore del client Axis2 per l'invio dell'evento.
     * @throws AxisFault
     *             - eccezione generata in caso di problemi di connessione con
     *             il servizio.
     */
    public EgaChannelSender() throws AxisFault {
        Singleton singleton = Singleton.getInstance();

        options = new Options();
        addUrl = singleton.getProperty("ega-channel-sender.addUrl");
        topicns = singleton.getProperty("ega-channel-sender.topicNs");
        action = "urn:event";

        String clientRepoPath =
            singleton.getProperty("ega-channel-sender.client-repo");

        logger.debug("clientRepoPath: " + clientRepoPath);

        String repoAxis2xml = clientRepoPath + "/conf/axis2.xml";

        AxisConfigurator configurator = null;
        try {
            configurator = new FileSystemConfigurator(
                    clientRepoPath,
                    repoAxis2xml);
        } catch (AxisFault e1) {
            e1.printStackTrace();
        }

        ConfigurationContext configuratorContext = null;
        try {
            configuratorContext = ConfigurationContextFactory
                    .createConfigurationContext(configurator);
        } catch (AxisFault e1) {
            e1.printStackTrace();
            throw e1;
        }

        try {
            if (serviceClient == null) {
                serviceClient = new ServiceClient(configuratorContext, null);
            }
            serviceClient.engageModule("addressing");
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("end EgaChannelSender()");
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
    public final Boolean sendEvent(final String xmlEvent, final String topic)
            throws Exception {

        logger.debug("sendEvent - begin");

        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace nsaip = factory.createOMNamespace(topicns, "aip");

        // set the target topic
        OMElement topicOm = factory.createOMElement("Topic", nsaip);
        factory.createOMText(topicOm, topic);

        // set addressing, transport and proxy url
        options.setTo(new EndpointReference(addUrl));
        options.setAction(action);

        OMElement payload = AXIOMUtil.stringToOM(xmlEvent);

        serviceClient.setOptions(options);
        serviceClient.addHeader(topicOm);

        try {
            serviceClient.sendReceive(payload);
        } catch (AxisFault e1) {
            e1.printStackTrace();
        }

        if (configContext != null) {
            try {
                configContext.terminate();
            } catch (AxisFault e) {
                e.printStackTrace();
            }
        }

        logger.debug("sendEvent - end");

        return isSent;
    }
}
