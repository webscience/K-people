package it.webscience.uima.ocControl.ocSolrServerUtility;

import org.apache.solr.client.solrj.SolrServer;

public class SolrServerUtil {

    /**
     * @return SolrServer - istanza del server.
     */
    public static SolrServer getSolrServer() {
        return OCSolrServer.getInstance().getSolrServer();
    }

}
