
/* First created by JCasGen Thu Dec 09 13:26:31 CET 2010 */
package it.webscience.uima.annotations.eventData;

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
 * Updated by JCasGen Tue Feb 22 11:24:36 CET 2011
 * @generated */
public class BodyAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (BodyAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = BodyAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new BodyAnnotation(addr, BodyAnnotation_Type.this);
  			   BodyAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new BodyAnnotation(addr, BodyAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = BodyAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("it.webscience.uima.annotations.eventData.BodyAnnotation");
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "it.webscience.uima.annotations.eventData.BodyAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "it.webscience.uima.annotations.eventData.BodyAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_bodyType;
  /** @generated */
  final int     casFeatCode_bodyType;
  /** @generated */ 
  public String getBodyType(int addr) {
        if (featOkTst && casFeat_bodyType == null)
      jcas.throwFeatMissing("bodyType", "it.webscience.uima.annotations.eventData.BodyAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_bodyType);
  }
  /** @generated */    
  public void setBodyType(int addr, String v) {
        if (featOkTst && casFeat_bodyType == null)
      jcas.throwFeatMissing("bodyType", "it.webscience.uima.annotations.eventData.BodyAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_bodyType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public BodyAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_bodyType = jcas.getRequiredFeatureDE(casType, "bodyType", "uima.cas.String", featOkTst);
    casFeatCode_bodyType  = (null == casFeat_bodyType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_bodyType).getCode();

  }
}



    