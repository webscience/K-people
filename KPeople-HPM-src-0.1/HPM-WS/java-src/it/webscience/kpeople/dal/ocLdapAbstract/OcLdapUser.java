package it.webscience.kpeople.dal.ocLdapAbstract;

import java.io.Serializable;

/** oggetto di passaggio nell'ldap annotator.
 * @author dellanna
 */
public class OcLdapUser implements Serializable {
    /**
     * serial.
     */
    private static final long serialVersionUID = -3865616684982908996L;

    /** username associato all'user. */
    private String  username;

    /** common name associato all'user. */
    private String name;

    /** email associata all'user. */
    private String email;

    /** distinguished name associata all'user. */
    private String dn;


    /** costruttore. */
    public OcLdapUser() {
        super();
    }

    /**
     * @return the username
     */
    public final String getUsername() {
        return username;
    }

    /**
     * @param in the username to set
     */
    public final void setUsername(final String in) {
        this.username = in;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param in the name to set
     */
    public final void setName(final String in) {
        this.name = in;
    }

    /**
     * @return the email
     */
    public final String getEmail() {
        return email;
    }

    /**
     * @param in the email to set
     */
    public final void setEmail(final String in) {
        this.email = in;
    }

    /**
     * @return the dn
     */
    public final String getDn() {
        return dn;
    }

    /**
     * @param in the dn to set
     */
    public final void setDn(final String in) {
        this.dn = in;
    }
}
