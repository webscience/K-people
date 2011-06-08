package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.document.DocumentFactory;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.openrdf.repository.RepositoryException;

public class CommunicationEventDAO {

    /**
     * loggger
     */
    private Logger logger;
    
    /**
     * Costruttore
     */
    public CommunicationEventDAO() {
        logger = Logger.getLogger(this.getClass().getName());
    }
    
   /* public final List<User> getUsersTo(final EmailEvent in) {
        String xml = null;
        List<User> listUserTo = new ArrayList<User>();
        List<EventMetadata> listEventMetadata = in.getEventMetadata(); 
        for (int i = 0; i < listEventMetadata.size(); i++) {
            if (listEventMetadata.get(i).getKeyname().equalsIgnoreCase("details")) {
                xml = listEventMetadata.get(i).getValue();
                break;
            }
        }
        List<String> listHpmUserIds = MailParser.get
        
    }*/

    public final List<User>getUsersTo(
            final int idAttachment) throws KPeopleDAOException {

        List<User>listUsersTo = new ArrayList<User>();
        if (logger.isDebugEnabled()) {
            logger.debug("getUsersTo: " + idAttachment);
        }
        Connection conn = null;
        User user = null;
        try {
            conn = Singleton.getInstance().getConnection();
            String query = "SELECT e.EMAIL FROM email_to e "
                           + "WHERE e.ID_ATTACHMENT = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idAttachment);

            ResultSet rs = ps.executeQuery();
            UserDAO userDAO = new UserDAO();
            while (rs.next()) {
                String hpmUserID = rs.getString("EMAIL");
                try {
                    user = userDAO.getUserByHpmUserId(hpmUserID);
                } catch (NamingException e) {
//              se l'utente non è nel dao, viene creato mediante la sua mail

                    user = new User();
                    user.setEmail(hpmUserID);
                    user.setHpmUserId(hpmUserID);
                }
                listUsersTo.add(user);
            }

            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw new KPeopleDAOException(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new KPeopleDAOException(e.getMessage());
            }
        }
        return listUsersTo;
    }

    public final List<User>getCcUser(final int idAttachment)
    throws KPeopleDAOException {
        List<User>listCcUsers = new ArrayList<User>();

        if (logger.isDebugEnabled()) {
            logger.debug("getCcUser: " + idAttachment);
        }
        Connection conn = null;
        User user = null;
        try {
            conn = Singleton.getInstance().getConnection();
            String query = "SELECT e.EMAIL FROM email_cc e "
                           + "WHERE e.ID_ATTACHMENT = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idAttachment);

            ResultSet rs = ps.executeQuery();
            UserDAO userDAO = new UserDAO();
            while (rs.next()) {
                String hpmUserID = rs.getString("EMAIL");
                try {
                    user = userDAO.getUserByHpmUserId(hpmUserID);
                } catch (NamingException e) {
//              se l'utente non è nel dao, viene creato mediante la sua mail

                    user = new User();
                    user.setEmail(hpmUserID);
                    user.setHpmUserId(hpmUserID);
                }
                listCcUsers.add(user);
            }
            rs.close();
            ps.close();
            

        } catch (SQLException e) {
            throw new KPeopleDAOException(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new KPeopleDAOException(e.getMessage());
            }
        }
        return listCcUsers;
    }

    
    public final User getUserFrom(final int idAttachment) 
        throws KPeopleDAOException
     {

        if (logger.isDebugEnabled()) {
            logger.debug("getUserFrom: " + idAttachment);
        }
        Connection conn = null;
        User user = null;
        try {
            conn = Singleton.getInstance().getConnection();
            String query = "SELECT e.EMAIL FROM email_from e "
                           + "WHERE e.ID_ATTACHMENT = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idAttachment);

            ResultSet rs = ps.executeQuery();
            UserDAO userDAO = new UserDAO();
            while (rs.next()) {
                String hpmUserID = rs.getString("EMAIL");

                try {
                    user = userDAO.getUserByHpmUserId(hpmUserID);
                } catch (NamingException e) {
//                  se l'utente non è nel dao, viene creato mediante la sua mail

                    user = new User();
                    user.setEmail(hpmUserID);
                    user.setHpmUserId(hpmUserID);
                }
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new KPeopleDAOException(e.getMessage());
        } catch (KPeopleDAOException e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new KPeopleDAOException(e.getMessage());
            }
        }

        return user;
    }

    public final List<User>getCcnUser(final int idAttachment)
    throws KPeopleDAOException {
        List<User>listCcnUsers = new ArrayList<User>();

        if (logger.isDebugEnabled()) {
            logger.debug("getCcnUser: " + idAttachment);
        }
        Connection conn = null;
        User user = null;
        try {
            conn = Singleton.getInstance().getConnection();
            String query = "SELECT e.EMAIL FROM email_ccn e "
                           + "WHERE e.ID_ATTACHMENT = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idAttachment);

            ResultSet rs = ps.executeQuery();
            UserDAO userDAO = new UserDAO();
            while (rs.next()) {
                String hpmUserID = rs.getString("EMAIL");
                try {
                    user = userDAO.getUserByHpmUserId(hpmUserID);
                } catch (NamingException e) {
//              se l'utente non è nel dao, viene creato mediante la sua mail

                    user = new User();
                    user.setEmail(hpmUserID);
                    user.setHpmUserId(hpmUserID);
                }
                listCcnUsers.add(user);
            }
            rs.close();
            ps.close();
            
            
        } catch (SQLException e) {
            throw new KPeopleDAOException(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new KPeopleDAOException(e.getMessage());
            }
        }
        return listCcnUsers;
    }

    
    public final List<Document> getDocList(int idAttachment)
    throws SQLException {
        List<Document> docList = new ArrayList<Document>();

        if (logger.isDebugEnabled()) {
            logger.debug("getDocList: " + idAttachment);
        }
        Connection conn = null;
        Document doc = null;

        try {
            conn = Singleton.getInstance().getConnection();
            String query =  "SELECT * "
                          + "FROM email e inner join attachment_children ed ON e.ID_ATTACHMENT = ed.ID_ATTACHMENT_PARENT "
                          + "inner join document d on d.ID_ATTACHMENT = ed.ID_ATTACHMENT_CHILD"
                          + "WHERE e.ID_ATTACHMENT = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idAttachment);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                doc = DocumentFactory.createDocument(rs);
                docList.add(doc);
            }
            rs.close();
            ps.close();
            

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        return docList;
    }
}
