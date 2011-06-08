package it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl;

import it.webscience.uima.annotations.action.SystemAnnotation;

import it.webscience.uima.ocControl.SesamePropertyKeys;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.SesameMetadataInterpreterInterface;
import it.webscience.uima.ocSesameAdapter.sesameAdapterConfigurationManager.SesameAdapterConfigurationManager;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationFS;

import org.dom4j.Element;

/**
 * Classe per la generazione dei metadati da inserire nell'ontologia e relativi
 * al sistema verticale interessato dall'evento.
 * @author Antonella Filieri
 */
public class SesameSystemMetadataInterpreter implements
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
    public SesameSystemMetadataInterpreter() {
        logger = Logger.getLogger(this.getClass().getName());
        
        configInstance = SesameAdapterConfigurationManager.getInstance();
    }

    /**
     * Implementazione del metodo interpreter specifico per l'interpretazione
     * delle annotations relative al sistema verticale interessato dall'evento.
     */
    public String
            interpreter(final AnnotationFS annotation, final Element root) {
        if (logger.isDebugEnabled()) {
            logger.debug("interpreter - begin");
        }
        
        SystemAnnotation systemAnnotation = (SystemAnnotation) annotation;

        String systemId = systemAnnotation.getSystemId();
        String systemType = systemAnnotation.getSystemType();

        if (systemId != null) {
            Element individual =
                    root.addElement(configInstance
                            .getProperty(SesamePropertyKeys.OWL_NAME_INDIVIDUAL));
            // individual.addAttribute("rdf:about",
            // "http://kpeople.webscience.it/ontologies/2010/12/kpexample.owl#"
            // + systemId);

            String rdfAbout =
                    configInstance.getProperty(SesamePropertyKeys.RDF_ABOUT);
            String rdfAboutValue =
                    configInstance.getProperty(SesamePropertyKeys.KPBASE);
            individual.addAttribute(rdfAbout, rdfAboutValue + systemId);

            String rdfType =
                    configInstance.getProperty(SesamePropertyKeys.RDF_TYPE);

            Element type = individual.addElement(rdfType);

            String rdfResource =
                    configInstance.getProperty(SesamePropertyKeys.RDF_RESOURCE);
            String rdfResourceValue =
                    configInstance
                            .getProperty(SesamePropertyKeys.KPBASE_SYSTEM);
            type.addAttribute(rdfResource, rdfResourceValue);

            if (systemId != null) {
                String kpbaseSystemId =
                        configInstance
                                .getProperty(SesamePropertyKeys.KPBASE_SYSTEM_ID);
                Element systemID = individual.addElement(kpbaseSystemId);
                systemID.setText(systemId);
            }
        }
        
        if (logger.isDebugEnabled()) {
            logger.debug("interpreter - end");
        }
        
        return systemType;

    }

    @Override
    public String interpreter(final CAS cas, final Element root) {
        return null;
    }

}
