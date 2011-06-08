package it.webscience.kpeople.bll.impl.cross;

import it.webscience.kpeople.be.CommunicationEvent;
import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.Keyword;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.cross.IMailManager;
import it.webscience.kpeople.bll.exception.KPeopleMailServiceDomainException;
import it.webscience.kpeople.bll.exception.KPeopleMailServiceKeywordNotFoundException;
import it.webscience.kpeople.bll.exception.KPeopleMailServicePatternNotFoundException;
import it.webscience.kpeople.bll.exception.KPeopleMailServiceProcessNotFoundException;
import it.webscience.kpeople.bll.exception.KPeopleMailServiceSendMailException;
import it.webscience.kpeople.dal.Singleton;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.xml.stream.XMLStreamException;

import org.apache.log4j.Logger;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * Web service per l'invio di una mail.
 */
public class MailManager implements IMailManager {
    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /* (non-Javadoc)
     * @see it.webscience.kpeople.bll.cross.IMailService#sendMail(it.webscience.kpeople.be.CommunicationEvent, java.util.List, it.webscience.kpeople.be.User)
     */
    @Override
    public final String sendMail(final CommunicationEvent email,
            final List<Process> processes, final User user) throws Exception {

        // 1. verifica che tutti i destinatari sono presenti in LDAP
        for (User emailTo : email.getToUser()) {
            boolean test = testDominioDestinatario(emailTo.getEmail());
            if (!test) {
                throw new KPeopleMailServiceDomainException("La mail "
                        + emailTo + " non è presente nel dominio");
            }
        }

        if (processes == null || processes.size() == 0) {
            throw new KPeopleMailServiceProcessNotFoundException();
        }

        // 2. verifica che il process ha almeno 1 keyword
        for (Process process : processes) {
            List<Keyword> keys = process.getKeywords();
            if (keys == null || keys.size() == 0) {
                throw new KPeopleMailServiceKeywordNotFoundException();
            }
        }

        // invio della mail
        try {
            sendMail(email, processes);
        } catch (Exception e) {
            throw new KPeopleMailServiceSendMailException(e.getMessage());
        }

        return "";
    }

