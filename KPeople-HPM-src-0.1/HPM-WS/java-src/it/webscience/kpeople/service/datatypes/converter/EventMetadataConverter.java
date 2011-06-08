package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.EventMetadata;


import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe EventMetadata BE e EventMetadata Service.
 */
public final class EventMetadataConverter {

    /**
     * Costruttore privato.
     */
    private EventMetadataConverter() {

    }

    /**
     * Converte da Service a BE.
     * @param in oggetto EventMetadata Service
     * @return oggetto EventMetadata BE
     */
    public static it.webscience.kpeople.be.EventMetadata toBE(
    		final EventMetadata in) {

        it.webscience.kpeople.be.EventMetadata out =
            new it.webscience.kpeople.be.EventMetadata();

        out.setIdEventMetadata(in.getIdEventMetadata());
        out.setEvent(EventConverter.toBE(in.getEvent()));
        out.setKeyname(in.getKeyname());
        out.setValue(in.getValue());
        
        DataTraceClassConverter.toBE(in, out);

        return out;
    }
    
    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti PatternMetadata Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.EventMetadata>
        toBE(final EventMetadata[] in) {

        List<it.webscience.kpeople.be.EventMetadata> out =
            new ArrayList<it.webscience.kpeople.be.EventMetadata>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }
    
    /**
     * Metodo che converte EventMetadata di tipo BE in
     * EventMetadata di tipo Service.
     * @param in oggetto EventMetadata di tipo BE.
     * @return oggetto EventMetadata di tipo Service.
     */
    public static it.webscience.kpeople.service.datatypes.EventMetadata 
    toService(
            final it.webscience.kpeople.be.EventMetadata in) {
        if (in == null) {
            return null;
        }

        it.webscience.kpeople.service.datatypes.EventMetadata out =
            new it.webscience.kpeople.service.datatypes.EventMetadata();

        out.setIdEventMetadata(in.getIdEventMetadata());
        out.setKeyname(in.getKeyname());
        out.setValue(in.getValue());
        //out.setEvent(EventConverter.eventToService(in.getEvent()));

        return out;
    }


    /**
     * Metodo per convertire una lista di EventMetadata di tipo BE
     * in un array di EventMetadata di tipo Service.
     * @param in Lista di EventMetadata di tipo BE.
     * @return Array di EventMetadata di tipo Service.
     */
    public static it.webscience.kpeople.service.datatypes.EventMetadata[]
                  toService(
                          final List<it.webscience.kpeople.
                          be.EventMetadata> in) {
        it.webscience.kpeople.service.datatypes.EventMetadata[] out =
            new it.webscience.kpeople.service.
            datatypes.EventMetadata[in.size()];
        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
            }
        return out;
        }

}
