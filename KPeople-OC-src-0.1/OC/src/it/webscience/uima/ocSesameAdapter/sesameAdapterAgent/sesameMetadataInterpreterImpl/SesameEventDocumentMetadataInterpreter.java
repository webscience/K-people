package it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl;

import it.webscience.uima.annotations.UserAnnotation;
import it.webscience.uima.annotations.action.EventIdAnnotation;
import it.webscience.uima.annotations.action.SystemAnnotation;
import it.webscience.uima.annotations.eventData.AttachmentAnnotation;
import it.webscience.uima.annotations.eventData.DateCreationDateAnnotation;
import it.webscience.uima.annotations.eventData.PropertyAnnotation;
import it.webscience.uima.annotations.eventData.UserAuthorAnnotation;
import it.webscience.uima.annotations.eventData.UserReceiverCcAnnotation;
import it.webscience.uima.annotations.eventData.UserReceiverToAnnotation;
import it.webscience.uima.ocControl.SesamePropertyKeys;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.SesameMetadataInterpreterInterface;
import it.webscience.uima.ocSesameAdapter.sesameAdapterConfigurationManager.SesameAdapterConfigurationManager;
import it.webscience.uima.ocSesameAdapter.sesameAdapterService.serviceimpl.SesameAdapterServiceImpl;
import it.webscience.uima.ocSesameAdapter.sesameAdapterService.serviceinterface.SesameAdapterServiceInterface;
import it.webscience.uima.util.MultiValueSplit;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.dom4j.Element;

/**
 * Classe per la generazione dei metadati da inserire nell'ontologia e relativi
 * ad un evento di tipo communication.
 * @author Antonella Filieri
 */
