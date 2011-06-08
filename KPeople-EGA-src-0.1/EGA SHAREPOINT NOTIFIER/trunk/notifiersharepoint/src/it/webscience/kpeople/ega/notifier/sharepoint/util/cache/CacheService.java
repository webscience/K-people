package it.webscience.kpeople.ega.notifier.sharepoint.util.cache;

import java.io.IOException;

import it.webscience.kpeople.ega.notifier.sharepoint.util.configuration.SharepointConfigurationManager;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;

/**
 * @author filieri
 */
public final class CacheService {

    /**
     * Istanza della classe CacheService per
     *  l'implementazione del pattern Singleton.
     */
    private static volatile CacheService singleton;

    /**
     * Istanza della cache per la gestione degli eventi di cancellazione dei
     * documenti.
     */
    private static Cache cacheDelete;

    /**
     * Istanza della cache per la gestione degli eventi di inserimento o
     * modifica dei documenti.
     */
    private static Cache cacheModify;
    
    private static CacheManager cachemanager;

    private CacheService() {
        BundleContext context = FrameworkUtil.getBundle(
                SharepointConfigurationManager.class).getBundleContext();
        try {
            cachemanager = new CacheManager(context.getBundle()
                    .getResource("/configuration/ehcache.xml").openStream());
        } catch (CacheException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cacheModify = cachemanager.getCache("sharepointModifyCache");
        cacheDelete = cachemanager.getCache("sharepointDeleteCache");
    }

    public static CacheService getInstance() {
        if (singleton == null) {
            synchronized (CacheService.class) {
                singleton = new CacheService();
            }
        }
        return singleton;
    }

    /**
     * @return the cacheDelete_
     */
    public static Cache getCacheDelete() {
        return cacheDelete;
    }

    /**
     * @return the cacheModify_
     */
    public static Cache getCacheModify() {
        return cacheModify;
    }

}
