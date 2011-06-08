package it.webscience.kpeople.dal.report;

import it.webscience.kpeople.be.ProcessEvent;
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

public class ProcessEventDAO implements IProcessEventDAO{
    /** logger. */
    private Logger logger;

    /** Costruttore. */
    public ProcessEventDAO() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    public final List<ProcessEvent>getProcessEvents() throws KPeopleDAOException {

        if (logger.isDebugEnabled()){
            logger.debug("start - getProcessEvents");
        }
        List<ProcessEvent> results = new ArrayList<ProcessEvent>();
        try {
            results = getSesameProcessIds();

        } catch (Exception e) {
            throw new KPeopleDAOException(e.getMessage());
        }
        if (logger.isDebugEnabled()) {
            logger.debug("end - getProcessEvents");
        }
        return results;
    }

    /**
     * @return lista di ProcessEvent
     * @throws RepositoryException eccezione durante l'elaborazione.
     * @throws MalformedQueryException eccezione durante l'elaborazione.
     * @throws QueryEvaluationException eccezione durante l'elaborazione.
     */
    private List<ProcessEvent>getSesameProcessIds() throws RepositoryException,
    MalformedQueryException, QueryEvaluationException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getSesameProcessIds");
        }

        List<ProcessEvent> listProcessEventFromSesame =
            new ArrayList<ProcessEvent>();
        Repository sesameRepository =
            OCRdfRepository.getInstance().getSesameRepository();

        RepositoryConnection con = sesameRepository.getConnection();
        String query = null;
        String prefix = null;

        prefix =
            "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                    + "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"
                    + "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                    + "PREFIX protons:<http://proton.semanticweb.org/2005/04/protons#>"
                    + "PREFIX protont:<http://proton.semanticweb.org/2005/04/protont#>"
                    + "PREFIX kp:<http://kpeople.webscience.it/ontologies/2010/12/kpbase#>";
        query =
            prefix
               + "SELECT ?Event ?Process WHERE { ?Process "
               + "<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent>?Event}";

        logger.debug("Query: " + query);
        TupleQuery tupleQuery =
            con.prepareTupleQuery(QueryLanguage.SPARQL, query);

        TupleQueryResult result = tupleQuery.evaluate();

        listProcessEventFromSesame = addToProcessEventList(result);
        result.close();
        con.close();
        return listProcessEventFromSesame;
    }

    /**
     * Tale metodo scorre il resultset contenente l'elenco degli HpmProcessId
     * e contemporaneamente richiama il metodo getSesameEventIds per ottenere
     * la lista degli HpmEventId associati a ciascun processo.
     * @param result ResultSet
     * @return lista di ProcessEvent
     * @throws QueryEvaluationException eccezione durante l'elaborazione.
     * @throws RepositoryException eccezione durante l'elaborazione.
     * @throws MalformedQueryException eccezione durante l'elaborazione.
     */
    private List<ProcessEvent> addToProcessEventList(
            final TupleQueryResult result) throws QueryEvaluationException,
            RepositoryException, MalformedQueryException {
        List<ProcessEvent> listProcessEventFromSesame = new ArrayList<ProcessEvent>();

        while (result.hasNext()) {
            ProcessEvent processEvent = new ProcessEvent();
            BindingSet bindingSet = result.next();

            String hpmProcessId = bindingSet.getValue("Process").toString()
                    .split("#")[1];
            String hpmEventId = bindingSet.getValue("Event").toString()
                    .split("#")[1];
            processEvent.setHpmProcessId(hpmProcessId);
            processEvent.setHpmEventId(hpmEventId);
            listProcessEventFromSesame.add(processEvent);
        }
        return listProcessEventFromSesame;
    }
}
