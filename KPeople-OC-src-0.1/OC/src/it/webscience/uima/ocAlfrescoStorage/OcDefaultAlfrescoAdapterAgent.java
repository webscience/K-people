package it.webscience.uima.ocAlfrescoStorage;

import java.io.IOException;

import org.apache.uima.jcas.JCas;

import it.webscience.uima.ocStorageAbstract.OcStorageAdapterAgent;
import it.webscience.uima.ocStorageAbstract.OcStorageRequest;
import it.webscience.uima.ocStorageAbstract.OcStorageResponse;

/** Gestisce il salvataggio su Alfresco.
 * @author dellanna
 */
public class OcDefaultAlfrescoAdapterAgent extends OcStorageAdapterAgent {
    /** parser per il parsing della cas in ingresso. */
    private OcAlfrescoCasParser alfrescoCasParser;

    /**
     * @param cas cas da processare
     * @return risultato del salvataggio
     * @throws ClassNotFoundException classe non trovata
     * @throws IOException problemi di connessione con Alfresco
     */
    @Override
    public final OcStorageResponse storeEventData(final JCas cas)
            throws IOException, ClassNotFoundException {

//      parsing dell'xml in ingresso
        alfrescoCasParser = new OcAlfrescoCasParser();
        OcStorageRequest sr = alfrescoCasParser.parseCas(cas);

        OcStorageResponse response = callAlfrescoWebScript(sr);

        return response;
    }

    /** esegue la chiamata ad Alfresco.
     * @param sr documento da inviare in POST
     * @return risposta di Alfresco
     * @throws IOException problemi di connessione con Alfresco
     * @throws ClassNotFoundException classe non trovata
     */
    private OcStorageResponse callAlfrescoWebScript(final OcStorageRequest sr)
            throws IOException, ClassNotFoundException {
        OcAlfrescoStorageUtility asu = new OcAlfrescoStorageUtility();
        OcStorageResponse response =  asu.store(sr);

        return response;
    }
}
