package it.webscience.kpeople.bll.proxy;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternActivityFilter;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiPerformTaskOperationException;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiStartPatternException;
import it.webscience.kpeople.dal.activity.ActivityDAO;
import it.webscience.kpeople.dal.activity.IActivityDAO;
import it.webscience.kpeople.dal.exception.KPeopleActivitiException;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.pattern.IPatternDAO;
import it.webscience.kpeople.dal.pattern.PatternDAO;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

/**
 * Proxy per la classe PatternDAO.
 */
public class ActivityDAOProxy implements IActivityDAO {

    /**
     * @param pFilter
     *            filtro per la ricerca delle attività.
     * @param pLoggedUser
     *            user che esegue la chiamata al servizio.
     * @return un array contenente le attività in stato toDo.
     * @throws SQLException
     *             eccezione generata durante l'esecuzione del servizio.
     * @throws KPeopleDAOException
     *             eccezione generata durante l'esecuzione del servizio.
     * @throws NamingException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    @Override
    public final List<Activity> searchToDoActivities(
            final PatternActivityFilter pFilter, final User pLoggedUser)
            throws SQLException, KPeopleDAOException, NamingException {
        IActivityDAO activityDao = new ActivityDAO();
        return activityDao.searchToDoActivities(pFilter, pLoggedUser);
    }

    /**
     * @param pFilter
     *            filtro per la ricerca delle attività.
     * @param pLoggedUser
     *            user che esegue la chiamata al servizio.
     * @return un array contenente le attività in stato pending.
     * @throws SQLException
     *             eccezione generata durante l'esecuzione del servizio.
     * @throws KPeopleDAOException
     *             eccezione generata durante l'esecuzione del servizio.
     * @throws NamingException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    @Override
    public final List<Activity> searchPendingActivities(
            final PatternActivityFilter pFilter, final User pLoggedUser)
            throws SQLException, KPeopleDAOException, NamingException {
        IActivityDAO activityDao = new ActivityDAO();
        return activityDao.searchPendingActivities(pFilter, pLoggedUser);
    }

    /**
     * @param pActivity
     *            attività che deve essere eseguita.
     * @param pLoggedUser
     *            user che esegue la chiamata al servizio.
     * @return un array contenente le attività in stato pending.
     * @throws KPeopleActivitiPerformTaskOperationException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    @Override
    public final Activity executeActivity(final Activity pActivity,
            final User pLoggedUser)
            throws KPeopleActivitiPerformTaskOperationException {
        IActivityDAO activityDao = new ActivityDAO();
        return activityDao.executeActivity(pActivity, pLoggedUser);

    }

    /**
     * @param hpmActivityId
     *            id dell'attività che deve essere ricercata.
     * @param user
     *            utente che invoca il servizio.
     * @return l'attività ricercata.
     */
    @Override
    public final Activity getActivityByHpmId(final String hpmActivityId,
            final User user) {
        IActivityDAO activityDao = new ActivityDAO();
        return activityDao.getActivityByHpmId(hpmActivityId, user);
    }

}
