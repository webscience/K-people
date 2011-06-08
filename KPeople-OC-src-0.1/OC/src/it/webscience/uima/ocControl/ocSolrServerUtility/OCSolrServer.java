package it.webscience.uima.ocControl.ocSolrServerUtility;

import it.webscience.uima.ocControl.SolrPropertyKeys;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;

/**
 * @author filieri
 */
public final class OCSolrServer {

    /**
     * volatile is needed so that multiple thread can reconcile the instance.
     * semantics for volatile changed in Java 5.
     */
    private static volatile OCSolrServer _singleton;

    /**
     * default properties.
     */
    private Properties defaultProps = new Properties();

    /**
     * application properties.
     */
    protected Properties applicationProps = new Properties();

    /**
     */
    private static Logger _logger = Logger.getLogger(OCSolrServer.class);

    /**
     */
    private SolrServer _solrServer = null;

    /**
     */
    private OCSolrServer() {
        try {
            _logger.info("Lettura della configurazione "
                    + "per la connessione al server SOLR ...");

            String confPath =
                    OCSolrServer.class
                            .getClassLoader()
                            .getResource(
                                    SolrPropertyKeys.SOLR_ADAPTER_PROPERTIES_DEFAULT_RES)
                            .getFile();

            FileInputStream in = new FileInputStream(confPath);
            defaultProps.load(in);
            in.close();

            applicationProps = new Properties(defaultProps);

            StringBuffer sb = new StringBuffer();
            sb.append(applicationProps.getProperty(
                    SolrPropertyKeys.SOLR_SERVER_PROTOCOL, "http"));
            sb.append("://");
            sb.append(applicationProps.getProperty(
                    SolrPropertyKeys.SOLR_SERVER_HOST, "localhost"));
            sb.append(":");
            sb.append(applicationProps.getProperty(
                    SolrPropertyKeys.SOLR_SERVER_PORT, "8983"));
            sb.append(applicationProps.getProperty(
                    SolrPropertyKeys.SOLR_SERVER_CONTEXT, "/solr"));

            String url = sb.toString();

            _solrServer = new CommonsHttpSolrServer(url);

            /*
             * Eventuali altre propriet� da settare server.setSoTimeout(1000);
             * // socket read timeout server.setConnectionTimeout(100);
             * server.setDefaultMaxConnectionsPerHost(100);
             * server.setMaxTotalConnections(100);
             * server.setFollowRedirects(false); // defaults to false //
             * allowCompression defaults to false. // Server side must support
             * gzip or deflate for this to have any effect.
             * server.setAllowCompression(true); server.setMaxRetries(1); //
             * defaults to 0. > 1 not recommended. server.setParser(new
             * XMLResponseParser()); // binary parser is used by default
             */
        } catch (Exception e) {

            _logger.error("Errore di inizializzazione: " + e);

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
        if (_singleton == null) {
            synchronized (OCSolrServer.class) {
                // this is needed if two threads are waiting at the monitor at
                // the
                // time when singleton was getting instantiated
                if (_singleton == null) {
                    _singleton = new OCSolrServer();
                }
            }
        }
        return _singleton;
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
        return _solrServer;
    }

}
