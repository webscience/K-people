package it.webscience.kpeople.dal.process;

import it.webscience.kpeople.be.ProcessType;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Classe per l'accesso alla tabella PROCESS_TYPE.
 */
public class ProcessTypeDAO implements IProcessTypeDAO {
    /** logger. */
    private Logger logger;

    /**
     * Costruttore di default.
     */
    public ProcessTypeDAO() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Restituisce l'elenco completo dei ProcessType.
     * @param user user che esegue la chiamata
     * @return elenco di User
     * @throws SQLException eccezione durante l'elaborazione
     */
    public final List<ProcessType> getProcessTypes(final User user)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getProcessTypes");
        }

        List<ProcessType> results = new ArrayList<ProcessType>();

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            String query =
                "SELECT * FROM process_type WHERE IS_DELETED = FALSE;";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProcessType pt = ProcessTypeFactory.createProcessType(rs);
                results.add(pt);
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end getOwners");
        }

        return results;
    }

    /**
     * ritorna l'oggetto ProcessType associato alla chiave desiderata.
     * @param idProcessType chiave
     * @throws SQLException eccezioni db
     * @return oggetto User
     */
    public final ProcessType getProcessTypeByIdProcessType(
            final int idProcessType)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getProcessTypeByIdProcessType: " + idProcessType);
        }

        ProcessType pt = null;

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            String query =
                "SELECT * FROM process_type WHERE ID_PROCESS_TYPE = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idProcessType);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pt = ProcessTypeFactory.createProcessType(rs);
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return pt;
    }
}
