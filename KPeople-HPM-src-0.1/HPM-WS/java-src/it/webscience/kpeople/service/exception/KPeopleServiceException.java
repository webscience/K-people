package it.webscience.kpeople.service.exception;

/**
 * Classe relativa ad un'eccezione generica del livello BLL.
 */
public class KPeopleServiceException extends Exception {

    /**
     * Auto generated version UID.
     */
    private static final long serialVersionUID = 5411645337875261011L;

    /**
     * Costruttore di default.
     */
    public KPeopleServiceException() {
        super();
    }

    /**
     * Costruttore che definisce il messaggio dell'eccezione.
     * @param message messaggio dell'eccezione
     */
    public KPeopleServiceException(final String message) {
        super(message);
    }

    /**
     * Costruttore che accetta un oggetto throwable per preservare
     * lo stack trace.
     * @param e eccezione
     */
    public KPeopleServiceException(final Throwable e) {
        super(e);
    }

    /**
     * Costruttore che definisce il messaggio dell'eccezione.
     * @param message messaggio dell'eccezione
     * @param e eccezione
     */
    public KPeopleServiceException(final String message, final Throwable e) {
        super(message, e);
    }
}
