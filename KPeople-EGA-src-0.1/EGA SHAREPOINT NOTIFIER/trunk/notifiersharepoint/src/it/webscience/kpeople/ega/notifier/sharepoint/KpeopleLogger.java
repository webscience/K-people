package it.webscience.kpeople.ega.notifier.sharepoint;

import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * Classe che implementa il pattern Singleton per la gestione del servizio di
 * logging.
 * @author filieri
 */
public class KpeopleLogger {

    /**
     * Istanza della classe KpeopleLog.
     */
    private KpeopleLog service;

    /**
     * Costruttore di tipo private per evitare che altre classi possano
     * istanziare la classe.
     */
    private KpeopleLogger() {

        BundleContext context = FrameworkUtil.getBundle(KpeopleLogger.class)
                .getBundleContext();
        // recupera il riferimento al servizio esposto dal bundle logging
        // all'interno del framework OSGI.
        ServiceReference reference = context
                .getServiceReference(KpeopleLog.class.getName());
        service = (KpeopleLog) context.getService(reference);
    }

    /**
     * SingletonHolder è invocata alla prima esecuzione di
     * Singleton.getInstance() o al primo accesso a SingletonHolder.INSTANCE,
     * mai prima.
     */
    private static class SingletonHolder {
        public static final KpeopleLogger INSTANCE = new KpeopleLogger();
    }

    /**
     * @return SingletonHolder.INSTANCE - l'istanza di SingletonHolder.
     */
    public static KpeopleLogger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * @return service - il servizio di logging associato all'istanza.
     */
    public KpeopleLog getService() {
        return service;
    }
}
