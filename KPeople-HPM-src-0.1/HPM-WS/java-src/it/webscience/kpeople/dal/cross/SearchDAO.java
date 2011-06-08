/**
 * 
 */
package it.webscience.kpeople.dal.cross;

import it.webscience.kpeople.bll.impl.solr.OCSolrServer;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.util.DataAccessConstants;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

/**
 * Classe di accesso ai dati per effettuare le ricerche full text.
 */
public class SearchDAO implements ISearchDAO {

    /** sepratore dei valori dei campi di SOLR. */
    private static final String SOLR_ID_TYPE_SEPARATOR = "#";
    /** indice del campo id. */
    private static final int ID_COL = 0;
    /** indice del campo tipologia. */
    private static final int TYPE_COL = 1;
    /** indice del campo hpmProcessId. */
    private static final int PROC_ID_COL = 2;

    /** logger. */
    private static Logger logger  = Logger.getLogger(SearchDAO.class);

    @Override
    public final List<Object> find(final String freeText)
        throws KPeopleDAOException {

        List<Object> results = null;
        try {
            List<String> ids = getSolrIds(freeText);

            results = getHpmObjects(ids);
        } catch (SolrServerException e) {
            logger.error(e.getMessage());
            throw new KPeopleDAOException(e.getMessage());
        }
        return results;
    }

