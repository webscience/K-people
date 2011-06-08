package it.webscience.kpeople.dal.report;

import it.webscience.kpeople.be.ProcessEvent;
import it.webscience.kpeople.be.UserEvent;
import it.webscience.kpeople.bll.impl.sesame.OCRdfRepository;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class UserEventDAO {

    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public UserEventDAO() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Il metodo si occupa di recuperare gli utenti creatori degli eventi.
     * @return lista di UserEvent
     * @throws KPeopleDAOException eccezione durante l'elaborazione.
     */
    public final List<UserEvent> getUserEventsCreator()
            throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("start - getUserEventsCreator");
        }
        List<UserEvent> results = new ArrayList<UserEvent>();
        try {
            results = getSesameUserEventCreator();
        } catch (Exception e) {
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end - getUserEventsCreator");
        }
        return results;
    }

    /**
     * Il metodo effettua un'interrogazione su Sesame pèer recupeare
     * tutti gli utenti creatori degli eventi.
     * @return Lista di UserEvent.
     * @throws RepositoryException eccezione durante l'elaborazione.
     * @throws MalformedQueryException eccezione durante l'elaborazione.
     * @throws QueryEvaluationException eccezione durante l'elaborazione.
     */
    private List<UserEvent> getSesameUserEventCreator()
            throws RepositoryException, MalformedQueryException,
            QueryEvaluationException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getSesameUserEventCreator");
        }

        List<UserEvent> listUserEventCreator = new ArrayList<UserEvent>();
        Repository sesameRepository = OCRdfRepository.getInstance()
                .getSesameRepository();

        RepositoryConnection con = sesameRepository.getConnection();
        String query = null;
        String prefix = null;

        prefix = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"
                + "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX protons:<http://proton.semanticweb.org/2005/04/protons#>"
                + "PREFIX protont:<http://proton.semanticweb.org/2005/04/protont#>"
                + "PREFIX kp:<http://kpeople.webscience.it/ontologies/2010/12/kpbase#>";

        query = prefix
                + "SELECT ?Event ?User WHERE { ?Event "
                + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#eventCreator> ?User}";

        logger.debug("Query: " + query);
        TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
                query);

        TupleQueryResult result = tupleQuery.evaluate();

        listUserEventCreator = createListUserEvent(result);
        result.close();
        con.close();
        return listUserEventCreator;
    }

    /**
     * Il metodo si occupa di recuperare gli utenti destinatari di
     * comunicazioni (TO).
     * @return lista di UserEvent
     * @throws KPeopleDAOException eccezione durante l'elaborazione.
     */
    public final List<UserEvent> getUserEventsCommunicationTo()
            throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("start - getUserEventsCommunicationTo");
        }
        List<UserEvent> results = new ArrayList<UserEvent>();
        try {
            results = getSesameUserEventCommunicationTo();
        } catch (Exception e) {
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end - getUserEventsCommunicationTo");
        }
        return results;
    }


    /**
     * Il metodo effettua un'interrogazione su Sesame per recupeare
     * tutti gli utenti destinatari di comunicazioni(TO).
     * @return Lista di UserEvent.
     * @throws RepositoryException eccezione durante l'elaborazione.
     * @throws MalformedQueryException eccezione durante l'elaborazione.
     * @throws QueryEvaluationException eccezione durante l'elaborazione.
     */
    private List<UserEvent> getSesameUserEventCommunicationTo()
            throws RepositoryException, MalformedQueryException,
            QueryEvaluationException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getSesameUserEventCommunicationTo");
        }

        List<UserEvent> listUserEventCreator = new ArrayList<UserEvent>();
        Repository sesameRepository = OCRdfRepository.getInstance()
                .getSesameRepository();

        RepositoryConnection con = sesameRepository.getConnection();
        String query = null;
        String prefix = null;

        prefix = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"
                + "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX protons:<http://proton.semanticweb.org/2005/04/protons#>"
                + "PREFIX protont:<http://proton.semanticweb.org/2005/04/protont#>"
                + "PREFIX kp:<http://kpeople.webscience.it/ontologies/2010/12/kpbase#>";

        query = prefix
                + "SELECT ?Event ?User WHERE { ?Event "
                + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#communicationTO> ?User}";

        logger.debug("Query: " + query);
        TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
                query);

        TupleQueryResult result = tupleQuery.evaluate();

        listUserEventCreator = createListUserEvent(result);
        result.close();
        con.close();
        return listUserEventCreator;
    }

    /**
     * Il metodo si occupa di recuperare gli utenti destinatari di
     * comunicazioni (CC).
     * @return lista di UserEvent
     * @throws KPeopleDAOException eccezione durante l'elaborazione.
     */
    public final List<UserEvent> getUserEventsCommunicationCC()
            throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("start - getUserEventsCommunicationCC");
        }
        List<UserEvent> results = new ArrayList<UserEvent>();
        try {
            results = getSesameUserEventCommunicationCC();
        } catch (Exception e) {
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end - getUserEventsCommunicationCC");
        }
        return results;
    }


    /**
     * Il metodo effettua un'interrogazione su Sesame per recupeare
     * tutti gli utenti destinatari di comunicazioni(TO).
     * @return Lista di UserEvent.
     * @throws RepositoryException eccezione durante l'elaborazione.
     * @throws MalformedQueryException eccezione durante l'elaborazione.
     * @throws QueryEvaluationException eccezione durante l'elaborazione.
     */
    private List<UserEvent> getSesameUserEventCommunicationCC()
            throws RepositoryException, MalformedQueryException,
            QueryEvaluationException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getSesameUserEventCommunicationCC");
        }

        List<UserEvent> listUserEventCreator = new ArrayList<UserEvent>();
        Repository sesameRepository = OCRdfRepository.getInstance()
                .getSesameRepository();

        RepositoryConnection con = sesameRepository.getConnection();
        String query = null;
        String prefix = null;

        prefix = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"
                + "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX protons:<http://proton.semanticweb.org/2005/04/protons#>"
                + "PREFIX protont:<http://proton.semanticweb.org/2005/04/protont#>"
                + "PREFIX kp:<http://kpeople.webscience.it/ontologies/2010/12/kpbase#>";

        query = prefix
                + "SELECT ?Event ?User WHERE { ?Event "
                + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#communicationCC> ?User}";

        logger.debug("Query: " + query);
        TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
                query);

        TupleQueryResult result = tupleQuery.evaluate();

        listUserEventCreator = createListUserEvent(result);
        result.close();
        con.close();
        return listUserEventCreator;
    }
    
    
    
    
    
    
    
    
    
    /**
     * Il metodo si occupa di recuperare gli utenti destinatari di
     * comunicazioni (From).
     * @return lista di UserEvent
     * @throws KPeopleDAOException eccezione durante l'elaborazione.
     */
    public final List<UserEvent> getUserEventsCommunicationFrom()
            throws KPeopleDAOException {

        if (logger.isDebugEnabled()) {
            logger.debug("start - getUserEventsCommunicationFrom");
        }
        List<UserEvent> results = new ArrayList<UserEvent>();
        try {
            results = getSesameUserEventCommunicationFrom();
        } catch (Exception e) {
            throw new KPeopleDAOException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end - getUserEventsCommunicationFrom");
        }
        return results;
    }


    /**
     * Il metodo effettua un'interrogazione su Sesame per recupeare
     * tutti gli utenti destinatari di comunicazioni(From).
     * @return Lista di UserEvent.
     * @throws RepositoryException eccezione durante l'elaborazione.
     * @throws MalformedQueryException eccezione durante l'elaborazione.
     * @throws QueryEvaluationException eccezione durante l'elaborazione.
     */
    private List<UserEvent> getSesameUserEventCommunicationFrom()
            throws RepositoryException, MalformedQueryException,
            QueryEvaluationException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getSesameUserEventCommunicationFrom");
        }

        List<UserEvent> listUserEventCreator = new ArrayList<UserEvent>();
        Repository sesameRepository = OCRdfRepository.getInstance()
                .getSesameRepository();

        RepositoryConnection con = sesameRepository.getConnection();
        String query = null;
        String prefix = null;

        prefix = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"
                + "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX protons:<http://proton.semanticweb.org/2005/04/protons#>"
                + "PREFIX protont:<http://proton.semanticweb.org/2005/04/protont#>"
                + "PREFIX kp:<http://kpeople.webscience.it/ontologies/2010/12/kpbase#>";

        query = prefix
                + "SELECT ?Event ?User WHERE { ?Event "
                + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#communicationFROM> ?User}";

        logger.debug("Query: " + query);
        TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
                query);

        TupleQueryResult result = tupleQuery.evaluate();

        listUserEventCreator = createListUserEvent(result);
        result.close();
        con.close();
        return listUserEventCreator;
    }



    /**
     * Il metodo scorre il resultSet, crea una BE UserEvent, lo popola
     * e lo aggiunge alla lista di UserEvent.
     * @param result resultSet.
     * @return lista di Userevent
     * @throws QueryEvaluationException eccezione durante l'elaborazione.
     * @throws RepositoryException eccezione durante l'elaborazione.
     * @throws MalformedQueryException eccezione durante l'elaborazione.
     */
    private List<UserEvent> createListUserEvent(final TupleQueryResult result)
            throws QueryEvaluationException, RepositoryException,
            MalformedQueryException {
        List<UserEvent> listUserEvent = new ArrayList<UserEvent>();

        while (result.hasNext()) {
            UserEvent userEvent = new UserEvent();
            BindingSet bindingSet = result.next();

            String hpmEventId = bindingSet.getValue("Event").toString()
                    .split("#")[1];
            String hpmUserId = bindingSet.getValue("User").toString()
                    .split("#")[1];
            userEvent.setHpmEventId(hpmEventId);
            userEvent.setHpmUserId(hpmUserId);

            listUserEvent.add(userEvent);
        }
        return listUserEvent;
    }
}
