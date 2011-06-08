package it.webscience.kpeople.web.portlet.globalsearch.action.converter;

import it.webscience.kpeople.service.cross.GlobalSearchServiceStub.KPeopleGenericDTO;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe di conversione tra la classe Process BE e Process Service.
 */
public final class GenericConverter {

    /**
     * Costruttore privato.
     */
    private GenericConverter() {
    }

    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti Document Service
     * @return Lista BE
     */
    public static List<KPeopleGenericDTO>
        toBE(final KPeopleGenericDTO[] in) {

        List<KPeopleGenericDTO> out =
            new ArrayList<KPeopleGenericDTO>();

        if (in != null) {
            for (int i = 0; i < in.length; i++) {
                out.add(in[i]);
            }
        }

        return out;
    }
}
