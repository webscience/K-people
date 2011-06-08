package it.webscience.uima.annotators;

import java.io.PrintWriter;
import java.io.StringWriter;

import it.webscience.uima.CasErrorManager;
import it.webscience.uima.annotations.action.ActionReferenceAnnotation;
import it.webscience.uima.annotations.action.ActionTypeAnnotation;
import it.webscience.uima.annotations.action.EventIdAnnotation;
import it.webscience.uima.annotations.action.SystemAnnotation;
import it.webscience.uima.util.Split;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

/** Analizza il nodo <action> ed applica le annotations relative.
 */
public class ActionAnnotator extends JCasAnnotator_ImplBase {

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

    /** Analizza il documento della CAS ed applica le annotations.
     * @param cas CAS da elaborare
     * @throws AnalysisEngineProcessException errore nell'analisi del documento
     * @see JCasAnnotator_ImplBase#process(JCas)
     */
    public final void process(final JCas cas)
            throws AnalysisEngineProcessException {
        logger.debug("Start process");

        try {
            addEventIdAnnotation(cas);
            addSystemAnnotation(cas);
            addActionTypeAnnotation(cas);
            addActionReferenceAnnotation(cas);
        } catch (Exception e) {
            logger.fatal("CAS non elaborata correttamente: ");
            logger.fatal("Salvataggio della cas "
                    + cas.toString()
                    + " su file");

            StringWriter sWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(sWriter));
            logger.fatal(sWriter.getBuffer().toString());

            CasErrorManager cem = new CasErrorManager();
            cem.storeCasInFile(cas);

            throw new AnalysisEngineProcessException();
        }
    }

    /** aggiunge l'annotation SystemAnnotation alla CAS.
    *
    * @param cas CAS da elaborare
    */
   private void addSystemAnnotation(final JCas cas) {
       Split system = new Split();
       system.setDocumentText(cas.getDocumentText());
       system.add("<system>", 1);
       system.add("</system>", 0);
       system.process();

       Split systemId = new Split();
       systemId.setDocumentText(cas.getDocumentText());
       systemId.add("<system-id>", 1);
       systemId.add("</system-id>", 0);
       systemId.process();

       Split systemType = new Split();
       systemType.setDocumentText(cas.getDocumentText());
       systemType.add("<system-type>", 1);
       systemType.add("</system-type>", 0);
       systemType.process();

       SystemAnnotation ann = new SystemAnnotation(cas);
       ann.setBegin(system.getBegin());
       ann.setEnd(system.getEnd());
       ann.setSystemId(systemId.getValue());
       ann.setSystemType(systemType.getValue());
       ann.addToIndexes();
   }

    /** aggiunge l'annotation ActionReferenceAnnotation alla CAS.
    *
    * @param cas CAS da elaborare
    */
   private void addActionReferenceAnnotation(final JCas cas) {
       Split actionReference = new Split();
       actionReference.setDocumentText(cas.getDocumentText());
       actionReference.add("<action-reference>", 1);
       actionReference.add("</action-reference>", 0);
       actionReference.process();

       String value = actionReference.getValue();

       /**
        * nel caso di system-type = "SHAREPOINT" vale la seguente regola
        */
       String systemType = getSystemType(cas);
       if (systemType.equals("SHAREPOINT")) {
           String[] split = value.split("@");
           value = split[0] + split[2];
       }

       ActionReferenceAnnotation ann = new ActionReferenceAnnotation(cas);
       ann.setBegin(actionReference.getBegin());
       ann.setEnd(actionReference.getEnd());
       ann.setValue(value);
       ann.addToIndexes();
   }

   /** ritorna il system-type della cas.
    * @param cas CAS da elaborare
    * @return system-type
    */
   private String getSystemType(final JCas cas) {
       String systemType = "";

       Type annotationType = cas.getTypeSystem().
           getType(SystemAnnotation.class.getCanonicalName());
       FSIterator<Annotation> iter = cas.getAnnotationIndex(annotationType).
           iterator();
       if (iter.isValid()) {
           FeatureStructure fs = iter.get();
           SystemAnnotation ann = (SystemAnnotation) fs;
           systemType = ann.getSystemType();
       }
       return systemType;
   }

    /** aggiunge l'annotation ActionTypeAnnotation alla CAS.
    *
    * @param cas CAS da elaborare
    */
   private void addActionTypeAnnotation(final JCas cas) {
       Split split = new Split();
       split.setDocumentText(cas.getDocumentText());
       split.add("<action-type>", 1);
       split.add("</action-type>", 0);
       split.process();

       ActionTypeAnnotation ann = new ActionTypeAnnotation(cas);
       ann.setBegin(split.getBegin());
       ann.setEnd(split.getEnd());
       ann.setValue(split.getValue());
       ann.addToIndexes();
   }

    /** aggiunge l'annotation EventIdAnnotation alla CAS.
     *
     * @param cas CAS da elaborare
     */
    private void addEventIdAnnotation(final JCas cas) {
        Split split = new Split();
        split.setDocumentText(cas.getDocumentText());
        split.add("<event", 1);
        split.add("id=\"", 1);
        split.add("\">", 0);
        split.process();

        EventIdAnnotation ann = new EventIdAnnotation(cas);
        ann.setBegin(split.getBegin());
        ann.setEnd(split.getEnd());
        ann.setValue(split.getValue());
        ann.addToIndexes();
    }
}
