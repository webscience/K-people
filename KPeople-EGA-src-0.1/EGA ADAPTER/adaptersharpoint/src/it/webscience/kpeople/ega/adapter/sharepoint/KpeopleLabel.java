package it.webscience.kpeople.ega.adapter.sharepoint;

/**
 * @author XPMUser
 */
public class KpeopleLabel {

    /**
     * @return L'ID associato al sistema verticale.
     */
    public static String getSystemId() {
        return "sharepoint.systemId";
    }

    /**
     * @return La label "start".
     */
    public static String getLogStart() {
        return " : START";
    }

    /**
     * @return La label "stop".
     */
    public static String getLogStop() {
        return " : STOP";
    }

    /**
     * @return il numero associato allo stato toProcess.
     */
    public static Long toProcess() {
        return (long) 1;
    }

    /**
     * @return il numero associato allo stato readyToProcess.
     */
    public static Long readyToProcess() {
        return (long) 2;
    }

    /**
     * @return il numero associato allo stato stateFetched.
     */
    public static Long stateFetched() {
        return (long) 3;
    }

    /**
     * @return il numero associato allo stato statePacked.
     */
    public static Long statePacked() {
        return (long) 4;
    }

    /**
     * @return il numero associato allo stato stateNotDelivered.
     */
    public static Long stateNotDelivered() {
        return (long) 7;
    }

    /**
     * @return il riferimento al parametro indicante la dimensione del pool di
     *         thread.
     */
    public static String getThreadPoolSize() {
        return "sharepoint.egaadapter.treadPoolSize";
    }

    /**
     * @return il riferimento al parametro indicante l'intervallo di polling.
     */
    public static String getPollingTime() {
        return "sharepoint.egaadapter.pollingTime";
    }

    /**
     * @return il riferimento al parametro indicante il percorso sul filesystem
     *         dove salvare temporaneamente i file xml relativi agli eventi.
     */
    public static String getEventXMLPath() {
        return "eventXMLFilesPath";
    }

    public static String getCorePropertiesAuthor() {
        return "sharepoint.egaadapter.coreproperties.author";
    }

    public static String getCorePropertiesCreationDate() {
        return "sharepoint.egaadapter.coreproperties.creationDate";
    }

    public static String getCorePropertiesKeywords() {
        return "sharepoint.egaadapter.coreproperties.keywords";
    }
    
    public static String getUserName() {
        return "sharepoint.server.username";
    }
    
    public static String getPassword() {
        return "sharepoint.server.password";
    }
    
    public static String getDomain() {
        return "sharepoint.server.domain";
    }
    
    public static String getHost() {
        return "sharepoint.host";
    }

    
}
