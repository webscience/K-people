package it.webscience.kpeople.dal.keyword;

import it.webscience.kpeople.be.ObjectType;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * factory per le classi ObjectType.
 */
public class ObjectTypeFactory {

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

        DataTraceClassFactory.createDataTraceClass(ot, rs);

        return ot;
    }
}
