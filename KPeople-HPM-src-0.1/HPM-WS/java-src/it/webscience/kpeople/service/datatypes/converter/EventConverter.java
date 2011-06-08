package it.webscience.kpeople.service.datatypes.converter;
import it.webscience.kpeople.service.datatypes.converter.AttachmentConverter;
import java.util.ArrayList;
import java.util.List;

public class EventConverter {

    /**
     * Costruttore privato.
     */
    private EventConverter() {

    }

    /**
     * Converte la classe Event service in Event BE.
     * @param in classe Event Service.
     * @return classe Event BE.
     */
    public static it.webscience.kpeople.be.Event toBE(
            final it.webscience.kpeople.service.datatypes.Event in) {
        if (in == null) {
            return null;
        }

        it.webscience.kpeople.be.Event out =
            new it.webscience.kpeople.be.Event();
        out.setIdEvent(in.getIdEvent());
        out.setName(in.getName());
        out.setHpmEventId(in.getHpmEventId());
        out.setHpmSystemId(in.getHpmSystemId());
        //out.setAttachments(AttachmentConverter.toService(in.getAttachments()));

        return out;
    }




    /**
     * Metodo che converte la Lista di Event di tipo BE in un Array di
     * Event di tipo Service.
     * @param in Lista di Event di tipo BE.
     * @return Array di Event di tipo Service.
     */
    public static it.webscience.kpeople.service.datatypes.Event[] toService(
            final List<it.webscience.kpeople.be.Event> in) {

        if (in == null) {
            return null;
        }
        it.webscience.kpeople.service.datatypes.Event[] out =
            new it.webscience.kpeople.service.datatypes.Event[in.size()];
        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
            }
        return out;
    }


    /**
     * Converte un Evento di tipo BE in Event di tipo Service.
     * @param in oggetto Event di tipo BE.
     * @return oggetto Event di tipo Service.
     */
    public static it.webscience.kpeople.service.datatypes.Event toService(
            final it.webscience.kpeople.be.Event in) {

        if (in == null) {
            return null;
        }
        it.webscience.kpeople.service.datatypes.Event out =
            new it.webscience.kpeople.service.datatypes.Event();

            out.setIdEvent(in.getIdEvent());
            out.setName(in.getName());
            out.setHpmEventId(in.getHpmEventId());
            out.setHpmSystemId(in.getHpmSystemId());
            out.setFirstActionDate(in.getFirstActionDate());
            out.setLastActionDate(in.getLastActionDate());
            out.setAttachments(AttachmentConverter.toService(
                    in.getAttachments()));
            out.setEventMetadata(EventMetadataConverter.
                    toService(in.getEventMetadata()));
            out.setKeywords(KeywordConverter.toService(in.getKeyword()));
            out.setUser(UserConverter.toService(in.getUser()));
        return out;
    }


}
