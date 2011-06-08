package it.webscience.kpeople.dal.process;

import java.sql.SQLException;

import it.webscience.kpeople.be.ProcessMetadataSet;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

/**
 * @author depascalis Interfaccia per la classe ProcessMetadataDAO relativa ai
 *         metadati associati al processo.
 */
public interface IProcessMetadataDAO {

    /**
     * @param user user che effettua la chiamata
     * @return elenco dei metadati relativi al processo
     * @throws KPeopleDAOException eccezione generata durante l'esecuzione.
     * @throws SQLException 
     */
    ProcessMetadataSet getMetadataProcess(User user) throws KPeopleDAOException, SQLException;

}
