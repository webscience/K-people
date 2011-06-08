package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.PatternMetadata;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.dal.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class PatternMetadataDAO implements IPatternMetadataDAO {

	/** logger. */
    private Logger logger;

    /** Costruttore. */
    public PatternMetadataDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }
    
	/**
	 * Ottiene la lista dei metadati associati ad un pattern.
	 * @param pPatternId identificativo del pattern di cui estrarre i metadati
	 * @return lista di PatternMetadata
	 * @throws SQLException eccezione
	 */
	@Override
	public final List<PatternMetadata> getPatternMetadatasByPatternId(
			final int pPatternId) throws SQLException {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
            logger.debug("getPatternMetadatasByPatternId: "
                    + pPatternId);
        }

		//Lettura parametri formali
		int idPattern = pPatternId;
		
        Connection conn = null;
        List<PatternMetadata> patternMetadatas = null;

        try {
            conn = Singleton.getInstance().getConnection();

            String query = null;
            StringBuffer sbQuery = new StringBuffer();
            
            //Esecuzione query
            sbQuery.append("SELECT * FROM pattern_metadata ");
            sbQuery.append(" WHERE ");
            sbQuery.append(" is_deleted = false ");
            sbQuery.append(" AND id_pattern = ? ");
            query = sbQuery.toString();
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idPattern);
            ResultSet rs = ps.executeQuery();

            patternMetadatas = new ArrayList<PatternMetadata>();
            while (rs.next()) {
            	PatternMetadata patternMetadata 
            		= this.getPatternMetadataById(
            				rs.getInt("ID_PATTERN_METADATA"));
            	patternMetadatas.add(patternMetadata);
            }
            rs.close();
            ps.close();
            

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        
        return patternMetadatas;
	}
	
	/**
	 * Estrae i dati della business entiti PatternMetadata dato un id.
	 * @param pIdPatternMetadata identificativo BE da estrarre.
	 * @return Info relative al PatternMetadata
	 * @throws SQLException eccezione
	 */
	@Override
	public final PatternMetadata getPatternMetadataById(
			final int pIdPatternMetadata)
		throws SQLException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("getPatternMetadataById: "
                    + pIdPatternMetadata);
        }

		//Lettura parametri formali
		int idPatternMetadata = pIdPatternMetadata;
		
        Connection conn = null;
        PatternMetadata patternMetadata = null;

        try {
            conn = Singleton.getInstance().getConnection();

            String query = null;
            StringBuffer sbQuery = new StringBuffer();
            
            //Esecuzione query
            sbQuery.append("SELECT * FROM pattern_metadata ");
            sbQuery.append(" WHERE ");
            sbQuery.append(" is_deleted = false ");
            sbQuery.append(" AND id_pattern_metadata = ? ");
            query = sbQuery.toString();
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idPatternMetadata);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	patternMetadata 
            		= PatternMetadataFactory.createPatternMetadata(rs);
            }
            rs.close();
            ps.close();
            

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        
        return patternMetadata;
	}

}
