package it.webscience.kpeople.util;

import it.webscience.kpeople.Singleton;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * Classi di utils per l'interfacciamento con gli stub.
 */
public class StubUtil {

    /** logger. */
    private static Log logger =
        LogFactoryUtil.getLog(StubUtil.class);

    /**
     * costruisce il target endpoint per la chiamata allo stub.
     * @param endpoint end point da chiamare
     * @return target end point
     */
    public final String buildTarget(final String endpoint) {
        Singleton singleton = Singleton.getInstance();

        String url = singleton.getProperty(PropsKeys.WSO2SERVER_URL);

        String port = "";
        if (Validator.isNotNull(
                singleton.getProperty(PropsKeys.WSO2SERVER_PORT))) {
            port = ":" + singleton.getProperty(PropsKeys.WSO2SERVER_PORT);
        }

        String endPointProp = singleton.getProperty(endpoint);

        String target = url + port + endPointProp;

        logger.debug("Target per " + endpoint + ": " + target);

        return target;
    }
}
