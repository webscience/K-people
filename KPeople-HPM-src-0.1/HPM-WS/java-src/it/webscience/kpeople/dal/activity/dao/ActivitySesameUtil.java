package it.webscience.kpeople.dal.activity.dao;

import it.webscience.kpeople.bll.impl.sesame.OCRdfRepository;
import it.webscience.kpeople.dal.exception.KPeopleSesameException;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class ActivitySesameUtil {

	public List<String> getHpmPatternIdFromHpmProjectId(String pHpmProcessid) throws KPeopleSesameException  {
		
		//Lettura parametri formali
		String hpmProcessId = pHpmProcessid;
		
		Repository sesameRepository =
            OCRdfRepository.getInstance().getSesameRepository();
		RepositoryConnection con;
		try {
			con = sesameRepository.getConnection();
		} catch (RepositoryException e) {
			
			e.printStackTrace();
			throw new KPeopleSesameException(e.getMessage());
		}
        String query = null;
        StringBuffer sb = new StringBuffer();
        sb.append("PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>");
        sb.append("PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>");
        sb.append("PREFIX owl:<http://www.w3.org/2002/07/owl#>");
        sb.append("PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>");
        sb.append("PREFIX protons:<http://proton.semanticweb.org/2005/04/protons#>");
        sb.append("PREFIX protont:<http://proton.semanticweb.org/2005/04/protont#>");
        sb.append("PREFIX kp:<http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#>");
        sb.append(" SELECT ?Event WHERE ");
        sb.append(" { <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#"); 
        sb.append(hpmProcessId);		
        sb.append("> <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#processEvent> ");
        sb.append(" ?Event . ");
        sb.append("?Event rdf:type <http://kpeople.webscience.it/ontologies/2010/12/kpbase.owl#Pattern>");
        sb.append("}");
        
        query = sb.toString();
        
        TupleQuery tupleQuery;
        List<String> hpmPattersIdList = new ArrayList<String>();
		try {
			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, query);
			 TupleQueryResult result = tupleQuery.evaluate();
		     hpmPattersIdList = addToHpmPatternIdList(result);
		     result.close();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleSesameException(e.getMessage());
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleSesameException(e.getMessage());
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleSesameException(e.getMessage());
		}

       
        try {
			con.close();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleSesameException(e.getMessage());
		}
		
		
		return hpmPattersIdList;
	}
	
	/**
     * @param result resultset Sesame
     * @return lista degli indici degli eventi
     * @throws QueryEvaluationException eccezione durante l'elaborazione.
     */
    private List<String> addToHpmPatternIdList(
            final TupleQueryResult result) throws QueryEvaluationException {
        List<String> listHpmPatternIds = new ArrayList<String>();
        while (result.hasNext()) {
            BindingSet bindingSet = result.next();

            String hpmPatternId =
                bindingSet.getValue("Event").toString().split("#")[1];

            listHpmPatternIds.add(hpmPatternId);
            }
        return listHpmPatternIds;
        }
}
