package it.webscience.kpeople.dal.keyword;

import it.webscience.kpeople.be.Keyword;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * factory per le classi Keyword.
 * @author dellanna
 */
public class KeywordFactory {

    /**
     * factory per l'oggetto Keyword.
     * @param rs resultset
     * @return istanza dell'oggetto Keyword
     * @throws SQLException label colonne non valido
     */
    public static Keyword createKeyword(final ResultSet rs)
            throws SQLException {

        Keyword k = new Keyword();

        k.setIdKeyword(rs.getInt("ID_KEYWORD"));
        k.setKeyword(rs.getString("KEYWORD"));
        k.setDescription(rs.getString("DESCRIPTION"));
        k.setHpmKeywordId(rs.getString("HPM_KEYWORD_ID"));

        DataTraceClassFactory.createDataTraceClass(k, rs);

        return k;
    }






}
