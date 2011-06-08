package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.SortCriteria;

/**
 * Classe di conversione tra SortCriteria BE e SortCriteria Service.
 */
public final class SortCriteriaConverter {

    /**
     * Costruttore privato.
     */
    private SortCriteriaConverter() {
    }

    /**
     * Converte da Service a BE.
     * @param in oggetto SortCriteria Service
     * @return oggetto SortCriteria BE
     */
    public static it.webscience.kpeople.be.SortCriteria toBE(
            final SortCriteria in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.be.SortCriteria out =
            new it.webscience.kpeople.be.SortCriteria();

        out.setFieldName(in.getFieldName());
        out.setOrder(in.getOrder());

        return out;
    }
}
