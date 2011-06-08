package it.webscience.kpeople.be;

import java.util.ArrayList;
import java.util.List;

/**
 * @author depascalis Classe rappresentante il metadataset relativo ai processi
 */
public class ProcessMetadataSet {

    /** Elenco dei CompoundMetadati associati al MetadataSet. */
    public List<ProcessCompoundMetadata> cmpMetadataList;

    /** Elenco delle rule associate al MetadataSet. */
    private List<ProcessRule> ruleSetList;

    /** Costruttore. */
    public ProcessMetadataSet() {
        cmpMetadataList = new ArrayList<ProcessCompoundMetadata>();
        ruleSetList = new ArrayList<ProcessRule>();
    }

    /**
     * @param in
     *            Lista dei ProcessCompoundMetadata.
     */
    public final void
            setCmpMetadataList(final List<ProcessCompoundMetadata> in) {
        this.cmpMetadataList = in;
    }

    /**
     * @return la lista dei ProcessCompoundMetadata.
     */
    public final List<ProcessCompoundMetadata> getCmpMetadataList() {
        return cmpMetadataList;
    }
}
