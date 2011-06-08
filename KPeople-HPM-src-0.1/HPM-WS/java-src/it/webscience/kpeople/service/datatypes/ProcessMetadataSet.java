package it.webscience.kpeople.service.datatypes;



/**
 * @author depascalis Classe rappresentante il metadataset relativo ai processi.
 */
public class ProcessMetadataSet {

    /** Elenco dei CompoundMetadati associati al MetadataSet. */
    private ProcessCompoundMetadata[] cmpMetadataList;

    /** Elenco delle rule associate al MetadataSet. */
    private ProcessRule[] ruleSetList;

    /** Costruttore. */
    public ProcessMetadataSet() {
    }

    /**
     * @param in ProcessCompoundMetadata da settare.
     */
    public final void setCmpMetadataList(final ProcessCompoundMetadata[] in) {
        this.cmpMetadataList = in;
    }

    /**
     * @param in ProcessRule da settare.
     */
    public final void setRuleSetList(final ProcessRule[] in) {
        this.ruleSetList = in;
    }

    /**
     * @return ProcessCompoundMetadata.
     */
    public final ProcessCompoundMetadata[] getCmpMetadataList() {
        return cmpMetadataList;
    }

    /**
     * @return ProcessRule.
     */
    public final ProcessRule[] getRuleSetList() {
        return ruleSetList;
    }
}