package it.webscience.kpeople.ega.core
.configurationService.configurationServiceImpl;

import java.util.HashMap;
import java.util.Properties;

import it.webscience.kpeople.ega.core.configurationService.ConfigurationManager;
import it.webscience.kpeople.ega.core.configurationService
.configurationServiceInterface.AdapterConfiguration;

/**
 * @author XPMUser
 */
public class AdapterConfigurationImpl implements AdapterConfiguration {

    /**
     * @return Ritorna tutte le proprietà di configurazione.
     */
    public final Properties getAllConfigurationItem() {
        Properties configurationProperties = ConfigurationManager
                .getProperties();
        return configurationProperties;
    }

    /**
     * @return null.
     * @throws Exception
     *             eccezione.
     */
    public final HashMap<Object, Object>
    getAllConfigurationItemByComponentName()
            throws Exception {
        return null;
    }

    /**
     * @param key chiave della proprietà
     * @return null.
     *             eccezione.
     */
    public final String getConfigurationInfoBykey(final String key) {
        return null;
    }

}
