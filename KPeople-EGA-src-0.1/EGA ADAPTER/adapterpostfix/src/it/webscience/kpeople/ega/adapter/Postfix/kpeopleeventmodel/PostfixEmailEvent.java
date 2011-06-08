package it.webscience.kpeople.ega.adapter.Postfix.kpeopleeventmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axis2.jaxws.utility.AttachmentUtils;

/**
 * @author XPMUser
 */
public class PostfixEmailEvent extends KpeopleCommunicationEvent {

    

    protected String bodyType;

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
    public PostfixEmailEvent(final String idevent, final String actiontype,
            final String systemtype, final Long idaction) {
        super(idevent, actiontype, systemtype, idaction);
    }

    public PostfixEmailEvent(final String idevent, final String actiontype,
            final String systemtype, final Long idaction, final String systemId,
            final String actionReference) {
        super(idevent, actiontype, systemtype, idaction, systemId,
                actionReference);
    }

   

    public void setDetail(final String key, final String value) {
        if (details == null) {
            details = "";
        }
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMElement element = fac.createOMElement(key, null);
        element.setText(value);
        String elemtnstring = null;
        try {
            elemtnstring = element.toStringWithConsume();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        details = details.concat(elemtnstring);
    }

    public void addAttachment(final KpeopleAttachment attachment) {
        if (attachments == null) {
            attachments = new ArrayList<KpeopleAttachment>();
        }
        if (attachment != null) {
            attachments.add(attachment);
        }
    }

    /**
     * @return the bodyType
     */
    public String getBodyType() {
        return bodyType;
    }

    /**
     * @param bodyType
     *            the bodyType to set
     */
    public void setBodyType(final String bodyType) {
        this.bodyType = bodyType;
    }

}
