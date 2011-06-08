package it.webscience.kpeople.dal.util;

/**
 * Costanti utilizzate nel package dal.
 */
public final class DataAccessConstants {

    /** Default constructor. */
    private DataAccessConstants() {
    }

    /** Numero massimo di risultati di SOLR. */
    public static final int MAX_SOLR_RESULTS = 1000;
    /** Score minimo per risultati validi. */
    public static final double MIN_SOLR_SCORE = 0.0;

    /** field di SOLR dell'id dei processi. */
    public static final String SOLR_PROCESS_ID = "process.id";
    /** field di SOLR dell'id degli eventi. */
    public static final String SOLR_EVENT_ID = "event.id";
    /** field di SOLR del body degli eventi. */
    public static final String SOLR_EVENT_BODY = "event.event-data.body";
    /** field di SOLR dell'id dei documenti. */
    public static final String SOLR_DOCUMENT_ID = "id";
    /** field di SOLR dell'id dei pattrn. */
    public static final String SOLR_PATTERN_ID = "pattern.id";
    /** field di SOLR dell'id delle attività. */
    public static final String SOLR_ACTIVITY_ID = "activity.id";
    /** field di SOLR dell'id degli utenti. */
    public static final String SOLR_USER_ID = "user.id";

    /** field di SOLR contenente l'id del processo associato ad un evento. */
    public static final String SOLR_FIELD_PROPERTY_KPEOPLETAG =
            "property_kpeopletag";
    /** field di SOLR contenente l'id del processo associato ad un evento. */
    public static final String SOLR_FIELD_PATTERN_PROCESS_HPMID =
            "pattern.process.hpmid";

    /** entità SOLR di tipo processo. */
    public static final String SOLR_TYPE_PROCESS = "process";
    /** entità SOLR di tipo documento. */
    public static final String SOLR_TYPE_DOCUMENT = "document";
    /** entità SOLR di tipo evento. */
    public static final String SOLR_TYPE_EVENT = "event";
    /** entità SOLR di tipo evento di comunicazione. */
    public static final String SOLR_TYPE_COMM_EVENT = "comm_event";
    /** entità SOLR di tipo evento di tipo download. */
    public static final String SOLR_TYPE_DOWN_EVENT = "down_event";
    /** entità SOLR di tipo evento legato ad un documento Sharepoint. */
    public static final String SOLR_TYPE_DOC_EVENT = "doc_event";
    /** entità SOLR di tipo pattern. */
    public static final String SOLR_TYPE_PATTERN = "pattern";
    /** entità SOLR di tipo attività. */
    public static final String SOLR_TYPE_ACTIVITY = "activity";
    /** entità SOLR di tipo utente. */
    public static final String SOLR_TYPE_USER = "user";
    /** entità SOLR di tipo diverso. */
    public static final String SOLR_TYPE_OTHER = "other";

    /** metadato contenente l'autore della mail. */
    public static final String METADATA_EMAIL_AUTHOR = "AUTHOR";
    /** metadato contenente l'oggetto della mail. */
    public static final String METADATA_TITLE = "TITLE";
    /** metadato contenente la tipologia di evento. */
    public static final String METADATA_ACTION_TYPE = "action-type";
    /** metadato contenente la tipologia di evento. */
    public static final String DOCUMENT_DOWNLOAD_ACTION_TYPE =
            "DOCUMENT.DOWNLOAD";

}
