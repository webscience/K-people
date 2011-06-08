package it.webscience.uima.ocStorageAbstract;

import java.io.IOException;

import org.apache.uima.jcas.JCas;

/** agent per il salvataggio sullo storage.
 * @author dellanna
 */
public abstract class OcStorageAdapterAgent {

    /** invia la chiamata allo storage.
     * @param cas cas da processare
     * @return esito del salvatagio sullo storage
     * @throws IOException problemi di connessione con lo storage adapter
     * @throws ClassNotFoundException classi non trovate
     */
    public abstract OcStorageResponse storeEventData(final JCas cas)
        throws IOException, ClassNotFoundException;
}
