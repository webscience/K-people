package it.webscience.kpeople.dal.cross;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.webscience.kpeople.be.EventMetadata;
import it.webscience.kpeople.dal.Singleton;

import org.apache.log4j.Logger;

public class EventMetadataDAO {

    private Logger logger; 

    /** Costruttore. */
    public EventMetadataDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }

    public final List<EventMetadata> getEventMetadataByHpmEventId(
            final String hpmEventId, final int idEvent) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getEventMetadataByIdEvent: " + hpmEventId);
        }

        EventMetadata eventMetadata = null;
        List<EventMetadata> eventMetadataList =
            new ArrayList<EventMetadata>();

        Connection conn = null;

        try {
            conn = Singleton.getInstance().getConnection();
            String queryMetadata =   "SELECT event_metadata.ID_EVENT_METADATA, event_metadata.KEYNAME, "
                + "event_metadata.VALUE, event_metadata.DELETED_BY, event_metadata.IS_DELETED, "
                + "event_metadata.DELETED_DATE, event_metadata.FIRST_ACTION_PERFORMER, "
                + "event_metadata.FIRST_ACTION_DATE, event_metadata.FIRST_ACTION_DATE, "
                + "event_metadata.LAST_ACTION_PERFORMER, event_metadata.LAST_ACTION_DATE, event.ID_EVENT "
                + "FROM event, event_metadata "
                + "WHERE (event.ID_EVENT = event_metadata.ID_EVENT) AND "
                + "(event.HPM_EVENT_ID = ? ) AND (event.ID_EVENT = ? )";

                PreparedStatement ps = conn.prepareStatement(queryMetadata);
                ps.setString(1, hpmEventId);
                ps.setInt(2, idEvent);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    eventMetadata = EventMetadataFactory.
                        createEventMetadata(rs);
                    eventMetadataList.add(eventMetadata);
                }
                rs.close();
                ps.close();
                
              
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        return eventMetadataList;
    }

}
