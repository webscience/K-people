package it.webscience.kpeople.dal.pattern.dao;

import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternMetadata;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.document.DocumentFactory;
import it.webscience.kpeople.dal.pattern.PatternFactory;
import it.webscience.kpeople.util.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

/**
 * Classe di utilità per azioni sulla base dati relazionale.
 * 
 * @author gnoni
 * 
 */
public class PatternDAOUtil {

    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public PatternDAOUtil() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * 
     * @param pConn
     *            connessione verso la base dati
     * @param pPattern
     *            pattern che si sta memorizzando
     * @return identificativo pattern database
     * @throws SQLException
     */
    public final int savePattern(final Connection pConn,
            final Pattern pPattern, final Process pProcess) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("insertPattern - start");
        }

        // Lettura parametri formali
        Connection conn = pConn;
        Pattern pattern = pPattern;
        Process process = pProcess;

        // Conversione e controllo informazioni
        int patternId = 0;

        java.sql.Timestamp sqlDateStart = null;
        if (pattern.getStartDate() != null) {
            sqlDateStart = new java.sql.Timestamp(pattern.getStartDate()
                    .getTime());
        }

        java.sql.Timestamp sqlDateDue = null;
        if (pattern.getDueDate() != null) {
            sqlDateDue = new java.sql.Timestamp(pattern.getDueDate().getTime());
        }

        java.sql.Timestamp sqlDateEnd = null;
        if (pattern.getEndDate() != null) {
            sqlDateEnd = new java.sql.Timestamp(pattern.getEndDate().getTime());
        }

        // Salvataggio tabella Attachment
        StringBuffer sbInsertQuery = new StringBuffer();
        sbInsertQuery.append("INSERT INTO ATTACHMENT (");
        sbInsertQuery.append(" NAME, ");
        sbInsertQuery.append(" DESCRIPTION, ");
        sbInsertQuery.append(" HPM_ATTACHMENT_ID, ");
        sbInsertQuery.append(" ID_ATTACHMENT_TYPE, ");
        sbInsertQuery.append(" IS_DELETED, ");
        sbInsertQuery.append(" DELETED_BY, ");
        sbInsertQuery.append(" FIRST_ACTION_PERFORMER, ");
        sbInsertQuery.append(" FIRST_ACTION_DATE, ");
        sbInsertQuery.append(" LAST_ACTION_PERFORMER, ");
        sbInsertQuery.append(" LAST_ACTION_DATE ");
        sbInsertQuery.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        String query = sbInsertQuery.toString();

        PreparedStatement ps = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, pattern.getName());
        ps.setString(2, pattern.getDescription());
        ps.setString(3, pattern.getHpmAttachmentId());
        ps.setInt(4, pattern.getAttachmentType().getIdAttachmentType());
        ps.setBoolean(5, false);
        ps.setInt(6, 0);
        ps.setInt(7, pattern.getPatternRequestor().getIdUser());
        ps.setTimestamp(8, sqlDateStart);
        ps.setInt(9, pattern.getPatternRequestor().getIdUser());
        ps.setTimestamp(10, sqlDateStart);
        ps.execute();

        ResultSet res = ps.getGeneratedKeys();

        while (res.next()) {
            patternId = res.getInt(1);
        }

        // Salvataggio nella tabella Pattern
        sbInsertQuery = new StringBuffer();
        sbInsertQuery.append("INSERT INTO PATTERN (");
        sbInsertQuery.append(" ID_ATTACHMENT, ");
        sbInsertQuery.append(" DATE_START, ");
        sbInsertQuery.append(" DATE_END, ");
        sbInsertQuery.append(" DATE_DUE, ");
        sbInsertQuery.append(" STARTED_BY, ");
        sbInsertQuery.append(" ID_PATTERN_TYPE, ");
        sbInsertQuery.append(" ACTIVITI_PROCESS_INSTANCE_ID, ");
        sbInsertQuery.append(" ID_PATTERN_STATE, ");
        sbInsertQuery.append(" HPM_PATTERN_ID, ");
        sbInsertQuery.append(" WAITING_ACTIVITY, ");
        sbInsertQuery.append(" WAITING_ACTIVITY_DATE ");
        sbInsertQuery.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        query = sbInsertQuery.toString();

        ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, patternId);
        ps.setTimestamp(2, sqlDateStart);
        ps.setTimestamp(3, sqlDateEnd);
        ps.setTimestamp(4, sqlDateDue);
        ps.setInt(5, pattern.getPatternRequestor().getIdUser());
        ps.setInt(6, pattern.getPatternType().getIdPatternType());
        ps.setString(7, pattern.getActivitiProcessInstanceId());
        ps.setInt(8, pattern.getPatternState().getIdPatternState());
        ps.setString(9, pattern.getHpmPatternId());
        ps.setBoolean(10, pattern.getPatternType().isWaitingActivity());
        ps.setTimestamp(11, null);
        ps.execute();

        return patternId;
    }

    /**
     * Memorizza i ruoli utente nella tabella user_patternrole_pattern.
     * 
     * @param pConn
     *            connessione da utilizzare verso la base dati
     * @param pUserId
     *            identificativo utente da memorizzare
     * @param pUserRole
     *            identificativo ruolo utente nel pattern
     * @param pPatternId
     *            identificativo pattern
     * @param pCreatorId
     *            identificativo creatore record
     * @param pDateStart
     *            data di creazione
     * @throws SQLException
     *             eccezione
     */
    public final void savePatternUserRole(final Connection pConn,
            final int pUserId, final int pUserRole, final int pPatternId,
            final int pCreatorId, final java.sql.Timestamp pDateStart)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("savePatternUserRole - start");
        }
        // Lettura parametri formali
        Connection conn = pConn;
        int userId = pUserId;
        int userRole = pUserRole;
        int patternId = pPatternId;
        int creatorId = pCreatorId;
        java.sql.Timestamp dateStart = pDateStart;

        // Salvataggio ruoli
        StringBuffer sbInsertQuery = new StringBuffer();
        sbInsertQuery.append("INSERT INTO USER_PATTERNROLE_PATTERN (");
        sbInsertQuery.append(" ID_USER, ");
        sbInsertQuery.append(" ID_PATTERN_ROLE, ");
        sbInsertQuery.append(" ID_PATTERN, ");
        sbInsertQuery.append(" DATE_CREATE, ");
        sbInsertQuery.append(" CREATED_BY, ");
        sbInsertQuery.append(" IS_DELETED, ");
        sbInsertQuery.append(" FIRST_ACTION_PERFORMER, ");
        sbInsertQuery.append(" FIRST_ACTION_DATE, ");
        sbInsertQuery.append(" LAST_ACTION_PERFORMER, ");
        sbInsertQuery.append(" LAST_ACTION_DATE ");
        sbInsertQuery.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        String query = sbInsertQuery.toString();

        PreparedStatement ps = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, userId);
        ps.setInt(2, userRole);
        ps.setInt(3, patternId);
        ps.setTimestamp(4, dateStart);
        ps.setInt(5, creatorId);
        ps.setBoolean(6, false);
        ps.setInt(7, creatorId);
        ps.setTimestamp(8, dateStart);
        ps.setInt(9, creatorId);
        ps.setTimestamp(10, dateStart);
        ps.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("savePatternUserRole - end");
        }
    }

    /**
     * Memorizza i metadati associati al pattern nella base di dati.
     * 
     * @param pConn
     *            connessione da utilizzare verso la base dati
     * @param pPatternId
     *            identificativo pattern
     * @param pPatternMetadata
     *            BE pattern metadata
     * @param pCreatorId
     *            identificativo creatore record
     * @param pDateStart
     *            data di creazione
     * @throws SQLException
     *             eccezione
     * 
     */
    public final void savePatternMetadata(final Connection pConn,
            final int pPatternId, final PatternMetadata pPatternMetadata,
            final int pCreatorId, final java.sql.Timestamp pDateStart)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("savePatternMetadata - start");
        }

        // Lettura parametri formali
        Connection conn = pConn;
        int patternId = pPatternId;
        PatternMetadata patternMetadata = pPatternMetadata;
        int creatorId = pCreatorId;
        java.sql.Timestamp dateStart = pDateStart;

        // Salvataggio ruoli
        StringBuffer sbInsertQuery = new StringBuffer();
        sbInsertQuery.append("INSERT INTO PATTERN_METADATA (");
        sbInsertQuery.append(" KEYNAME, ");
        sbInsertQuery.append(" VALUE, ");
        sbInsertQuery.append(" ID_PATTERN, ");
        sbInsertQuery.append(" ACTIVITI_PROCESS_METADATA, ");
        sbInsertQuery.append(" IS_DELETED, ");
        sbInsertQuery.append(" FIRST_ACTION_PERFORMER, ");
        sbInsertQuery.append(" FIRST_ACTION_DATE, ");
        sbInsertQuery.append(" LAST_ACTION_PERFORMER, ");
        sbInsertQuery.append(" LAST_ACTION_DATE ");
        sbInsertQuery.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        String query = sbInsertQuery.toString();

        PreparedStatement ps = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, patternMetadata.getKeyname());
        ps.setString(2, patternMetadata.getValue());
        ps.setInt(3, patternId);
        ps.setBoolean(4, patternMetadata.isActivitiProcessMetadata());
        ps.setBoolean(5, false);
        ps.setInt(6, creatorId);
        ps.setTimestamp(7, dateStart);
        ps.setInt(8, creatorId);
        ps.setTimestamp(9, dateStart);
        ps.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("savePatternMetadata - end");
        }

    }

    /**
     * Carica i dettagli di un pattern dalla base dati HPM
     * 
     * @param pPatternId
     * @return
     * @throws SQLException
     */
    public final Pattern loadPatternByPatternId(final int pPatternId)
            throws SQLException {

        // Lettura parametri formali
        Connection conn = null;
        int patternId = pPatternId;

        // Esecuzione query sulla tabella ATTACHEMENT
        StringBuffer sbQuerySelect = null;
        String query = null;

        sbQuerySelect = new StringBuffer();
        sbQuerySelect.append("SELECT *, p.id_attachment id_attachment ");
        sbQuerySelect.append(" FROM attachment a, pattern p ");
        sbQuerySelect.append(" WHERE a.id_attachment = p.id_attachment ");
        sbQuerySelect.append("  AND p.id_attachment = ? ");
        query = sbQuerySelect.toString();
        Pattern pattern = null;
        try {
            conn = Singleton.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query.toString());
            ps.setInt(1, patternId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pattern = PatternFactory.createPattern(rs);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            DaoUtils.rollbackTransaction(conn, e);
        } finally {
            conn.close();
        }

        if (pattern != null) {
            // Carica BE relative agli utenti del pattern
            pattern.setPatternRequestor(getPatternRequestor(patternId));
            pattern.setPatternProvider(getPatternProvider(patternId));
            pattern.setCcUsers(getPatternCCUsers(patternId));

//          associo gli attachments al pattern
            pattern.setDocList(getDocListByIdPattern(pPatternId));
        }
        return pattern;
    }

    /**
     * Estrae gli attachments associati ad un pattern.
     * @param idPattern id del pattern
     * @return lista di documenti
     * @throws SQLException eccezione durante l'estrazione
     */
    public final List<Document> getDocListByIdPattern(
            final int idPattern) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getDocListByIdPattern: " + idPattern);
        }

        List<Document> docList = new ArrayList<Document>();
        Connection conn = null;

        try {
            conn = Singleton.getInstance().getConnection();

            String query =
                "SELECT"
                + "   a.ID_ATTACHMENT,"
                + "   a.DESCRIPTION,"
                + "   a.IS_DELETED ,"
                + "   a.DELETED_BY ,"
                + "   a.DELETED_DATE ,"
                + "   a.FIRST_ACTION_PERFORMER ,"
                + "   a.FIRST_ACTION_DATE ,"
                + "   a.LAST_ACTION_PERFORMER ,"
                + "   a.LAST_ACTION_DATE ,"
                + "   a.ID_ATTACHMENT_TYPE ,"
                + "   a.HPM_ATTACHMENT_ID ,"
                + "   a.NAME,"

                + "   d.AUTHOR ,"
                + "   d.IS_TEMPLATE ,"
                + "   d.ID_DOCUMENT_TYPE ,"
                + "   d.GUID ,"
                + "   d.HASHCODE "

                + "FROM attachment a "
                + "INNER JOIN document d"
                + "      ON a.ID_ATTACHMENT = d.ID_ATTACHMENT "
                + "INNER JOIN attachment_children ac"
                + "      ON a.ID_ATTACHMENT = ac.ID_ATTACHMENT_CHILD "
                + " WHERE ac.ID_ATTACHMENT_PARENT = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idPattern);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idAttachment = rs.getInt("ID_ATTACHMENT");

                logger.debug("id attachment:" + idAttachment);

                Document doc = DocumentFactory.createDocument(rs);
                doc.setName(rs.getString("NAME"));
                docList.add(doc);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return docList;
    }

    /**
     * Carica i dettagli di un pattern dalla base dati HPM
     * 
     * @param pHpmPatternId
     * @return
     * @throws SQLException
     */
    public final Pattern loadPatternByHpmPatternId(final String pHpmPatternId)
            throws SQLException {

        // Lettura parametri formali
        Connection conn = null;
        String hpmPatternId = pHpmPatternId;

        int idPattern = getPatternIdFromHpmPatternId(hpmPatternId);
        return loadPatternByPatternId(idPattern);
    }

    /**
     * 
     * @param pIdPattern
     *            pattern del quale si sta estraendo il pattern provider.
     * @return pattern provider
     * @throws SQLException
     */
    private User getPatternProvider(final int pIdPattern) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getPatternProvider - start " + pIdPattern);
        }

        // Lettura parametri formali
        int idPattern = pIdPattern;

        List<User> u = getPatternUsersByRoleAndPatternId(idPattern,
                Pattern.PATTERN_ROLE_PROVIDER);

        User provider = null;
        if (u != null && u.size() == 1) {
            provider = u.get(0);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternProvider - end");
        }
        return provider;
    }

    /**
     * 
     * @param pIdPattern
     *            pattern del quale si sta estraendo il pattern requestor.
     * @return pattern requestor
     * @throws SQLException
     */
    private User getPatternRequestor(final int pIdPattern) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getPatternRequestor - start " + pIdPattern);
        }

        // Lettura parametri formali
        int idPattern = pIdPattern;

        List<User> u = getPatternUsersByRoleAndPatternId(idPattern,
                Pattern.PATTERN_ROLE_REQUESTOR);

        User requestor = null;
        if (u != null && u.size() == 1) {
            requestor = u.get(0);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternRequestor - end");
        }
        return requestor;
    }

    /**
     * 
     * @param pIdPattern
     *            pattern del quale si sta estraendo il pattern requestor.
     * @return pattern requestor
     * @throws SQLException
     */
    private List<User> getPatternCCUsers(final int pIdPattern)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternCCUsers - start " + pIdPattern);
        }

        // Lettura parametri formali
        int idPattern = pIdPattern;

        List<User> u = getPatternUsersByRoleAndPatternId(idPattern,
                Pattern.PATTERN_ROLE_CCUSERS);

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternCCUsers - end");
        }

        return u;
    }

    /**
     * 
     * @param pIdPattern
     *            pattern del quale si sta estraendo gli user
     * @param pPatternRoleId
     *            ruolo assunto dagli utenti nel pattern
     * @return pattern requestor
     * @throws SQLException
     */
    private List<User> getPatternUsersByRoleAndPatternId(final int pIdPattern,
            final int pPatternRoleId) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternUsersByRoleAndPatternId - start "
                    + pIdPattern + " " + pPatternRoleId);
        }

        // Lettura parametri formali
        int idPattern = pIdPattern;
        int patternRoleId = pPatternRoleId;

        Connection conn = Singleton.getInstance().getConnection();
        StringBuffer sbQuerySelect = null;
        String query = null;

        sbQuerySelect = new StringBuffer();
        sbQuerySelect.append("SELECT id_user FROM user_patternrole_pattern ");
        sbQuerySelect.append(" WHERE is_deleted = false ");
        sbQuerySelect.append("  AND id_pattern = ? ");
        sbQuerySelect.append("  AND id_pattern_role = ? ");
        query = sbQuerySelect.toString();
        List<User> users = null;

        try {
            PreparedStatement ps = conn.prepareStatement(query.toString());
            ps.setInt(1, idPattern);
            ps.setInt(2, patternRoleId);

            ResultSet rs = ps.executeQuery();
            users = new ArrayList<User>();

            while (rs.next()) {
                User u = new UserDAO().getUserByIdUser(rs.getInt("id_user"));
                users.add(u);
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternUsersByRoleAndPatternId - end ");
        }

        return users;
    }

    /**
     * Recupera il patternId (attachmentId) a partire da HpmPatternId
     * 
     * @param pHpmPatternId
     *            identificativo hpm del pattern
     * @return identificativo del pattern
     * @throws SQLException
     */
    public final int getPatternIdFromHpmPatternId(final String pHpmPatternId)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternIdFromHpmPatternId - start "
                    + pHpmPatternId);
        }

        // Lettura parametri formali
        String hpmPatternId = pHpmPatternId;
        int patternId = 0;

        StringBuffer sbQuerySelect = null;
        String query = null;

        sbQuerySelect = new StringBuffer();
        sbQuerySelect.append("SELECT p.id_attachment ");
        sbQuerySelect.append(" FROM pattern p, attachment a ");
        sbQuerySelect.append(" WHERE p.id_attachment = a.id_attachment ");
        sbQuerySelect.append("  AND is_deleted = false ");
        sbQuerySelect.append("  AND hpm_pattern_id = ? ");
        query = sbQuerySelect.toString();

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query.toString());
            ps.setString(1, hpmPatternId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                patternId = rs.getInt("ID_ATTACHMENT");
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternIdFromHpmPatternId - end ");
        }

        return patternId;
    }

    /**
     * Recupera il patternId (attachmentId) a partire da HpmPatternId
     * 
     * @param pHpmPatternId
     *            identificativo hpm del pattern
     * @return identificativo del pattern
     * @throws SQLException
     */
    public final List<Integer> getPatternIdListFromHpmPatternIdList(
            final List<String> pHpmPatternIdList) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternIdListFromHpmPatternIdList - start "
                    + pHpmPatternIdList);
        }

        // Lettura parametri formali
        List<String> hpmPatternIdList = pHpmPatternIdList;

        List<Integer> patternIdList = new ArrayList<Integer>();

        for (int i = 0; i < hpmPatternIdList.size(); i++) {
            int newId = getPatternIdFromHpmPatternId(hpmPatternIdList.get(i));
            patternIdList.add(newId);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternIdFromHpmPatternId - end ");
        }

        return patternIdList;
    }

    /**
     * Recupera il patternId (attachmentId) a partire da HpmPatternId
     * 
     * @param pHpmPatternId
     *            identificativo hpm del pattern
     * @return identificativo del pattern
     * @throws SQLException
     */
    public final int getPatternIdFromActivitiProcessInstanceId(
            final String pActivitiProcessInstanceId) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternIdFromActivitiProcessInstanceId - start "
                    + pActivitiProcessInstanceId);
        }

        // Lettura parametri formali
        String activitiProcessInstanceId = pActivitiProcessInstanceId;
        int patternId = 0;

        StringBuffer sbQuerySelect = null;
        String query = null;

        sbQuerySelect = new StringBuffer();

        sbQuerySelect.append("SELECT p.id_attachment ");
        sbQuerySelect.append(" FROM pattern p, attachment a ");
        sbQuerySelect.append(" WHERE p.id_attachment = a.id_attachment ");
        sbQuerySelect.append("  AND is_deleted = false ");
        sbQuerySelect.append("  AND activiti_process_instance_id = ? ");
        query = sbQuerySelect.toString();
        Connection conn = null;

        try {
            conn = Singleton.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query.toString());
            ps.setString(1, activitiProcessInstanceId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                patternId = rs.getInt("ID_ATTACHMENT");
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternIdFromActivitiProcessInstanceId - end ");
        }

        return patternId;
    }

    /**
     * Recupera HpmPatternId a partire da patternId (attachmentId).
     * 
     * @param pPatternId
     *            identificativo del pattern
     * @return identificativo HPM del pattern
     * @throws SQLException
     */
    public final String getHpmPatternIdFromPatternId(final int pPatternId)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getHpmPatternIdFromPatternId - start " + pPatternId);
        }

        // Lettura parametri formali
        int patternId = pPatternId;
        String hpmPatternId = "";

        StringBuffer sbQuerySelect = null;
        String query = null;

        sbQuerySelect = new StringBuffer();
        sbQuerySelect.append("SELECT hpm_pattern_id ");
        sbQuerySelect.append(" FROM pattern p, attachment a ");
        sbQuerySelect.append(" WHERE p.id_attachment = a.id_attachment ");
        sbQuerySelect.append("  AND is_deleted = false ");
        sbQuerySelect.append("  AND p.id_attachment = ? ");
        query = sbQuerySelect.toString();

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query.toString());
            ps.setInt(1, pPatternId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                hpmPatternId = rs.getString("HPM_PATTERN_ID");
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getHpmPatternIdFromPatternId - end ");
        }

        return hpmPatternId;
    }

    public final Pattern getPatternFromActivitiProcessInstanceId(
            final String pActivitiProcessInstanceId) 
        throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternFromActivitiProcessInstanceId - start "
                    + pActivitiProcessInstanceId);
        }

        // Lettura parametri formali
        String activitiProcessInstanceId = pActivitiProcessInstanceId;

        Pattern pattern = null;
        int patternId = getPatternIdFromActivitiProcessInstanceId(
                pActivitiProcessInstanceId);
        pattern = loadPatternByPatternId(patternId);

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternFromActivitiProcessInstanceId - end "
                    + activitiProcessInstanceId);
        }

        return pattern;
    }

    public String createPatternIdStringList(List<Integer> idPatterns) {

        String ret = "";
        for (int i = 0; i < idPatterns.size(); i++) {
            int a = idPatterns.get(i).intValue();
            if (ret.equals("")) {
                ret = "" + a;
            } else {
                ret = ret + ", " + a;
            }
        }
        return ret;
    }

    /**
     * 
     * @param pIdOwner
     * @return
     * @throws SQLException
     */
    public final List<String> getAllHpmProcessIdByOwnerId(final int pIdOwner)
            throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getAllHpmProcessIdByOwnerId - start "
                    + pIdOwner);
        }
        
        // Lettura parametri formali
        int idOwner = pIdOwner;

        StringBuffer sbQuery = new StringBuffer();
        String query = null;

        sbQuery.append(" SELECT hpm_process_id ");
        sbQuery.append(" FROM process ");
        sbQuery.append(" WHERE is_deleted = false  ");
        sbQuery.append(" AND id_user_owner = ? ");
        query = sbQuery.toString();
        
        Connection conn = null;
        List<String> hpmPatternIdList = new ArrayList<String>();
        
        try {
            conn = Singleton.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idOwner);
            ResultSet rs = ps.executeQuery();

            hpmPatternIdList = new ArrayList<String>();

            while (rs.next()) {
                String hpmProcessId = rs.getString("hpm_process_id");
                hpmPatternIdList.add(hpmProcessId);
            }
            rs.close();
            ps.close();
            
        }  catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        
        if (logger.isDebugEnabled()) {
            logger.debug("getAllHpmProcessIdByOwnerId - end ");
        }
        
        return hpmPatternIdList;
    }

    /**
     * 
     * @param pPattern
     * @param pLoggedUser
     * @return
     * @throws SQLException
     */
    public Pattern closePattern(Pattern pPattern, User pLoggedUser)
            throws SQLException {

        // Lettura parametri formali
        Pattern pattern = pPattern;
        User loggedUser = pLoggedUser;

        
        // TODO gestire le transazioni
        // conn.setAutoCommit(true);

        java.sql.Timestamp sqlDateEnd = null;
        if (pattern.getEndDate() != null) {
            sqlDateEnd = new java.sql.Timestamp(pattern.getEndDate().getTime());
        }

        java.sql.Timestamp sqlDateCloseActivity 
            = new java.sql.Timestamp(new Date().getTime());
        
        
        StringBuffer sbQuery = null;
        String query = "";
        PreparedStatement ps = null;

        Connection conn = null;
        
        conn = Singleton.getInstance().getConnection();
        conn.setAutoCommit(false);
        // Aggiornamento tabella attachment
        try {
            sbQuery = new StringBuffer();
            sbQuery.append("UPDATE attachment SET");
            sbQuery.append(" last_action_performer = ?, ");
            sbQuery.append(" last_action_date = ? ");
            sbQuery.append("WHERE ");
            sbQuery.append(" id_attachment = ? ");
            query = sbQuery.toString();
        
            ps = conn.prepareStatement(query);
            ps.setInt(1, loggedUser.getIdUser());
            ps.setTimestamp(2, sqlDateCloseActivity);
            ps.setInt(3, pattern.getIdAttachment());
            ps.executeUpdate();

            // Aggiornamento tabella pattern
            sbQuery = new StringBuffer();
            sbQuery.append("UPDATE pattern SET ");
            sbQuery.append(" date_end = ?, ");
            sbQuery.append(" closed_by = ?, ");
            sbQuery.append(" waiting_activity = ?, ");
            sbQuery.append(" waiting_activity_date = ? ");
            sbQuery.append(" WHERE ");
            sbQuery.append(" id_attachment = ? ");
            query = sbQuery.toString();

            ps = conn.prepareStatement(query);
            ps.setTimestamp(1, sqlDateCloseActivity);
            ps.setInt(2, loggedUser.getIdUser());
            ps.setBoolean(3, false);
            ps.setTimestamp(4, sqlDateCloseActivity );
            ps.setInt(5, pattern.getIdAttachment());
            ps.executeUpdate();

            pattern.setWaitingActivity(false);

            conn.commit();
            ps.close();
        } catch (SQLException e) {
            DaoUtils.rollbackTransaction(conn, e);
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }    
        
        return pattern;
    }

    /**
     * 
     * @param pPattern
     * @return
     * @throws SQLException
     */
    public final Pattern updatePatternState(final Pattern pPattern)
            throws SQLException {

        // Lettura parametri formali
        Pattern pattern = pPattern;

        // Preparazione query
        StringBuffer sbQuery = new StringBuffer();
        String query = null;
        sbQuery.append("UPDATE pattern SET ");
        sbQuery.append(" id_pattern_state = ? ");
        sbQuery.append(" WHERE id_attachment = ? ");
        query = sbQuery.toString();

        // Esecuzione query
        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pattern.getPatternState().getIdPatternState());
            ps.setInt(2, pattern.getIdAttachment());
            ps.executeUpdate();

            // Chiusura connessione
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        
        return pPattern;
    }

}
