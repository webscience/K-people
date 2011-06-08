package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.PatternActivityFilter;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.IActivityManager;
import it.webscience.kpeople.bll.exception.KPeopleActivityHandlerException;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.activitihandler.ActivityHandler;
import it.webscience.kpeople.bll.impl.activitihandler.ActivityHandlerFactory;
import it.webscience.kpeople.bll.proxy.ActivityDAOProxy;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiPerformTaskOperationException;
import it.webscience.kpeople.dal.activity.IActivityDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

/**
 * @author filieri Classe per la gestione delle attività.
 */
public class ActivityManager implements IActivityManager {

    /** logger. */
    private Logger logger;

    /**
     * Costruttore di default.
     */
    public ActivityManager() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Metodo per la ricerca delle attività in stato todo.
     * @param pFilter
     *            filtro da utilizzare per la ricerca delle attività.
     * @param pLoggedUser
     *            utente che invoca il servizio
     * @return la lista delle attività in stato todo.
     * @throws KPeopleBLLException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    public final List<Activity> searchToDoActivities(
            final PatternActivityFilter pFilter, final User pLoggedUser)
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("searchToDoActivities start: " + pFilter);
        }

        // Lettura parametriformali
        PatternActivityFilter filter = pFilter;
        User loggedUser = pLoggedUser;

        ActivityDAOProxy dao = new ActivityDAOProxy();

        List<Activity> activities = null;
        try {
            activities = dao.searchToDoActivities(filter, loggedUser);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("searchToDoActivities end");
        }

        return activities;
    }

    /**
     * Metodo per la ricerca delle attività in stato pending.
     * @param pFilter
     *            filtro da utilizzare per la ricerca delle attività.
     * @param pLoggedUser
     *            utente che invoca il servizio
     * @return la lista delle attività in stato pending.
     * @throws KPeopleBLLException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    public final List<Activity> searchPendingActivities(
            final PatternActivityFilter pFilter, final User pLoggedUser)
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("searchToDoActivities start: " + pFilter);
        }

        // Lettura parametri formali
        PatternActivityFilter filter = pFilter;
        User loggedUser = pLoggedUser;

        ActivityDAOProxy dao = new ActivityDAOProxy();

        List<Activity> activities = null;
        try {
            activities = dao.searchPendingActivities(filter, loggedUser);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("searchToDoActivities end");
        }

        return activities;
    }

    /**
     * Metodo per eseguire un'attività.
     * @param pActivity
     *            attività che deve essere eseguita.
     * @param pLoggedUser
     *            utente che invoca il servizio.
     * @return activity attività che è stata eseguita.
     * @throws KPeopleBLLException
     *             eccezione durante l'esecuzione
     */
    public final Activity executeActivity(final Activity pActivity,
            final User pLoggedUser) throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("executeActivity start: " + pActivity + " "
                    + pLoggedUser);
        }

        // Lettura parametri formali
        Activity activity = pActivity;
        User loggedUser = pLoggedUser;

        ActivityDAOProxy dao = new ActivityDAOProxy();

        try {
            activity = dao.executeActivity(activity, loggedUser);

            if (activity.isClosed()) {
                ActivityHandler handler =
                        ActivityHandlerFactory.createActivityHandler(activity);
                try {
                    handler.execute();
                } catch (KPeopleActivityHandlerException e) {
                    e.printStackTrace();
                    throw new KPeopleBLLException(e.getMessage());
                }
            }
        } catch (KPeopleActivitiPerformTaskOperationException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("searchToDoActivities end");
        }

        return activity;
    }


    /**
     * @param hpmActivityId
     *            id dell'attività che deve essere ricercata.
     * @param user
     *            utente che invoca il servizio.
     * @return l'attività ricercata.
     * @throws KPeopleBLLException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    @Override
    public final Activity getActivityByHpmId(final String hpmActivityId,
            final User user) throws KPeopleBLLException {

        IActivityDAO dao = new ActivityDAOProxy();
        Activity result = null;
        
        result = dao.getActivityByHpmId(hpmActivityId, user);
        
        return result;
    }

}
