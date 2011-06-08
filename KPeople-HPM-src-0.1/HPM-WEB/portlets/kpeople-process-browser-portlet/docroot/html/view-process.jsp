<%@page import="javax.portlet.PortletSession"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="it.webscience.kpeople.service.processType.ProcessTypeServiceStub"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessDisplayTerms"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessSearchTerms"%>
<%@page import="it.webscience.kpeople.web.portlet.process.search.ProcessSearch"%>
<%@ include file="/html/init.jsp" %>

<%
    int risultatiPerPagina = 10;

    Log logger = LogFactoryUtil.getLog(this.getClass());

    String text = ParamUtil.getString(request,"text","");
    
    PortletURL portletURL = renderResponse.createRenderURL();
    portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);
    portletURL.setParameter("_spage", "/portlet_action/process-browser/view-process");
    
    ProcessSearch searchContainer = new ProcessSearch(renderRequest, portletURL);
    ProcessDisplayTerms displayTerms = (ProcessDisplayTerms)searchContainer.getDisplayTerms();
    
    searchContainer.setDelta(risultatiPerPagina);
    searchContainer.setDeltaConfigurable(false);
    searchContainer.setOrderByCol("");
    searchContainer.setOrderByType("");
    
    ProcessTypeServiceStub.ProcessType[] processTypes = (ProcessTypeServiceStub.ProcessType[]) request.getAttribute("processTypes");
    if (processTypes == null) {
    	processTypes = new ProcessTypeServiceStub.ProcessType[0]; 
    }
    ProcessServiceStub.User[] users = (ProcessServiceStub.User[]) request.getAttribute("users");
    if (users == null) {
        users = new ProcessServiceStub.User[0];
    }
    WindowState state = LiferayWindowState.EXCLUSIVE;
    
    String displayAdv = "none";
    if (displayTerms.getCmd().equals(ProcessBrowserConstants.ADVANCED_SEARCH_PROCESS)) {
        displayAdv = "block";
    }
    
    List<ProcessServiceStub.Process> results = new ArrayList<ProcessServiceStub.Process>();
%>

<portlet:renderURL var="searchURL">
    <portlet:param name="_spage" value="/portlet_action/process-browser/begin-search" />
</portlet:renderURL>

