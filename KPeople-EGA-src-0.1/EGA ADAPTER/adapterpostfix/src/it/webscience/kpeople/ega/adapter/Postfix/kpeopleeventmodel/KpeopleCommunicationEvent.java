package it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel;

import java.util.HashMap;



/**
 * @author filieri
 */
public class KpeopleCommunicationEvent extends KpeopleSimpleEvent {

	protected HashMap<String, String> properties;
    /**
     * @param idevent
     *            Identificativo univoco dell'evento.
     * @param actiontype
     *            Identificativo della tipologia di evento.
     * @param systemtype
     *            Riferimento al sistema verticale.
     * @param idaction
     *            Riferimento all'azione associata all'evento.
     */
    public KpeopleCommunicationEvent(final String idevent,
            final String actiontype, final String systemtype,
            final Long idaction) {
        super(idevent, actiontype, systemtype, idaction);

    }

    /**
     * @param idevent Identificativo univoco dell'evento.
     * @param actiontype Identificativo della tipologia di evento.
     * @param systemtype Riferimento al sistema verticale.
     * @param idaction Riferimento all'azione associata all'evento.
     * @param systemId Identificativo univoco del sistema verticale.
     * @param actionReference riferimento all'azione che è stata intercettata.
     */
    public KpeopleCommunicationEvent(final String idevent,
            final String actiontype, final String systemtype,
            final Long idaction, final String systemId,
            final String actionReference) {
        super(idevent, actiontype, systemtype, idaction, systemId,
                actionReference);
    }

    protected String operation;

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation
     *            the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @see it.webscience.kpeople.ega.adapter
     *      .sharepoint.kpeopleeventmodel.KpeopleAbstractEvent#generateXml().
     * @return un oggetto di tipo String che rappresenta il frammento xml
     *         relativo all'evento.
     */
    public String generateXml() {
        return null;
    }
    
    /**
     * @return the properties
     */
    public HashMap<String, String> getProperties() {
        return properties;
    }

    /**
     * @param properties
     *            the properties to set
     */
    public void setProperties(final HashMap<String, String> properties) {
        this.properties = properties;
    }

}
