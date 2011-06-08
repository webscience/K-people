package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.EventMetadata;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.client.activiti.ActivitiClient;
import it.webscience.kpeople.client.activiti.IActivitiClient;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiStartPatternException;
import it.webscience.kpeople.client.activiti.object.ActivitiProcessInstance;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.activity.dao.ActivityDAOUtil;
import it.webscience.kpeople.dal.cross.AttachmentTypeDAO;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.event.EventDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.pattern.dao.PatternDAOUtil;
import it.webscience.kpeople.util.DaoUtils;
import it.webscience.kpeople.util.SesamePropertyKey;
import it.webscience.kpeople.util.SesameUtils;
import it.webscience.kpeople.util.SolrIndexName;
import it.webscience.kpeople.be.PatternMetadata;
import it.webscience.kpeople.bll.impl.sesame.OCRdfRepository;
import it.webscience.kpeople.bll.impl.solr.OCSolrServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import com.mysql.jdbc.Statement;

/**
 * DAO per l'accesso su db alle classi Pattern.
 */
public class PatternDAO implements IPatternDAO {

    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public PatternDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * @param pIgnoreShowInList
     *            specifica se visualizzare o meno i patternType con il flag
     *            ShowInList su tre.
     * @throws SQLException
     *             eccezioni db
     * @return ritorna l'elenco dei pattern type
     */
    public final List<PatternType> getPatternTypes(
            final boolean pIgnoreShowInList, final User pLoggedUser)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternTypes: " + pIgnoreShowInList + pLoggedUser);
        }

        boolean ignoreShowInList = pIgnoreShowInList;

        List<PatternType> patternTypes = new ArrayList<PatternType>();
        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            StringBuffer sbQuery = null;
            sbQuery = new StringBuffer();
            sbQuery.append("SELECT * FROM pattern_type WHERE ");
            sbQuery.append(" is_active = true ");
            if (!ignoreShowInList) {
                sbQuery.append(" AND show_in_list = true");
            }
            sbQuery.append(" AND is_deleted = false ");
            sbQuery.append(" ORDER BY ordering ASC ");
            String query = sbQuery.toString();

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PatternType pt = PatternTypeFactory.createPatternType(rs);
                patternTypes.add(pt);
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternTypes end");
        }

        return patternTypes;
    }

    /**
     * @param pPattern
     *            pattern che deve essere attivato
     * @param pUser
     *            utente che richiama il servizio.
     * @param pProcess
     *            processo associato al pattern.
     * @throws KPeopleDAOException
     *             eccezione generata durante l'invio del pattern al server
     *             activity.
     * @return un oggetto di tipo pattern.
     */
    public final Pattern startPattern(final Pattern pPattern, final User pUser,
            final Process pProcess) throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("startPattern: " + pPattern);
        }
        // Lettura parametri formali
        Process process = pProcess;
        Pattern pattern = pPattern;
        User user = pUser;

        UserDAO userDao = new UserDAO();

        try {
            User requestor =
                    userDao.getUserByHpmUserId(pattern.getPatternRequestor()
                            .getHpmUserId());
            pattern.setPatternRequestor(requestor);
        } catch (NamingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            throw new KPeopleDAOException("Impossibile leggere "
                    + "le informazioni del pattern requestor");
        }

        try {
            User provider =
                    userDao.getUserByHpmUserId(pattern.getPatternProvider()
                            .getHpmUserId());
            pattern.setPatternProvider(provider);
        } catch (NamingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            throw new KPeopleDAOException("Impossibile leggere "
                    + "le informazioni del pattern provider");
        }

        for (int i = 0; i < pattern.getCcUsers().size(); i++) {
            try {
                User ccuser =
                        userDao.getUserByHpmUserId(pattern.getCcUsers().get(i)
                                .getHpmUserId());
                pattern.getCcUsers().set(i, ccuser);

            } catch (NamingException e1) {
                e1.printStackTrace();
                throw new KPeopleDAOException("Impossibile leggere "
                        + "le informazioni di uno dei pattern ccuser");
            }
        }

        try {
            Pattern newPattern = sendToActiviti(pattern, user, process);

            newPattern = insertPatternIntoHpm(newPattern, user, process);

            generateEventForPattern(newPattern, process);

            indexPatternIntoSolr(newPattern, process);

            indexPatternIntoSesame(newPattern, user, process);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        } catch (KPeopleActivitiStartPatternException e) {
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        } catch (SolrServerException e) {
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        } catch (RepositoryException e) {
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("startPattern end");
        }

        // ritorno della BE Pattern
        return pattern;
    }

    /**
     * @throws SQLException
     */
    private void generateEventForPattern(Pattern pPattern, Process pProcess)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("generateEventForPattern start"
                    + pPattern.getHpmPatternId());
        }

        // Lettura parametri formali
        Pattern pattern = pPattern;
        Process process = pProcess;

        EventMetadata eventMetadata = new EventMetadata();
        eventMetadata.setKeyname("action-type");
        eventMetadata.setValue("PATTERN");

        Event patternEvent = new Event();
        patternEvent.setName("");

        patternEvent.setHpmSystemId("HPM");
        patternEvent.setUser(pattern.getPatternRequestor());

        patternEvent.addEventMetadata(eventMetadata);

        EventDAO evDao = new EventDAO();
        AttachmentType at = new AttachmentType();
        at =
                new AttachmentTypeDAO()
                        .getAttachmentTypeByIdAttachmentType(AttachmentType.ATTACHMENT_TYPE_PATTERN);

        User user = pattern.getPatternProvider();
        Date now = new Date();
        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            int idEvent = evDao.saveEvent(patternEvent, user, now, conn);
            patternEvent.setIdEvent(idEvent);

            patternEvent.setHpmEventId(pattern.getHpmPatternId());

            evDao.updateEvent(conn, patternEvent);
            List<EventMetadata> metadatas = patternEvent.getEventMetadata();
            for (int i = 0; i < metadatas.size(); i++) {
                EventMetadata em = metadatas.get(i);
                evDao.saveEventMetadata(em, user, now, conn);
            }

            if (!evDao.existsEventAttachmentAssociation(
                    pattern.getIdAttachment(), idEvent)) {
                evDao.saveEventAttachmentAssociation(conn,
                        pattern.getIdAttachment(), idEvent);
            }

            conn.commit();

        } catch (SQLException e) {
            DaoUtils.rollbackTransaction(conn, e);
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        // evDao.updateEvent(patternEvent);

        if (logger.isDebugEnabled()) {
            logger.debug("generateEventForPattern end");
        }
    }

    /**
     * @param pPattern
     *            pattern che deve essere attivato
     * @param pUser
     *            utente che richiama il servizio.
     * @param pProcess
     *            processo associato al pattern.
     * @throws RepositoryException
     *             eccezione generate dal server sesame.
     */
    private void indexPatternIntoSesame(final Pattern pPattern,
            final User pUser, final Process pProcess)
            throws RepositoryException {

        if (logger.isDebugEnabled()) {
            logger.debug("indexPatternIntoSesame - start");
        }

        Pattern pattern = pPattern;
        Process process = pProcess;

        Document rdfDocument = DocumentHelper.createDocument();
        rdfDocument = SesameUtils.createSesameDocSchema(rdfDocument);

        Singleton configInstance = Singleton.getInstance();

        Element root = rdfDocument.getRootElement();

        Element individual =
                root.addElement(configInstance
                        .getProperty(SesamePropertyKey.OWL_NAME_INDIVIDUAL));
        String kpbase =
                Singleton.getInstance().getProperty(SesamePropertyKey.KPBASE);

        String patternUri = kpbase + pattern.getHpmPatternId();

        String rdfAbout =
                configInstance.getProperty(SesamePropertyKey.RDF_ABOUT);
        individual.addAttribute(rdfAbout, patternUri);

        String rdfType = configInstance.getProperty(SesamePropertyKey.RDF_TYPE);
        Element type = individual.addElement(rdfType);
        String rdfResource =
                configInstance.getProperty(SesamePropertyKey.RDF_RESOURCE);
        String rdfResourceValue =
                configInstance.getProperty(SesamePropertyKey.KPBASE_PATTERN);
        type.addAttribute(rdfResource, rdfResourceValue);

        // popola con i dati dell'evento
        String kpbasePatternId =
                configInstance.getProperty(SesamePropertyKey.KPBASE_EVENTID);

        Element id = individual.addElement(kpbasePatternId);
        id.setText(pattern.getHpmPatternId());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
        if (pattern.getStartDate() != null) {
            String startDate = df.format(pattern.getStartDate());

            String patternInsertDate =
                    configInstance
                            .getProperty(SesamePropertyKey.KPBASE_EVENT_INSERT_DATA);

            Element date = individual.addElement(patternInsertDate);
            date.setText(startDate);
        }

        String processUri = kpbase + process.getHpmProcessId();
        // aggiunge il riferimento al processo
        String eventProcess =
                configInstance
                        .getProperty(SesamePropertyKey.KPBASE_EVENT_PROCESS);
        Element eventProcessElement = individual.addElement(eventProcess);
        eventProcessElement.addAttribute(rdfResource, processUri);

        // aggiunge il riferimento al patternRequestor
        String patternRequestor =
                configInstance
                        .getProperty(SesamePropertyKey.KPBASE_PATTERN_REQUESTOR);
        Element patternRequestorEl = individual.addElement(patternRequestor);
        patternRequestorEl.addAttribute(rdfResource, kpbase
                + pattern.getPatternRequestor().getHpmUserId());

        // aggiunge il riferimento al creatore dell'evento
        String eventAuthor =
                configInstance
                        .getProperty(SesamePropertyKey.KPBASE_EVENT_CREATOR);
        Element patternAuthorEl = individual.addElement(eventAuthor);
        patternAuthorEl.addAttribute(rdfResource, kpbase
                + pattern.getPatternRequestor().getHpmUserId());

        // aggiunge il riferimento al patternProvider
        String patternProvider =
                configInstance
                        .getProperty(SesamePropertyKey.KPBASE_PATTERN_PROVIDER);
        Element patternProviderEl = individual.addElement(patternProvider);
        patternProviderEl.addAttribute(rdfResource, kpbase
                + pattern.getPatternProvider().getHpmUserId());

        // aggiunge il riferimento al patternType
        String patternType =
                configInstance
                        .getProperty(SesamePropertyKey.KPBASE_PATTERN_TYPE);
        Element patternTypeEl = individual.addElement(patternType);
        patternTypeEl.addAttribute(rdfResource, kpbase
                + pattern.getPatternType().getHpmPatternTypeId());

        // aggiunge il riferimento al patternCc

        List<User> ccUsers = pattern.getCcUsers();

        if (!ccUsers.isEmpty()) {
            for (User ccUser : ccUsers) {
                User currUser = ccUser;
                String patternCc =
                        configInstance
                                .getProperty(SesamePropertyKey.KPBASE_PATTERN_CC);
                Element patternCcEl = individual.addElement(patternCc);
                patternCcEl.addAttribute(rdfResource,
                        kpbase + currUser.getHpmUserId());
            }

        }

        // aggiunge il nameindividual process
        addProcessElement(patternUri, processUri, root);
        commitToSesame(rdfDocument);

    }

    /**
     * @param pPattern
     *            pattern da indicizzare sul server solr.
     * @param pProcess
     *            processo associato al pattern.
     * @throws SolrServerException
     *             eccezione generata dal server solr.
     */
    private void indexPatternIntoSolr(final Pattern pPattern,
            final Process pProcess) throws SolrServerException {

        if (logger.isDebugEnabled()) {
            logger.debug("indexPatternIntoSolr - start");
        }

        Pattern pattern = pPattern;
        Process process = pProcess;

        String key = null;
        Object value = null;

        SolrServer solrServer = OCSolrServer.getInstance().getSolrServer();
        SolrInputDocument doc = new SolrInputDocument();

        String patternHpmId = pattern.getHpmPatternId();
        if (patternHpmId != null) {
            String keyId = SolrIndexName.SOLR_ID;
            String keyPatternId = SolrIndexName.SOLR_PATTERN_ID;
            Object patternIdValue = patternHpmId;
            doc.setField(keyPatternId, patternIdValue);
            doc.setField(keyId, patternIdValue);
        }

        String patternName = pattern.getName();
        if (patternName != null) {
            String keyPatternName = SolrIndexName.SOLR_PATTERN_NAME;
            Object patternNameValue = patternName;
            doc.setField(keyPatternName, patternNameValue);
        }

        String patternDescription = pattern.getDescription();
        if (patternDescription != null) {
            String keyPatternDescription =
                    SolrIndexName.SOLR_PATTERN_DESCRIPTION;
            Object patternDescriptionValue = patternDescription;
            doc.setField(keyPatternDescription, patternDescriptionValue);
        }

        String patternRequestor = pattern.getPatternRequestor().getHpmUserId();
        if (patternRequestor != null) {
            String keyPatternRequestor = SolrIndexName.SOLR_PATTERN_REQUESTOR;
            Object patternRequestorValue = patternRequestor;
            doc.setField(keyPatternRequestor, patternRequestorValue);
        }

        String patternProvider = pattern.getPatternProvider().getHpmUserId();
        if (patternProvider != null) {
            String keyPatternProvider = SolrIndexName.SOLR_PATTERN_PROVIDER;
            Object patternProviderValue = patternProvider;
            doc.setField(keyPatternProvider, patternProviderValue);
        }

        String hpmProcessId = process.getHpmProcessId();
        if (hpmProcessId != null) {
            String keyProcessHpmId = SolrIndexName.SOLR_PATTERN_PROCESS_HPMID;
            Object processHpmIdValue = hpmProcessId;
            doc.setField(keyProcessHpmId, processHpmIdValue);
        }

        try {
            UpdateResponse updateResponseAdd = solrServer.add(doc);
            UpdateResponse updateResponseCommit = solrServer.commit();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SolrServerException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("indexPatternIntoSolr - end");
        }
    }

    /**
     * @param pPattern
     *            pattern che deve essere avviato.
     * @param pUser
     *            utente che effettua la richiesta di attivazione pattern.
     * @return l'id generato il activiti ed associato al pattern creato.
     * @throws SQLException
     *             eccezione generata dall'interrogazione al db hpm.
     * @throws KPeopleActivitiStartPatternException
     *             eccezione generata durante l'avvio del pattern sul server
     *             activity.
     */
    private Pattern sendToActiviti(final Pattern pPattern, final User pUser,
            final Process pProcess) throws SQLException,
            KPeopleActivitiStartPatternException {

        if (logger.isDebugEnabled()) {
            logger.debug("sendToActiviti: " + pPattern);
        }

        // Lettura parametri formali
        Pattern pattern = pPattern;
        Process process = pProcess;

        // Generazione HASHMAP per l'invocazione ad Activiti
        JSONObject jSonObjectInput = new JSONObject();
        String pt = pattern.getPatternType().getActivitiProcessDefinitionId();
        jSonObjectInput.put("processDefinitionId", pt);

        jSonObjectInput.put("patternProvider", pattern.getPatternProvider()
                .getHpmUserId());
        jSonObjectInput.put("patternProvider_type", "User");
        jSonObjectInput.put("patternProvider_required", "true");

        jSonObjectInput.put("patternRequestor", pattern.getPatternRequestor()
                .getHpmUserId());
        jSonObjectInput.put("patternRequestor_type", "User");
        jSonObjectInput.put("patternRequestor_required", "true");

        jSonObjectInput.put("patternTitle", pattern.getName());

        // Info custom per il pattern
        List<PatternMetadata> pms = pattern.getPatternMetadatas();
        for (int i = 0; i < pms.size(); i++) {
            PatternMetadata pm = pms.get(i);
            if (pm.isActivitiProcessMetadata()) {
                jSonObjectInput.put(pm.getKeyname(), pm.getValue());
            }
        }

        // Generazione stringa json da passare
        String jSonParams = jSonObjectInput.toString();

        // Chiamata verso il motore di workflow
        IActivitiClient activitiClient =
                new ActivitiClient(pattern.getPatternRequestor().getHpmUserId());

        ActivitiProcessInstance actProc =
                activitiClient.startActivitiProcessObj(jSonParams);
        String activitiProcessInstanceId = actProc.getId();

        pattern.setActivitiProcessInstanceId(activitiProcessInstanceId);

        // Generazione HpmPatternId e HpmAttachmentId (vengono settati uguali)

        String hpmPatternId =
                Pattern.generateHpmPatternId(process.getHpmProcessId(), pattern
                        .getPatternType().getPatternTypeCode(),
                        activitiProcessInstanceId);

        pattern.setHpmPatternId(hpmPatternId);
        pattern.setHpmAttachmentId(hpmPatternId);

        // Salvataggio nella base dati del pattern
        int idPattern = 0;
        pattern.setIdAttachment(idPattern);

        if (logger.isDebugEnabled()) {
            logger.debug("sendToActiviti end");
        }

        return pattern;
    }

    /**
     * Metodo per l'invio del file rdf, rappresentativo dei dati da inserire
     * all'interno dell'ontologia presente sul server Sesame.
     * @param rdfDocument
     *            - file rdf che deve essere inviato.
     * @throws RepositoryException
     *             eccezione generata in fase di commit sil repository sesame.
     */
    private void commitToSesame(final Document rdfDocument)
            throws RepositoryException {
        File rdfFile = null;

        Repository sesameRepository =
                OCRdfRepository.getInstance().getSesameRepository();

        try {

            RepositoryConnection con = sesameRepository.getConnection();

            String rdfPath =
                    Singleton.getInstance().getProperty(
                            SesamePropertyKey.SESAME_RDF_DIR_PATH);
            String fileName =
                    Singleton.getInstance().getProperty(
                            SesamePropertyKey.SESAME_RDF_FILENAME);

            if (rdfPath != null) {
                rdfFile =
                        new File(rdfPath + SesamePropertyKey.SEPARATOR
                                + fileName);
            }

            // File newFile = new File("rdfkpeople.rdf");
            StringWriter sw = new StringWriter();
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter output = new XMLWriter(sw, format);
            output.write(rdfDocument);
            output.close();

            FileWriter fileWriter = new FileWriter(rdfFile);
            String content = sw.toString();
            content = content.replaceAll("&amp;", "&");
            fileWriter.write(content);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            e.printStackTrace();
            throw new RepositoryException(e.getMessage());
        }

        RepositoryConnection con;

        String baseURI =
                Singleton.getInstance().getProperty(
                        SesamePropertyKey.SESAME_SERVER_BASEURI);
        try {
            con = sesameRepository.getConnection();
            con.add(rdfFile, baseURI, RDFFormat.RDFXML);
            con.commit();
        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (RDFParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param eventId
     * @param processResource
     * @param root
     */
    private void addProcessElement(String eventId, String processResource,
            Element root) {
        if (logger.isDebugEnabled()) {
            logger.debug("addProcessElement - begin");
        }

        Singleton configInstance = Singleton.getInstance();
        Element individual =
                root.addElement(configInstance
                        .getProperty(SesamePropertyKey.OWL_NAME_INDIVIDUAL));

        String rdfAbout =
                configInstance.getProperty(SesamePropertyKey.RDF_ABOUT);
        individual.addAttribute(rdfAbout, processResource);
        String rdfType = configInstance.getProperty(SesamePropertyKey.RDF_TYPE);
        Element type = individual.addElement(rdfType);
        String rdfResource =
                configInstance.getProperty(SesamePropertyKey.RDF_RESOURCE);

        String kpbaseProcess =
                configInstance.getProperty(SesamePropertyKey.KPBASE_PROCESS);
        type.addAttribute(rdfResource, kpbaseProcess);

        String kpbaseProcessEvent =
                configInstance
                        .getProperty(SesamePropertyKey.KPBASE_PROCESS_EVENT);
        Element eventProcess = individual.addElement(kpbaseProcessEvent);
        eventProcess.addAttribute(rdfResource, eventId);

        if (logger.isDebugEnabled()) {
            logger.debug("addProcessElement - end");
        }
    }

    /**
     * Memorizza le informazioni relative al pattern nella base dati HPM
     * @param pPattern
     *            pattern da memorizzare
     * @param pUser
     *            utente che effettua l'operazione (dovrebbe coincidere con il
     *            pattern requestor)
     * @param pProcess
     *            processo associato al pattern
     * @throws SQLException
     *             eccezione
     */
    private Pattern insertPatternIntoHpm(final Pattern pPattern,
            final User pUser, final Process pProcess) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("insertPatternIntoHpm - start");
        }

        // Lettura parametri formali
        Process process = pProcess;
        Pattern pattern = pPattern;
        User user = pUser;

        Connection conn = null;
        try {
            // GET CONNECTION
            conn = Singleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            // Salvataggio tabella Attachment
            PatternDAOUtil pDaoUtil = new PatternDAOUtil();
            int patternId = pDaoUtil.savePattern(conn, pattern, process);
            pattern.setIdAttachment(patternId);

            // TODO: ELIMINAZIONE DALLA TABELLA USER_PATTERNROLE_PATTERN
            // DI TUTTE LE OCCORRENZE RELATIVE ALL'ID PATTERN

            // Salvataggio ruoli
            java.sql.Timestamp sqlDateStart = null;
            if (pattern.getStartDate() != null) {
                sqlDateStart =
                        new java.sql.Timestamp(pattern.getStartDate().getTime());
            }

            pDaoUtil.savePatternUserRole(conn, pattern.getPatternRequestor()
                    .getIdUser(), Pattern.PATTERN_ROLE_REQUESTOR, pattern
                    .getIdAttachment(), user.getIdUser(), sqlDateStart);

            pDaoUtil.savePatternUserRole(conn, pattern.getPatternProvider()
                    .getIdUser(), Pattern.PATTERN_ROLE_PROVIDER, pattern
                    .getIdAttachment(), user.getIdUser(), sqlDateStart);

            List<User> ccUsers = pattern.getCcUsers();
            for (int i = 0; i < ccUsers.size(); i++) {
                User u = ccUsers.get(i);
                pDaoUtil.savePatternUserRole(conn, u.getIdUser(),
                        Pattern.PATTERN_ROLE_CCUSERS,
                        pattern.getIdAttachment(), user.getIdUser(),
                        sqlDateStart);
            }

            List<PatternMetadata> metadatas = pattern.getPatternMetadatas();
            for (int i = 0; i < metadatas.size(); i++) {
                PatternMetadata pm = metadatas.get(i);
                pDaoUtil.savePatternMetadata(conn, patternId, pm,
                        user.getIdUser(), sqlDateStart);
            }

            conn.commit();

        } catch (SQLException e) {
            DaoUtils.rollbackTransaction(conn, e);
        } finally {
            conn.close();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Inserito pattern: " + process.getName());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("insertPatternIntoHpm - end");
        }

        return pattern;
    }

    @Override
    public Pattern patternDetailByHpmPatternId(Pattern pPattern, User pUser)
            throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("patternDetailByHpmPatternId - start"
                    + pPattern.getHpmAttachmentId());
        }

        // Lettura parametri formali
        Pattern pattern = pPattern;
        User user = pUser;

        Pattern patternDetail = null;

        try {
            PatternDAOUtil pDaoUtil = new PatternDAOUtil();
            patternDetail =
                    pDaoUtil.loadPatternByHpmPatternId(pattern
                            .getHpmPatternId());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("patternDetailByHpmPatternId - end");
        }

        return patternDetail;
    }

    /**
     * Ottiene
     * @param pHpmPatternId
     * @return pattern
     */
    public final Pattern getPatternByHpmPatternId(final String pHpmPatternId)
            throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternById - start " + pHpmPatternId);
        }

        // Lettura parametri formali
        String hpmPatternId = pHpmPatternId;
        int idPattern = 0;
        Pattern pattern = null;

        PatternDAOUtil pDauUtil = new PatternDAOUtil();
        try {
            idPattern = pDauUtil.getPatternIdFromHpmPatternId(hpmPatternId);
            PatternDAOUtil pDaoUtil = new PatternDAOUtil();
            pattern = pDaoUtil.loadPatternByPatternId(idPattern);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternById - end");
        }
        return pattern;
    }

    @Override
    public PatternType getPatternTypeByPatternTypeId(PatternType pPatternType,
            User pLoggedUser) throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternTypeByPatternTypeId - start "
                    + pPatternType.getIdPatternType());
        }
        // Lettura parametri formali
        PatternType patternType = pPatternType;
        User loggedUser = pLoggedUser;

        PatternTypeDAO ptDao = new PatternTypeDAO();
        try {
            patternType =
                    ptDao.getPatternTypeById(patternType.getIdPatternType());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternTypeByPatternTypeId - end");
        }

        return patternType;
    }

    @Override
    public Pattern closePattern(Pattern pPattern, User pLoggedUser)
            throws KPeopleDAOException {

        // Lettura parametri formali
        Pattern pattern = pPattern;
        User loggedUser = pLoggedUser;

        if (logger.isDebugEnabled()) {
            logger.debug("closePattern - start");
        }

        PatternDAOUtil patDaoUtil = new PatternDAOUtil();
        try {
            pattern = patDaoUtil.closePattern(pattern, loggedUser);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("closePattern - end");
        }

        return pattern;
    }

    @Override
    public PatternState getPatternStateByPatternStateId(
            PatternState pPatternState, User pLoggedUser)
            throws KPeopleDAOException {

        // Lettura parametri formali
        PatternState patternState = pPatternState;
        User loggedUser = pLoggedUser;

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternStateByPatternStateId - start");
        }

        PatternStateDAO patStateDao = new PatternStateDAO();
        try {
            patternState =
                    patStateDao.getPatternStateById(patternState
                            .getIdPatternState());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternStateByPatternStateId - end");
        }

        return patternState;
    }

    @Override
    public boolean closePatternFromActiviti(
            final String pActivitiProcessInstanceId, final String pHpmUserId)
            throws KPeopleDAOException {

        boolean ret = true;
        // Ottiene identificativo pattern
        if (logger.isDebugEnabled()) {
            logger.debug("closePatternFromActiviti - start"
                    + pActivitiProcessInstanceId + " " + pHpmUserId);
        }

        // Lettura parametri formali
        String activitiProcessInstanceId = pActivitiProcessInstanceId;
        String hpmUserId = pHpmUserId;

        // BE da passare al servizio interno per la chiusura del pattern
        User user = null;

        UserDAO userDao = new UserDAO();
        try {
            user = userDao.getUserByHpmUserId(hpmUserId);
        } catch (NamingException e1) {
            throw new KPeopleDAOException(e1.getMessage());
        }

        PatternDAOUtil pDaoUtil = new PatternDAOUtil();
        int idPattern = 0;

        try {
            idPattern =
                    pDaoUtil.getPatternIdFromActivitiProcessInstanceId(activitiProcessInstanceId);
        } catch (SQLException e) {
            throw new KPeopleDAOException(e.getMessage());
        }

        Pattern pattern = null;

        if (idPattern != 0) {
            try {
                pattern = pDaoUtil.loadPatternByPatternId(idPattern);
            } catch (SQLException e) {
                throw new KPeopleDAOException(e.getMessage());
            }
        }

        if (pattern != null && user != null) {
            pattern = closePattern(pattern, user);
            ret = pattern.isWaitingActivity();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("closePatternFromActiviti - end");
        }
        // Chiama il servizio di aggiornamento che funziona con le BE
        return ret;
    }
}
