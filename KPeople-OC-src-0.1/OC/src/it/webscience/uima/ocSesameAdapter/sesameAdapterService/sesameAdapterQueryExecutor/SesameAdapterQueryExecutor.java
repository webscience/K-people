package it.webscience.uima.ocSesameAdapter.sesameAdapterService.sesameAdapterQueryExecutor;

import it.webscience.uima.ocControl.ocSesameServerUtility.OCRdfRepository;

import org.apache.log4j.Logger;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class SesameAdapterQueryExecutor {

    /** istanza del server sesame. */
    private static Repository sesameRepository;

    /** istanza di connessione al server sesame. */
    private static RepositoryConnection con;

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    /**
     * Costruttore di default che istanzia il repository.
     */
    public SesameAdapterQueryExecutor() {
        sesameRepository = OCRdfRepository.getInstance().getSesameRepository();
        try {
            con = sesameRepository.getConnection();
        } catch (RepositoryException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Implementazione del metodo per interrogare l'ontologia presente sul
     * server Sesame al fine di individuare il processo associato ad una
     * specifica keyword.
     * @param keyword
     *            - keyword da utilizzare come indice per la query.
     * @return String - rappresenta l'URI del processo associato alla keyword.
     */
    public final String queryProcessByKeyword(final String keyword) {

        String processUri = null;
        // String prefix =
        // "PREFIX kpbase:<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#>"
        // +
        // "PREFIX kpexample:<http://kpeople.webscience.it/ontologies/2010/12/kpexample.owl#> "
        // + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
        //
        // String queryString =
        // prefix + "SELECT ?x WHERE { ?x kpbase:processKeyword kpexample:"
        // + keyword + " }";

        String prefix =
                "PREFIX kpbase:<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#>"

                + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";

        String queryString =
                prefix
                        + "SELECT ?x WHERE { ?x kpbase:processKeyword kpbase:"
                        + keyword + " }";

        TupleQuery tupleQuery;
        TupleQueryResult result = null;
        try {
            tupleQuery =
                    con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value process = bindingSet.getValue("x");
                processUri = process.toString();

            }

            result.close();

        } catch (RepositoryException e) {
            logger.error(e.getMessage());

        } catch (MalformedQueryException e) {
            logger.error(e.getMessage());

        } catch (QueryEvaluationException e) {
            logger.error(e.getMessage());

        } finally {
            try {
                con.close();
            } catch (RepositoryException e) {
                logger.error(e.getMessage());

            }
        }

        return processUri;
    }
    
    /**
     * Implementazione del metodo per interrogare l'ontologia presente sul
     * server Sesame al fine di individuare il pattern associato ad uno
     * specifico id.
     * @param id
     *            - id da utilizzare come indice per la query.
     * @return String - rappresenta l'URI del pattern associato al'id.
     */
    public final String queryPatternById(final String id) {

        String patternUri = null;

        String prefix =
                "PREFIX kpbase:<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#> "

                + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";

        String queryString =
                prefix
                        + " SELECT ?x WHERE { ?x kpbase:eventID \"" + id + "\" }";
        System.out.println(queryString);
        TupleQuery tupleQuery;
        TupleQueryResult result = null;
        try {
            tupleQuery =
                    con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            result = tupleQuery.evaluate();
            //System.out.println("-----process-------");
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value pattern = bindingSet.getValue("x");
                patternUri = pattern.toString();

            }

            result.close();

        } catch (RepositoryException e) {
            logger.error(e.getMessage());

        } catch (MalformedQueryException e) {
            logger.error(e.getMessage());

        } catch (QueryEvaluationException e) {
            logger.error(e.getMessage());

        } finally {
            try {
                con.close();
            } catch (RepositoryException e) {
                logger.error(e.getMessage());

            }
        }

        return patternUri;
    }

}
