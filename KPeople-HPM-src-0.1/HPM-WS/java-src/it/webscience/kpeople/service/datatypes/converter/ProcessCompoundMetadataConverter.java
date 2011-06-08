package it.webscience.kpeople.service.datatypes.converter;

import java.util.ArrayList;
import java.util.List;

import it.webscience.kpeople.service.datatypes.ProcessCompoundMetadata;

/**
 * @author depascalis
 * Classe per la conversione della classe ProcessCompoundMetadata Service
 * in ProcessCompoundMetadata be e viceversa.
 */
public final class ProcessCompoundMetadataConverter {

    /**
     * Costruttore privato della classe.
     */
    private ProcessCompoundMetadataConverter() {
    }

    /**
     * @param in ProcessCompoundMetadata service.
     * @return ProcessCompoundMetadata be.
     */
    private static it.webscience.kpeople.be.ProcessCompoundMetadata toBE(
            final ProcessCompoundMetadata in) {
        it.webscience.kpeople.be.ProcessCompoundMetadata out =
            new it.webscience.kpeople.be.ProcessCompoundMetadata();
        out.setKey(in.getKey());
        out.setDescription(in.getDescription());
        if (in.getMetadataList() != null) {
            out.setMetadataList(ProcessMetadataConverter.toBE(in
                    .getMetadataList()));
        }
        return out;
    }

    /**
     * @param in Array di ProcessCompoundMetadata Service.
     * @return List di ProcessCompoundMetadata be.
     */
    public static List<it.webscience.kpeople.be.ProcessCompoundMetadata> toBE(
            final ProcessCompoundMetadata[] in) {
        List<it.webscience.kpeople.be.ProcessCompoundMetadata> out =
            new ArrayList<it.webscience.kpeople.be.ProcessCompoundMetadata>();
        for (int i = 0; i < in.length; i++) {
            out.add(toBE(in[i]));
        }
        return out;
    }

    /**
     * @param in Array di ProcessCompoundMetadata service
     * @return Lista di ProcessCompoundMetadata be
     */
    public static it.webscience.kpeople.service.datatypes.
    ProcessCompoundMetadata[]
            toService(final List<it.webscience.kpeople.be.
                    ProcessCompoundMetadata> in) {
        it.webscience.kpeople.service.datatypes.ProcessCompoundMetadata[] out =
            new it.webscience.kpeople.service.datatypes.
            ProcessCompoundMetadata[in.size()];
        for (int i = 0; i < in.size(); i++) {
            out [i] = toService(in.get(i));
        }
        return out;
    }

    /**
     * @param in ProcessCompoundMetadata Service
     * @return ProcessCompoundMetadata be.
     */
    private static it.webscience.kpeople.service.datatypes.
    ProcessCompoundMetadata
    toService(final it.webscience.kpeople.be.ProcessCompoundMetadata in) {
        it.webscience.kpeople.service.datatypes.ProcessCompoundMetadata out =
            new it.webscience.kpeople.service.datatypes.
            ProcessCompoundMetadata();
        out.setKey(in.getKey());
        out.setDescription(in.getDescription());
        out.setMetadataList(ProcessMetadataConverter.
                toService(in.getMetadataList()));
        return out;
    }
}
