package it.webscience.kpeople.dal.document;

import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.DocumentFilter;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.impl.sesame.OCRdfRepository;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

/**
 * Classe per l'accesso alla tabella DOCUMENT.
 */
public class DocumentDAO implements IDocumentDAO {
    /** logger. */
    private Logger logger;

    /**
     * Costruttore di default.
     */
    public DocumentDAO() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * ritorna l'oggetto Document associato agli hashcodes.
     * @param hashcodes lista di hashcodes
     * @throws SQLException eccezioni db
     * @return oggetto User
     */
    private List<Document> getDocumentsByHashcodes(
            final List<String> hashcodes)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getDocumentsByHashcodes - start  ");
            logger.debug("hashcodes: " + hashcodes.toString());
        }

        List<Document> documents = new ArrayList<Document>();

//      non eseguo alcuna operazione se non ho hashcodes in lista
        if (hashcodes.size() == 0) {
            return documents;
        }

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            StringBuffer querySb = new StringBuffer(
                "SELECT "
                + "   doc.AUTHOR, "
                + "   doc.IS_TEMPLATE, "
                + "   doc.ID_DOCUMENT_TYPE, "
                + "   doc.ID_ATTACHMENT, "
                + "   doc.GUID,"
                + "   doc.HASHCODE,"
                + "   att.DESCRIPTION,"
                + "   att.HPM_ATTACHMENT_ID,"
                + "   att.NAME "
                + "FROM document AS doc, attachment AS att "
                + "WHERE "
                + "   doc.ID_ATTACHMENT = att.ID_ATTACHMENT AND "
                + "   att.IS_DELETED = FALSE AND "
                + "     doc.HASHCODE IN (");
            for (String hashcode : hashcodes) {
                querySb.append("'" + hashcode + "',");
            }
            String query = querySb.substring(0, querySb.length() - 1);
            PreparedStatement ps = conn.prepareStatement(query + ")");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Document d = DocumentFactory.createDocument(rs);
                d.setDescription(rs.getString("DESCRIPTION"));
                d.setHpmAttachmentId(rs.getString("HPM_ATTACHMENT_ID"));
                d.setName(rs.getString("NAME"));
                documents.add(d);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return documents;
    }

    /**
     * ritorna l'oggetto Document associato agli hashcodes.
     * @param idAttachment idAttachment
     * @throws SQLException eccezioni db
     * @return oggetto User
     */
    public final Document getDocumentByIdAttachment(
            final int idAttachment) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getDocumentByIdAttachment - start  ");
        }

        Document document = new Document();

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            String query =
                "SELECT "
                + "   doc.AUTHOR, "
                + "   doc.IS_TEMPLATE, "
                + "   doc.ID_DOCUMENT_TYPE, "
                + "   doc.ID_ATTACHMENT, "
                + "   doc.GUID,"
                + "   doc.HASHCODE,"
                + "   att.DESCRIPTION,"
                + "   att.HPM_ATTACHMENT_ID,"
                + "   att.NAME "
                + "FROM document AS doc, attachment AS att "
                + "WHERE "
                + "   doc.ID_ATTACHMENT = att.ID_ATTACHMENT AND "
                + "   att.IS_DELETED = FALSE AND "
                + "   att.ID_ATTACHMENT = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idAttachment);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                document = DocumentFactory.createDocument(rs);
                document.setDescription(rs.getString("DESCRIPTION"));
                document.setHpmAttachmentId(rs.getString("HPM_ATTACHMENT_ID"));
                document.setName(rs.getString("NAME"));
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return document;
    }

    /**
     * legge il file contenente la query.
     * @param hpmProcessId hpm process id da cui ricavare i documenti
     * @return query sesame
     * @throws IOException eccezione dovuto all'accesso al file di query
     */
    private String getQueryFindDocument(final String hpmProcessId)
            throws IOException {

        String prefix =
            "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
            + "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"
            + "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
            + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
            + "PREFIX protons:<http://proton.semanticweb.org/2005/04/protons#>"
            + "PREFIX protont:<http://proton.semanticweb.org/2005/04/protont#>"
            + "PREFIX kp:<http://kpeople.webscience.it/ontologies/2010/12/kpbase#>";

        String query =
            prefix
            + "SELECT ?Document WHERE { "
            + "{ <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#" + hpmProcessId + ">"
            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> ?Event . "
            + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventAttachment> ?Document "
            + "} UNION { "
            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#" + hpmProcessId + ">"
            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> ?Event . "
            + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventGenerate> ?SubEvent . "
            + "?SubEvent <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventAttachment> ?Document } }";

        return query;
    }

    /**
     * Ricerca del primo utente che ha caricato un
     * documento in un dato processo.
     * @param hpmProcessId riferimento al processo
     * @param hashcode riferimento al documento
     * @return utente che per primo ha caricato il documento
     * @throws KPeopleDAOException eccezione
     */
    public final User findFirstUploader(
            final String hpmProcessId,
            final String hashcode) throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("start findDocuments");
        }

        User user = null;
        Date eventInsertDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.S'Z'");

        try {
//          1. interrogazione di sesame per ottenere gli hashcodes
            Repository sesameRepository =
                OCRdfRepository.getInstance().getSesameRepository();

            RepositoryConnection con = sesameRepository.getConnection();

            String query = getQueryUploaders(hpmProcessId, hashcode);
            logger.debug("Query: " + query);

            TupleQuery tupleQuery =
                    con.prepareTupleQuery(QueryLanguage.SPARQL, query);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();

                String email =
                    bindingSet.getValue("User").toString().split("#")[1];

//              ricavo l'utente che ha caricato per primo il documento
                Date parsed = sdf.parse(
                        bindingSet.getValue("InsertDate").toString()
                        .replaceAll("\"", ""));

                if (eventInsertDate == null
                     || parsed.getTime() < eventInsertDate.getTime()) {
                    eventInsertDate = parsed;
                }

                user  = new User();
                user.setHpmUserId(email);
            }

            result.close();

