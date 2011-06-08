package it.webscience.kpeople.util;

import java.util.Properties;

import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.Singleton;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;

/**
 * Classe di utility di supporto per ldap.
 * @author bolognese
 */
public class LdapUtils {
    /** logger. */
    private static Logger logger;

    /**
     * ritorna una enumeration in base alla query di ricerca ldap.
     * @param filterExpr
     *            the filter expression to use for the search
     * @param filterArgs
     *            the array of arguments to substitute for the variables in
     *            filterExpr
     * @return risultato della ricerca su ldap
     * @throws NamingException
     *             if a naming exception is encountered
     */
    public static NamingEnumeration<SearchResult> ldapSearch(
            final String filterExpr, final Object[] filterArgs)
            throws NamingException {
        String contextName =
                Singleton.getInstance().getProperty("openldap.context-name");

        SearchControls ctls = new SearchControls();
        ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> answer =
                getDirContext().search(contextName, filterExpr, filterArgs,
                        ctls);
        return answer;
    }

    /**
     * restituisce in context per la connessione al server LDAP.
     * @return oggetto DirContext
     * @throws NamingException
     *             if a naming exception is encountered
     */
    private static DirContext getDirContext() throws NamingException {
        Singleton singleton = Singleton.getInstance();

        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");

        env.put(Context.PROVIDER_URL,
                singleton.getProperty("openldap.provider-url"));

        env.put(Context.SECURITY_PRINCIPAL,
                singleton.getProperty("openldap.security-principal"));

        env.put(Context.SECURITY_CREDENTIALS,
                singleton.getProperty("openldap.security-credentials"));

        return new InitialDirContext(env);
    }

    /**
     * ritorna uno user di tipo user be dalla enumeration ldap.
     * @param answer
     *            the enum from ldap
     * @return user be User
     * @throws NamingException
     *             if a naming exception is encountered
     */
    public static User createUserBefromLdap(
            final NamingEnumeration<SearchResult> answer)
            throws NamingException {
        User user = null;

        if (answer.hasMoreElements()) {
            user = new User();

            SearchResult sr = answer.nextElement();
            Attributes attributes = sr.getAttributes();
            user.setScreenName(getLdapAttributeValue(attributes, "cn"));
            user.setUsername(getLdapAttributeValue(attributes, "mail"));
            user.setAccount(getLdapAttributeValue(attributes,
                    "sAMAccountName"));
            user.setHpmUserId(getLdapAttributeValue(attributes, "mail"));
            user.setFirstName(getLdapAttributeValue(attributes, "givenName"));
            user.setLastName(getLdapAttributeValue(attributes, "sn"));
        }

        return user;

    }

    /**
     * @param attributes
     *            enum di attributi ldap user
     * @param attrKey
     *            chiave del l'attribute da cercare
     * @return value dell'attributo
     * @throws NamingException
     *             if a naming exception is encountered
     */
    private static String getLdapAttributeValue(final Attributes attributes,
            final String attrKey) throws NamingException {
        String value = "";
        Attribute attr = attributes.get(attrKey);
        NamingEnumeration<?> vals = attr.getAll();
        while (vals.hasMoreElements()) {
            Object val = (Object) vals.nextElement();
            value = val.toString();
        }

        return value;
    }

}
