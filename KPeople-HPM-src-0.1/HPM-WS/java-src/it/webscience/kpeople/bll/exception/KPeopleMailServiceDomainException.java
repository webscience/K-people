package it.webscience.kpeople.bll.exception;

/**
 * Classe relativa ad un'eccezione sul dominio della mail.
 */
public class KPeopleMailServiceDomainException extends KPeopleBLLException {

    /**
     * Auto generated version UID.
     */
    private static final long serialVersionUID = 3227232682588767992L;

    /** Costruttore. */
    public KPeopleMailServiceDomainException() {
        super();
    }

    /**
     * Costruttore che definisce il messaggio dell'eccezione.
     * @param message messaggio dell'eccezione
     */
    public KPeopleMailServiceDomainException(final String message) {
        super(message);
    }
}
