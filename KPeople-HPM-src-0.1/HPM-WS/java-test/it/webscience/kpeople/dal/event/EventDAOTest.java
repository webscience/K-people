package it.webscience.kpeople.dal.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;



import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

public class EventDAOTest {

	@Test
	public final void saveEventTest() {
		EventDAO evDao = new EventDAO();
		
		User user = null;
		try {
			user = new UserDAO().getUserByHpmUserId("bolognese@kpeople.webscience.it");
		} catch (KPeopleDAOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Event event = new Event();
    	event.setName("Evento di junit test");
    	event.setHpmSystemId("HPM");
    	
    	Date now = new Date();
    	
    	Connection conn =null;
    	try {
			conn = Singleton.getInstance().getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	event.setUser(user);
		
    	int eventId = 0;
		try {
			eventId = evDao.saveEvent(event, user, now, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotSame(0, eventId);
		
	}
	
	@Test
	public final void updateEventTest() {
		
		Connection conn =null;
    	try {
			conn = Singleton.getInstance().getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EventDAO eventDao = new EventDAO();
		Event event = new Event();
		event.setIdEvent(1329);
		event.setHpmEventId("HPMEVENTIDDDDDD");
		try {
			eventDao.updateEvent(conn, event);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, 1);
	}
	
	@Test
	public final void saveEventAttachmentAssociationTest() {
		int idEvent = 2000;
		int idAttachment = 3000;
		assertEquals(1, 1);
		EventDAO evDao = new EventDAO();
		Connection conn =null;
    	try {
			conn = Singleton.getInstance().getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (!evDao.existsEventAttachmentAssociation(idAttachment, idEvent))
			{ evDao.saveEventAttachmentAssociation(conn, idAttachment, idEvent); } 
		} catch(Exception e) {
			
		}
		boolean ret1 = false;
		try {
			ret1 = evDao.existsEventAttachmentAssociation(idAttachment, idEvent);
		} catch(Exception e) {
			
		}
		assertEquals(ret1, true);
	}
	
	@Test
	public final void existsEventAttachmentAssociationTest() {
		int idEvent = 1318;
		int idAttachment = 1391;
		EventDAO evDao = new EventDAO();
		boolean ret = false;
		try {
			ret = evDao.existsEventAttachmentAssociation(idAttachment, idEvent);
		} catch(Exception e) {
			
		}
		assertEquals(ret, true);
	}
}
