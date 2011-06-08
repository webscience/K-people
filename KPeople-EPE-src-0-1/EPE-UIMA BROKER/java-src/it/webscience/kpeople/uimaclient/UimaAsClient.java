package it.webscience.kpeople.uimaclient;

import it.webscience.kpeople.util.ConfigurationManager;
import it.webscience.kpeople.util.UimaPropertyKeys;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.uima.UIMAFramework;
import org.apache.uima.aae.client.UimaAsynchronousEngine;
import org.apache.uima.adapter.jms.client.BaseUIMAAsynchronousEngine_impl;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * Classe per la connessione al broker Uima.
 * @author filieri
 */
public class UimaAsClient {

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Istanza della classe singleton per la gestione dei parametri di
     * configurazione.
     */
    private ConfigurationManager configuration = null;

    /**
     * The application context map is used to pass initialization parameters.
     */
    private Map<String, Object> appCtx;

    /**
     * Size of Cas pool to create to send to specified service. Default = 1.
     */
    private int casPoolSize;

    /**
     * The initialFsHeapSize attribute is optional, and allows setting the size
     * of the initial CAS Feature Structure heap. This number is specified in
     * bytes, and the default is approximately 2 megabytes for Java top-level
     * services, and 40 kilobytes for C++ top level services. The heap grows as
     * needed; this parameter is useful for those cases where the expected heap
     * size is much smaller than the default.
     */
    private int fsHeapSize;

    /**
     * The brokerURL attribute is optional. When omitted, a default value of
     * tcp://localhost:61616 will be used. A different brokerURL can be provided
     * as an override when launching a service.
     */
    private String brokerUrl = null;

    /**
     * Service queue name. Required for initialize.
     */
    private String endpoint = null;

    /**
     * A client API object must be instantiated for each remote service an
     * application will directly connect with, and a listener class registered
     * in order to process asynchronous events.
     */
    private UimaAsynchronousEngine uimaAsEngine = null;

    /**
     * Specifies a timeout period in seconds. If a CAS does not return within
     * this time period it is considered an error. By default there is no
     * timeout, so the client will wait forever.
     */
    private int timeout;

    /**
     * Constructor for the class.
     */
    public UimaAsClient() {

        logger.info(this.getClass() + " start :configura client uima ");
        configuration = ConfigurationManager.getInstance();
        casPoolSize =
                Integer.parseInt(configuration
                        .getProperty(UimaPropertyKeys.CAS_POOL_SIZE));
        fsHeapSize =
                Integer.parseInt(configuration
                        .getProperty(UimaPropertyKeys.FS_HEAP_SIZE));
        timeout =
                Integer.parseInt(configuration
                        .getProperty(UimaPropertyKeys.TIMEOUT));

        brokerUrl = configuration.getProperty(UimaPropertyKeys.UIMA_BROKER_URL);
        endpoint = configuration.getProperty(UimaPropertyKeys.UIMA_ENDPOINT);

        logger.info(this.getClass() + " stop :configura client uima ");

    }

    /**
     * Metodo per l'esecuzione del client uima.
     * @param content
     *            - stringa che rappresenta l'evento che deve essere inviato al
     *            framework uima.
     * @throws Exception
     *             - ResourceProcessException
     */
    public final void run(final String content) throws Exception {

        logger.info(this.getClass()
                + " start :crea un Asynchronous Client API e istanzia una cas");

        // crea un Asynchronous Client API e lo inizializza.
        uimaAsEngine = new BaseUIMAAsynchronousEngine_impl();

        initializeUimaAsEngine(uimaAsEngine);

        // restituisce una cas vuota dal Cas pool
        CAS cas = uimaAsEngine.getCAS();

        // Initializza la cas con l'evento da processare.
        cas.setDocumentText(content);

        String response = null;
        try {
            // invia la cac al servizio per essere processata.
            response = uimaAsEngine.sendAndReceiveCAS(cas);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }
        uimaAsEngine.stop();
        logger.info(this.getClass() + " stop :chiamata al client uima riuscita");

    }

    /**
     * Metodo che inizializza l'engine sul framework uima.
     * @param uimaAsEngineInstance
     *            -
     * @throws ResourceInitializationException
     *             -
     */
    private void initializeUimaAsEngine(
            final UimaAsynchronousEngine uimaAsEngineInstance)
             {

        logger.info(this.getClass() + " start :inizializzazione client");
        appCtx = new HashMap<String, Object>();

        appCtx.put(UimaAsynchronousEngine.ServerUri, brokerUrl);
        logger.info(this.getClass() + " start :brokerUrl: " + brokerUrl);

        appCtx.put(UimaAsynchronousEngine.Endpoint, endpoint);
        logger.info(this.getClass() + " start :endpoint: " + endpoint);
        
        
        appCtx.put(UimaAsynchronousEngine.Timeout, timeout * 1000);
        logger.info(this.getClass() + " start :timeout: " + timeout);

        appCtx.put(UimaAsynchronousEngine.CasPoolSize, casPoolSize);
        logger.info(this.getClass() + " start :casPoolSize: " + casPoolSize);
        
        appCtx.put(UIMAFramework.CAS_INITIAL_HEAP_SIZE,
                Integer.valueOf(fsHeapSize / 4).toString());

        logger.info(this.getClass() + " prima initializa ");
        try {
            uimaAsEngineInstance.initialize(appCtx);
        } catch (ResourceInitializationException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }
        logger.info(this.getClass() + " dopo initializa ");


        logger.info(this.getClass() + " stop :inizializzazione client riuscita");
    }

}
