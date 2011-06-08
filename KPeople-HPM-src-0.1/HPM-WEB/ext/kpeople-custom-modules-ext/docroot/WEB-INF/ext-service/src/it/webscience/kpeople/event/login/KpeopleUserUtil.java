package it.webscience.kpeople.event.login;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

public class KpeopleUserUtil {
    /**
     * Ritorna l'user id dell'utente connesso.
     * @param request
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getCurrUserId(
            final HttpServletRequest request)
            throws PortalException, SystemException {

        User liferayUser = PortalUtil.getUser(request);
 
        String userId = liferayUser.getOpenId();

        if (Validator.isNotNull(liferayUser) && Validator.isNull(userId)) {
            KpeopleLoginPostAction instance = new KpeopleLoginPostAction();
            instance.run(request, null);
            userId = liferayUser.getOpenId();
        }
        return  Integer.parseInt(userId);
    }

    /**
     * Ritorna l'hpm user id dell'utente connesso.
     * @param request
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static String getCurrUserHpmId(
            final HttpServletRequest request)
            throws PortalException, SystemException {

        User liferayUser = PortalUtil.getUser(request);

        String hpmUserId = liferayUser.getEmailAddress();

        return hpmUserId;
    }
}
