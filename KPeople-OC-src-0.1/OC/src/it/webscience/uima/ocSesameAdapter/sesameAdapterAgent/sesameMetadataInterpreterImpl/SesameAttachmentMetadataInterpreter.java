package it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl;

import it.webscience.uima.annotations.UserAnnotation;
import it.webscience.uima.annotations.eventData.AttachmentAnnotation;
import it.webscience.uima.ocControl.SesamePropertyKeys;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.SesameMetadataInterpreterInterface;
import it.webscience.uima.ocSesameAdapter.sesameAdapterConfigurationManager.SesameAdapterConfigurationManager;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.dom4j.Element;

/**
 * Classe per la generazione dei metadati da inserire nell'ontologia e relativi
 * agli allegati eventualmente associati all'evento.
 * @author Antonella Filieri
 */
public class SesameAttachmentMetadataInterpreter implements
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
    public SesameAttachmentMetadataInterpreter() {
        logger = Logger.getLogger(this.getClass().getName());
        
        configInstance = SesameAdapterConfigurationManager.getInstance();
    }

    /**
     * Implementazione del metodo interpreter specifico per l'interpretazione
     * delle annotations relative agli allegati eventualmente associati
     * all'evento.
     * @param cas cas da analizzare
     * @param annotationFS attachment annotation
     * @param root oggetto Element del dom4j
     * @param eventResource event resource
     * @return resource
     */
    public final String interpreter(
            final CAS cas,
            final AnnotationFS annotationFS,
            final Element root,
            final String eventResource) {
        if (logger.isDebugEnabled()) {
            logger.debug("interpreter - begin");
        }
        
        AttachmentAnnotation attachmentAnnotation =
                (AttachmentAnnotation) annotationFS;

        String attachmentHashcode = attachmentAnnotation.getHashcode();

//      user annotation associato all'attachment annotation
        UserAnnotation uaAttachment =
            getUserAnnotation(cas, attachmentAnnotation);

        String resource = null;

        Element individual =
                root.addElement(configInstance
                        .getProperty(SesamePropertyKeys.OWL_NAME_INDIVIDUAL));

        if (attachmentHashcode != null) {
            // individual.addAttribute("rdf:about",
            // "http://kpeople.webscience.it/ontologies/2010/12/kpexample.owl#"
            // + attachmentHashcode);

            String rdfAbout =
                    configInstance.getProperty(SesamePropertyKeys.RDF_ABOUT);
            String kpbase =
                    configInstance.getProperty(SesamePropertyKeys.KPBASE);

            individual.addAttribute(rdfAbout, kpbase + attachmentHashcode);

            String rdfType =
                    configInstance.getProperty(SesamePropertyKeys.RDF_TYPE);
            Element type = individual.addElement(rdfType);
            String rdfResource =
                    configInstance.getProperty(SesamePropertyKeys.RDF_RESOURCE);
            String kpbaseAttachment =
                    configInstance
                            .getProperty(SesamePropertyKeys.KPBASE_ATTACHMENT);
            type.addAttribute(rdfResource, kpbaseAttachment);

            String kpbaseAttachmentId =
                 configInstance
                     .getProperty(SesamePropertyKeys.KPBASE_ATTACHMENT_ID);
            Element id = individual.addElement(kpbaseAttachmentId);
            id.setText(attachmentHashcode);

            String kpbaseAttachmentEvent =
                    configInstance
                       .getProperty(SesamePropertyKeys.KPBASE_ATTACHMENT_EVENT);

            Element attachmentEvent =
                    individual.addElement(kpbaseAttachmentEvent);
            attachmentEvent.addAttribute(rdfResource, eventResource);

//          se l'attachment viene associato ad un utente del dominio,
//          aggiungo il riferimento all'xml
            if (uaAttachment != null && uaAttachment.getEmail() != null) {
                appendAttachmentCreator(individual, uaAttachment);
            }

            if (individual.attributeValue(rdfAbout) != null) {
                resource = individual.attributeValue(rdfAbout);
            }

        }
        
        
        if (logger.isDebugEnabled()) {
            logger.debug("interpreter - end");
        }

        return resource;
    }

    /**
     * inserisce nell'xml le informazioni sull'autore del documento.
     * @param individual elemento owl:NamedIndividual dell'xml
     * @param uaAttachment user annotation relativa all'attachment
     */
    private void appendAttachmentCreator(
            final Element individual,
            final UserAnnotation uaAttachment) {
        if (logger.isDebugEnabled()) {
            logger.debug("appebdattachmentcreator - begin");
        }
        
        String rdfResource =
            configInstance.getProperty(SesamePropertyKeys.RDF_RESOURCE);

        String kpbaseAttachmentCreator =
            configInstance
                    .getProperty(SesamePropertyKeys.KPBASE_ATTACHMENT_CREATOR);

        String kpbase = configInstance.getProperty(SesamePropertyKeys.KPBASE);

            individual
                .addElement(kpbaseAttachmentCreator)
                .addAttribute(rdfResource, kpbase + uaAttachment.getEmail());
        
        if (logger.isDebugEnabled()) {
            logger.debug("appebdattachmentcreator - end");
        }
    }

    /**
     * Recupera lo userAnnotation associato all'attachment.
     * @param cas cas in analisi
     * @param attachmentAnnotation attachment per la quale si vuole
     * recuperare lo user annotation
     * @return user annotation associato all'attachment annotation desiderato
     */
    private UserAnnotation getUserAnnotation(
            final CAS cas,
            final AttachmentAnnotation attachmentAnnotation) {
        if (logger.isDebugEnabled()) {
            logger.debug("getUserAnnotation - begin");
        }

        UserAnnotation uaReturn = null;
        int attachmentBegin = attachmentAnnotation.getBegin();
        int attachmentEnd = attachmentAnnotation.getEnd();

        AnnotationIndex<AnnotationFS> annotationIndex =
            cas.getAnnotationIndex();

        for (AnnotationFS annotationFS : annotationIndex) {
            if (annotationFS instanceof UserAnnotation) {
                UserAnnotation ua = (UserAnnotation) annotationFS;

//              l'user annotation da cercare è quello avente gli stessi indici
//              di begin ed end
                if (attachmentBegin == ua.getBegin()
                        && attachmentEnd == ua.getEnd()) {
                    uaReturn = ua;

                    break;
                }
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getUserAnnotation - end");
        }
        
        return uaReturn;
    }

    public String
            interpreter(final AnnotationFS annotation, final Element root) {
        return null;
    }

    public String interpreter(final CAS cas, final Element root) {
        return null;
    }

}
