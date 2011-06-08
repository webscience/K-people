package it.webscience.uima.ocSesameAdapter.sesameAdapterAgent;

import it.webscience.uima.annotations.UserAnnotation;
import it.webscience.uima.annotations.action.ActionTypeAnnotation;
import it.webscience.uima.annotations.action.SystemAnnotation;
import it.webscience.uima.annotations.eventData.PropertyAnnotation;
import it.webscience.uima.ocControl.SesamePropertyKeys;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl.SesameEventCommunicationMetadataInterpreter;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl.SesameEventDocumentMetadataInterpreter;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl.SesameKeywordMetadataInterpreter;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl.SesamePatternDocumentMetadataInterpreter;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl.SesameSystemMetadataInterpreter;
import it.webscience.uima.ocSesameAdapter.sesameAdapterAgent.sesameMetadataInterpreterImpl.SesameUserMetadataInterpreter;
import it.webscience.uima.ocSesameAdapter.sesameAdapterConfigurationManager.SesameAdapterConfigurationManager;

import java.util.Vector;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.dtd.InternalEntityDecl;
import org.dom4j.tree.DefaultDocumentType;

/**
 * @author filieri
 */
public class SesameMetadataInterpreterFactory {

    /** istanza della classe Document relativa al file rdf da popolare. */
    private static Document rdfDocument;

    /** elemento root del file rdf. */
    private static Element rdfRoot;

    /** id dell'evento da inserire su sesame. */
    private static String eventId;

    /**
     * istanza della classe singleton SesameAdapterConfigurationManager per la
     * lettura delle properties.
     */
    private static SesameAdapterConfigurationManager configInstance;

    /** Costruttore della classe. */
    public SesameMetadataInterpreterFactory() {
        configInstance = SesameAdapterConfigurationManager.getInstance();
        rdfDocument = DocumentHelper.createDocument();
        createSchema(rdfDocument);
        rdfRoot = rdfDocument.getRootElement();
    }

    /**
     * @param cas
     *            - rappresenta l'oggetto cas che è stato elaborato.
     * @return un oggetto di tipo Document contenente i dati rdf da inviare al
     *         server sesame.
     */
    public final Document interpreter(final CAS cas) {

        AnnotationIndex<AnnotationFS> annotationIndex =
                cas.getAnnotationIndex();

        for (AnnotationFS annotationFS : annotationIndex) {
            if (annotationFS instanceof SystemAnnotation) {
                SesameSystemMetadataInterpreter systemInterpreter =
                        new SesameSystemMetadataInterpreter();
                systemInterpreter.interpreter(annotationFS, rdfRoot);
            }

            if (annotationFS instanceof UserAnnotation) {
                SesameUserMetadataInterpreter userInterpreter =
                        new SesameUserMetadataInterpreter();
                String userResource =
                        userInterpreter.interpreter(annotationFS, rdfRoot);
            }

            if (annotationFS instanceof PropertyAnnotation) {
                SesameKeywordMetadataInterpreter keywordInterpreter =
                        new SesameKeywordMetadataInterpreter();
                keywordInterpreter.interpreter(annotationFS, rdfRoot);

            }

            /* Verifico la tipologia di evento: Communication, Document ecc. */
            if (annotationFS instanceof ActionTypeAnnotation) {
                ActionTypeAnnotation actionAnnotation =
                        (ActionTypeAnnotation) annotationFS;

                String actionType = actionAnnotation.getValue();
                actionType = actionType.toLowerCase();

                String eventTypeCommunication = configInstance.getProperty(
                        SesamePropertyKeys.SESAME_EVENT_TYPE_COMMUNICATION);
                eventTypeCommunication = eventTypeCommunication.toLowerCase();

                String eventTypeDocument = configInstance.getProperty(
                        SesamePropertyKeys.SESAME_EVENT_TYPE_DOCUMENT);
                eventTypeDocument = eventTypeDocument.toLowerCase();

                String eventTypePatternDocument = configInstance.getProperty(
                        SesamePropertyKeys.SESAME_EVENT_TYPE_PATTERN_DOCUMENT);
                eventTypePatternDocument =
                    eventTypePatternDocument.toLowerCase();

                String processPatternRichiestaContributo =
                    configInstance.getProperty(
                       SesamePropertyKeys.PROCESS_PATTERN_RICHIESTA_CONTRIBUTO);
                processPatternRichiestaContributo =
                    processPatternRichiestaContributo.toLowerCase();

                String documentDownload =
                    configInstance.getProperty(
                            SesamePropertyKeys.DOCUMENT_DOWNLOAD);
                documentDownload = documentDownload.toLowerCase();

                if (actionType.equalsIgnoreCase(eventTypeCommunication)
                    || actionType.contains(processPatternRichiestaContributo)
                    || actionType.equalsIgnoreCase(documentDownload)) {
                    /*Elaboro l'evento di tipo communication */
                    SesameEventCommunicationMetadataInterpreter
                        eventInterpreter =
                            new SesameEventCommunicationMetadataInterpreter();
                    eventInterpreter.interpreter(cas, rdfRoot);
                } else if (
                        actionType.equalsIgnoreCase(eventTypePatternDocument)) {
                    /*Elaboro l'evento di tipo pattern document */
                    SesamePatternDocumentMetadataInterpreter eventInterpreter =
                            new SesamePatternDocumentMetadataInterpreter();
                    eventInterpreter.interpreter(cas, rdfRoot);
                } else if (actionType.contains(eventTypeDocument)) {
                    /*Elaboro l'evento di tipo document */
                    SesameEventDocumentMetadataInterpreter eventInterpreter =
                            new SesameEventDocumentMetadataInterpreter();
                    eventInterpreter.interpreter(cas, rdfRoot);
                }


            }
        }

        return rdfDocument;
    }

