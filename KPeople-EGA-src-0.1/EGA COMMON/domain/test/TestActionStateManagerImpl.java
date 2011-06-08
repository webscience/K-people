import java.util.Set;

import it.webscience.kpeople.domain.model.KpeopleAction;
import it.webscience.kpeople.domain.service.impl.ActionStateManagerImpl;
import it.webscience.kpeople.domain.service.interf.ActionStateManager;
import junit.framework.TestCase;


public class TestActionStateManagerImpl extends TestCase {

    public void testGetActionByStateLong() {
        
        ActionStateManager actionstatemanager = new ActionStateManagerImpl();
        Set<KpeopleAction> result = actionstatemanager.getActionByState(6);
        assertEquals(2, result.size());

    }

    public void testGetActionByStateLongString() {
        fail("Not yet implemented");
    }

}
