package it.webscience.kpeople.bll.impl;


import it.webscience.kpeople.be.CommunicationEvent;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.PatternState;
import it.webscience.kpeople.be.PatternType;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.bll.IPatternManager;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.impl.cross.MailManager;
import it.webscience.kpeople.bll.proxy.PatternDAOProxy;
import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.service.exception.KPeopleServiceException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 *  .
 * @author gnoni
 *
 */
public class PatternManager implements IPatternManager {

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
    public PatternManager() {
        logger = Logger.getLogger(this.getClass().getName());
        singleton = Singleton.getInstance();
    }

    /**
     * Restituisce gli utenti proprietari di almeno un processo.
     * @param pIgnoreShowInList
     * @return elenco tipologie di pattern
     * @throws KPeopleBLLException
     *             eccezione durante l'elaborazione
     */
    @Override
    public final List<PatternType> getPatternTypes(
            final boolean pIgnoreShowInList,
            final User pLoggedUser) throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternTypes: " + pIgnoreShowInList + pLoggedUser);
        }

        User loggedUser = pLoggedUser;
        PatternDAOProxy dao = new PatternDAOProxy();
        List<PatternType> daoResult = null;
        try {
            daoResult = dao.getPatternTypes(pIgnoreShowInList, loggedUser);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end getPatternTypes");
        }

        return daoResult;

    }

    /**
     * Restituisce gli utenti proprietari di almeno un processo.
     * @param pIgnoreShowInList
     * @param pPattern
     *            pattern che deve essere creato.
     * @param pUser
     *            utente che richiama il servizio.
     * @param pProcess
     *            processo associato al pattern.
     * @return elenco tipologie di pattern
     * @throws KPeopleBLLException
     *             eccezione durante l'elaborazione
     */
    @Override
    public final Pattern startPattern(
            final Pattern pPattern,
            final User pUser,
            final Process pProcess) throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("startPattern: " + pPattern);
        }

        Pattern daoResult = null;

        try {
            Pattern pattern = pPattern;
            User user = pUser;
            Process process = pProcess;

            PatternDAOProxy dao = new PatternDAOProxy();
            daoResult = dao.startPattern(pattern, user, process);

            if (pPattern.getDocList() != null &&
                    pPattern.getDocList().size() > 0) {
                logger.debug("doclist size: " + pPattern.getDocList().size());

                SendEventManager sem = new SendEventManager();
                sem.sendEventCreatePattern(pPattern, pUser, pProcess);
            }

            sendEmailNotificationPattern(pProcess, pPattern, pUser);
        } catch (Exception e) {
            logger.fatal(e.getMessage());
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end startPattern");
        }

        return daoResult;
    }

    /**
     * Metodo per l'invio della email di creazione nuovo pattern.
     * @param processBE
     *            processo al quale è legato il pattern.
     * @param patternBE
     *            il pattern che è stato creato.
     * @param userBE
     *            utente che ha richiesto l'avvio del nuovo pattern.
     * @throws KPeopleBLLException
     *             eccezione generica del livello BLL.
     */
    private void sendEmailNotificationPattern(
            final it.webscience.kpeople.be.Process processBE,
            final it.webscience.kpeople.be.Pattern patternBE,
            final it.webscience.kpeople.be.User userBE)
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("sendEmailNotification: start");
        }

        CommunicationEvent notification = new CommunicationEvent();
        notification.setBody(singleton
                .getProperty("carbon.mail-service.body.pattern")
                + " \""
                + patternBE.getName()
                + "\""
                + " associato al processo \""
                + processBE.getName() + " \"");

        notification.setObject(singleton
                .getProperty("carbon.mail-service.object.pattern"));

        // aggiunta dell'email FROM
        // it.webscience.kpeople.be.User userFrom =
        // new it.webscience.kpeople.be.User();
        // userFrom.setEmail();
        notification.setUserFrom(patternBE.getPatternRequestor());

        // aggiunta dell'email TO
        List<it.webscience.kpeople.be.User> usersTo =
                new ArrayList<it.webscience.kpeople.be.User>();
        // it.webscience.kpeople.be.User userTo =
        // new it.webscience.kpeople.be.User();
        // userTo.setEmail(patternBE.getPatternProvider().getHpmUserId());
        usersTo.add(patternBE.getPatternProvider());
        notification.setToUser(usersTo);

        // aggiunta dell'email CC

        List<it.webscience.kpeople.be.User> ccUsers = patternBE.getCcUsers();
        // aggiungo anche il requestor nella lista degli utenti in copia
        ccUsers.add(patternBE.getPatternRequestor());
        notification.setCcUser(ccUsers);

        MailManager mailService = new MailManager();

        List<it.webscience.kpeople.be.Process> processes =
                new ArrayList<it.webscience.kpeople.be.Process>();
        processes.add(processBE);

        List<it.webscience.kpeople.be.Pattern> patterns =
                new ArrayList<it.webscience.kpeople.be.Pattern>();
        patterns.add(patternBE);

        try {
            mailService.sendMailPattern(
                    notification, processes, userBE, patterns, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("sendEmailNotification: end");
        }
    }

    /**
     * Consente di estrarre tutte le informazioni associate ad un pattern.
     * @param pPattern business entity del pattern che si desidera estrarre
     * @param pLoggedUser Utente loggato nel sistema
     * @return pattern detail
     * @throws KPeopleServiceException eccezione
     */
    @Override
    public final Pattern patternDetailByHpmPatternId(final Pattern pPattern,
            final User pLoggedUser)
         throws KPeopleBLLException {
    	
    	if (logger.isDebugEnabled()) {
            logger.debug("patternDetailByHpmPatternId: " + pPattern);
        }

        Pattern pattern = pPattern;
        User user = pLoggedUser;
        

        PatternDAOProxy dao = new PatternDAOProxy();
        Pattern daoResult = null;
        try {
            daoResult = dao.patternDetailByHpmPatternId(pattern, user);
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end patternDetailByHpmPatternId");
        }

        return daoResult;
    }

    /**
     * @param pIdPatternType
     * @param pLoggedUser
     * @exception KPeopleBLLException eccezione
     */
	@Override
	public PatternType getPatternTypeByPatternTypeId(PatternType pPatternType,
			User pLoggedUser) throws KPeopleBLLException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("getPatternTypeByPatternTypeId: " 
            		+ pPatternType.getIdPatternType());
        }

		PatternType patternType = pPatternType;
        User loggedUser = pLoggedUser;
        

        PatternDAOProxy dao = new PatternDAOProxy();
        PatternType daoResult = null;
        try {
            daoResult = dao.getPatternTypeByPatternTypeId(
            		patternType, loggedUser);
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end patternDetailByHpmPatternId");
        }

        return daoResult;
	}

	/**
	 * 
	 */
	@Override
	public final Pattern closePattern(Pattern pPattern, User pLoggedUser)
			throws KPeopleBLLException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("closePattern start: " 
            		+ pPattern + " " + pLoggedUser);
        }

		//Lettura parametri formali
		Pattern pattern = pPattern;
        User loggedUser = pLoggedUser;
        

        PatternDAOProxy dao = new PatternDAOProxy();
        
        try {
            pattern = dao.closePattern(pattern, loggedUser);
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("closePattern end");
        }
        
        return pattern;
	}

	/**
	 * 
	 */
	@Override
	public final PatternState getPatternStateByPatternStateId(
			PatternState pPatternState, User pLoggedUser)
		throws KPeopleBLLException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("getPatternStateByPatternStateId start: " 
            		+ pPatternState + " " + pLoggedUser);
        }

		//Lettura parametri formali
		PatternState patternState = pPatternState;
        User loggedUser = pLoggedUser;
        

        PatternDAOProxy dao = new PatternDAOProxy();
        
        try {
            patternState = dao.getPatternStateByPatternStateId(
            		pPatternState, pLoggedUser);  
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getPatternStateByPatternStateId end");
        }
        
        return patternState;
	}
	
	/**
	 * 
	 */
	public boolean closePatternFromActiviti(
			final String pActivitiProcessInstanceId, 
			final String pHpmUserId) 
		throws KPeopleBLLException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("closePatternFromActiviti start: " 
            		+ pActivitiProcessInstanceId + " " + pHpmUserId);
        }

		//Lettura parametri formali
		String activitiProcessInstanceId = pActivitiProcessInstanceId;
		String hpmUserId = pHpmUserId;
		
        boolean ret = false;
        
        PatternDAOProxy dao = new PatternDAOProxy();
        
        try {
        	ret = dao.closePatternFromActiviti(
            		activitiProcessInstanceId, hpmUserId);
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("closePatternFromActiviti end");
        }
        
        return ret;
	}
}
