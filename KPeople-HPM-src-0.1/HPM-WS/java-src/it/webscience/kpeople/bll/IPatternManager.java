package it.webscience.kpeople.bll;

import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiStartPatternException;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.util.List;

/**
 * Interfaccia del manager Pattern.
 */
public interface IPatternManager {

    /**
     * Restituisce gli utenti proprietari di almeno un processo.
     * @param pIgnoreShowInList
     * @return elenco tipologie di pattern
     * @throws KPeopleBLLException
     *             eccezione durante l'elaborazione
     */
    List<PatternType> getPatternTypes(final boolean pIgnoreShowInList,
            final User pLoggedUser) throws KPeopleBLLException;

    /**
     * Consente di avviare un pattern sul workflow engine Activiti.
     * @param pPattern
     *            pattern che deve essere creato.
     * @param pProcess
     *            processo associato al pattern.
     * @param pUser
     *            utente che richiede il servizio..
     * @return versione aggiornata del pattern che viene passato
     * @throws KPeopleBLLException
     *             eccezione durante l'elaborazione
     */
    Pattern startPattern(final Pattern pPattern, final User pUser,
            final Process pProcess) throws KPeopleBLLException;
    
    
    /**
     * Consente di estrarre tutte le informazioni associate ad un pattern.
     * @param pPattern business entity del pattern che si desidera estrarre
     * @param pLoggedUser Utente loggato nel sistema
     * @return pattern detail
     * @throws KPeopleServiceException eccezione
     */
    Pattern patternDetailByHpmPatternId(final Pattern pPattern, final User pLoggedUser)
         throws KPeopleBLLException;
    
    /**
     * @param pIdPatternType
     * @param pLoggedUser
     * @exception KPeopleBLLException eccezione
     */
    PatternType getPatternTypeByPatternTypeId(
    		final PatternType pPatternType, final User pLoggedUser) 
    	throws KPeopleBLLException;
    
    /**
     * 
     * @param pPattern
     * @param pLoggedUser
     * @return
     * @throws KPeopleBLLException
     */
    Pattern closePattern(
    		final Pattern pPattern, final User pLoggedUser) 
    	throws KPeopleBLLException;
    
    /**
     * 
     * @param pPatternState
     * @param pLoggUser
     * @return
     * @throws KPeopleBLLException
     */
    PatternState getPatternStateByPatternStateId(
    		final PatternState pPatternState, final User pLoggUser)
    	throws KPeopleBLLException;
    

    /**
     * 
     * @param pActivitiProcessInstanceId
     * @param pHpmUserId
     * @return
     * @throws KPeopleBLLException
     */
    boolean closePatternFromActiviti(
    		final String pActivitiProcessInstanceId, 
    		final String pHpmUserId) 
		throws KPeopleBLLException;
}
