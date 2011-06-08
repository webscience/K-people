package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.impl.sesame.OCRdfRepository;
import it.webscience.kpeople.bll.impl.solr.OCSolrServer;
import it.webscience.kpeople.dal.ActivitySingleton;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.ocLdapAbstract.OcLdapAdapterAgent;
import it.webscience.kpeople.dal.ocLdapAbstract.OcLdapUser;
import it.webscience.kpeople.dal.ocOpenLdap.OcOpenLdapFactory;
import it.webscience.kpeople.service.exception.KPeopleServiceException;
import it.webscience.kpeople.util.LdapUtils;
import it.webscience.kpeople.util.SesamePropertyKey;
import it.webscience.kpeople.util.SolrIndexName;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.dtd.InternalEntityDecl;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultDocumentType;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

/**
 * DAO per l'accesso su db alle classi User.
 * @author dellanna
 */
public class UserDAO implements IUserDAO {
    /** logger. */
    private Logger logger;

    /** Costruttore di default. */
    public UserDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * ritorna l'oggetto User associato alla chiave desiderata.
     * @param idUser
     *            chiave
     * @throws SQLException
     *             eccezioni db
     * @return oggetto User
     */
    public final User getUserByIdUser(final int idUser) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getUserByIdUser: " + idUser);
        }

        User user = null;

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            String query = "SELECT * FROM user WHERE ID_USER = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idUser);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = UserFactory.createUser(rs);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return user;
    }

    /**
     * ritorna l'oggetto User associato alla chiave desiderata.
     * @param hpmUserId
     *            chiave univoca
     * @throws SQLException
     *             eccezioni db
     * @return oggetto User
     * @throws NamingException
     *             eccezione ldap
     * @throws SolrServerException
     *             eccezione solr
     * @throws RepositoryException
     *             eccezione sesame
     */
    public final User getUserByHpmUserId(final String hpmUserId)
            throws KPeopleDAOException, NamingException {
        if (logger.isDebugEnabled()) {
            logger.debug("getUserByHpmUserId: " + hpmUserId);
        }

        User user = null;

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            String query = "SELECT * FROM user WHERE HPM_USER_ID = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, hpmUserId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = UserFactory.createUser(rs);
            } else {
                // interrogazione ldap

                NamingEnumeration<SearchResult> answer =
                        LdapUtils.ldapSearch("(&(mail={0}))",
                                new Object[] {hpmUserId});
                if (answer == null) {
                    throw new NamingException(
                            "getUserByHpmUserId :Ricerca LDAP nulla per user: "
                                    + hpmUserId);
                }
                user = LdapUtils.createUserBefromLdap(answer);
                if (user == null) {
                    throw new NamingException(
                            "getUserByHpmUserId :Ricerca user LDAP nulla per : "
                                    + hpmUserId);
                }
                // inserimento db hpm
                user = saveHpmUser(user);
                // inserimento solr
                indexUserSolr(user);
                // inserimento sesame
                indexUserSesame(user);
                // inserimento in activity
                saveActivityUser(user);
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                throw new KPeopleDAOException(e1.getMessage());
            }
            throw new KPeopleDAOException(e.getMessage());
        } catch (NamingException e) {
            throw e;
        } catch (SolrServerException e) {
            throw new KPeopleDAOException(e.getMessage());
        } catch (RepositoryException e) {
            throw new KPeopleDAOException(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new KPeopleDAOException(e.getMessage());
            }
        }

        return user;
    }

    /**
     * @param user
     *            user be avvalorato con id
     * @return oggetto user be
     * @throws SQLException
     *             eccezioni db
     */
    private User saveHpmUser(final User user) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveHpmUser START: " + user.getHpmUserId());
        }
        Connection conn = null;
        // user da ulizzare per first_action _performer e last_action
        // _performer.
        Integer hpmUserID =
                Integer.parseInt(Singleton.getInstance().getProperty(
                        "hpm.userId"));

        try {
            conn = Singleton.getInstance().getConnection();
            java.util.Date now = new java.util.Date();
            String sql =
                    "INSERT INTO user" + "(USERNAME, ACCOUNT,"
                            + " IS_DELETED, FIRST_ACTION_PERFORMER, "
                            + "FIRST_ACTION_DATE, "
                            + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE, "
                            + "HPM_USER_ID, ROLE, FIRST_NAME, LAST_NAME, "
                            + "SCREEN_NAME, EMAIL" + ") "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst =
                    conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, user.getUsername());
            pst.setString(2, user.getAccount());

            pst.setBoolean(3, false);
            pst.setInt(4, hpmUserID);
            pst.setTimestamp(5, new Timestamp(now.getTime()));
            pst.setInt(6, hpmUserID);
            pst.setTimestamp(7, new Timestamp(now.getTime()));

            pst.setString(8, user.getHpmUserId());
            pst.setString(9, null); // no role
            pst.setString(10, user.getFirstName());
            pst.setString(11, user.getLastName());
            pst.setString(12, user.getScreenName());
            pst.setString(13, user.getHpmUserId());

            pst.execute();

            ResultSet keys = pst.getGeneratedKeys();
            keys.next();
            int id = keys.getInt(1);
            user.setIdUser(id);
            
            keys.close();
            pst.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("saveHpmUser END: " + user.getHpmUserId());
        }
        return user;
    }

    /**
     * Metodo per indicizzare lo user sul server solr.
     * @param user
     *            user be che deve essere indicizzato.
     * @throws SolrServerException
     *             eccezione generata durante la connessione al server solr.
     */
    private void indexUserSolr(final User user) throws SolrServerException {

        if (logger.isDebugEnabled()) {
            logger.debug("indexUserSolr - start");
        }

        SolrServer solrServer = OCSolrServer.getInstance().getSolrServer();
        SolrInputDocument doc = new SolrInputDocument();

        String userId = user.getHpmUserId();
        if (userId != null) {
            String keyId = SolrIndexName.SOLR_ID;
            String keyUserId = SolrIndexName.SOLR_USER_ID;
            Object userIdValue = userId;
            doc.setField(keyUserId, userIdValue);
            doc.setField(keyId, userIdValue);
        }

        String userFirstName = user.getFirstName();
        if (userFirstName != null) {
            String keyUserFirstName = SolrIndexName.SOLR_USER_FIRSTNAME;
            Object userFirstNameValue = userFirstName;
            doc.setField(keyUserFirstName, userFirstNameValue);
        }

        String userLastName = user.getLastName();
        if (userLastName != null) {
            String keyUserLastName = SolrIndexName.SOLR_USER_LASTNAME;
            Object userLastNameValue = userLastName;
            doc.setField(keyUserLastName, userLastNameValue);
        }

        String userAccount = user.getAccount();
        if (userAccount != null) {
            String keyUserAccountValue = SolrIndexName.SOLR_USER_ACCOUNT;
            Object userAccountValue = userAccount;
            doc.setField(keyUserAccountValue, userAccountValue);
        }
        String userMail = user.getHpmUserId();
        if (userMail != null) {
            String keyUserMail = SolrIndexName.SOLR_USER_MAIL;
            Object userMailValue = userMail;
            doc.setField(keyUserMail, userMailValue);
        }
        try {
            UpdateResponse updateResponseAdd = solrServer.add(doc);
            UpdateResponse updateResponseCommit = solrServer.commit();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SolrServerException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("indexUserSolr - end");
        }
    }

    /**
     * Metodo per indicizzare lo user su sesame.
     * @param user
     *            user che deve esssere indicizzato.
     * @throws RepositoryException
     *             errore generato durante l'accesso al repository sesame.
     */
    private void indexUserSesame(final User user) throws RepositoryException {
        if (logger.isDebugEnabled()) {
            logger.debug("indexUserSesame - start");
        }

        Document rdfDocument = DocumentHelper.createDocument();
        rdfDocument = createSesameDocSchema(rdfDocument);

        Element rdfRoot = rdfDocument.getRootElement();

        // creazione elemento user.
        Element individual =
                rdfRoot.addElement(Singleton.getInstance().getProperty(
                        SesamePropertyKey.OWL_NAME_INDIVIDUAL));
        String rdfAbout =
                Singleton.getInstance()
                        .getProperty(SesamePropertyKey.RDF_ABOUT);
        String rdfAboutValue =
                Singleton.getInstance().getProperty(SesamePropertyKey.KPBASE)
                        + user.getHpmUserId();
        individual.addAttribute(rdfAbout, rdfAboutValue);

        String rdfType =
                Singleton.getInstance().getProperty(SesamePropertyKey.RDF_TYPE);

        Element type = individual.addElement(rdfType);

        // aggiungo elementi rdf:type
        String rdfResource =
                Singleton.getInstance().getProperty(
                        SesamePropertyKey.RDF_RESOURCE);
        String rdfResourceValue =
                Singleton.getInstance().getProperty(
                        SesamePropertyKey.KPBASE_USER);
        type.addAttribute(rdfResource, rdfResourceValue);

        // creo l'elemento account
        String account =
                Singleton.getInstance().getProperty(
                        SesamePropertyKey.KPBASE_ACCOUNT);
        Element accountElement = individual.addElement(account);
        accountElement.setText(user.getAccount());

        commitToSesame(rdfDocument);

        if (logger.isDebugEnabled()) {
            logger.debug("indexUserSesame - end");
        }
    }

    /**
     * fare refactoring con i processi. Metodo per creare lo scheletro del
     * documento rdf contenente i riferimenti ai necessari namespace.
     * @param rdfDocument
     *            documento che deve essere popolato
     * @return un oggetto di tipo Document con lo scheletro del documento rdf.
     */
    private Document createSesameDocSchema(final Document rdfDocument) {

        if (logger.isDebugEnabled()) {
            logger.debug("createSesameDocSchema - start");
        }

        Document doc = rdfDocument;
        Element rdfRoot = doc.getRootElement();

        String terms = SesamePropertyKey.TERMS;
        String termsValue = Singleton.getInstance().getProperty(terms);

        String owl = SesamePropertyKey.OWL;
        String owlValue = Singleton.getInstance().getProperty(owl);

        String xsd = SesamePropertyKey.XSD;
        String xsdValue = Singleton.getInstance().getProperty(xsd);

        String rdfs = SesamePropertyKey.RDFS;
        String rdfsValue = Singleton.getInstance().getProperty(rdfs);

        String rdf = SesamePropertyKey.RDF;
        String rdfValue = Singleton.getInstance().getProperty(rdf);

        String kpbase = SesamePropertyKey.KPBASE;
        String kpbaseValue = Singleton.getInstance().getProperty(kpbase);

        String rdfDoctype = SesamePropertyKey.RDF_DOCTYPE;
        String rdfDoctypeValue =
                Singleton.getInstance().getProperty(rdfDoctype);

        Vector<InternalEntityDecl> entityDeclList =
                new Vector<InternalEntityDecl>();
        entityDeclList.add(new InternalEntityDecl(terms, termsValue));

        entityDeclList.add(new InternalEntityDecl(owl, owlValue));

        entityDeclList.add(new InternalEntityDecl(xsd, xsdValue));

        entityDeclList.add(new InternalEntityDecl(rdfs, rdfsValue));

        entityDeclList.add(new InternalEntityDecl(rdf, rdfValue));

        entityDeclList.add(new InternalEntityDecl(kpbase, kpbaseValue));

        DefaultDocumentType docType = new DefaultDocumentType();
        docType.setInternalDeclarations(entityDeclList);
        docType.setName(rdfDoctypeValue);
        doc.setDocType(docType);

        rdfRoot = doc.addElement(rdfDoctypeValue);
        Namespace namespace = new Namespace("", kpbaseValue);
        rdfRoot.addAttribute("xml:base", kpbaseValue);
        Namespace namespacerdfs = new Namespace(rdfs, rdfsValue);
        Namespace namespacekpbase = new Namespace(kpbase, kpbaseValue);
        Namespace namespaceterms = new Namespace(terms, termsValue);
        Namespace namespaceowl = new Namespace(owl, owlValue);
        Namespace namespacexsd = new Namespace(xsd, xsdValue);
        Namespace namespacerdf = new Namespace(rdf, rdfValue);

        // add the created namespaces to the document</span>
        doc.getRootElement().add(namespace);

        doc.getRootElement().add(namespacerdfs);
        doc.getRootElement().add(namespacekpbase);
        doc.getRootElement().add(namespaceterms);
        doc.getRootElement().add(namespaceowl);
        doc.getRootElement().add(namespacexsd);
        doc.getRootElement().add(namespacerdf);

        if (logger.isDebugEnabled()) {
            logger.debug("createSesameDocSchema - end");
        }

        return doc;
    }

    /**
     * fare refactoring con i processi. Metodo per l'invio del file rdf,
     * rappresentativo dei dati da inserire all'interno dell'ontologia presente
     * sul server Sesame.
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
     * save te user in activity probe db.
     * @param user
     *            user
     * @throws SQLException
     *             db exception
     */
    private void saveActivityUser(final User user) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveActivityUser" + user.getHpmUserId());
        }

        Connection conn = null;
        // user da ulizzare per first_action _performer e last_action
        // _performer.
        String activityGroup =
                Singleton.getInstance().getProperty("activity.group");
        try {
            conn = ActivitySingleton.getInstance().getConnection();

            String sql =
                    "INSERT INTO act_id_user"
                            + "(ID_, REV_, FIRST_, LAST_, EMAIL_, PWD_ ) "
                            + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql.toString());

            String password = "password";
            
            pst.setString(1, user.getHpmUserId());
            pst.setInt(2, 1);
            pst.setString(3, user.getFirstName());
            pst.setString(4, user.getLastName());
            pst.setString(5, user.getHpmUserId());
            pst.setString(6, password);

            pst.execute();

            sql =
                    "INSERT INTO act_id_membership" + "(USER_ID_, GROUP_ID_) "
                            + "VALUES (?, ?)";
            pst = conn.prepareStatement(sql.toString());

            pst.setString(1, user.getHpmUserId());
            pst.setString(2, activityGroup);

            pst.execute();

            pst.close();
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveHpmUser END: " + user.getHpmUserId());
        }
    }

    /**
     * ritorna l'oggetto User associato allo username desiderato o una lista
     * completa di tutto gli utenti.
     * @param username username da ricercare.
     *          null se si vuole estrarre la lista completa
     * @throws KPeopleServiceException eccezione durante l'elaborazione
     */
    @Override
    public List<User> getUserByUsername(String username) {
        if (logger.isDebugEnabled()) {
            logger.debug("getUserByUsername BEGIN:" + username);
        }

        OcLdapUser ocLdapUser = new OcLdapUser();
        ocLdapUser.setUsername(username);

        OcOpenLdapFactory ocLdapFactory = new OcOpenLdapFactory();

        OcLdapAdapterAgent ocLdapAdapterAgent =
            ocLdapFactory.createLdapAdapterAgent();

        List<OcLdapUser> response = null;
        try {
            response = ocLdapAdapterAgent.search(ocLdapUser);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        List<User> users = new ArrayList<User>();
        if (response != null) {
            for (OcLdapUser u : response) {
                User user = new User();

                if (u != null) {
                    user.setEmail(u.getEmail());
                    user.setUsername(u.getUsername());
                    user.setScreenName(u.getName());
                }

                users.add(user);
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getUserByUsername END: " + username);
        }
        return users;
    }
}
