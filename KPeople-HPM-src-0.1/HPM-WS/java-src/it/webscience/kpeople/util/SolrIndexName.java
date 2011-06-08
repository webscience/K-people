package it.webscience.kpeople.util;

/**
 * Classe contenente il nome degli indici definiti all'interno dello schema di
 * indicizzazione di solr.
 * @author filieri
 */
/**
 * @author bolognese
 */
public class SolrIndexName {

    // Indici per il processo
    /**
     * Indice di solr per identificare in maniera univoca il processo
     * indicizzato.
     */
    public static final String SOLR_ID = "id";

    /**
     * Indice di solr per l'id del processo.
     */
    public static final String SOLR_PROCESS_ID = "process.id";

    /**
     * Indice di solr per il nome del processo.
     */
    public static final String SOLR_PROCESS_NAME = "process.name";

    /**
     * Indice di solr per la descrizione del processo.
     */
    public static final String SOLR_PROCESS_DESCRIPTION = "process.description";

    /**
     * Indice di solr per indicare l'owner del processo.
     */
    public static final String SOLR_PROCESS_OWNER = "process.owner";

    /**
     * Indice di solr per indicare il processtype del processo.
     */
    public static final String SOLR_PROCESS_PROCESS_TYPE =
            "process.process-type";

    // Indici per l'utente
    
    /**
     * Indice di solr per indicare lo user id.
     */
    public static final String SOLR_USER_ID = "user.id";

    /**
     * Indice di solr per indicare il firstname dello user.
     */
    public static final String SOLR_USER_FIRSTNAME = "user.firstname";

    /**
     * Indice di solr per indicare il lastname dello user.
     */
    public static final String SOLR_USER_LASTNAME = "user.lastname";

    /**
     * Indice di solr per indicare il account dello user.
     */
    public static final String SOLR_USER_ACCOUNT = "user.account";

    /**
     * Indice di solr per indicare il mail dello user.
     */
    public static final String SOLR_USER_MAIL = "user.mail";

    // Indici per i pattern.

    /**
     * Indice di solr per indicare la tipologia di pattern.
     */
    public static final String SOLR_PATTERN_ID = "pattern.id";

    /**
     * Indice di solr per indicare la tipologia di pattern.
     */
    public static final String SOLR_PATTERN_TYPE = "pattern.type";

    /**
     * Indice di solr per indicare il nome del pattern.
     */
    public static final String SOLR_PATTERN_NAME = "pattern.name";

    /**
     * Indice di solr per indicare la descrizione del pattern.
     */
    public static final String SOLR_PATTERN_DESCRIPTION = "pattern.description";

    /**
     * Indice di solr per indicare il processo associato al pattern.
     */
    public static final String SOLR_PATTERN_PROCESS_HPMID =
            "pattern.process.hpmid";

    /**
     * Indice di solr per indicare l'utente requestor del pattern.
     */
    public static final String SOLR_PATTERN_REQUESTOR = "pattern.requestor";

    /**
     * Indice di solr per indicare l'utente provider del pattern.
     */
    public static final String SOLR_PATTERN_PROVIDER = "pattern.provider";

}
