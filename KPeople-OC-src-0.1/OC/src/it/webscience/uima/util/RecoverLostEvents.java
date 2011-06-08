package it.webscience.uima.util;


import it.webscience.uima.Singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.uima.aae.client.UimaAsynchronousEngine;
import org.apache.uima.adapter.jms.client.BaseUIMAAsynchronousEngine_impl;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * Classe per recuperare gli eventi non correttamente elaborati dalla catena.
 * @author dellanna
 */
public class RecoverLostEvents {
    /** engine uima. */
    private UimaAsynchronousEngine uimaAsEngine = null;

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * main per l'avvio del processo.
     * @param args command line arguments
     * @throws Exception eccezione generica
     */
    public static void main(final String[] args) throws Exception {
        RecoverLostEvents runner = new RecoverLostEvents(args);
        runner.run();
    }

    /**
     * esegue la lettura della directory.
     * Ogni xml viene inviato all'engine
     */
    private void run() {
        String dirString = Singleton.getInstance().
            getProperty("cas-error-folder");

        logger.info("Analisi dei documenti della cartella "
                + dirString);

        File dir = new File(dirString);

        String[] list = dir.list();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                String fileName = list[i];

//              ignora i files di tipo svn
                if (fileName.endsWith("svn")) {
                    continue;
                }

                try {
                    CAS cas = uimaAsEngine.getCAS();
                    cas.setDocumentText(readFile(dirString + fileName));

                    logger.info("Invio della CAS");
                    uimaAsEngine.sendCAS(cas);

                    logger.info(
                            fileName
                            + " inviato con successo.");
                    File f = new File(dirString + fileName);
                    f.renameTo(new File(dirString + "OK_" + fileName));
                } catch (Exception e) {
                    logger.error(
                            "Errore nell'analisi del file "
                            + fileName);

                    StringWriter sWriter = new StringWriter();
                    e.printStackTrace(new PrintWriter(sWriter));
                    logger.fatal(sWriter.getBuffer().toString());
                }
            }
        }
    }

    /**
     * costruttore.
     * @param args parametri in ingresso
     * @throws Exception eccezione generica
     */
    public RecoverLostEvents(final String[] args) throws Exception {
        uimaAsEngine = new BaseUIMAAsynchronousEngine_impl();
        initUimaAsEngine();
    }

    /**
     * inizializzazione dell'engine UIMA.
     * @throws ResourceInitializationException errore in init
     */
    private void initUimaAsEngine()
            throws ResourceInitializationException {
        logger.info("Inizializzazione uima AS engine");

        Singleton conf = Singleton.getInstance();
        String brokerUrl = conf.getProperty("uima.brokerUrl");
        String endpoint = conf.getProperty("uima.endpoint");

        Map<String, Object> appCtx = new HashMap<String, Object>();
        appCtx.put(UimaAsynchronousEngine.ServerUri, brokerUrl);
        appCtx.put(UimaAsynchronousEngine.Endpoint, endpoint);
        appCtx.put(UimaAsynchronousEngine.CasPoolSize, 2);

        uimaAsEngine.initialize(appCtx);
    }

    /**
     * legge il contenuto del file.
     * @param filePath file da leggere
     * @return contenuto del file
     * @throws FileNotFoundException if the file does not exist
     */
    private String readFile(
            final String filePath) throws FileNotFoundException {
        logger.info("Lettura del file " + filePath);

        StringBuilder text = new StringBuilder();
        String separator = System.getProperty("line.separator");

        Scanner scanner = new Scanner(
                new FileInputStream(new File(filePath)), "UTF-8");
        try {
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine() + separator);
            }
        } finally {
            scanner.close();
        }

        return text.toString();
    }
}
