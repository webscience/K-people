package it.webscience.kpeople.web.portlet.report.action;


import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * DisplayTerms della pagina di ricerca processi.
 */
public class ReportDisplayTerms extends DisplayTerms {

    /** filtro Tipo. */
    public static final String TIPO = "tipo";

    /** ordina per. */
    public static final String ORDINA_PER_COMBO = "ordinaPerCombo";

    /** ordinamento ASC/DESC. */
    public static final String ORDINA_PER_RADIO = "ordinaPerRadio";

    /** valore combo Ordina per. */
    private String ordinaPerCombo;

    /** valore radio Ordina per. */
    private String ordinaPerRadio;

    /** valore Tipo. */
    private String tipo;

    /**
     * Costruttore.
     * @param portletRequest
     *            portletRequest
     */
    public ReportDisplayTerms(final PortletRequest portletRequest) {
        super(portletRequest);

        ordinaPerCombo = ParamUtil.getString(portletRequest, ORDINA_PER_COMBO);
        ordinaPerRadio = ParamUtil.getString(portletRequest, ORDINA_PER_RADIO);

        tipo = ParamUtil.getString(portletRequest, TIPO);

        // valori default
        if (ordinaPerRadio.equals("")) {
            ordinaPerRadio = "DESC";
        }

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
    public final String getOrdinaPerRadio() {
        return ordinaPerRadio;
    }

    /**
     * @return the tipo
     */
    public final String getTipo() {
        return tipo;
    }

}
