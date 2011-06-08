package it.webscience.uima;

import it.webscience.uima.Singleton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;

/** Gestisce le CAS che non hanno raggiunto il consumer.
 * Interviene nei casi in cui uno degli annotators non si è
 * concluso correttamente.
 * @author dellanna
 */
public final class CasErrorManager {
    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /** Salva su file system il contenuto della CAS.
     * @param cas cas da salvare
     */
    public void storeCasInFile(final JCas cas) {
        String folderProp = Singleton.getInstance()
            .getProperty("cas-error-folder");

        File folder = new File(folderProp);

        String filename = new Date().getTime() + ".xml";

        logger.error(
                "Cas "
                + cas.toString()
                + "salvata in "
                + filename);

        File file = new File(folder, filename);
        FileWriter fstream;
        try {
            fstream = new FileWriter(file, false);
            fstream.write(cas.getDocumentText());
            fstream.close();
        } catch (IOException e) {
            logger.fatal(e.getMessage());

            StringWriter sWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(sWriter));
            logger.fatal(sWriter.getBuffer().toString());
        }
    }
}
