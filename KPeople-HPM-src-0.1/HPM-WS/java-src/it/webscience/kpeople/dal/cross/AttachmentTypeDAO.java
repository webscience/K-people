package it.webscience.kpeople.dal.cross;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.dal.Singleton;

import org.apache.log4j.Logger;

/**
 * @author depascalis
 * DAO per l'accesso al DB alle classi AttachmentType.
 */
public class AttachmentTypeDAO implements IAttachmentTypeDAO {

    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public AttachmentTypeDAO() {
        super();
        logger = Logger.getLogger(this.getClass().getName());
    }

    public final AttachmentType getAttachmentTypeByIdAttachmentType(
            final int idAttachmentType) throws SQLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getAttachmentTypeByIdAttachmentType: "
                    + idAttachmentType);
        }

        Connection conn = null;
        AttachmentType attachType = null;

        try {
            conn = Singleton.getInstance().getConnection();

            /*
            String queryAttachType = "SELECT * FROM attachment, attachment_type "
                + "WHERE (attachment.ID_ATTACHMENT_TYPE = attachment_type.ID_ATTACHMENT_TYPE) AND "
                + "(attachment.ID_ATTACHMENT_TYPE = ?)";
			*/
            String queryAttachType = "SELECT * FROM attachment_type "
                + "WHERE ID_ATTACHMENT_TYPE = ?";
            
            PreparedStatement ps = conn.prepareStatement(queryAttachType);
            ps.setInt(1, idAttachmentType);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                attachType = AttachmentTypeFactory.createAttachmentType(rs);
            }
            rs.close();
            ps.close();
            

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            conn.close();
        }
        return attachType;
    }
}
