package it.webscience.kpeople.dal.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.webscience.kpeople.be.ProcessCompoundMetadata;
import it.webscience.kpeople.be.ProcessMetadata;
import it.webscience.kpeople.be.ProcessMetadataSet;
import it.webscience.kpeople.be.ProcessValue;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * @author depascalis
 * Classe per il ProcessMetadata.
 */
public class ProcessMetadataDAO {

    /**
     * Logger.
     */
    private Logger logger;

    /**
     * Costruttore della classe.
     */
    public ProcessMetadataDAO() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * @param user utente che effettua la richiesta
     * @return l'intero set dei metadati associati al processo.
     * @throws KPeopleDAOException eccezione durante l'elaborazione.
     */
    public final ProcessMetadataSet getMetadataProcess(final User user)
            throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("start getMetadataProcess");
        }
        ProcessMetadataSet processMetadataSet = new ProcessMetadataSet();
        ProcessCompoundMetadata processCompoundMetadata =
            new ProcessCompoundMetadata();
        processCompoundMetadata.setKey("Processi Associabili");
        processCompoundMetadata
                .setDescription("Processi con cui associare gli eventi");
        ProcessMetadata processMetadata = new ProcessMetadata();
        ProcessValue processValue = new ProcessValue();
        
        Connection con = null;
        try {
            con = Singleton.getInstance().getConnection();
            String query = "SELECT k.KEYWORD, k.DESCRIPTION, "
                    + "obk.VALUE, obk.DESCRIPTION AS DESCR, p.ID_PROCESS_STATE AS STATE "
                    + "FROM keyword k inner join object_keyword obk on k.ID_KEYWORD = obk.ID_KEYWORD "
                    + "inner join object_type obt on obk.ID_OBJECT_TYPE = obt.ID_OBJECT_TYPE "
                    + "inner join process p on obk.ID_OBJECT = p.ID_PROCESS "
                    + "AND obt.NAME ='process' "
                    + "ORDER BY(k.KEYWORD)";

            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.first();
            processMetadata.setKey(rs.getString("KEYWORD"));
            processMetadata.setDescription(rs.getString("DESCRIPTION"));
            processValue.setValue(rs.getString("VALUE"));
            processValue.setDescription(rs.getString("DESCR"));
            processValue.setZidState(rs.getInt("STATE"));
            processMetadata.valueList.add(processValue);
            while (rs.next()) {
                if (processMetadata.getKey().equalsIgnoreCase(rs.getString("KEYWORD"))) {
                    processValue = new ProcessValue();
                    processValue.setValue(rs.getString("VALUE"));
                    processValue.setDescription(rs.getString("DESCR"));
                    processValue.setZidState(rs.getInt("STATE"));
                    processMetadata.valueList.add(processValue);
                } else {
                    processCompoundMetadata.metadataList.add(processMetadata);
                    processMetadata = new ProcessMetadata();
                    processValue = new ProcessValue();
                    processMetadata.setKey(rs.getString("KEYWORD"));
                    processMetadata.setDescription(rs.getString("DESCRIPTION"));
                    processValue.setValue(rs.getString("VALUE"));
                    processValue.setDescription(rs.getString("DESCR"));
                    processValue.setZidState(rs.getInt("STATE"));
                    processMetadata.valueList.add(processValue);
                }
            }
            processCompoundMetadata.metadataList.add(processMetadata);
            processMetadataSet.cmpMetadataList.add(processCompoundMetadata);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());

        } finally {
            con.close();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("end getMetadataProcess");
        }
        return processMetadataSet;
    }


}
