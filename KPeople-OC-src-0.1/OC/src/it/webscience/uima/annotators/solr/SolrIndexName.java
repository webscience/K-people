package it.webscience.uima.annotators.solr;

/**
 * Classe contenente tutte le label associate agli indici definiti all'interno
 * dello schema di indicizzazione di solr.
 * @author filieri
 */
public class SolrIndexName {

    /**
     * Indice di solr per identificare in maniera univoca il documento
     * indicizzato.
     */
    public static final String SOLR_ID = "id";

    /**
     * Indice di solr per indicare l'id dell'evento.
     */
    public static final String SOLR_EVENT_ID = "event.id";

    /**
     * Indice di solr per identificare il tipo di azione.
     */
    public static final String SOLR_ACTION_TYPE = "event.action.action-type";

    /**
     * Indice di solr per il riferimento all'azione.
     */
    public static final String SOLR_ACTION_REF =
        "event.action.action-reference";

    /**
     * Indice di solr per indicare la tipologia di sistema verticale.
     */
    public static final String SOLR_SYSTEM_TYPE =
        "event.action.system.system-type";

    /**
     * Indice di solr relativo all'identificativo del sistema verticale.
     */
    public static final String SOLR_SYSTEM_ID =
        "event.action.system.system-id";

    /**
     * Indice di solr relativo all'indirizzo email dell'autore dell'evento.
     */
    public static final String SOLR_AUTHOR_EMAIL =
        "event.event-data.author.email";

    /**
     * Indice di solr relativo al nome dell'autore dell'evento.
     */
    public static final String SOLR_AUTHOR_NAME =
        "event.event-data.author.name";

    /**
     * Indice di solr relativo all'username dell'autore dell'evento.
     */
    public static final String SOLR_AUTHOR_USERNAME =
        "event.event-data.author.username";

    /**
     * Indice di solr relativo agli identificativi degli allegati associati
     * all'evento.
     */
    public static final String SOLR_ATTACHMENT_ID =
        "event.event-data.attachments.attachment_id";

    /**
     * Indice di solr relativo al tipo di allegato associato all'evento.
     */
    public static final String SOLR_ATTACHMENT_TYPE =
        "event.event-data.attachments.attachment_type";

    /**
     * Indice di solr relativo al tipo di allegato associato all'evento.
     */
    public static final String SOLR_ATTACHMENT_NAME =
        "event.event-data.attachments.attachment_name";

    /**
     * Indice di solr relativo all'url dell'attachment sullo storage Alfresco.
     */
    public static final String SOLR_ATTACHMENT_URL =
        "event.event-data.attachments.attachment_url";

    /**
     * Indice di solr relativo al body associato all'evento.
     */
    public static final String SOLR_EVENT_BODY = "event.event-data.body";

    /**
     * Indice di solr relativo alla tipologia di body associato all'evento.
     */
    public static final String SOLR_EVENT_BODY_TYPE =
        "event.event-data.body.type";

    /**
     * Indice di solr relativo alla data di creazione dell'evento.
     */
    public static final String SOLR_EVENT_CREATION_DATE =
        "event.event-data.creation-date";

    /**
     * Indice di solr relativo al titolo dell'evento.
     */
    public static final String SOLR_EVENT_TITLE = "event.event-data.title";

    /**
     * Indice di solr relativo ai destinatari in copia della mail contenuta
     * nell'evento.
     */
    public static final String SOLR_EVENT_DETAILS_RECEIVERCC =
        "event.event-data.details_receivercc";

    /**
     * Indice di solr relativo ai destinatari della mail contenuta nell'evento.
     */
    public static final String SOLR_EVENT_DETAILS_RECEIVERTO =
        "event.event-data.details_receiverto";

    /**
     * Indice di solr relativo ai destinatari della mail contenuta nell'evento.
     */
    public static final String SOLR_EVENT_DETAILS_RECEIVERBCC =
        "event.event-data.details_receiverbcc";

    /**
     * Indice di solr relativo alle propriet� contenute nell'evento.
     */
    public static final String SOLR_PROPERTY = "property_";

    /**
     * Indice di solr relativo all'url sullo storage Alfresco dell'evento.
     */
    public static final String SOLR_EVENT_STORAGE_URL = "event.storage.url";

}
