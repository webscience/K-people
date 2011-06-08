package it.webscience.kpeople.web.portlet.user.action;

import it.webscience.kpeople.service.process.ProcessServiceStub;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;
import it.webscience.kpeople.web.portlet.user.util.UserBrowserConstants;
import it.webscience.kpeople.web.portlet.user.util.UserByLastnameComparator;
import it.webscience.kpeople.web.portlet.user.util.UserByNameComparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserEnabledAction extends Action {
    
    /** logger. */
    private static Log logger =
        LogFactoryUtil.getLog(UserEnabledAction.class);
    
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
            String targetEndpoint = new StubUtil().buildTarget(
                    PropsKeys.WSO2SERVER_SERVICE_PROCESS_ENDPOINT);
            
                String par = (String)req.getAttribute("select");
                String paramOrder = ParamUtil.getString(req, "select");
                //Id del processo del quale voglio conoscere gli utenti coinvolti
                String hpmProcessId = ParamUtil.getString(req, "processid");
                //String paramOrder = "Cognome";
                ProcessServiceStub.Process process = new ProcessServiceStub.Process();
                process.setHpmProcessId(hpmProcessId);
                ProcessServiceStub stub = new ProcessServiceStub(targetEndpoint);
                ProcessServiceStub.FindEnabledUsers findEnabledUsers = new ProcessServiceStub.FindEnabledUsers();
                findEnabledUsers.setProcess(process);
                ProcessServiceStub.FindEnabledUsersResponse response = stub.findEnabledUsers(findEnabledUsers);
                //ProcessServiceStub.User[] users = getAllUsers();
                //ProcessServiceStub.User[] users = ProcessServiceStub.
                List<ProcessServiceStub.User> listUsers = null;
                if (response.get_return() != null) {
	                listUsers = Arrays.asList(response.get_return());
	                if (paramOrder.equalsIgnoreCase("Nome")) {
	                    Collections.sort( listUsers, new UserByNameComparator() );
	                }
	                else {
	                    Collections.sort(listUsers, new UserByLastnameComparator());
	                }
                }
//                users = (ProcessServiceStub.User[])listUsers.toArray();
                req.setAttribute(UserBrowserConstants.ENABLED_USERS, listUsers);
                req.setAttribute("processid", hpmProcessId);

            }catch (Exception e) {
                e.printStackTrace();
            }
            return mapping.findForward("/user-browser/begin-search");
    }

//    private ProcessServiceStub.User[] getAllUsers(){
//        ProcessServiceStub.User[] user = new ProcessServiceStub.User[3];
//        ProcessServiceStub.User user1 = new ProcessServiceStub.User();
//        user1.setFirstName("Fabio");
//        user1.setLastName("Dell'Anna");
//        user1.setHpmUserId("dellanna@webscience.it");
//        user[0]= user1;
//        ProcessServiceStub.User user2 = new ProcessServiceStub.User();
//        user2.setFirstName("Antonio");
//        user2.setLastName("Spalluto");
//        user2.setHpmUserId("spalluto@webscience.it");
//        user[1]= user2;
//        ProcessServiceStub.User user3 = new ProcessServiceStub.User();
//        user3.setFirstName("Antonella");
//        user3.setLastName("Filieri");
//        user3.setHpmUserId("fiieri@webscience.it");
//        user[2]= user3;
//        return user;
//    }
    
} 
