package it.webscience.kpeople.dal.ocOpenLdap;

import it.webscience.kpeople.dal.ocLdapAbstract.OcLdapAdapterAgent;


/** abstract factory pattern.
 */
public interface OcLdapFactory {
    /** factory per lo storage agent.
     * @return implementazione dello OcLdapAdapterAgent
     */
    OcLdapAdapterAgent createLdapAdapterAgent();
}
