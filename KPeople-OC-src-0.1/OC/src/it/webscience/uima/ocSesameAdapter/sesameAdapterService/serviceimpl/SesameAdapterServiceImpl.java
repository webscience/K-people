package it.webscience.uima.ocSesameAdapter.sesameAdapterService.serviceimpl;

import org.apache.uima.cas.CAS;

import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.SesameAdapterAgent;
import it.webscience.uima.ocSesameAdapter.sesameAdapterService.serviceinterface.SesameAdapterServiceInterface;
import it.webscience.uima.ocSesameAdapter.sesameAdapterService.sesameAdapterQueryExecutor.SesameAdapterQueryExecutor;

/**
 * @author filieri
 */
public class SesameAdapterServiceImpl implements SesameAdapterServiceInterface {

    /**
     * Implementazione del metodo per interrogare l'ontologia presente sul
     * server Sesame al fine di individuare il processo associato ad una
     * specifica keyword.
     * @param keyword
     *            - keyword da utilizzare come indice per la query.
     * @return String - rappresenta l'URI del processo associato alla keyword.
     */
    public final String getProcessByKeyword(final String keyword) {
        SesameAdapterQueryExecutor sesameQueryExecutor =
                new SesameAdapterQueryExecutor();
        String processResource =
                sesameQueryExecutor.queryProcessByKeyword(keyword);
        return processResource;
    }

    /**
     * Definizione del metodo per interrogare l'ontologia presente sul server
     * Sesame al fine di individuare il pattern associato ad uno specifico id.
     * @param id
     *            - id da utilizzare come indice per la query.
     * @return String - rappresenta l'URI del pattern associato all'id.
     */
    public final String getPatternById(final String id) {
        SesameAdapterQueryExecutor sesameQueryExecutor =
                new SesameAdapterQueryExecutor();
        String patternResource =
                sesameQueryExecutor.queryPatternById(id);
        return patternResource;
    }
    
    /**
     * Definizione del metodo per inserire all'interno dell'ontologia presente
     * sul server Sesame i metadati relativi all'evento generato e contenuti
     * all'interno della cas ottenuta dal processo di annotation.
     * @param cas
     *            - cas da elaborare.
     */
    public final void indexEvent(final CAS cas) {
        SesameAdapterAgent sesameAdapterAgent = new SesameAdapterAgent();
        sesameAdapterAgent.indexEvent(cas);
    }

}
