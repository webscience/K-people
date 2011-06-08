package it.webscience.kpeople.web.portlet.document.action.converter;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe di conversione tra la classe Document BE e Document Service.
 */
public final class DocumentConverter {

    /**
     * Costruttore privato.
     */
    private DocumentConverter() {
    }

    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti Document Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.service.document.DocumentServiceStub.Document>
        toBE(final it.webscience.kpeople.service.document.DocumentServiceStub.Document[] in) {

        List<it.webscience.kpeople.service.document.DocumentServiceStub.Document> out =
            new ArrayList<it.webscience.kpeople.service.document.DocumentServiceStub.Document>();

        if (in != null) {
            for (int i = 0; i < in.length; i++) {
                out.add(in[i]);
            }
        }

        return out;
    }
}
