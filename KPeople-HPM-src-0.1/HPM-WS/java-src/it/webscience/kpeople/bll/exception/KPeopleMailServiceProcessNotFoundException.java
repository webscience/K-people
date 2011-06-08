package it.webscience.kpeople.bll.exception;

/**
 * Eccezione lanciata nel servizio MailService.
 * Viene genetara quando la mail non è associata ad un processo.
 */
public class KPeopleMailServiceProcessNotFoundException
    extends KPeopleBLLException {

    /**
     * Auto generated version UID.
     */
    private static final long serialVersionUID = 7269495189796289794L;

    /**
     * Costruttore.
     */
    public KPeopleMailServiceProcessNotFoundException() {
        super();
    }

    /**
     * Costruttore che definisce il messaggio dell'eccezione.
     * @param message
     *            messaggio dell'eccezione
     */
    public KPeopleMailServiceProcessNotFoundException(final String message) {
        super(message);
    }
}
