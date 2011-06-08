package it.webscience.kpeople.web.portlet.activities.search;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * DisplayTerms della pagina di ricerca documenti.
 */
public class ActivitiesDisplayTerms extends DisplayTerms {

    /** filtro hpmProcessId. */
    public static final String HPM_PROCESS_ID = "hpmProcessId";

    /** valore hpm process id. */
    private String hpmProcessId;

    /** Costruttore.
     * @param portletRequest portletRequest
     */
    public ActivitiesDisplayTerms(final PortletRequest portletRequest) {
        super(portletRequest);

        hpmProcessId = ParamUtil.getString(portletRequest, HPM_PROCESS_ID);

    }

    /**
     * @return the hpmProcessId
     */
    public final String getHpmProcessId() {
        return hpmProcessId;
    }

    /**
     * @param in the hpmProcessId to set
     */
    public final void setHpmProcessId(final String in) {
        this.hpmProcessId = in;
    }
}
