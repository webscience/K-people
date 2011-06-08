package it.webscience.kpeople.service.report;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import it.webscience.kpeople.service.datatypes.ProcessEvent;
import it.webscience.kpeople.service.datatypes.UserEvent;

import org.junit.Test;


public class UserEventServiceTest {
    
    @Test
    public void testGetUserEventsCreator() {
        try {
            UserEventService service = new UserEventService();
            UserEvent[] UserEventList = service.getUserEventsCreator();
            UserEvent userEvent = new UserEvent();
            userEvent = UserEventList[0];
            assertTrue(userEvent.getHpmUserId().equalsIgnoreCase("filieri@kpeople.webscience.it"));
            assertTrue(userEvent.getHpmEventId().equalsIgnoreCase("5emailserver.POSTFIX.systemId@3007@2011-03-03 21:56:48.0"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
    
    @Test
    public void testGetUserEventsCommunicationTo() {
        try {
            UserEventService service = new UserEventService();
            UserEvent[] UserEventList = service.getUserEventsCommunicationTo();
            UserEvent userEvent = new UserEvent();
            userEvent = UserEventList[0];
            assertTrue(userEvent.getHpmUserId().equalsIgnoreCase("bolognese@kpeople.webscience.it"));
            assertTrue(userEvent.getHpmEventId().equalsIgnoreCase("5emailserver.POSTFIX.systemId@3007@2011-03-03 21:56:48.0"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
    
    @Test
    public void testGetUserEventsCommunicationCC() {
        try {
            UserEventService service = new UserEventService();
            UserEvent[] UserEventList = service.getUserEventsCommunicationCC();
            UserEvent userEvent = new UserEvent();
            userEvent = UserEventList[0];
            assertTrue(userEvent.getHpmUserId().equalsIgnoreCase("cattaneo@webscience.it"));
            assertTrue(userEvent.getHpmEventId().equalsIgnoreCase("mailserver.POSTFIX.systemId@3013@2011-03-04 15:58:16.0"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testGetUserEventsCommunicationFrom() {
        try {
            UserEventService service = new UserEventService();
            UserEvent[] UserEventList = service.getUserEventsCommunicationFrom();
            UserEvent userEvent = new UserEvent();
            userEvent = UserEventList[0];
            assertTrue(userEvent.getHpmUserId().equalsIgnoreCase("filieri@kpeople.webscience.it"));
            assertTrue(userEvent.getHpmEventId().equalsIgnoreCase("5emailserver.POSTFIX.systemId@3007@2011-03-03 21:56:48.0"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
