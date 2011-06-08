package it.webscience.kpeople.web.portlet.document.search;

import it.webscience.kpeople.service.document.DocumentServiceStub.Document;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.search.SearchContainer;

/**
 * @author Search container per la "Ricerca documenti"
 */
public class DocumentSearch extends SearchContainer<Document> {
    /** Nomi dell'header. */
    private static List<String> headerNames = new ArrayList<String>();

    /** Messaggio di 'nessun risultato trovato.'. */
    public static final String EMPTY_RESULTS_MESSAGE =
        "find-documents.no-documents-were-found";

    /**
     * Costruttore.
     * @param portletRequest portlet request
     * @param iteratorURL iterator
     */
    public DocumentSearch(
            final PortletRequest portletRequest,
            final PortletURL iteratorURL) {

        super(
                portletRequest, new DocumentDisplayTerms(portletRequest),
                new DocumentSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
                DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

        DocumentDisplayTerms displayTerms =
            (DocumentDisplayTerms) getDisplayTerms();

//      salvataggio dei parametri per la paginazione
        iteratorURL.setParameter(
                DocumentDisplayTerms.HPM_PROCESS_ID,
                displayTerms.getHpmProcessId());
    }
}
