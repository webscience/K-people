package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.PatternFilter;


/**
 * Classe di conversione tra la classe PatternFilterConverter BE e 
 * PatternFilterConverter Service.
 */
public final class PatternFilterConverter {

	/**
     * Costruttore privato.
     */
    private PatternFilterConverter() {
    }

    /**
     * Converte da Service a BE.
     * @param in oggetto PatternFilter Service
     * @return oggetto PatternFilter BE
     */
    public static it.webscience.kpeople.be.PatternFilter toBE(
            final PatternFilter in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.be.PatternFilter out =
            new it.webscience.kpeople.be.PatternFilter();

        out.setHpmProcessId(in.getHpmProcessId());
        out.setPatternTypeId(in.getPatternTypeId());
        out.setPatternStateId(in.getPatternStateId());
        out.setHpmOwnerUserId(in.getHpmOwnerUserId());
        out.setHpmUserId(in.getHpmUserId());
        out.setFreeText(in.getFreeText());
        out.setSort(SortCriteriaConverter.toBE(in.getSort()));
        
        return out;
    }
}
