package it.webscience.kpeople.ega.channel;

import java.io.File;

/**
 * @author filieri
 */
public class EgaChannelLabel {

    /**
     * @return L'ID associato al sistema verticale.
     */
    public static String getSystemId() {
        return "EgaChannelComponent";
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
     * @return il riferimento al parametro indicante l'intervallo di polling.
     */
    public static String getChannelPollingTime() {
        return "egaChannel.pollingTime";
    }

    /**
     * @return il riferimento al parametro indicante il numero di
     * thread contemporanei che possono essere avviati.
     */
    public static String getNumThreadChannels() {
        return "egaChannel.numThreadChannels";
    }

    /**
     * @return il numero associato allo stato statePacked.
     */
    public static Long statePacked() {
        return (long) 4;
    }
    /**
     * @return il numero associato allo stato readyToSent.
     */
    public static Long readyToSent() {
        // TODO Auto-generated method stub
        return (long) 5;
    }
    /**
     * @return il numero associato allo stato eventSent.
     */
    public static Long eventSent() {
        // TODO Auto-generated method stub
        return (long) 6;
    }
    /**
     * @return il riferimento al parametro indicante il percorso sul filesystem
     *         dove salvare temporaneamente i file xml relativi agli eventi.
     */
    public static String getEventXMLPath() {
        return "eventXMLFilesPath";
    }

    /**
     * @return l'indirizzo del proxy al quale inviare l'evento.
     */
    public static String getProxyAddress() {
       // return "http://192.168.0.27:8280/services/KPeopleEPEChannelProxy/getEvent";
       return "egaChannel.proxy";
    }

    /**
     * @return il namespace del topic.
     */
    public static String getEventTopicNS() {
        //return "http://kpeople.webscience.it/event";
        return "egaChannel.EventTopicNS";
    }

    /**
     * @return l'urn del servizio esposto.
     */
    public static String getEventUrn() {
        return "urn:event";
    }



    /**
     * @return riferimento al repository
     * locale di Axis2 all'interno del WSO2 ESB.
     */
    public static final String getRepositoryAxis2Path() {
        //return "C:\\kpeople-sviluppo\\wso2esb-3.0.1\\samples\\axis2Client\\client_repo";
        return "egaChannel.RepositoryAxis2Path";
    }
}
