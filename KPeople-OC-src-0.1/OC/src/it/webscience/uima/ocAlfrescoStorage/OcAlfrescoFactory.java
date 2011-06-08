package it.webscience.uima.ocAlfrescoStorage;

import it.webscience.uima.annotators.storage.OcStorageFactory;
import it.webscience.uima.ocStorageAbstract.OcStorageAdapterAgent;

/** factory specifico per lo storage Alfresco.
 * @author dellanna
 *
 */
public class OcAlfrescoFactory implements OcStorageFactory {

    @Override
    public final OcStorageAdapterAgent createStorageAdapterAgent() {
        return new OcDefaultAlfrescoAdapterAgent();
    }
}
