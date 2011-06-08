package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.AttachmentType;

/**
 * Classe di conversione tra la classe
 * AttachmentType BE e AttachmentType Service.
 */
public final class AttachmentTypeConverter {

    /**
     * Costruttore privato.
     */
    private AttachmentTypeConverter() { }

    /**
     * Converte da Service a BE.
     * @param in oggetto AttachmentType Service
     * @return oggetto AttachmentType BE
     */
    public static it.webscience.kpeople.be.AttachmentType toBE(
            final AttachmentType in) {
        it.webscience.kpeople.be.AttachmentType out =
            new it.webscience.kpeople.be.AttachmentType();

        out.setIdAttachmentType(in.getIdAttachmentType());
        out.setName(in.getName());


        return out;
    }

    /**
     * Converte da BE a Service.
     * @param in oggetto AttachmentType Be.
     * @return oggetto AttachmentType Service.
     */
    public static it.webscience.kpeople.service.datatypes.
        AttachmentType toService(
            final it.webscience.kpeople.be.AttachmentType in) {

        if (in == null) {
            
            it.webscience.kpeople.service.datatypes.AttachmentType out =
                new it.webscience.kpeople.service.datatypes.AttachmentType();
    
            
            return out;
        }

        it.webscience.kpeople.service.datatypes.AttachmentType out =
            new it.webscience.kpeople.service.datatypes.AttachmentType();
        out.setIdAttachmentType(in.getIdAttachmentType());
        out.setName(in.getName());

        return out;
    }
}
