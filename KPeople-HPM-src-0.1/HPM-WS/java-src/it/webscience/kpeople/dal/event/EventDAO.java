package it.webscience.kpeople.dal.event;

import it.webscience.kpeople.be.Attachment;
import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.CommunicationEvent;
import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.EventFilter;
import it.webscience.kpeople.be.EventMetadata;
import it.webscience.kpeople.be.ObjectType;
import it.webscience.kpeople.be.PatternMetadata;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.impl.sesame.OCRdfRepository;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.cross.AttachmentDAO;
import it.webscience.kpeople.dal.cross.EventMetadataDAO;
import it.webscience.kpeople.dal.cross.KeywordDAO;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.keyword.ObjectTypeFactory;
import it.webscience.kpeople.dal.util.DataAccessConstants;
import it.webscience.kpeople.util.DaoUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.xml.crypto.Data;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

/**
 * DAO per l'accesso su db alle classi HPM.
 * @author dellanna
 */
public class EventDAO implements IEventDAO {
    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public EventDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }


    /**
     * ritorna l'oggetto ObjectType associato al name.
     * @param name
     *            name da cercare
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             eccezioni db
     * @return oggetto Keyword
     */
    private ObjectType getObjectType(final String name, final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getObjectType: " + name);
        }

        String query = "SELECT * FROM object_type WHERE name = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();

        ObjectType ot = null;
        if (rs.next()) {
            ot = ObjectTypeFactory.createObjectType(rs);
        }

        return ot;
    }

    /**
     * salva le proprietà associate all'evento.
     * @param event
     *            evento
     * @param user
     *            first action performer
     * @param now
     *            first action date
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             eccezione db
     */
    /*private void saveProperties(final Event event, final User user,
            final java.util.Date now, final Connection conn)
            throws SQLException {

        KeywordDAO keywordDAO = new KeywordDAO();

        Enumeration<String> keys = event.getProperties().keys();
        while (keys.hasMoreElements()) {
            String k = (String) keys.nextElement();
            String value = event.getProperties().get(k);

            // 1. cerco l'oggetto Keyword associato alla keyword
            Keyword keyword = keywordDAO.getKeyword(k);

            // 2. cerco l'oggetto ObjectType relativo a Event
            ObjectType objectType = getObjectType("Event", conn);

            // 3. creo e salvo l'oggetto ObjectKeyword
            ObjectKeyword objectKeyword = new ObjectKeyword();
            objectKeyword.setKeyword(keyword);
            objectKeyword.setObjectType(objectType);
            objectKeyword.setIdObject(event.getIdEvent());
            objectKeyword.setValue(value);

            keywordDAO.saveObjectKeyword(objectKeyword, user, now, conn);
        }
    }*/

    /**
     * creazione del legame email-document.
     * @param event
     *            oggetto da salvare
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             if a database access error occurs
     */
    private void saveEmailDocumentManager(final Event event,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailDocumentManager - BEGIN");
        }

        for (Attachment attachment : event.getAttachments()) {
            if (attachment instanceof CommunicationEvent) {
                CommunicationEvent email = (CommunicationEvent) attachment;
                for (Document document : email.getDocList()) {
                    saveEmailDocument(email.getIdAttachment(),
                            document.getIdAttachment(), conn);
                }
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailDocumentManager - END");
        }
    }

    /**
     * scrive una riga nella tabellla event_attachment.
     * @param idAttachment
     *            idAttachment
     * @param idEvent
     *            idEvent
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             if a database access error occurs
     */
    private void saveEventAttachment(final int idAttachment, final int idEvent,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEventAttachment - BEGIN");
        }

        String sql =
                "INSERT INTO event_attachment" + "(ID_EVENT, ID_ATTACHMENT)"
                        + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, idEvent);
        pst.setInt(2, idAttachment);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEventAttachment - END");
        }
    }

    /**
     * scrive una riga nella tabellla attachment_children.
     * @param idEmail
     *            campo id_email
     * @param idDocument
     *            campo id_document
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             if a database access error occurs
     */
    private void saveEmailDocument(final int idEmail, final int idDocument,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailDocument - BEGIN");
        }

        String sql =
                "INSERT INTO attachment_children" + "(ID_ATTACHMENT_PARENT, ID_ATTACHMENT_CHILD)"
                        + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, idEmail);
        pst.setInt(2, idDocument);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailDocument - END");
        }
    }

    /**
     * salva un oggetto di tipo Event.
     * @param event
     *            oggetto da salvare su db
     * @param conn
     *            connection per la gestione della transazione
     * @param user
     *            first action performer
     * @param now
     *            first action date
     * @return id autogenerato
     * @throws SQLException
     *             if a database access error occurs
     */
    @Override
    public int saveEvent(final Event event, final User user,
            final java.util.Date now, final Connection conn)
            throws SQLException {
        
    	if (logger.isDebugEnabled()) {
            logger.debug("saveEvent - BEGIN");
        }

        String sql =
                "INSERT INTO event"
                        + "(NAME, HPM_EVENT_ID, HPM_SYSTEM_ID,"
                        + " IS_DELETED, FIRST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
                        + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE"
                        + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst =
                conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pst.setString(1, event.getName());
        pst.setString(2, event.getHpmEventId());
        pst.setString(3, event.getHpmSystemId());

        pst.setBoolean(4, false);
        pst.setInt(5, user.getIdUser());
        pst.setTimestamp(6, new Timestamp(now.getTime()));
        pst.setInt(7, user.getIdUser());
        pst.setTimestamp(8, new Timestamp(now.getTime()));

        pst.execute();

        ResultSet keys = pst.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        
    	
        if (logger.isDebugEnabled()) {
            logger.debug("saveEvent - END. id = " + id);
        }

        return id;
    }

    
    /**
	 * 
	 * @param pConn connessione verso il db hpm
	 * @param pEvent evento da aggiornare
	 * @return pattern aggiornato
	 * @throws SQLException eccezione
	 */
	@Override
    public final void updateEvent(final Connection pConn, final Event pEvent) 
		throws SQLException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("updatePattern - start" );
        }
		//Lettura parametri formali
		Event event = pEvent;
		Connection conn = pConn;
        StringBuffer sbQuery = null;
        String query = null;
        
        //Aggiorno la tabella event
        sbQuery = new StringBuffer();
        sbQuery.append("UPDATE event ");
        sbQuery.append(" SET hpm_event_id = ?  ");
        sbQuery.append(" WHERE id_event = ? ");
        query = sbQuery.toString();
            
        PreparedStatement ps = conn.prepareStatement(query.toString());
            
        ps.setString(1, event.getHpmEventId());
        ps.setInt(2, event.getIdEvent());
            
        ps.execute();
        ps.close();
            
        
		if (logger.isDebugEnabled()) {
            logger.debug("updatePattern - end");
        }
	}
	
	/**
	 * Memorizza una associazione tra un pattern e un evento
	 * @param pConn connessione verso il db HPM
	 * @param idAttachment identificativo dell'attachment da associare
	 * @param idEvent identificativo dell'evento da associare
	 * @throws SQLException
	 */
	public final void saveEventAttachmentAssociation(
			final Connection pConn,
			final int pIdAttachment, 
			final int pIdEvent) 
		throws SQLException {
		
		//Lettura parametri formali
		int idAttachment = pIdAttachment;
		int idEvent = pIdEvent;
		Connection conn = pConn;
		
		StringBuffer sbInsertQuery = new StringBuffer();
    	sbInsertQuery.append("INSERT INTO EVENT_ATTACHMENT (");
    	sbInsertQuery.append(" ID_EVENT, ");
    	sbInsertQuery.append(" ID_ATTACHMENT ");
    	sbInsertQuery.append(") VALUES (?, ?) ");
    	String query = sbInsertQuery.toString();
        
    	PreparedStatement ps =
            conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
    	
    	ps.setInt(1, idEvent);
    	ps.setInt(2, idAttachment);
    	
        ps.execute();
	}
	
	/**
	 * Verifica se esiste una associazione tra evento e attachment
	 * @param pIdAttachment identificativo dell'attachment da associare
	 * @param pIdEvent identificativo dell'evento da associare
	 * @throws SQLException
	 */
	public final boolean existsEventAttachmentAssociation(
			final int pIdAttachment, 
			final int pIdEvent) 
		throws SQLException {
		
	//Lettura parametri formali
	int idAttachment = pIdAttachment;
	int idEvent = pIdEvent;
	boolean ret = false;
		
	if (logger.isDebugEnabled()) {
            logger.debug("existsEventAttachmentAssociation - start");
        }
	Connection conn = null;
	
	try {
	    conn = Singleton.getInstance().getConnection();
	    StringBuffer sbQuery = new StringBuffer();
	    sbQuery.append("SELECT * FROM event_attachment ");
	    sbQuery.append(" WHERE id_event = ? ");
	    sbQuery.append(" AND id_attachment = ? ");
	    String query = sbQuery.toString();
		
	    PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idEvent);
            ps.setInt(2, idAttachment);
            ResultSet rs = ps.executeQuery();
		
            if (rs.next()) {
                ret = true;
            }
            rs.close();
            ps.close();
            
	} catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }   
        
	if (logger.isDebugEnabled()) {
            logger.debug("existsEventAttachmentAssociation - end");
        }
		
	return ret; 
     }
	
    /**
     * salva un oggetto di tipo AttachmentType.
     * @param at
     *            oggetto da salvare su db
     * @param user
     *            first action performer
     * @param now
     *            first action date
     * @return id autogenerato
     * @throws SQLException
     *             if a database access error occurs
     */
    public final int saveAttachmentType(final AttachmentType at,
            final User user, final java.util.Date now) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachmentType - BEGIN");
        }

        int id = 0;
        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            String sql =
                    "INSERT INTO attachment_type"
                            + "(NAME,"
                            + " IS_DELETED, FIRST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
                            + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE"
                            + ") VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst =
                    conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, at.getName());

            pst.setBoolean(2, false);
            pst.setInt(3, user.getIdUser());
            pst.setTimestamp(4, new Timestamp(now.getTime()));
            pst.setInt(5, user.getIdUser());
            pst.setTimestamp(6, new Timestamp(now.getTime()));

            pst.execute();

            ResultSet keys = pst.getGeneratedKeys();
            keys.next();
            id = keys.getInt(1);

            if (logger.isDebugEnabled()) {
                logger.debug("Transaction commit...");
            }
            
            pst.close();
            keys.close();
        } catch (SQLException e) {
            DaoUtils.rollbackTransaction(conn, e);
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachmentType - END. id = " + id);
        }

        return id;
    }

    /**
     * salva un oggetto di tipo Attachment.
     * @param attachment
     *            oggetto da salvare su db
     * @param user
     *            first action performer
     * @param now
     *            first action date
     * @param conn
     *            connection per la gestione della transazione
     * @return id autogenerato
     * @throws SQLException
     *             if a database access error occurs
     */
    private int saveAttachment(final Attachment attachment, final User user,
            final java.util.Date now, final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachment - BEGIN");
        }

        String sql =
                "INSERT INTO attachment"
                        + "(NAME, DESCRIPTION, ID_ATTACHMENT_TYPE, HPM_ATTACHMENT_ID,"
                        + " IS_DELETED, FIRST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
                        + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE"
                        + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst =
                conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pst.setString(1, null);
        pst.setString(2, attachment.getDescription());
        if (attachment.getAttachmentType() == null) {
            pst.setNull(3, java.sql.Types.INTEGER);
        } else {
            pst.setInt(3, attachment.getAttachmentType().getIdAttachmentType());
        }
        pst.setString(4, attachment.getHpmAttachmentId());

        pst.setBoolean(5, false);
        pst.setInt(6, user.getIdUser());
        pst.setTimestamp(7, new Timestamp(now.getTime()));
        pst.setInt(8, user.getIdUser());
        pst.setTimestamp(9, new Timestamp(now.getTime()));

        pst.execute();

        ResultSet keys = pst.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        attachment.setIdAttachment(id);

        if (attachment instanceof CommunicationEvent) {
            saveEmail((CommunicationEvent) attachment, conn);
        } else if (attachment instanceof Document) {
            saveDocument((Document) attachment, conn);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachment - END. id = " + id);
        }

        return id;
    }

    /**
     * salva un oggetto di tipo Document.
     * @param document
     *            oggetto da salvare su db
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             if a database access error occurs
     */
    private void saveDocument(final Document document, final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveDocument - BEGIN");
        }

        String sql =
                "INSERT INTO document"
                        + "(ID_ATTACHMENT, AUTHOR, GUID, HASHCODE)"
                        + "VALUES (?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, document.getIdAttachment());
        pst.setString(2, document.getAuthor());
        pst.setString(3, document.getGuid());
        pst.setString(4, document.getHashcode());

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveDocument - END.");
        }
    }

    /**
     * salva un oggetto di tipo EventMetadata.
     * @param em
     *            oggetto da salvare su db
     * @param user
     *            first action performer
     * @param now
     *            first action date
     * @param conn
     *            connection per la gestione della transazione
     * @return id autogenerato
     * @throws SQLException
     *             if a database access error occurs
     */
    public final int saveEventMetadata(final EventMetadata em, final User user,
            final java.util.Date now, final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEventMetadata - BEGIN");
        }

        String sql =
                "INSERT INTO event_metadata"
                        + "(KEYNAME, VALUE, ID_EVENT,"
                        + " IS_DELETED, FIRST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
                        + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE"
                        + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst =
                conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pst.setString(1, em.getKeyname());
        pst.setString(2, em.getValue());
        pst.setInt(3, em.getEvent().getIdEvent());

        pst.setBoolean(4, false);
        pst.setInt(5, user.getIdUser());
        pst.setTimestamp(6, new Timestamp(now.getTime()));
        pst.setInt(7, user.getIdUser());
        pst.setTimestamp(8, new Timestamp(now.getTime()));

        pst.execute();

        ResultSet keys = pst.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        if (logger.isDebugEnabled()) {
            logger.debug("saveEventMetadata - END. id = " + id);
        }

        return id;
    }

    /**
     * salva un oggetto di tipo Email.
     * @param email
     *            oggetto da salvare su db
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             if a database access error occurs
     */
    private void saveEmail(final CommunicationEvent email, final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmail - BEGIN");
        }

        String sql =
                "INSERT INTO email"
                        + "(ID_ATTACHMENT, EMAIL_OBJECT, EMAIL_BODY)"
                        + "VALUES (?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, email.getIdAttachment());
        pst.setString(2, email.getObject());
        pst.setString(3, email.getBody());
        pst.execute();

        for (User cc : email.getCcUser()) {
            saveEmailCc(cc.getEmail(), email.getIdAttachment(), conn);
        }

        for (User ccn : email.getCcnUser()) {
            saveEmailCcn(ccn.getEmail(), email.getIdAttachment(), conn);
        }

        if (email.getUserFrom() != null) {
            saveEmailFrom(
                    email.getUserFrom().getEmail(),
                    email.getIdAttachment(), conn);
        }

        for (User to : email.getToUser()) {
            saveEmailTo(to.getEmail(), email.getIdAttachment(), conn);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmail - END.");
        }
    }

    /**
     * salva una riga nella tabella EMAIL_CC.
     * @param email
     *            email
     * @param idEmail
     *            id della mail
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             if a database access error occurs
     */
    private void saveEmailCc(final String email, final int idEmail,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailCc - BEGIN");
        }

        String sql =
                "INSERT INTO EMAIL_CC" + "(EMAIL, ID_EMAIL)" + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, email);
        pst.setInt(2, idEmail);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailCc - END.");
        }
    }

    /**
     * salva una riga nella tabella EMAIL_CCN.
     * @param email
     *            email
     * @param idEmail
     *            id della mail
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             if a database access error occurs
     */
    private void saveEmailCcn(final String email, final int idEmail,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailCcn - BEGIN");
        }

        String sql =
                "INSERT INTO EMAIL_CCN" + "(EMAIL, ID_EMAIL)" + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, email);
        pst.setInt(2, idEmail);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailCcn - END.");
        }
    }

    /**
     * salva una riga nella tabella EMAIL_FROM.
     * @param email
     *            email
     * @param idEmail
     *            id della mail
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             if a database access error occurs
     */
    private void saveEmailFrom(final String email, final int idEmail,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailFrom - BEGIN");
        }

        String sql =
                "INSERT INTO EMAIL_FROM" + "(EMAIL, ID_EMAIL)"
                        + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, email);
        pst.setInt(2, idEmail);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailFrom - END.");
        }
    }

    /**
     * salva una riga nella tabella EMAIL_TO.
     * @param email
     *            email
     * @param idEmail
     *            id della mail
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             if a database access error occurs
     */
    private void saveEmailTo(final String email, final int idEmail,
            final Connection conn) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailTo - BEGIN");
        }

        String sql =
                "INSERT INTO EMAIL_TO" + "(EMAIL, ID_EMAIL)" + "VALUES (?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, email);
        pst.setInt(2, idEmail);

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveEmailTo - END.");
        }
    }

    /**
     * Restituisce gli eventi associati ad un processo.
     * @param eventFilter business entity.
     * @param user user che ha effettuo la chiamata al servizio.
     * @return lista degli eventi associati al processo.
     * @throws KPeopleDAOException eccezione sollevata durante l'elaborazione.
     */
    public final List<Event> getEvents(final EventFilter eventFilter,
            final User user) throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("start - getEvents");
        }
        List<Event> results = new ArrayList<Event>();
        try {
            // 1. interrogazione di Sesame
            List<String> listEventIdsFromSesame =
                getSesameEventIds(eventFilter);

//            if (eventFilter.getFreeText() != null) {
                // 2. interrogazione di solr
//            }

            // 3. interrogazione dell'hpm
            results = getEventsByHpmEventIds(listEventIdsFromSesame);

        } catch (Exception e) {
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end - getEvents");
        }

        return results;
    }

    /**
     * Metodo che effettua un'interrogazione su Sesame e recupera gli id-events.
     * @param eventFilter business entity contenente i parametri di ricerca
     * @return lista degli id degli eventi associati ad un processi
     * @throws RepositoryException eccezione durante l'elaborazione.
     * @throws MalformedQueryException eccezione durante l'elaborazione.
     * @throws QueryEvaluationException eccezione durante l'elaborazione.
     */
    private List<String> getSesameEventIds(final EventFilter eventFilter)throws
    RepositoryException, MalformedQueryException, QueryEvaluationException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getSasameEventIds");
        }
        List<String> listEventsIds = new ArrayList<String>();

        Repository sesameRepository =
            OCRdfRepository.getInstance().getSesameRepository();

        RepositoryConnection con = sesameRepository.getConnection();
        String query = null;
        String prefix =
            "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"
                    + "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                    + "PREFIX protons:<http://proton.semanticweb.org/2005/04/protons#>"
                    + "PREFIX protont:<http://proton.semanticweb.org/2005/04/protont#>"
                    + "PREFIX kp:<http://kpeople.webscience.it/ontologies/2010/12/kpbase#>";
        
        if (eventFilter.getHpmPatternId() != null) {
            query =
                prefix
                + "SELECT ?Event WHERE {"
                + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#" + eventFilter.getHpmPatternId() + ">"
                + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventGenerate>"
                + "?Event}";
        } else if (eventFilter.getHpmUserId() == null) {
            query =
                prefix
                    + "SELECT ?Event WHERE { "
                    + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#"+eventFilter.getHpmProcessId()+">"
                    + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> "
                    + "?Event } ";
        } else {
            query =
                prefix
                    + "SELECT ?Event WHERE { "
                    + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#"+eventFilter.getHpmProcessId()+">"
                    + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> ?Event ."
                    + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventCreator> "
                    + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#" + eventFilter.getHpmUserId() + " }";
        }

        logger.debug("Query: " + query);
        TupleQuery tupleQuery =
            con.prepareTupleQuery(QueryLanguage.SPARQL, query);

        TupleQueryResult result = tupleQuery.evaluate();

        listEventsIds = addToEventIdList(result);
        result.close();
        con.close();
        return listEventsIds;
    }

    /**
     * @param result resultset Sesame
     * @return lista degli indici degli eventi
     * @throws QueryEvaluationException eccezione durante l'elaborazione.
     */
    private List<String> addToEventIdList(
            final TupleQueryResult result) throws QueryEvaluationException {
        List<String> listEventFromSesame = new ArrayList<String>();
        while (result.hasNext()) {
            BindingSet bindingSet = result.next();

            String hpmEventId =
                bindingSet.getValue("Event").toString().split("#")[1];

            listEventFromSesame.add(hpmEventId);
            }
        return listEventFromSesame;
        }


    /*private List<String> getSolrEventIds(final EventFilter filter)
        throws SolrServerException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getSolrIds");
        }

        List<String> listEventsIds = new ArrayList<String>();

        SolrServer solrServer = OCSolrServer.getInstance().getSolrServer();
        SolrQuery solrQuery = new SolrQuery();

        solrQuery.setRows(1000);

        String[] tokens = filter.getFreeText().split(" ");
        String queryFilter = "";
        for (String token : tokens) {
            queryFilter += "+*" + token + "* ";
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
                //out.add(id.toString());
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end getSolrIds");
        }
        return null;
    }*/

    /**
     * @param listEventIdsFromSesame lista degli id degli eventi
     * @return lista degli eventi
     * @throws SQLException eccezione generata durante l'interrogazione del DB
     * @throws RepositoryException eccezione durante l'accesso a sesame
     * @throws SolrServerException eccezione durante l'accesso a solr
     */
    public List<Event> getEventsByHpmEventIds(
            final List<String>listEventIdsFromSesame)
            throws SQLException, RepositoryException, SolrServerException {

        if (logger.isDebugEnabled()) {
            logger.debug("getEventsByHpmEventId - start");
        }

        List<Event> listEvent = new ArrayList<Event>();

//      definizione dei dao
        AttachmentDAO attachDAO = new AttachmentDAO();
        EventMetadataDAO eventMetadataDAO = new EventMetadataDAO();
        KeywordDAO keywordDAO = new KeywordDAO();
        UserDAO userDAO = new UserDAO();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Singleton.getInstance().getConnection();
            for (String eventId : listEventIdsFromSesame) {
//              per ogni id, recupero i dati dalla tabella 'event'
                String query = "SELECT * FROM event WHERE HPM_EVENT_ID = ?";

                ps = conn.prepareStatement(query);
                ps.setString(1, eventId);
                rs = ps.executeQuery();
                while (rs.next()) {
                   Event event = EventFactory.createEvent(rs);

//                 popolo gli attachments per associati all'evento
                   event.setAttachments(
                         attachDAO.getAttachmentsByIdEvent(event.getIdEvent()));

//                 popolo i metadati associati all'evento
                   event.setEventMetadata(eventMetadataDAO.
                           getEventMetadataByHpmEventId(
                                   eventId,
                                   event.getIdEvent()));

//                popolo le keywords associate all'evento
                  event.setKeyword(keywordDAO.
                          getKeywordByEventId(event.getIdEvent()));

//                recupero il creatore dell'evento
                  for (EventMetadata em : event.getEventMetadata()) {
                      if (em.getKeyname().equalsIgnoreCase(
                              DataAccessConstants.METADATA_EMAIL_AUTHOR)) {
                          User user = null;
                          try {
                              user = userDAO.getUserByHpmUserId(
                                      em.getValue());
                              event.setUser(user);
                              em.setValue(user.getScreenName());
                          } catch (Exception e) {
                              user = new User();
                              user.setEmail(em.getValue());
                              user.setHpmUserId(em.getValue());
                              logger.warn(
                                      "user non trovato: " + em.getValue());
//                            non viene modificato il valore del metadato
                          }
                      }
                  }
//                aggiunta dell'evento alla lista
                  listEvent.add(event);
                }
                rs.close();
                ps.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        return listEvent;
    }
    
    
}
