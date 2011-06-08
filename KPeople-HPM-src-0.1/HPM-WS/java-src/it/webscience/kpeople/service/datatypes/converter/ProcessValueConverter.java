package it.webscience.kpeople.service.datatypes.converter;

import java.util.ArrayList;
import java.util.List;

import it.webscience.kpeople.service.datatypes.ProcessValue;

/**
 * @author depascalis Classe per la conversione della classe ProcessValue
 *         Service in ProcessValue be e viceversa.
 */
public final class ProcessValueConverter {

    /**
     * Costruttore privato della classe.
     */
    private ProcessValueConverter() {
    }

    /**
     * @param in ProcessValue Service
     * @return ProcessValue be
     */
    private static it.webscience.kpeople.be.ProcessValue toBE(
            final ProcessValue in) {
        it.webscience.kpeople.be.ProcessValue out =
            new it.webscience.kpeople.be.ProcessValue();
        out.setValue(in.getValue());
        out.setDescription(in.getDescription());
        return out;
    }

    /**
     * @param in Array di ProcessValue Service.
     * @return List di ProcessValue be.
     */
    public static List<it.webscience.kpeople.be.ProcessValue> toBE(
            final ProcessValue[] in) {
        List<it.webscience.kpeople.be.ProcessValue> out =
            new ArrayList<it.webscience.kpeople.be.ProcessValue>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }
        return out;
    }

    /**
     * @param in Lista di ProcessValue be
     * @return Array di Processvalue service
     */
    public static it.webscience.kpeople.service.datatypes.ProcessValue[]
       toService(final List<it.webscience.kpeople.be.ProcessValue> in) {
        it.webscience.kpeople.service.datatypes.ProcessValue[] out =
            new it.webscience.kpeople.service.datatypes.ProcessValue[in.size()];
        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }

    /**
     * @param in ProcessValue be.
     * @return ProcessValue service.
     */
    private static it.webscience.kpeople.service.datatypes.ProcessValue
    toService(final it.webscience.kpeople.be.ProcessValue in) {
        it.webscience.kpeople.service.datatypes.ProcessValue out =
            new it.webscience.kpeople.service.datatypes.ProcessValue();
        out.setValue(in.getValue());
        out.setDescription(in.getDescription());
        out.setZidState(in.getZidState());
        out.setListProcessExtraData(ProcessExtraDataConverter.
                toService(in.getListProcessExtraData()));
        return out;
    }

}
