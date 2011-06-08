package it.webscience.kpeople.dal.pattern;

import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternMetadata;
import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.dal.cross.AttachmentTypeDAO;
import it.webscience.kpeople.dal.cross.IAttachmentTypeDAO;
import it.webscience.kpeople.dal.dataTraceClass.DataTraceClassFactory;
import it.webscience.kpeople.dal.pattern.IPatternTypeDAO;
import it.webscience.kpeople.dal.pattern.PatternTypeDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe Pattern Factory.
 * @author gnoni
 * 
 */
public class PatternFactory {

    /**
     * factory per l'oggetto PatternType.
     * @param rs resultset
     * @return istanza dell'oggetto PatternType
     * @throws SQLException label colonne non valido
     */
    public static Pattern createPattern(final ResultSet rs) 
        throws SQLException {

        Pattern p = new Pattern();

        // Popolamento dei campi e gestione delle eccezioni
        p.setIdAttachment(rs.getInt("ID_ATTACHMENT"));

        IAttachmentTypeDAO atDao = new AttachmentTypeDAO();
        AttachmentType at = atDao
                .getAttachmentTypeByIdAttachmentType(
                        AttachmentType.ATTACHMENT_TYPE_PATTERN);
        p.setAttachmentType(at);

        p.setDescription(rs.getString("DESCRIPTION"));
        p.setHpmAttachmentId(rs.getString("HPM_ATTACHMENT_ID"));
        p.setName(rs.getString("NAME"));

        IPatternTypeDAO ptDAO = new PatternTypeDAO();
        PatternType pt = ptDAO.getPatternTypeById(rs.getInt("ID_PATTERN_TYPE"));
        p.setPatternType(pt);

        IPatternStateDAO psDAO = new PatternStateDAO();
        PatternState ps = psDAO.getPatternStateById(rs
                .getInt("ID_PATTERN_STATE"));
        p.setPatternState(ps);

        IPatternMetadataDAO pmDAO = new PatternMetadataDAO();
        List<PatternMetadata> pm = pmDAO.getPatternMetadatasByPatternId(
                rs.getInt("ID_ATTACHMENT"));
        p.setPatternMetadatas(pm);

        // Vengono settati all'esterno
        p.setPatternRequestor(null);
        p.setPatternProvider(null);
        p.setCcUsers(null);

        p.setStartDate(rs.getTimestamp("DATE_START"));
        p.setEndDate(rs.getTimestamp("DATE_END"));
        p.setDueDate(rs.getDate("DATE_DUE"));

        p.setActivitiProcessInstanceId(rs
                .getString("ACTIVITI_PROCESS_INSTANCE_ID"));
        p.setHpmPatternId(rs.getString("HPM_PATTERN_ID"));

        p.setWaitingActivity(rs.getBoolean("WAITING_ACTIVITY"));
        p.setWaitingActivityDate(rs.getTimestamp("WAITING_ACTIVITY_DATE"));

        DataTraceClassFactory.createDataTraceClass(p, rs);

        return p;
    }
}
