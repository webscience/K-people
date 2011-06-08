package it.webscience.kpeople.ega.adapter;

/**
 * Classe per la definizione di label e costanti da utilizzare nei diversi
 * package che compongono il modulo Listner.
 * 
 * @author filieri
 */
public class KpeopleLabel {

    /**
     * @return un oggetto di tipo String indicante il nome del modulo.
     */
    public static String getModuleName() {

        return "Listener component ";
    }

    /**
     * @return un oggetto di tipo String da utilizzare come label all'interno
     *         del file di log per indicare l'inizio di esecuzione di un metodo.
     */
    public static String getLogStart() {

        return " : START";
    }

    /**
     * @return un oggetto di tipo String da utilizzare come label all'interno
     *         del file di log per indicare la fine dell'esecuzione di un
     *         metodo.
     */
    public static String getLogStop() {

        return " : STOP";
    }

    /**
     * @return un oggetto di tipo String da utilizzare come label all'interno
     *         del file di log per indicare l'esito positivo della chiamata al
     *         webservice.
     */
    public static String getReturnMessage() {
        return " : invio riuscito";
    }

    /**
     * @return un oggetto di tipo String da utilizzare come chiave di property
     *         all'interno del configurationContext.
     */
    public static String getConfigurationContextPropertyKey() {
        return "name";
    }

    /**
     * @return un oggetto di tipo String da utilizzare come valore della chiave
     *         di property all'interno del configurationContext.
     */
    public static String getConfigurationContextPropertyValue() {
        return "bundleservice";
    }
}
