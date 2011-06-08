package it.webscience.uima;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.collection.CasConsumer_ImplBase;

/**
 * CAS consumer che scrive su STD out le annotations.
 */
public class OntologyCasConsumer extends CasConsumer_ImplBase {

    /** Processes the CAS.
     * @param cas a CAS which has been populated by the TAEs
     */
    public final void processCas(final CAS cas) {
        System.out.println("Cas Consumer: processCas");

        AnnotationIndex<AnnotationFS> annotationIndex = cas
                .getAnnotationIndex();
        for (AnnotationFS annotationFS : annotationIndex) {
            System.out.println(annotationFS.toString());
        }
    }
}
