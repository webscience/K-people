package it.webscience.kpeople.be;

/**
 * Tabella OBJECT_TYPE.
 */
public class ObjectType extends DataTraceClass {

    /**
     * Id per l'object type di default per l'entità process.
     */
    public static final int DEFAULT_OBJ_TYPE_PROCESS = 1;

    /**
     * Id per l'object type di default per l'entità event.
     */
    public static final int DEFAULT_OBJ_TYPE_EVENT = 2;

    /**
     * Chiave primaria. Identificativo univoco per un record dela tabella
     * OBJECT_TYPE
     */
    private int idObjectType;

    /**
     * Campo obbligatorio. Valore testuale relativo al nome dela tipologia di
     * oggetto
     */
    private String name;

    /**
     * @return the idObjectType
     */
    public final int getIdObjectType() {
        return idObjectType;
    }

    /**
     * @param in
     *            the idObjectType to set
     */
    public final void setIdObjectType(final int in) {
        this.idObjectType = in;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param in
     *            the name to set
     */
    public final void setName(final String in) {
        this.name = in;
    }
}
