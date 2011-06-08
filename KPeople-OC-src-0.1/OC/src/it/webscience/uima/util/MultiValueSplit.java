package it.webscience.uima.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import it.webscience.uima.ocControl.SesamePropertyKeys;
import it.webscience.uima.ocSesameAdapter.sesameAdapterConfigurationManager.SesameAdapterConfigurationManager;

/**
 * Classe di utility per l'analisi delle properties associate ad un evento.
 * @author filieri
 */
public class MultiValueSplit {

    /** logger. */
    private Logger logger;

    /**
     * istanza della classe singleton SesameAdapterConfigurationManager per la
     * lettura delle properties.
     */
    private static SesameAdapterConfigurationManager configInstance;

    /**
     * Metodo costruttore.
     */
    public MultiValueSplit() {
        logger = Logger.getLogger(this.getClass().getName());
        configInstance = SesameAdapterConfigurationManager.getInstance();
    }

    /**
     * @param value
     *            - valore della property che deve essere analizzata.
     * @return un array di string contenente i valori delle properties associate
     *         all'evento.
     */
    public final String[] getPropertyValues(final String value) {

        String[] multiValue = null;

        String multi =
                configInstance
                        .getProperty(SesamePropertyKeys.PROPERTY_MULTIVALUE);
        if (value.contains(multi)) {
            int begin = value.indexOf("[");
            int end = value.indexOf("]");
            String separator = value.substring(begin + 1, end);

            String[] valueSplitted = value.split(":");
            multiValue = valueSplitted[1].split(separator);
        } else {
            multiValue = new String[1];
            multiValue[0] = value;
        }
        return multiValue;

    }

}
