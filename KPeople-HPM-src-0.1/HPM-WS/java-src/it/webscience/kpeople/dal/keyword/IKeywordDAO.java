package it.webscience.kpeople.dal.keyword;

import it.webscience.kpeople.be.Keyword;
import it.webscience.kpeople.be.ObjectKeyword;
import it.webscience.kpeople.be.User;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Interfaccia per la classe DAO relativa alle keywords.
 */
public interface IKeywordDAO {

    /**
     * ritorna l'oggetto Keyword associato alla chiave desiderata.
     * @param k chiave
     * @throws SQLException eccezioni db
     * @return oggetto Keyword
     */
    Keyword getKeyword(
            final String k)
            throws SQLException;

    /**
     * scrive una riga nella tabellla object_keyword.
     * @param ok oggetto da salvare
     * @param user first action performer
     * @param now first action date
     * @param conn connection per la gestione della transazione
     * @throws SQLException if a database access error occurs
     */
    void saveObjectKeyword(
            final ObjectKeyword ok,
            final User user,
            final java.util.Date now,
            final Connection conn) throws SQLException;
}
