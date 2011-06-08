package it.webscience.uima.ocOpenLdap;

import java.io.InputStream;

import org.apache.log4j.Logger;

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
    
    /** logger. */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /** costruttore. */
    private OcOpenLdapCache() {
        try {
            InputStream ehcache = this.getClass().getClassLoader().
                getResourceAsStream("ehcache.xml");
            cacheManager = new CacheManager(ehcache);
        } catch (CacheException e) {
            logger.error(e.getMessage());
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
