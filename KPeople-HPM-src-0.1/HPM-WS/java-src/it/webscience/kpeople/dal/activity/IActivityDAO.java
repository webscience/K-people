package it.webscience.kpeople.dal.activity;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternActivityFilter;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiPerformTaskOperationException;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

/**
 * Interfaccia della classe ActivityDAO.
 * @author gnoni
 */
public interface IActivityDAO {

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
    List<Activity> searchToDoActivities(final PatternActivityFilter pFilter,
            final User pLoggedUser) throws SQLException, KPeopleDAOException,
            NamingException;

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
    List<Activity> searchPendingActivities(final PatternActivityFilter pFilter,
            final User pLoggedUser) throws SQLException, KPeopleDAOException,
            NamingException;

    /**
     * @param pActivity
     *            attività che deve essere eseguita.
     * @param pLoggedUser
     *            user che esegue la chiamata al servizio.
     * @return un array contenente le attività in stato pending.
     * @throws KPeopleActivitiPerformTaskOperationException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    Activity executeActivity(final Activity pActivity, final User pLoggedUser)
            throws KPeopleActivitiPerformTaskOperationException;

    /**
     * @param hpmActivityId
     *            id dell'attività che deve essere ricercata.
     * @param user
     *            utente che invoca il servizio.
     * @return l'attività ricercata.
     */
    Activity getActivityByHpmId(final String hpmActivityId, final User user);

}
