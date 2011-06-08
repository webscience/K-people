package it.webscience.uima.consumer;

import it.webscience.uima.ocSesameAdapter.sesameAdapterService.serviceimpl.SesameAdapterServiceImpl;
import it.webscience.uima.ocSesameAdapter.sesameAdapterService.serviceinterface.SesameAdapterServiceInterface;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CasConsumer_ImplBase;

/**
 * CAS consumer che scrive su STD out le annotations.
 */
public class SesameCasConsumer extends CasConsumer_ImplBase {

    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Processes the CAS.
     * @param cas
     *            a CAS which has been populated.
     */
    public final void processCas(final CAS cas) {
        logger.debug("Cas Consumer: processCas - Begin");

        SesameAdapterServiceInterface service = new SesameAdapterServiceImpl();
        service.indexEvent(cas);

        logger.debug("Cas Consumer: processCas - End");
    }
}
