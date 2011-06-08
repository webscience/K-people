package it.webscience.kpeople.web.portlet.activities.util;

import it.webscience.kpeople.service.activity.ActivityServiceStub.PatternMetadata;

/**
 * classe di util per la gestione delle attività.
 * @author filieri
 */
public class ActivityUtil {

    /**
     * @param pKeyname
     *            chiave del metadato da cercare.
     * @param activityMetadata
     *            insieme dei metadati associati all'attività.
     * @return il valore del metadato.
     */
    public static final String getActivityMetadataValueByKeyname(
            final PatternMetadata[] activityMetadata, final String pKeyname) {

        String keyName = pKeyname;
        String keyValue = null;

        if (activityMetadata != null) {
            for (int i = 0; i < activityMetadata.length; i++) {
                PatternMetadata meta = activityMetadata[i];
                if (meta.getKeyname().equals(keyName)) {
                    keyValue = meta.getValue();
                    break;
                }
            }
        }

        return keyValue;
    }

}
