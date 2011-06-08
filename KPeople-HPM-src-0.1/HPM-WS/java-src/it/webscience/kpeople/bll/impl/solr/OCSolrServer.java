package it.webscience.kpeople.bll.impl.solr;

import it.webscience.kpeople.dal.Singleton;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;

/**
 * Classe per la gestione del server SOLR.
 */
public final class OCSolrServer {

    /**
     * volatile is needed so that multiple thread can reconcile the instance.
     * semantics for volatile changed in Java 5.
     */
    private static volatile OCSolrServer singleton;

    /**
     * application properties.
     */
    private Properties applicationProps = new Properties();

    /**
     */
    private static Logger logger = Logger.getLogger(OCSolrServer.class);

    /**
     */
    private SolrServer solrServer = null;

    /**
     */
    private OCSolrServer() {
        try {
            String protocol =
                Singleton.getInstance().getProperty("solr.server.protocol");
            String host =
                Singleton.getInstance().getProperty("solr.server.host");
            String port =
                Singleton.getInstance().getProperty("solr.server.port");
            String context =
                Singleton.getInstance().getProperty("solr.server.context");

            String url = protocol + "://" + host + ":" + port + context;

            logger.info("Solr server url: " + url);

            solrServer = new CommonsHttpSolrServer(url);

        } catch (Exception e) {
            logger.error("Errore di inizializzazione: " + e);

            System.exit(1);
        }
    }

    // synchronized keyword has been removed from here
    /**
     * @return OCSolrServer.
     */
    public static OCSolrServer getInstance() {
        // needed because once there is singleton available no need to acquire
        // monitor again & again as it is costly
        if (singleton == null) {
            synchronized (OCSolrServer.class) {
                // this is needed if two threads are waiting at the monitor at
                // the
                // time when singleton was getting instantiated
                if (singleton == null) {
                    singleton = new OCSolrServer();
                }
            }
        }
        return singleton;
    }

    /**
     * @return Properties.
     */
    public Properties getApplicationProps() {
        return applicationProps;
    }

    /**
     * @return SolrServer.
     */
    public SolrServer getSolrServer() {
        return solrServer;
    }

}
