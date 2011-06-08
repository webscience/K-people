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

public class GetActivityByIDTest {

    @Test
    public void getActivityById() throws Exception {

        UserService userService = new UserService();
       // User user =
         //       userService
           //             .getUserByHpmUserId("filieri@kpeople.webscience.it");

        User user = new User();
        user.setHpmUserId("filieri@kpeople.webscience.it");
        String activityId = "515";
        
        ActivityService actservice = new ActivityService();

        Activity activity = actservice.getActivityByHpmId(activityId, user);

        Assert.assertEquals(activity.getTitle(), "");
    }

    

}