    /**
     * Invia la mail senza allegato Kpeople.
     * @param email oggetto Email
     * @param user utente che invia la mail
     * @throws Exception eccezione durante l'invio della mail
     */
    public final void sendSimpleMail(
            final CommunicationEvent email,
            final User user) throws Exception  {
        if (logger.isDebugEnabled()) {
            logger.debug("sendSimpleMail - BEGIN");
        }

        Singleton singleton = Singleton.getInstance();
        Multipart multipart = new MimeMultipart();

        // default mail session
        Session session =
                Session.getDefaultInstance(buildProperties(singleton),
                        new SMTPAuthenticator());
        session.setDebug(false);

        // bodypart del corpo della mail
        BodyPart contentBp = new MimeBodyPart();
        contentBp.setText(email.getBody());
        multipart.addBodyPart(contentBp);

        // aggiungo gli attachments della mail
        addAttachments(email, multipart);

        // creazione dell'oggetto Message
        String emailAddress = email.getUserFrom().getEmail();

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailAddress));
        msg.setRecipients(Message.RecipientType.TO, getRecipientsTo(email));
        msg.setSubject(email.getObject());
        msg.setContent(multipart);
        msg.setSentDate(new Date());

        // invio della mail
        Transport.send(msg);

        if (logger.isDebugEnabled()) {
            logger.debug("sendSimpleMail - END");
        }
    }

    /**
     * Invia la mail.
     * @param email
     *            oggetto Email
     * @param processes
     *            array di processi associati alla mail
     * @throws MessagingException
     *             problemi con l'invio della mail
     * @throws XMLStreamException
     *             probleli con la creazione dell'attachment
     * @throws IOException
     *             eccezione nella generazione dell'xml
     */
    private void sendMail(
            final CommunicationEvent email,
            final List<Process> processes) throws XMLStreamException,
            MessagingException, IOException {
        Singleton singleton = Singleton.getInstance();
        Multipart multipart = new MimeMultipart();

        // default mail session
        Session session =
                Session.getDefaultInstance(buildProperties(singleton),
                        new SMTPAuthenticator());
        session.setDebug(false);

        // bodypart del corpo della mail
        BodyPart contentBp = new MimeBodyPart();
        contentBp.setText(email.getBody());
        multipart.addBodyPart(contentBp);

        // aggiungo gli attachments della mail
        addAttachments(email, multipart);

        // aggiungo l'attachment kpeople
        addKpeopleAttachment(processes, multipart);

        // creazione dell'oggetto Message
        String emailAddress = email.getUserFrom().getEmail();

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailAddress));
        msg.setRecipients(Message.RecipientType.TO, getRecipientsTo(email));
        msg.setSubject(email.getObject());
        msg.setContent(multipart);
        msg.setSentDate(new Date());

        // invio della mail
        Transport.send(msg);
    }

    /**
     * Effettua una query ldap per verificare l'esistenza della email
     * all'interno del dominio.
     * @param email
     *            email da cercare
     * @return <b>true</b>: se l'email cercata esiste all'interno del dominio<br>
     *         <b>false</b>: se l'email cercata non esiste all'interno del
     *         dominio<br>
     * @throws NamingException
     *             if a naming exception is encountered
     */
    private boolean testDominioDestinatario(final String email)
            throws NamingException {

        Singleton singleton = Singleton.getInstance();
        boolean testOk = true;

        boolean flagOn =
                Boolean.parseBoolean(singleton
                        .getProperty("carbon.mail-service.test-dominio-destinatario"));

        if (flagOn) {
            // 1. controllo del dominio
            String emailDomain = email.split("@")[1];
            String ldapDomain = singleton.getProperty("openldap.domain");
            if (!emailDomain.equalsIgnoreCase(ldapDomain)) {
                testOk = false;
            }

            // 2. controllo dell'esistenza della mail all'interno del domino
            List<String> results =
                    queryLdapServer("(&(mail={0}))", new Object[] { email });
            if (results.size() == 0) {
                testOk = false;
            }
        }

        return testOk;
    }

    /**
     * @param filterExpr
     *            the filter expression to use for the search
     * @param filterArgs
     *            the array of arguments to substitute for the variables in
     *            filterExpr
     * @return risultato della ricerca su ldap
     * @throws NamingException
     *             if a naming exception is encountered
     */
    private NamingEnumeration<SearchResult> ldapSearch(final String filterExpr,
            final Object[] filterArgs) throws NamingException {
        String contextName =
                Singleton.getInstance().getProperty("openldap.context-name");

        SearchControls ctls = new SearchControls();
        ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> answer =
                getDirContext().search(contextName, filterExpr, filterArgs,
                        ctls);
        return answer;
    }

    /**
     * interroga il server LDAP.
     * @param filterExpr
     *            the filter expression to use for the search
     * @param filterArgs
     *            the array of arguments to substitute for the variables in
     *            filterExpr
     * @return elenco di distinguishedName
     * @throws NamingException
     *             if a naming exception is encountered
     */
    private List<String> queryLdapServer(final String filterExpr,
            final Object[] filterArgs) throws NamingException {

        List<String> out = new ArrayList<String>();

        NamingEnumeration<SearchResult> answer =
                ldapSearch(filterExpr, filterArgs);

        while (answer.hasMoreElements()) {
            SearchResult sr = answer.nextElement();
            Attributes attributes = sr.getAttributes();
            Attribute attr = attributes.get("distinguishedName");

            NamingEnumeration<?> vals = attr.getAll();
            while (vals.hasMoreElements()) {
                Object val = (Object) vals.nextElement();
                out.add(val.toString());
            }
        }

        return out;
    }

    /**
     * restituisce in context per la connessione al server LDAP.
     * @return oggetto DirContext
     * @throws NamingException
     *             if a naming exception is encountered
     */
    private DirContext getDirContext() throws NamingException {
        Singleton singleton = Singleton.getInstance();

        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");

        env.put(Context.PROVIDER_URL,
                singleton.getProperty("openldap.provider-url"));

        env.put(Context.SECURITY_PRINCIPAL,
                singleton.getProperty("openldap.security-principal"));

        env.put(Context.SECURITY_CREDENTIALS,
                singleton.getProperty("openldap.security-credentials"));

        return new InitialDirContext(env);
    }

    /**
     * Aggiunge l'attachment kpeople alla mail da inviare.
     * @param processes
     *            lista di Process associati alla mail
     * @param multipart
     *            Multipart da inviare
     * @throws MessagingException
     *             eccezione durante l'aggiunta del BodyPart
     * @throws IOException
     *             eccezione nella generazione dell'xml
     */
    private void addKpeopleAttachment(final List<Process> processes,
            final Multipart multipart) throws MessagingException, IOException {

        // costruzione del Document DOM4J
        org.dom4j.Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("MetaDataSet").addAttribute("xmlns:xsi",
                        "http://www.w3.org/2001/XMLSchema-instance");

        for (Process process : processes) {
            Element compoundMetadata =
                    root.addElement("CompoundMetadata")
                            .addAttribute("Key", process.getHpmProcessId())
                            .addAttribute("Description",
                                    process.getDescription());

            for (Keyword keyword : process.getKeywords()) {
                Element metadata =
                        compoundMetadata.addElement("Metadata")
                                .addAttribute("Key", keyword.getKeyword())
                                .addAttribute("Description", "");

                metadata.addElement("value")
                        .addAttribute("value", keyword.getHpmKeywordId())
                        .addAttribute("description", "");
            }
        }

        // pretty printing dell'xml su String
        StringWriter stringWriter = new StringWriter();
        XMLWriter xmlWriter =
                new XMLWriter(stringWriter, OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        String xml = stringWriter.toString();

        String fileName =
                Singleton.getInstance().getProperty(
                        "carbon.mail-service.kpeople-attachment-filename");

        BodyPart bp = new MimeBodyPart();
        bp.setText(xml);
        bp.setFileName(fileName);
        multipart.addBodyPart(bp);
    }

    /**
     * Aggiunge gli attachments presenti nella mail.
     * @param email
     *            mail
     * @param multipart
     *            MimeMultipart: contenuto della mail
     * @throws XMLStreamException
     *             generazione dell'xml MTOM
     * @throws MessagingException
     *             problema nell'aggiunta del bodypart
     */
    private void addAttachments(final CommunicationEvent email,
            final Multipart multipart) throws XMLStreamException,
            MessagingException {
        for (Document doc : email.getDocList()) {
            DataHandler dh = getDataHandler(doc.getData());

            BodyPart attachmentBp2 = new MimeBodyPart();
            attachmentBp2.setDataHandler(dh);
            attachmentBp2.setFileName(doc.getFileName());
            multipart.addBodyPart(attachmentBp2);
        }
    }

    /**
     * recupera i destinatari della mail.
     * @param email
     *            oggetto Email
     * @throws AddressException
     *             eccezione nella crezione dell'InternetAddress
     * @return destinatati della mail
     */
    private InternetAddress[] getRecipientsTo(final CommunicationEvent email)
            throws AddressException {
        // destinatari della mail
        InternetAddress[] recipients =
                new InternetAddress[email.getToUser().size()];

        for (int i = 0; i < email.getToUser().size(); i++) {
            recipients[i] =
                    new InternetAddress(email.getToUser().get(i).getHpmUserId());
        }

        return recipients;
    }

    /**
     * Costruisce l'oggetto Properties per l'invio della mail.
     * @param singleton
     *            singleton
     * @return oggetto Properties
     */
    private Properties buildProperties(final Singleton singleton) {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host",
                singleton.getProperty("carbon.mail-service.smtp-host"));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        return props;
    }

    /**
     * converte il documento MTOM in un DataHandler.
     * @param data
     *            binario del documento
     * @return DataHandler associato al file
     * @throws XMLStreamException
     *             eccezione nell'analisi dell'MTOM
     */
    private DataHandler getDataHandler(final byte[] data)
            throws XMLStreamException {

        ByteArrayDataSource ds = new ByteArrayDataSource(
                data,
                "application/octet-stream");

        return new DataHandler(ds);
    }


    /**
     * Servizio di invio pattern.
     */
    public void sendMailPattern(
            final CommunicationEvent email,
            final List<Process> processes, final User userBE,
            final List<Pattern> patterns,
            boolean sendWithKpeopleTag) throws Exception {

        User userFrom = email.getUserFrom();
        if (userFrom != null) {
            boolean test = testDominioDestinatario(userFrom.getHpmUserId());
            if (!test) {
                throw new KPeopleMailServiceDomainException("La mail "
                        + userFrom.getHpmUserId()
                        + " non è presente nel dominio");
            }
        }

        List<User> usersCc = email.getCcUser();
        // 1. verifica che tutti i destinatari sono presenti in LDAP
        if (usersCc != null) {
            for (User emailCc : usersCc) {
                boolean test = testDominioDestinatario(emailCc.getHpmUserId());
                if (!test) {
                    throw new KPeopleMailServiceDomainException("La mail "
                            + emailCc.getHpmUserId()
                            + " non è presente nel dominio");
                }
            }
        }

        List<User> usersTo = email.getToUser();
        // 1. verifica che tutti i destinatari sono presenti in LDAP
        if (usersTo != null) {
            for (User emailTo : usersTo) {
                boolean test = testDominioDestinatario(emailTo.getHpmUserId());
                if (!test) {
                    throw new KPeopleMailServiceDomainException("La mail "
                            + emailTo.getHpmUserId()
                            + " non è presente nel dominio");
                }
            }
        }

        if (processes == null || processes.size() == 0) {
            throw new KPeopleMailServiceProcessNotFoundException();
        }

        if (patterns == null || patterns.size() == 0) {
            throw new KPeopleMailServicePatternNotFoundException();
        }

        // 2. verifica che il process ha almeno 1 keyword
        for (Process process : processes) {
            List<Keyword> keys = process.getKeywords();
            if (keys == null || keys.size() == 0) {
                throw new KPeopleMailServiceKeywordNotFoundException();
            }
        }

        // invio della mail
        try {
            sendMailPattern(email, processes, patterns, sendWithKpeopleTag);
        } catch (Exception e) {
            throw new KPeopleMailServiceSendMailException(e.getMessage());
        }

    }

    /**
     * Costruisce un oggetto Multipart e invia la mail.
     * @param email communication event contenente le informazioni della mail
     * @param processes processi accociati
     * @param patterns pattern associati
     * @param sendWithKpeopleTag invia la mail con il tag kpeople
     * @throws XMLStreamException eccezione
     * @throws MessagingException eccezione
     * @throws IOException eccezione
     */
    private void sendMailPattern(
            final CommunicationEvent email,
            final List<Process> processes,
            final List<Pattern> patterns,
            final boolean sendWithKpeopleTag)
            throws XMLStreamException, MessagingException, IOException {

        Singleton singleton = Singleton.getInstance();
        Multipart multipart = new MimeMultipart();

        // default mail session
        Session session =
                Session.getDefaultInstance(buildProperties(singleton),
                        new SMTPAuthenticator());
        session.setDebug(false);

        // bodypart del corpo della mail
        BodyPart contentBp = new MimeBodyPart();
        contentBp.setText(email.getBody());
        multipart.addBodyPart(contentBp);

        // aggiungo gli attachments della mail
        addAttachments(email, multipart);

        // aggiungo l'attachment kpeople
        if (sendWithKpeopleTag) {
            addKpeopleAttachmentPattern(processes, multipart, patterns);
        }

        // creazione dell'oggetto Message
        String emailAddress = email.getUserFrom().getHpmUserId();

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailAddress));
        msg.setRecipients(Message.RecipientType.TO, getRecipientsTo(email));
        msg.setRecipients(Message.RecipientType.CC, getRecipientsCc(email));
        msg.setSubject(email.getObject());
        msg.setContent(multipart);
        msg.setSentDate(new Date());

        // invio della mail
        Transport.send(msg);
    }

    private Address[] getRecipientsCc(CommunicationEvent email) throws AddressException{
        // destinatari della mail
        InternetAddress[] recipients =
                new InternetAddress[email.getCcUser().size()];

        for (int i = 0; i < email.getCcUser().size(); i++) {
            recipients[i] =
                    new InternetAddress(email.getCcUser().get(i).getHpmUserId());
        }

        return recipients;

    }

    /**
     * Aggiunge l'attachment kpeople alla mail da inviare.
     * @param processes
     *            lista di Process associati alla mail
     * @param multipart
     *            Multipart da inviare
     * @throws MessagingException
     *             eccezione durante l'aggiunta del BodyPart
     * @throws IOException
     *             eccezione nella generazione dell'xml
     */
    private void addKpeopleAttachmentPattern(final List<Process> processes,
            final Multipart multipart, final List<Pattern> patterns)
            throws MessagingException, IOException {

        // costruzione del Document DOM4J
        org.dom4j.Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("MetaDataSet").addAttribute("xmlns:xsi",
                        "http://www.w3.org/2001/XMLSchema-instance");

        Element compoundMetadata = null;
        Element metadata = null;
        for (Process process : processes) {
            compoundMetadata =
                    root.addElement("CompoundMetadata")
                            .addAttribute("Key", process.getHpmProcessId())
                            .addAttribute("Description",
                                    process.getDescription());

            for (Keyword keyword : process.getKeywords()) {
                metadata =
                        compoundMetadata.addElement("Metadata")
                                .addAttribute("Key", keyword.getKeyword())
                                .addAttribute("Description", "");
//
//                metadata.addElement("value")
//                        .addAttribute("value", keyword.getHpmKeywordId())
//                        .addAttribute("description", "");

                metadata.addElement("value")
                        .addAttribute("value", process.getHpmProcessId())
                        .addAttribute("description", "");
            }
        }

        for (Pattern pattern : patterns) {

            metadata =
                    compoundMetadata.addElement("Metadata")
                            .addAttribute("Key", "kpeopletagpattern")
                            .addAttribute("Description", "");

            metadata.addElement("value")
                    .addAttribute("value", pattern.getHpmPatternId())
                    .addAttribute("description", "");

        }

        // pretty printing dell'xml su String
        StringWriter stringWriter = new StringWriter();
        XMLWriter xmlWriter =
                new XMLWriter(stringWriter, OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        String xml = stringWriter.toString();

        String fileName =
                Singleton.getInstance().getProperty(
                        "carbon.mail-service.kpeople-attachment-filename");

        BodyPart bp = new MimeBodyPart();
        bp.setText(xml);
        bp.setFileName(fileName);
        multipart.addBodyPart(bp);
    }
}

/**
 * Classe di autenticazione al server smtp.
 */
final class SMTPAuthenticator extends Authenticator {
    /**
     * restiruisce i dati per l'autenticazione.
     * @return PasswordAuthentication
     */
    public PasswordAuthentication getPasswordAuthentication() {
        Singleton singleton = Singleton.getInstance();

        String username = singleton.getProperty("carbon.mail-service.username");
        String password = singleton.getProperty("carbon.mail-service.password");

        return new PasswordAuthentication(username, password);
    }
}
