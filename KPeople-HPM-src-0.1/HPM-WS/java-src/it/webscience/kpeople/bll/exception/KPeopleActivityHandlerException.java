package it.webscience.kpeople.bll.exception;

/**
 * Classe relativa ad un'eccezione generica del livello DAL.
 */
public class KPeopleActivityHandlerException extends Exception {

    /**
     * Auto generated serial version UID.
     */
    private static final long serialVersionUID = 4137578344542936811L;

    /** Costruttore. */
    public KPeopleActivityHandlerException() {
        super();
    }

    /**
     * Costruttore che definisce il messaggio dell'eccezione.
     * @param message messaggio dell'eccezione
     */
    public KPeopleActivityHandlerException(final String message) {
        super(message);
    }

}
