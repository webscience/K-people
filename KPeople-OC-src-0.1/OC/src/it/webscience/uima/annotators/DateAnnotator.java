package it.webscience.uima.annotators;

import it.webscience.uima.CasErrorManager;
import it.webscience.uima.annotations.DateAnnotation;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

/** Estrae la data da tutte le annotations contenenti un'informazione temporale.
 */
public class DateAnnotator extends JCasAnnotator_ImplBase {

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
    //      estraggo le annotations di tipo DateAnnotation
            Type annotationType = cas.getTypeSystem().
                getType(DateAnnotation.class.getCanonicalName());
            FSIterator<Annotation> it = cas.getAnnotationIndex(annotationType).
                iterator();

            while (it.hasNext()) {
                DateAnnotation annotation = (DateAnnotation) it.next();

                String value = annotation.getValue();

                long timeInMillis;
                try {
                    timeInMillis = getTimeInMillis(value);
                } catch (ParseException e) {

                    StringWriter sWriter = new StringWriter();
                    e.printStackTrace(new PrintWriter(sWriter));
                    logger.fatal(
                            sWriter.getBuffer().toString());

                    throw new AnalysisEngineProcessException();
                }

                annotation.setTimeInMillis(timeInMillis);
            }
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

    /** converte la data dal formato String al long.
     * @param value data nel formato stringa
     * @return milliseconds since January 1, 1970, 00:00:00 GMT
     * @throws ParseException data non parserizzata
     */
    private long getTimeInMillis(final String value) throws ParseException {
        String xmlDateFormat = "yyyy-MM-dd'T'HH:mm:ss.S'Z'";
        SimpleDateFormat sdf = new SimpleDateFormat(xmlDateFormat);

        Date date = sdf.parse(value);
        return date.getTime();
    }
}
