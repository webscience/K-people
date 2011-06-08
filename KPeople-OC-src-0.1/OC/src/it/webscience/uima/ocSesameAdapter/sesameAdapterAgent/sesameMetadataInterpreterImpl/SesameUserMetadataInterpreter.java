package it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl;

import it.webscience.uima.annotations.UserAnnotation;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.SesameMetadataInterpreterInterface;
import it.webscience.uima.ocSesameAdapter.sesameAdapterConfigurationManager.SesameAdapterConfigurationManager;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationFS;
import org.dom4j.Element;

/**
 * Classe per la generazione dei metadati da inserire nell'ontologia e relativi
 * all'utente che ha generato l'evento.
 * @author Antonella Filieri
 */
public class SesameUserMetadataInterpreter implements
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
    public SesameUserMetadataInterpreter() {
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
        
        UserAnnotation userAnnotation = (UserAnnotation) annotation;
        String resource = null;
        Element individual = null;
        String dn = userAnnotation.getDn();
        if (dn != null) {
            String username = userAnnotation.getUsername();
            String email = userAnnotation.getEmail();

            individual = root.addElement("owl:NamedIndividual");
            // individual.addAttribute("rdf:about",
            // "http://kpeople.webscience.it/ontologies/2010/12/kpexample.owl#"
            // + email);
            individual.addAttribute("rdf:about",
                    "http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#"
                            + email);

            Element type = individual.addElement("rdf:type");

            type.addAttribute("rdf:resource", "&kpbase;User");
            Element dnElement = individual.addElement("kpbase:dn");
            dnElement.setText(dn);
            Element account = individual.addElement("kpbase:account");
            account.setText(email);
            if (individual.attributeValue("rdf:about") != null) {
                resource = individual.attributeValue("rdf:about");
            }
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
