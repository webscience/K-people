package it.webscience.kpeople.dal.cross;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.openrdf.repository.RepositoryException;

import it.webscience.kpeople.be.Attachment;
import it.webscience.kpeople.be.Document;
import it.webscience.kpeople.be.Event;
import it.webscience.kpeople.be.Pattern;
import it.webscience.kpeople.be.Process;
import it.webscience.kpeople.be.User;
import it.webscience.kpeople.dal.document.DocumentDAO;
import it.webscience.kpeople.dal.event.EventDAO;
import it.webscience.kpeople.dal.exception.KPeopleDAOException;
import it.webscience.kpeople.dal.pattern.PatternDAO;
import it.webscience.kpeople.dal.process.ProcessDAO;
import it.webscience.kpeople.dal.util.DataAccessConstants;

/**
 * Factory per creare tutti i tipi di dati presenti nell'HPM.
 */
public class HpmObjectFactory {

    /** Classe di logging. */
    private static final Logger LOGGER =
        Logger.getLogger(HpmObjectFactory.class);

    /**
     * Crea una business entity in base all'id e al tipo.
     * @param hpmId hpm id della business entity
     * @param type tipologia di business entity
     * @param hpmProcessId id del processo associato
     * @return business entity
     * @throws SQLException eccezione sql
     * @throws KPeopleDAOException errore durante l'esecuzione
     */
    public final Object create(final String hpmId, final String type,
            final String hpmProcessId)
        throws SQLException, KPeopleDAOException {

        Object result = null;

        if (DataAccessConstants.SOLR_TYPE_PROCESS.equals(type)) {
            List<String> ids = new ArrayList<String>();
            ids.add(hpmId);
            List<it.webscience.kpeople.be.Process> processList =
                getProcessDao().getProcessesByHpmProcessId(ids, null);
            if (processList != null && !processList.isEmpty()) {
                it.webscience.kpeople.be.Process process = processList.get(0);

                User owner = getUserDao().getUserByIdUser(
                        process.getOwner().getIdUser());
                process.setOwner(owner);
                result = process;
            }
//        } else if (DataAccessConstants.SOLR_TYPE_DOCUMENT.equals(type)) {
//            return getDocumentDao().getDocumentByHpmId(id);
        } else if (DataAccessConstants.SOLR_TYPE_EVENT.equals(type)) {
            List<String> ids = new ArrayList<String>();
            ids.add(hpmId);
            Event evento = null;
            List<Event> event = null;

            try {
                event = getEventDao().getEventsByHpmEventIds(ids);
                if (event != null && !event.isEmpty()) {
                    evento = event.get(0);
                    evento.setHpmProcessId(hpmProcessId);
                }
            } catch (RepositoryException e) {
                LOGGER.error(e.getMessage(), e);
            } catch (SolrServerException e) {
                LOGGER.error(e.getMessage(), e);
            }

            result = evento;
        } else if (DataAccessConstants.SOLR_TYPE_PATTERN.equals(type)) {
            Pattern pattern = getPatternDao().getPatternByHpmPatternId(hpmId);

            if (pattern != null) {
                pattern.setHpmProcessRefId(hpmProcessId);
            }

            result = pattern;
        } else if (DataAccessConstants.SOLR_TYPE_USER.equals(type)) {
            try {
                result = getUserDao().getUserByHpmUserId(hpmId);
            } catch (NamingException e) {
                LOGGER.error(e.getMessage(), e);
                throw new KPeopleDAOException(e.getMessage());
            }
        } else {
            Document doc = getDocumentDao().getDocumentByHpmId(hpmId);
            Attachment att = null;

            if (doc != null) {
                att = getAttachmentDao().getAttachmentsByHpmId(
                    doc.getHpmAttachmentId());
            }
            
            result = att;
        }

        return result;
    }

    /** @return ProcessDAO */
    private ProcessDAO getProcessDao() {

        if (processDao == null) {
            processDao = new ProcessDAO();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Instanziato ProcessDAO");
            }
        }

        return processDao;
    }

    /** @return DocumentDAO */
    private DocumentDAO getDocumentDao() {
        if (documentDao == null) {
            documentDao = new DocumentDAO();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Instanziato DocumentDAO");
            }
        }

        return documentDao;
    }

    /** @return AttachmentDAO */
    private AttachmentDAO getAttachmentDao() {
        if (attachmentDao == null) {
            attachmentDao = new AttachmentDAO();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Instanziato AttachmentDAO");
            }
        }

        return attachmentDao;
    }

    /** @return EventDAO */
    private EventDAO getEventDao() {
        if (eventDao == null) {
            eventDao = new EventDAO();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Instanziato EventDAO");
            }
        }

        return eventDao;
    }

    /** @return PatternDAO */
    private PatternDAO getPatternDao() {
        if (patternDao == null) {
            patternDao = new PatternDAO();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Instanziato PatternDAO");
            }
        }

        return patternDao;
    }

    /** @return UserDAO */
    private UserDAO getUserDao() {
        if (userDao == null) {
            userDao = new UserDAO();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Instanziato UserDAO");
            }
        }

        return userDao;
    }

    /** DAO dei processi. */
    private transient ProcessDAO processDao = null;
    /** DAO dei documenti. */
    private transient DocumentDAO documentDao = null;
    /** DAO degli allegati. */
    private transient AttachmentDAO attachmentDao = null;
    /** DAO degli eventi. */
    private transient EventDAO eventDao = null;
    /** DAO dei pattern. */
    private transient PatternDAO patternDao = null;
    /** DAO degli utenti. */
    private transient UserDAO userDao = null;
}
