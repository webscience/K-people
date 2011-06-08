package it.webscience.kpeople.ega.core;

public class KpeopleLabel {

    public static String getSystemId() {
        return "KpeopleCore component";
    }

    public static String getCoreConfigurationFileName() {
        return "/resources/core.properties";
    }

    public static String getAdapterConfigurationFileName() {
        return "adapter.properties";
    }

    public static String getBundleNum() {
        return "bundlesNum";
    }

    public static String getLogStart() {
        return " : START";
    }

    public static String getLogStop() {
        return " : STOP";
    }

    public static String getStart() {
        return "start";
    }

    public static String getStop() {
        return "stop";
    }

    public static String getThreadPoolSizeLabel() {
        return "treadPoolSize";
    }

    public static String getPollingTimeLabel() {
        return "pollingTime";
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

    public static Long readyToSent() {
        return (long) 5;
    }

    public static String getBundleDomain() {
        return "it.webscience.kpeople.domain";
    }
}