public class SesameEventDocumentMetadataInterpreter implements
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
    public SesameEventDocumentMetadataInterpreter() {
        logger = Logger.getLogger(this.getClass().getName());

        configInstance = SesameAdapterConfigurationManager.getInstance();
    }

    /**
     * Implementazione del metodo interpreter per l'interpretazione delle
     * annotations relative ad un evento di tipo communication.
     */
    public final String interpreter(final CAS cas, final Element root) {
        if (logger.isDebugEnabled()) {
            logger.debug("interpreter - begin");
        }

        Element individual =
                root.addElement(configInstance
                        .getProperty(SesamePropertyKeys.OWL_NAME_INDIVIDUAL));

        String eventId = getEventID(cas);
        String eventResource = null;

        if (eventId != null) {
            String kpbase =
                    configInstance.getProperty(SesamePropertyKeys.KPBASE);
            String eventUri = kpbase + eventId;

            // String eventUri =
            // "http://kpeople.webscience.it/ontologies/2010/12/kpexample.owl#"
            // + eventId;
            String rdfAbout =
                    configInstance.getProperty(SesamePropertyKeys.RDF_ABOUT);
            individual.addAttribute(rdfAbout, eventUri);

            String rdfType =
                    configInstance.getProperty(SesamePropertyKeys.RDF_TYPE);
            Element type = individual.addElement(rdfType);
            String rdfResource =
                    configInstance.getProperty(SesamePropertyKeys.RDF_RESOURCE);
            String rdfResourceValue =
                    configInstance
                            .getProperty(SesamePropertyKeys.KPBASE_DOCUMENT);

            type.addAttribute(rdfResource, rdfResourceValue);

            String kpbaseEventId =
                    configInstance
                            .getProperty(SesamePropertyKeys.KPBASE_EVENTID);

            Element id = individual.addElement(kpbaseEventId);
            id.setText(eventId);

            if (individual.attributeValue(rdfAbout) != null) {
                eventResource = individual.attributeValue(rdfAbout);
            }

            // AnnotationIndex<AnnotationFS> index = getAnnotations(cas,
            // AttachmentAnnotation.class.getCanonicalName());
            AnnotationIndex<AnnotationFS> annotationIndex =
                    cas.getAnnotationIndex();

            for (AnnotationFS annotationFS : annotationIndex) {

                if (annotationFS instanceof DateCreationDateAnnotation) {
                    String creationDate =
                            ((DateCreationDateAnnotation) annotationFS)
                                    .getValue();

                    String eventInsertDate =
                            configInstance
                                    .getProperty(SesamePropertyKeys.KPBASE_EVENT_INSERT_DATA);

                    Element date = individual.addElement(eventInsertDate);
                    date.setText(creationDate);
                }

                if (annotationFS instanceof PropertyAnnotation) {
                    String key = ((PropertyAnnotation) annotationFS).getKey();
                    String value =
                            ((PropertyAnnotation) annotationFS).getValue();

                    MultiValueSplit valueSplit = new MultiValueSplit();
                    String[] multiValue = valueSplit.getPropertyValues(value);

                    for (int i = 0; i < multiValue.length; i++) {
                        String keyword = key + "-" + multiValue[i];

                        String eventKeyword =
                                configInstance
                                        .getProperty(SesamePropertyKeys.KPBASE_EVENT_KEYWORD);
                        Element keywordElement =
                                individual.addElement(eventKeyword);
                        // keywordElement.addAttribute("rdf:resource",
                        // "http://kpeople.webscience.it/ontologies/2010/12/kpexample.owl#"
                        // + keyword);

                        keywordElement.addAttribute(rdfResource, kpbase
                                + keyword);

                        SesameAdapterServiceImpl sesameService =
                                new SesameAdapterServiceImpl();
                        String processUri =
                                sesameService.getProcessByKeyword(keyword);
                        if (processUri != null) {
                            String eventProcess =
                                    configInstance
                                            .getProperty(SesamePropertyKeys.KPBASE_EVENT_PROCESS);
                            Element eventProcessElement =
                                    individual.addElement(eventProcess);
                            eventProcessElement.addAttribute(rdfResource,
                                    processUri);

                            addProcessElement(eventUri, processUri, root);

                        }
                    }

                }

                if (annotationFS instanceof SystemAnnotation) {
                    String systemId =
                            ((SystemAnnotation) annotationFS).getSystemId();

                    String eventFromSystem =
                            configInstance
                                    .getProperty(SesamePropertyKeys.KPBASE_EVENT_FROMSYSTEM);
                    Element system = individual.addElement(eventFromSystem);
                    // system.addAttribute("rdf:resource",
                    // "http://kpeople.webscience.it/ontologies/2010/12/kpexample.owl#"
                    // + systemId);

                    system.addAttribute(rdfResource, kpbase + systemId);
                }

                if (annotationFS instanceof AttachmentAnnotation) {
                    AttachmentAnnotation attachmentAnnotation =
                            (AttachmentAnnotation) annotationFS;

                    String attachmentName =
                            attachmentAnnotation.getAttachmentName();
                    String attachmentType =
                            attachmentAnnotation.getAttachmentType();

                    String author = attachmentAnnotation.getAuthor();

                    if (author != null) {
                        String documentCreator =
                                configInstance
                                        .getProperty(SesamePropertyKeys.KPBASE_DOCUMENT_CREATOR);
                        Element documentCreatorElement =
                                individual.addElement(documentCreator);
                        documentCreatorElement
                                .addAttribute(rdfResource, author);
                    }

                    SesameAttachmentMetadataInterpreter attachmentInterpreter =
                            new SesameAttachmentMetadataInterpreter();
                    String attachmentResource =
                            attachmentInterpreter.interpreter(cas,
                                    annotationFS, root, eventResource);

                    String eventAttachment =
                            configInstance
                                    .getProperty(SesamePropertyKeys.KPBASE_EVENT_ATTACHMENT);
                    Element attachment = individual.addElement(eventAttachment);
                    attachment.addAttribute(rdfResource, attachmentResource);
                }

                if (annotationFS instanceof UserAnnotation) {
                    String email = ((UserAnnotation) annotationFS).getEmail();

                    if (annotationFS instanceof UserAuthorAnnotation) {

                        String eventCreator =
                                configInstance
                                        .getProperty(SesamePropertyKeys.KPBASE_EVENT_CREATOR);
                        Element eventAuthor =
                                individual.addElement(eventCreator);
                        // eventAuthor.addAttribute("rdf:resource",
                        // "http://kpeople.webscience.it/ontologies/2010/12/kpexample.owl#"
                        // + email);

                        eventAuthor.addAttribute(rdfResource, kpbase + email);

                    }

                }

            }

        }

        if (logger.isDebugEnabled()) {
            logger.debug("interpreter - end");
        }

        return null;

    }

    private void addProcessElement(String eventId, String processResource,
            Element root) {
        if (logger.isDebugEnabled()) {
            logger.debug("addProcessElement - begin");
        }

        Element individual =
                root.addElement(configInstance
                        .getProperty(SesamePropertyKeys.OWL_NAME_INDIVIDUAL));

        String rdfAbout =
                configInstance.getProperty(SesamePropertyKeys.RDF_ABOUT);
        individual.addAttribute(rdfAbout, processResource);
        String rdfType =
                configInstance.getProperty(SesamePropertyKeys.RDF_TYPE);
        Element type = individual.addElement(rdfType);
        String rdfResource =
                configInstance.getProperty(SesamePropertyKeys.RDF_RESOURCE);

        String kpbaseProcess =
                configInstance.getProperty(SesamePropertyKeys.KPBASE_PROCESS);
        type.addAttribute(rdfResource, kpbaseProcess);

        String kpbaseProcessEvent =
                configInstance
                        .getProperty(SesamePropertyKeys.KPBASE_PROCESS_EVENT);
        Element eventProcess = individual.addElement(kpbaseProcessEvent);
        eventProcess.addAttribute(rdfResource, eventId);

        if (logger.isDebugEnabled()) {
            logger.debug("addProcessElement - end");
        }
    }

    /**
     * Metodo per ricavere l'id dell'evento analizzando le annotation generate.
     * @param cas
     *            - cas che è stata elaborata.
     * @return oggetto di tipo String che rappresenta l'id dell'evento.
     */
    private String getEventID(final CAS cas) {
        if (logger.isDebugEnabled()) {
            logger.debug("getEventID - begin");
        }

        String eventId = null;
        Type annotationType =
                cas.getTypeSystem().getType(
                        EventIdAnnotation.class.getCanonicalName());
        AnnotationIndex<AnnotationFS> annotationIndex =
                cas.getAnnotationIndex(annotationType);
        for (AnnotationFS annotationFS : annotationIndex) {
            EventIdAnnotation eventAnnotation =
                    (EventIdAnnotation) annotationFS;
            eventId = eventAnnotation.getValue();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getEventID - end");
        }

        return eventId;
    }

    @Override
    public final String interpreter(final AnnotationFS annotation,
            final Element root) {
        return null;
    }
}
