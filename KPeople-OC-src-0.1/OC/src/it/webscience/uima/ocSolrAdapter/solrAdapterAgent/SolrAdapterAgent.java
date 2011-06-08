package it.webscience.uima.ocSolrAdapter.solrAdapterAgent;

import java.io.File;

/**
 * @author filieri
 */
interface SolrAdapterAgent {

    /**metodo per l'indicizzazione dell'evento.
     */
    void indexEvent();


    /**metodo per l'indicizzazione su solr dell'allegato associato all'evento.
     * @param attachment - file dell'attachment.
     */
    void indexAttachment(final File attachment);
}
