package it.webscience.kpeople.ega.core
.configurationService.remoteControlService;

import it.webscience.kpeople.ega.core.configurationService.ConfigurationManager;

import java.util.Properties;

import org.apache.axis2.context.MessageContext;

/**
 * @author XPMUser
 */
public class AdapterConfigurationWS {

    /**
     * @return null.
     */
    public final Properties getAllConfigurationItem() {
        MessageContext.getCurrentMessageContext().getConfigurationContext()
                .setProperty("name", "bundleservice");

        return null;
    }

    /**
     * @return null.
     */
    public final Properties getAllConfigurationItemByComponentName() {

        MessageContext.getCurrentMessageContext().getConfigurationContext()
                .setProperty("name", "bundleservice");
        return null;
    }

    /**
     * @return null.
     */
    public final Properties getConfigurationInfoBykey() {

        MessageContext.getCurrentMessageContext().getConfigurationContext()
                .setProperty("name", "bundleservice");
        return null;
    }

    /**
     * @param key
     *            nome della propietà da settare.
     * @param value
     *            valore della proprietà.
     * @return null.
     */
    public final Properties setConfigurationItem(
            final String key, final String value) {

        MessageContext.getCurrentMessageContext().getConfigurationContext()
                .setProperty("name", "bundleservice");
        if (key != null && value != null) {

            ConfigurationManager.getProperties().put(key, value);
            ConfigurationManager.commitConfiguration();

        }
        return null;
    }
}
