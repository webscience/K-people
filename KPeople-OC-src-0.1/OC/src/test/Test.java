package test;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.aae.client.UimaAsynchronousEngine;
import org.apache.uima.adapter.jms.client.BaseUIMAAsynchronousEngine_impl;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceProcessException;

public class Test {

  BaseUIMAAsynchronousEngine_impl uimaAsEngine;

  public void initialize() throws Exception {
    uimaAsEngine = new BaseUIMAAsynchronousEngine_impl();
    Map<String, Object> appCtx = new HashMap<String, Object>();
    appCtx.put(UimaAsynchronousEngine.ServerUri, "tcp://192.168.0.61:61616");
    appCtx.put(UimaAsynchronousEngine.Endpoint, "mailQueue");
    appCtx.put(UimaAsynchronousEngine.CasPoolSize, 1);
    appCtx.put(UimaAsynchronousEngine.Timeout, 0);
    uimaAsEngine.initialize(appCtx);
  }
  public void run() throws Exception {
    for (int i = 0; i < 150; i++) {
      CAS cas = uimaAsEngine.getCAS();
      JCas jcas;
      try {
        System.out.println("UIMA AS Client Sending CAS#" + (i + 1) + "Request to a Service");
        jcas = cas.getJCas();
        jcas.setDocumentText("Some Text");
      } catch (CASException e) {
        throw new ResourceProcessException(e);
      }
      uimaAsEngine.sendCAS(cas);
    }
    // done
    uimaAsEngine.stop();

  }
  public static void main(String[] args ) {
    Test client = new Test();
    try {
      client.initialize();
      client.run();
    } catch( Exception e) {
      e.printStackTrace();
    }
  }

}
