package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.PatternState;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di conversione tra la classe  PatternState BE e  PatternState Service.
 */
public final class PatternStateConverter {

	/**
     * Costruttore privato.
     */
    private PatternStateConverter() {
    }
    
    /**
     * Converte da Service a BE.
     * @param in oggetto PatternState Service
     * @return oggetto PatternState BE
     */
    public static it.webscience.kpeople.be.PatternState toBE(
    		final PatternState in) {

        it.webscience.kpeople.be.PatternState out =
            new it.webscience.kpeople.be.PatternState();

        out.setIdPatternState(in.getIdPatternState());
        out.setIdPatternType(in.getIdPatternType());
        out.setState(in.getState());
        out.setDescription(in.getDescription());
        
        DataTraceClassConverter.toBE(in, out);

        return out;
    }

    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti PatternState Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.PatternState>
        toBE(final PatternState[] in) {

        List<it.webscience.kpeople.be.PatternState> out =
            new ArrayList<it.webscience.kpeople.be.PatternState>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }

    /**
     * Converte da List PatternState BE a array PatternState Service.
     * @param in List PatternState BE
     * @return array PatternState Service
     */
    public static PatternState[] toService(
            final List<it.webscience.kpeople.be.PatternState> in) {

    	PatternState[] out = new PatternState[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }

    /**
     * Converte oggetto PatternState BE in Service.
     * @param in oggetto PatternState BE
     * @return oggetto PatternState Service
     */
    public static PatternState toService(
            final it.webscience.kpeople.be.PatternState in) {
    	PatternState out = new PatternState();

    	out.setIdPatternState(in.getIdPatternState());
    	out.setIdPatternType(in.getIdPatternType());
        out.setState(in.getState());
        out.setDescription(in.getDescription());

        return out;
    }
}
