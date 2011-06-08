package it.webscience.uima.ocSolrAdapter.solrAdapterAgent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import it.webscience.uima.annotators.solr.OcSolrProperties;
import it.webscience.uima.ocControl.SolrPropertyKeys;
import it.webscience.uima.ocControl.ocSolrServerUtility.OCSolrServer;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest.ACTION;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;

/**
 * @author filieri
 */
public class DefaultSolrAdapterAgent implements SolrAdapterAgent {

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * istanza del server solr.
     */
    private static SolrServer solrServer = null;
    /**
     * oggetto di tipo properties contenente i parametri di configurazione di
     * solr.
     */
    private static Properties solrServerProperties = null;

    /**
     * Costruttore di default che istanzia il server solr.
     */
    public DefaultSolrAdapterAgent() {
        solrServer = OCSolrServer.getInstance().getSolrServer();
        solrServerProperties = OCSolrServer.getInstance().getApplicationProps();
    }

    /**
     * @see it.webscience.uima.ocSolrAdapter
     *      .solrAdapterAgent.SolrAdapterAgent#indexEvent()
     */
    public final void indexEvent() {

        if (logger.isDebugEnabled()) {
            logger.debug("Solr indexEvent - start");
        }
        SolrInputDocument doc = new SolrInputDocument();

        HashMap<String, Object> index = OcSolrProperties.getProperties();

        Iterator<String> it = index.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            Object value = index.get(key);
            doc.setField(key, value);
        }

        try {
            UpdateResponse updateResponseAdd = solrServer.add(doc);
            UpdateResponse updateResponseCommit = solrServer.commit();
        } catch (SolrServerException e) {
            logger.error(e.getStackTrace());
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Solr indexEvent - end");
        }

    }

    /**
     * @param attachment
     *            - File relativo all'attachment che deve essere indicizzato
     * @see it.webscience.uima.ocSolrAdapter.solrAdapterAgent.
     *      SolrAdapterAgent#indexAttachment(java.io.File)
     */
    public final void indexAttachment(final File attachment) {
        if (logger.isDebugEnabled()) {
            logger.debug("Solr indexAttachment - start");
        }
        String context =
                solrServerProperties
                        .getProperty(SolrPropertyKeys.SOLR_SERVER_UPDATECONTEXT);
        ContentStreamUpdateRequest up = new ContentStreamUpdateRequest(context);
        try {
            up.addFile(attachment);
            logger.debug("Solr indexAttachment - up.addFile" + attachment.getName());
        } catch (IOException e) {
            logger.error("Solr indexAttachment - up.addFile" + e.getStackTrace());
        }
        HashMap<String, Object> index = OcSolrProperties.getProperties();
        Iterator<String> it = index.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            String value = (String) index.get(key);
            up.setParam("literal." + key, value);
        }

        // up.setParam("literal.id" , "rrrr" );
        up.setAction(ACTION.COMMIT, true, true);
        try {
            NamedList<Object> result = solrServer.request(up);
            logger.debug("Solr indexAttachment - solrServer.request(up) riuscito");
        } catch (SolrServerException e) {
            logger.error(e.getStackTrace());
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Solr indexAttachment - end");
        }
    }
}
