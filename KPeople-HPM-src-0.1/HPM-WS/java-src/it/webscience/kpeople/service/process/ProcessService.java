package it.webscience.kpeople.service.process;

import it.webscience.kpeople.be.CommunicationEvent;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.ProcessManager;
import it.webscience.kpeople.bll.impl.SearchManager;
import it.webscience.kpeople.bll.impl.cross.MailManager;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.service.datatypes.Process;
import it.webscience.kpeople.service.datatypes.ProcessFilter;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.datatypes.converter.ProcessConverter;
import it.webscience.kpeople.service.datatypes.converter.ProcessFilterConverter;
import it.webscience.kpeople.service.datatypes.converter.UserConverter;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Servizio per la gestione dei processi.
 */
public class ProcessService {
    /** logger. */
    private Logger logger;

    /**
     * istanza della classe singleton per la lettura delle proprietà di
     * configurazione.
     */
    private Singleton singleton;

    /**
     * Costruttore di default.
     */
    public ProcessService() {
        logger = Logger.getLogger(this.getClass().getName());

        singleton = Singleton.getInstance();
    }

    /*
     * public final void sendEgaChannelEvent( final Document document, final
     * Process process, final String actionReference, final User user) throws
     * KPeopleServiceException { if (logger.isDebugEnabled()) {
     * logger.debug("sendEgaChannelEvent - Begin"); }
     * it.webscience.kpeople.be.Document documentBE =
     * DocumentConverter.toBE(document); it.webscience.kpeople.be.Process
     * processBE = ProcessConverter.toBE(process); it.webscience.kpeople.be.User
     * userBE = UserConverter.toBE(user); ProcessManager mng = new
     * ProcessManager(); try { mng.callEgaChannelSender(documentBE, processBE,
     * actionReference, userBE); } catch (KPeopleBLLException e) {
     * e.printStackTrace(); throw new KPeopleServiceException(e.getMessage()); }
     * if (logger.isDebugEnabled()) { logger.debug("sendEgaChannelEvent - end");
     * } }
     */
    /**
     * Restituisce gli utenti proprietari di almeno un processo.
     * @param user
     *            user che esegue la chiamata
     * @return elenco di User
     * @throws KPeopleServiceException
     *             eccezione durante l'elaborazione
     */
    public final User[] getOwners(final User user)
            throws KPeopleServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("getOwners - Begin");
        }

        ProcessManager mng = new ProcessManager();

        User[] users = null;
        try {
            // it.webscience.kpeople.be.User userBe = UserConverter.toBE(user);

            List<it.webscience.kpeople.be.User> usersBe = mng.getOwners(null);
            users = UserConverter.toService(usersBe);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getOwners - End. Owners trovati: " + users.length);
        }

        return users;
    }

    /**
     * Metodo per la ricerca dei processi.
     * @param filter
     *            criteri di ricerca dei processi
     * @param user
     *            utente che effettua la richiesta
     * @return lista dei processi che soddisfano i criteri di ricerca
     * @throws KPeopleServiceException
     *             eccezione durante l'elaborazione
     */
    public final Process[] findProcesses(final ProcessFilter filter,
            final User user) throws KPeopleServiceException {
        List<it.webscience.kpeople.be.Process> processBE = null;

        logger.debug("findProcesses - begin");

        SearchManager mng = new SearchManager();

        it.webscience.kpeople.be.ProcessFilter filterBE =
                ProcessFilterConverter.toBE(filter);

        it.webscience.kpeople.be.User userBE = UserConverter.toBE(user);

        try {
            processBE = mng.findProcesses(filterBE, userBE);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        Process[] processes = ProcessConverter.toService(processBE);

        logger.debug("findPRocesses - end");
        logger.debug("Processi trovati: " + processes.length);

        return processes;
    }

    /**
     * Metodo per il recupero dei dettagli di processo.
     * @param user
     *            utente che effettua la richiesta.
     * @param hpmProcessId
     *            identificativo univoco di un processo all'interno del sistema.
     * @return Process oggetto di tipo process corrispondente all'identificativo
     *         fornito.
     * @throws KPeopleServiceException
     *             eccezione legata all'esecuzione del servizio.
     */
    public final Process getProcessByHpmId(final String hpmProcessId,
            final User user) throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("getProcessByHpmId - begin");
        }
        it.webscience.kpeople.be.Process processBE = null;

        ProcessManager mng = new ProcessManager();

        it.webscience.kpeople.be.User userBE = UserConverter.toBE(user);

        try {
            processBE = mng.getProcessByHpmId(hpmProcessId, userBE);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        Process process = ProcessConverter.toService(processBE);

        if (logger.isDebugEnabled()) {
            logger.debug("getProcessByHpmId - end");
        }

        return process;
    }

    /**
     * Metodo per l'inserimento di un nuovo processo.
     * @param process
     *            processo che deve essere memorizzato
     * @param user
     *            utente che effettua l'inserimento
     * @throws KPeopleServiceException
     *             eccezione legata all'esecuzione del servizio.
     * @return il processo creato.
     */
    public final Process
            saveProcessInfo(final Process process, final User user)
                    throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("addProcess - start");
        }
        it.webscience.kpeople.be.Process newProcessBE = null;
        it.webscience.kpeople.be.Process processBE =
                ProcessConverter.toBE(process);
        it.webscience.kpeople.be.User userBE = UserConverter.toBE(user);

        ProcessManager mng = new ProcessManager();

        try {
            newProcessBE = mng.addProcess(processBE, userBE);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        Process result = ProcessConverter.toService(newProcessBE);
        if (logger.isDebugEnabled()) {
            logger.debug("addProcess - end");
        }
        return result;

    }

    /**
     * Metodo per l'inserimento di un nuovo processo.
     * @param process
     *            processo che deve essere memorizzato
     * @param user
     *            utente che effettua l'inserimento
     * @throws KPeopleServiceException
     *             eccezione legata all'esecuzione del servizio.
     */
    public final void updateProcess(final Process process, final User user)
            throws KPeopleServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("updateProcess - begin");
        }
        it.webscience.kpeople.be.Process processBE =
                ProcessConverter.toBE(process);
        it.webscience.kpeople.be.User userBE = UserConverter.toBE(user);

        ProcessManager mng = new ProcessManager();

        try {
            mng.updateProcess(processBE, userBE);
        } catch (KPeopleBLLException e) {
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("updateProcess - end");
        }
    }

    /**
     * @param process
     *            processo che deve essere chiuso
     * @param user
     *            utente che richiede la chiusura del processo
     * @return un oggetto di tipo boolean (true se l'operazione di chiusura ha
     *         avuto esito positivo false altrimenti).
     * @throws KPeopleServiceException
     *             eccezione legata all'esecuzione del servizio.
     */
    public final boolean closeProcess(final Process process, final User user)
            throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("closeProcess - begin");
        }

        boolean updateResult = false;

        it.webscience.kpeople.be.Process inProcessBE =
                ProcessConverter.toBE(process);
        it.webscience.kpeople.be.User userBE = UserConverter.toBE(user);

        // to do: PermissionManager per il controllo dei permessi dell'utente.

        String hpmProcessId = inProcessBE.getHpmProcessId();

        // verifica che il processo non sia gia' chiuso.
        Process currProcess = this.getProcessByHpmId(hpmProcessId, user);

        inProcessBE = ProcessConverter.toBE(currProcess);

        if (inProcessBE.isActive()) {
            ProcessManager mng = new ProcessManager();

            try {
                updateResult = mng.closeProcess(inProcessBE, userBE);
            } catch (KPeopleBLLException e) {
                e.printStackTrace();
                throw new KPeopleServiceException(e.getMessage());
            }

            if (updateResult) {

                User[] enabledUsers = this.findEnabledUsers(process);

                if (enabledUsers.length != 0) {
                    sendEmailNotification(inProcessBE, enabledUsers, userBE);
                }

            }

        } else {
            throw new KPeopleServiceException("Il processo e gia' chiuso");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("closeProcess - end");
        }

        return updateResult;
    }

    /**
     * @param process
     *            per il quale si vuole ottenere l'elenco degli users abilitati.
     * @return Array degli users abilitati per il processo fornito in input.
     * @throws KPeopleServiceException
     *             eccezione generata durante l'esecuzione.
     */
    public final User[] findEnabledUsers(final Process process)
            throws KPeopleServiceException {

        List<it.webscience.kpeople.be.User> listUserBE = null;

        logger.info("findEnabledUsers - begin");

        SearchManager mng = new SearchManager();

        it.webscience.kpeople.be.Process processBE =
                ProcessConverter.toBE(process);

        try {
            listUserBE = mng.findEnabledUsers(processBE);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        User[] users = UserConverter.toService(listUserBE);

        logger.info("findEnabledUsers - end");

        return users;
    }

    /**
     * Metodo per l'invio della email di notifica di chiusura processo. Il
     * metodo è invocato se esistono utenti abilitati sul processo.
     * @param process
     *            processo che è stato chiuso.
     * @param enabledUsers
     *            lista degli utenti abilitati.
     * @param userBE
     *            utente che ha richiesto la chiusura del processo.
     * @throws KPeopleServiceException
     *             eccezione generica del livello BLL.
     */
    private void sendEmailNotification(
            final it.webscience.kpeople.be.Process process,
            final User[] enabledUsers,
            final it.webscience.kpeople.be.User userBE)
            throws KPeopleServiceException {

        CommunicationEvent notification = new CommunicationEvent();
        notification.setBody(singleton.getProperty("carbon.mail-service.body")
                + " " + process.getName());

        notification.setObject(singleton
                .getProperty("carbon.mail-service.object"));

        List<String> emailTo = new ArrayList<String>();

        for (User currUser : enabledUsers) {
            String email = currUser.getHpmUserId();
            emailTo.add(email);
        }

        // aggiunta degli utenti 'to'
        List<it.webscience.kpeople.be.User> usersTo =
                new ArrayList<it.webscience.kpeople.be.User>();
        for (String tmp : emailTo) {
            it.webscience.kpeople.be.User userTo =
                    new it.webscience.kpeople.be.User();

            userTo.setEmail(tmp);

            usersTo.add(userTo);
        }
        notification.setToUser(usersTo);

        // aggiunta dell'email FROM
        it.webscience.kpeople.be.User userFrom =
                new it.webscience.kpeople.be.User();
        userFrom.setEmail(process.getOwner().getHpmUserId());
        notification.setUserFrom(userFrom);

        MailManager mailService = new MailManager();

        List<it.webscience.kpeople.be.Process> processes =
                new ArrayList<it.webscience.kpeople.be.Process>();
        processes.add(process);

        try {
            mailService.sendMail(notification, processes, userBE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

    }

}
