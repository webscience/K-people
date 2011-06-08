package it.webscience.kpeople.dal.process;

import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.ProcessFilter;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Interfaccia per la classe DAO relativa ai processi.
 */
public interface IProcessDAO {

    /** Formatter per date di tipo sql. */
    DateFormat SQL_DATE_FORMATTER = new SimpleDateFormat("yyyy-mm-dd");

    /**
     * Restituisce gli utenti proprietari di almeno un processo.
     * @param user
     *            user che esegue la chiamata
     * @return elenco di User
     * @throws SQLException
     *             eccezione durante l'elaborazione
     */
    List<User> getOwners(final User user) throws SQLException;

    /**
     * Ricerca di uno specifico processo mediante il suo identificativo univoco.
     * @param hpmProcessId
     *            identificativo univoco del processo all'interno del sistema.
     * @return il processo corrispondente all'identificativo passato come
     *         parametro.
     * @throws SQLException
     *             eccezione generata a livello DAL
     */
    Process getProcessByHpmId(final String hpmProcessId) throws SQLException;

    /**
     * Ricerca dei processi.
     * @param filter
     *            parametri di ricerca
     * @return lista dei processi trovati
     * @throws KPeopleDAOException
     *             eccezione di livello DAL
     */
    List<Process> findProcesses(final ProcessFilter filter)
            throws KPeopleDAOException;

    /**
     * Restituisce gli utenti abilitati ad un processo.
     * @param process
     *            processo da analizzare
     * @return elenco di User abilitati al processo
     * @throws KPeopleDAOException
     *             eccezione durante l'elaborazione
     */
    List<User> findEnabledUsers(final Process process)
            throws KPeopleDAOException;

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
    Process setProcess(final Process process, final User user)
            throws SQLException, KPeopleDAOException;

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
    void updateProcess(final Process process, final User user)
            throws SQLException, KPeopleDAOException;

    /**
     * @param process
     *            processo che deve essere chiuso
     * @param user
     *            utente che richiede la chiusura del processo
     * @return un oggetto di tipo boolean (true se l'operazione di chiusura ha
     *         avuto esito positivo false altrimenti).
     * @throws SQLException
     *             eccezione legata all'esecuzione del servizio.
     * @throws KPeopleDAOException
     *             eccezione legata all'esecuzione del servizio.
     */
    boolean closeProcess(final Process process, final User user)
            throws SQLException, KPeopleDAOException;
}
