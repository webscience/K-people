package it.webscience.kpeople.dal.pattern;

import java.sql.SQLException;
import java.util.List;

import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;

import it.webscience.kpeople.dal.exception.KPeopleDAOException;

/**
 * Interfaccia per la classe DAO relativa ai pattern.
 */
public interface IPatternDAO {

    /**
     * @param pIgnoreShowInList
     *            specifica se visualizzare o meno i patternType con il flag
     *            ShowInList su tre.
     * @throws SQLException
     *             eccezioni db
     * @return ritorna l'elenco dei pattern type
     */
    List<PatternType> getPatternTypes(final boolean pIgnoreShowInList,
            final User pLoggedUser) throws SQLException;

    /**
     * @param pPattern
     *            pattern che deve essere avviato.
     * @param pUser
     *            utente che richiama il servizio.
     * @param pProcess
     *            processo associato al pattern
     * @throws KPeopleDAOException
     *             eccezione generata a livello dao.
     * @return ritorna l'elenco dei pattern type
     */
    Pattern startPattern(final Pattern pPattern, final User pUser,
            final Process pProcess) throws KPeopleDAOException;
    
    
    /**
     * @param pPattern
     *            pattern del quale si vogliono i dettagli.
     * @param pUser
     *            utente che richiama il servizio.
     * @throws KPeopleDAOException
     *             eccezione generata a livello dao.
     * @return ritorna l'elenco dei pattern type
     */
    Pattern patternDetailByHpmPatternId(final Pattern pPattern, final User pUser
            ) throws KPeopleDAOException;

    /**
     * 
     * @param pHpmPatternId
     * @return
     */
    Pattern getPatternByHpmPatternId(final String pHpmPatternId) 
    	throws KPeopleDAOException;
    
    /**
     * @param pIdPatternType
     * @param pLoggedUser
     * @exception KPeopleDAOException eccezione
     */
    PatternType getPatternTypeByPatternTypeId(
    		final PatternType pPatternType, 
    		final User pLoggedUser) 
    	throws KPeopleDAOException;
    
    /**
     * 
     * @param pattern
     * @param pLoggedUser
     * @return
     * @throws KPeopleDAOException
     */
    Pattern closePattern(
    		final Pattern pattern, 
    		final User pLoggedUser) 
    	throws KPeopleDAOException;
    
    /**
     * 
     * @param pPatternState
     * @param pLoggedUser
     * @return
     * @throws KPeopleDAOException
     */
    PatternState getPatternStateByPatternStateId(
    		final PatternState pPatternState,
    		final User pLoggedUser)
    	throws KPeopleDAOException;
    
    /**
     * 
     * @param pActivitiProcessInstanceId
     * @return
     * @throws KPeopleDAOException
     */
    boolean closePatternFromActiviti(
    		final String pActivitiProcessInstanceId, 
    		final String pHpmUserId) 
		throws KPeopleDAOException;
}
