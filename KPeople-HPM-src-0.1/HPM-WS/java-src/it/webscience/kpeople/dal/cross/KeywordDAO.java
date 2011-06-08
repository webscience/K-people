package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.be.Keyword;
import it.webscience.kpeople.dal.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author depascalis
 * DAO per l'accesso su db alle classi Keyword.
 */
public class KeywordDAO {

    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public KeywordDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }

    public final List<Keyword> getKeywordByEventId(
            final int eventId) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getKeywordByHpmEventId: " + eventId);
        }

        Keyword keyword = null;
        List<Keyword> listKeyword = new ArrayList<Keyword>();
        Connection conn = null;

        try {
            conn = Singleton.getInstance().getConnection();
            String queryKeyword = "SELECT keyword.ID_KEYWORD, keyword.KEYWORD, keyword.DESCRIPTION,"
                + "keyword.HPM_KEYWORD_ID, object_keyword.VALUE, object_keyword.DESCRIPTION AS DESCR "
                + "FROM keyword, object_keyword, object_type "
                + "WHERE (keyword.ID_KEYWORD = object_keyword.ID_KEYWORD)"
                + "AND (object_keyword.ID_OBJECT_TYPE = "
                + "object_type.ID_OBJECT_TYPE)"
                + "AND(object_type.NAME ='event')"
                + "AND(object_keyword.ID_OBJECT = ?)";

            PreparedStatement ps = conn.prepareStatement(queryKeyword);
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                keyword = KeywordFactory.createKeyword(rs);
                listKeyword.add(keyword);
            }
            rs.close();
            ps.close();
            
            return listKeyword;

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

    }
}
