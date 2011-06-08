package it.webscience.kpeople.service.datatypes;

/**
 * @author depascalis Classe rappresentante il CompoundMetadata.
 */

public class ProcessCompoundMetadata {

    /**
     * chiave che identifica il CompoundMetadata.
     */
    private String key;

    /**
     * Descrizione associata al CompoundMetadata.
     */
    private String description;

    /**
     * Insieme dei ProcessMetadata associati al CompoundMetadata.
     */
    private ProcessMetadata[] metadataList;

    /**
     * Costruttore della classe.
     */
    public ProcessCompoundMetadata() {
        super();
    }

    /**
     * @param in valore da settare alla chiave del CompoundMetadata.
     */
    public final void setKey(final String in) {
        this.key = in;
    }

    /**
     * @param in valore da settare alla descrizione del CompoundMetadata.
     */
    public final void setDescription(final String in) {
        this.description = in;
    }

    /**
     * @return la key del CompoundMetadata
     */
    public final String getKey() {
        return key;
    }

    /**
     * @return la descrizione del CompoundMetadata
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param in ProcessMetadata da settare.
     */
    public final void setMetadataList(final ProcessMetadata[] in) {
        this.metadataList = in;
    }

    /**
     * @return metadataList
     */
    public final ProcessMetadata[] getMetadataList() {
        return metadataList;
    }

}
