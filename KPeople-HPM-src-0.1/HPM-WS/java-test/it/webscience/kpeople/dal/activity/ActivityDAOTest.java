package it.webscience.kpeople.dal.activity;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.ActivityMetadata;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternActivityFilter;
import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.pattern.PatternDAO;
import it.webscience.kpeople.dal.pattern.PatternStateDAO;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @author gnoni
 * 
 */
public class ActivityDAOTest {

    /**
     * 
     * @throws Exception
     */
    @Test
    public void searchToDoActivitiesTest() throws Exception {
        String providerHpmId = "filieri@kpeople.webscience.it";
        String hpmPatternId = "";
        String hpmProcessId = "";
        int expectedToDoActivities = 1;

        PatternActivityFilter paf = new PatternActivityFilter();
        paf.setHpmPatternId(hpmPatternId);
        paf.setHpmProcessId(hpmProcessId);
        paf.setHpmUserId(providerHpmId);

        UserDAO usDao = new UserDAO();
        User user = usDao.getUserByHpmUserId(providerHpmId);

        ActivityDAO acDao = new ActivityDAO();
        List<Activity> ls = acDao.searchToDoActivities(paf, user);

        for (int i = 0; i < ls.size(); i++) {
            System.out.println("attività :"
                    + ls.get(i).getActivitiProcessTaskId() + " - "
                    + ls.get(i).getPattern().getIdAttachment() + " - "
                    + ls.get(i).getPattern().getHpmPatternId() + " - "
                    + ls.get(i).getActivityType().getName() + " - "
                    + ls.get(i).getActivityType().getDescription());
        }

        Assert.assertEquals(ls.size(), expectedToDoActivities);
    }

    /**
     * 
     * @throws Exception
     */

    @Test
    public void searchPendingActivitiesTest() throws Exception {

        String requestorHpmId = "filieri@kpeople.webscience.it";
        String hpmPatternId = "";
        String hpmProcessIs = "";
        int expectedPendingActivities = 2;

        PatternActivityFilter paf = new PatternActivityFilter();
        paf.setHpmPatternId(hpmPatternId);
        paf.setHpmProcessId(hpmProcessIs);
        paf.setHpmUserId(requestorHpmId);

        UserDAO usDao = new UserDAO();
        User user = usDao.getUserByHpmUserId(requestorHpmId);

        ActivityDAO acDao = new ActivityDAO();
        List<Activity> ls = acDao.searchPendingActivities(paf, user);
        System.out.println("Attività in attesa dello user: "
                + paf.getHpmUserId());

        for (int i = 0; i < ls.size(); i++) {
            System.out.println("attività :"
                    + ls.get(i).getActivitiProcessTaskId() + " - "
                    + ls.get(i).getPattern().getIdAttachment() + " - "
                    + ls.get(i).getPattern().getHpmPatternId() + " - "
                    + ls.get(i).getActivityType().getName() + " - "
                    + ls.get(i).getActivityType().getDescription());
        }

        Assert.assertEquals(ls.size(), expectedPendingActivities);
    }

    
    @Test
    public void executeActivityTest() throws Exception {

        String hpmUserId = "dellanna@kpeople.webscience.it";
        String pActivitiProcessTaskId = "580";

        UserDAO userDao = new UserDAO();
        User user = userDao.getUserByHpmUserId(hpmUserId);

        String hpmPatternId = "";
        Activity activity = new Activity();
        activity.setActivitiProcessTaskId(pActivitiProcessTaskId);

        PatternDAO patDao = new PatternDAO();
        Pattern pattern = patDao.getPatternByHpmPatternId(hpmPatternId);
        PatternStateDAO pstDao = new PatternStateDAO();
        PatternState patternState = pstDao.getPatternStateById(3);

        List<ActivityMetadata> metas = new ArrayList<ActivityMetadata>();

        ActivityMetadata mt = new ActivityMetadata();
        mt.setActivitiProcessMetadata(true);
        mt.setKeyname("patternContent");
        mt.setValue("Content1");
        metas.add(mt);
        activity.setActivityMetadata(metas);

        // Da usare per la risposta
        // /patternState = new PatternState
        // /patternState = patDao.getPatternStateByPatternStateId(pPatternState,
        // pLoggedUser);

        /*
         * 
         * metas.add(new ActivityMetadata( "patternContent",
         * "Il mio contributo per il task " + pActivitiProcessTaskId, true) );
         */
        // Da usare per l'approvazione
        /*
         * metas.add(new ActivityMetadata( "contributeApproved", "true", true)
         * ); metas.add(new ActivityMetadata( "contributeApproved_type",
         * "Boolean", true) ); metas.add(new ActivityMetadata(
         * "contributeMotivation", "La mia moticazione per " +
         * pActivitiProcessTaskId, true) );
         */
        // Da usare per la chiusura

        /*
         * mt = new ActivityMetadata(); mt.setActivitiProcessMetadata(true);
         * mt.setKeyname("patternTitle"); mt.setValue("Test1"); metas.add(mt);
         */
        activity.setActivityMetadata(metas);

        ActivityDAO actDao = new ActivityDAO();
        activity = actDao.executeActivity(activity, user);

        Assert.assertEquals(activity.isClosed(), true);

        // Un test ulteriore potrebbe essere quello di verificare se tra
        // i TODO di questo utente è ancora presente questa attività
    }

    /**
     * DEPRECATA : scritta oer testare un metodo private
     * calculateUserToCheckOnActiviti
     * 
     * @throws Exception
     * @Test public void calculateUserToCheckOnActivitiTest() throws Exception {
     * 
     *       String requestorHpmId = "filieri@kpeople.webscience.it"; String
     *       processHpmId = "app5"; int expectedUsersCount = 1;
     * 
     *       UserDAO usDao = new UserDAO(); User user =
     *       usDao.getUserByHpmUserId(requestorHpmId);
     * 
     *       ProcessDAO pDao = new ProcessDAO(); Process process =
     *       pDao.getProcessByHpmId(processHpmId);
     * 
     *       ActivityDAO acDao = new ActivityDAO(); List<String> ls =
     *       acDao.calculateUserToCheckOnActiviti(process, null, user);
     * 
     *       System.out.println("Attività da fare dello user: ");
     * 
     *       for (int i = 0; i < ls.size(); i++) {
     *       System.out.println(ls.get(i)); }
     * 
     *       Assert.assertEquals(ls.size(), expectedUsersCount); }
     */
}
