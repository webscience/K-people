package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.cross.AttachmentTypeFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

/**
 *  .
 * @author gnoni
 *
 */
public class PatternTypeDAO implements IPatternTypeDAO{

	/** logger. */
    private Logger logger;

    /** Costruttore. */
    public PatternTypeDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }
    
    @Override
	public final PatternType getPatternTypeById(
            final int pPatternTypeId)
            throws SQLException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("getPatternTypeById: "
                    + pPatternTypeId);
        }

		//Lettura parametri formali
		int patternTypeId = pPatternTypeId;
		
        Connection conn = null;
        PatternType patternType = null;

        try {
            conn = Singleton.getInstance().getConnection();

            String query = null;
            StringBuffer sbQuery = new StringBuffer();
            
            //Esecuzione query
            sbQuery.append("SELECT * FROM pattern_type ");
            sbQuery.append(" WHERE is_active = true ");
            sbQuery.append(" AND is_deleted = false ");
            sbQuery.append(" AND id_pattern_type = ? ");
            query = sbQuery.toString();
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, patternTypeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	patternType = PatternTypeFactory.createPatternType(rs);
            }
            rs.close();
            ps.close();
            

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        
        return patternType;
    }
}
