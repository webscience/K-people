package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe Document BE e Document Service.
 */
public final class DocumentConverter {

    /**
     * Costruttore privato.
     */
    private DocumentConverter() { }

    /**
     * Converte da Service a BE.
     * @param in oggetto Document Service
     * @return oggetto Document BE
     */
    public static Document toService(
            final it.webscience.kpeople.be.Document in) {
        Document out = new Document();

        out.setAuthor(in.getAuthor());
        out.setGuid(in.getGuid());
        out.setHashcode(in.getHashcode());
        out.setIdAttachment(in.getIdAttachment());
        out.setDescription(in.getDescription());
        out.setHpmAttachmentId(in.getHpmAttachmentId());
        out.setData(in.getData());
        out.setFileName(in.getFileName());
        out.setDocumentType(in.isDocumentType());
        out.setDocumentType(in.isDocumentType());
        out.setDescription(in.getDescription());
        out.setHpmAttachmentId(in.getHpmAttachmentId());
        out.setName(in.getName());


            out.setAttachmentType(
                    AttachmentTypeConverter.toService(in.getAttachmentType()));
        

        return out;
    }

    /**
     * Converte da Service a BE.
     * @param in oggetto Document Service
     * @return oggetto Document BE
     */
    public static it.webscience.kpeople.be.Document toBE(final Document in) {
        it.webscience.kpeople.be.Document out =
            new it.webscience.kpeople.be.Document();

        out.setAuthor(in.getAuthor());
        out.setGuid(in.getGuid());
        out.setHashcode(in.getHashcode());
        out.setIdAttachment(in.getIdAttachment());
        out.setDescription(in.getDescription());
        out.setHpmAttachmentId(in.getHpmAttachmentId());
        out.setData(in.getData());
        out.setFileName(in.getFileName());
        out.setDocumentType(in.isDocumentType());
        out.setDocumentType(in.isDocumentType());

        if (in.getAttachmentType() != null) {
            out.setAttachmentType(
                    AttachmentTypeConverter.toBE(in.getAttachmentType()));
        }

        return out;
    }

    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti Document Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.Document> toBE(
            final Document[] in) {
        List<it.webscience.kpeople.be.Document> out =
            new ArrayList<it.webscience.kpeople.be.Document>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }

    /**
     * Converte da List Document BE a array Document Service.
     * @param in List Document BE
     * @return array Document Service
     */
    public static Document[] toService(
            final List<it.webscience.kpeople.be.Document> in) {

        Document[] out = new Document[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }
}
