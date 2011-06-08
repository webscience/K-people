package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.be.CommunicationEvent;
import it.webscience.kpeople.dal.exception.KPeopleActivitiException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Factory per la generazione degli oggetti di tipo communication.
 */
public class CommunicationFactory {

    /**
     * Factory per la creazione di un oggetto di tipo CommunicationEvent.
     * @param rs result set
     * @return oggetto CommunicationEvent
     * @throws SQLException eccezione durante l'estrazione del dato
     */
    public static CommunicationEvent createCommunicationEvent(
            final ResultSet rs) throws KPeopleActivitiException {

        CommunicationEvent retVal = null;

        try {
            CommunicationEventDAO commEventDAO = new CommunicationEventDAO();

            retVal = new CommunicationEvent();
            retVal.setIdAttachment(rs.getInt("ID_ATTACHMENT"));
            retVal.setDescription(rs.getString("DESCRIPTION"));
            retVal.setHpmAttachmentId(rs.getString("HPM_ATTACHMENT_ID"));
            retVal.setUserFrom(
                    commEventDAO.getUserFrom(rs.getInt("ID_ATTACHMENT")));
            retVal.setToUser(
                    commEventDAO.getUsersTo(rs.getInt("ID_ATTACHMENT")));
            retVal.setCcUser(
                    commEventDAO.getCcUser(rs.getInt("ID_ATTACHMENT")));
            retVal.setCcnUser(
                    commEventDAO.getCcUser(rs.getInt("ID_ATTACHMENT")));
            retVal.setBody(rs.getString("EMAIL_BODY"));
            retVal.setObject(rs.getString("EMAIL_OBJECT"));

        } catch (Exception e) {
           throw new KPeopleActivitiException(e.getMessage());
        }

        return retVal;
    }
}
