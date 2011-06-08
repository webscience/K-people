package it.webscience.kpeople.dal.cross;

import java.sql.ResultSet;
import java.sql.SQLException;

import it.webscience.kpeople.be.AttachmentType;

public class AttachmentTypeFactory {
    
    public static AttachmentType createAttachmentType(
            final ResultSet rs) throws SQLException{
        
        AttachmentType attachType = new AttachmentType();
        attachType.setIdAttachmentType(rs.getInt("ID_ATTACHMENT_TYPE"));
        attachType.setName(rs.getString("NAME"));
        
        return attachType;
    }

}
