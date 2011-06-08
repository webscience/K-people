package it.webscience.kpeople.service.datatypes.converter;

import java.util.ArrayList;
import java.util.List;

import it.webscience.kpeople.service.datatypes.ProcessExtraData;

/**
 * @author depascalis Classe per convertire la classe ProcessExtraData service
 *         nella classe ProcessExtraData be e viceversa.
 */
public final class ProcessExtraDataConverter {
    /**
     * Costruttore privato della classe.
     */
    private ProcessExtraDataConverter() {
    }

    /**
     * @param in
     *            Lista di ProcessExtraData be.
     * @return Array ProcessExtraData service.
     */
    public static List<it.webscience.kpeople.be.ProcessExtraData> toBE(
            final ProcessExtraData[] in) {
        List<it.webscience.kpeople.be.ProcessExtraData> out = null;
        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }
        return out;
    }

    /**
     * @param in
     *            ProcessExtraData service.
     * @return ProcessExtraData be.
     */
    private static it.webscience.kpeople.be.ProcessExtraData toBE(
            final ProcessExtraData in) {
        it.webscience.kpeople.be.ProcessExtraData out =
            new it.webscience.kpeople.be.ProcessExtraData();
        out.setKey(in.getKey());
        out.setValue(in.getValue());
        return out;
    }

    /**
     * @param in
     *            Array di ProcessExtraData service.
     * @return List di ProcessExtraData be.
     */
    public static it.webscience.kpeople.service.datatypes.ProcessExtraData[]
            toService(final List<it.webscience.kpeople.be.ProcessExtraData> in) {
        it.webscience.kpeople.service.datatypes.ProcessExtraData[] out =
            new it.webscience.kpeople.service.datatypes.ProcessExtraData[in
                .size()];
        if (in != null) {

            for (int i = 0; i < in.size(); i++) {
                out[i] = toService(in.get(i));
            }
        }
        return out;
    }

    /**
     * @param in
     *            ProcessExtraData be.
     * @return ProcessExtraData service.
     */
    private static it.webscience.kpeople.service.datatypes.ProcessExtraData
            toService(final it.webscience.kpeople.be.ProcessExtraData in) {
        it.webscience.kpeople.service.datatypes.ProcessExtraData out =
            new it.webscience.kpeople.service.datatypes.ProcessExtraData();
        out.setKey(in.getKey());
        out.setValue(in.getValue());
        return out;
    }

}
