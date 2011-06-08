package it.webscience.kpeople.bll.proxy;

import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.ProcessFilter;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.process.IProcessDAO;
import it.webscience.kpeople.dal.process.ProcessDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Proxy per la classe ProcessDAO.
 */
public class ProcessDAOProxy implements IProcessDAO {

    /**
     * Restituisce gli utenti proprietari di almeno un processo.
     * @param user
     *            user che esegue la chiamata
     * @return elenco di User
     * @throws SQLException
     *             eccezione durante l'elaborazione
     */
    @Override
    public final List<User> getOwners(final User user) throws SQLException {

        ProcessDAO dao = new ProcessDAO();

        return dao.getOwners(user);
    }

    @Override
    public final List<Process> findProcesses(final ProcessFilter filter)
            throws KPeopleDAOException {

        ProcessDAO dao = new ProcessDAO();

        return dao.findProcesses(filter);
    }

    /**
     * Restituisce gli utenti abilitati ad un processo.
     * @param process
     *            processo da analizzare
     * @return elenco di User abilitati al processo
     * @throws KPeopleDAOException
     *             eccezione durante l'elaborazione
     */
    @Override
    public final List<User> findEnabledUsers(final Process process)
            throws KPeopleDAOException {

        ProcessDAO dao = new ProcessDAO();

        return dao.findEnabledUsers(process);
    }

    /**
     * Ritorna il processo avente lo specifico identificativo univoco.
     * @param hpmProcessId
     *            identificativo univoco del processo all'interno del sistema.
     * @return il processo corrispondente all'identificativo passato come
     *         parametro.
     * @throws SQLException
     *             eccezione generata a livello DAL
     */
    @Override
    public final Process getProcessByHpmId(final String hpmProcessId)
            throws SQLException {
        ProcessDAO dao = new ProcessDAO();

        return dao.getProcessByHpmId(hpmProcessId);
    }

    /**
     * Inserisce il processo all'interno del db hpm, del server solr e del
     * server sesame.
     * @param process
     *            processo che deve essere inserito
     * @param user
     *            utente che richiama il servizio.
     * @throws SQLException
     *             eccezione sql generata a livello dal.
     * @throws KPeopleDAOException
     *             eccezione generata a livello dal.
     * @return il processo creato.
     */
    public final Process setProcess(final Process process, final User user)
            throws SQLException, KPeopleDAOException {
        ProcessDAO dao = new ProcessDAO();
        return dao.setProcess(process, user);
    }

    /**
     * Aggiorna il processo all'interno del db hpm, ed eventualmente del server
     * solr e del server sesame.
     * @param process
     *            processo che deve essere inserito
     * @param user
     *            utente che richiama il servizio.
     * @throws SQLException
     *             eccezione sql generata a livello dal.
     * @throws KPeopleDAOException
     *             eccezione generata a livello dal.
     */
    public final void updateProcess(final Process process, final User user)
            throws SQLException, KPeopleDAOException {
        ProcessDAO dao = new ProcessDAO();
        dao.updateProcess(process, user);
    }

    /**
     * Setta lo stato "closed" per il processo corrente.
     * @param process
     *            processo che deve essere chiuso
     * @param user
     *            utente che richiede la chiusura del processo
     * @return un oggetto di tipo boolean (true se l'operazione di chiusura ha
     *         avuto esito positivo false altrimenti).
     * @throws KPeopleDAOException
     *             eccezione legata all'esecuzione del servizio.
     * @throws SQLException eccezione durante l'accesso al db.
     */
    public final boolean closeProcess(final Process process, final User user)
            throws KPeopleDAOException, SQLException {
        ProcessDAO dao = new ProcessDAO();
        return dao.closeProcess(process, user);
    }
}
