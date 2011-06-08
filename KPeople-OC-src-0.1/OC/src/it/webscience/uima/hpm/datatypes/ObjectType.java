package it.webscience.uima.hpm.datatypes;

/**
 * Tabella OBJECT_TYPE.
 *
 */
public class ObjectType {
    /**
     * Chiave primaria.
     * Identificativo univoco per un record dela tabella OBJECT_TYPE
     */
    private int idObjectType;

    /**
     * Campo obbligatorio.
     * Valore testuale relativo al nome dela tipologia di oggetto
     */
    private String name;

    /**
     * @return the idObjectType
     */
    public final int getIdObjectType() {
        return idObjectType;
    }

    /**
     * @param in the idObjectType to set
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
     * @param in the name to set
     */
    public final void setName(final String in) {
        this.name = in;
    }
}
