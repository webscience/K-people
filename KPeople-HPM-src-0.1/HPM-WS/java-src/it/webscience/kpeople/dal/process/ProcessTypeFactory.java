package it.webscience.kpeople.dal.process;

import it.webscience.kpeople.be.ProcessType;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * factory per le classi ProcessType.
 */
public class ProcessTypeFactory {

    /**
     * factory per l'oggetto ProcessType.
     * @param rs resultset
     * @return istanza dell'oggetto ProcessType
     * @throws SQLException label colonne non valido
     */
    public static ProcessType createProcessType(final ResultSet rs)
            throws SQLException {

        ProcessType pt = new ProcessType();

        pt.setIdProcessType(rs.getInt("ID_PROCESS_TYPE"));
        pt.setName(rs.getString("NAME"));
        pt.setProcessTypeCode(rs.getString("PROCESS_TYPE_CODE"));

        DataTraceClassFactory.createDataTraceClass(pt, rs);

        return pt;
    }
}
