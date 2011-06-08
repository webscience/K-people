package test;

import org.junit.Assert;
import org.junit.Test;

import it.webscience.uima.annotators.HpmAnnotator;

public class TestValidActionType {

    @Test
    public void testCheckValidAction() {
        HpmAnnotator hpm = new HpmAnnotator();
        
        Assert.assertTrue(hpm.checkValidAction("COMMUNICATION"));
        Assert.assertTrue(hpm.checkValidAction("PROCESS.PATTERN.RICHIESTACONTRIBUTO.ACCETTA"));
        Assert.assertTrue(hpm.checkValidAction("PROCESS.PATTERN.RICHIESTACONTRIBUTO.RIFIUTATA"));
    }
}
