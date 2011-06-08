package it.webscience.uima.annotators.storage;

import it.webscience.uima.ocStorageAbstract.OcStorageAdapterAgent;

/** absratct factory pattern.
 */
public interface OcStorageFactory {
    /** factory per lo storage agent.
     * @return implementazione dello OcStorageAdapterAgent
     */
    OcStorageAdapterAgent createStorageAdapterAgent();
}
