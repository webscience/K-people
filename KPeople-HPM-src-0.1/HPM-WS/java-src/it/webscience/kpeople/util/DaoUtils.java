package it.webscience.kpeople.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**Classe di utility di supporto per il livello dal.
 * @author filieri
 */
public class DaoUtils {

    /** logger. */
    private static Logger logger;

    /**
     * Esegue un rollback della transazione.
     * @param conn
     *            connessione al db
     * @param e
     *            eccezione lanciata
     * @throws SQLException
     *             if a database access error occurs
     */
    public static void rollbackTransaction(final Connection conn,
            final SQLException e) throws SQLException {
        if (conn != null) {
            conn.rollback();
            logger.fatal("Connection rollback...");
            logger.fatal(e.getStackTrace());

            StringWriter sWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(sWriter));
            logger.fatal(sWriter.getBuffer().toString());
        }
    }
}
