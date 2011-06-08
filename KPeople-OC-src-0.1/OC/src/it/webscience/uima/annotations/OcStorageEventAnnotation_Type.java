
/* First created by JCasGen Thu Dec 09 13:26:38 CET 2010 */
package it.webscience.uima.annotations;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Feb 22 11:24:35 CET 2011
 * @generated */
public class OcStorageEventAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (OcStorageEventAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = OcStorageEventAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new OcStorageEventAnnotation(addr, OcStorageEventAnnotation_Type.this);
  			   OcStorageEventAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new OcStorageEventAnnotation(addr, OcStorageEventAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = OcStorageEventAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("it.webscience.uima.annotations.OcStorageEventAnnotation");
 
  /** @generated */
  final Feature casFeat_eventNodeRefId;
  /** @generated */
  final int     casFeatCode_eventNodeRefId;
  /** @generated */ 
  public String getEventNodeRefId(int addr) {
        if (featOkTst && casFeat_eventNodeRefId == null)
      jcas.throwFeatMissing("eventNodeRefId", "it.webscience.uima.annotations.OcStorageEventAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_eventNodeRefId);
  }
  /** @generated */    
  public void setEventNodeRefId(int addr, String v) {
        if (featOkTst && casFeat_eventNodeRefId == null)
      jcas.throwFeatMissing("eventNodeRefId", "it.webscience.uima.annotations.OcStorageEventAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_eventNodeRefId, v);}
    
  
 
  /** @generated */
  final Feature casFeat_eventUrl;
  /** @generated */
  final int     casFeatCode_eventUrl;
  /** @generated */ 
  public String getEventUrl(int addr) {
        if (featOkTst && casFeat_eventUrl == null)
      jcas.throwFeatMissing("eventUrl", "it.webscience.uima.annotations.OcStorageEventAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_eventUrl);
  }
  /** @generated */    
  public void setEventUrl(int addr, String v) {
        if (featOkTst && casFeat_eventUrl == null)
      jcas.throwFeatMissing("eventUrl", "it.webscience.uima.annotations.OcStorageEventAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_eventUrl, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public OcStorageEventAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_eventNodeRefId = jcas.getRequiredFeatureDE(casType, "eventNodeRefId", "uima.cas.String", featOkTst);
    casFeatCode_eventNodeRefId  = (null == casFeat_eventNodeRefId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_eventNodeRefId).getCode();

 
    casFeat_eventUrl = jcas.getRequiredFeatureDE(casType, "eventUrl", "uima.cas.String", featOkTst);
    casFeatCode_eventUrl  = (null == casFeat_eventUrl) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_eventUrl).getCode();

  }
}



    