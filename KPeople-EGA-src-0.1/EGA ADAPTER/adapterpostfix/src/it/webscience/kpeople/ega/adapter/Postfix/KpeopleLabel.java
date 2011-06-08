package it.webscience.kpeople.ega.adapter.Postfix;

public class KpeopleLabel {

    public static String getSystemId() {
        return "postfix.systemId";
    }

    public static String getLogStart() {
        return " : START";
    }

    public static String getLogStop() {
        return " : STOP";
    }

    public static Long toProcess() {
        return (long) 1;
    }

    public static Long readyToProcess() {
        return (long) 2;
    }

    public static Long stateFetched() {
        return (long) 3;
    }

    public static Long statePacked() {
        return (long) 4;
    }

    public static Long stateNotDelivered() {
        return (long) 7;
    }

    public static String getThreadPoolSize() {
        return "treadPoolSize";
    }

    public static String getPollingTime() {
        return "pollingTime";
    }

    public static String getEventTmpPath() {
        return "eventTmpPath";
    }

    public static String getEventXMLPath() {
        return "eventXMLFilesPath";
    }

    public static String getSamba() {
        return "smb";
    }
    
    public static String getXPath() {
    return "//MetaDataSet/CompoundMetadata/Metadata";
    }
    
    public static String getSeparatore1() {
    return "#&";
    }
    
    public static String getSeparatore2() {
    return "$£";
    }
    

}
