package it.webscience.kpeople.dal.event;

import java.sql.ResultSet;
import java.sql.SQLException;

import it.webscience.kpeople.be.Event;

public class EventFactory {

    public static Event createEvent(final ResultSet rs) throws SQLException {

        Event event = new Event();
        event.setIdEvent(rs.getInt("ID_EVENT"));
        event.setName(rs.getString("NAME"));
        event.setHpmEventId(rs.getString("HPM_EVENT_ID"));
        event.setHpmSystemId(rs.getString("HPM_SYSTEM_ID"));
        event.setFirstActionDate(rs.getTimestamp("FIRST_ACTION_DATE"));
        event.setLastActionDate(rs.getTimestamp("LAST_ACTION_DATE"));

        return event;

    }

}
