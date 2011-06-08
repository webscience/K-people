package it.webscience.kpeople.bll.exception;

/**
 * Eccezione lanciata nel servizio MailService.
 * Viene generata quando i processi associati alla mail non hanno keywords
 */
public class KPeopleMailServiceKeywordNotFoundException
    extends KPeopleBLLException {

    /**
     * Auto generated version UID.
     */
    private static final long serialVersionUID = -329421398662874962L;

    /**
     * Costruttore.
     */
    public KPeopleMailServiceKeywordNotFoundException() {
        super();
    }

    /**
     * Costruttore che definisce il messaggio dell'eccezione.
     * @param message
     *            messaggio dell'eccezione
     */
    public KPeopleMailServiceKeywordNotFoundException(final String message) {
        super(message);
    }
}
