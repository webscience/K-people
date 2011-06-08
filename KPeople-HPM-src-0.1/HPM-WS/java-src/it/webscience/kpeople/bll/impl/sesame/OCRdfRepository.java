package it.webscience.kpeople.bll.impl.sesame;

import it.webscience.kpeople.dal.Singleton;

import org.apache.log4j.Logger;
import org.openrdf.repository.Repository;
import org.openrdf.repository.http.HTTPRepository;

/**
 * classe per l'accesso al server Sesame.
 */
public final class OCRdfRepository {

    /**
     * volatile is needed so that multiple thread can reconcile the instance.
     * semantics for volatile changed in Java 5.
     */
    private static volatile OCRdfRepository singleton;

    /** logger. */
    private static Logger logger = Logger.getLogger(OCRdfRepository.class);

    /** repository. */
    private Repository sesameRepository = null;


    /**
     * costruttore.
     */
    private OCRdfRepository() {
        try {
            logger.info(
                    "Lettura della configurazione "
                    + "per la connessione al server SESAME ...");

            String protocol =
                Singleton.getInstance().getProperty("sesame.server.protocol");

            String host =
                Singleton.getInstance().getProperty("sesame.server.host");

            String port =
                Singleton.getInstance().getProperty("sesame.server.port");

            String context =
                Singleton.getInstance().getProperty("sesame.server.context");

            String idRepository =
                Singleton.getInstance().getProperty(
                        "sesame.server.idRepository");

            String url = protocol + "://" + host + ":" + port + context;

            logger.info("Sesame url:" + url);
            logger.info("Sesame idRepository: " + idRepository);

            sesameRepository = new HTTPRepository(url, idRepository);
            sesameRepository.initialize();

        } catch (Exception e) {
            logger.error("Errore di inizializzazione: " + e);
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
        if (singleton == null) {
            synchronized (OCRdfRepository.class) {
                // this is needed if two threads are waiting at the monitor at
                // the
                // time when singleton was getting instantiated
                if (singleton == null) {
                    singleton = new OCRdfRepository();
                }
            }
        }
        return singleton;
    }

    /**
     * @return _sesameRepository
     */
    public Repository getSesameRepository() {
        return sesameRepository;
    }

    /**
     * @return _sesameRepository
     */
    public String getSesameBaseUri() {
        String baseUri =
            Singleton.getInstance().getProperty("sesame.server.baseURI");

        return baseUri;
    }

}
