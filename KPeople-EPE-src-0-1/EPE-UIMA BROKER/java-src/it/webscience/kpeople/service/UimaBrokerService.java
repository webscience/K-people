package it.webscience.kpeople.service;

import it.webscience.kpeople.uimaclient.UimaAsClient;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

/**
 * Servizio per la gestione dei processi.
 */
public class UimaBrokerService {

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Metodo per la ricerca dei processi.
     * @param request
     *            - richiesta ricevuta contenente l'evento in formato xml.
     */
    public final void sendEvent(final OMElement request) {

        logger.info(this.getClass() + " :request received");

        String content = request.toString();

        UimaAsClient runner;
        try {
            runner = new UimaAsClient();
            runner.run(content);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }

    }
}
