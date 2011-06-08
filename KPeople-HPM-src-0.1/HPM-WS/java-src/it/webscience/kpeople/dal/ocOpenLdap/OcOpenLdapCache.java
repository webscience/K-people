package it.webscience.kpeople.dal.ocOpenLdap;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;

/**
 * @author dellanna
 */
public final class OcOpenLdapCache {

    /**
     * Istanza della classe CacheService per
     *  l'implementazione del pattern Singleton.
     */
    private static volatile OcOpenLdapCache singleton;

    /** Istanza della cache per la gestione delle query. */
    private static Cache cacheQuery;

    /** cache manager. */
    private static CacheManager cacheManager;

    /** costruttore. */
    private OcOpenLdapCache() {
        try {
            InputStream ehcache = this.getClass().getClassLoader().
                getResourceAsStream("ehcache.xml");
            cacheManager = new CacheManager(ehcache);
        } catch (CacheException e) {
            e.printStackTrace();
        }

        cacheQuery = cacheManager.getCache("ldapQueryCache");
    }

    /** ottiene l'istanza del servizio.
     * @return istanza del servizio
     */
    public static OcOpenLdapCache getInstance() {
        if (singleton == null) {
            synchronized (OcOpenLdapCache.class) {
                singleton = new OcOpenLdapCache();
            }
        }
        return singleton;
    }

    /**
     * @return the cacheQuery
     */
    public Cache getCacheQuery() {
        return cacheQuery;
    }
}
