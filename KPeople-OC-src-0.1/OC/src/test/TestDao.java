package test;

import it.webscience.kpeople.be.AttachmentType;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.HpmDao;

import java.util.Date;

/**
 * classe di test del dao hpm.
 * @author dellanna
 *
 */
public class TestDao {
    /**
     * @param args parametri di ingresso.
     * @throws Exception ex
     */
    public static void main(final String[] args) throws Exception {
        HpmDao dao = new HpmDao();

        User u = new User();
        u.setIdUser(1);
        
      
            AttachmentType at = new AttachmentType(1);
            at.setName("type xx");
            at.setDeleted(false);
            at.setFirstActionDate(new Date());
            at.setLastActionDate(new Date());
            at.setFirstActionPerformer(u);
            at.setLastActionPerformer(u);
            
//            dao.saveAttachmentType(at);
 
    }
}
