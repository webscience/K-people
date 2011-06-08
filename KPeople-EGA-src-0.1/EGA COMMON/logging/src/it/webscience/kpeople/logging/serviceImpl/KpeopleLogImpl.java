package it.webscience.kpeople.logging.serviceImpl;


import org.apache.log4j.Logger;

import org.apache.log4j.xml.DOMConfigurator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

public class KpeopleLogImpl implements KpeopleLog {

    public static Logger logger_xml;

    public KpeopleLogImpl() {

        BundleContext context = FrameworkUtil.getBundle(KpeopleLogImpl.class)
                .getBundleContext();

        DOMConfigurator.configure(context.getBundle().getResource(
                "/resources/kpeopleLog.xml"));
        logger_xml = Logger.getLogger("it.webscience.kpeople");
        
        
        /*
         * logger_xml.info("Info starting");
         * logger_xml.warn("Warning starting");
         * logger_xml.error("Error starting");
         */

    }

    public int logInfo(final String name) {

        return 0;
    }

    public int logInfo(final String component, final String name) {

        logger_xml.info(component + name);
        return 0;
    }

    public int logWarn(final String name) {

        return 0;
    }

    public int logWarn(final String component, final String name) {
        logger_xml.warn(component + name);
        return 0;
    }

    public int logError(final String name) {

        return 0;
    }

    public int logError(final String component, final String name) {
        logger_xml.error(component + name);
        return 0;
    }

    public int logFatal(final String name) {
        return 0;
    }

    public int logFatal(final String component, final String name) {
        logger_xml.fatal(component + name);
        return 0;
    }

    public int logDebug(final String name) {
        return 0;
    }

    public int logDebug(final String component, final String name) {
        logger_xml.debug(component + name);
        return 0;
    }

}
