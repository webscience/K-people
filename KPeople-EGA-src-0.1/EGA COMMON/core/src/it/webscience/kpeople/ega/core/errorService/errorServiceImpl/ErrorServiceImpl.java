package it.webscience.kpeople.ega.core.errorService.errorServiceImpl;

import it.webscience.kpeople.ega.core.errorService
.errorServiceInterface.ErrorService;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Properties;

/**
 * @author XPMUser
 */
public class ErrorServiceImpl implements ErrorService {

    /**
     * Nome del server SMTP.
     */
    private static final String SMTP_HOST_NAME = "mail.kpeople.webscience.it";
    /**
     * Username del mittente.
     */
    private static final String SMTP_AUTH_USER = "kpeopleadmin@kpeople.webscience.it";

    /**
     * Password del mittente.
     */
    private static final String SMTP_AUTH_PWD = "Password1";

    /**
     * Oggetto della mail di notifica errore.
     */
    private static final String EMAIL_SUBJECT_TEXT = "error";

    /**
     * Indirizzo del mittente.
     */
    private static final String EMAIL_FROM_ADDRESS = "kpeopleadmin@kpeople.webscience.it";

    /**
     * Lista di indirizzi email ai quali inviare la mail di segnalazione errore.
     */
    private static final String[] EMAIL_LIST = {"filieri@kpeople.webscience.it"};

    /**
     * Costruttore della classe.
     */
    public ErrorServiceImpl() {
    }

    /**
     * @param e
     *            eccezione generata.
     */
    public final void sendErrorNotification(final Exception e) {
        ByteArrayOutputStream exception = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(exception);
        e.printStackTrace(ps);
        String content = exception.toString();

        try {
            postMail(EMAIL_LIST, EMAIL_SUBJECT_TEXT,
                    content, EMAIL_FROM_ADDRESS);
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
        System.out.println("Sucessfully Sent mail to Administrator");

    }

    /**
     * @param recipients
     *            rappresenta la lista di destinatari.
     * @param subject
     *            rappresenta l'oggetto dell'email.
     * @param message
     *            rappresenta il contenuto della mail.S
     * @param from
     *            rappresenta il mittente della mail.
     * @throws MessagingException
     *             see javax.mail.MessagingException
     */
    public final void postMail(final String[] recipients, final String subject,
            final String message, final String from) throws MessagingException {
        boolean debug = false;

        // Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(props, auth);

        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }

    /**
     * SimpleAuthenticator is used to do simple authentication when the SMTP
     * server requires it.
     */
    private class SMTPAuthenticator extends javax.mail.Authenticator {

        /**
         * @return new PasswordAuthentication.
         */
        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }

}
