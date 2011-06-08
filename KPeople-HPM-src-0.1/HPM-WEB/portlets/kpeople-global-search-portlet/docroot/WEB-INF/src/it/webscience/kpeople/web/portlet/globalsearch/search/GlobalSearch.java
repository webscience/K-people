package it.webscience.kpeople.web.portlet.globalsearch.search;

import it.webscience.kpeople.service.cross.GlobalSearchServiceStub.KPeopleGenericDTO;

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
public class GlobalSearch extends SearchContainer<KPeopleGenericDTO> {

    static List<String> headerNames = new ArrayList<String>();
    static Map<String, String> orderableHeaders = new HashMap<String, String>();

    public static final String EMPTY_RESULTS_MESSAGE =
        "no-process-were-found";

    public GlobalSearch(
            PortletRequest portletRequest, PortletURL iteratorURL) {

        super(
                portletRequest, new GlobalSearchDisplayTerms(portletRequest),
                new GlobalSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
                DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

        GlobalSearchDisplayTerms displayTerms =
            (GlobalSearchDisplayTerms) getDisplayTerms();

//      salvataggio dei parametri per la paginazione
        iteratorURL.setParameter(GlobalSearchDisplayTerms.CMD,
                displayTerms.getCmd());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.FREE_TEXT,
                displayTerms.getFreeText());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.ORDINA_PER_COMBO,
                displayTerms.getOrdinaPerCombo());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.ORDINA_PER_RADIO,
                displayTerms.getOrdinaPerRadio());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.DATA_COMBO,
                displayTerms.getDataCombo());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.DATA_FROM,
                displayTerms.getDataFrom());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.DATA_TO,
                displayTerms.getDataTo());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.GESTITO_DA,
                displayTerms.getGestitoDa());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.TIPO,
                displayTerms.getTipo());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.MOSTRA_APERTI,
                displayTerms.getMostraAperti());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.MOSTRA_CHIUSI,
                displayTerms.getMostraChiusi());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.MOSTRA_RISERVATI,
                displayTerms.getMostraRiservati());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.MOSTRA_IN_TEMPO,
                displayTerms.getMostraInTempo());

        iteratorURL.setParameter(GlobalSearchDisplayTerms.MOSTRA_IN_RITARDO,
                displayTerms.getMostraInRitardo());
    }
}
