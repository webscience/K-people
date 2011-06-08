package it.webscience.kpeople.service.activity;

import it.webscience.kpeople.bll.IActivityManager;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.ActivityManager;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiPerformTaskOperationException;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.service.datatypes.Activity;
import it.webscience.kpeople.service.datatypes.PatternActivityFilter;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.datatypes.converter.ActivityConverter;
import it.webscience.kpeople.service.datatypes.converter.PatternActivityFilterConverter;
import it.webscience.kpeople.service.datatypes.converter.UserConverter;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author filieri Classe per la gestione delle attività.
 */
public class ActivityService {

    /** logger. */
    private Logger logger;

    /**
     * istanza della classe singleton per la lettura delle proprietà di
     * configurazione.
     */
    private Singleton singleton;

    /**
     * Costruttore di default.
     */
    public ActivityService() {
        logger = Logger.getLogger(this.getClass().getName());

        singleton = Singleton.getInstance();
    }

    /**
     * @param pFilter
     *            filtro per la ricerca delle attività.
     * @param pLoggedUser
     *            user che esegue la chiamata al servizio.
     * @return un array contenente le attività in stato toDo.
     * @throws KPeopleServiceException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    public final Activity[] searchToDoActivities(
            final PatternActivityFilter pFilter, final User pLoggedUser)
            throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("searchToDoActivities start : " + pFilter);
        }

        IActivityManager mng = new ActivityManager();

        Activity[] activitiesDT = null;
        try {
            it.webscience.kpeople.be.PatternActivityFilter filterBE =
                    PatternActivityFilterConverter.toBE(pFilter);

            it.webscience.kpeople.be.User loggedUserBE =
                    UserConverter.toBE(pLoggedUser);

            List<it.webscience.kpeople.be.Activity> activitiesBE =
                    mng.searchToDoActivities(filterBE, loggedUserBE);

            activitiesDT = ActivityConverter.toService(activitiesBE);
        } catch (KPeopleBLLException e) {
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("searchToDoActivities end");
        }

        return activitiesDT;
    }

    /**
     * @param pFilter
     *            filtro per la ricerca delle attività.
     * @param pLoggedUser
     *            user che esegue la chiamata al servizio.
     * @return un array contenente le attività in stato pending.
     * @throws KPeopleServiceException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    public final Activity[] searchPendingActivities(
            final PatternActivityFilter pFilter, final User pLoggedUser)
            throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("searchPendingActivities start : " + pFilter);
        }

        IActivityManager mng = new ActivityManager();

        Activity[] activitiesDT = null;
        try {
            it.webscience.kpeople.be.PatternActivityFilter filterBE =
                    PatternActivityFilterConverter.toBE(pFilter);

            it.webscience.kpeople.be.User loggedUserBE =
                    UserConverter.toBE(pLoggedUser);

            List<it.webscience.kpeople.be.Activity> activitiesBE =
                    mng.searchPendingActivities(filterBE, loggedUserBE);

            activitiesDT = ActivityConverter.toService(activitiesBE);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("searchToDoActivities end");
        }

        return activitiesDT;
    }

    /**
     * @param pActivity
     *            attività che deve essere eseguita.
     * @param pLoggedUser
     *            user che esegue la chiamata al servizio.
     * @return un array contenente le attività in stato pending.
     * @throws KPeopleServiceException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    public final Activity executeActivity(final Activity pActivity,
            final User pLoggedUser) throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("executeActivity start : " + pActivity + " "
                    + pLoggedUser);
        }

        IActivityManager mng = new ActivityManager();

        // Lettura parametri formali
        Activity activityDT = pActivity;
        User user = pLoggedUser;

        try {
            it.webscience.kpeople.be.Activity activityBE =
                    ActivityConverter.toBE(activityDT);

            it.webscience.kpeople.be.User loggedUserBE =
                    UserConverter.toBE(user);

            activityBE = mng.executeActivity(activityBE, loggedUserBE);

            // Factory ACTIVITY HANDLER

            activityDT = ActivityConverter.toService(activityBE);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("executeActivity end");
        }

        return activityDT;

    }

    /**
     * @param hpmActivityId
     *            id dell'attività che deve essere ricercata.
     * @param user
     *            utente che invoca il servizio.
     * @return l'attività ricercata.
     * @throws KPeopleServiceException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    public final Activity getActivityByHpmId(final String hpmActivityId,
            final User user) throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("getActivityByHpmId start : " + hpmActivityId);
        }

        IActivityManager mng = new ActivityManager();

        it.webscience.kpeople.be.User loggedUserBE = UserConverter.toBE(user);

        it.webscience.kpeople.be.Activity activityBE = null;

        try {
            activityBE = mng.getActivityByHpmId(hpmActivityId, loggedUserBE);
        } catch (KPeopleBLLException e) {
            throw new KPeopleServiceException(e.getMessage());
        }

        Activity activityDT = ActivityConverter.toService(activityBE);

        if (logger.isDebugEnabled()) {
            logger.debug("getActivityByHpmId end");
        }

        return activityDT;

    }

}
