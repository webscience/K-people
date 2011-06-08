package it.webscience.kpeople.web.portlet.pattern.search;

import it.webscience.kpeople.web.portlet.pattern.search.EventDisplayTerms;

import javax.portlet.PortletRequest;

/**
 * Event display terms per la ricerca degli eventi associati al pattern.
 *
 */
public class EventSearchTerms extends EventDisplayTerms {

    /**
     * Costruttore.
     * @param portletRequest portlet request
     */
    public EventSearchTerms(final PortletRequest portletRequest) {
        super(portletRequest);
    }
}