package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.EventFilter;



/**
 * @author depascalis
 * Classe di conversione tra la classe EventFilter BE e la classe
 * EventFilter di Service.
 */
public final class EventFilterConverter {

    /**
     * Costruttore privato.
     */
    private EventFilterConverter() {

    }

    /**
     * Converte da Service a BE.
     * @param in oggetto EventFilter service.
     * @return oggetto EventFilter BE.
     */
    public static it.webscience.kpeople.be.EventFilter toBE(
            final EventFilter in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.be.EventFilter out =
            new it.webscience.kpeople.be.EventFilter();

        out.setHpmProcessId(in.getHpmProcessId());
        out.setPatternType(in.getPatternType());
        out.setHpmPatternId(in.getHpmPatternId());
        out.setHpmUserId(in.getHpmUserId());
        out.setFreeText(in.getFreeText());
        out.setSort(SortCriteriaConverter.toBE(in.getSort()));

        return out;


    }
}
