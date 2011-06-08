package it.webscience.kpeople.bll.impl;

import java.io.IOException;

import it.webscience.kpeople.be.Activity;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;

/**
 * Interfaccia per il SendEventManager.
 * @author dellanna
 *
 */
public interface ISendEventManager {

    /**
     * Invia un evento.
     * @param pPattern pattern
     * @param pUser user
     * @param pProcess process
     * @throws Exception eccezione
     */
    void sendEventCreatePattern(
            Pattern pPattern,
            User pUser,
            Process pProcess) throws Exception;

    /**
     * Invia un evento.
     * @param activity attività
     * @throws Exception eccezione durante l'invio dell'evento all'ega
     */
    void sendEventAcceptContribute(final Activity activity)
            throws Exception;
}
