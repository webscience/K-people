package it.webscience.kpeople.web.portlet.activities.search;

import it.webscience.kpeople.service.activity.ActivityServiceStub.Activity;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.search.SearchContainer;

/**
 * @author Search container per la "Ricerca attività"
 */
public class ActivitiesSearch extends SearchContainer<Activity> {
    /** Nomi dell'header. */
    private static List<String> headerNames = new ArrayList<String>();

    /** Messaggio di 'nessun risultato trovato.'. */
    public static final String EMPTY_RESULTS_MESSAGE =
        "find-activities.no-activities-were-found";

    /**
     * Costruttore.
     * @param portletRequest portlet request
     * @param iteratorURL iterator
     */
    public ActivitiesSearch(
            final PortletRequest portletRequest,
            final PortletURL iteratorURL) {

        super(
                portletRequest, new ActivitiesDisplayTerms(portletRequest),
                new ActivitiesSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
                DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

        ActivitiesDisplayTerms displayTerms =
            (ActivitiesDisplayTerms) getDisplayTerms();

//      salvataggio dei parametri per la paginazione
        iteratorURL.setParameter(
                ActivitiesDisplayTerms.HPM_PROCESS_ID,
                displayTerms.getHpmProcessId());
    }
}
