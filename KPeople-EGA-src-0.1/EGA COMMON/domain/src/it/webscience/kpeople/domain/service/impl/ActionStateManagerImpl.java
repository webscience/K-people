package it.webscience.kpeople.domain.service.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.webscience.kpeople.domain.KpeopleLabel;
import it.webscience.kpeople.domain.KpeopleLogger;
import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.domain.model.KpeopleEvent;
import it.webscience.kpeople.domain.model.KpeopleState;
import it.webscience.kpeople.domain.service.interf.ActionStateManager;
import it.webscience.kpeople.domain.util.HibernateUtil;
import it.webscience.kpeople.logging.serviceInterface.KpeopleLog;

public class ActionStateManagerImpl implements ActionStateManager {

    public String methodName;
    private static KpeopleLog logService = KpeopleLogger.getInstance()
    .getService();

    public String addNewState(final String name, final String description) {

        methodName = this.getClass() + ".addNewState";

        logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + KpeopleLabel.getLogStart());

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        KpeopleState s = null;
        try {
            tx = session.beginTransaction();
            s = new KpeopleState();
            s.setName(name);
            s.setDescription(description);
            session.save(s);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            logService.logError(KpeopleLabel.getSystemId(),
                            methodName + he.getMessage());
            throw he;
        } finally {
            session.close();
        }

        logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + KpeopleLabel.getLogStop());

        return s.getName();
    }

    public String addNewAction(final String systemId, final String actionType,
            final String actionReference) {

        KpeopleState topProcess = getState(1);

        methodName = this.getClass() + ".addNewAction";
        logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + KpeopleLabel.getLogStart());

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        KpeopleAction a = null;
        try {
            tx = session.beginTransaction();
            a = new KpeopleAction();
            a.setSystemId(systemId);
            a.setActionType(actionType);
            a.setActionReference(actionReference);
            // a.setTimestamp("timestamp");
            a.setFKActionState(topProcess);
            session.save(a);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            logService.logError(KpeopleLabel.getSystemId(),
                            methodName + he.getMessage());
            throw he;
        } finally {
                      
            session.close();
        }
        logService.logInfo(KpeopleLabel.getSystemId(),
                methodName + " inserito attivita' " + a.getActionReference());
        logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + KpeopleLabel.getLogStop());

        return a.getActionType() + " " + a.getActionReference() + " "
                + a.getSystemId();

    }

    public KpeopleEvent addNewEvent(final String xMLevent, final String numRetries,
            final String lastRetry, final KpeopleAction fKEventAction) {

        methodName = this.getClass() + ".addNewEvent";

        logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + KpeopleLabel.getLogStart());

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        KpeopleEvent e = null;
        try {
            tx = session.beginTransaction();
            e = new KpeopleEvent();
            e.setXMLevent(xMLevent);
            e.setNumRetries(numRetries);
            e.setLastRetry(lastRetry);
            e.setFKEventAction(fKEventAction);
            session.save(e);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            logService.logError(KpeopleLabel.getSystemId(),
                            methodName + he.getMessage());

            throw he;
        } finally {
            session.close();
        }

        logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + KpeopleLabel.getLogStop());
        return e;

    }

    public Set<KpeopleAction> getActionByState(final long id) {
        methodName = this.getClass() + ".getActionByState";
        Set<KpeopleAction> actions = new HashSet<KpeopleAction>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        KpeopleState result = null;
        try {
            tx = session.beginTransaction();

            Query q = session.createQuery(
                    "from KpeopleState where idState=:idState").setLong(
                    "idState", id);

            if (!q.list().isEmpty()) {
                result = (KpeopleState) q.list().get(0);

                actions = result.getActions();
                logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + " result " +  actions.size());
               
                tx.commit();

            } else {
                logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + " non e' presente nessuna azione con id " + id);

            }

        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }

        return actions;
    }

    public List<KpeopleAction> getActionByState(final long id,
            final String systemId) {
        methodName = this.getClass() + ".getActionByState";

        Set<KpeopleAction> actions = new HashSet<KpeopleAction>();
        List<KpeopleAction> filteredResult = new LinkedList<KpeopleAction>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        KpeopleState result = null;
        try {
            tx = session.beginTransaction();

            Query q = session.createQuery(
                    "from KpeopleState where idState=:idState").setLong(
                    "idState", id);

            if (!q.list().isEmpty()) {
                result = (KpeopleState) q.list().get(0);

                actions = result.getActions();
                Query filterQuery = session.createFilter(actions,
                        "where this.systemId like '" + systemId + "'");
                filteredResult = filterQuery.list();
                logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + " result " +  filteredResult.size());
               
                tx.commit();

            } else {
                logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + " non e' presente nessuna azione con id " + id);

            }

        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }

        return filteredResult;
    }

    public  KpeopleState getState(final long id) {
        methodName = this.getClass() + ".getState";

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        KpeopleState result = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery(
                    "from KpeopleState where idState=:idState").setLong(
                    "idState", id);

            if (!q.list().isEmpty()) {
                result = (KpeopleState) q.list().get(0);
                tx.commit();
            } else {
                logService.logInfo(KpeopleLabel.getSystemId(),
                        methodName + " non e' presente nessuna azione con id " + id);

            }
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }

        return result;
    }

    public Boolean updateActionState(final KpeopleAction action, final Long idState) {

        KpeopleState fetched = getState(idState);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            KpeopleAction actionSelected = (KpeopleAction) session.get(
                    KpeopleAction.class, action.getIdAction());
            actionSelected.setFKActionState(fetched);
            session.update(actionSelected);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }

        return null;
    }

    /*
     * testati ma non utilizzati da decommentare in base alle esigenze

     * public static KpeopleEvent getEvent(long id) { Session session =
     * HibernateUtil.getSessionFactory().openSession(); Transaction tx = null;
     * KpeopleEvent result = null; try { tx = session.beginTransaction(); Query
     * q =
     * session.createQuery("from KpeopleEvent where idEvent=:idEvent").setLong
     * ("idEvent", id);
     * if (!q.list().isEmpty()) { result = (KpeopleEvent)q.list().get(0);
     * tx.commit(); } else {
     * System.out.println("non � presente nessun evento con id " + id); } }
     * catch (HibernateException he) { if (tx != null) tx.rollback(); throw he;
     * } finally { session.close(); }
     * return result; }
     * public static List<KpeopleAction> getAllAction() { List result = null;
     * Session session = HibernateUtil.getSessionFactory().openSession();
     * Transaction tx = null; try { tx = session.beginTransaction(); Query q =
     * session.createQuery("from Action"); result = q.list(); tx.commit(); }
     * catch (HibernateException he) { if (tx != null) tx.rollback(); throw he;
     * } finally { session.close(); }
     * return result; }
     * public static List<KpeopleEvent> getAllEvent() { List result = null;
     * Session session = HibernateUtil.getSessionFactory().openSession();
     * Transaction tx = null; try { tx = session.beginTransaction(); Query q =
     * session.createQuery("from KpeopleEvent"); result = q.list(); tx.commit();
     * } catch (HibernateException he) { if (tx != null) tx.rollback(); throw
     * he; } finally { session.close(); }
     * return result; }
     */

}