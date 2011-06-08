/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved. This library is
 * free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 2.1 of the License, or (at your option) any later
 * version. This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 */

package it.webscience.kpeople.web.portlet.report.action;

import it.webscience.kpeople.event.login.KpeopleUserUtil;
import it.webscience.kpeople.service.cross.UserServiceStub;
import it.webscience.kpeople.service.cross.UserServiceStub.User;
import it.webscience.kpeople.service.process.ProcessServiceStub;
import it.webscience.kpeople.service.process.ProcessServiceStub.Process;
import it.webscience.kpeople.service.processType.ProcessTypeServiceStub;
import it.webscience.kpeople.service.processType.ProcessTypeServiceStub.ProcessType;
import it.webscience.kpeople.util.KpeoplePortletConfigurationUtil;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 */
public class ViewAction extends Action {

    /** logger. */
    private static Log logger = LogFactoryUtil.getLog(ViewAction.class);

    private int currUserId;

    /**
     * <p>
     * Process the specified non-HTTP request, and create the corresponding
     * non-HTTP response (or forward to another web component that will create
     * it), with provision for handling exceptions thrown by the business logic.
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param req
     *            The non-HTTP request we are processing
     * @param res
     *            The non-HTTP response we are creating
     * @exception Exception
     *                if the application business logic throws an exception.
     * @return action forward
     */
    @Override
    public final ActionForward execute(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws Exception {

        return this.render(mapping, form, req, res);
    }

    /**
     * metodo render.
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param req
     *            The non-HTTP request we are processing
     * @param res
     *            The non-HTTP response we are creating
     * @return action forward
     * @throws SystemException
     * @throws PortalException
     */
    private ActionForward render(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest req,
            final HttpServletResponse res) throws PortalException,
            SystemException {

        String src = getSrc(req);

        String hpmUserId = KpeopleUserUtil.getCurrUserHpmId(req);
        currUserId = KpeopleUserUtil.getCurrUserId(req);

        String hpmProcessId = ParamUtil.getString(req, "processId");

        PortletRequest aReq =
                (PortletRequest) req
                        .getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

        PortletPreferences preferences = aReq.getPreferences();

        String widthChart = preferences.getValue("widthChart", "");
        String heightChart = preferences.getValue("heightChart", "");

        String actionValue =
                preferences
                        .getValue(
                                "reportType",
                                ReportBrowserConstants
                                    .CHART_COMMUNICAZIONI_DESTINATARIO);

        if (actionValue
                .equalsIgnoreCase(ReportBrowserConstants
                        .CHART_COMMUNICAZIONI_DESTINATARIO)
                || actionValue
                        .equalsIgnoreCase(ReportBrowserConstants
                                .CHART_COMMUNICAZIONI_MITTENTE)) {

            try {
                
                popolaForm(req);

                String ordinaPerRadio =
                        ParamUtil.getString(req,
                                ReportBrowserConstants.ORDINA_PER_RADIO);
                String idProcessType = null;
                if (Validator.isNotNull(ordinaPerRadio)) {
                    if (ordinaPerRadio
                            .equals(ReportBrowserConstants.FILTER_BY_TYPE)) {
                        idProcessType =
                                ParamUtil.getString(req,
                                        ReportBrowserConstants.TYPE);
                        if (idProcessType.equals(ReportBrowserConstants.ALL)) {
                            idProcessType = null;
                        }
                    }

                    if (ordinaPerRadio
                            .equals(ReportBrowserConstants.FILTER_BY_PROCESS)) {
                        hpmProcessId =
                                ParamUtil.getString(req,
                                        ReportBrowserConstants.PROCESS);
                        if (hpmProcessId.equals(ReportBrowserConstants.ALL)) {
                            hpmProcessId = null;
                        }

                    }

                    hpmUserId = ParamUtil.getString(req,
                            ReportBrowserConstants.USER);


                }
                src += actionValue + "&nameUser=" + hpmUserId;
                if (Validator.isNotNull(hpmProcessId)) {
                    src += "&hpmProcessId=" + hpmProcessId;
                }

                if (Validator.isNotNull(idProcessType)) {
                    src += "&idProcessType=" + idProcessType;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (actionValue
                .equalsIgnoreCase(ReportBrowserConstants.KPI_PROCESS_XACTION)) {
            
            if (Validator.isNotNull(ParamUtil.getString(req, ReportBrowserConstants.FILTER_BY_KPI))) {
                src += ParamUtil.getString(req, ReportBrowserConstants.FILTER_BY_KPI);
            } else {
                src += actionValue;    
            }
        }

        if (actionValue
                .equalsIgnoreCase(ReportBrowserConstants
                        .PIVOT_PROCESS_XACTION) || 
            actionValue
                .equalsIgnoreCase(ReportBrowserConstants
                        .PIVOT_COMMUNICATION_XACTION)) {
            src += actionValue;
            src += "&userid=anonym&password=anonym";
        }

        if (Validator.isNotNull(widthChart)) {
            src += "&widthChart=" + widthChart;
        }

        if (Validator.isNotNull(heightChart)) {
            src += "&heightChart=" + heightChart;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("REPORT render src param: " + src);
        }

        req.setAttribute("REPORT_SRC", src);

        if (logger.isInfoEnabled()) {
            logger.info("REPORT render src: " + src);
        }
        return mapping.findForward("/report/view");
    }


    private void popolaForm(HttpServletRequest req) throws Exception {

        ProcessTypeServiceStub.ProcessType[] processTypes =
            (ProcessType[]) req.getSession().getAttribute("processTypes");

        if (Validator.isNull(processTypes)) {
            setProcessTypes(req);
        }

        Process[] processes = (Process[]) req.getSession()
            .getAttribute(ReportBrowserConstants.ATTR_PROCESSES_FOUND);
        if (Validator.isNull(processes)) {
            getAllProcess(req);
        }
        
        UserServiceStub.User[] users =
            (User[]) req.getSession().getAttribute(ReportBrowserConstants.ALL_USERS);

        if (Validator.isNull(users)) {
            getAllUsers(req);
        }

    }

    /**
     * metodo che legge il src dalle preferences.
     * @param HttpServletRequest
     *            the portlet renderRequest
     * @return string src
     */
    protected final String getSrc(final HttpServletRequest req) {

        String src = new KpeoplePortletConfigurationUtil().getBiSrc();

        return src;
    }

    private void setProcessTypes(final HttpServletRequest req) throws Exception {

        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys
                                .WSO2SERVER_SERVICE_PROCESS_TYPE_ENDPOINT);

        // chiamata al web service: recupero dei tipi di processo
        int idUser = currUserId;
        ProcessTypeServiceStub.User ptUser = new ProcessTypeServiceStub.User();
        ptUser.setIdUser(idUser);

        ProcessTypeServiceStub.GetProcessTypes processTypeSend =
                new ProcessTypeServiceStub.GetProcessTypes();
        processTypeSend.setUser(ptUser);
        ProcessTypeServiceStub.ProcessType[] processTypes =
                new ProcessTypeServiceStub(targetEndpoint).getProcessTypes(
                        processTypeSend).get_return();
        //req.setAttribute("processTypes", processTypes);
        req.getSession().setAttribute("processTypes", processTypes);
    }

    private void getAllProcess(final HttpServletRequest req) throws Exception {
        
        String targetEndpoint =
                new StubUtil()
                        .buildTarget(PropsKeys
                                .WSO2SERVER_SERVICE_PROCESS_ENDPOINT);

        int idUser = currUserId;
        Process[] results = null;

        ProcessServiceStub.User psUser = new ProcessServiceStub.User();
        psUser.setIdUser(idUser);

        String freeText = "";

        ProcessServiceStub.FindProcesses findProcess =
                new ProcessServiceStub.FindProcesses();

        ProcessServiceStub.ProcessFilter filter =
                new ProcessServiceStub.ProcessFilter();
        filter.setFreeText(freeText);

        /** ordinamento default nel caso di ricerca semplice */
        filter.setSort(new ProcessServiceStub.SortCriteria());
        filter.getSort().setFieldName("ORDER_BY_LAST_UPDATE");
        filter.getSort().setOrder("DESC");

        findProcess.setFilter(filter);
        findProcess.setUser(psUser);

        ProcessServiceStub stub = new ProcessServiceStub(targetEndpoint);
        ProcessServiceStub.FindProcessesResponse response =
                stub.findProcesses(findProcess);

        if (logger.isDebugEnabled()) {
            logger.debug("Ricerca semplice. Filter: " + filter);
            logger.debug("Processi trovati: " + response.get_return().length);
        }

        results = response.get_return();
        //req.setAttribute(ReportBrowserConstants.ATTR_PROCESSES_FOUND, results);
        req.getSession().setAttribute(
                ReportBrowserConstants.ATTR_PROCESSES_FOUND, results);
    }

    
    /**
     * Chiama il servizio per il recupero degli utenti da LDAP.
     * @return elenco utenti
     * @throws Exception eccezione durante la chiamata del servizio
     */
    private void getAllUsers(final HttpServletRequest req) throws Exception {
        String targetEndpoint = new StubUtil().buildTarget(
                PropsKeys.WSO2SERVER_SERVICE_USER_ENDPOINT);

        UserServiceStub userStub = new UserServiceStub(targetEndpoint);

        UserServiceStub.GetUserByUsername sendParameter =
            new UserServiceStub.GetUserByUsername();
        sendParameter.setUser(new UserServiceStub.User());
 
        UserServiceStub.GetUserByUsernameResponse response =
            userStub.getUserByUsername(sendParameter);
        UserServiceStub.User[] users = response.get_return();

        req.getSession().setAttribute(
                ReportBrowserConstants.ALL_USERS, users);

    }
}
