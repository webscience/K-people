package it.webscience.kpeople.bll.proxy;

import it.webscience.kpeople.be.ProcessType;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.process.IProcessTypeDAO;
import it.webscience.kpeople.dal.process.ProcessTypeDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Proxy per la classe ProcessTypeDAO.
 */
public class ProcessTypeDAOProxy implements IProcessTypeDAO {

    /**
     * Restituisce l'elenco completo dei ProcessType.
     * @param user user che esegue la chiamata
     * @return elenco di ProcessType
     * @throws SQLException eccezione durante l'elaborazione
     */
    public final List<ProcessType> getProcessTypes(final User user)
            throws SQLException {

        ProcessTypeDAO dao = new ProcessTypeDAO();

        return dao.getProcessTypes(user);
    }
}
