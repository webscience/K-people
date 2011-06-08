package it.webscience.kpeople.dal.ocOpenLdap;

import it.webscience.kpeople.dal.Singleton;
import it.webscience.kpeople.dal.ocLdapAbstract.OcLdapAdapterAgent;
import it.webscience.kpeople.dal.ocLdapAbstract.OcLdapUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

/** Gestisce l'interrogazione della cache o del server LDAP.
 * @author dellanna
 */
public class OcDefaultOpenLdapAdapterAgent extends OcLdapAdapterAgent {

    /**
     * mappa dei punteggi associati ai risultati.
     * key: distinguishedName
     * value: punteggio associato
     */
    private Hashtable<String, Double> punteggiDn;

    /** istanza Singleton. */
    private Singleton singleton;

    /** Ehcache. */
    private Cache cache;

    /** logger. */
    private Logger logger;

    /** costruttore. */
    public OcDefaultOpenLdapAdapterAgent() {
        super();

        punteggiDn = new Hashtable<String, Double>();
        cache = OcOpenLdapCache.getInstance().getCacheQuery();
        singleton = Singleton.getInstance();
        logger = Logger.getLogger(this.getClass().getName());
    }

    /** restituisce in context per la connessione al server LDAP.
     * @return oggetto DirContext
     * @throws NamingException if a naming exception is encountered
     */
    public final DirContext getDirContext() throws NamingException {
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

    /** interroga la cache o il server ldap su tutti i parametri presenti.
     * @param ocLdapUser oggetto di partenza per l'interrogazione
     * @return risultato della query
     * @throws NamingException if a naming exception is encountered
     */
    @Override
    public final List<OcLdapUser> search(final OcLdapUser ocLdapUser)
            throws NamingException {
        logger.debug("*** search: BEGIN ***");

        logger.debug("Parametro in ingresso username: "
                + ocLdapUser.getUsername());

        searchUsername(ocLdapUser.getUsername());
        


//      ottiene l'elenco dei dn con punteggio massimo
        List<String> bestResults = getBestResults();

//      popolo la lista di OcLdapUser da restituire all'annotator
        List<OcLdapUser> out = new ArrayList<OcLdapUser>();
        for (String dn : bestResults) {
            out.add(getOcLdapUser(dn));
        }

        logger.debug("*** search: END ***");

        return out;
    }

    /** analizza i risultati ottenuti e ritorna quelli con punteggio maggiore.
     * @return distinguishedName dei risultati aventi il punteggio maggiore
     */
    private List<String> getBestResults() {
        logger.debug("Analisi del risultato migliore");

        List<String> bestResult = new ArrayList<String>();

        double bestPunteggio = 0;

//      recupero il punteggio massimo
        for (String dn : punteggiDn.keySet()) {
            double punteggio = punteggiDn.get(dn);
            if (punteggiDn.get(dn) > bestPunteggio) {
                bestPunteggio = punteggio;
            }
        }
        logger.debug("Punteggio massimo: " + bestPunteggio);

//      nel caso di punteggio > 0, seleziono i risultati
        if (bestPunteggio > 0) {
            for (String dn : punteggiDn.keySet()) {
                double punteggio = punteggiDn.get(dn);
                if (punteggio == bestPunteggio) {
                    bestResult.add(dn);
                }
            }

            logger.debug("Dn estratti: "
                    + bestResult.toString());
        } else {
            logger.debug("Nessun risultato trovato");
        }

        return bestResult;
    }

    /** recupera i risultati associati al name.
     * @param name name da cercare
     * @throws NamingException if a naming exception is encountered
     */
    private void searchName(final String name) throws NamingException {
        String[] split = name.split(" ");
        for (int i = 0; i < split.length; i++) {
            String val = split[i];

            double punteggioNameJolly = Double.parseDouble(
                    singleton.getProperty(
                            "openldap.punteggi.name"));

            search(
               "(&(cn=*{0}*))",
               new Object[]{val},
               "cn=" + val,
               punteggioNameJolly);
        }
    }

    /** recupera i risultati associati all'username.
     * @param username username da cercare
     * @throws NamingException if a naming exception is encountered
     */
    private void searchUsername(final String username) throws NamingException {
        String user = username;

        if (user != null) {
    //      rimozione del nome del dominio (es. ws\)
            if (user.indexOf("\\") != -1) {
                user = user.substring(user.indexOf("\\") + 1, user.length());
            }

            search(
               "(&(sAMAccountName={0}))",
               new Object[]{user},
               "sAMAccountName=" + user,
               1);
        } else {

            search(
                    "(&(sAMAccountName=*)(objectCategory=person))",
                    new Object[]{},
                    "*",
                    1);
        }
    }

    /** recupera l'oggetto OcLdapUser a partire dal dn.
     * @param dn dn da cercare
     * @return OcLdapUser oggetto OcLdapUser associato al dn
     * @throws NamingException if a naming exception is encountered
     */
    private OcLdapUser getOcLdapUser(final String dn)
            throws NamingException {
        String cacheQuery = "distinguishedName=" + dn;

        OcLdapUser ocLdapUser = null;

        Element element = cache.get(cacheQuery);
        if (element != null) {
            logger.debug("cache hit");

            ocLdapUser = (OcLdapUser) element.getObjectValue();
        } else {
            NamingEnumeration<SearchResult> answer =
                ldapSearch("(&(distinguishedName={0}))", new Object[]{dn});

            Attributes attributes = answer.nextElement().getAttributes();

            if (attributes.get("mail") != null) {
                NamingEnumeration<?> mailEn = attributes.get("mail").getAll();
                String mail = mailEn.nextElement().toString();
    
                NamingEnumeration<?> cnEn = attributes.get("cn").getAll();
                String cn = cnEn.nextElement().toString();
    
                NamingEnumeration<?> usernameEn =
                    attributes.get("sAMAccountName").getAll();
                String username = usernameEn.nextElement().toString();
    
                ocLdapUser = new OcLdapUser();
                ocLdapUser.setDn(dn);
                ocLdapUser.setEmail(mail);
                ocLdapUser.setName(cn);
                ocLdapUser.setUsername(username);
    
    //          inserisco il nuovo valore nella cache
                cache.put(new Element(cacheQuery, ocLdapUser));
            }
        }

        return ocLdapUser;
    }

    /** recupera i risultati associati alla email.
     * @param email email da cercare
     * @throws NamingException if a naming exception is encountered
     */
    private void searchEmail(final String email) throws NamingException {
//      1. ricerca senza carattery jolly
        double punteggioEmail = Double.parseDouble(
                singleton.getProperty("openldap.punteggi.email"));

        search(
           "(&(mail={0}))",
           new Object[]{email},
           "mail=" + email,
           punteggioEmail);

//      2. ricerca con carattery jolly
        double punteggioEmailJolly = Double.parseDouble(
                singleton.getProperty("openldap.punteggi.email.jolly"));

        search(
           "(&(mail=*{0}*))",
           new Object[]{email},
           "mail*=" + email,
           punteggioEmailJolly);
    }

    /** esegue la ricerca su cache/server ed aumenta i punteggi.
     * @param filterExpr the filter expression to use for the search
     * @param filterArgs the array of arguments to substitute for the variables
     *          in filterExpr
     * @param cacheQuery query per l'interrogazione della cache
     * @param punteggio punteggi da assegnare ai risultati della ricerca
     * @throws NamingException if a naming exception is encountered
     */
    @SuppressWarnings("unchecked")
    private void search(
            final String filterExpr,
            final Object[] filterArgs,
            final String cacheQuery,
            final double punteggio) throws NamingException {
        List<String> results = null;

        Date start = new Date();

        Element element = cache.get(cacheQuery);
        if (element != null) {
            logger.debug("cache hit");

            results = (List<String>) element.getObjectValue();
        } else {
            logger.debug("Interrogazione LDAP");

            results = queryLdapServer(filterExpr, filterArgs);

//          inserisco il nuovo valore nella cache
            cache.put(new Element(cacheQuery, results));
        }

        long time = new Date().getTime() - start.getTime();
        logger.debug("Tempo elaborazione: " + time + "ms");

        for (String result : results) {
            addRisultato(result, punteggio);
        }

        logger.debug("Punteggio: " + punteggio);
        logger.debug("Ricerca: " + cacheQuery);
        logger.debug("Risultati: " + results.toString());
        logger.debug("");
    }

    /** aggiunge il risultato alla tabella dei punteggi.
     * @param key chiave/risultato
     * @param punteggio punteggio associato
     */
    private void addRisultato(
            final String key,
            final Double punteggio) {
        if (punteggiDn.containsKey(key)) {
            double newPunteggio = punteggiDn.get(key) + punteggio;
            punteggiDn.put(key, newPunteggio);
        } else {
            punteggiDn.put(key, punteggio);
        }
    }

    /** interroga il server LDAP.
     * @param filterExpr the filter expression to use for the search
     * @param filterArgs the array of arguments to substitute for the variables
     *          in filterExpr
     * @return elenco di distinguishedName
     * @throws NamingException if a naming exception is encountered
     */
    private List<String> queryLdapServer(
            final String filterExpr,
            final Object[] filterArgs)
            throws NamingException {

//      distinguishedName - risultati della ricerca
        List<String> out = new ArrayList<String>();

        NamingEnumeration<SearchResult> answer =
            ldapSearch(filterExpr, filterArgs);

        while (answer.hasMoreElements()) {
            SearchResult sr = answer.nextElement();
            Attributes attributes = sr.getAttributes();
            Attribute attr = attributes.get("distinguishedName");

            NamingEnumeration<?> vals = attr.getAll();
            while (vals.hasMoreElements()) {
                Object val = (Object) vals.nextElement();
                out.add(val.toString());
            }
        }

        return out;
    }

    /**
     * @param filterExpr the filter expression to use for the search
     * @param filterArgs the array of arguments to substitute for the
     *           variables in filterExpr
     * @return risultato della ricerca su ldap
     * @throws NamingException if a naming exception is encountered
     */
    private NamingEnumeration<SearchResult> ldapSearch(
            final String filterExpr,
            final Object[] filterArgs) throws NamingException {
        String contextName = Singleton.getInstance().
                getProperty("openldap.context-name");

        SearchControls ctls = new SearchControls();
        ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> answer = getDirContext().search(
                contextName,
                filterExpr,
                filterArgs,
                ctls);
        return answer;
    }
}
