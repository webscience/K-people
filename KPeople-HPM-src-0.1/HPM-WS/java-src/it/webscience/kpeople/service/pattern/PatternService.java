package it.webscience.kpeople.service.pattern;

import it.webscience.kpeople.bll.IPatternManager;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.PatternManager;
import it.webscience.kpeople.service.datatypes.Pattern;
import it.webscience.kpeople.service.datatypes.PatternState;
import it.webscience.kpeople.service.datatypes.PatternType;
import it.webscience.kpeople.service.datatypes.Process;
import it.webscience.kpeople.service.datatypes.User;
import it.webscience.kpeople.service.datatypes.converter.PatternConverter;
import it.webscience.kpeople.service.datatypes.converter.PatternStateConverter;
import it.webscience.kpeople.service.datatypes.converter.PatternTypeConverter;
import it.webscience.kpeople.service.datatypes.converter.ProcessConverter;
import it.webscience.kpeople.service.datatypes.converter.UserConverter;
import it.webscience.kpeople.service.exception.KPeopleServiceException;
import it.webscience.kpeople.service.process.ProcessService;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * Servizio per la gestione dei pattern.
 */
public class PatternService {

    /** logger. */
    private Logger logger;

    /**
     * Costruttore di default.
     */
    public PatternService() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Servizio che ritorna tutte le tipologie di pattern presenti a sistema.
     * @param pIgnoreShowInList
     * @param pLoggedUser
     *            utente che richiede il servizio.
     * @param pIgnoreShowInList
     *            parametro di configurazione per activiti.
     * @return patternTypes: array di oggetti di tipo PatternType
     * @throws KPeopleServiceException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    public final PatternType[] getPatternTypes(final boolean pIgnoreShowInList,
            final User pLoggedUser) throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternTypes: " + pIgnoreShowInList + " "
                    + pLoggedUser);
        }

        IPatternManager mng = new PatternManager();

        PatternType[] patternTypes = null;
        try {
            it.webscience.kpeople.be.User loggedUserBE =
                    UserConverter.toBE(pLoggedUser);

            List<it.webscience.kpeople.be.PatternType> patternTypesBe =
                    mng.getPatternTypes(pIgnoreShowInList, loggedUserBE);
            patternTypes = PatternTypeConverter.toService(patternTypesBe);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getOwners - End. PatternType estratti: "
                    + patternTypes.length);
        }

        return patternTypes;

    }

    /**
     * @param pPattern
     *            che deve essere avviato.
     * @param pLoggedUser
     *            utente che richiama il servizio.
     * @param pProcess
     *            processo associato al pattern.
     * @return il pattern creato.
     * @throws KPeopleServiceException
     *             eccezione generata durante l'esecuzione del servizio.
     */
    public final Pattern startPattern(
            final Pattern pPattern,
            final User pLoggedUser,
            final Process pProcess)
            throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("startPattern: " + pPattern);
        }

        String hpmProcessId = pProcess.getHpmProcessId();
        ProcessService processService = new ProcessService();

        Process processDT =
                processService.getProcessByHpmId(hpmProcessId, pLoggedUser);

        PatternManager mng = new PatternManager();

        // Lettura parametri formali
        Pattern patternDT = pPattern;
        User userDT = pLoggedUser;

        try {
            it.webscience.kpeople.be.Pattern patternBE =
                    PatternConverter.toBE(patternDT);

            it.webscience.kpeople.be.User userBE = UserConverter.toBE(userDT);

            it.webscience.kpeople.be.Process processBE =
                    ProcessConverter.toBE(processDT);

            // chiamata al servizio
            it.webscience.kpeople.be.Pattern patternBENew =
                    mng.startPattern(patternBE, userBE, processBE);

            patternDT = PatternConverter.toService(patternBENew);
        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("startPattern - End. ");
        }

        return patternDT;
    }


    /**
     * Servizio che consente di estrarre tutte le informazioni associate ad un
     * pattern.
     * @param pPattern
     *            business entity relativa al pattern di cui si desidera
     *            estrarre informazioni
     * @param pLoggedUser
     *            Utente loggato
     * @return dettagli del pattern
     * @throws KPeopleServiceException
     *             eccezione
     */
    public final Pattern patternDetailByHpmPatternId(final Pattern pPattern,
            final User pLoggedUser) throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("patternDetailByHpmPatternId: "
                    + pPattern.getHpmPatternId());
        }

        PatternManager mng = new PatternManager();

        // Lettura parametri formali
        Pattern patternDT = pPattern;
        User userDT = pLoggedUser;

        try {
            it.webscience.kpeople.be.Pattern patternBE =
                    PatternConverter.toBE(patternDT);

            it.webscience.kpeople.be.User userBE = UserConverter.toBE(userDT);

            // chiamata al servizio
            it.webscience.kpeople.be.Pattern patternBENew =
                    mng.patternDetailByHpmPatternId(patternBE, userBE);

            patternDT = PatternConverter.toService(patternBENew);

        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("patternDetailByHpmPatternId end");
        }
        return patternDT;
    }

    /**
     * @param pIdPatternType
     * @param pLoggedUser
     * @exception KPeopleServiceException
     *                eccezione
     */
    public final PatternType getPatternTypeByPatternTypeId(
            final PatternType pPatternType, final User pLoggedUser)
            throws KPeopleServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternTypeByPatternTypeId start: "
                    + pPatternType.getIdPatternType());
        }

        // Lettura parametri formali
        PatternType patternTypeDT = pPatternType;
        User loggedUserDT = pLoggedUser;

        // Converzione da DataTypes a BEs
        it.webscience.kpeople.be.PatternType patternTypeBE =
                PatternTypeConverter.toBE(patternTypeDT);
        it.webscience.kpeople.be.User loggedUserBE =
                UserConverter.toBE(loggedUserDT);

        try {
            PatternManager mng = new PatternManager();
            patternTypeBE =
                    mng.getPatternTypeByPatternTypeId(patternTypeBE,
                            loggedUserBE);

        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternTypeByPatternTypeId end");
        }

        return PatternTypeConverter.toService(patternTypeBE);
    }
    
    public boolean closePattern(String activityProcessInstanceId) {
    	return true;
    }
    
    public Pattern closePattern(Pattern pPattern, User pLoggedUser)
		throws KPeopleServiceException { 
    	
    	if (logger.isDebugEnabled()) {
            logger.debug("closePattern start: "
                    + pPattern + " " + pLoggedUser);
        }
    	
    	//Lettura paramtri formali
    	Pattern patternDT = pPattern;
    	User loggedUserDT = pLoggedUser;
    	
    	
    	// Converzione da DataTypes a BEs
        it.webscience.kpeople.be.Pattern patternBE =
                PatternConverter.toBE(patternDT);
        it.webscience.kpeople.be.User loggedUserBE =
                UserConverter.toBE(loggedUserDT);
        
        try {
            PatternManager mng = new PatternManager();
            patternBE =
                    mng.closePattern(patternBE, loggedUserBE);

        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }
        
        patternDT = PatternConverter.toService(patternBE);
    	
    	if (logger.isDebugEnabled()) {
            logger.debug("closePattern end ");
                    
        }
    	
    	return patternDT;
    }

    public PatternState getPatternStateByPatternStateId(
    		final PatternState pPatternState, final User pLoggedUser) 
    	throws KPeopleServiceException {
    	
    	if (logger.isDebugEnabled()) {
            logger.debug("getPatternStateByPatternStateId start: "
                    + pPatternState);
        }
    	
    	//Lettura paramtri formali
    	PatternState patternStateDT = pPatternState;
    	User loggedUserDT = pLoggedUser;
    	
    	
    	// Converzione da DataTypes a BEs
        it.webscience.kpeople.be.PatternState patternStateBE =
                PatternStateConverter.toBE(patternStateDT);
        it.webscience.kpeople.be.User loggedUserBE =
                UserConverter.toBE(loggedUserDT);
        
        try {
            PatternManager mng = new PatternManager();
            patternStateBE =
                    mng.getPatternStateByPatternStateId(
                    		patternStateBE, loggedUserBE);

        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }
        
        patternStateDT = PatternStateConverter.toService(patternStateBE);
        
        if (logger.isDebugEnabled()) {
            logger.debug("getPatternStateByPatternStateId end "
                    + pPatternState);
        }
        
        return patternStateDT;
    }
    
    public boolean closePatternFromActiviti(
    		final String pActivitiProcessInstanceId, final String pHpmUserId) 
    	throws KPeopleServiceException {
    	
    	if (logger.isDebugEnabled()) {
            logger.debug("closePatternFromActiviti start: "
                    + pActivitiProcessInstanceId + " " 
                    + pHpmUserId);
        }
    	
    	//Lettura paramtri formali
    	String activitiProcessInstanceId = pActivitiProcessInstanceId;
    	String hpmUserId = pHpmUserId;
    	
    	boolean ret = false;
        try {
            PatternManager mng = new PatternManager();
            ret = mng.closePatternFromActiviti(
            		activitiProcessInstanceId, 
            		pHpmUserId);

            

        } catch (KPeopleBLLException e) {
            e.printStackTrace();
            throw new KPeopleServiceException(e.getMessage());
        }
        
        
        if (logger.isDebugEnabled()) {
            logger.debug("closePatternFromActiviti end ");
        }
        
        return ret;
    	}
}
