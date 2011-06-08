package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.be.CommunicationEvent;
import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.Pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author depascalis
 * Classe per la conversione della classe Attachment service nella classe
 * Attachment BE.
 */
public final class AttachmentConverter {

    /**
     * Costruttore privato.
     */
    private AttachmentConverter() {
    }

    /**
     * Converte da Attachment Service in Attachment BE.
     * @param in oggetto di tipo Attachment service.
     * @return oggetto di tipo Attachment BE.
     */
    public static it.webscience.kpeople.be.Attachment toBE(
            final it.webscience.kpeople.service.datatypes.Attachment in) {
        if (in == null) {
            return null;
        }

        it.webscience.kpeople.be.Attachment out =
            new it.webscience.kpeople.be.Attachment();

        out.setIdAttachment(in.getIdAttachment());
        
        
        if (in.getAttachmentType()!=null) {
        	out.setAttachmentType(AttachmentTypeConverter.
                toBE(in.getAttachmentType()));
        }
        
        out.setDescription(in.getDescription());
        out.setHpmAttachmentId(in.getHpmAttachmentId());
        out.setName(in.getName());

        return out;
    }

    /**
     * Converte da Attachment BE in Attachment Service.
     * @param in oggetto di tipo Attachment BE.
     * @return oggetto di tipo Attachment Service.
     */
    public static it.webscience.kpeople.service.datatypes.Attachment toService(
            final it.webscience.kpeople.be.Attachment in) {
        if (in == null) {
            return null;
        }

        it.webscience.kpeople.service.datatypes.Attachment out =
            new it.webscience.kpeople.service.datatypes.Attachment();
        out.setIdAttachment(in.getIdAttachment());

        out.setDescription(in.getDescription());

        if (in.getAttachmentType() != null) {
        out.setAttachmentType(AttachmentTypeConverter.
                toService(in.getAttachmentType()));
        }

        out.setHpmAttachmentId(in.getHpmAttachmentId());
        out.setName(in.getName());

        if (in instanceof Document) {
            out.setDocument(DocumentConverter.toService((Document) in));
        } else if (in instanceof CommunicationEvent) {
            out.setCommunicationEvent(
                    CommunicationEventConverter.toService(
                            (CommunicationEvent) in));
        } else if (in instanceof Pattern) {
            out.setPattern(PatternConverter.toService((Pattern) in));
        }

        return out;
    }

    /**
     * Metodo per convertire la lista di Attachment di tipo BE in
     * un array di Attachments di tipo Service.
     * @param in lista di Attachments di tipo BE.
     * @return Array di Attachments di tipo Service.
     */
    public static it.webscience.kpeople.service.datatypes.Attachment[]
            toService(
                    final List<it.webscience.kpeople.be.Attachment> in) {

        it.webscience.kpeople.service.datatypes.Attachment[] out =
            new it.webscience.kpeople.service.datatypes.Attachment[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }

    /**
     * Metodo che converte l'array di Attachments di tipo Service
     * in una Lista di Attachments di tipo BE.
     * @param in Array di Attachments di tipo Service.
     * @return lista di Attachments di tipo BE.
     */
    public static List<it.webscience.kpeople.be.Attachment> toBE(
            final it.webscience.kpeople.service.datatypes.Attachment[] in) {

        List<it.webscience.kpeople.be.Attachment> listAttachments =
            new ArrayList<it.webscience.kpeople.be.Attachment>();

        for (int i = 0; i < in.length; i++) {

            listAttachments.add(toBE(in[i]));
        }
        return listAttachments;
    }


}
