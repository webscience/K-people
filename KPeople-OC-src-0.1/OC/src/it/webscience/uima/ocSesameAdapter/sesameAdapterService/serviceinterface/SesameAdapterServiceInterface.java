package it.webscience.uima.ocSesameAdapter.sesameAdapterService.serviceinterface;

import org.apache.uima.cas.CAS;

/**
 * @author filieri
 */
public interface SesameAdapterServiceInterface {

    /**
     * Definizione del metodo per interrogare l'ontologia presente sul server
     * Sesame al fine di individuare il processo associato ad una specifica
     * keyword.
     * @param keyword
     *            - keyword da utilizzare come indice per la query.
     * @return String - rappresenta l'URI del processo associato alla keyword.
     */
    String getProcessByKeyword(final String keyword);

    /**
     * Definizione del metodo per interrogare l'ontologia presente sul server
     * Sesame al fine di individuare il pattern associato ad uno specifico id.
     * @param id
     *            - id da utilizzare come indice per la query.
     * @return String - rappresenta l'URI del pattern associato all'id.
     */
    String getPatternById(final String id);

    /**
     * Definizione del metodo per inserire all'interno dell'ontologia presente
     * sul server Sesame i metadati relativi all'evento generato e contenuti
     * all'interno della cas ottenuta dal processo di annotation.
     * @param cas
     *            - cas da elaborare.
     */
    void indexEvent(final CAS cas);
}
