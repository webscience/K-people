package it.webscience.kpeople.bll.proxy;

import java.util.List;

import it.webscience.kpeople.dal.cross.ISearchDAO;
import it.webscience.kpeople.dal.cross.SearchDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

/**
 * Proxy per la classe SearchDAO.
 * @author danieletramis
 *
 */
public class SearchDAOProxy implements ISearchDAO {

    @Override
    public final List<Object> find(final String freeText)
        throws KPeopleDAOException {
        ISearchDAO dao = new SearchDAO();

        return dao.find(freeText);
    }

}
