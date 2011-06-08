package it.webscience.kpeople.service.datatypes.converter;

import java.util.ArrayList;
import java.util.List;

import it.webscience.kpeople.service.datatypes.Keyword;

/**
 * Classe di conversione tra la classe Keyword BE e Keyword Service.
 */
public final class KeywordConverter {

    /**
     * Costruttore privato.
     */
    private KeywordConverter() {

    }

    /**
     * Converte da Service a BE.
     * @param in oggetto Keyword Service
     * @return oggetto Keyword BE
     */
    public static it.webscience.kpeople.be.Keyword toBE(
            final Keyword in) {
        it.webscience.kpeople.be.Keyword out =
            new it.webscience.kpeople.be.Keyword();

        out.setIdKeyword(in.getIdKeyword());
        out.setKeyword(in.getKeyword());
        out.setHpmKeywordId(in.getHpmKeywordId());

        return out;
    }

    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti Keyword Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.be.Keyword> toBE(
            final Keyword[] in) {
        List<it.webscience.kpeople.be.Keyword> out =
            new ArrayList<it.webscience.kpeople.be.Keyword>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }

        return out;
    }

    /**
     * Converte da List BE a array Service.
     * @param in lista di oggetti Keyword BE
     * @return array Service
     */
    public static Keyword[] toService(
            final List<it.webscience.kpeople.be.Keyword> in) {
        Keyword[] out = new Keyword[in.size()];

        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }

        return out;
    }

    /**
     * @param in oggetto keyword di tipo BE.
     * @return oggetto keyword di tipo service.
     */
    private static Keyword toService(
            final it.webscience.kpeople.be.Keyword in) {

        if (in == null) {
            return null;
        }
        Keyword out = new Keyword();
        out.setIdKeyword(in.getIdKeyword());
        out.setKeyword(in.getKeyword());
        out.setDescription(in.getDescription());
        out.setHpmKeywordId(in.getHpmKeywordId());
        out.setValue(in.getValue());
        return out;
    }

    /**
     * Converte da BE a Service.
     * @param in oggetto Keyword BE
     * @return oggetto Keyword Service
     */
    private static Keyword toBE(final it.webscience.kpeople.be.Keyword in) {
        Keyword out = new Keyword();

        out.setIdKeyword(in.getIdKeyword());
        out.setKeyword(in.getKeyword());
        out.setHpmKeywordId(in.getHpmKeywordId());

        return out;
    }

   
}
