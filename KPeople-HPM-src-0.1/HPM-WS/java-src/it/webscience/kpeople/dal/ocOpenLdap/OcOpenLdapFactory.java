package it.webscience.kpeople.dal.ocOpenLdap;

import it.webscience.kpeople.dal.ocLdapAbstract.OcLdapAdapterAgent;


/** factory specifico per le interrogazioni OpenLDAP.
 * @author dellanna
 */
public class OcOpenLdapFactory implements OcLdapFactory {

    @Override
    public final OcLdapAdapterAgent createLdapAdapterAgent() {
        return new OcDefaultOpenLdapAdapterAgent();
    }
}
