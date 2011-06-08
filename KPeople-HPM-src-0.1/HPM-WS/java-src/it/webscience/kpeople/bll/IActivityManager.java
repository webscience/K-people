package it.webscience.kpeople.bll;

import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternActivityFilter;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiStartPatternException;
import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.util.List;

/**
 * Interfaccia del manager Activity.
 */
public interface IActivityManager {

    /**
     * @param pFilter
     *            filtro per la ricerca delle attività.
     * @param pLoggedUser
     *            user che esegue la chiamata al servizio.
     * @return un array contenente le attività in stato toDo.
     * @throws KPeopleBLLException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    List<Activity> searchToDoActivities(final PatternActivityFilter pFilter,
            final User pLoggedUser) throws KPeopleBLLException;

    /**
     * @param pFilter
     *            filtro per la ricerca delle attività.
     * @param pLoggedUser
     *            user che esegue la chiamata al servizio.
     * @return un array contenente le attività in stato pending.
     * @throws KPeopleBLLException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    List<Activity> searchPendingActivities(final PatternActivityFilter pFilter,
            final User pLoggedUser) throws KPeopleBLLException;

    /**
     * @param pActivity
     *            attività che deve essere eseguita.
     * @param pLoggedUser
     *            user che esegue la chiamata al servizio.
     * @return un array contenente le attività in stato pending.
     * @throws KPeopleBLLException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    Activity executeActivity(final Activity pActivity, final User pLoggedUser)
            throws KPeopleBLLException;

    /**
     * @param hpmActivityId
     *            id dell'attività che deve essere ricercata.
     * @param user
     *            utente che invoca il servizio.
     * @return l'attività ricercata.
     * @throws KPeopleBLLException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    Activity getActivityByHpmId(final String hpmActivityId, final User user)
            throws KPeopleBLLException;

}
