package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.CommunicationEvent;

/**
 * Classe di conversione tra la classe Email BE e Email Service.
 */
public final class CommunicationEventConverter {

    /**
     * Costruttore privato.
     */
    private CommunicationEventConverter() {

    }

    /**
     * Converte da BE a Service.
     * @param in oggetto Email BE
     * @return oggetto Email Service
     */
    public static CommunicationEvent toService(
            final it.webscience.kpeople.be.CommunicationEvent in) {

        CommunicationEvent out = new CommunicationEvent();

        out.setObject(in.getObject());
        out.setBody(in.getBody());
        
        out.setAttachmentType(
                AttachmentTypeConverter.toService(in.getAttachmentType()));

        if (in.getCcUser() != null) {
            out.setCcUser(UserConverter.toService(in.getCcUser()));
        }

        if (in.getCcnUser() != null) {
            out.setCcnUser(UserConverter.toService(in.getCcnUser()));
        }

        if (in.getUserFrom() != null) {
            out.setUserFrom(UserConverter.toService(in.getUserFrom()));
        }

        if (in.getToUser() != null) {
            out.setToUser(UserConverter.toService(in.getToUser()));
        }

        if (in.getDocList() != null) {
            out.setDocList(DocumentConverter.toService(in.getDocList()));
        }

        return out;
    }

    /**
     * Converte da Service a BE.
     * @param in oggetto Email Service
     * @return oggetto Email BE
     */
    public static it.webscience.kpeople.be.CommunicationEvent toBE(
            final CommunicationEvent in) {
        it.webscience.kpeople.be.CommunicationEvent out =
            new it.webscience.kpeople.be.CommunicationEvent();

        out.setObject(in.getObject());
        out.setBody(in.getBody());

        if (in.getCcUser() != null) {
            out.setCcUser(UserConverter.toBE(in.getCcUser()));
        }

        if (in.getCcnUser() != null) {
            out.setCcnUser(UserConverter.toBE(in.getCcnUser()));
        }

        if (in.getUserFrom() != null) {
            out.setUserFrom(UserConverter.toBE(in.getUserFrom()));
        }

        if (in.getToUser() != null) {
            out.setToUser(UserConverter.toBE(in.getToUser()));
        }

        if (in.getDocList() != null) {
            out.setDocList(DocumentConverter.toBE(in.getDocList()));
        }

        return out;
    }
}
