 package it.webscience.uima.annotators;

import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.Email;
import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.EventMetadata;
import it.webscience.kpeople.dal.HpmDao;
import it.webscience.uima.CasErrorManager;
import it.webscience.uima.Singleton;
import it.webscience.uima.annotations.action.ActionReferenceAnnotation;
import it.webscience.uima.annotations.action.ActionTypeAnnotation;
import it.webscience.uima.annotations.action.EventIdAnnotation;
import it.webscience.uima.annotations.action.SystemAnnotation;
import it.webscience.uima.annotations.eventData.AttachmentAnnotation;
import it.webscience.uima.annotations.eventData.BodyAnnotation;
import it.webscience.uima.annotations.eventData.DateCreationDateAnnotation;
import it.webscience.uima.annotations.eventData.DetailsAnnotation;
import it.webscience.uima.annotations.eventData.PropertyAnnotation;
import it.webscience.uima.annotations.eventData.TitleAnnotation;
import it.webscience.uima.annotations.eventData.UserAuthorAnnotation;
import it.webscience.uima.annotations.eventData.UserReceiverBccAnnotation;
import it.webscience.uima.annotations.eventData.UserReceiverCcAnnotation;
import it.webscience.uima.annotations.eventData.UserReceiverToAnnotation;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

/** Gestisce il salvataggio delle annotations su HPM.
 */
public class HpmAnnotator extends JCasAnnotator_ImplBase {

    /** tipo evento associato alla creazione  di un pattern.*/
    private static final String PROCESS_PATTERN_CREATE_ATTACHDOCUMENT =
        "PROCESS.PATTERN.CREATE.ATTACHDOCUMENT";
    /** tipo evento associato all'accettaione di una richiesta di contributo.*/
    private static final String PROCESS_PATTERN_RICHIESTACONTRIBUTO_ACCETTA =
        "PROCESS.PATTERN.RICHIESTACONTRIBUTO.ACCETTA";

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * @see AnalysisComponent#initialize(UimaContext)
     * @param aContext context
     * @throws ResourceInitializationException exception
     */
    public final void initialize(final UimaContext aContext)
            throws ResourceInitializationException {

        if (aContext != null) {
            super.initialize(aContext);
        }
    }

    /**
     * Restituisce l'annotation EventIdAnnotation.
     * @param cas CAS da elaborare
     * @return annotation di tipo EventIdAnnotation
     */
    private EventIdAnnotation getEventIdAnnotation(final JCas cas) {
        Type type = cas.getTypeSystem().
            getType(EventIdAnnotation.class.getCanonicalName());

        FSIterator<Annotation> it =
                cas.getAnnotationIndex(type).iterator();
        return (EventIdAnnotation) it.next();
    }

    /**
     * Restituisce l'annotation UserAuthorAnnotation.
     * @param cas CAS da elaborare
     * @return annotation di tipo UserAuthorAnnotation
     */
    private UserAuthorAnnotation getUserAuthorAnnotation(final JCas cas) {
        Type type = cas.getTypeSystem().
            getType(UserAuthorAnnotation.class.getCanonicalName());

        FSIterator<Annotation> it =
                cas.getAnnotationIndex(type).iterator();
        return (UserAuthorAnnotation) it.next();
    }

    /**
     * Restituisce l'annotation TitleAnnotation.
     * @param cas CAS da elaborare
     * @return annotation di tipo TitleAnnotation
     */
    private TitleAnnotation getTitleAnnotation(final JCas cas) {
        Type type = cas.getTypeSystem().
            getType(TitleAnnotation.class.getCanonicalName());

        FSIterator<Annotation> it =
                cas.getAnnotationIndex(type).iterator();
        return (TitleAnnotation) it.next();
    }

    /**
     * Restituisce l'annotation ActionTypeAnnotation.
     * @param cas CAS da elaborare
     * @return annotation di tipo ActionTypeAnnotation
     */
    private ActionTypeAnnotation getActionTypeAnnotation(final JCas cas) {
        Type annotationType = cas.getTypeSystem().
            getType(ActionTypeAnnotation.class.getCanonicalName());

        FSIterator<Annotation> it =
                cas.getAnnotationIndex(annotationType).iterator();
        return (ActionTypeAnnotation) it.next();
    }

