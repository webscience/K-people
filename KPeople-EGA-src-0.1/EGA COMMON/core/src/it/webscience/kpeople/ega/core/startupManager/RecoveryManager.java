package it.webscience.kpeople.ega.core.startupManager;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.domain.service.interf.ActionStateManager;
import it.webscience.kpeople.ega.core.KpeopleLabel;


import java.util.LinkedHashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author XPMUser
 */
public class RecoveryManager {

    /**
     * oggetto della classe ActionStateManager.
     */
    private ActionStateManager service;

    /**
     * Method to check the system state. It controls if all actions were be
     * processed and delivered to EPE component before to start EGA and Channel
     * components.
     */
    public final void checkSystem() {
        service = getService();
        Set<KpeopleAction> actionsToRestore =
            new LinkedHashSet<KpeopleAction>();

        Set<KpeopleAction> actionsReadytoprocess = service
                .getActionByState(KpeopleLabel.readyToProcess());

        Set<KpeopleAction> actionsFetched = service
                .getActionByState(KpeopleLabel.stateFetched());

        if (!actionsReadytoprocess.isEmpty()) {
            actionsToRestore.addAll(actionsReadytoprocess);
        }

        if (!actionsFetched.isEmpty()) {
            actionsToRestore.addAll(actionsFetched);
        }

        if (!actionsToRestore.isEmpty()) {
            System.out.println("******  restore:  " + actionsToRestore
                    + " ********\n");
            for (KpeopleAction action : actionsToRestore) {
                service.updateActionState(action, KpeopleLabel.toProcess());

            }

        }

        Set<KpeopleAction> actionsReadytosent = service
                .getActionByState(KpeopleLabel.readyToSent());
        if (!actionsReadytosent.isEmpty()) {
            System.out.println("****************  restore:  "
                    + actionsReadytosent + " *******************\n");
            for (KpeopleAction action : actionsReadytosent) {
                service.updateActionState(action, KpeopleLabel.statePacked());

            }
        }

    }

    /**
     * @return il servizio di recovery.
     */
    private ActionStateManager getService() {

        BundleContext context = FrameworkUtil.getBundle(this.getClass())
                .getBundleContext();
        ServiceReference reference = context
                .getServiceReference(ActionStateManager.class.getName());
        service = (ActionStateManager) context.getService(reference);

        return service;

    }

}
