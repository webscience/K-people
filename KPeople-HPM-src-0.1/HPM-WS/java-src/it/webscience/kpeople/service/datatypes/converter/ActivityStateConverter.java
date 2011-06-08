package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.ActivityState;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe ActivityState BE e ActivityState Service.
 */
public final class ActivityStateConverter {

	/**
     * Costruttore privato.
     */
    private ActivityStateConverter() {
    }
    
    /**
     * Converte da Service a BE.
     * @param in oggetto ActivityState Service
     * @return oggetto ActivityState BE
     */
    public static it.webscience.kpeople.be.ActivityState toBE(
    		final ActivityState in) {

        it.webscience.kpeople.be.ActivityState out =
            new it.webscience.kpeople.be.ActivityState();

        out.setIdActivityState(in.getIdActivityState());
        out.setName(in.getName());
        out.setDescription(in.getDescription());
        
        DataTraceClassConverter.toBE(in, out);
        return out;
    }
    
    
    
    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti ActivityState Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.ActivityState>
        toBE(final ActivityState[] in) {

        List<it.webscience.kpeople.be.ActivityState> out =
            new ArrayList<it.webscience.kpeople.be.ActivityState>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }
    
    
    
    /**
     * Converte da List Process BE a array ActivityState Service.
     * @param in List ActivityState BE
     * @return array ActivityState Service
     */
    public static ActivityState[] toService(
            final List<it.webscience.kpeople.be.ActivityState> in) {

    	ActivityState[] out = new ActivityState[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }
    
    /**
     * Converte oggetto ActivityState BE in Service.
     * @param in oggetto ActivityState BE
     * @return oggetto ActivityState Service
     */
    public static ActivityState toService(
            final it.webscience.kpeople.be.ActivityState in) {
    	ActivityState out = new ActivityState();

    	out.setIdActivityState(in.getIdActivityState());
    	out.setName(in.getName());
    	out.setDescription(in.getDescription());
        
        DataTraceClassConverter.toService(in, out);

        return out;
    }
    
}
