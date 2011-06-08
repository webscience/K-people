package it.webscience.uima;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.uima.UIMAFramework;
import org.apache.uima.aae.client.UimaAsynchronousEngine;
import org.apache.uima.adapter.jms.client.BaseUIMAAsynchronousEngine_impl;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceInitializationException;



public class AsClient {
        /**
         * The application context map is used to pass initialization parameters
         */
        Map<String, Object> appCtx;
        
        /**
         * Size of Cas pool to create to send to specified service. Default = 1.
         */
        private int casPoolSize = 2;
        
        /**
         * The initialFsHeapSize attribute is optional, and allows setting the size of the initial CAS Feature 
         * Structure heap. This number is specified in bytes, and the default is approximately 2 megabytes for Java 
         * top-level services, and 40 kilobytes for C++ top level services. The heap grows as needed; this 
         * parameter is useful for those cases where the expected heap size is much smaller than the default.
         */
        private int fsHeapSize = 2000000;
        
        /**
         * Specifies an Output Directory. All CASes received by the client's CallbackListener will be 
         * serialized to XMI in the specified OutputDir
         */
        private File outputDir = null;

        /**
         * The brokerURL attribute is optional. When omitted, a default value of tcp://localhost:61616 will be used. 
         * A different brokerURL can be provided as an override when launching a service.
         */
        private String brokerUrl = null;

        /**
         * Service queue name. Required for initialize.
         */
        private String endpoint = null;
        
        /**
         * Specifies a CollectionReader descriptor
         */
        private File collectionReaderDescriptor = null;
        
        /**
         * A client API object must be instantiated for each remote service an application will directly 
         * connect with, and a listener class registered in order to process asynchronous events
         */
        private UimaAsynchronousEngine uimaAsEngine = null;
        
        /**
         * Specifies a timeout period in seconds. If a CAS does not return within this time period it
         * is considered an error. By default there is no timeout, so the client will wait forever.
         */
        private int timeout = 0;
        
        /** logger. */
        private Logger logger = Logger.getLogger(this.getClass().getName());
        
        /**
         * Constructor for the class.
         * 
         * @param args command line arguments
         */
        public AsClient(String args[]) throws Exception {
                collectionReaderDescriptor = new File("C:\\kpeople-sviluppo\\k-peopleWorkSpace-solr\\workspace\\uimaJava\\descriptors\\collection_reader\\FileSystemCollectionReader.xml");
                
                outputDir = new File("C:\\kpeople-sviluppo\\k-peopleWorkSpace-solr\\workspace\\uimaJava\\processed");
                
                brokerUrl = "tcp://192.168.0.61:61616";
                
                endpoint = "mailQueue";
        }
        
        private void initializeUimaAsEngine(UimaAsynchronousEngine uimaAsEngine) throws ResourceInitializationException {
                appCtx = new HashMap<String, Object>();

//              utilizzati per undeployare l'uimaAsEngine
//              appCtx.put(UimaAsynchronousEngine.DD2SpringXsltFilePath, System.getenv("UIMA_HOME") + "/bin/dd2spring.xsl");
//              appCtx.put(UimaAsynchronousEngine.SaxonClasspath, "file:" + System.getenv("UIMA_HOME") + "/saxon/saxon8.jar");

//          TODO: valutare SerializationStrategy
//          SerializationStrategy:(Optional) xmi or binary serialization. Default = xmi 
//          appCtx.put(UimaAsynchronousEngine.SerializationStrategy, "binary");

                appCtx.put(UimaAsynchronousEngine.ServerUri, brokerUrl);
            appCtx.put(UimaAsynchronousEngine.Endpoint, endpoint);
            appCtx.put(UimaAsynchronousEngine.Timeout, timeout * 1000);
            appCtx.put(UimaAsynchronousEngine.CasPoolSize, casPoolSize);
            appCtx.put(UIMAFramework.CAS_INITIAL_HEAP_SIZE, Integer.valueOf(fsHeapSize / 4).toString());

            uimaAsEngine.initialize(appCtx);
        }

        public void run() throws Exception {
//              create Asynchronous Client API and initialize it
                uimaAsEngine = new BaseUIMAAsynchronousEngine_impl();
                
//              callback
//              uimaAsEngine.addStatusCallbackListener(new StatusCallbackListener());
                
                initializeUimaAsEngine(uimaAsEngine);

                String filePath = "C:\\kpeople-sviluppo\\k-peopleWorkSpace-solr\\workspace\\uimaJava\\src\\test\\event-2491.xml";
                String xml = readFile(filePath);
                

//              get an empty CAS from the Cas pool
                CAS cas = uimaAsEngine.getCAS();
        
//              Initialize it with input data
                cas.setDocumentText(xml);
                
        

//              Send Cas to service for processing
                String response = null;
                try {
                    response = uimaAsEngine.sendCAS(cas);
                } catch (Exception e) {
                    logger.error(e.getMessage());

                }

                System.out.println(response);

        }
        
     /*   @SuppressWarnings({ "unused" })
        private void analizzaXml(String xml) throws DocumentException, DatatypeConfigurationException {
                SAXReader reader = new SAXReader();
                Document document = reader.read(xml);
        
                String actionType = document.selectSingleNode( "//action/action-type" ).getText();
                
                String creationDate = document.selectSingleNode( "//event-data/creation-date" ).getText();
                String title = document.selectSingleNode( "//event-data/title" ).getText();
                String emailAuthor = document.selectSingleNode( "//event-data/author/email" ).getText();
                
                String a = "";
        }*/

        /**
         * Invia un CAS alla coda
         * @param args command line arguments
         * @throws Exception 
         */
        public static void main(String[] args) throws Exception {
                AsClient runner = new AsClient(args);
                runner.run();
        }

        /** 
         * legge il contenuto del file
         * @param filePath file da leggere
         * @return contenuto del file
         * @throws FileNotFoundException 
         */
        private String readFile(String filePath) throws FileNotFoundException {
            StringBuilder text = new StringBuilder();
            String NL = System.getProperty("line.separator");
            
            Scanner scanner = new Scanner(new FileInputStream(new File(filePath)), "UTF-8");
            try {
              while (scanner.hasNextLine()) {
                text.append(scanner.nextLine() + NL);
              }
            } finally {
              scanner.close();
            }
        
                return text.toString();
        }
}