    /**
     * Metodo per la creazione dello scheletro del file rdf.
     * @param rdf2
     *            - param
     */
    private void createSchema(final Document rdf2) {

        String terms = SesamePropertyKeys.TERMS;
        String termsValue = configInstance.getProperty(terms);

        String owl = SesamePropertyKeys.OWL;
        String owlValue = configInstance.getProperty(owl);

        String xsd = SesamePropertyKeys.XSD;
        String xsdValue = configInstance.getProperty(xsd);

        String rdfs = SesamePropertyKeys.RDFS;
        String rdfsValue = configInstance.getProperty(rdfs);

        String rdf = SesamePropertyKeys.RDF;
        String rdfValue = configInstance.getProperty(rdf);

        String kpbase = SesamePropertyKeys.KPBASE;
        String kpbaseValue = configInstance.getProperty(kpbase);

        String rdfDoctype = SesamePropertyKeys.RDF_DOCTYPE;
        String rdfDoctypeValue = configInstance.getProperty(rdfDoctype);

        Vector<InternalEntityDecl> entityDeclList =
                new Vector<InternalEntityDecl>();
        entityDeclList.add(new InternalEntityDecl(terms, termsValue));

        entityDeclList.add(new InternalEntityDecl(owl, owlValue));

        entityDeclList.add(new InternalEntityDecl(xsd, xsdValue));

        entityDeclList.add(new InternalEntityDecl(rdfs, rdfsValue));

        entityDeclList.add(new InternalEntityDecl(rdf, rdfValue));

        entityDeclList.add(new InternalEntityDecl(kpbase, kpbaseValue));

        DefaultDocumentType docType = new DefaultDocumentType();
        docType.setInternalDeclarations(entityDeclList);
        docType.setName(rdfDoctypeValue);
        rdfDocument.setDocType(docType);

        rdfRoot = rdfDocument.addElement(rdfDoctypeValue);
        Namespace namespace = new Namespace("", kpbaseValue);
        rdfRoot.addAttribute("xml:base", kpbaseValue);
        Namespace namespacerdfs = new Namespace(rdfs, rdfsValue);
        Namespace namespacekpbase = new Namespace(kpbase, kpbaseValue);
        Namespace namespaceterms = new Namespace(terms, termsValue);
        Namespace namespaceowl = new Namespace(owl, owlValue);
        Namespace namespacexsd = new Namespace(xsd, xsdValue);
        Namespace namespacerdf = new Namespace(rdf, rdfValue);

        // add the created namespaces to the document</span>
        rdfDocument.getRootElement().add(namespace);

        rdfDocument.getRootElement().add(namespacerdfs);
        rdfDocument.getRootElement().add(namespacekpbase);
        rdfDocument.getRootElement().add(namespaceterms);
        rdfDocument.getRootElement().add(namespaceowl);
        rdfDocument.getRootElement().add(namespacexsd);
        rdfDocument.getRootElement().add(namespacerdf);

    }

}
