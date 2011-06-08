package it.webscience.kpeople.web.portlet.activities.util;

public interface ActivitiesBrowserConstants {

    /** filtro hpmProcessId. */
    public static final String HPM_PROCESS_ID = "hpmProcessId";

    /** filtro per le attività da fare. */
    public static final String HPM_ACTIVITIES_TODO = "ToDo";
    
    /** filtro per le attività in attesa */
    public static final String HPM_ACTIVITIES_PENDING = "Pending";

    /** filtro per le attività in attesa */
    public static final String HPM_ACTIVITIES_STATE = "State";


    /** label per le attivita correnti in stato todo */
    public static final String CURR_ACTIVITIES_TODO = "currActivitiesToDo";
    
    /** label per le attivita correnti in stato pending */
    public static final String CURR_ACTIVITIES_PENDING = "currActivitiesPending";

    /** label per attivita corrente */
    public static final String CURR_ACTIVITY = "currActivity";

    /** label per l'id dell'attivita corrente */
    public static final String CURR_ACTIVITY_HPM_ID = "currActivityHpmId";
    
    /** label per l'id dell'attivita corrente */
    public static final String CURR_ACTIVITY_TASK_ID = "currActivityTaskId";
    
    /** label per la chiave del metadato request type */
    public static final String PATTERN_REQUEST_TYPE = "TipoDiRichiesta";
    
    /** label per indicare che l'attività è stata accettata */
    public static final String ACTIVITY_ACCEPTED = "Accepted";
    

    /** label per indicare che l'attività è stata rifiutata */
    public static final String ACTIVITY_REJECTED = "Rejected";
    
    /** label per indicare che l'attività è stata rifiutata */
    public static final String ACTIVITY_REJECTED_CONFIRM = "RejectedConfirm";
    
    /** label per i metadata dell'attività */
    public static final String CONTRIBUTE_ACCEPTED = "contributeAccepted";

    /** label per i metadata dell'attività */
    public static final String CONTRIBUTE_ACCEPTED_TYPE = "contributeAccepted_type";
    
    /** label per attivita corrente */
    public static final String CURR_PATTERN = "currPattern";

    /** label per attivita corrente */
    public static final int RICHIESTA_CONTRIBUTO_STATE_INVIATA = 1;
    /** label per attivita corrente */
    public static final int RICHIESTA_CONTRIBUTO_STATE_ACCETTATA = 3;
    /** label per attivita corrente */
    public static final int RICHIESTA_CONTRIBUTO_STATE_RIFIUTATA = 2;
    /** label per attivita corrente */
    public static final int RICHIESTA_CONTRIBUTO_STATE_ANNULLATA = 4;
    
    /** label per attivita corrente */
    public static final int RICHIESTA_CONTRIBUTO_STATE_RICEVUTO = 5;
    
    
    /** label per attivita corrente */
    public static final String REDIRECT_URL = "redirectUrl";
    
    /** label per attivita corrente */
    public static final String REDIRECT_FORM = "redirectForm";
    
    /** label per attivita corrente */
    public static final String REDIRECT_FORM_RICHIESTA_CONTRIBUTO_CONTRIBUTE = "richiestacontributo_contribute";
    
    
    /** label per attivita corrente */
    public static final String REDIRECT_FORM_RICHIESTA_CONTRIBUTO_ACCEPT = "richiestacontributo_accept";
    
    /** label per attivita corrente */
    public static final String RICHIESTA_CONTRIBUTO_INVIA_CONTRIBUTO = "inviaContributo";
    
    /** label per attivita corrente */
    public static final String RICHIESTA_CONTRIBUTO_DESCRIZIONE_INVIO = "descrizioneInvio";
    
    /** label per attivita corrente */
    public static final String RICHIESTA_CONTRIBUTO_DESCRIZIONE_RIFIUTO = "descrizioneRifiuto";
}