    /**
     * Restituisce l'annotation ActionReferenceAnnotation.
     * @param cas CAS da elaborare
     * @return annotation di tipo ActionReferenceAnnotation
     */
    private ActionReferenceAnnotation getActionReferenceAnnotation(
            final JCas cas) {
        Type type = cas.getTypeSystem().
            getType(ActionReferenceAnnotation.class.getCanonicalName());

        FSIterator<Annotation> it =
                cas.getAnnotationIndex(type).iterator();
        return (ActionReferenceAnnotation) it.next();
    }

    /**
     * Restituisce l'annotation SystemAnnotation.
     * @param cas CAS da elaborare
     * @return annotation di tipo SystemAnnotation
     */
    private SystemAnnotation getSystemAnnotation(final JCas cas) {
        Type type = cas.getTypeSystem().
            getType(SystemAnnotation.class.getCanonicalName());

        FSIterator<Annotation> it =
                cas.getAnnotationIndex(type).iterator();
        return (SystemAnnotation) it.next();
    }

    /**
     * Restituisce l'annotation BodyAnnotation.
     * @param cas CAS da elaborare
     * @return annotation di tipo BodyAnnotation
     */
    private BodyAnnotation getBodyAnnotation(final JCas cas) {
        Type type = cas.getTypeSystem().
            getType(BodyAnnotation.class.getCanonicalName());

        BodyAnnotation ann = null;

        FSIterator<Annotation> it =
                cas.getAnnotationIndex(type).iterator();
        if (it.hasNext()) {
            ann = (BodyAnnotation) it.next();
        }

        return ann;
    }

    /**
     * Restituisce l'annotation DetailsAnnotation.
     * @param cas CAS da elaborare
     * @return annotation di tipo BodyAnnotation
     */
    private DetailsAnnotation getDetailsAnnotation(final JCas cas) {
        Type type = cas.getTypeSystem().
            getType(DetailsAnnotation.class.getCanonicalName());

        DetailsAnnotation ann = null;

        FSIterator<Annotation> it =
                cas.getAnnotationIndex(type).iterator();

        if (it.hasNext()) {
            ann = (DetailsAnnotation) it.next();
        }

        return ann;
    }

    /**
     * Restituisce l'annotation DateCreationDateAnnotation.
     * @param cas CAS da elaborare
     * @return annotation di tipo DateCreationDateAnnotation
     */
    private DateCreationDateAnnotation getDateCreationDateAnnotation(
            final JCas cas) {
        Type type = cas.getTypeSystem().
            getType(DateCreationDateAnnotation.class.getCanonicalName());

        FSIterator<Annotation> it =
                cas.getAnnotationIndex(type).iterator();
        return (DateCreationDateAnnotation) it.next();
    }

    /**
     * Imposta il campo fieldTo per l'oggetto Email.
     * @param email email da popolare
     * @param cas cas da cui estrarre le annotations
     */
    private void addEmailTo(
            final Email email,
            final JCas cas) {

        Type annotationType = cas.getTypeSystem().
            getType(UserReceiverToAnnotation.class.getCanonicalName());
        FSIterator<Annotation> it =
            cas.getAnnotationIndex(annotationType).iterator();

        //  uso la mappa per evitare l'inserimento di email duplicate
        Hashtable<String, String> emailMap = new Hashtable<String, String>();

        while (it.hasNext()) {
            UserReceiverToAnnotation ann = (UserReceiverToAnnotation) it.next();
            emailMap.put(ann.getEmail(), "Y");
        }

        Enumeration<String> en = emailMap.keys();
        while (en.hasMoreElements()) {
            String element = en.nextElement();
            email.getEmailTo().add(element);
        }
    }

    /**
     * Imposta il campo fieldCc per l'oggetto Email.
     * @param email email da popolare
     * @param cas cas da cui estrarre le annotations
     * @return valore del campo
     */
    private String addEmailCc(
            final Email email,
            final JCas cas) {
        Type annotationType = cas.getTypeSystem().
            getType(UserReceiverCcAnnotation.class.getCanonicalName());
        FSIterator<Annotation> it = cas.getAnnotationIndex(annotationType).
            iterator();

//      uso la mappa per evitare l'inserimento di email duplicate
        Hashtable<String, String> emailMap = new Hashtable<String, String>();

        while (it.hasNext()) {
            UserReceiverCcAnnotation ann = (UserReceiverCcAnnotation) it.next();
            emailMap.put(ann.getEmail(), "Y");
        }

        Enumeration<String> en = emailMap.keys();
        while (en.hasMoreElements()) {
            String element = en.nextElement();
            email.getEmailCc().add(element);
        }

        return "";
    }

