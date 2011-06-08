package it.webscience.uima.ocControl.ocStorageUtility;

import it.webscience.uima.Singleton;
import it.webscience.uima.ocControl.SolrPropertyKeys;
import it.webscience.uima.ocControl.ocSolrServerUtility.OCSolrServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author filieri
 */
public class OcAlfrescoServerUtility {

    /**
     * oggetto di tipo properties contenente i parametri di configurazione di
     * solr.
     */
    private static Properties solrProperties = OCSolrServer.getInstance()
            .getApplicationProps();

    private static Singleton conf = Singleton.getInstance();

    
    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    
    
    
    public OcAlfrescoServerUtility() {

    }

    /**
     * @param url
     *            - url pubblico del file nello storage Alfresco.
     * @return File - il file memorizzato nello storage Alfresco.
     */
    public  File getEventAttacchment(final String url) {

        String token = getToken();
        String urlwithtoken = url + "?ticket=" + token;

        File file = new File(url);
        String filename = file.getName();

        File result = null;
        String path = createDir();
        if (path != null) {
            result = new File(path + "//" + file.getName());
        }

        // File result = new File("./solrtmp/" + file.getName());
        BufferedInputStream in;
        try {
            in = new BufferedInputStream(new URL(urlwithtoken).openStream());

            FileOutputStream fos = new FileOutputStream(result);

            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);

            byte[] data = new byte[1024];

            int x = 0;

            while ((x = in.read(data, 0, 1024)) >= 0) {
                bout.write(data, 0, x);
            }
            bout.close();
            in.close();
            fos.close();
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        // TODO inserire logiche di autenticazione o configurazione
        return result;

    }

    private String getToken() {
        // TODO Auto-generated method stub
        URL alfresco = null;
        String inputLine = null;
        String token = null;
        try {
            String alfrescoUrl = conf.getProperty("alfresco-public-url");
            String alfrescoUser = conf.getProperty("alfresco-username");
            String alfrescoPassword = conf.getProperty("alfresco-password");
            String alfrescoLogin = conf.getProperty("alfresco-login");

            alfresco =
                    new URL(alfrescoUrl + alfrescoLogin + "?u=" + alfrescoUser
                            + "&pw=" + alfrescoPassword);
            BufferedReader in = null;
            in =
                    new BufferedReader(new InputStreamReader(
                            alfresco.openStream()));

            while ((inputLine = in.readLine()) != null) {
                token = in.readLine();
            }
            token = token.replace("<ticket>", "");
            token = token.replace("</ticket>", "");
            token = token.trim();
            in.close();
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());

        } catch (IOException e) {
            logger.error(e.getMessage());

        }
        return token;

    }

    private static String createDir() {
        String path =
                solrProperties.getProperty(SolrPropertyKeys.SOLR_TMP_DIR)
                        + Thread.currentThread().getId();

        File dir = new File(path);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
        }
        return path;
    }

    public static String deleteDir(final long idtrhread) {
        String path =
                solrProperties.getProperty(SolrPropertyKeys.SOLR_TMP_DIR)
                        + idtrhread;
        File dir = new File(path);
        boolean deleted = deleteAllFile(dir);

        dir.delete();

        return path;
    }

    private static boolean deleteAllFile(final File dir) {
        boolean deleted = false;
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteAllFile(files[i]);
                } else {
                    deleted = files[i].delete();
                }
            }

        }
        return deleted;
    }

}
