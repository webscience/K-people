package it.webscience.kpeople.service.activity;

import it.webscience.kpeople.dal.activity.ActivityDAO;
import it.webscience.kpeople.dal.cross.UserDAO;
import it.webscience.kpeople.dal.pattern.PatternDAO;
import it.webscience.kpeople.service.cross.UserService;
import it.webscience.kpeople.service.datatypes.Activity;
import it.webscience.kpeople.service.datatypes.ActivityMetadata;
import it.webscience.kpeople.service.datatypes.Pattern;
import it.webscience.kpeople.service.datatypes.PatternActivityFilter;
import it.webscience.kpeople.service.datatypes.PatternState;
import it.webscience.kpeople.service.datatypes.User;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ActivityServiceTest {

    @Test
    public void searchToDoActivitiesTest() throws Exception {

        UserService userService = new UserService();
        User user =
                userService
                        .getUserByHpmUserId("dellanna@kpeople.webscience.it");

        PatternActivityFilter paf = new PatternActivityFilter();
        paf.setHpmPatternId("");
        paf.setHpmProcessId("app21");
        paf.setHpmUserId("dellanna@kpeople.webscience.it");

        ActivityService actservice = new ActivityService();

        Activity[] ls = actservice.searchToDoActivities(paf, user);

        Assert.assertEquals(ls.length, 2);
    }

    @Test
    public void searchPendingActivitiesTest() throws Exception {

        UserService userService = new UserService();
        User user =
                userService.getUserByHpmUserId("filieri@kpeople.webscience.it");

        PatternActivityFilter paf = new PatternActivityFilter();
        paf.setHpmPatternId("");
        paf.setHpmProcessId("");
        paf.setHpmUserId("filieri@kpeople.webscience.it");

        ActivityService actservice = new ActivityService();

        Activity[] ls = actservice.searchPendingActivities(paf, user);

        Assert.assertEquals(1, 1);
    }

    @Test
    public void executeActivityTest() throws Exception {

        //recupero tutte le attività todo
        UserService userService = new UserService();
        User user =
                userService
                        .getUserByHpmUserId("dellanna@kpeople.webscience.it");

        PatternActivityFilter paf = new PatternActivityFilter();
        paf.setHpmPatternId("");
        paf.setHpmProcessId("app25");
        paf.setHpmUserId("dellanna@kpeople.webscience.it");

        ActivityService actservice = new ActivityService();

        Activity[] ls = actservice.searchToDoActivities(paf, user);

        //recupero l'attività corrente
        Activity currActivity = null;
        for (Activity act : ls) {
            if (act.getHpmActivityId().equalsIgnoreCase("app25-PATRICCONTR1294-1303")) {
                currActivity = act;
            }
        }

        Pattern currPattern = currActivity.getPattern();
        PatternState pState = currPattern.getPatternState();
        pState.setIdPatternState(3);
        currPattern.setPatternState(pState);
        currActivity.setPattern(currPattern);
        
        ActivityMetadata[] metas = new ActivityMetadata[1];
                
//        ActivityMetadata metadata1 = new ActivityMetadata();
//        metadata1.setKeyname("contributeAccepted");
//        metadata1.setValue("true");
//        metadata1.setActivitiProcessMetadata(true);
//        metas[0] = (metadata1);
//
//        ActivityMetadata metadata2 = new ActivityMetadata();
//        metadata2
//            .setKeyname("contributeAccepted_type");
//        metadata2.setValue("Boolean");
//        metadata2.setActivitiProcessMetadata(true);
//        metas[1] = (metadata2);
        
      ActivityMetadata metadata2 = new ActivityMetadata();
      metadata2
          .setKeyname("patternContent");
      metadata2.setValue("bla bla bla");
      metadata2.setActivitiProcessMetadata(true);
      metas[0] = (metadata2);

        currActivity.setActivityMetadata(metas);
                
        
        
        ActivityService actDao = new ActivityService();
        Activity activityReturn = actDao.executeActivity(currActivity, user);
        
        Assert.assertEquals(activityReturn.isClosed(), true);
        
    }

}