    /**
     * Imposta il campo fieldCcn per l'oggetto Email.
     * @param email email da popolare
     * @param cas cas da cui estrarre le annotations
     * @return valore del campo
     */
    private String addEmailCcn(
            final Email email,
            final JCas cas) {

        Type annotationType = cas.getTypeSystem().
        getType(UserReceiverBccAnnotation.class.getCanonicalName());
        FSIterator<Annotation> it = cas.getAnnotationIndex(annotationType).
        iterator();

        //  uso la mappa per evitare l'inserimento di email duplicate
        Hashtable<String, String> emailMap = new Hashtable<String, String>();

        while (it.hasNext()) {
            UserReceiverBccAnnotation ann = (UserReceiverBccAnnotation)
                it.next();
            emailMap.put(ann.getEmail(), "Y");
        }

        Enumeration<String> en = emailMap.keys();
        while (en.hasMoreElements()) {
            String element = en.nextElement();
            email.getEmailCcn().add(element);
        }

        return "";
    }

    /**
     * Imposta il campo fieldFrom per l'oggetto Email.
     * @param email email da popolare
     * @param cas cas da cui estrarre le annotations
     * @return valore del campo
     */
    private String addEmailFrom(
            final Email email,
            final JCas cas) {
        Type annotationType = cas.getTypeSystem().
            getType(UserAuthorAnnotation.class.getCanonicalName());
        FSIterator<Annotation> it = cas.getAnnotationIndex(annotationType).
        iterator();

        //  uso la mappa per evitare l'inserimento di email duplicate
        Hashtable<String, String> emailMap = new Hashtable<String, String>();

        while (it.hasNext()) {
            UserAuthorAnnotation ann = (UserAuthorAnnotation) it.next();
            emailMap.put(ann.getEmail(), "Y");
        }

        Enumeration<String> en = emailMap.keys();
        while (en.hasMoreElements()) {
            String element = en.nextElement();
            email.getEmailFrom().add(element);
        }

        return "";
    }

    /**
     * Imposta il campo emailBody per l'oggetto Email.
     * @param cas cas da elaborare
     * @return valore del campo
     */
    private String getEmailBody(final JCas cas) {
        Type annotationType = cas.getTypeSystem().
            getType(BodyAnnotation.class.getCanonicalName());
        FSIterator<Annotation> it = cas.getAnnotationIndex(annotationType).
            iterator();

        String emailBody = "";
        if (it.hasNext()) {
            BodyAnnotation ann = (BodyAnnotation) it.next();

            String value = ann.getValue();
            if (value != null) {
                emailBody = value;
            }
        }

        return emailBody;
    }

    /**
     * aggiunge oggetti di tipo Document all'event.
     * Nel caso di Communication associo anche la email ai documenti.
     * @param event oggetto a cui associare i Document
     * @param email email da salvare
     * @param cas cas da elaborare
     */
    private void addDocuments(
            final Event event,
            final Email email,
            final JCas cas) {

        AnnotationIndex<Annotation> aaIdx = cas.getAnnotationIndex(
                AttachmentAnnotation.type);
        FSIterator<Annotation> itAa = aaIdx.iterator();
        while (itAa.hasNext()) {
            AttachmentAnnotation aa =
                (AttachmentAnnotation) itAa.next();

            Document document = new Document();
            document.setAttachmentType(new AttachmentType(2));
            document.setGuid(aa.getUrlAttachment());
            document.setHashcode(aa.getHashcode());
            document.setAuthor(aa.getAuthor());
            document.setTemplate(false);
            document.setHpmAttachmentId(aa.getId());
            document.setName(aa.getAttachmentName());

            event.getAttachments().add(document);

//          associo il documento alla mail (per il legame EMAIL-DOCUMENT)
            if (email != null) {
                email.getDocuments().add(document);
            }
        }
    }

