package it.webscience.kpeople.dal.activity.dao;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.ActivityMetadata;
import it.webscience.kpeople.be.ActivityType;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternMetadata;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.client.activiti.ActivitiClient;
import it.webscience.kpeople.client.activiti.IActivitiClient;
import it.webscience.kpeople.client.activiti.IActivitiConstants;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiGetTaskException;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiListTaskException;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiPerformTaskOperationException;
import it.webscience.kpeople.client.activiti.object.ActivitiTask;
import it.webscience.kpeople.client.activiti.object.ActivitiTaskPerformResponse;
import it.webscience.kpeople.client.activiti.object.ActivitiUserTaskList;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.pattern.PatternDAO;
import it.webscience.kpeople.dal.pattern.dao.PatternDAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

public class ActivitiUtil {

    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public ActivitiUtil() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * @param pProcess
     * @param pPattern
     * @param pUser
     * @return
     * @throws KPeopleActivitiListTaskException
     */
    public final List<Activity> syncToDoActivities(final Process pProcess,
            final Pattern pPattern, final User pUser)
            throws KPeopleActivitiListTaskException {

        // Lettura parametri formali
        Process process = pProcess;
        Pattern pattern = pPattern;
        User user = pUser;

        String activitiUser = user.getHpmUserId();
        String httpUser = user.getHpmUserId();
        // String httpPass = user.getHpmUserId();

        IActivitiClient client = new ActivitiClient(httpUser);

        ActivitiUserTaskList activityList =
                client.listTasksAssigneeObj(activitiUser);

        List<Activity> activities = new ArrayList<Activity>();
        List<ActivitiTask> activitiesTask = activityList.getData();
        for (int i = 0; i < activitiesTask.size(); i++) {
            ActivitiTask actTask = activitiesTask.get(i);

            Pattern currentPattern = null;
            PatternDAOUtil pattDaoUtil = new PatternDAOUtil();

            try {
                currentPattern =
                        pattDaoUtil
                                .getPatternFromActivitiProcessInstanceId(actTask
                                        .getProcessInstanceId());
            } catch (SQLException e) {
                logger.warn("Impossibile trovare un pattern per il task "
                        + actTask.getId());

            }

            if (currentPattern != null) {

                Activity activityToAdd =
                        createActivity(currentPattern, user, actTask);

                if (activityToAdd != null) {
                    activities.add(activityToAdd);
                }
            }
        }

        return activities;
    }

