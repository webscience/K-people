package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.ActivityMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe Activity BE e Activity Service.
 */
public final class ActivityMetadataConverter {

	/**
     * Costruttore privato.
     */
    private ActivityMetadataConverter() {
    }
    
    /**
     * Converte da Service a BE.
     * @param in oggetto Activity Service
     * @return oggetto Activity BE
     */
    public static it.webscience.kpeople.be.ActivityMetadata toBE(
    		final ActivityMetadata in) {

        it.webscience.kpeople.be.ActivityMetadata out =
            new it.webscience.kpeople.be.ActivityMetadata();

        out.setIdActivityMetadata(in.getIdActivityMetadata());
        out.setKeyname(in.getKeyname());
        out.setValue(in.getValue());
        out.setActivitiProcessMetadata(in.isActivitiProcessMetadata());
        DataTraceClassConverter.toBE(in, out);
        return out;
    }
    
    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti Activity Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.ActivityMetadata>
        toBE(final ActivityMetadata[] in) {

        List<it.webscience.kpeople.be.ActivityMetadata> out =
            new ArrayList<it.webscience.kpeople.be.ActivityMetadata>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }
    
    
    
    /**
     * Converte da List Process BE a array Activity Service.
     * @param in List Activity BE
     * @return array Activity Service
     */
    public static ActivityMetadata[] toService(
            final List<it.webscience.kpeople.be.ActivityMetadata> in) {

    	ActivityMetadata[] out = new ActivityMetadata[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }
    
    /**
     * Converte oggetto Activity BE in Service.
     * @param in oggetto Activity BE
     * @return oggetto Activity Service
     */
    public static ActivityMetadata toService(
            final it.webscience.kpeople.be.ActivityMetadata in) {
    	ActivityMetadata out = new ActivityMetadata();

    	out.setIdActivityMetadata(in.getIdActivityMetadata());
        out.setKeyname(in.getKeyname());
        out.setValue(in.getValue());
        out.setActivitiProcessMetadata(in.isActivitiProcessMetadata());
        DataTraceClassConverter.toService(in, out);

        return out;
    }
    
    
}