<div id="process-browser-portlet-simple-search">
    <form onsubmit="search_process();return;" action="<%= searchURL %>" method="post" name="<portlet:namespace />fm" id="<portlet:namespace />fm" >
        <input type="submit" value="invia" style="display: none;">
        <input type="hidden" 
               name="<%= Constants.CMD %>"
               id="<portlet:namespace/><%= Constants.CMD %>" 
               value="<%= displayTerms.getCmd() %>" />

        <!-- div ricerca avanzata -->
        <div>
            <div style="float: left;">
                <input type="text"
                       value="<%= displayTerms.getFreeText() %>" 
                       name="<%=ProcessDisplayTerms.FREE_TEXT %>" 
                       id="<portlet:namespace/><%=ProcessDisplayTerms.FREE_TEXT %>"/>
            </div>
            <div class="smplSubmit" onclick ="javascript:search_process()"></div>

            <div id="<portlet:namespace/>switchAdvBtn" class="switchBtn" style="display: <%=displayAdv.equals("none")?"block":"none" %>">      
                <a onclick="KPeople.ProcessSearch.switchAdv('<portlet:namespace/>');" class="button-style-viola button-style-viola-piu">
                    <span><liferay-ui:message key="find-processes.switch-advanced" /></span>
                </a>
            </div>
            <div id="<portlet:namespace/>switchSmplBtn" class="switchBtn" style="display: <%=displayAdv.equals("none")?"none":"block" %>;">  
                <a onclick="KPeople.ProcessSearch.switchSmpl('<portlet:namespace/>');" class="button-style-viola button-style-viola-meno">
                    <span><liferay-ui:message key="find-processes.switch-simple" /></span>
                </a>
            </div>
        </div>   
        <div style="clear: both;"></div>
        <div id="<portlet:namespace/>ricAvContainer" class="ricAvContainer" style="display: <%=displayAdv%>">
            <div class="resultsSeparator"></div>
            
            <div class="ordinaPerLblFilter">
                <liferay-ui:message key="find-processes.filters.order-by" />
            </div>
            <div class="dataLblFilter">
                <liferay-ui:message key="find-processes.filters.data" />
            </div>
            
            <div style="clear: both;"></div>
            
            <div class="ordinaPerFilter">
                <select name="<%=ProcessDisplayTerms.ORDINA_PER_COMBO%>">
                    <option <%=displayTerms.getOrdinaPerCombo().equals("ORDER_BY_LAST_UPDATE")?"selected='selected'":"" %> value="ORDER_BY_LAST_UPDATE"><liferay-ui:message key="find-processes.filters.order-by.last-update" /></option>
                    <option <%=displayTerms.getOrdinaPerCombo().equals("ORDER_BY_CREATION_DATE")?"selected='selected'":"" %> value="ORDER_BY_CREATION_DATE"><liferay-ui:message key="find-processes.filters.order-by.creation-date" /></option>
                    <option <%=displayTerms.getOrdinaPerCombo().equals("ORDER_BY_DUE_DATE")?"selected='selected'":"" %> value="ORDER_BY_DUE_DATE"><liferay-ui:message key="find-processes.filters.order-by.due-date" /></option>
                </select>
                <input <%=displayTerms.getOrdinaPerRadio().equals("ASC")?"checked='checked''":"" %> type='radio' name='<%=ProcessDisplayTerms.ORDINA_PER_RADIO%>' value='ASC'> ASC   
                <input <%=displayTerms.getOrdinaPerRadio().equals("DESC")?"checked='checked''":"" %> type='radio' name='<%=ProcessDisplayTerms.ORDINA_PER_RADIO%>' value='DESC'> DESC
            </div>
            <div class="dataFilter">
                <select name="<%=ProcessDisplayTerms.DATA_COMBO%>">
                    <option <%=displayTerms.getDataCombo().equals("CREATION_DATE")?"selected='selected'":"" %> value="CREATION_DATE"><liferay-ui:message key="find-processes.filters.data.creation-date" /></option>
                    <option <%=displayTerms.getDataCombo().equals("DUE_DATE")?"selected='selected'":"" %> value="DUE_DATE"><liferay-ui:message key="find-processes.filters.data.due-date" /></option>
                </select>
            </div>
            <div class="dataRange">
                <liferay-ui:message key="find-processes.filters.from" />

                <input type="text" 
                	   onblur="KPeople.ProcessSearch.checkDueDate('<portlet:namespace/><%=ProcessDisplayTerms.DATA_FROM%>');" 
                       name="<%=ProcessDisplayTerms.DATA_FROM%>"
                       id="<portlet:namespace/><%=ProcessDisplayTerms.DATA_FROM%>" 
                       value="<%= displayTerms.getDataFrom() %>" />
                
                <liferay-ui:message key="find-processes.filters.to" />
                
                <input type="text" 
                	   onblur="KPeople.ProcessSearch.checkDueDate('<portlet:namespace/><%=ProcessDisplayTerms.DATA_TO%>');" 
                       name="<%=ProcessDisplayTerms.DATA_TO %>"
                       id="<portlet:namespace/><%=ProcessDisplayTerms.DATA_TO %>" 
                       value="<%= displayTerms.getDataTo() %>" />
            </div>
            
            <div class="advFiltersSeparator"></div>
            
            <div class="gestitoDaLblFilter">
                <liferay-ui:message key="find-processes.filters.owner" />
            </div>
            <div class="tipoLblFilter">
                <liferay-ui:message key="find-processes.filters.type" />
            </div>
            <div class="mostraLblFilter">
                <liferay-ui:message key="find-processes.filters.show" />
            </div>
            
            <div style="clear: both;"></div>
            
            <div class="gestitoDaFilter">
                <select name="<%=ProcessDisplayTerms.GESTITO_DA %>">
                    <option value=""><liferay-ui:message key="find-processes.filters.choose-users" /></option>
                    <%
                        String idUser = displayTerms.getGestitoDa();

                        for (int i = 0; i < users.length; i++) {
                           String selected = "";
                           if (idUser.equals(users[i].getIdUser()+"")) {
                               selected = "selected=\"selected\"";
                           }

//                         seleziono l'utente corrente se sono al primo caricamento
//                         della pagina e nelle preferenze della portlet ho impostato onlyMyProcesses
                           if (request.getAttribute("onlyMyProcesses") != null) {
                               if (user.getOpenId().equals(users[i].getIdUser()+"")) {
                                   selected = "selected=\"selected\"";
                               }
                           }
                           
                           out.println(
                                "<option " + selected + " value=\"" + users[i].getIdUser() + "\">" + 
                                    users[i].getScreenName() +
                                "</option>"); 
                        }
                    %>
                </select>
            </div>

            <div class="tipoFilter">
                <select name="<%=ProcessDisplayTerms.TIPO %>">
                    <option value=""><liferay-ui:message key="find-processes.filters.all" /></option>
                    <%
                        String tipo = displayTerms.getTipo();
                        for (int i = 0; i < processTypes.length; i++) {
                            String selected = "";
                               if (tipo.equals(processTypes[i].getIdProcessType()+"")) {
                                   selected = "selected=\"selected\"";
                               }
                            
                            out.println(
                                "<option " + selected + " value=\"" + processTypes[i].getIdProcessType() + "\">" + 
                                LanguageUtil.get(pageContext, processTypes[i].getName()) +
                                "</option>"); 
                        }
                    %>
                </select>
            </div>
            
            <div class="mostraFilter">
                <span>
                    <input <%=displayTerms.getMostraAperti().equals("Y")?"checked='checked'":"" %> type="checkbox" value="Y" name="<%=ProcessDisplayTerms.MOSTRA_APERTI %>">
                    <liferay-ui:message key="find-processes.filters.show.open" />
                </span>
                <span>
                    <input <%=displayTerms.getMostraChiusi().equals("Y")?"checked='checked'":"" %> type="checkbox" value="Y" name="<%=ProcessDisplayTerms.MOSTRA_CHIUSI %>">
                    <liferay-ui:message key="find-processes.filters.show.closed" />
                </span> 
                <span style="display: none;">
                    <input <%=displayTerms.getMostraRiservati().equals("Y")?"checked='checked'":"" %> type="checkbox" value="Y" name="<%=ProcessDisplayTerms.MOSTRA_RISERVATI %>">
                    <liferay-ui:message key="find-processes.filters.show.reserved" />
                </span>
                <span>
                    <input <%=displayTerms.getMostraInTempo().equals("Y")?"checked='checked'":"" %> type="checkbox" value="Y" name="<%=ProcessDisplayTerms.MOSTRA_IN_TEMPO %>">
                    <liferay-ui:message key="find-processes.filters.show.in-time" />
                </span>
                <span>
                    <input <%=displayTerms.getMostraInRitardo().equals("Y")?"checked='checked'":"" %> type="checkbox" value="Y" name="<%=ProcessDisplayTerms.MOSTRA_IN_RITARDO %>">
                    <liferay-ui:message key="find-processes.filters.show.late" />
                </span>
            </div>
            
            <div style="clear: both;"></div>
        </div>
    </form>
