package it.webscience.kpeople.util;

/**
 * Classe util per la validazione di stringhe.
 */
public final class Validator {

    /**
     * Costruttore privato della classe util Validator.
     */
    private Validator() {
    }

    /**
     * Validazione semplice di una stringa.
     * @param value stringa in ingresso
     * @return validazione
     */
    public static boolean isValid(final String value) {
        return (value != null && value.length() > 0);
    }
}
