package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * factory per le classi User.
 */
public class UserFactory {

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
        user.setHpmUserId(rs.getString("HPM_USER_ID"));
        user.setFirstName(rs.getString("FIRST_NAME"));
        user.setLastName(rs.getString("LAST_NAME"));
        user.setScreenName(rs.getString("SCREEN_NAME"));

        DataTraceClassFactory.createDataTraceClass(user, rs);

        return user;
    }
}
