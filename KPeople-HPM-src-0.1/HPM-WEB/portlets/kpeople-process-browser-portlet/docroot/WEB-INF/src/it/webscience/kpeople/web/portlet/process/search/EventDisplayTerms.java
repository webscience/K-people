package it.webscience.kpeople.web.portlet.process.search;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

public class EventDisplayTerms extends DisplayTerms {
    
    public static final String PROCESS_ID = "processId";
    
    private String processId;
    
    public EventDisplayTerms(final PortletRequest portletRequest) {
        super(portletRequest);
        
        processId = ParamUtil.getString(portletRequest, PROCESS_ID);
        
        
    }
    
    public final String getProcessId(){
        return processId;
    }
    

}
