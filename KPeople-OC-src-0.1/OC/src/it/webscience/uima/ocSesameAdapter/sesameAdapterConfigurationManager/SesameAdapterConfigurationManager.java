package it.webscience.uima.ocSesameAdapter.sesameAdapterConfigurationManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import it.webscience.uima.ocControl.SesamePropertyKeys;
import it.webscience.uima.ocControl.ocSesameServerUtility.OCRdfRepository;

import org.apache.log4j.Logger;

/**
 * @author filieri
 */
public class SesameAdapterConfigurationManager {

    // volatile is needed so that multiple thread can reconcile the instance
    // semantics for volatile changed in Java 5.
    /** istanza della classe. */
    private volatile static SesameAdapterConfigurationManager _singleton;

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /** Sesame configuration properties. */
    private Properties properties = null;

    /** Percorso della directory dove sono salvati i file rdf temporanei. */
    private String filePath = null;

    /** costruttore. inizializza le proprietà. */
    private SesameAdapterConfigurationManager() {
        try {
            properties = new Properties();
            try {
                String confPath =
                        OCRdfRepository.class
                                .getClassLoader()
                                .getResource(
                                        SesamePropertyKeys.SESAME_ADAPTER_PROPERTIES_DEFAULT_RES)
                                .getFile();

                FileInputStream in = new FileInputStream(confPath);
                properties.load(in);
                in.close();
                
                createDir();
                
            } catch (Exception e) {
                logger.error(e.getMessage());
                logger.error(e.getStackTrace());
            }
        } catch (Exception e) {
            logger.error("Errore di inizializzazione: " + e);

            System.exit(1);
        }
    }

    /**
     * @return _singleton - istanza singleton della classe
     *         SolrAdapterConfigurationManager.
     */
    public static SesameAdapterConfigurationManager getInstance() {
        // needed because once there is singleton available no need to acquire
        // monitor again & again as it is costly
        if (_singleton == null) {
            synchronized (SesameAdapterConfigurationManager.class) {
                // this is needed if two threads are waiting at the monitor at
                // the
                // time when singleton was getting instantiated
                if (_singleton == null) {
                    _singleton = new SesameAdapterConfigurationManager();
                }
            }
        }
        return _singleton;
    }

    /**
     * ritorna la proprietà associata.
     * @param key
     *            chiave
     * @return valore della proprietà
     */
    public String getProperty(final String key) {
        String value = null;
        if (properties.containsKey(key)) {
            value = (String) properties.get(key);
        }

        return value;
    }

    /**
     * @return l'insieme delle proprietà.
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @return il percorso della directory dove sono memorizzati i file
     *         temporanei rdf.
     */
    public String getFilePath() {
        return filePath;
    }

    private String createDir() {
        filePath =
                properties.getProperty(SesamePropertyKeys.SESAME_RDF_DIR_PATH)
                        + Thread.currentThread().getId();
        File dir = new File(filePath);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
        }
        return filePath;
    }

    private String deleteDir(final long idtrhread) {
        filePath =
            properties.getProperty(SesamePropertyKeys.SESAME_RDF_DIR_PATH)
                        + idtrhread;
        File dir = new File(filePath);
        boolean deleted = deleteAllFile(dir);

        dir.delete();

        return filePath;
    }

    private static boolean deleteAllFile(final File dir) {
        boolean deleted = false;
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteAllFile(files[i]);
                } else {
                    deleted = files[i].delete();
                }
            }

        }
        return deleted;
    }

}
