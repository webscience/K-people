package it.webscience.kpeople.storage;

import it.webscience.kpeople.Singleton;
import it.webscience.kpeople.util.PropsKeys;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Utility di comunicazione con lo Storage adapter.
 */
public class OcStorageUtility {

    /**
     * @param url url pubblico del file nello storage Alfresco.
     * @return File il file memorizzato nello storage Alfresco.
     * @throws IOException eccezione durante la chiamata allo storage
     */
    public static BufferedInputStream getEventAttacchment(final String url)
            throws IOException {

//      encoding sul nome del file
        int slashIndex = url.lastIndexOf("/");
        String encodedUrl =
            url.substring(0, slashIndex + 1)
            + URLEncoder.encode(
                url.substring(slashIndex + 1), "UTF-8");

        String urlwithtoken = encodedUrl + "?ticket=" + getToken();

        BufferedInputStream in = new BufferedInputStream(
                new URL(urlwithtoken).openStream());

        return in;
    }

    /**
     * Ottiene il token dallo storage adapter.
     * @return token
     * @throws IOException eccezione durante la chiamata allo storage
     */
    private static String getToken() throws IOException {
        String token = null;

        Singleton singleton = Singleton.getInstance();
        String alfrescoIp = singleton.getProperty(
                PropsKeys.ALFRESCO_URL);
        String alfrescoLoginUrl = singleton.getProperty(
                PropsKeys.ALFRESCO_LOGIN_URL);
        String alfrescoUsername = singleton.getProperty(
                PropsKeys.ALFRESCO_USERNAME);
        String alfrescoPassword = singleton.getProperty(
                PropsKeys.ALFRESCO_PASSWORD);

      URL alfresco = new URL(
              alfrescoIp
              + alfrescoLoginUrl
              + "?u=" + alfrescoUsername + "&pw=" + alfrescoPassword);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(alfresco.openStream()));

        while ((in.readLine()) != null) {
            token = in.readLine();
        }
        token = token.replace("<ticket>", "");
        token = token.replace("</ticket>", "");
        token = token.trim();

        in.close();

        return token;
    }
}
