package it.webscience.uima.annotators;

import it.webscience.uima.annotations.UserAnnotation;
import it.webscience.uima.annotations.eventData.AttachmentAnnotation;
import it.webscience.uima.ocControl.ocStorageUtility.OcAlfrescoServerUtility;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.xml.sax.ContentHandler;

/**
 * Uses TIKA to convert original markup into UIMA annotations.
 */
public class TikaAnnotator extends JCasAnnotator_ImplBase {

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * @see AnalysisComponent#initialize(UimaContext)
     * @param aContext context
     * @throws ResourceInitializationException exception
     */
    public final void initialize(final UimaContext aContext)
        throws ResourceInitializationException {
        if (aContext != null) {
            super.initialize(aContext);
        }
    }

    /**
     * Recupera i documenti salvati su alfresco e li processa attraverso Tika.
     * @param cas cas da analizzare
     * @throws AnalysisEngineProcessException errore durante l'analisi della cas
     */
    public final void process(final JCas cas)
        throws AnalysisEngineProcessException {

    //      recupero le annotations
            AnnotationIndex<Annotation> attAnn =
                cas.getAnnotationIndex(AttachmentAnnotation.type);
            FSIterator<Annotation> it = attAnn.iterator();

            // per ogni annotation, recupero da Alfresco il file associato
            while (it.hasNext()) {
                try {
                    AttachmentAnnotation annotation =
                        (AttachmentAnnotation) it.next();

//                  viene eseguito l'encoding dell'url
                    int index = annotation.getUrlAttachment().lastIndexOf("/");
                    String encodedUrl =
                        annotation.getUrlAttachment().substring(0, index + 1)
                        + URLEncoder.encode(
                            annotation.getUrlAttachment().substring(index + 1),
                            "UTF-8");

                    OcAlfrescoServerUtility alfrescoUtil = new OcAlfrescoServerUtility();
                    File file =
                        alfrescoUtil.getEventAttacchment(encodedUrl);
                    FileInputStream is = new FileInputStream(file);

                    ContentHandler contenthandler = new BodyContentHandler();
                    Metadata metadata = new Metadata();
                    Parser parser = new AutoDetectParser();
                    ParseContext context = new ParseContext();

                    parser.parse(is, contenthandler, metadata, context);
                    is.close();

    //              aggiorno l'annotation con i dati estratti da tika
                    annotation.setCreationDate(
                            metadata.get(Metadata.CREATION_DATE));
                    annotation.setAuthor(
                            metadata.get(Metadata.AUTHOR));

//                  se presente l'autore del documento, creo una user annotation
//                  begin ed end corrispondono con quelli della
//                  attachment annotation
                    if (annotation.getAuthor() != null) {
                        creaUserAnnotation(cas, annotation);
                    }

                    if (logger.isDebugEnabled()) {
                        logger.debug(
                                "Estrazione delle info per il file "
                                + encodedUrl);
                        logger.debug(
                                "Data di creazione: "
                                + annotation.getCreationDate());
                        logger.debug("Autore: "
                                + annotation.getAuthor());
                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }
           }
     }

    /**
     * crea una user annotation per recuperare l'utente ldap associato.
     * @param cas cas da analizzare
     * @param attachmentAnnotation attachment annotation
     */
    private void creaUserAnnotation(
            final JCas cas,
            final AttachmentAnnotation attachmentAnnotation) {
        UserAnnotation ua = new UserAnnotation(cas);

        ua.setUsername(attachmentAnnotation.getAuthor());
        ua.setBegin(attachmentAnnotation.getBegin());
        ua.setEnd(attachmentAnnotation.getEnd());

        ua.addToIndexes();
    }
}
