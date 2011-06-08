package it.webscience.kpeople.service.datatypes;

/**
 * Tabella OBJECT_KEYWORD.
 *
 */
public class ObjectKeyword  {
    /**
     * Campo obbligatorio.
     * Chiave esterna che punta ad un record della tabella KEYWORD
     */
    private Keyword keyword;

    /**
     * Campo obbligatorio.
     * Chiave esterna che punta ad un record della tabella OBJECT_TYPE
     */
    private ObjectType objectType;

    /**
     * Campo obbligatorio.
     * Chiave esterna che punta ad un record della tabella
     * PROCESS o EVENT.
     * L'interpretazione di tale campo è legata al valore
     * della chiave esterna id_object_type.
     */
    private int idObject;

    /** valore. */
    private String value;

    /**
     * @return the keyword
     */
    public final Keyword getKeyword() {
        return keyword;
    }

    /**
     * @param in the keyword to set
     */
    public final void setKeyword(final Keyword in) {
        this.keyword = in;
    }

    /**
     * @return the objectType
     */
    public final ObjectType getObjectType() {
        return objectType;
    }

    /**
     * @param in the objectType to set
     */
    public final void setObjectType(final ObjectType in) {
        this.objectType = in;
    }

    /**
     * @return the idObject
     */
    public final int getIdObject() {
        return idObject;
    }

    /**
     * @param in the idObject to set
     */
    public final void setIdObject(final int in) {
        this.idObject = in;
    }

    /**
     * @return the value
     */
    public final String getValue() {
        return value;
    }

    /**
     * @param in the value to set
     */
    public final void setValue(final String in) {
        this.value = in;
    }
}
