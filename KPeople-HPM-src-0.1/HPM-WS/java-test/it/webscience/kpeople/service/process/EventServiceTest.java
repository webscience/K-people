package it.webscience.kpeople.service.process;

import static org.junit.Assert.*;
import it.webscience.kpeople.service.datatypes.Event;
import it.webscience.kpeople.service.datatypes.EventFilter;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.event.EventService;

import org.junit.Test;


public class EventServiceTest {
    
    @Test
    public void testGetEventsByHpmProcessId() {
        EventFilter eventFilter = new EventFilter();
        eventFilter.setHpmProcessId("prg31");
        User user = new User();
        user.setHpmUserId(null);
        

        try {
        EventService eventService = new EventService();
        Event[] events = eventService.getEvents(eventFilter, user);
        Event event = new Event();
        
        event = events[0];
        assertTrue(event.getIdEvent()== 1107);
        assertTrue(event.getHpmEventId().equals("mailserver.POSTFIX.systemId@2887@2011-02-28 13:29:43.0"));
        assertTrue(event.getHpmSystemId().equals("mailserver.POSTFIX.systemId"));
        assertTrue(event.getFirstActionDate().toString().equals("2011-02-28"));
        assertTrue(event.getLastActionDate().toString().equals("2011-02-28"));
        assertTrue(event.getAttachments()[0].getIdAttachment() == 1109);
        assertTrue(event.getEventMetadata()[0].getIdEventMetadata()== 1959);
        assertTrue(event.getEventMetadata()[0].getKeyname().equals("author"));
        assertTrue(event.getEventMetadata()[0].getValue().equals("dervishi@webscience.it"));
        assertTrue(event.getKeywords()[0].getDescription().equals("Chiave identificativa del processo generata dal sistema"));
        assertTrue(event.getKeywords()[0].getValue().equals("prg1158"));
        //assertTrue(event.getUser().getUsername().equals("filieri"));
        assertTrue(event.getUser().getScreenName().equals("dervishi@webscience.it"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
