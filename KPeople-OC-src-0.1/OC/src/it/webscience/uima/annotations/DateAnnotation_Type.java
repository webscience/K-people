
/* First created by JCasGen Thu Dec 09 13:26:14 CET 2010 */
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
 * Updated by JCasGen Tue Feb 22 11:24:36 CET 2011
 * @generated */
public class DateAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DateAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DateAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DateAnnotation(addr, DateAnnotation_Type.this);
  			   DateAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DateAnnotation(addr, DateAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DateAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("it.webscience.uima.annotations.DateAnnotation");
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "it.webscience.uima.annotations.DateAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "it.webscience.uima.annotations.DateAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_timeInMillis;
  /** @generated */
  final int     casFeatCode_timeInMillis;
  /** @generated */ 
  public long getTimeInMillis(int addr) {
        if (featOkTst && casFeat_timeInMillis == null)
      jcas.throwFeatMissing("timeInMillis", "it.webscience.uima.annotations.DateAnnotation");
    return ll_cas.ll_getLongValue(addr, casFeatCode_timeInMillis);
  }
  /** @generated */    
  public void setTimeInMillis(int addr, long v) {
        if (featOkTst && casFeat_timeInMillis == null)
      jcas.throwFeatMissing("timeInMillis", "it.webscience.uima.annotations.DateAnnotation");
    ll_cas.ll_setLongValue(addr, casFeatCode_timeInMillis, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DateAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_timeInMillis = jcas.getRequiredFeatureDE(casType, "timeInMillis", "uima.cas.Long", featOkTst);
    casFeatCode_timeInMillis  = (null == casFeat_timeInMillis) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_timeInMillis).getCode();

  }
}



    