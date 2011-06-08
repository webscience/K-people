package it.webscience.kpeople.dal;

import it.webscience.kpeople.be.Keyword;
import it.webscience.kpeople.be.ObjectType;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.ProcessType;
import it.webscience.kpeople.be.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * factory per le classi HPM.
 * @author dellanna
 */
public class HpmFactory {

    /**
     * factory per l'oggetto Keyword.
     * @param rs resultset
     * @return istanza dell'oggetto Keyword
     * @throws SQLException label colonne non valido
     */
    public static Keyword createKeyword(final ResultSet rs)
            throws SQLException {
        Keyword keyword = new Keyword();
        keyword.setIdKeyword(rs.getInt("ID_KEYWORD"));
        keyword.setKeyword(rs.getString("KEYWORD"));
        keyword.setHpmKeywordId(rs.getString("HPM_KEYWORD_ID"));

        return keyword;
    }

    /**
     * factory per l'oggetto ObjectType.
     * @param rs resultset
     * @return istanza dell'oggetto ObjectType
     * @throws SQLException label colonne non valido
     */
    public static ObjectType createObjectType(final ResultSet rs)
            throws SQLException {

        ObjectType ot = new ObjectType();
        ot.setIdObjectType(rs.getInt("ID_OBJECT_TYPE"));
        ot.setName(rs.getString("NAME"));
        ot.setDeleted(rs.getBoolean("IS_DELETED"));
        ot.setDeletedBy(new User(rs.getInt("DELETED_BY")));
        ot.setDeletedDate(rs.getTimestamp("DELETED_DATE"));
        ot.setFirstActionPerformer(
                new User(rs.getInt("FIRST_ACTION_PERFORMER")));
        ot.setFirstActionDate(rs.getTimestamp("FIRST_ACTION_DATE"));
        ot.setLastActionPerformer(new User(rs.getInt("LAST_ACTION_PERFORMER")));
        ot.setLastActionDate(rs.getTimestamp("LAST_ACTION_DATE"));

        return ot;
    }

    /**
     * factory per l'oggetto User.
     * @param rs resultset
     * @return istanza dell'oggetto User
     * @throws SQLException label colonne non valido
     */
    public static User createUser(final ResultSet rs) throws SQLException {

        User user = new User();
        user.setUsername(rs.getString("USERNAME"));
        user.setAccount(rs.getString("ACCOUNT"));
        user.setIdUser(rs.getInt("ID_USER"));
        user.setDeleted(rs.getBoolean("IS_DELETED"));
        user.setDeletedBy(new User(rs.getInt("DELETED_BY")));
        user.setDeletedDate(rs.getTimestamp("DELETED_DATE"));
        user.setFirstActionPerformer(new User(rs
                .getInt("FIRST_ACTION_PERFORMER")));
        user.setFirstActionDate(rs.getTimestamp("FIRST_ACTION_DATE"));
        user.setLastActionPerformer(
                new User(rs.getInt("LAST_ACTION_PERFORMER")));
        user.setLastActionDate(rs.getTimestamp("LAST_ACTION_DATE"));
        user.setHpmUserId(rs.getString("HPM_USER_ID"));

        return user;
    }

    /**
     * factory per l'oggetto Process.
     * @param rs resultset
     * @return istanza dell'oggetto Process
     * @throws SQLException label colonne non valido
     */
    public static Process createProcess(final ResultSet rs)
            throws SQLException {
        Process process = new Process();

        process.setActive(rs.getBoolean("IS_ACTIVE"));
        process.setDateCreated(rs.getDate("DATE_CREATED"));
        process.setDateDue(rs.getDate("DATE_DUE"));
        process.setDeleted(rs.getBoolean("IS_DELETED"));
        process.setDeletedBy(new User());
        process.getDeletedBy().setIdUser(rs.getInt("DELETED_BY"));
        process.setDeletedDate(rs.getDate("DELETED_DATE"));
        process.setDescription(rs.getString("DESCRIPTION"));
        process.setFirstActionDate(rs.getDate("FIRST_ACTION_DATE"));
        process.setFirstActionPerformer(new User());
        process.getFirstActionPerformer().setIdUser(
                rs.getInt("FIRST_ACTION_PERFORMER"));
        process.setHpmProcessId(rs.getString("HPM_PROCESS_ID"));
        process.setIdProcess(rs.getInt("ID_PROCESS"));
        process.setLastActionDate(rs.getDate("LAST_ACTION_DATE"));
        process.setLastActionPerformer(new User());
        process.setName(rs.getString("NAME"));
        process.setOwner(new User());
        process.getOwner().setIdUser(rs.getInt("ID_USER_OWNER"));
        process.setPrivate(rs.getBoolean("IS_PRIVATE"));
        process.setProcessType(new ProcessType());
        process.getProcessType().setIdProcessType(rs.getInt("ID_PROCESS_TYPE"));

        return process;
    }
}
