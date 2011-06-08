package it.webscience.kpeople.dal.activity;

import it.webscience.kpeople.be.ActivityMetadata;
import it.webscience.kpeople.be.PatternMetadata;
import it.webscience.kpeople.dal.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ActivityMetadataDAO implements IActivityMetadataDAO {

    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public ActivityMetadataDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Ottiene la lista dei metadati associati ad un pattern.
     * 
     * @param pPatternId
     *            identificativo del pattern di cui estrarre i metadati
     * @return lista di PatternMetadata
     * @throws SQLException
     *             eccezione
     */
    @Override
    public List<ActivityMetadata> getActivityMetadatasByActivityId(
            int pActivityId) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getActivityMetadatasByActivityId: " + pActivityId);
        }

        // Lettura parametri formali
        int activityId = pActivityId;

        Connection conn = null;
        List<ActivityMetadata> activityMetadatas = null;

        try {
            conn = Singleton.getInstance().getConnection();

            String query = null;
            StringBuffer sbQuery = new StringBuffer();

            // Esecuzione query
            sbQuery.append("SELECT * FROM activity_metadata ");
            sbQuery.append(" WHERE ");
            sbQuery.append(" is_deleted = false ");
            sbQuery.append(" AND id_activity = ? ");
            query = sbQuery.toString();

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, activityId);
            ResultSet rs = ps.executeQuery();

            activityMetadatas = new ArrayList<ActivityMetadata>();
            while (rs.next()) {
                ActivityMetadata activityMetadata = this
                        .getActivityMetadataById(rs
                                .getInt("ID_ACTIVITY_METADATA"));
                activityMetadatas.add(activityMetadata);
            }
            rs.close();
            ps.close();
            

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return activityMetadatas;
    }

    /**
     * Estrae i dati della business entiti PatternMetadata dato un id.
     * 
     * @param pIdPatternMetadata
     *            identificativo BE da estrarre.
     * @return Info relative al PatternMetadata
     * @throws SQLException
     *             eccezione
     */
    @Override
    public ActivityMetadata getActivityMetadataById(int pIdActivityMetadata)
            throws SQLException {
        // TODO Auto-generated method stub
        if (logger.isDebugEnabled()) {
            logger.debug("getActivityMetadataById: " + pIdActivityMetadata);
        }

        // Lettura parametri formali
        int idActivityMetadata = pIdActivityMetadata;

        Connection conn = null;
        ActivityMetadata activityMetadata = null;

        try {
            conn = Singleton.getInstance().getConnection();

            String query = null;
            StringBuffer sbQuery = new StringBuffer();

            // Esecuzione query
            sbQuery.append("SELECT * FROM activity_metadata ");
            sbQuery.append(" WHERE ");
            sbQuery.append(" is_deleted = false ");
            sbQuery.append(" AND id_activity_metadata = ? ");
            query = sbQuery.toString();

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idActivityMetadata);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // activityMetadata
                // = ActivityMetadataFactory
            }
            rs.close();
            ps.close();
            

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return activityMetadata;
    }

}
