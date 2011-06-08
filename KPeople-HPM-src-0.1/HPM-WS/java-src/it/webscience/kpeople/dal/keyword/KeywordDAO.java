package it.webscience.kpeople.dal.keyword;

import it.webscience.kpeople.be.Keyword;
import it.webscience.kpeople.be.ObjectKeyword;
import it.webscience.kpeople.be.ObjectType;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.Singleton;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * DAO per l'accesso su db alle classi Keyword.
 */
public class KeywordDAO implements IKeywordDAO {
    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public KeywordDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * ritorna l'oggetto Keyword associato alla chiave desiderata.
     * @param idKeyword
     *            chiave
     * @throws SQLException
     *             eccezioni db
     * @return oggetto Keyword
     */
    public final Keyword getKeywordByIdKeyword(final int idKeyword)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getKeywordByIdKeyword: " + idKeyword);
        }

        Keyword k = null;
        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            String query = "SELECT * FROM keyword WHERE ID_KEYWORD = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idKeyword);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                k = KeywordFactory.createKeyword(rs);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return k;
    }

    /**
     * Recupera le keywords associate ad un oggetto.
     * @param ot
     *            tipologia di oggetto
     * @param id
     *            id dell'oggetto
     * @return lista di oggetti Keyword
     * @throws SQLException
     *             eccezione generica
     */
    public final List<Keyword> getKeywordsByObjectType(final ObjectType ot,
            final int id) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getKeywordsByObjectType");
        }

        List<Keyword> list = new ArrayList<Keyword>();

        Connection conn = null;
        try {
            conn = Singleton.getInstance().getConnection();

            String query =
                    "SELECT * FROM object_keyword "
                            + "WHERE ID_OBJECT_TYPE = ? AND ID_OBJECT = ?";

            logger.debug("id object type: " + ot.getIdObjectType());
            logger.debug("id object: " + id);

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ot.getIdObjectType());
            ps.setInt(2, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String value = rs.getString("VALUE");

                int idKeyword = rs.getInt("ID_KEYWORD");

                Keyword keyword = getKeywordByIdKeyword(idKeyword);
                keyword.setValue(value);
                list.add(keyword);
            }
            
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return list;
    }

    /**
     * scrive una riga nella tabellla object_keyword.
     * @param ok
     *            oggetto da salvare
     * @param user
     *            first action performer
     * @param now
     *            first action date
     * @param conn
     *            connection per la gestione della transazione
     * @throws SQLException
     *             if a database access error occurs
     */
    public final void saveObjectKeyword(final ObjectKeyword ok,
            final User user, final java.util.Date now, final Connection conn)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveObjectKeyword - BEGIN");
        }

        String sql =
                "INSERT INTO object_keyword"
                        + "(ID_KEYWORD, ID_OBJECT_TYPE, ID_OBJECT, VALUE,"
                        + " IS_DELETED, FIRST_ACTION_PERFORMER, FIRST_ACTION_DATE, "
                        + " LAST_ACTION_PERFORMER, LAST_ACTION_DATE"
                        + ") VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, ok.getKeyword().getIdKeyword());
        pst.setInt(2, ok.getObjectType().getIdObjectType());
        pst.setInt(3, ok.getIdObject());
        pst.setString(4, ok.getValue());

        pst.setBoolean(5, false);
        pst.setInt(6, user.getIdUser());
        pst.setTimestamp(7, new Timestamp(now.getTime()));
        pst.setInt(8, user.getIdUser());
        pst.setTimestamp(9, new Timestamp(now.getTime()));

        pst.execute();

        if (logger.isDebugEnabled()) {
            logger.debug("saveObjectKeyword - END");
        }
    }

    /**
     * ritorna l'oggetto Keyword associato alla chiave desiderata.
     * @param k
     *            chiave
     * @throws SQLException
     *             eccezioni db
     * @return oggetto Keyword
     */
    public final Keyword getKeyword(final String k) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("getKeyword: " + k);
        }

        Keyword keyword = null;
        Connection conn = null;

        try {
            conn = Singleton.getInstance().getConnection();

            String query = "SELECT * FROM keyword WHERE keyword = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, k);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                keyword = KeywordFactory.createKeyword(rs);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }

        return keyword;
    }

    /**
     * Esegue un rollback della transazione.
     * @param conn
     *            connessione al db
     * @param e
     *            eccezione lanciata
     * @throws SQLException
     *             if a database access error occurs
     */
    private void
            rollbackTransaction(final Connection conn, final SQLException e)
                    throws SQLException {
        if (conn != null) {
            conn.rollback();
            logger.fatal("Connection rollback...");
            logger.fatal(e.getStackTrace());

            StringWriter sWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(sWriter));
            logger.fatal(sWriter.getBuffer().toString());
        }
    }
}
