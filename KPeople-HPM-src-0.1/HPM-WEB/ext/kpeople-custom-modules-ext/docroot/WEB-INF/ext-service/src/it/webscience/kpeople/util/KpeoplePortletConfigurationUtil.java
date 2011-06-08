package it.webscience.kpeople.util;

import it.webscience.kpeople.Singleton;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * Classi di utils per l'interfacciamento con gli stub.
 */
public class KpeoplePortletConfigurationUtil {

    /** logger. */
    private static Log logger = LogFactoryUtil
            .getLog(KpeoplePortletConfigurationUtil.class);

    /** istanza singleton. */
    private static Singleton singleton = Singleton.getInstance();

    /**
     * Metodo che restituisce la friendly url per il dettaglio processo.
     * @return la friendly url per il dettaglio processo.
     */
    public final String getFriendlyUrlDettaglioProcesso() {

        String friendlyUrlProcesso =
                singleton
                        .getProperty(PropsKeys.FRIENDLY_URL_DETTAGLIO_PROCESSO);
        logger.debug("Friendly url dettaglio processo " + friendlyUrlProcesso);

        return friendlyUrlProcesso;
    }

    /**
     * Metodo che restituisce la pagina di destinazione per il dettaglio
     * processo.
     * @return il percorso della pagina di dettaglio processo.
     */
    public final String getPaginaDettaglioProcesso() {

        String pageProcesso =
                singleton.getProperty(PropsKeys.PAGINA_DETTAGLIO_PROCESSO);
        logger.debug("Url pagina dettaglio processo " + pageProcesso);

        return pageProcesso;
    }

    /**
     * Metodo che restituisce la friendly url per la creazione nuovo pattern.
     * @return la friendly url per la creazione nuovo pattern.
     */
    public final String getFriendlyUrlNuovoPattern() {

        String friendlyUrlPattern =
                singleton.getProperty(PropsKeys.FRIENDLY_URL_NUOVO_PATTERN);
        logger.debug("Friendly url nuovo pattern " + friendlyUrlPattern);

        return friendlyUrlPattern;
    }

    /**
     * Metodo che restituisce la pagina di destinazione per la creazione nuovo
     * pattern.
     * @return l'url della pagina di creazione nuovo pattern.
     */
    public final String getPaginaNuovoPattern() {

        String pagePattern =
                singleton.getProperty(PropsKeys.PAGINA_NUOVO_PATTERN);
        logger.debug("Url pagina creazione nuovo pattern " + pagePattern);

        return pagePattern;
    }

    /**
     * Metodo che restituisce la friendly url per il dettaglio processo.
     * @return la friendly url per il dettaglio processo.
     */
    public final String getFriendlyUrlDettaglioPattern() {

        String friendlyUrlDettaglioPattern =
                singleton
                        .getProperty(PropsKeys.FRIENDLY_URL_DETTAGLIO_PATTERN);
        logger.debug("Friendly url dettaglio pattern "
                + friendlyUrlDettaglioPattern);

        return friendlyUrlDettaglioPattern;
    }

    /**
     * Metodo che restituisce la pagina di destinazione per il dettaglio
     * processo.
     * @return il percorso della pagina di dettaglio processo.
     */
    public final String getPaginaDettaglioPattern() {

        String pageDettaglioPattern =
                singleton.getProperty(PropsKeys.PAGINA_DETTAGLIO_PATTERN);
        logger.debug("Url pagina dettaglio pattern " + pageDettaglioPattern);

        return pageDettaglioPattern;
    }
    
    
    
    

    /**
     * Metodo che restituisce la friendly url per il dettaglio processo.
     * @return la friendly url per il dettaglio processo.
     */
    public final String getFriendlyUrlDettaglioAttivita() {

        String friendlyUrlDettaglioAttivita =
                singleton
                        .getProperty(PropsKeys.FRIENDLY_URL_DETTAGLIO_ATTIVITA);
        logger.debug("Friendly url dettaglio Attivita "
                + friendlyUrlDettaglioAttivita);

        return friendlyUrlDettaglioAttivita;
    }

    /**
     * Metodo che restituisce la pagina di destinazione per il dettaglio
     * processo.
     * @return il percorso della pagina di dettaglio processo.
     */
    public final String getPaginaDettaglioAttivita() {

        String pageDettaglioAttivita =
                singleton.getProperty(PropsKeys.PAGINA_DETTAGLIO_ATTIVITA);
        logger.debug("Url pagina dettaglio Attivita " + pageDettaglioAttivita);

        return pageDettaglioAttivita;
    }
    
    
    
    /**
     * Metodo che restituisce la pagina di destinazione per il dettaglio
     * processo.
     * @return il percorso della pagina di dettaglio processo.
     */
    public final String getBiSrc() {

        String src =
                singleton.getProperty(PropsKeys.REPORT_SRC_BI);
        logger.debug("link bi " + src);

        return src;
    }

}
