package it.webscience.kpeople.service.datatypes.converter;

import java.util.ArrayList;
import java.util.List;
import it.webscience.kpeople.service.datatypes.ProcessMetadata;

/**
 * @author depascalis Classe per convertire la classe ProcessMetadata Service in
 *         ProcessMetadata BE e viceversa.
 */
public final class ProcessMetadataConverter {

    /**
     * Costruttore privato della classe.
     */
    private ProcessMetadataConverter() {
    }

    /**
     * @param in ProcessMetadata Service.
     * @return Process Metadata be.
     */
    private static it.webscience.kpeople.be.ProcessMetadata toBE(
            final ProcessMetadata in) {
        it.webscience.kpeople.be.ProcessMetadata out =
            new it.webscience.kpeople.be.ProcessMetadata();
        out.setKey(in.getKey());
        out.setDescription(in.getDescription());
        if (in.getValueList() != null) {
            out.setValueList(ProcessValueConverter.toBE(in.getValueList()));
        }
        return out;
    }

    /**
     * @param in
     *            Array di ProcessMetadata Service.
     * @return List di ProcessMetadata be.
     */
    public static List<it.webscience.kpeople.be.ProcessMetadata> toBE(
            final ProcessMetadata[] in) {
        List<it.webscience.kpeople.be.ProcessMetadata> out =
            new ArrayList<it.webscience.kpeople.be.ProcessMetadata>();

        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }
        return out;
    }

    /**
     * @param in Lista di ProcessMetadata  be
     * @return Array di ProcessMetadata service
     */
    public static it.webscience.kpeople.service.datatypes.ProcessMetadata[]
            toService(final
                    List<it.webscience.kpeople.be.ProcessMetadata> in) {
        it.webscience.kpeople.service.datatypes.ProcessMetadata[] out =
            new it.webscience.kpeople.service.datatypes.
            ProcessMetadata[in.size()];
        for (int i = 0; i < in.size(); i++) {
            out[i] = toService(in.get(i));
        }
        return out;
    }

    /**
     * @param in ProcessMetadata be
     * @return ProcessMetadata Service
     */
    private static it.webscience.kpeople.service.datatypes.ProcessMetadata
            toService(final it.webscience.kpeople.be.ProcessMetadata in) {
        it.webscience.kpeople.service.datatypes.ProcessMetadata out =
            new it.webscience.kpeople.service.datatypes.ProcessMetadata();
        out.setKey(in.getKey());
        out.setDescription(in.getDescription());
        if (in.getValueList() != null) {
            out.setValueList(ProcessValueConverter.toService(
                    in.getValueList()));
        }
        return out;
    }
}
