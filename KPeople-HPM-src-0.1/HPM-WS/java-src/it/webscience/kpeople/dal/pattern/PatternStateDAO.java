package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.dal.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class PatternStateDAO implements IPatternStateDAO{

	/** logger. */
    private Logger logger;

    /** Costruttore. */
    public PatternStateDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }

	@Override
	public final PatternState getPatternStateById(final int pPatternStateId)
			throws SQLException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("getPatternStateById: "
                    + pPatternStateId);
        }

		//Lettura parametri formali
		int patternStateId = pPatternStateId;
		
        Connection conn = null;
        PatternState patternState = null;

        try {
            conn = Singleton.getInstance().getConnection();

            String query = null;
            StringBuffer sbQuery = new StringBuffer();
            
            //Esecuzione query
            sbQuery.append("SELECT * FROM pattern_state ");
            sbQuery.append(" WHERE ");
            sbQuery.append(" is_deleted = false ");
            sbQuery.append(" AND id_pattern_state = ? ");
            query = sbQuery.toString();
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, patternStateId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	patternState = PatternStateFactory.createPatternState(rs);
            }
            rs.close();
            ps.close();
            

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        
        return patternState;
		
	}
    
    
}
