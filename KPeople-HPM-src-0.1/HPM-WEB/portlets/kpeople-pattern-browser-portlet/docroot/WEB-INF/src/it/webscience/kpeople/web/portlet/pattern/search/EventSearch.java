package it.webscience.kpeople.web.portlet.pattern.search;
import it.webscience.kpeople.service.event.EventServiceStub.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.search.SearchContainer;


public class EventSearch extends SearchContainer<Event>{
    static List<String> headerNames = new ArrayList<String>();
    static Map<String, String> orderableHeaders = new HashMap<String, String>();

    public static final String EMPTY_RESULTS_MESSAGE =
        "";

    public EventSearch(
            PortletRequest portletRequest, PortletURL iteratorURL) {

        super(
                portletRequest, new EventDisplayTerms(portletRequest),
                new EventSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
                DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

        EventDisplayTerms displayTerms = (EventDisplayTerms) getDisplayTerms();

//      salvataggio dei parametri per la paginazione
        iteratorURL.setParameter(
                EventDisplayTerms.PATTERN_ID,
                displayTerms.getPatternId());

    }
}
