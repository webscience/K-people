package it.webscience.kpeople.bll.exception;

/**
 * Eccezione lanciata nel servizio MailService.
 * Viene generata quando l'invio della mail fallisce
 */
public class KPeopleMailServiceSendMailException extends KPeopleBLLException {

    /**
     * Auto generated version UID.
     */
    private static final long serialVersionUID = -721956528121393902L;

    /**
     * Costruttore.
     */
    public KPeopleMailServiceSendMailException() {
        super();
    }

    /**
     * Costruttore che definisce il messaggio dell'eccezione.
     * @param message
     *            messaggio dell'eccezione
     */
    public KPeopleMailServiceSendMailException(final String message) {
        super(message);
    }
}
