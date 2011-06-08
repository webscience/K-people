package it.webscience.kpeople.bll.impl;

import it.webscience.kpeople.be.ProcessEvent;
import it.webscience.kpeople.be.UserEvent;
import it.webscience.kpeople.bll.exception.KPeopleBLLException;
import it.webscience.kpeople.bll.proxy.ProcessEventDAOProxy;
import it.webscience.kpeople.bll.proxy.UserEventDAOProxy;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.util.List;

import org.apache.log4j.Logger;

public class UserEventManager implements IUserEventManager {

    /**
     * logger.
     */
    private Logger logger;

    /**
     * Costruttore della classe.
     */
    public UserEventManager() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    public final List<UserEvent> getUserEventsCreator()
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getUserEventsCreator");
        }
        UserEventDAOProxy dao = new UserEventDAOProxy();
        List<UserEvent> daoResult = null;
        try {
            daoResult = dao.getUserEventsCreator();
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }
        return daoResult;

    }

    public final List<UserEvent> getUserEventsCommunicationTo()
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getUserEventsCommunicationTo");
        }
        UserEventDAOProxy dao = new UserEventDAOProxy();
        List<UserEvent> daoResult = null;
        try {
            daoResult = dao.getUserEventsCommunicationTo();
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }
        return daoResult;

    }

    public final List<UserEvent> getUserEventsCommunicationCC()
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getUserEventsCommunicationCC");
        }
        UserEventDAOProxy dao = new UserEventDAOProxy();
        List<UserEvent> daoResult = null;
        try {
            daoResult = dao.getUserEventsCommunicationCC();
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }
        return daoResult;

    }
    
    public final List<UserEvent> getUserEventsCommunicationFrom()
            throws KPeopleBLLException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getUserEventsCommunicationFrom");
        }
        UserEventDAOProxy dao = new UserEventDAOProxy();
        List<UserEvent> daoResult = null;
        try {
            daoResult = dao.getUserEventsCommunicationFrom();
        } catch (KPeopleDAOException e) {
            e.printStackTrace();
            throw new KPeopleBLLException(e.getMessage());
        }
        return daoResult;

    }

}
