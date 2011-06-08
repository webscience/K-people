package it.webscience.kpeople.web.portlet.process.action.converter;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe di conversione tra la classe Process BE e Process Service.
 */
public final class ProcessConverter {

    /**
     * Costruttore privato.
     */
    private ProcessConverter() {
    }

    /**
     * Converte da array Service a List BE.
     * @param in array di oggetti Document Service
     * @return Lista BE
     */
    public static List<it.webscience.kpeople.service.process.ProcessServiceStub.Process>
        toBE(final it.webscience.kpeople.service.process.ProcessServiceStub.Process[] in) {

        List<it.webscience.kpeople.service.process.ProcessServiceStub.Process> out =
            new ArrayList<it.webscience.kpeople.service.process.ProcessServiceStub.Process>();

        if (in != null) {
            for (int i = 0; i < in.length; i++) {
                out.add(in[i]);
            }
        }

        return out;
    }
}
