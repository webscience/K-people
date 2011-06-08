package it.webscience.kpeople.service.datatypes.converter;

import it.webscience.kpeople.service.datatypes.DocumentFilter;

/**
 * Classe di conversione tra la
 * classe DocumentFilter BE e DocumentFilter Service.
 */
public final class DocumentFilterConverter {

    /**
     * Costruttore privato.
     */
    private DocumentFilterConverter() {
    }

    /**
     * Converte da Service a BE.
     * @param in oggetto DocumentFilter Service
     * @return oggetto DocumentFilter BE
     */
    public static it.webscience.kpeople.be.DocumentFilter toBE(
            final DocumentFilter in) {

        if (in == null) {
            return null;
        }

        it.webscience.kpeople.be.DocumentFilter out =
            new it.webscience.kpeople.be.DocumentFilter();

        out.setHpmProcessId(in.getHpmProcessId());

        return out;
    }
}