//          ricavo l'oggetto User completo di screnname
            UserDAO userDAO = new UserDAO();
            if (user != null) {
                try {
                    user = userDAO.getUserByHpmUserId(user.getHpmUserId());
                } catch (NamingException e) {
                    logger.warn(e.getMessage());
                }
            }

        } catch (Exception e) {
            throw new KPeopleDAOException(e.getMessage());
        }


        
        return user;
    }

    /**
     * ricava l'elenco degli utenti che hanno caricato un determinato documento
     * per un determinato processo.
     * @param hpmProcessId riferimento al processo
     * @param hashcode riferimento al documento
     * @return query
     * @throws IOException eccezione dovuto all'accesso al file di query
     */
    private String getQueryUploaders(
            final String hpmProcessId,
            final String hashcode) throws IOException {

        String query =
            "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
            + "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"
            + "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
            + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
            + "PREFIX protons:<http://proton.semanticweb.org/2005/04/protons#>"
            + "PREFIX protont:<http://proton.semanticweb.org/2005/04/protont#>"
            + "PREFIX kp:<http://kpeople.webscience.it/ontologies/2010/12/kpbase#>"
            + "SELECT ?User ?InsertDate WHERE { { "
            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#" + hpmProcessId + ">"
            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> ?Event . "
            + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventAttachment> "
            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#" + hashcode + "> . "
            + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventCreator> ?User . "
            + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventInsertDate> ?InsertDate "
            + "} UNION { "
            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#" + hpmProcessId + ">"
            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> ?Event . "
            + "?Event <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventGenerate> ?SubEvent . "
            + "?SubEvent <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventCreator> ?User . "
            + "?SubEvent <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventInsertDate> ?InsertDate ."
            + "?SubEvent <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventAttachment>"
            + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#" + hashcode + ">"
            + "} }";

        return query;
    }

    @Override
    /**
     * Ricerca dei documenti in base al filtro.
     * @param filter filtro di ricerca
     */
    public final List<Document> findDocuments(final DocumentFilter filter)
            throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("start findDocuments");
        }

        List<Document> documents = new ArrayList<Document>();

        try {
//          1. interrogazione di sesame per ottenere gli hashcodes
            Repository sesameRepository =
                OCRdfRepository.getInstance().getSesameRepository();

            RepositoryConnection con = sesameRepository.getConnection();

            String query = getQueryFindDocument(filter.getHpmProcessId());
            logger.debug("Query: " + query);

            TupleQuery tupleQuery =
                    con.prepareTupleQuery(QueryLanguage.SPARQL, query);

            TupleQueryResult result = tupleQuery.evaluate();
            List<String> hashcodes = new ArrayList<String>();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();

                String hashcode =
                    bindingSet.getValue("Document").toString().split("#")[1];
                hashcodes.add(hashcode);
            }

            result.close();

//          2. interrogazione dell'hpm
            documents = getDocumentsByHashcodes(hashcodes);
        } catch (Exception e) {
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end findDocuments. Documenti trovati: "
                    + documents.size());
        }

        return documents;
    }

    /**
     * Cariaca un documento in base all'id.
     * @param id hashcode del documento
     * @return documento
     * @throws KPeopleDAOException eccezione in caso di errori
     */
    public final Document getDocumentByHpmId(final String id)
        throws KPeopleDAOException {
        List<String> ids = new ArrayList<String>();

        ids.add(id);

        try {
            return getDocumentsByHashcodes(ids).get(0);
        } catch (SQLException e) {
            throw new KPeopleDAOException(e.getMessage());
        }
    }

    /**
     * salva nella tabella dello storico download l'evento di download corrente.
     * @param document documento scaricato
     * @param user utenet che effettua il download
     * @throws KPeopleDAOException eccezione durante l'elaborazione
     */
    public final void saveDocumentDownloadHistory(
            final Document document,
            final User user) throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("saveDocumentDownloadHistory - BEGIN");
        }

        Date now = new Date();
        int id = 0;
        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            String sql =
                "INSERT INTO document_download_history "
                + "(ID_USER, ID_ATTACHMENT, DOWNLOAD_DATE) "
                + "VALUES (?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, user.getIdUser());
            pst.setInt(2, document.getIdAttachment());
            pst.setTimestamp(3, new Timestamp(now.getTime()));

            pst.execute();

            ResultSet keys = pst.getGeneratedKeys();
            keys.next();
            id = keys.getInt(1);

            conn.commit();

            if (logger.isDebugEnabled()) {
                logger.debug("Transaction commit...");
            }
            
            keys.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleDAOException(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new KPeopleDAOException(e.getMessage());
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveAttachmentType - END. id = " + id);
        }
    }
}
