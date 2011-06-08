package it.webscience.uima.ocAlfrescoStorage;

import it.webscience.uima.Singleton;
import it.webscience.uima.ocStorageAbstract.OcStorageRequest;
import it.webscience.uima.ocStorageAbstract.OcStorageResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;

/** Classe per l'interazione con Alfresco.
 * Gestisce il salvataggio dell'evento e degli attachments
 * @author dellanna
 */
public class OcAlfrescoStorageUtility {

    /** parametri di configurazione di Alfresco. */
    private OcAlfrescoStorageConfiguration configuration;

    /** Salva le informazioni contenute nell'xml.
     * @param sr oggetto da inviare ad Alfresco
     * @return xml di uscita
     * @throws IOException problemi di connessione con Alfresco
     * @throws ClassNotFoundException classe non trovata
     */
    public final OcStorageResponse store(final OcStorageRequest sr)
            throws IOException, ClassNotFoundException {
        loadConfiguration();

        OcStorageResponse ocStorageResponse = callWebScript(sr);

        return ocStorageResponse;
    }

    /** chiama il webscript per il salvataggio dell'evento.
     * @param sr oggetto da passare al web script
     * @return OcStorageResponse ritornato da Alfresco
     * @throws IOException problemi di connessione con Alfresco
     * @throws ClassNotFoundException classe non trovata
     */
    private OcStorageResponse callWebScript(final OcStorageRequest sr)
            throws IOException, ClassNotFoundException {

        String alfrescoUrl = Singleton.getInstance().
            getProperty("alfresco-url");
        String webScriptUrl = Singleton.getInstance().
            getProperty("webScript-url");

//      genera la URLConnection verso Alfresco
        URL url = new URL(alfrescoUrl + webScriptUrl);
        URLConnection conn = url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setDefaultUseCaches(false);
        conn.setRequestProperty("Content-Type", "application/octet-stream");

//      invia all'output stream gli oggetti
        ObjectOutputStream outStream = new ObjectOutputStream(
                conn.getOutputStream());
        outStream.writeObject(sr);
        outStream.writeObject(configuration);
        outStream.flush();
        outStream.close();

//      recupera la response
        OcStorageResponse response = null;
        ObjectInputStream in = new ObjectInputStream(
                conn.getInputStream());
        response = (OcStorageResponse) in.readObject();

        outStream.close();

        return response;
    }

    /** carica i parametri di configurazione di Alfresco. */
    private void loadConfiguration() {
//        recupera le proprietà dal file di properties
        Singleton conf = Singleton.getInstance();
        String duplicateDocs = conf.getProperty("allow_duplicate-docs");
        String adminUsername = conf.getProperty("alfresco-username");
        String adminPassword = conf.getProperty("alfresco-password");

//        inizializza l'oggetto OcAlfrescoStorageConfiguration
        configuration = new OcAlfrescoStorageConfiguration();
        configuration.setHasDuplicateDocs(Boolean.parseBoolean(duplicateDocs));
        configuration.setAdminUsername(adminUsername);
        configuration.setAdminPassword(adminPassword);
    }
}
