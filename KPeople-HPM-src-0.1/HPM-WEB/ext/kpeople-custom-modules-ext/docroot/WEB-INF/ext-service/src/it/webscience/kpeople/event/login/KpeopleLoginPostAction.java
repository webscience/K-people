package it.webscience.kpeople.event.login;

import it.webscience.kpeople.service.cross.UserServiceStub;
import it.webscience.kpeople.util.PropsKeys;
import it.webscience.kpeople.util.StubUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author bolognese
 */
public class KpeopleLoginPostAction extends Action {

    /**
     * @param request
     *            request
     * @param response
     *            response
     * @throws ActionException
     *             eccezione sulle action
     * @see com.liferay.portal.kernel.events.Action#run(javax.servlet.http.
     *      HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void run(final HttpServletRequest request,
            final HttpServletResponse response) throws ActionException {

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("KpeopleLoginPostAction START "
                        + request.getRemoteUser());
            }

            User liferayUser = PortalUtil.getUser(request);

            String userEmail = liferayUser.getEmailAddress();

            if (logger.isDebugEnabled()) {
                logger.debug("KpeopleLoginPostAction userEmail " + userEmail);
            }

            // chiamo lo userService
            String targetEndpoint = new StubUtil().buildTarget(
                    PropsKeys.WSO2SERVER_SERVICE_USER_ENDPOINT);

            UserServiceStub userStub = new UserServiceStub(targetEndpoint);
            UserServiceStub.GetUserByHpmUserId userSt =
                    new UserServiceStub.GetUserByHpmUserId();
            userSt.setHpmUserId(userEmail);
            UserServiceStub.GetUserByHpmUserIdResponse userResponse =
                    userStub.getUserByHpmUserId(userSt);
            UserServiceStub.User userDatatype = userResponse.get_return();

            liferayUser.setOpenId(String.valueOf(userDatatype.getIdUser()));

            UserLocalServiceUtil.updateUser(liferayUser);
            // metto lo user in sessione
            
            // session.setAttribute(KpeoplePortalConstants.HPM_CURRENT_USER,userDatatype);
            if (logger.isDebugEnabled()) {
                logger.debug("KpeopleLoginPostAction store user in session "
                        + userDatatype.getHpmUserId());
            }
            if (logger.isDebugEnabled()) {
                logger.debug("KpeopleLoginPostAction OVER " + userEmail);
            }

        } catch (Exception e) {
            throw new ActionException(e);
        }
    }

    /**
     * logger.
     */
    private static Log logger = LogFactoryUtil
            .getLog(KpeopleLoginPostAction.class);

}
