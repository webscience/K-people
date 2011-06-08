package it.webscience.kpeople.bll.exception;

/**
 * Eccezione lanciata nel servizio MailService.
 * Viene genetara quando la mail non è associata ad un processo.
 */
public class KPeopleMailServicePatternNotFoundException
    extends KPeopleBLLException {


    /**
     * Auto generated version UID.
     */
    private static final long serialVersionUID = 8579694187531905942L;

    /**
     * Costruttore.
     */
    public KPeopleMailServicePatternNotFoundException() {
        super();
    }

    /**
     * Costruttore che definisce il messaggio dell'eccezione.
     * @param message
     *            messaggio dell'eccezione
     */
    public KPeopleMailServicePatternNotFoundException(final String message) {
        super(message);
    }
}
