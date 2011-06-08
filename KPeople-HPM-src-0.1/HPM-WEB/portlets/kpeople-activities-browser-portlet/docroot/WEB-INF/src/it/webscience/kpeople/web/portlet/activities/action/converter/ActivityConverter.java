package it.webscience.kpeople.web.portlet.activities.action.converter;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe di conversione tra la classe Document BE e Document Service.
 */
public final class ActivityConverter {

    /**
     * Costruttore privato.
     */
    private ActivityConverter() {
    }

    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti Document Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.service.activity.ActivityServiceStub.Activity>
        toBE(final it.webscience.kpeople.service.activity.ActivityServiceStub.Activity[] in) {

        List<it.webscience.kpeople.service.activity.ActivityServiceStub.Activity> out =
            new ArrayList<it.webscience.kpeople.service.activity.ActivityServiceStub.Activity>();

        if (in != null) {
            for (int i = 0; i < in.length; i++) {
                out.add(in[i]);
            }
        }

        return out;
    }
}
