package it.webscience.kpeople.bll.proxy;

import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.EventFilter;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.event.EventDAO;
import it.webscience.kpeople.dal.event.IEventDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Proxy per la classe EventDAO.
 */
public class EventDAOProxy implements IEventDAO {

    /**
     * Restituisce la lista degli eventi associati ad un processo.
     * @param user user che esegue la chiamata.
     * @param eventFilter oggetto che contiene i parametri
     * di ricerca degli eventi.
     * @return elenco degli Events
     * @throws KPeopleDAOException eccezione durante l'elaborazione
     */
    public final List<Event> getEvents(final EventFilter eventFilter,
            final User user) throws KPeopleDAOException {

        EventDAO dao = new EventDAO();

        return dao.getEvents(eventFilter, user);
    }

	@Override
	public int saveEvent(Event event, User user, Date now, Connection conn)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateEvent(Connection pConn, Event pEvent) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveEventAttachmentAssociation(Connection pConn,
			int pIdAttachment, int pIdEvent) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsEventAttachmentAssociation(int pIdAttachment,
			int pIdEvent) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}

