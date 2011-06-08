package it.webscience.kpeople.domain.util;

import it.webscience.kpeople.domain.KpeopleLabel;
import it.webscience.kpeople.domain.KpeopleLogger;

import java.net.URL;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class HibernateUtil {
    private static  SessionFactory sessionFactory = null;
    private static void initialize()  {
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(KpeopleLabel.getSystemId(),
                        HibernateUtil.class + KpeopleLabel.getLogStart());
        try {
            // Create the SessionFactory from hibernate.cfg.xml

            BundleContext cont = FrameworkUtil.getBundle(HibernateUtil.class)
                    .getBundleContext();
            URL url = cont.getBundle().getResource("hibernate.cfg.xml");
            sessionFactory = new Configuration().configure(url)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            KpeopleLogger
                    .getInstance()
                    .getService()
                    .logError(
                            KpeopleLabel.getSystemId(),
                            HibernateUtil.class
                                    + "Initial SessionFactory creation failed."
                                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
        KpeopleLogger
                .getInstance()
                .getService()
                .logInfo(KpeopleLabel.getSystemId(),
                        HibernateUtil.class + KpeopleLabel.getLogStop());
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            initialize();
        }
        return sessionFactory;
    }
}
