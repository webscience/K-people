package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.PatternMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe PatternMetadata BE e 
 * PatternMetadata Service.
 */
public final class PatternMetadataConverter {

	/**
     * Costruttore privato.
     */
    private PatternMetadataConverter() {
    }
    
    /**
     * Converte da Service a BE.
     * @param in oggetto PatternMetadata Service
     * @return oggetto PatternMetadata BE
     */
    public static it.webscience.kpeople.be.PatternMetadata toBE(
    		final PatternMetadata in) {

        it.webscience.kpeople.be.PatternMetadata out =
            new it.webscience.kpeople.be.PatternMetadata();

        out.setIdPatternMetadata(in.getIdPatternMetadata());
        out.setIdPattern(in.getIdPattern());
        out.setKeyname(in.getKeyname());
        out.setValue(in.getValue());
        out.setActivitiProcessMetadata(in.isActivitiProcessMetadata());
        
        DataTraceClassConverter.toBE(in, out);

        return out;
    }
    
    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti PatternMetadata Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.PatternMetadata>
        toBE(final PatternMetadata[] in) {

        List<it.webscience.kpeople.be.PatternMetadata> out =
            new ArrayList<it.webscience.kpeople.be.PatternMetadata>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }

    /**
     * Converte da List PatternMetadata BE a array PatternMetadata Service.
     * @param in List PatternMetadata BE
     * @return array PatternMetadata Service
     */
    public static PatternMetadata[] toService(
            final List<it.webscience.kpeople.be.PatternMetadata> in) {

    	PatternMetadata[] out = new PatternMetadata[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }

    /**
     * Converte oggetto PatternMetadata BE in Service.
     * @param in oggetto PatternMetadata BE
     * @return oggetto PatternMetadata Service
     */
    public static PatternMetadata toService(
            final it.webscience.kpeople.be.PatternMetadata in) {
    	PatternMetadata out = new PatternMetadata();

    	out.setIdPatternMetadata(in.getIdPatternMetadata());
    	out.setIdPattern(in.getIdPattern());
    	out.setKeyname(in.getKeyname());
    	out.setValue(in.getValue());
        out.setActivitiProcessMetadata(in.isActivitiProcessMetadata());

        return out;
    }
}
