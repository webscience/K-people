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

package it.webscience.kpeople.event.login;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.LastPath;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Giorgio Chiriacò
 */
public class KpeoplePrivatePageRedirectAction extends Action {

    public void run(HttpServletRequest request, HttpServletResponse response)
            throws ActionException {

        try {
            doRun(request, response);
        } catch (Exception e) {
            throw new ActionException(e);
        }
    }

    protected void doRun(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        long companyId = PortalUtil.getCompanyId(request);

        String path =
                PrefsPropsUtil
                        .getString(
                                companyId,
                                PropsKeys.LAYOUT_FRIENDLY_URL_PRIVATE_USER_SERVLET_MAPPING);

        path = path + "/" + PortalUtil.getUser(request).getScreenName();

        path =
                path
                        + PrefsPropsUtil
                                .getString(
                                        companyId,
                                        PropsKeys.DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL);

        _log.info(String.format("PrivatePageRedirectAction, path=%s...", path));

        if (_log.isInfoEnabled()) {
            _log.info("Private page path: " + StringPool.EQUAL + path);
        }

        if (Validator.isNotNull(path)) {
            LastPath lastPath =
                    new LastPath(StringPool.BLANK, path,
                            new HashMap<String, String[]>());

            HttpSession session = request.getSession();
            request.setAttribute(WebKeys.LAST_PATH, lastPath);
            session.setAttribute(WebKeys.LAST_PATH, lastPath);
        }
    }

    private static Log _log = LogFactoryUtil
            .getLog(KpeoplePrivatePageRedirectAction.class);

}
