package it.webscience.uima.ocOpenLdap;

import it.webscience.uima.annotators.ldap.OcLdapFactory;
import it.webscience.uima.ocLdapAbstract.OcLdapAdapterAgent;

/** factory specifico per le interrogazioni OpenLDAP.
 * @author dellanna
 */
public class OcOpenLdapFactory implements OcLdapFactory {

    @Override
    public final OcLdapAdapterAgent createLdapAdapterAgent() {
        return new OcDefaultOpenLdapAdapterAgent();
    }
}
