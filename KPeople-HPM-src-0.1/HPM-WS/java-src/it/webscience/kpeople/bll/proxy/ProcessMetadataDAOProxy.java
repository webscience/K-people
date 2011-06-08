package it.webscience.kpeople.bll.proxy;

import java.sql.SQLException;

import it.webscience.kpeople.be.ProcessMetadataSet;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.process.IProcessMetadataDAO;
import it.webscience.kpeople.dal.process.ProcessMetadataDAO;

/**
 * @author depascalis
 * Proxy per la classe ProcessMetadataDAO.
 */
public class ProcessMetadataDAOProxy implements IProcessMetadataDAO {

    
    public final ProcessMetadataSet getMetadataProcess(final User user)
            throws SQLException {
        ProcessMetadataDAO dao = new ProcessMetadataDAO();
        return dao.getMetadataProcess(user);
    }
}
