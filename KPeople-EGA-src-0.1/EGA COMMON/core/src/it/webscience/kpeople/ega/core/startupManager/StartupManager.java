package it.webscience.kpeople.ega.core.startupManager;

import it.webscience.kpeople.ega.core.KpeopleLabel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * @author XPMUser
 */
public class StartupManager {


    /**
     * Nome del file di configurazione.
     */
    private static final String CONFIGURATION_FILE = KpeopleLabel
            .getCoreConfigurationFileName();
    /**
     * Oggetto della classe Properties.
     */
    private static final Properties CORE_PROPERTIES = new Properties();
    /**
     * Label relativa alla chiave indicante il numero di bundle.
     */
    private static final String BUNDLES_NUM = KpeopleLabel.getBundleNum();

    /**
     * Method to start EGA and Channel components.
     * @param context
     *            rappresenta il contesto di esecuzione del bundle.
     * @throws InterruptedException .
     */

    public final void startupComponents(final BundleContext context)
            throws InterruptedException {
        String bundlename;
        int bundles;
        Bundle[] bundle = null;
        try {
            InputStream fileUrl = null;

            fileUrl = context.getBundle().getResource(CONFIGURATION_FILE)
                    .openStream();

            bundlename = null;
            bundle = context.getBundles();
            CORE_PROPERTIES.load(fileUrl);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        bundles = Integer.parseInt(CORE_PROPERTIES.getProperty(BUNDLES_NUM));

        for (int i = 1; i <= bundles; i++) {
            bundlename = CORE_PROPERTIES.getProperty("bundle" + i);
            boolean isfirst = true;
            for (Bundle curr : bundle) {
                String currentSimbolicName = curr.getSymbolicName();
                if (currentSimbolicName.equals(bundlename)) {

                    System.out
                            .println("Started .... " + curr.getSymbolicName());
                    try {
                        if (bundlename.equals(KpeopleLabel.getBundleDomain())) {
                            curr.start();
                        } else {
                            if (isfirst) {
                                RecoveryManager recovery =
                                    new RecoveryManager();
                                recovery.checkSystem();
                                isfirst = false;
                            }
                            curr.start();
                        }
                    } catch (BundleException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

    }

    /**
     * @return the properties
     */
    public static Properties getProperties() {
        return CORE_PROPERTIES;
    }

}
