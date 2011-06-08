package it.webscience.kpeople.bll;

import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.ProcessType;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;

import java.util.List;

/**
 * Interfaccia del manager Process.
 */
public interface IProcessManager {

    /**
     * Restituisce gli utenti proprietari di almeno un processo.
     * @param user
     *            user che esegue la chiamata
     * @return elenco di User
     * @throws KPeopleBLLException
     *             eccezione durante l'elaborazione
     */
    List<User> getOwners(final User user) throws KPeopleBLLException;

    /**
     * @param hpmProcessId
     *            identificativo univoco del processo all'interno del sistema.
     * @param user
     *            riferimento all'utente che richiama il servizio.
     * @return istanza di classe Process corrispondente al processo avente l'id
     *         specificato (hpmProcessId).
     * @throws KPeopleBLLException
     *             eccezione genenerata durante l'esecuzione della logica di
     *             business.
     */
    Process getProcessByHpmId(final String hpmProcessId, final User user)
            throws KPeopleBLLException;

    /**
     * @param process
     *            riferimento al processo in fase di inserimento.
     * @param user
     *            riferimento all'utente che richiama il servizio.
     * @throws KPeopleBLLException
     *             eccezione genenerata durante l'esecuzione della logica di
     *             business.
     * @return il processo creato.
     */
    Process addProcess(final Process process, final User user)
            throws KPeopleBLLException;

    /**
     * @param process
     *            riferimento al processo in fase di inserimento.
     * @param user
     *            riferimento all'utente che richiama il servizio.
     * @throws KPeopleBLLException
     *             eccezione genenerata durante l'esecuzione della logica di
     *             business.
     */
    void updateProcess(final Process process, final User user)
            throws KPeopleBLLException;

    /**
     * @param process
     *            processo che deve essere chiuso
     * @param user
     *            utente che richiede la chiusura del processo
     * @return un oggetto di tipo boolean (true se l'operazione di chiusura ha
     *         avuto esito positivo false altrimenti).
     * @throws KPeopleBLLException
     *             eccezione legata all'esecuzione del servizio.
     */
    boolean closeProcess(final Process process, final User user)
            throws KPeopleBLLException;
}
