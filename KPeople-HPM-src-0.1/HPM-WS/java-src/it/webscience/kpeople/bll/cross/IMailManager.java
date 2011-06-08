package it.webscience.kpeople.bll.cross;

import it.webscience.kpeople.be.CommunicationEvent;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;

import java.util.List;

public interface IMailManager {

    /**
     * Servizio di invio mail.
     * @param email
     *            oggetto contenente le informazioni sulla mail.
     * @param processes
     *            array di processi associati alla mail
     * @param user
     *            utente che ha chiamato il servizio
     * @return messaggio di ritorno
     * @throws Exception
     *             errore generico
     */
    public abstract String sendMail(final CommunicationEvent email,
            final List<Process> processes, final User user) throws Exception;

    /**
     * Servizio di invio mail.
     * @param notification
     *            oggetto contenente le informazioni sulla mail.
     * @param processes
     *            array di processi associati alla mail
     * @param userBE
     *            utente che ha chiamato il servizio
     * @param patterns
     *            patterns associati al process
     * @throws Exception
     *             errore generico
     */
    public abstract void sendMailPattern(
            final CommunicationEvent email,
            final List<Process> processes, final User userBE,
            final List<Pattern> patterns,
            boolean sendWithKpeopleTag) throws Exception;

}