    /**
     * Carica le business entity tramite gli id trovati da SOLR.
     * @param result lista di id di SOLR
     * @return lista di business entity
     * @throws KPeopleDAOException eccezione nel dao
     */
    private List<Object> getHpmObjects(final List<String> result)
        throws KPeopleDAOException {

        HpmObjectFactory factory = new HpmObjectFactory();

        List<Object> out = new ArrayList<Object>();

        for (String key : result) {
            String [] tokens = key.split(SOLR_ID_TYPE_SEPARATOR);

            try {
                if (logger.isDebugEnabled()) {
                    logger.debug("carico oggetto con id: " + tokens[ID_COL]);
                }

                String id = "";
                String type = "";
                String processId = "";

                if (tokens.length > ID_COL) {
                    id = tokens[ID_COL];
                }

                if (tokens.length > TYPE_COL) {
                    type = tokens[TYPE_COL];
                }

                if (tokens.length > PROC_ID_COL) {
                    processId = tokens[PROC_ID_COL];
                }

                Object hpmObject = factory.create(id, type, processId);

                if (hpmObject != null) {
                    out.add(hpmObject);
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return out;
    }

    /**
     * Esegue l'interrogazione di SOLR.
     * @param freeText testo da cercare
     * @return lista di id di oggetti SOLR
     * @throws SolrServerException eccezione nell'interrogazione
     */
    private List<String> getSolrIds(final String freeText)
            throws SolrServerException {

        if (logger.isDebugEnabled()) {
            logger.debug("start getSolrIds");
        }

        List<String> out = new ArrayList<String>();

        SolrServer solrServer = OCSolrServer.getInstance().getSolrServer();
        SolrQuery solrQuery = new SolrQuery();

        solrQuery.setRows(DataAccessConstants.MAX_SOLR_RESULTS);
        //solrQuery.setShowDebugInfo(true);
        solrQuery.setFields("*", "score");

        /*
         * esegue lo split della stringa libera. viene costruita la query:
         * process.name:(+*str1* +*str2" +*str3*) OR
         * process.description:(+*str1* +*str2" +*str3*)
         */

        String[] tokens = freeText.split(" ");
        String queryFilter = "";
        for (String token : tokens) {
            queryFilter += "+*" + token.toLowerCase() + "* ";
        }

        String query = "text:(" + queryFilter + ") OR " + freeText;

        if (logger.isDebugEnabled()) {
            logger.debug("Solr query: " + query);
        }

        solrQuery.setQuery(query);

        QueryResponse rsp = solrServer.query(solrQuery);
        Iterator<SolrDocument> it = rsp.getResults().iterator();
        while (it.hasNext()) {
            SolrDocument solrDocument = (SolrDocument) it.next();

            if (logger.isDebugEnabled()) {
                logger.debug("score: " + solrDocument.getFieldValue("score"));
            }

            if (isValidScore(solrDocument)) {
                checkTypeAndAdd(out, solrDocument);
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("end getSolrIds");
        }

        return out;
    }

    /**
     * Controlla il tipo di be e la aggiunge alla lista.
     * @param out lista degli oggetti be
     * @param solrDocument oggetto da aggiungere
     */
    private void checkTypeAndAdd(final List<String> out,
            final SolrDocument solrDocument) {
        Object id = solrDocument.getFieldValue("id");

        if (id != null) {
            String tipologia = getTipologia(solrDocument);
            String hpmProcessId = getHpmProcessId(tipologia, solrDocument);

            if (logger.isDebugEnabled()) {
                logger.debug("Solr: tipologia " + tipologia);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Solr: id processo " + hpmProcessId);
            }

            if (!tipologia.equalsIgnoreCase(
                    DataAccessConstants.SOLR_TYPE_OTHER)) {
                out.add(id.toString() + SOLR_ID_TYPE_SEPARATOR + tipologia
                        + SOLR_ID_TYPE_SEPARATOR + hpmProcessId);
            }
        }
    }

    /**
     * Legge l'id del processo associato.
     * @param tipologia tipologia di oggetto
     * @param solrDocument documento SOLR
     * @return id del processo
     */
    private String getHpmProcessId(final String tipologia,
            final SolrDocument solrDocument) {
        if (DataAccessConstants.SOLR_TYPE_COMM_EVENT.equals(tipologia)
                || DataAccessConstants.SOLR_TYPE_DOC_EVENT.equals(tipologia)
                || DataAccessConstants.SOLR_TYPE_EVENT.equals(tipologia)) {
            Object id = solrDocument.getFieldValue(
                    DataAccessConstants.SOLR_FIELD_PROPERTY_KPEOPLETAG);
            if (id != null) {
                return id.toString();
            }
        } else if (DataAccessConstants.SOLR_TYPE_PATTERN.equals(tipologia)) {
            Object id = solrDocument.getFieldValue(
                    DataAccessConstants.SOLR_FIELD_PATTERN_PROCESS_HPMID);
            if (id != null) {
                return id.toString();
            }
        }

        return "";
    }

    /**
     * Verifica lo score del documento SOLR.
     * @param solrDocument documento SOLR
     * @return true se supera uno score stabilito, altrimenti false
     */
    private boolean isValidScore(final SolrDocument solrDocument) {
        boolean result = true;

        if (solrDocument.getFieldValue("score") == null) {
            // se non è definito lo score vine ritenuto valido
            return result;
        }

        String scoreStr = solrDocument.getFieldValue("score").toString();
        double score = Double.valueOf(scoreStr);

        if (score < DataAccessConstants.MIN_SOLR_SCORE) {
            result = false;
        }

        return result;
    }

    /**
     * Ritorna la tipologia di documento.
     * @param solrDocument documento SOLR
     * @return tipologia di be
     */
    private String getTipologia(final SolrDocument solrDocument) {
        String result = "";

        result = checkDocumentType(solrDocument,
                DataAccessConstants.SOLR_PROCESS_ID,
                DataAccessConstants.SOLR_TYPE_PROCESS);
        if (!"".equals(result)) {
            return result;
        }

        result = checkDocumentType(solrDocument,
                DataAccessConstants.SOLR_EVENT_ID,
                DataAccessConstants.SOLR_TYPE_EVENT);
        if (!"".equals(result)) {
            return result;
            // TODO VERIFICARE SE NECESSARIO DISTINGUERA A QUESTO LIVELLO
            // TRA EMAIL O DOCUMENTO

//            result = checkDocumentType(solrDocument,
//                    DataAccessConstants.SOLR_EVENT_BODY,
//                    DataAccessConstants.SOLR_TYPE_EVENT);
//
//            if (!"".equals(result)) {
//                return result;
//            }
//
//            // Se non c'è il body allora è un documento
//            return DataAccessConstants.SOLR_TYPE_DOCUMENT;
        }

        result = checkDocumentType(solrDocument,
                DataAccessConstants.SOLR_PATTERN_ID,
                DataAccessConstants.SOLR_TYPE_PATTERN);
        if (!"".equals(result)) {
            return result;
        }

        result = checkDocumentType(solrDocument,
                DataAccessConstants.SOLR_ACTIVITY_ID,
                DataAccessConstants.SOLR_TYPE_ACTIVITY);
        if (!"".equals(result)) {
            return result;
        }

        result = checkDocumentType(solrDocument,
                DataAccessConstants.SOLR_USER_ID,
                DataAccessConstants.SOLR_TYPE_USER);
        if (!"".equals(result)) {
            return result;
        }

        result = checkDocumentType(solrDocument,
                DataAccessConstants.SOLR_DOCUMENT_ID,
                DataAccessConstants.SOLR_TYPE_DOCUMENT);
        if (!"".equals(result)) {
            return result;
        }

        return DataAccessConstants.SOLR_TYPE_OTHER;
    }

    /**
     * Verifica la chiave per determinare il tipo di documento.
     * @param solrDocument documento SOLR
     * @param key chiave di scelta
     * @param type tipologia del documento associata alla chiave
     * @return tipologia del documento in ingresso
     */
    private String checkDocumentType(final SolrDocument solrDocument,
            final String key, final String type) {
        String result = "";
        Object id = solrDocument.getFieldValue(key);
        if (id != null) {
            result = type;
        }
        return result;
    }
}
