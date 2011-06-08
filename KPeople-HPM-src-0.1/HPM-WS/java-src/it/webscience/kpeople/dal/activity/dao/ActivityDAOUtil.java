package it.webscience.kpeople.dal.activity.dao;

import it.webscience.kpeople.be.ActivityType;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.activity.ActivityTypeFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Classe di utilità sul DB per la BE Activity.
 * @author gnoni
 *
 */
public class ActivityDAOUtil {

	/** logger. */
    private Logger logger;

    /** Costruttore. */
    public ActivityDAOUtil() {
        logger = Logger.getLogger(this.getClass().getName());
    }
    
    /**
     * 
     * @param pPatternRoleId
     * @param pPatternIdStr
     * @return
     * @throws SQLException
     */
	public List<String> getHpmUsersIdByPatternRoleIdAndPatternIdList(
			int pPatternRoleId, String pPatternIdStr) 
		throws SQLException {
		
		//Lettura parametri formali
		int patternRoleId = pPatternRoleId;
		String patternIdStr = pPatternIdStr;
		
		StringBuffer sb = new StringBuffer();
		String query = "";
		sb.append(" SELECT DISTINCT hpm_user_id ");
		sb.append(" FROM ");
		sb.append(" user_patternrole_pattern upr, user u  ");
		sb.append(" WHERE ");
		sb.append(" u.id_user=upr.id_user ");
		sb.append(" AND upr.is_deleted=false ");
		sb.append(" AND u.is_deleted=false ");
		sb.append(" AND id_pattern IN (" + patternIdStr + ") ");
		sb.append(" AND id_pattern_role = ? ");
		query = sb.toString();
		
		Connection conn = null;
		List<String> hpmUserIdProviders = null;
		try {
		    conn = Singleton.getInstance().getConnection();
		    PreparedStatement ps = conn.prepareStatement(query);
		    ps.setInt(1, patternRoleId);
		    ResultSet rs = ps.executeQuery();
		
		    hpmUserIdProviders = new ArrayList<String>();
		    while (rs.next()) {
			String a = rs.getString("hpm_user_id");
			hpmUserIdProviders.add(a);
		    }
		
		    rs.close();
		    ps.close();
		 
		} catch (SQLException e) {
	            throw new SQLException(e.getMessage());
	        } finally {
	            conn.close();
	        }
		
		return hpmUserIdProviders;	
	}
	
	/**
	 * 
	 * @param pIdUserRequestor
	 * @return
	 * @throws SQLException
	 */
	public List<String> getHpmProviderIdByRequestorId(int pIdUserRequestor) 
		throws SQLException {
		
		//Lettura parametri formali
		int idUserRequestor = pIdUserRequestor;
		
		
		StringBuffer sb = new StringBuffer();
		String query = "";
		
		sb.append(" SELECT ");
		sb.append(" distinct hpm_user_id ");
		sb.append(" FROM ");
		sb.append(" user_patternrole_pattern upr, user u ");
		sb.append(" WHERE ");
		sb.append(" u.id_user=upr.id_user ");
		sb.append(" AND id_pattern_role = 2 and id_pattern IN ");
		sb.append(" ( SELECT ");
		sb.append(" id_pattern ");
		sb.append(" FROM  ");
		sb.append(" user_patternrole_pattern   ");
		sb.append(" WHERE id_pattern_role ");
		sb.append(" AND id_user = ? )");
		query = sb.toString();
		Connection conn = null;
		List<String> hpmUserIdProviders = new ArrayList<String>();
		
		try {
		    conn = Singleton.getInstance().getConnection();
		    PreparedStatement ps = conn.prepareStatement(query);
		    ps.setInt(1, idUserRequestor);
		    ResultSet rs = ps.executeQuery();
		
    		    hpmUserIdProviders = new ArrayList<String>();
    		    while (rs.next()) {
    			String a = rs.getString("hpm_user_id");
    			hpmUserIdProviders.add(a);
    		    }
    		rs.close();
    		    ps.close();
    		    
    		    
		} catch (SQLException e) {
	            throw new SQLException(e.getMessage());
	        } finally {
	            conn.close();
	        }
		
		return hpmUserIdProviders;
	}
}