    /**
     * aggiunge le proprietà presenti.
     * @param event eventoda salvare
     * @param cas oggetto Cas
     */
    private void addProperties(
            final Event event,
            final JCas cas) {
        AnnotationIndex<Annotation> annIdx = cas.getAnnotationIndex(
                PropertyAnnotation.type);
        FSIterator<Annotation> it = annIdx.iterator();

        while (it.hasNext()) {
            PropertyAnnotation annotation =
                (PropertyAnnotation) it.next();

            String key = annotation.getKey();
            String value = annotation.getValue();

            logger.debug(
                 "Aggiunta proprietà\nKey: "
                    + key
                    + "\nValue: " + value);

            event.getProperties().put(key, value);
        }
    }

    /**
     * aggiunge un metadato all'oggetto Event.
     * @param key chiave del metadato
     * @param value valore del metadato
     * @param event event da salvare
     */
    private void addEventMetadata(
            final String key,
            final String value,
            final Event event) {
        EventMetadata em = new EventMetadata();
        em.setKeyname(key);
        em.setValue(value);

        logger.debug(
                "Aggiunta metadato\nKey: " + key
                + "\nValue: " + value);
        event.addEventMetadata(em);
    }

    /**
     * estrae i metadati della cas e li associa all'oggetto Event.
     * @param event event da salvare su hpm
     * @param cas oggetto cas
     */
    private void addEventMetadata(
            final Event event,
            final JCas cas) {
        if (logger.isDebugEnabled()) {
            logger.debug("addEventMetadata - BEGIN");
        }

        addEventMetadata(
                "author",
                getUserAuthorAnnotation(cas).getEmail(),
                event);

        addEventMetadata(
                "action-reference",
                getActionReferenceAnnotation(cas).getValue(),
                event);

        addEventMetadata(
                "action-type",
                getActionTypeAnnotation(cas).getValue(),
                event);

        addEventMetadata(
                "event-id",
                getEventIdAnnotation(cas).getValue(),
                event);

        SystemAnnotation systemAnnotation = getSystemAnnotation(cas);
        addEventMetadata(
                "system-id",
                systemAnnotation.getSystemId(),
                event);
        addEventMetadata(
                "system-type",
                systemAnnotation.getSystemType(),
                event);

        BodyAnnotation bodyAnnotation = getBodyAnnotation(cas);
        if (bodyAnnotation != null) {
            addEventMetadata(
                    "body-value",
                    bodyAnnotation.getValue(),
                    event);
            addEventMetadata(
                    "body-type",
                    bodyAnnotation.getBodyType(),
                    event);
        }

        addEventMetadata(
                "creation-date",
                getDateCreationDateAnnotation(cas).getValue(),
                event);

        DetailsAnnotation detailsAnnotation = getDetailsAnnotation(cas);
        if (detailsAnnotation != null) {
            addEventMetadata(
                "details",
                detailsAnnotation.getValue(),
                event);
        }

        addEventMetadata(
                "title",
                getTitleAnnotation(cas).getValue(),
                event);

        logger.debug("addEventMetadata - END");
    }

    /** Individua le annotations salvate ed esegue il salvataggio sul dao.
     * @param cas CAS da elaborare
     * @throws AnalysisEngineProcessException errore nell'analisi del documento
     * @see JCasAnnotator_ImplBase#process(JCas)
     */
    public final void process(final JCas cas)
            throws AnalysisEngineProcessException {
        logger.debug("Start process - HpmAnnotator");

        String actionType = getActionTypeAnnotation(cas).getValue();

        try {
    //      nel caso di PROCESS.PATTERN.CREATE.ATTACHDOCUMENT associo gli
    //      attachments al pattern associato
            if (actionType.equals(PROCESS_PATTERN_CREATE_ATTACHDOCUMENT)) {
                processPatternAttachmentCas(cas);
            } else {
                processDefaultCas(cas);
            }
        } catch (Exception e) {

            logger.fatal("CAS non elaborata correttamente: ");
            logger.fatal("Salvataggio della cas "
                    + cas.toString()
                    + " su file");

            StringWriter sWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(sWriter));
            logger.fatal(sWriter.getBuffer().toString());

            CasErrorManager cem = new CasErrorManager();
            cem.storeCasInFile(cas);

            throw new AnalysisEngineProcessException();
        }