    public final Activity getActivityFromActiviti(final String hpmActivityId,
            final User user) {

        ActivitiClient client = new ActivitiClient(user.getHpmUserId());
        ActivitiTask actTask = null;
        Activity activity = null;
        try {
            actTask = client.getTaskObj(hpmActivityId);
        } catch (KPeopleActivitiGetTaskException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Pattern currentPattern = null;
        PatternDAOUtil pattDaoUtil = new PatternDAOUtil();

        try {
            currentPattern =
                    pattDaoUtil.getPatternFromActivitiProcessInstanceId(actTask
                            .getProcessInstanceId());
        } catch (SQLException e) {
            logger.warn("Impossibile trovare un pattern per il task "
                    + actTask.getId());

        }

        if (currentPattern != null) {

            activity =
                    createActivity(currentPattern, user, actTask);

            
        }

        return activity;
    }

    /**
     * @param pPattern
     * @param pUser
     * @param pActivitiTask
     * @return
     * @throws SQLException
     */
    private final Activity createActivity(Pattern pPattern, User pUser,
            ActivitiTask pActivitiTask) {

        // Lettura parametri formali
        Pattern pattern = pPattern;
        User user = pUser;
        ActivitiTask activitiTask = pActivitiTask;

        Activity activityToAdd = new Activity();
        activityToAdd.setIdPattern(pattern.getIdAttachment());
        activityToAdd.setTitle(pattern.getName());
        activityToAdd.setDescription("");
        activityToAdd.setActivityOwner(user);
        activityToAdd.setActivityRequestor(pattern.getPatternRequestor());

        activityToAdd.setCreateDate(new Date());
        // TODO Ha senso la data di scadenza per una attività?
        // Se si come la gestiamo nel workflow engine
        activityToAdd.setDueDate(null);
        activityToAdd.setClosed(false);
        // TODO Ha senso usarlo? non è sufficiente la variabile booleana
        // TODO scrivere classe ActivityStateDAO e ActivityStateDAoUtil

        activityToAdd.setActivityState(null);
        activityToAdd.setActivitiProcessTaskId(activitiTask.getId());

        String hpmPatternId = pattern.getHpmPatternId();
        String taskId = activitiTask.getId();

        activityToAdd.setActivityMetadata(null);
        ActivityTypeDAOUtil actTypeDaoUtil = new ActivityTypeDAOUtil();
        try {
            ActivityType activityType =
                    actTypeDaoUtil.getActivityTypeByRelatedForm(activitiTask
                            .getFormResourceKey());
            activityToAdd.setActivityType(activityType);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("Impossibile associare il task "
                    + activitiTask.getId());
            return null;
        }

        activityToAdd.setPattern(pattern);

        String hpmActivityId =
                Activity.generateHpmActivityId(hpmPatternId, taskId);
        activityToAdd.setHpmActivityId(hpmActivityId);

        return activityToAdd;
    }

    /**
     * @param pPattern
     * @param pUser
     * @return
     * @throws KPeopleActivitiListTaskException
     */
    public final List<Activity> syncToDoActivities(final Pattern pPattern,
            final User pUser) throws KPeopleActivitiListTaskException {
        return syncToDoActivities(null, pPattern, pUser);
    }

    /**
     * @param pUser
     * @return
     * @throws KPeopleActivitiListTaskException
     */
    public final List<Activity> syncToDoActivities(final User pUser)
            throws KPeopleActivitiListTaskException {
        return syncToDoActivities(null, null, pUser);
    }

    /**
     * @param pProcess
     * @param pPattern
     * @param pUser
     * @return
     * @throws SQLException
     */

    public final List<Activity> syncPendingActivities(final Process pProcess,
            final Pattern pPattern, final User pUser)
            throws KPeopleActivitiListTaskException {
        return null;
    }

    /**
     * @param pPattern
     * @param pUser
     * @return
     * @throws SQLException
     */
    public final List<Activity> syncPendingActivities(final Pattern pPattern,
            final User pUser) throws KPeopleActivitiListTaskException {

        return syncPendingActivities(null, pPattern, pUser);
    }

    /**
     * @param pUser
     * @return
     * @throws SQLException
     */
    public final List<Activity> syncPendingActivities(final User pUser)
            throws KPeopleActivitiListTaskException {

        return syncPendingActivities(null, null, pUser);
    }

    public final Activity
            executeTask(final Activity pActivity, final User pUser)
                    throws KPeopleActivitiPerformTaskOperationException {

        if (logger.isDebugEnabled()) {
            logger.debug("executeTask: " + pActivity);
        }

        // Lettura parametri formali
        Activity activity = pActivity;
        User user = pUser;

        // Generazione HASHMAP per l'invocazione ad Activiti
        JSONObject jSonObjectInput = new JSONObject();

        // Prende le info custom x il pattern
        List<ActivityMetadata> metadatas = activity.getActivityMetadata();
        for (int i = 0; i < metadatas.size(); i++) {
            ActivityMetadata metadata = metadatas.get(i);
            if (metadata.isActivitiProcessMetadata()) {
                jSonObjectInput.put(metadata.getKeyname(), metadata.getValue());
            }
        }

        // Generazione stringa json da passare
        String jSonParams = jSonObjectInput.toString();

        // Chiamata verso il motore di workflow
        IActivitiClient activitiClient = new ActivitiClient(
        // pattern.getPatternProvider().getHpmUserId()
                user.getHpmUserId());

        ActivitiTaskPerformResponse activitiResponse =
                activitiClient
                        .performTaskOperationObj(
                                activity.getActivitiProcessTaskId(),
                                jSonParams,
                                IActivitiConstants.ACTIVITI_SERVICE_TASK_OPERATION_COMPLETE);

        activity.setClosed(activitiResponse.isSuccess());

        return activity;
    }
}
