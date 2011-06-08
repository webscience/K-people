package it.webscience.kpeople.web.portlet.user.search;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * DisplayTerms della pagina di ricerca processi.
 */
public class UserDisplayTerms extends DisplayTerms{
    
    public static final String PROCESS_ID = "processId";
    
    /** ordina per cognome */
    public static final String ORDINA_PER_SELECT = "ordinaPerSelect"; 
    
    
    private String processId;
    
    /** valore radio Ordina per cognome */
    private String ordinaPerSelect;
    
    
    
    /** Costruttore.
     * @param portletRequest portletRequest
     */
    public UserDisplayTerms(final PortletRequest portletRequest) {
        super(portletRequest);
        
        processId = ParamUtil.getString(portletRequest, PROCESS_ID);
        ordinaPerSelect = ParamUtil.getString(portletRequest, ORDINA_PER_SELECT);
        
    }
    
    public final String getOrdinaPerSelect(){
        return ordinaPerSelect;
    }
    
    public final String getProcessId(){
        return processId;
    }
    
    
    

}
