package it.webscience.kpeople.web.portlet.process.action;

import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.process.ProcessServiceStub;
import it.webscience.kpeople.service.process.ProcessServiceStub.Process;
import it.webscience.kpeople.service.processType.ProcessTypeServiceStub;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.process.action.converter.ProcessConverter;
import it.webscience.kpeople.web.portlet.process.search.ProcessDisplayTerms;
import it.webscience.kpeople.web.portlet.process.util.ProcessBrowserConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * Struts action per la ricerca dei processi.
 */
public class SearchProcessAction extends Action {

    /** logger. */
    private static Log logger =
        LogFactoryUtil.getLog(SearchProcessAction.class);

    /**
     * <p>Process the specified non-HTTP request, and create the
     * corresponding non-HTTP response (or forward to another web
     * component that will create it), with provision for handling
     * exceptions thrown by the business logic.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param req The non-HTTP request we are processing
     * @param res The non-HTTP response we are creating
     *
     * @exception Exception if the application business logic throws
     * an exception.
     * @return action forward
     */
    @Override
    public final ActionForward execute(
            final ActionMapping mapping,
            final ActionForm form,
            final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("execute");
            }

            PortletRequest aReq = (PortletRequest)
                req.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
            if (aReq != null) {
                String command = ParamUtil.getString(req, Constants.CMD);

                if (Validator.isNull(command)) {
                    command = ProcessBrowserConstants.SIMPLE_SEARCH_PROCESS;
                }

                if (ProcessBrowserConstants.SIMPLE_SEARCH_PROCESS.equals(
                        command)) {
                    return processActionSimpleSearch(mapping, form, req, res);
                } else if (ProcessBrowserConstants.
                        ADVANCED_SEARCH_PROCESS.equals(command)) {
                    return processActionAdvancedSearch(mapping, form, req, res);
                }
            }
            return processAction(mapping, form, req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Restituisce la view della portlet.
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param req The non-HTTP request we are processing
     * @param res The non-HTTP response we are creating
     * @return action forward
     * @throws Exception if the application business logic throws
     * an exception.
     */
    private ActionForward processAction(
            final ActionMapping mapping,
            final ActionForm form,
            final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("processAction");
        }

        try {
            PortletRequest aReq = (PortletRequest)
                req.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
            if (aReq != null) {
                popolaCombo(aReq, req);

//              ricerca avanzata di default in base al 'process-filter'
//              recupero della portlet preference per filtro su utente
                PortletPreferences pp = aReq.getPreferences();
                String processFilter = pp.getValue("process-filter", "");
                if (processFilter.equals("onlyMyProcesses")) {
                    req.setAttribute("onlyMyProcesses", "Y"); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapping.findForward("/process-browser/view-process");
    }

    /**
     * esegue la ricerca semplice dei processi.
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param req The non-HTTP request we are processing
     * @param res The non-HTTP response we are creating
     * @return action forward
     * @throws Exception if the application business logic throws
     * an exception.
     */
    private ActionForward processActionSimpleSearch(
            final ActionMapping mapping,
            final ActionForm form,
            final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("processActionSimpleSearch");
        }

        PortletRequest aReq = (PortletRequest)
            req.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

        if (aReq != null) {

//          contenitore dei risultati della ricerca.
//          viene popolato dalla sessione (in caso di cambio pagina)
            Process[] results = null;

            String cur = ParamUtil.getString(req, "cur");

            if (!cur.isEmpty()) {
//              recupero della ricerca dalla sessione

                results = (Process[]) aReq.getPortletSession().getAttribute(
                        ProcessBrowserConstants.ATTR_PROCESSES_FOUND);
            } else {
                ProcessServiceStub.User psUser = new ProcessServiceStub.User();
                psUser.setIdUser(KpeopleUserUtil.getCurrUserId(req));

                String freeText = ParamUtil.getString(
                        req, ProcessDisplayTerms.FREE_TEXT);

                ProcessServiceStub.FindProcesses findProcess =
                    new ProcessServiceStub.FindProcesses();

                ProcessServiceStub.ProcessFilter filter =
                    new ProcessServiceStub.ProcessFilter();
                filter.setFreeText(freeText);

//              recupero della portlet preference per filtro su utente
                PortletPreferences pp = aReq.getPreferences();
                String processFilter = pp.getValue("process-filter", "");
                if (processFilter.equals("") || 
                        processFilter.equals("onlyMyProcesses")) {
                    filter.setUserId(KpeopleUserUtil.getCurrUserId(req) + "");
                }

                /** ordinamento default nel caso di ricerca semplice */
                filter.setSort(new ProcessServiceStub.SortCriteria());
                filter.getSort().setFieldName("ORDER_BY_LAST_UPDATE");
                filter.getSort().setOrder("DESC");

                findProcess.setFilter(filter);
                findProcess.setUser(psUser);

                String targetEndpoint = new StubUtil().buildTarget(
                        PropsKeys.WSO2SERVER_SERVICE_PROCESS_ENDPOINT);

                ProcessServiceStub stub =
                    new ProcessServiceStub(targetEndpoint);
                ProcessServiceStub.FindProcessesResponse response =
                    stub.findProcesses(findProcess);

                if (logger.isDebugEnabled()) {
                    logger.debug(
                          "Ricerca semplice. Filter: " + filter);
                    logger.debug(
                          "Processi trovati: " + response.get_return().length);
                }

                results = response.get_return();

//              copia in sessione i risultati della ricerca
                aReq.getPortletSession().setAttribute(
                        ProcessBrowserConstants.ATTR_PROCESSES_FOUND,
                        results);
            }

//          inserisce nella request i risultati della ricerca
            aReq.setAttribute(
                    ProcessBrowserConstants.ATTR_PROCESSES_FOUND,
                    ProcessConverter.toBE(results));

            sendEvent(req, "process.search", results);

            popolaCombo(aReq, req);
        }
        return mapping.findForward("/process-browser/view-process");
    }

    /**
     * ritorna l'oggetto User da inviare ai web services.
     * @param req The non-HTTP request we are processing
     * @return oggetto User
     * @throws Exception eccezione durante il reucpero dell'user id
     */
    private ProcessServiceStub.User buildUser(
            final HttpServletRequest req) throws Exception {

        ProcessServiceStub.User psUser = new ProcessServiceStub.User();
        psUser.setIdUser(KpeopleUserUtil.getCurrUserId(req));

        return psUser;
    }

    /**
     * Costruisce l'oggetto ProcessFilter da inviare ai web services.
     * Utilizzato nella ricerca avanzata.
     * @param req The non-HTTP request we are processing
     * @return oggetto ProcessFilter
     */
    private ProcessServiceStub.ProcessFilter buildProcessFilter(
            final HttpServletRequest req) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

//      costruisco il filter
        ProcessServiceStub.ProcessFilter filter =
            new ProcessServiceStub.ProcessFilter();

        filter.setFreeText(
                ParamUtil.getString(req, ProcessDisplayTerms.FREE_TEXT));
        filter.setUserId(
                ParamUtil.getString(req, ProcessDisplayTerms.GESTITO_DA));
        filter.setType(
                ParamUtil.getString(req, ProcessDisplayTerms.TIPO));

//      filtro data DA/A
        String dataMode =
            ParamUtil.getString(req, ProcessDisplayTerms.DATA_COMBO);
        Date dataFrom =
            ParamUtil.getDate(req, ProcessDisplayTerms.DATA_FROM, format, null);
        Date dataTo =
            ParamUtil.getDate(req, ProcessDisplayTerms.DATA_TO, format, null);
        if (dataMode.equals("CREATION_DATE")) {
            filter.setCreationDateFrom(dataFrom);
            filter.setCreationDateTo(dataTo);
        } else {
            filter.setDueDateFrom(dataFrom);
            filter.setDueDateTo(dataTo);
        }

//      recupero degli stati (aperto/chiuso)
        List<Integer> statesList = new ArrayList<Integer>();
        if (ParamUtil.getString(
                req, ProcessDisplayTerms.MOSTRA_APERTI).equals("Y")) {
            statesList.add(1);
        }
        if (ParamUtil.getString(
                req, ProcessDisplayTerms.MOSTRA_CHIUSI).equals("Y")) {
            statesList.add(2);
        }
        int[] states = new int[statesList.size()];
        for (int i = 0; i < statesList.size(); i++) {
            states[i] = statesList.get(i).intValue();
        }
        filter.setState(states);

//      recupero degli altri checkbox
        filter.setShowReserved(ParamUtil.getBoolean(
                req, ProcessDisplayTerms.MOSTRA_RISERVATI));
        filter.setShowInTime(ParamUtil.getBoolean(
                req, ProcessDisplayTerms.MOSTRA_IN_TEMPO));
        filter.setShowLate(ParamUtil.getBoolean(
                req, ProcessDisplayTerms.MOSTRA_IN_RITARDO));

//        i filtri "in tempo" ed "in ritardo" sono esclusivi e vanno
//        puliti nel caso si selezionano entrambi
        if (filter.getShowInTime() && filter.getShowLate()) {
            filter.setShowInTime(false);
            filter.setShowLate(false);
        }

//      ordinamento per + ASC/DESC
        ProcessServiceStub.SortCriteria sc =
            new ProcessServiceStub.SortCriteria();
        sc.setFieldName(
                ParamUtil.getString(req, ProcessDisplayTerms.ORDINA_PER_COMBO));
        sc.setOrder(
                ParamUtil.getString(req, ProcessDisplayTerms.ORDINA_PER_RADIO));
        filter.setSort(sc);

        return filter;
    }

    /**
     * esegue la ricerca avanzata dei processi.
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param req The non-HTTP request we are processing
     * @param res The non-HTTP response we are creating
     * @return action forward
     * @throws Exception if the application business logic throws
     * an exception.
     */
    private ActionForward processActionAdvancedSearch(
            final ActionMapping mapping,
            final ActionForm form,
            final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("processActionAdvancedSearch");
        }

        PortletRequest aReq = (PortletRequest)
            req.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

        if (aReq != null) {
            ProcessServiceStub.FindProcesses findProcess =
                new ProcessServiceStub.FindProcesses();
            findProcess.setFilter(buildProcessFilter(req));
            findProcess.setUser(buildUser(req));

            String targetEndpoint = new StubUtil().buildTarget(
                    PropsKeys.WSO2SERVER_SERVICE_PROCESS_ENDPOINT);

            ProcessServiceStub stub = new ProcessServiceStub(targetEndpoint);
            ProcessServiceStub.FindProcessesResponse response =
                stub.findProcesses(findProcess);

            aReq.setAttribute(
                ProcessBrowserConstants.ATTR_PROCESSES_FOUND,
                ProcessConverter.toBE(response.get_return()));

            popolaCombo(aReq, req);
        }
        return mapping.findForward("/process-browser/view-process");
    }

