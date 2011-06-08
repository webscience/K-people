package it.webscience.uima.ocSolrAdapter.solrAdapterConfigurationManager;

import org.apache.log4j.Logger;

/**
 * @author filieri
 *
 */
public class SolrAdapterConfigurationManager {

    // volatile is needed so that multiple thread can reconcile the instance
    // semantics for volatile changed in Java 5.
    private volatile static SolrAdapterConfigurationManager _singleton;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private SolrAdapterConfigurationManager() {
        try {

        } catch (Exception e) {
            logger.error("Errore di inizializzazione: " + e.getMessage());

            System.exit(1);
        }
    }

    /**
     * @return _singleton - istanza singleton
     * della classe SolrAdapterConfigurationManager.
     */
    public static SolrAdapterConfigurationManager getInstance() {
        // needed because once there is singleton available no need to acquire
        // monitor again & again as it is costly
        if (_singleton == null) {
            synchronized (SolrAdapterConfigurationManager.class) {
                // this is needed if two threads are waiting at the monitor at
                // the
                // time when singleton was getting instantiated
                if (_singleton == null) {
                    _singleton = new SolrAdapterConfigurationManager();
                }
            }
        }
        return _singleton;
    }

}
