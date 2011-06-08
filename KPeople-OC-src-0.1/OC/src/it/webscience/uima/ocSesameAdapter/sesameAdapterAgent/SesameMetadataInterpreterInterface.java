package it.webscience.uima.ocSesameAdapter.sesameAdapterAgent;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationFS;

import org.dom4j.Element;

/**
 * @author filieri
 */
public interface SesameMetadataInterpreterInterface {

    /**
     * Metodo per l'interpretazione degli elementi foglia dell'ontologia.
     * @param annotation
     *            - annotation da processare.
     * @param root
     *            - elemento root del documento rdf.
     * @return Un oggetto di tipo String che rappresenta l'URI della risorsa
     *         caricata nell'ontologia.
     */
    String interpreter(final AnnotationFS annotation, final Element root);

    /**
     * Metodo per l'interpretazione degli elementi complessi dell'ontologia.
     * @param cas
     *            - cas contenente tutte le annotations.
     * @param root
     *            - elemento root del documento rdf.
     * @return Un oggetto di tipo String che rappresenta l'URI della risorsa
     *         caricata nell'ontologia.
     */
    String interpreter(final CAS cas, Element root);
}
