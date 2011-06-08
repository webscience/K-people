/**
 * 
 */
package it.webscience.kpeople.dal;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * classe singleton di connesione al db per activity.
 * @author bolognese
 *
 */
public final class ActivitySingleton {
    
    /** istanza della classe. */
    private static ActivitySingleton instance = null;

    /** configuration properties. */
    private Properties props = null;

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /** pool di connessioni C3P0 al db activity. */
    private ComboPooledDataSource cpds;

    /** costruttore. inizializza le proprietà. */
    private ActivitySingleton() {
        props = new Properties();
        try {
            logger.info("Singleton: Inizializzazione proprietà");

            InputStream is =
                    this.getClass().getClassLoader()
                            .getResourceAsStream("configuration.properties");
            props.load(is);

             String hpmUser = props.getProperty("activity.mysql.user");
             String hpmPassword = props.getProperty("activity.mysql.password");
             String hpmUrl = props.getProperty("activity.mysql.url");

            // acquisisce il pool di connessione
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl(hpmUrl);
            cpds.setUser(hpmUser);
            cpds.setPassword(hpmPassword);
            
//            cpds.setMinPoolSize(5);
//            cpds.setAcquireIncrement(5);
//            cpds.setMaxPoolSize(20);

             logger.info("activity mysql user: " + hpmUser);
             logger.info("activity mysql password: " + hpmPassword);
             logger.info("activity mysql url: " + hpmUrl);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }
    }

    /**
     * ritorna l'istanza della classe.
     * @return istanza della classe Singleton
     */
    public static synchronized ActivitySingleton getInstance() {
        if (instance == null) {
            instance = new ActivitySingleton();
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

    /**
     * Restituisce la connessione al db.
     * @return oggetto Connection
     * @throws SQLException
     *             if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        Connection con = cpds.getConnection();
        con.setAutoCommit(true);

        return con;
    }
    
}
