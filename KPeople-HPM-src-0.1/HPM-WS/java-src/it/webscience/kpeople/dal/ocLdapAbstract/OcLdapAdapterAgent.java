package it.webscience.kpeople.dal.ocLdapAbstract;

import java.util.List;

import javax.naming.NamingException;

/** agent per l'interrogazione del server LDAP.
 * @author dellanna
 */
public abstract class OcLdapAdapterAgent {

    /** invia la chiamata allo storage.
     * @param ocLdapUser cas da processare
     * @return esito dell'interrogazione. <i>null</i> se nessun risultato
     * @throws NamingException if a naming exception is encountered
     */
    public abstract List<OcLdapUser> search(final OcLdapUser ocLdapUser)
        throws NamingException;
}
