package it.webscience.kpeople.util;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Classe per la configurazione del client uima implementata come singleton.
 * @author filieri
 */
public final class ConfigurationManager {
    /** istanza della classe. */
    private static ConfigurationManager instance = null;

    /** UIMA configuration properties. */
    private Properties props = null;

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /** costruttore. inizializza le proprietà. */
    private ConfigurationManager() {
        props = new Properties();
        try {
            logger.info(this.getClass() + "Inizializzazione proprieta");

            InputStream is =
                    this.getClass().getClassLoader()
                            .getResourceAsStream("configuration.properties");
            props.load(is);

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }
    }

    /**
     * ritorna l'istanza della classe.
     * @return istanza della classe Singleton
     */
    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }

        return instance;
    }

    /**
     * ritorna la proprietà associata.
     * @param key
     *            chiave
     * @return valore della proprietà
     */
    public String getProperty(final String key) {
        String value = null;
        if (props.containsKey(key)) {
            value = (String) props.get(key);
        }

        return value;
    }

}
