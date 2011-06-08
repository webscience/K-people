package it.webscience.kpeople.dal.process;

import it.webscience.kpeople.be.Keyword;
import it.webscience.kpeople.be.ObjectType;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.ProcessFilter;
import it.webscience.kpeople.be.ProcessState;
import it.webscience.kpeople.be.ProcessType;
import it.webscience.kpeople.be.SortCriteria;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.sesame.OCRdfRepository;
import it.webscience.kpeople.bll.impl.solr.OCSolrServer;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.cross.HpmObjectFactory;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.cross.UserFactory;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.keyword.KeywordDAO;
import it.webscience.kpeople.dal.util.DataAccessConstants;
import it.webscience.kpeople.util.DaoUtils;
import it.webscience.kpeople.util.SesamePropertyKey;
import it.webscience.kpeople.util.SesameUtils;
import it.webscience.kpeople.util.SolrIndexName;
import it.webscience.kpeople.util.Validator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import com.mysql.jdbc.Statement;

/**
 * Classe per l'accesso alla tabella PROCESS.
 */
public class ProcessDAO implements IProcessDAO {
    /** logger. */
    private Logger logger;

    /**
     * Costruttore di default.
     */
    public ProcessDAO() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Restituisce gli utenti proprietari di almeno un processo.
     * @param user
     *            user che esegue la chiamata
     * @return elenco di User
     * @throws SQLException
     *             eccezione durante l'elaborazione
     */
    public final List<User> getOwners(final User user) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getOwners");
        }

        List<User> results = new ArrayList<User>();

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            String query =
                    "SELECT DISTINCT u.* "
                            + "FROM user AS u "
                            + "INNER JOIN process AS p ON p.ID_USER_OWNER = u.ID_USER;";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User u = UserFactory.createUser(rs);
                results.add(u);
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end getOwners");
        }

        return results;
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

        if (logger.isDebugEnabled()) {
            logger.debug("start findEnabledUsers");
        }

        UserDAO userDao = new UserDAO();

        List<User> users = new ArrayList<User>();

        try {
            Repository sesameRepository =
                    OCRdfRepository.getInstance().getSesameRepository();

            RepositoryConnection con = sesameRepository.getConnection();

            String prefix =
                    "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"
                            + "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                            + "PREFIX protons:<http://proton.semanticweb.org/2005/04/protons#>"
                            + "PREFIX protont:<http://proton.semanticweb.org/2005/04/protont#>"
                            + "PREFIX kp:<http://kpeople.webscience.it/ontologies/2010/12/kpbase#>";
            // Query cablata sull'id del processo. Riceve staticamente il
            // processo RDA_Prova.

            String query =
                    prefix
                            + "SELECT ?User WHERE { "
                            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#"
                            + process.getHpmProcessId()
                            + ">"
                            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> ?Event . "
                            + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#communicationUser> "
                            + "?User } ";

            logger.debug("Query: " + query);

            TupleQuery tupleQuery =
                    con.prepareTupleQuery(QueryLanguage.SPARQL, query);

            TupleQueryResult result = tupleQuery.evaluate();

            addToUserResult(userDao, users, result);

            query =
                    prefix
                            + "SELECT ?User WHERE { "
                            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#"
                            + process.getHpmProcessId()
                            + ">"
                            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> ?Event . "
                            + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventCreator> "
                            + "?User } ";

            logger.debug("Query: " + query);

            tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, query);

            result = tupleQuery.evaluate();

            addToUserResult(userDao, users, result);

            query =
                    prefix
                            + "SELECT ?User WHERE { "
                            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#"
                            + process.getHpmProcessId()
                            + ">"
                            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> ?Event . "
                            + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#patternProvider> "
                            + "?User } ";

            logger.debug("Query: " + query);

            tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, query);

            result = tupleQuery.evaluate();

            addToUserResult(userDao, users, result);

            query =
                    prefix
                            + "SELECT ?User WHERE { "
                            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#"
                            + process.getHpmProcessId()
                            + ">"
                            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> ?Event . "
                            + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#patternRequestor> "
                            + "?User } ";

            logger.debug("Query: " + query);

            tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, query);

            result = tupleQuery.evaluate();

            addToUserResult(userDao, users, result);

            result.close();

        } catch (Exception e) {
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end getSolrIds");

            logger.debug("utenti abilitati al processo "
                    + process.getHpmProcessId() + ":");

            for (User user : users) {
                logger.debug(user.getUsername() + " - " + user.getLastName()
                        + " " + user.getFirstName());
            }
        }

        return users;
    }

    /**
     * @param userDao
     *            utente che richiede il servizio
     * @param users
     *            lista di utenti abilitati
     * @param result
     *            lista di utenti
     * @throws QueryEvaluationException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    private void addToUserResult(final UserDAO userDao, final List<User> users,
            final TupleQueryResult result) throws QueryEvaluationException {

        while (result.hasNext()) {
            BindingSet bindingSet = result.next();

            String hpmUserId =
                    bindingSet.getValue("User").toString().split("#")[1];

            // aggiungo l'utente all'elenco
            User user = null;
            try {
                user = userDao.getUserByHpmUserId(hpmUserId);
            } catch (KPeopleDAOException e) {
                logger.error(e.getMessage());
            } catch (NamingException e) {
                user = new User();
                user.setEmail(hpmUserId);
                user.setHpmUserId(hpmUserId);
                logger.error(e.getMessage());
            }

            if (user != null) {
                boolean find = false;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getHpmUserId().equalsIgnoreCase(hpmUserId)) {
                        find = true;
                        break;
                    }
                }
                if (!find) {
                    users.add(user);
                }
            }
        }
    }

    /**
     * Esegue l'interrogazione di SOLR.
     * @param filter
     *            filtri di ricerca
     * @return lista di hpmProcessId
     * @throws SolrServerException
     *             eccezione nell'interrogazione
     */
    private List<String> getSolrIds(final ProcessFilter filter)
            throws SolrServerException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getSolrIds");
        }

        List<String> out = new ArrayList<String>();

        SolrServer solrServer = OCSolrServer.getInstance().getSolrServer();
        SolrQuery solrQuery = new SolrQuery();

        solrQuery.setRows(DataAccessConstants.MAX_SOLR_RESULTS);

        /*
         * esegue lo split della stringa libera. viene costruita la query:
         * process.name:(+*str1* +*str2" +*str3*) OR
         * process.description:(+*str1* +*str2" +*str3*)
         */

        String[] tokens = filter.getFreeText().split(" ");
        String queryFilter = "";
        for (String token : tokens) {
            queryFilter += "+*" + token.toLowerCase() + "* ";
        }

        String query =
                "process.name:(" + queryFilter + ") OR "
                        + "process.description:(" + queryFilter + ")";

        logger.debug("Solr query: " + query);

        solrQuery.setQuery(query);

        QueryResponse rsp = solrServer.query(solrQuery);
        Iterator<SolrDocument> it = rsp.getResults().iterator();
        while (it.hasNext()) {
            SolrDocument solrDocument = (SolrDocument) it.next();

            Object id = solrDocument.getFieldValue("id");
            if (id != null) {
                logger.debug("Solr: aggiunto ai risultati id " + id.toString());
                out.add(id.toString());
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end getSolrIds");
        }

        return out;
    }

    /**
     * Ritorna i processi aventi hpmProcessId contenuto nella lista in ingresso.
     * @param hpmProcessIds
     *            lista di hpmProcessId da cercare
     * @param filter
     *            filtri di ricerca
     * @return lista di oggetti Process
     * @throws SQLException
     *             eccezione generica
     */
    public final List<Process> getProcessesByHpmProcessId(
            final List<String> hpmProcessIds, final ProcessFilter filter)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("start getProcessesByHpmProcessId");
        }

        List<Process> results = new ArrayList<Process>();

        // si esce immediatamente se la ricerca su solr non ha trovato risultati
        if (hpmProcessIds != null && hpmProcessIds.isEmpty()) {
            return results;
        }

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            StringBuffer query =
                    new StringBuffer(
                            "SELECT * FROM process WHERE ID_PROCESS > 0 ");

            // filtro su hpmProcessId (se si è passati da solr)
            if (hpmProcessIds != null) {
                query.append(" AND HPM_PROCESS_ID IN (");
                for (String id : hpmProcessIds) {
                    query.append("'" + id + "',");
                }
                query.deleteCharAt(query.length() - 1);
                query.append(")");
            }

            // filtro di ricerca avanzata
            query = applyFilter(filter, query);

            // ordinamento
            query = applySort(filter, query);

            logger.debug("Query: " + query.toString().replaceAll("'", "''"));

            PreparedStatement ps = conn.prepareStatement(query.toString());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(ProcessFactory.createProcess(rs));
            }
            
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end getProcessesByHpmProcessId");
            logger.debug("Processi trovati: " + results.size());
        }

        return results;
    }

    /**
     * Applica l'ordinamento in base alle impostazioni del filtro.
     * @param filter
     *            ProcessFilter con i parametri di ricerca
     * @param query
     *            query di ricerca dei processi
     * @return query con la definizione dell'ordinamaneto
     */
    private StringBuffer applySort(final ProcessFilter filter,
            final StringBuffer query) {
        query.append(" ORDER BY ");
        if (filter != null) {
            String fieldName = filter.getSort().getFieldName();
            if (fieldName.equals("ORDER_BY_LAST_UPDATE")) {
                query.append(" LAST_ACTION_DATE ");
            } else if (fieldName.equals("ORDER_BY_CREATION_DATE")) {
                query.append(" DATE_CREATED ");
            } else {
                query.append(" DATE_DUE ");
            }
        } else {
            query.append(" DATE_DUE ");
        }

        if (filter != null) {
            String order = filter.getSort().getOrder();
            if (order.equals("ASC")) {
                query.append(" ASC ");
            } else {
                query.append(" DESC ");
            }
        } else {
            query.append(" DESC ");
        }

        return query;
    }

    /**
     * Applica le condizioni definite nel filtro di ricerca.
     * @param filter
     *            ProcessFilter con i parametri di ricerca
     * @param query
     *            query di ricerca dei processi
     * @return query con i filtri di ricerca
     */
    private StringBuffer applyFilter(final ProcessFilter filter,
            final StringBuffer query) {
        if (filter != null) {
            // filtro su date (creazione/scadenza)
            if (filter.getCreationDateFrom() != null) {
                query.append(" AND DATE_CREATED >  '"
                        + SQL_DATE_FORMATTER.format(filter
                                .getCreationDateFrom()) + " 00:00:00' ");
            }
            if (filter.getCreationDateTo() != null) {
                query.append(" AND DATE_CREATED <=  '"
                        + SQL_DATE_FORMATTER.format(filter.getCreationDateTo())
                        + " 23:59:59' ");
            }
            if (filter.getDueDateFrom() != null) {
                query.append(" AND DATE_DUE >  '"
                        + SQL_DATE_FORMATTER.format(filter.getDueDateFrom())
                        + " 00:00:00' ");
            }
            if (filter.getDueDateTo() != null) {
                query.append(" AND DATE_DUE <=  '"
                        + SQL_DATE_FORMATTER.format(filter.getDueDateTo())
                        + " 23:59:59' ");
            }

            // filtro "gestito da"
            if (filter.getUserId() != null && !filter.getUserId().isEmpty()) {
                int id = Integer.parseInt(filter.getUserId());
                query.append(" AND ID_USER_OWNER = " + id + " ");
            }

            // filtro "tipo"
            if (filter.getType() != null && !filter.getType().isEmpty()) {
                int id = Integer.parseInt(filter.getType());
                query.append(" AND ID_PROCESS_TYPE = " + id + " ");
            }

            // filtro su aperto/chiuso
            if (filter.getState() != null) {
                query.append(" AND ID_PROCESS_STATE IN (-1,");
                for (int id : filter.getState()) {
                    query.append(id + ",");
                }
                query.deleteCharAt(query.length() - 1);
                query.append(") ");
            }

            // filtro sul flag privato
            if (filter.isShowReserved()) {
                query.append(" AND IS_PRIVATE = false ");
            }

            // filtro su "in tempo"
            if (filter.isShowInTime()) {
                query.append(" AND DATE_DUE IS NOT NULL AND DATE_DUE > now() ");
            }

            // filtro su "in ritardo"
            if (filter.isShowLate()) {
                query.append(" AND DATE_DUE IS NOT NULL AND DATE_DUE < now() ");
            }
        }

        return query;
    }

    @Override
    /**
     * Ricerca dei processi in base al filtro.
     * Il sistema interroga:
     * - SOLR filtrando su titolo e descrizione
     * - HPM si id processo
     * @param filter filtri di ricerca
     * @returl lista di processi
     * @throws KPeopleDAOException eccezione durante la query
     */
    public final List<Process> findProcesses(final ProcessFilter filter)
            throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("start findProcesses");
        }

        // creazione DAO
        UserDAO userDao = new UserDAO();
        ProcessTypeDAO processTypeDAO = new ProcessTypeDAO();
        KeywordDAO keywordDAO = new KeywordDAO();

        List<Process> results = new ArrayList<Process>();

        try {
            // 1. interrogazione di solr
            List<String> hpmProcessIds = null;
            if (filter.getFreeText() != null
                    && !filter.getFreeText().equals("")) {
                hpmProcessIds = getSolrIds(filter);
            }

            // 2. interrogazione dell'hpm
            results = getProcessesByHpmProcessId(hpmProcessIds, filter);

            for (Process process : results) {
                // popolamento dell'owner
                User owner =
                        userDao.getUserByIdUser(process.getOwner().getIdUser());
                process.setOwner(owner);

                // popolamento del process state
                process.setProcessState(getProcessStateByIdProcessState(process
                        .getProcessState().getIdProcessState()));

                // popolamento del process type
                process.setProcessType(processTypeDAO
                        .getProcessTypeByIdProcessType(process.getProcessType()
                                .getIdProcessType()));

                // popolamento keywords
                ObjectType ot = new ObjectType();
                ot.setIdObjectType(1);
                List<Keyword> keywords =
                        keywordDAO.getKeywordsByObjectType(ot,
                                process.getIdProcess());
                process.setKeywords(keywords);
            }

        } catch (Exception e) {
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end findProcesses");
        }

        return results;
    }

    /**
     * ritorna l'oggetto ProcessState associato alla chiave desiderata.
     * @param idProcessState
     *            chiave
     * @throws SQLException
     *             eccezioni db
     * @return oggetto User
     */
    public final ProcessState getProcessStateByIdProcessState(
            final int idProcessState) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getProcessStateByIdProcessState: " + idProcessState);
        }

        ProcessState processState = null;

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            String query =
                    "SELECT * FROM process_state WHERE ID_PROCESS_STATE = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idProcessState);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                processState = ProcessFactory.createProcessState(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return processState;
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
        if (logger.isDebugEnabled()) {
            logger.debug("getProcessByHpmId - begin");
        }

        UserDAO userDao = new UserDAO();
        ProcessTypeDAO processTypeDAO = new ProcessTypeDAO();
        KeywordDAO keywordDAO = new KeywordDAO();

        Process result = new Process();

        if (hpmProcessId == null) {
            return result;
        }
        result = getProcessByHpmIdQuery(hpmProcessId);

        User owner = userDao.getUserByIdUser(result.getOwner().getIdUser());
        result.setOwner(owner);

        // popolamento del process state
        result.setProcessState(getProcessStateByIdProcessState(result
                .getProcessState().getIdProcessState()));

        // popolamento del process type
        result.setProcessType(processTypeDAO
                .getProcessTypeByIdProcessType(result.getProcessType()
                        .getIdProcessType()));

        // popolamento keywords
        ObjectType ot = new ObjectType();
        ot.setIdObjectType(1);
        List<Keyword> keywords =
                keywordDAO.getKeywordsByObjectType(ot, result.getIdProcess());
        result.setKeywords(keywords);

        if (logger.isDebugEnabled()) {
            logger.debug("getProcessByHpmId - end");
            logger.debug("Processo trovato: " + result.getName());
        }

        return result;
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

        if (logger.isDebugEnabled()) {
            logger.debug("setProcess - begin");
        }

        Connection conn = null;
        Process newProcess = process;
        try {
            conn = Singleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            // forza lo stato open per il processo in fase di creazione
            ProcessState stateDefault = new ProcessState();
            stateDefault.setIdProcessState(ProcessState.DEFAULT_STATE_OPEN_ID);
            newProcess.setProcessState(stateDefault);

            // forza l'inserimento della data di creazione per il processo in
            // fase
            // di inserimento
            Calendar calendar = Calendar.getInstance();
            // get a date to represent "today"
            Date currentDate = calendar.getTime();
            newProcess.setDateCreated(currentDate);

            if (newProcess.getDateDue() != null
                    && (newProcess.getDateDue().compareTo(
                            newProcess.getDateCreated()) <= 0)) {
                throw new KPeopleDAOException();
            }

            // forza la keyword di default per il processo in fase di
            // inserimento,
            // recuperando dal db l'oggetto Keyword corrispondente all'id di
            // default.

            List<Keyword> keywords = new ArrayList<Keyword>();
            KeywordDAO keywordDAO = new KeywordDAO();
            try {
                Keyword defaultKeyword =
                        keywordDAO
                                .getKeywordByIdKeyword(Keyword.DEFAULT_KEYWORD);
                keywords.add(defaultKeyword);
                process.setKeywords(keywords);
            } catch (SQLException e1) {
                throw new KPeopleDAOException(e1.getMessage());
            }

            // inserisce il processo nel db hpm

            int idProcess = addProcessQuery(newProcess, conn);

            // aggiorna il processo appena creato nel db hmp per settare
            // l'hpmProcessId.
            newProcess.setIdProcess(idProcess);
            ProcessTypeDAO processTypeDAO = new ProcessTypeDAO();

            ProcessType processType =
                    processTypeDAO.getProcessTypeByIdProcessType(process
                            .getProcessType().getIdProcessType());

            String processTypeCode = processType.getProcessTypeCode();

            String hpmProcessId = processTypeCode + idProcess;
            newProcess.setHpmProcessId(hpmProcessId);
            updateProcessQuery(newProcess, user, conn);

            // popola la tabella di relazione object_keyword
            insertObjectKeyword(newProcess, user, conn);

            // indicizza il processo su solr
            indexProcessSolr(newProcess);

            // indicizza il processo su sesame
            indexProcessSesame(newProcess);

            conn.commit();
        } catch (SQLException e) {
            DaoUtils.rollbackTransaction(conn, e);
        } catch (SolrServerException e) {
            throw new KPeopleDAOException(e.getMessage());
        } catch (RepositoryException e) {
            throw new KPeopleDAOException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("setProcess - stop");
        }
        return newProcess;
    }

    /**
     * Metodo per aggiornare i dati di processo.
     * @param process
     *            - processo che deve essere aggiornato.
     * @param user
     *            - utente che richiede il servizio di aggiornamento.
     * @throws SQLException
     *             eccezione sql generata a livello dal.
     * @throws KPeopleDAOException
     *             eccezione generata a livello dal.
     */
    public final void updateProcess(final Process process, final User user)
            throws SQLException, KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("updateProcess - begin");
        }

        int processId = process.getIdProcess();
        String processName = process.getName();
        String processDescription = process.getDescription();


        
        Date dateDue = process.getDateDue();
        if (dateDue != null
                && (dateDue.compareTo(
                        process.getDateCreated()) <= 0)) {
            throw new KPeopleDAOException();
        }

        
        
        Timestamp sqlDateDue = null;
        if (dateDue != null) {
            sqlDateDue = new Timestamp(dateDue.getTime());
        }

        int processType = process.getProcessType().getIdProcessType();
        boolean isPrivate = process.isPrivate();

        Connection conn = null;

        try {
            conn = Singleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            StringBuffer query =
                    new StringBuffer("UPDATE process "
                            + "SET NAME = ? , DESCRIPTION = ? , "
                            + "DATE_DUE = ? , ID_PROCESS_TYPE = ? , "
                            + "IS_PRIVATE = ? " + "WHERE ID_PROCESS = ?");

            PreparedStatement ps = conn.prepareStatement(query.toString());

            ps.setString(1, processName);
            ps.setString(2, processDescription);
            ps.setTimestamp(3, sqlDateDue);
            ps.setInt(4, processType);
            ps.setBoolean(5, isPrivate);
            ps.setInt(6, processId);

            ps.execute();

            conn.commit();
            indexProcessSolr(process);
            
            
            ps.close();
        } catch (SQLException e) {
            DaoUtils.rollbackTransaction(conn, e);
        } catch (SolrServerException e) {
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        } finally {
            conn.close();
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
     * @throws SQLException
     *             eccezione legata all'esecuzione del servizio.
     */
    public final boolean closeProcess(final Process process, final User user)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("closeProcess - begin");
        }

        int processId = process.getIdProcess();

        int closedStateId = ProcessState.DEFAULT_STATE_CLOSED_ID;
        boolean result = false;
        Connection conn = null;

        try {
            conn = Singleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            StringBuffer query =
                    new StringBuffer("UPDATE process " + "SET IS_ACTIVE = ? , "
                            + "ID_PROCESS_STATE = ? " + "WHERE ID_PROCESS = ?");

            PreparedStatement ps = conn.prepareStatement(query.toString());

            ps.setBoolean(1, false);
            ps.setInt(2, closedStateId);
            ps.setInt(3, processId);

            ps.execute();

            result = true;

            conn.commit();

            
            ps.close();
        } catch (SQLException e) {
            DaoUtils.rollbackTransaction(conn, e);
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("closeProcess - end");
        }

        return result;
    }

    /**
     * Metodo per indicizzare il processo su sesame.
     * @param process
     *            processo che deve esssere indicizzato.
     * @throws RepositoryException
     *             errore generato durante l'accesso al repository sesame.
     */
    private void indexProcessSesame(final Process process)
            throws RepositoryException {
        if (logger.isDebugEnabled()) {
            logger.debug("indexProcessSesame - start");
        }

        String kpbase =
                Singleton.getInstance().getProperty(SesamePropertyKey.KPBASE);
        Document rdfDocument = DocumentHelper.createDocument();
        rdfDocument = SesameUtils.createSesameDocSchema(rdfDocument);

        Element rdfRoot = rdfDocument.getRootElement();

        // creazione elemento process.
        Element individual =
                rdfRoot.addElement(Singleton.getInstance().getProperty(
                        SesamePropertyKey.OWL_NAME_INDIVIDUAL));
        String rdfAbout =
                Singleton.getInstance()
                        .getProperty(SesamePropertyKey.RDF_ABOUT);
        String rdfAboutValue =
                Singleton.getInstance().getProperty(SesamePropertyKey.KPBASE);
        individual.addAttribute(rdfAbout,
                rdfAboutValue + process.getHpmProcessId());

        String rdfType =
                Singleton.getInstance().getProperty(SesamePropertyKey.RDF_TYPE);

        Element type = individual.addElement(rdfType);

        // aggiungo elementi rdf:type
        String rdfResource =
                Singleton.getInstance().getProperty(
                        SesamePropertyKey.RDF_RESOURCE);
        String rdfResourceValue =
                Singleton.getInstance().getProperty(
                        SesamePropertyKey.KPBASE_PROCESS);
        type.addAttribute(rdfResource, rdfResourceValue);

        // popolo sezione processId
        String kpbaseProcessId =
                Singleton.getInstance().getProperty(
                        SesamePropertyKey.KPBASE_PROCESS_ID);

        Element id = individual.addElement(kpbaseProcessId);
        id.setText(process.getHpmProcessId() + "-" + process.getName());

        // popolo il riferimento alla keyword.
        String keywordName = process.getKeywords().get(0).getKeyword();

        String processKeyword =
                Singleton.getInstance().getProperty(
                        SesamePropertyKey.KPBASE_PROCESS_KEYWORD);
        Element keywordElement = individual.addElement(processKeyword);

        keywordElement.addAttribute(rdfResource, kpbase + keywordName + "-"
                + process.getHpmProcessId());

        // popolo il riferimento all'utente owner.

        String ownerHpmId = process.getOwner().getHpmUserId();
        String processOwner =
                Singleton.getInstance().getProperty(
                        SesamePropertyKey.KPBASE_PROCESS_OWNER);
        Element ownerElement = individual.addElement(processOwner);
        ownerElement.addAttribute(rdfResource, kpbase + ownerHpmId);

        // creo l'elemento per la keyword di default
        Element individualKeyword =
                rdfRoot.addElement(Singleton.getInstance().getProperty(
                        SesamePropertyKey.OWL_NAME_INDIVIDUAL));

        individualKeyword.addAttribute(rdfAbout, rdfAboutValue + keywordName
                + "-" + process.getHpmProcessId());

        Element keywordType = individualKeyword.addElement(rdfType);

        keywordType.addAttribute(rdfResource, Singleton.getInstance()
                .getProperty(SesamePropertyKey.KPBASE_KEYWORD));

        Element key =
                individualKeyword.addElement(Singleton.getInstance()
                        .getProperty(SesamePropertyKey.KPBASE_KEYWORD_ID));
        key.setText(keywordName + "-" + process.getHpmProcessId());

        commitToSesame(rdfDocument);

        if (logger.isDebugEnabled()) {
            logger.debug("indexProcessSesame - end");
        }
    }

    /**
     * Metodo per indicizzare il processo sul server solr.
     * @param process
     *            processo che deve essere indicizzato.
     * @throws SolrServerException
     *             eccezione generata durante la connessione al server solr.
     */
    private void indexProcessSolr(final Process process)
            throws SolrServerException {

        if (logger.isDebugEnabled()) {
            logger.debug("indexProcessSolr - start");
        }
        String key = null;
        Object value = null;

        SolrServer solrServer = OCSolrServer.getInstance().getSolrServer();
        SolrInputDocument doc = new SolrInputDocument();

        String processId = process.getHpmProcessId();
        if (processId != null) {
            String keyId = SolrIndexName.SOLR_ID;
            String keyProcessId = SolrIndexName.SOLR_PROCESS_ID;
            Object processIdValue = processId;
            doc.setField(keyProcessId, processIdValue);
            doc.setField(keyId, processIdValue);
        }

        String processName = process.getName();
        if (processName != null) {
            String keyProcessName = SolrIndexName.SOLR_PROCESS_NAME;
            Object processNameValue = processName;
            doc.setField(keyProcessName, processNameValue);
        }

        String processDescription = process.getDescription();
        if (processDescription != null) {
            String keyProcessDescription =
                    SolrIndexName.SOLR_PROCESS_DESCRIPTION;
            Object processDescriptionValue = processDescription;
            doc.setField(keyProcessDescription, processDescriptionValue);
        }

        String processOwner = process.getOwner().getHpmUserId();
        if (processOwner != null) {
            String keyProcessOwner = SolrIndexName.SOLR_PROCESS_OWNER;
            Object processOwnerValue = processOwner;
            doc.setField(keyProcessOwner, processOwnerValue);
        }
        String processTypeName = process.getProcessType().getName();
        if (processTypeName != null) {
            String keyProcessTypeName = SolrIndexName.SOLR_PROCESS_PROCESS_TYPE;
            Object processTypeNameValue = processTypeName;
            doc.setField(keyProcessTypeName, processTypeNameValue);
        }
        try {
            UpdateResponse updateResponseAdd = solrServer.add(doc);
            UpdateResponse updateResponseCommit = solrServer.commit();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SolrServerException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("indexProcessSolr - end");
        }
    }

    /**
     * Metodo per popolare la tabella di relazione object_keyword.
     * @param process
     *            processo che deve è stato inserito nel db.
     * @param user
     *            utente che richiama il servizio.
     * @param conn
     *            connessione aperta con il db hmp.
     * @throws SQLException
     *             eccezione generata durante l'esecuzione della query sul db.
     */
    private void insertObjectKeyword(final Process process, final User user,
            final Connection conn) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("insertObjectKeyword - start");
        }

        int objectTypeId = ObjectType.DEFAULT_OBJ_TYPE_PROCESS;
        int processId = process.getIdProcess();
        String hpmProcessId = process.getHpmProcessId();

        int userId = process.getOwner().getIdUser();

        Date dateCreated = process.getDateCreated();
        Timestamp sqlDateCreated = new Timestamp(dateCreated.getTime());

        Iterator<Keyword> it = process.getKeywords().iterator();
        while (it.hasNext()) {
            Keyword currentKeyword = it.next();
            int currentKeywordId = currentKeyword.getIdKeyword();

            try {
                StringBuffer query =
                        new StringBuffer("INSERT INTO object_keyword "
                                + "( ID_KEYWORD, ID_OBJECT_TYPE, ID_OBJECT, "
                                + "VALUE, FIRST_ACTION_PERFORMER, "
                                + "LAST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
                                + "LAST_ACTION_DATE, DESCRIPTION ) "
                                + "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)");

                PreparedStatement ps = conn.prepareStatement(query.toString());

                ps.setInt(1, currentKeywordId);
                ps.setInt(2, objectTypeId);
                ps.setInt(3, processId);
                ps.setString(4, hpmProcessId);
                ps.setInt(5, userId);
                ps.setInt(6, userId);
                ps.setTimestamp(7, sqlDateCreated);
                ps.setTimestamp(8, sqlDateCreated);
                ps.setString(9, process.getName());

                ps.execute();

                if (logger.isDebugEnabled()) {
                    logger.debug("Inserita relazione su object_keyword ");
                }

            } catch (SQLException e) {
                DaoUtils.rollbackTransaction(conn, e);
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("insertObjectKeyword - end");
        }

    }

    /**
     * Metodo per settare l'hpm-process-id del processo in fase di inserimento.
     * @param process
     *            processo che deve esssere inserito.
     * @param user
     *            utente che richiama il servizio.
     * @param conn
     *            connessione aperta con il db hpm.
     * @throws SQLException
     *             eccezione generata durante la query di update.
     */
    private void updateProcessQuery(final Process process, final User user,
            final Connection conn) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("updateProcessQuery - start");
        }

        String hpmProcessId = process.getHpmProcessId();
        int processId = process.getIdProcess();

        try {
            StringBuffer query =
                    new StringBuffer("UPDATE process "
                            + "SET HPM_PROCESS_ID = ? WHERE ID_PROCESS = ?");

            PreparedStatement ps = conn.prepareStatement(query.toString());

            ps.setString(1, hpmProcessId);
            ps.setInt(2, processId);

            ps.execute();

            if (logger.isDebugEnabled()) {
                logger.debug("Aggiornato processo: " + process.getName());
            }

        } catch (SQLException e) {
            DaoUtils.rollbackTransaction(conn, e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("updateProcessQuery - stop");
        }
    }

    /**
     * Metodo per inserire un processo all'interno del database hpm.
     * @param process
     *            processo che deve essere inserito.
     * @param conn
     *            connessione stabilita con il db hpm.
     * @return un intero che rappresenta l'id del processo che è stato inserito.
     * @throws SQLException
     *             eccezione generata durante l'esecuzione della query.
     */
    private int addProcessQuery(final Process process, final Connection conn)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("setProcessQuery - start");
        }
        int processId = 0;

        Date dateCreated = process.getDateCreated();
        Timestamp sqlDateCreated = new Timestamp(dateCreated.getTime());
        Date dateDue = process.getDateDue();
        Timestamp sqlDateDue = null;
        if (dateDue != null) {
            sqlDateDue = new Timestamp(dateDue.getTime());
        }

        int ownerId = process.getOwner().getIdUser();

        String processName = process.getName();
        String processDescription = process.getDescription();

        int processType = process.getProcessType().getIdProcessType();

        int processState = process.getProcessState().getIdProcessState();

        boolean isPrivate = process.isPrivate();

        Process result = new Process();
        try {

            StringBuffer query =
                    new StringBuffer("INSERT INTO process ("
                            + "FIRST_ACTION_DATE, ID_PROCESS_STATE,"
                            + "NAME, DESCRIPTION, DATE_CREATED,"
                            + "DATE_DUE, IS_PRIVATE, IS_ACTIVE,"
                            + "FIRST_ACTION_PERFORMER, LAST_ACTION_PERFORMER,"
                            + "LAST_ACTION_DATE, ID_USER_OWNER,"
                            + "ID_PROCESS_TYPE, HPM_PROCESS_ID)" + " VALUES ("
                            + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            PreparedStatement ps =
                    conn.prepareStatement(query.toString(),
                            Statement.RETURN_GENERATED_KEYS);

            ps.setTimestamp(1, sqlDateCreated);
            ps.setInt(2, processState);
            ps.setString(3, processName);
            ps.setString(4, processDescription);
            ps.setTimestamp(5, sqlDateCreated);
            ps.setTimestamp(6, sqlDateDue);
            ps.setBoolean(7, isPrivate);
            ps.setInt(8, 1);
            ps.setInt(9, ownerId);
            ps.setInt(10, ownerId);
            ps.setTimestamp(11, sqlDateCreated);
            ps.setInt(12, ownerId);
            ps.setInt(13, processType);
            ps.setString(14, "");

            ps.execute();

            ResultSet res = ps.getGeneratedKeys();

            while (res.next()) {
                processId = res.getInt(1);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Inserito processo: " + process.getName());
            }

        } catch (SQLException e) {
            DaoUtils.rollbackTransaction(conn, e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("setProcessQuery - stop");
        }
        return processId;
    }

    /**
     * @param hpmProcessId
     *            id del processo
     * @return istanza del processo
     * @throws SQLException
     *             eccezione generata a livello DAL durante l'esecuzione della
     *             query
     */
    private Process getProcessByHpmIdQuery(final String hpmProcessId)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getProcessByHpmIdQuery - begin");
        }
        Connection conn = null;
        Process result = new Process();
        try {
            conn = Singleton.getInstance().getConnection();

            StringBuffer query =
                    new StringBuffer(
                            "SELECT * FROM process WHERE HPM_PROCESS_ID = ?");

            PreparedStatement ps = conn.prepareStatement(query.toString());
            ps.setString(1, hpmProcessId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = ProcessFactory.createProcess(rs);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getProcessByHpmIdQuery - end");
        }
        return result;
    }

    /**
     * Crea l'oggetto PrepareStatement in base al filtro in ingresso.
     * @param conn
     *            connessione al db attiva
     * @param filter
     *            filtro per i processi
     * @return oggetto PrepareStatement
     * @throws SQLException
     *             eccezione durante la creazione dello statement
     */
    private PreparedStatement generatePSFromFilter(final Connection conn,
            final ProcessFilter filter) throws SQLException {

        String query = this.generateQueryFromFilter(filter);

        if (logger.isDebugEnabled()) {
            logger.debug("Query from filter: " + query);
        }
        PreparedStatement ps = conn.prepareStatement(query);

        return ps;
    }

    /**
     * Prepara la query in base ai parametri del filtro.
     * @param filter
     *            filtro per i processi
     * @return query sui processi
     */
    private String generateQueryFromFilter(final ProcessFilter filter) {
        String query = "SELECT * FROM Process ";

        if (Validator.isValid(filter.getType())) {
            query += " INNER JOIN Process_Type ";
        }

        if (Validator.isValid(filter.getUserId())) {
            query += " INNER JOIN User ";
        }

        query += " WHERE 1=1 ";

        if (Validator.isValid(filter.getDeleted())) {
            if (filter.getDeleted().equalsIgnoreCase("TRUE")) {
                query += " AND IS_DELETED = 1 ";
            } else if (filter.getDeleted().equalsIgnoreCase("FALSE")) {
                query += " AND IS_DELETED = 0 ";
            }
        }

        if (Validator.isValid(filter.getFreeText())) {
            query += " AND (NAME LIKE '%" + filter.getFreeText() + "%' ";
            query += " OR DESCRIPTION LIKE '%" + filter.getFreeText() + "%') ";
        }

        // if (Validator.isValid(filter.getState())) {
        // query += this.generateQueryProcessState(filter.getState());
        // }

        if (Validator.isValid(filter.getType())) {
            query += " AND Process_Type.NAME = '" + filter.getType() + "' ";
        }

        if (Validator.isValid(filter.getUserId())) {
            query += " AND User.ID_USER = '" + filter.getUserId() + "'";
        }

        if (Validator.isValid(filter.getVisibility())) {
            if (filter.getVisibility().equalsIgnoreCase("TRUE")) {
                query += " AND IS_PRIVATE = 0";
            } else if (filter.getVisibility().equalsIgnoreCase("FALSE")) {
                query += " AND IS_PRIVATE = 1";
            }
        }

        if (filter.getCreationDateFrom() != null) {
            query +=
                    " AND DATE_CREATED >= '"
                            + SQL_DATE_FORMATTER.format(filter
                                    .getCreationDateFrom()) + "'";
        }

        if (filter.getCreationDateTo() != null) {
            query +=
                    " AND DATE_CREATED <= '"
                            + SQL_DATE_FORMATTER.format(filter
                                    .getCreationDateTo()) + "'";
        }

        if (filter.getDueDateFrom() != null) {
            query +=
                    " AND DATE_DUE >= '"
                            + SQL_DATE_FORMATTER
                                    .format(filter.getDueDateFrom()) + "'";
        }

        if (filter.getDueDateTo() != null) {
            query +=
                    " AND DATE_DUE <= '"
                            + SQL_DATE_FORMATTER.format(filter.getDueDateTo())
                            + "'";
        }

        if (filter.getSort() != null) {
            query += this.generateSortCriteria(filter.getSort());
        }

        return query;
    }

    /**
     * Genera la porzione di query per l'ordinamento.
     * @param sort
     *            oggetto SortCriteria contenente la colonna e l'ordine. Se
     *            l'ordine non è impostato viene impostato quello crescente
     * @return query order by
     */
    private String generateSortCriteria(final SortCriteria sort) {
        String query = "";

        if (Validator.isValid(sort.getFieldName())) {
            if (it.webscience.kpeople.service.datatypes.SortCriteria.ORDER_BY_LAST_UPDATE
                    .equals(sort.getFieldName())) {
                query += " ORDER BY LAST_ACTION_DATE";
            } else if (it.webscience.kpeople.service.datatypes.SortCriteria.ORDER_BY_CREATION_DATE
                    .equals(sort.getFieldName())) {
                query += " ORDER BY DATE_CREATED";
            } else if (it.webscience.kpeople.service.datatypes.SortCriteria.ORDER_BY_DUE_DATE
                    .equals(sort.getFieldName())) {
                query += " ORDER BY DATE_DUE";
            }

            if (Validator.isValid(sort.getOrder())) {
                query += " " + sort.getOrder();
            } else {
                query += " ASC";
            }
        }

        return query;
    }

    /**
     * Prepara la query per filtrare i processi in base allo stato.
     * @param state
     *            stato dei processi richiesto
     * @return query sql
     */
    private String generateQueryProcessState(final String state) {

        String query = "";

        if ("ALL".equalsIgnoreCase(state)) {
            query = "";
        } else if ("CLOSES".equalsIgnoreCase(state)) {
            query += " AND IS_ACTIVE = 0 ";
        } else if ("OPEN".equalsIgnoreCase(state)) {
            query += " AND IS_ACTIVE = 1 ";
        }

        return query;
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
}
