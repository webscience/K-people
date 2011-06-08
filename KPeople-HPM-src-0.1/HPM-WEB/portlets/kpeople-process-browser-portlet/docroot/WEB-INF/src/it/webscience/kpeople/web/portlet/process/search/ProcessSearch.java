package it.webscience.kpeople.web.portlet.process.search;

import it.webscience.kpeople.service.process.ProcessServiceStub.Process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.search.SearchContainer;

/**
 * @author Search conteiner per la "Ricerca processi"
 */
public class ProcessSearch extends SearchContainer<Process> {

    static List<String> headerNames = new ArrayList<String>();
    static Map<String, String> orderableHeaders = new HashMap<String, String>();

    public static final String EMPTY_RESULTS_MESSAGE =
        "no-process-were-found";

    public ProcessSearch(
            PortletRequest portletRequest, PortletURL iteratorURL) {

        super(
                portletRequest, new ProcessDisplayTerms(portletRequest),
                new ProcessSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
                DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

        ProcessDisplayTerms displayTerms =
            (ProcessDisplayTerms) getDisplayTerms();

//      salvataggio dei parametri per la paginazione
        iteratorURL.setParameter(ProcessDisplayTerms.CMD,
                displayTerms.getCmd());

        iteratorURL.setParameter(ProcessDisplayTerms.FREE_TEXT,
                displayTerms.getFreeText());

        iteratorURL.setParameter(ProcessDisplayTerms.ORDINA_PER_COMBO,
                displayTerms.getOrdinaPerCombo());

        iteratorURL.setParameter(ProcessDisplayTerms.ORDINA_PER_RADIO,
                displayTerms.getOrdinaPerRadio());

        iteratorURL.setParameter(ProcessDisplayTerms.DATA_COMBO,
                displayTerms.getDataCombo());

        iteratorURL.setParameter(ProcessDisplayTerms.DATA_FROM,
                displayTerms.getDataFrom());

        iteratorURL.setParameter(ProcessDisplayTerms.DATA_TO,
                displayTerms.getDataTo());

        iteratorURL.setParameter(ProcessDisplayTerms.GESTITO_DA,
                displayTerms.getGestitoDa());

        iteratorURL.setParameter(ProcessDisplayTerms.TIPO,
                displayTerms.getTipo());

        iteratorURL.setParameter(ProcessDisplayTerms.MOSTRA_APERTI,
                displayTerms.getMostraAperti());

        iteratorURL.setParameter(ProcessDisplayTerms.MOSTRA_CHIUSI,
                displayTerms.getMostraChiusi());

        iteratorURL.setParameter(ProcessDisplayTerms.MOSTRA_RISERVATI,
                displayTerms.getMostraRiservati());

        iteratorURL.setParameter(ProcessDisplayTerms.MOSTRA_IN_TEMPO,
                displayTerms.getMostraInTempo());

        iteratorURL.setParameter(ProcessDisplayTerms.MOSTRA_IN_RITARDO,
                displayTerms.getMostraInRitardo());
    }
}
