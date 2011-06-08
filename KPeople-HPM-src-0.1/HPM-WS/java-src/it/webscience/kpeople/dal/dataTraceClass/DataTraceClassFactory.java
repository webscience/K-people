package it.webscience.kpeople.dal.dataTraceClass;

import it.webscience.kpeople.be.DataTraceClass;
import it.webscience.kpeople.be.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * factory per le classi DataTraceClass.
 */
public class DataTraceClassFactory {

    /**
     * factory per l'oggetto DataTraceClass.
     * @param rs resultset
     * @param in dell'oggetto DataTraceClass
     * @throws SQLException label colonne non valido
     */
    public static void createDataTraceClass(
            final DataTraceClass in,
            final ResultSet rs)
            throws SQLException {

        in.setDeleted(rs.getBoolean("IS_DELETED"));
        in.setDeletedDate(rs.getDate("DELETED_DATE"));
        in.setFirstActionDate(rs.getDate("FIRST_ACTION_DATE"));
        in.setLastActionDate(rs.getDate("LAST_ACTION_DATE"));

        int deletedBy = rs.getInt("DELETED_BY");
        if (deletedBy > 0) {
            in.setDeletedBy(new User(deletedBy));
        }

        int firstActionPerformer = rs.getInt("FIRST_ACTION_PERFORMER");
        if (firstActionPerformer > 0) {
            in.setFirstActionPerformer(new User(firstActionPerformer));
        }

        int lastActionPerformer = rs.getInt("LAST_ACTION_PERFORMER");
        if (lastActionPerformer > 0) {
            in.setLastActionPerformer(new User(lastActionPerformer));
        }
    }
}
