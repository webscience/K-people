package it.webscience.uima.ocStorageAbstract;

/** Codici di uscita generati dallo storage agent.
 * @author dellanna
 */
public class OcStorageExitCodes {

    /** Uscita dallo storage agent senza errori. */
    public static final int EXIT_OK = 0;

    /** Cartella già presente.
     * L'errore viene generato se un evento con lo stesso
     * ID è già presenet nello storage
     */
    public static final int EXISTING_FOLDER = 1;

    /** problemi relativi a classi non trovate. */
    public static final int CLASS_NOT_FOUND_EXCEPTION = 2;

    /** problemi relativi a serializzazione/deserializzazione degli oggetti. */
    public static final int IO_EXCEPTION = 3;

    /** Errore generico. */
    public static final int GENERIC_ERROR = 99;
}
