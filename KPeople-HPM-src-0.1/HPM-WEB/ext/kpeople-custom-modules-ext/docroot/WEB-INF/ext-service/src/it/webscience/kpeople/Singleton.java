package it.webscience.kpeople;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/** implementazione del Singleton Pattern.
 */
public final class Singleton {
    /** istanza della classe. */
    private static Singleton instance = null;

    /** UIMA configuration properties. */
    private Properties props = null;

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());


    /** costruttore. inizializza le proprietà. */
    private Singleton() {
        props = new Properties();
        try {
            logger.info("Inizializzazione proprietà");

            InputStream is = this.getClass().getClassLoader().
                getResourceAsStream("configuration.properties");
            props.load(is);

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }
    }

    /** ritorna l'istanza della classe.
     * @return istanza della classe Singleton
     */
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

    /** ritorna la proprietà associata.
     * @param key chiave
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
