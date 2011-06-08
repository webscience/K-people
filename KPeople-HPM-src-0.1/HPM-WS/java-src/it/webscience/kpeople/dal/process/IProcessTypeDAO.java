package it.webscience.kpeople.dal.process;

import it.webscience.kpeople.be.ProcessType;
import it.webscience.kpeople.be.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia per la classe DAO relativa ai tipi di processi.
 */
public interface IProcessTypeDAO {
    /**
     * Restituisce gli utenti proprietari di almeno un processo.
     * @param user user che esegue la chiamata
     * @return elenco di User
     * @throws SQLException eccezione durante l'elaborazione
     */
    List<ProcessType> getProcessTypes(final User user)
            throws SQLException;

}
