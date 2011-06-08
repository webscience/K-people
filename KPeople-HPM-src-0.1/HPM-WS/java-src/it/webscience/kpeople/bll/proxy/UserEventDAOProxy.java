package it.webscience.kpeople.bll.proxy;

import it.webscience.kpeople.be.ProcessEvent;
import it.webscience.kpeople.be.UserEvent;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.report.IUserEventDAO;
import it.webscience.kpeople.dal.report.ProcessEventDAO;
import it.webscience.kpeople.dal.report.UserEventDAO;

import java.util.List;

public class UserEventDAOProxy implements IUserEventDAO {

    public final List<UserEvent> getUserEventsCreator()
            throws KPeopleDAOException {
        UserEventDAO dao = new UserEventDAO();

        return dao.getUserEventsCreator();
    }

    public final List<UserEvent> getUserEventsCommunicationTo()
            throws KPeopleDAOException {
        UserEventDAO dao = new UserEventDAO();

        return dao.getUserEventsCommunicationTo();
    }

    public final List<UserEvent> getUserEventsCommunicationCC()
            throws KPeopleDAOException {
        UserEventDAO dao = new UserEventDAO();

        return dao.getUserEventsCommunicationCC();
    }
    
    public final List<UserEvent> getUserEventsCommunicationFrom()
            throws KPeopleDAOException {
        UserEventDAO dao = new UserEventDAO();

        return dao.getUserEventsCommunicationFrom();
    }

}
