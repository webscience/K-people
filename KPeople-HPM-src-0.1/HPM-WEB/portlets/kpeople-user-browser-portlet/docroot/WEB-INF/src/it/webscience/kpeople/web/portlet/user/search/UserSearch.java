package it.webscience.kpeople.web.portlet.user.search;

import it.webscience.kpeople.service.process.ProcessServiceStub.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;

public class UserSearch extends SearchContainer<User> {
    static List<String> headerNames = new ArrayList<String>();
    static Map<String, String> orderableHeaders = new HashMap<String, String>();

//  static {
//          headerNames.add("");
//          headerNames.add("title");
//          headerNames.add("description");
//          headerNames.add("modified-date");
//          headerNames.add("display-date");
//          headerNames.add("author");

//          orderableHeaders.put("id", "id");
//          orderableHeaders.put("name", "title");
//          orderableHeaders.put("version", "version");
//          orderableHeaders.put("modified-date", "modified-date");
//          orderableHeaders.put("display-date", "display-date");
//  }

    public static final String EMPTY_RESULTS_MESSAGE =
            "no-user-were-found";

    public UserSearch(
            PortletRequest portletRequest, PortletURL iteratorURL) {

            super(
                    portletRequest, new UserDisplayTerms(portletRequest),
                    new UserSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
                    DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

            PortletConfig portletConfig =
                    (PortletConfig)portletRequest.getAttribute(
                            JavaConstants.JAVAX_PORTLET_CONFIG);

            UserDisplayTerms displayTerms = (UserDisplayTerms)getDisplayTerms();
            UserSearchTerms searchTerms = (UserSearchTerms)getSearchTerms();

            
//          salvataggio dei parametri per la paginazione
            iteratorURL.setParameter(UserDisplayTerms.ORDINA_PER_SELECT, displayTerms.getOrdinaPerSelect());
            iteratorURL.setParameter(UserDisplayTerms.PROCESS_ID, displayTerms.getProcessId());
            
  
            
//          try {
//                  PortalPreferences preferences =
//                          PortletPreferencesFactoryUtil.getPortalPreferences(
//                                  portletRequest);
//
//                  String orderByCol = ParamUtil.getString(
//                          portletRequest, "orderByCol");
//                  String orderByType = ParamUtil.getString(
//                          portletRequest, "orderByType");
//
//                  if (Validator.isNotNull(orderByCol) &&
//                          Validator.isNotNull(orderByType)) {
//
//                          preferences.setValue(
//                                  PortletKeys.JOURNAL, "articles-order-by-col", orderByCol);
//                          preferences.setValue(
//                                  PortletKeys.JOURNAL, "articles-order-by-type", orderByType);
//                  }
//                  else {
//                          orderByCol = preferences.getValue(
//                                  PortletKeys.JOURNAL, "articles-order-by-col", "id");
//                          orderByType = preferences.getValue(
//                                  PortletKeys.JOURNAL, "articles-order-by-type", "asc");
//                  }
//
//                  OrderByComparator orderByComparator =
//                          JournalUtil.getArticleOrderByComparator(
//                                  orderByCol, orderByType);
//
//                  setOrderableHeaders(orderableHeaders);
//                  setOrderByCol(orderByCol);
//                  setOrderByType(orderByType);
//                  setOrderByComparator(orderByComparator);
//          }
//          catch (Exception e) {
//                  _log.error(e);
//          }
    }

    private static Log _log = LogFactoryUtil.getLog(UserSearch.class);

}