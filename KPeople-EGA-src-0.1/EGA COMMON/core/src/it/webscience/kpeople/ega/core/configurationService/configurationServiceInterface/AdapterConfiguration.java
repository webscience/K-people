package it.webscience.kpeople.ega.core
.configurationService.configurationServiceInterface;

import java.util.HashMap;
import java.util.Properties;

/**
 * @author XPMUser
 *
 */
public interface AdapterConfiguration {

    /**
     * @return tutte le proprietà contenute nel file di properties.
     * @throws Exception eccezione.
     */
    Properties getAllConfigurationItem() throws Exception;

    /**
     * @return tutte le propietà associate ad un componente.
     * @throws Exception eccezione.
     */
    HashMap<Object, Object> getAllConfigurationItemByComponentName()
            throws Exception;

    /**
     * @param key chiave della proprietà.
     * @return il valore della proprietà.
     */
    String getConfigurationInfoBykey(String key);

}
