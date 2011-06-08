package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.ActivityType;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe ActivityType BE e ActivityType Service.
 */
public final class ActivityTypeConverter {

	/**
     * Costruttore privato.
     */
    private ActivityTypeConverter() {
    }
    
    /**
     * Converte da Service a BE.
     * @param in oggetto ActivityType Service
     * @return oggetto ActivityType BE
     */
    public static it.webscience.kpeople.be.ActivityType toBE(
    		final ActivityType in) {

        it.webscience.kpeople.be.ActivityType out =
            new it.webscience.kpeople.be.ActivityType();

        out.setIdActivityType(in.getIdActivityType());
        out.setName(in.getName());
        out.setDescription(in.getDescription());
        out.setRelatedForm(in.getRelatedForm());
        
        DataTraceClassConverter.toBE(in, out);
        return out;
    }
    
    
    
    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti ActivityType Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.ActivityType>
        toBE(final ActivityType[] in) {

        List<it.webscience.kpeople.be.ActivityType> out =
            new ArrayList<it.webscience.kpeople.be.ActivityType>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }
    
    
    
    /**
     * Converte da List Process BE a array ActivityType Service.
     * @param in List ActivityType BE
     * @return array ActivityType Service
     */
    public static ActivityType[] toService(
            final List<it.webscience.kpeople.be.ActivityType> in) {

    	ActivityType[] out = new ActivityType[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }
    
    /**
     * Converte oggetto ActivityType BE in Service.
     * @param in oggetto ActivityType BE
     * @return oggetto ActivityType Service
     */
    public static ActivityType toService(
            final it.webscience.kpeople.be.ActivityType in) {
    	ActivityType out = new ActivityType();

    	out.setIdActivityType(in.getIdActivityType());
    	out.setName(in.getName());
    	out.setDescription(in.getDescription());
    	out.setRelatedForm(in.getRelatedForm());
        
        DataTraceClassConverter.toService(in, out);

        return out;
    }
    
}
