package it.webscience.uima.annotators.ldap;

import it.webscience.uima.ocLdapAbstract.OcLdapAdapterAgent;

/** abstract factory pattern.
 */
public interface OcLdapFactory {
    /** factory per lo storage agent.
     * @return implementazione dello OcLdapAdapterAgent
     */
    OcLdapAdapterAgent createLdapAdapterAgent();
}
