package it.webscience.kpeople.web.portlet.globalsearch.search;

import it.webscience.kpeople.web.portlet.globalsearch.util.GlobalSearchConstants;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * DisplayTerms della pagina di ricerca processi.
 */
public class GlobalSearchDisplayTerms extends DisplayTerms {
    /** ???. */
    public static final String PROCESS_ID = "processId";

    /** identifica i campo ti testo vuoto. */
    public static final String EMPTY_FREE_TEXT = "emptyFreeText";

    /** modalità di ricerca (semplice/avanzata). */
    public static final String CMD = "cmd";

    /** campo libero (ricerca semplice). */
    public static final String FREE_TEXT = "freeText";

    /** ordina per. */
    public static final String ORDINA_PER_COMBO = "ordinaPerCombo";

    /** ordinamento ASC/DESC. */
    public static final String ORDINA_PER_RADIO = "ordinaPerRadio";

    /** filtro data. */
    public static final String DATA_COMBO = "dataCombo";

    /** filtro data DA. */
    public static final String DATA_FROM = "dataFrom";

    /** filtro data A. */
    public static final String DATA_TO = "dataTo";

    /** filtro Gestito da. */
    public static final String GESTITO_DA = "gestitoDa";

    /** filtro Tipo. */
    public static final String TIPO = "tipo";

    /** filtro Mostra Aperti. */
    public static final String MOSTRA_APERTI = "mostraAperti";

    /** filtro Mostra Chiusi. */
    public static final String MOSTRA_CHIUSI = "mostraChiusi";

    /** filtro Mostra Riservati. */
    public static final String MOSTRA_RISERVATI = "mostraRiservati";

    /** filtro Mostra in tempo. */
    public static final String MOSTRA_IN_TEMPO = "mostraInTempo";

    /** filtro Mostra in ritardo. */
    public static final String MOSTRA_IN_RITARDO = "mostraInRitardo";

    /** ??. */
    private String processId;

    /** valore della modalità di ricerca. */
    private String cmd;

    /** valore testo ricerca semplice. */
    private String freeText;

    /** valore combo Ordina per. */
    private String ordinaPerCombo;

    /** valore radio Ordina per. */
    private String ordinaPerRadio;

    /** valore filtro data. */
    private String dataCombo;

    /** valore filtro data DA. */
    private String dataFrom;

    /** valore filtro data A. */
    private String dataTo;

    /** valore Gestito da. */
    private String gestitoDa;

    /** valore Tipo. */
    private String tipo;

    /** valore checkbox Mostra aperti. */
    private String mostraAperti;

    /** valore checkbox Mostra chiusi. */
    private String mostraChiusi;

    /** valore checkbox Mostra riservati. */
    private String mostraRiservati;

    /** valore checkbox Mostra in tempo. */
    private String mostraInTempo;

    /** valore checkbox Mostra in ritardo. */
    private String mostraInRitardo;

    /** Costruttore.
     * @param portletRequest portletRequest
     */
    public GlobalSearchDisplayTerms(final PortletRequest portletRequest) {
        super(portletRequest);

        processId = ParamUtil.getString(portletRequest, PROCESS_ID);

        cmd = ParamUtil.getString(portletRequest, CMD);
        freeText = ParamUtil.getString(portletRequest, FREE_TEXT);
        ordinaPerCombo = ParamUtil.getString(portletRequest, ORDINA_PER_COMBO);
        ordinaPerRadio = ParamUtil.getString(portletRequest, ORDINA_PER_RADIO);
        dataCombo = ParamUtil.getString(portletRequest, DATA_COMBO);
        dataFrom = ParamUtil.getString(portletRequest, DATA_FROM);
        dataTo = ParamUtil.getString(portletRequest, DATA_TO);
        gestitoDa = ParamUtil.getString(portletRequest, GESTITO_DA);
        tipo = ParamUtil.getString(portletRequest, TIPO);
        mostraAperti = ParamUtil.getString(portletRequest, MOSTRA_APERTI);
        mostraChiusi = ParamUtil.getString(portletRequest, MOSTRA_CHIUSI);
        mostraRiservati = ParamUtil.getString(portletRequest, MOSTRA_RISERVATI);
        mostraInTempo = ParamUtil.getString(portletRequest, MOSTRA_IN_TEMPO);
        mostraInRitardo = ParamUtil.getString(
                portletRequest, MOSTRA_IN_RITARDO);

//      valori default
        if (ordinaPerRadio.equals("")) {
            ordinaPerRadio = "ASC";
        }
        if (cmd.equals("")) {
            cmd = GlobalSearchConstants.SIMPLE_SEARCH_PROCESS;
        }
    }

    /**
     * @return the cmd
     */
    public final String getCmd() {
        return cmd;
    }

    /**
     * @return the processId
     */
    public final String getProcessId() {
        return processId;
    }

    /**
     * @return the freeText
     */
    public final String getFreeText() {
        return freeText;
    }

    /**
     * @return the ordinaPerCombo
     */
    public final String getOrdinaPerCombo() {
        return ordinaPerCombo;
    }

    /**
     * @return the ordinaPerRadio
     */
    public String getOrdinaPerRadio() {
        return ordinaPerRadio;
    }

    /**
     * @return the dataCombo
     */
    public final String getDataCombo() {
        return dataCombo;
    }

    /**
     * @return the dataFrom
     */
    public final String getDataFrom() {
        return dataFrom;
    }

    /**
     * @return the dataTo
     */
    public final String getDataTo() {
        return dataTo;
    }

    /**
     * @return the gestitoDa
     */
    public final String getGestitoDa() {
        return gestitoDa;
    }

    /**
     * @return the tipo
     */
    public final String getTipo() {
        return tipo;
    }

    /**
     * @return the mostraAperti
     */
    public final String getMostraAperti() {
        return mostraAperti;
    }

    /**
     * @return the mostraChiusi
     */
    public final String getMostraChiusi() {
        return mostraChiusi;
    }

    /**
     * @return the mostraRiservati
     */
    public final String getMostraRiservati() {
        return mostraRiservati;
    }

    /**
     * @return the mostraInTempo
     */
    public final String getMostraInTempo() {
        return mostraInTempo;
    }

    /**
     * @return the mostraInRitardo
     */
    public final String getMostraInRitardo() {
        return mostraInRitardo;
    }
}
