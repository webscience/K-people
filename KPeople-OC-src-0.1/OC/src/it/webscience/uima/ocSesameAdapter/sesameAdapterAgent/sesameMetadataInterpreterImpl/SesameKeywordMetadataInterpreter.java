package it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl;

import it.webscience.uima.annotations.UserAnnotation;
import it.webscience.uima.annotations.eventData.PropertyAnnotation;
import it.webscience.uima.ocControl.SesamePropertyKeys;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.SesameMetadataInterpreterInterface;
import it.webscience.uima.ocSesameAdapter.sesameAdapterConfigurationManager.SesameAdapterConfigurationManager;
import it.webscience.uima.util.MultiValueSplit;

import org.apache.activemq.broker.region.policy.IndividualDeadLetterStrategy;
import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationFS;
import org.dom4j.Element;

/**
 * Classe per la generazione dei metadati da inserire nell'ontologia e relativi
 * alle keyword associate all'evento.
 * @author Antonella Filieri
 */
public class SesameKeywordMetadataInterpreter implements
        SesameMetadataInterpreterInterface {

    /** logger. */
    private Logger logger;

    /**
     * istanza della classe singleton SesameAdapterConfigurationManager per la
     * lettura delle properties.
     */
    private static SesameAdapterConfigurationManager configInstance;

    /**
     * Costruttore di default che istanzia l'oggetto singleton per la lettura
     * dei parametri di configurazione.
     */
    public SesameKeywordMetadataInterpreter() {
        logger = Logger.getLogger(this.getClass().getName());

        configInstance = SesameAdapterConfigurationManager.getInstance();
    }

    /**
     * Implementazione del metodo interpreter specifico per l'interpretazione
     * delle annotations relative alle keywords associate all'evento.
     */
    public String
            interpreter(final AnnotationFS annotation, final Element root) {
        if (logger.isDebugEnabled()) {
            logger.debug("interpreter - begin");
        }

        PropertyAnnotation userAnnotation = (PropertyAnnotation) annotation;
        String resource = null;
        Element individual = null;
        String key = userAnnotation.getKey();
        String value = userAnnotation.getValue();
        
        //gestisce il caso di property multivalore.
        MultiValueSplit valueSplit = new MultiValueSplit();
        String[] multiValue = valueSplit.getPropertyValues(value);

        for (int i = 0; i < multiValue.length; i++) {

            String keyword = key + "-" + multiValue[i];

            individual =
                    root.addElement(configInstance
                            .getProperty(SesamePropertyKeys.OWL_NAME_INDIVIDUAL));
            
            String rdfAbout =
                    configInstance.getProperty(SesamePropertyKeys.RDF_ABOUT);
            String rdfAboutValue =
                    configInstance.getProperty(SesamePropertyKeys.KPBASE);
            individual.addAttribute(rdfAbout, rdfAboutValue + keyword);
            String rdfType =
                    configInstance.getProperty(SesamePropertyKeys.RDF_TYPE);
            Element type = individual.addElement(rdfType);
            String rdfResource =
                    configInstance.getProperty(SesamePropertyKeys.RDF_RESOURCE);

            String eventKeyword =
                    configInstance
                            .getProperty(SesamePropertyKeys.KPBASE_KEYWORD);
            type.addAttribute(rdfResource, eventKeyword);
            String eventKeywordId =
                    configInstance
                            .getProperty(SesamePropertyKeys.KPBASE_KEYWORD_ID);
            Element dnElement = individual.addElement(eventKeywordId);
            dnElement.setText(keyword);

        }
        if (logger.isDebugEnabled()) {
            logger.debug("interpreter - end");
        }

        return resource;

    }

    @Override
    public String interpreter(final CAS cas, final Element root) {
        return null;
    }

}
