package it.webscience.kpeople.service.cross;

import it.webscience.kpeople.bll.impl.cross.MailManager;
import it.webscience.kpeople.service.datatypes.CommunicationEvent;
import it.webscience.kpeople.service.datatypes.Process;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.datatypes.converter.CommunicationEventConverter;
import it.webscience.kpeople.service.datatypes.converter.ProcessConverter;
import it.webscience.kpeople.service.datatypes.converter.UserConverter;

import java.util.List;

/**
 * Servizio di invio mail.
 */
public class MailService {

    /**
     * Invia la mail allegando l'attachment kpeople.xml.
     * @param email oggetto contenente le informazioni sulla mail.
     * @param processes array di processi associati alla mail
     * @param user user che esegue la chiamata
     * @return messaggio di ritorno
     * @throws Exception errore generico
     */
    public final String sendMailV1(
            final CommunicationEvent email,
            final Process[] processes,
            final User user)
    throws Exception {

//      Traduco i tipi dati da Service a DAO
        it.webscience.kpeople.be.User userBE = UserConverter.toBE(user);
        it.webscience.kpeople.be.CommunicationEvent emailBe =
            CommunicationEventConverter.toBE(email);
        List<it.webscience.kpeople.be.Process> processesBe =
            ProcessConverter.toBE(processes);

//      chiamata al BLL
        MailManager ms = new MailManager();
        ms.sendMail(emailBe, processesBe, userBE);

        return null;
    }

    /**
     * Invia la mail senza allegato kpeople.
     * @param email oggetto contenente le informazioni sulla mail.
     * @param user user che esegue la chiamata
     * @return messaggio di ritorno
     * @throws Exception errore generico
     */
    public final String sendSimpleMailV1(
            final CommunicationEvent email,
            final User user) throws Exception {

        try {
    //      Traduco i tipi dati da Service a DAO
            it.webscience.kpeople.be.User userBE = UserConverter.toBE(user);
            it.webscience.kpeople.be.CommunicationEvent emailBe =
                CommunicationEventConverter.toBE(email);

    //      chiamata al BLL
            MailManager ms = new MailManager();
            ms.sendSimpleMail(emailBe, userBE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
