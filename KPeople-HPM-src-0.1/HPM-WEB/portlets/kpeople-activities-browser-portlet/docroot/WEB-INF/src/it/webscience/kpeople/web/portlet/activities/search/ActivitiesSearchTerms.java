package it.webscience.kpeople.web.portlet.activities.search;

import javax.portlet.PortletRequest;

/**
 * document search terms.
 */
public class ActivitiesSearchTerms extends ActivitiesDisplayTerms {

    /**
     * Costruttore.
     * @param portletRequest portlet request
     */
    public ActivitiesSearchTerms(final PortletRequest portletRequest) {
        super(portletRequest);
    }
}