        logger.debug("End process - HpmAnnotator");
    }

    /**
     * Metodo per il process della Cas generata con l'upload del documenti in
     * 'Nuovo pattern'.
     * @param cas cas da analizzare
     * @throws SQLException eccezione durante l'estrazione dei dati
     */
    private void processPatternAttachmentCas(final JCas cas)
            throws SQLException {

        HpmDao dao = new HpmDao();

//      il valore della property è l'hpmPatternId
        String kpeopleTagPattern = getKpeopleTagPattern(cas);

//      recupero e salvo su db gli attachments associati alla creazione pattern
        List<Document> docs = new ArrayList<Document>();

//      recupero l'email dell'utente che ha generato il pattern
        String email = getUserAuthorAnnotation(cas).getEmail();

        AnnotationIndex<Annotation> aaIdx = cas.getAnnotationIndex(
                AttachmentAnnotation.type);
        FSIterator<Annotation> itAa = aaIdx.iterator();

        while (itAa.hasNext()) {
            AttachmentAnnotation aa = (AttachmentAnnotation) itAa.next();

            Document document = new Document();
            document.setAttachmentType(new AttachmentType(2));
            document.setGuid(aa.getUrlAttachment());
            document.setHashcode(aa.getHashcode());
            document.setTemplate(false);
            document.setHpmAttachmentId(aa.getId());
            document.setName(aa.getAttachmentName());

            docs.add(document);
        }

        docs = dao.savePatternDocument(docs, email, kpeopleTagPattern);
    }

    /**
     * Recupera il valore della proprietà kpeopletagpattern.
     * @param cas cas da analizzare
     * @return valore della proprietà kpeopletagpattern
     */
    private String getKpeopleTagPattern(final JCas cas) {
        AnnotationIndex<Annotation> annIdx = cas.getAnnotationIndex(
                PropertyAnnotation.type);
        FSIterator<Annotation> it = annIdx.iterator();

        String kpeopletagpattern = null;

        while (it.hasNext()) {
            PropertyAnnotation annotation =
                (PropertyAnnotation) it.next();

            String key = annotation.getKey();
            String value = annotation.getValue();

            if (key.equals("kpeopletagpattern")) {
                kpeopletagpattern = value;
            }
        }

        return kpeopletagpattern;
    }

    /** Esegue il process nel caso default.
     * @param cas cas
     * @throws SQLException errore nel salvataggio
     */
    private void processDefaultCas(final JCas cas) throws SQLException {
//      recupero le annotations presenti nella CAS
        String eventId = getEventIdAnnotation(cas).getValue();

        SystemAnnotation systemAnnotation = getSystemAnnotation(cas);
        String systemId = systemAnnotation.getSystemId();

        String title = getTitleAnnotation(cas).getValue();

//      costruisco l'oggetto Hpm Event
        Event event = new Event();
        event.setHpmEventId(eventId);
        event.setHpmSystemId(systemId);

//      associa all'oggetto Event i suoi metadati
        addEventMetadata(event, cas);

//      aggiunge le proprietà
        addProperties(event, cas);

        String actionType = getActionTypeAnnotation(cas).getValue();

        if (checkValidAction(actionType)) {
            Email email = new Email();
            email.setAttachmentType(new AttachmentType(1));

            addEmailTo(email, cas);
            addEmailCc(email, cas);
            addEmailCcn(email, cas);
            addEmailFrom(email, cas);

            email.setEmailObject(title);
            email.setEmailBody(getEmailBody(cas));
            event.getAttachments().add(email);

            addDocuments(event, email, cas);
        } else {
            addDocuments(event, null, cas);
        }

        HpmDao dao = new HpmDao();
        dao.saveEventManager(event);
    }

    /**
     * Verifica se e una la action type valida come comunicazione.
     * @param actionType action attuale
     * @return true se la action è valida
     */
    public boolean checkValidAction(
            final String actionType) {
        String actionTypeDefault = Singleton.getInstance()
            .getProperty("annotator.default.action.types");
        String[] actionTypeValues = actionTypeDefault.split(",");
        for (String action : actionTypeValues) {
            action = action.trim();
            if (action.equalsIgnoreCase(actionType)) {
                return true;
            }
        }

        return false;
    }
}