    /**
     * Inserisce nella request i valori delle combo presenti nella pagina.
     * @param aReq The portlet request we are processing
     * @param req The portlet request we are processing
     * @throws Exception if the application business logic throws
     * an exception.
     */
    private void popolaCombo(
            final PortletRequest aReq,
            final HttpServletRequest req)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("popolaCombo");
        }

        String processTypeTargetEndpoint = new StubUtil().buildTarget(
                PropsKeys.WSO2SERVER_SERVICE_PROCESS_TYPE_ENDPOINT);

//      recupero dell'utente loggato
        int idUser = KpeopleUserUtil.getCurrUserId(req);

//      chiamata al web service: recupero dei tipi di processo
        ProcessTypeServiceStub.User ptUser = new ProcessTypeServiceStub.User();
        ptUser.setIdUser(idUser);
        ProcessTypeServiceStub.GetProcessTypes processTypeSend =
            new ProcessTypeServiceStub.GetProcessTypes();
        processTypeSend.setUser(ptUser);
        ProcessTypeServiceStub.ProcessType[] processTypes =
            new ProcessTypeServiceStub(processTypeTargetEndpoint)
                .getProcessTypes(processTypeSend).get_return();
        aReq.setAttribute("processTypes", processTypes);

//      recupero gli owners di processo
        String processTargetEndpoint = new StubUtil().buildTarget(
                PropsKeys.WSO2SERVER_SERVICE_PROCESS_ENDPOINT);

        ProcessServiceStub.User psUser = new ProcessServiceStub.User();
        psUser.setIdUser(idUser);
        ProcessServiceStub.GetOwners ownersReq =
            new ProcessServiceStub.GetOwners();
        ownersReq.setUser(psUser);
        ProcessServiceStub.User[] owners =
            new ProcessServiceStub(processTargetEndpoint).getOwners(
                ownersReq).get_return();
        aReq.setAttribute("users", owners);
    }

    /**
     * genera un evento e invia i processi.
     * @param req The non-HTTP request we are processing
     * @param string Nome dell'evento
     * @param processes
     */
    private void sendEvent(HttpServletRequest req, String event, Process[] processes) {
        QName qName = new QName("http://kpeople.it/events", event);
        
        PortletResponse aReq = (PortletResponse) req.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);
        
        if (aReq instanceof ActionResponse ) {
//            String[] hpm_process_ids = new String[processes.length];
//            int i = 0;
//            for (Process p : processes) {
//                hpm_process_ids[i++] = p.getHpmProcessId();
//            }
            ((ActionResponse)aReq).setEvent(qName , processes);
        }
    }
    
//    @ProcessAction(name="simple-search")
//	public void processAction(ActionRequest request, ActionResponse response)
//			throws PortletException, IOException {
//		
//		//super.processAction(request, response);
//		
//		String[] test = new String[1];
//		
//		test[0] = "Prova";
//		
//		QName qName = new QName("http://liferay.com/events", "ipc.pitch");
//		response.setEvent(qName, test);
//	}
    
}