</div>

<div id="process-browser-portlet-advanced-search">
</div>

<div style="clear: both;height: 10px;"></div>

<div id="process-browser-portlet-search-result">
    <%

    	results = (List<ProcessServiceStub.Process>)renderRequest.getAttribute(ProcessBrowserConstants.ATTR_PROCESSES_FOUND);
    
        if (results != null) {
            ProcessSearchTerms searchTerms = (ProcessSearchTerms)searchContainer.getSearchTerms();
            
//          imposto totale risultati ed elenco
            searchContainer.setTotal(results.size());
            searchContainer.setResults(results);

            List<ResultRow> resultRows = searchContainer.getResultRows();
            for (int i = searchContainer.getStart(); i < Math.min(searchContainer.getEnd(), results.size()) ; i++) {
                ProcessServiceStub.Process process = (ProcessServiceStub.Process)results.get(i);
        
                ResultRow row = new ResultRow(process, process.getHpmProcessId(), i);
                row.setClassName("yy");
                row.setClassHoverName("yy");
                row.addJSP("/html/row_details.jsp");
    
                resultRows.add(row);
            }
            
            request.setAttribute("liferay-ui:page-iterator:url", null);
            %>

        <webscience-ui:asynchronous-search-iterator 
        		divId="process-browser-portlet-process-box"
        		formId="<%= renderResponse.getNamespace() + "fm"%>"
        		searchContainer="<%= searchContainer %>"/>

    <%}
    %>
</div>


<script type="text/javascript">

function <portlet:namespace />executeCommand(cmd) {

	document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;
	submitForm(document.<portlet:namespace />fm);
}


jQuery(
	function() {
		$("#<portlet:namespace/><%=ProcessDisplayTerms.DATA_FROM%>").datepicker({dateFormat: 'dd/mm/yy'});
		$("#<portlet:namespace/><%=ProcessDisplayTerms.DATA_TO%>").datepicker({dateFormat: 'dd/mm/yy'});
	}
);
</script>