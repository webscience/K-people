package it.webscience.kpeople.dal;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * implementazione del Singleton Pattern. Classe per la condivisione di
 * Properties all'interno dell'uima
 * @author dellanna
 */
public final class Singleton {
    /** istanza della classe. */
    private static Singleton instance = null;

    /** UIMA configuration properties. */
    private Properties props = null;

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /** pool di connessioni C3P0 al db HPM. */
    private ComboPooledDataSource cpds;

    /** costruttore. inizializza le proprietà. */
    private Singleton() {
        props = new Properties();
        try {
            logger.info("Singleton: Inizializzazione proprietà");

            InputStream is =
                    this.getClass().getClassLoader()
                            .getResourceAsStream("configuration.properties");
            props.load(is);

             String hpmUser = props.getProperty("hpm.mysql.user");
             String hpmPassword = props.getProperty("hpm.mysql.password");
             String hpmUrl = props.getProperty("hpm.mysql.url");

            // acquisisce il pool di connessione
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl(hpmUrl);
            cpds.setUser(hpmUser);
            cpds.setPassword(hpmPassword);
            
            
//            cpds.setMinPoolSize(
//                    Integer.parseInt(props.getProperty("c3p0.min.pool.size")));
//            cpds.setAcquireIncrement(
//                    Integer.parseInt(props
//                            .getProperty("c3p0.acquire.increment")));
//            cpds.setMaxPoolSize(
//                    Integer.parseInt(props.getProperty("c3p0.max.pool.size")));

            /*cpds.setIdleConnectionTestPeriod(
                    Integer.parseInt(props.getProperty("c3p0.idle.conn.test")));
            cpds.setCheckoutTimeout(
                    Integer.parseInt(props.getProperty("c3p0.timeout")));*/

            logger.info("HPM mysql user: " + hpmUser);
            logger.info("HPM mysql password: " + hpmPassword);
            logger.info("HPM mysql url: " + hpmUrl);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }
    }

    /**
     * ritorna l'istanza della classe.
     * @return istanza della classe Singleton
     */
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
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
