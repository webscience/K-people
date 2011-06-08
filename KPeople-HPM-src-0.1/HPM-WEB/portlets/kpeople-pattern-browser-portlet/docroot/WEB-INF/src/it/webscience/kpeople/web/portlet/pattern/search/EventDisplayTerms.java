package it.webscience.kpeople.web.portlet.pattern.search;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * Classe la visualizzazione dell'elenco eventi associati ad un pattern.
 *
 */
public class EventDisplayTerms extends DisplayTerms {

    /** costante per il passaggio parametri. */
    public static final String PATTERN_ID = "patternId";

    /**
     * pattern id della pagina visualizzata.
     */
    private String patternId;

    /**
     * Costruttore.
     * @param portletRequest portlet request
     */
    public EventDisplayTerms(final PortletRequest portletRequest) {
        super(portletRequest);

        patternId = ParamUtil.getString(portletRequest, PATTERN_ID);
    }

    /**
     * @return the patternId
     */
    public String getPatternId() {
        return patternId;
    }

    /**
     * @param patternId the patternId to set
     */
    public void setPatternId(String patternId) {
        this.patternId = patternId;
    }
}
