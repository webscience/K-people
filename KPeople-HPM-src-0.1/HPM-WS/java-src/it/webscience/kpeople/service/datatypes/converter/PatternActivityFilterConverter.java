package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.PatternActivityFilter;


/**
 * Classe di conversione tra la classe PatternActivityFilterConverter BE e 
 * PatternActivityFilterConverter Service.
 */
public final class PatternActivityFilterConverter {

	/**
     * Costruttore privato.
     */
    private PatternActivityFilterConverter() {
    }
    
    /**
     * Converte da Service a BE.
     * @param in oggetto PatternActivityFilter Service
     * @return oggetto PatternActivityFilter BE
     */
    public static it.webscience.kpeople.be.PatternActivityFilter toBE(
            final PatternActivityFilter in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.be.PatternActivityFilter out =
            new it.webscience.kpeople.be.PatternActivityFilter();

        out.setHpmProcessId(in.getHpmProcessId());
        out.setHpmPatternId(in.getHpmPatternId());
        out.setHpmUserId(in.getHpmUserId());
        out.setTodo(in.getTodo());
        out.setSort(SortCriteriaConverter.toBE(in.getSort()));
        

        return out;
    }
}
