package it.webscience.uima.ocControl.ocSesameServerUtility;

import it.webscience.uima.ocControl.SesamePropertyKeys;
import it.webscience.uima.ocSesameAdapter.sesameAdapterConfigurationManager.SesameAdapterConfigurationManager;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openrdf.repository.Repository;
import org.openrdf.repository.http.HTTPRepository;

/**
 * @author filieri
 */
public final class OCRdfRepository {

    /**
     * volatile is needed so that multiple thread can reconcile the instance.
     * semantics for volatile changed in Java 5.
     */
    private static volatile OCRdfRepository _singleton;

    /**
     * default properties.
     */
    private Properties defaultProps = new Properties();

    /**
     * application properties.
     */
    // protected Properties applicationProps = new Properties();

    /**
     */
    private static Logger _logger = Logger.getLogger(OCRdfRepository.class);

    /**
     */
    private Repository _sesameRepository = null;

    /**Istanza della classe SesameAdapterConfigurationManager. */
    private SesameAdapterConfigurationManager configInstance;

    /**
     */
    private OCRdfRepository() {
        try {
            _logger.info("Lettura della configurazione "
                    + "per la connessione al server SESAME ...");

            configInstance = SesameAdapterConfigurationManager.getInstance();
            // String confPath = OCRdfRepository.class
            // .getClassLoader()
            // .getResource(
            // SesamePropertyKeys.
            // SESAME_ADAPTER_PROPERTIES_DEFAULT_RES)
            // .getFile();

            // FileInputStream in = new FileInputStream(confPath);
            // defaultProps.load(in);
            // in.close();

            // applicationProps = new Properties(defaultProps);

            StringBuffer sb = new StringBuffer();
            sb.append(configInstance
                    .getProperty(SesamePropertyKeys.SESAME_SERVER_PROTOCOL));
            sb.append("://");
            sb.append(configInstance
                    .getProperty(SesamePropertyKeys.SESAME_SERVER_HOST));
            sb.append(":");
            sb.append(configInstance
                    .getProperty(SesamePropertyKeys.SESAME_SERVER_PORT));
            sb.append(configInstance
                    .getProperty(SesamePropertyKeys.SESAME_SERVER_CONTEXT));

            String url = sb.toString();

            String idRepository =
                    configInstance
                            .getProperty(SesamePropertyKeys.SESAME_SERVER_IDREPOSITORY);

            _sesameRepository = new HTTPRepository(url, idRepository);

            _sesameRepository.initialize();

        } catch (Exception e) {

            _logger.error("Errore di inizializzazione: " + e);

            System.exit(1);
        }
    }

    // synchronized keyword has been removed from here
    /**
     * @return OCRdfRepository.
     */
    public static OCRdfRepository getInstance() {
        // needed because once there is singleton available no need to acquire
        // monitor again & again as it is costly
        if (_singleton == null) {
            synchronized (OCRdfRepository.class) {
                // this is needed if two threads are waiting at the monitor at
                // the
                // time when singleton was getting instantiated
                if (_singleton == null) {
                    _singleton = new OCRdfRepository();
                }
            }
        }
        return _singleton;
    }

    // /**
    // * @return Properties.
    // */
    // public Properties getApplicationProps() {
    // return applicationProps;
    // }

    /**
     * @return _sesameRepository
     */
    public Repository getSesameRepository() {
        return _sesameRepository;
    }

    /**
     * @return _sesameRepository
     */
    public String getSesameBaseUri() {
        return configInstance
                .getProperty(SesamePropertyKeys.SESAME_SERVER_BASEURI);
    }

}
