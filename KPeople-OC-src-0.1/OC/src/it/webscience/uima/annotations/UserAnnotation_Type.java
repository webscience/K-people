
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
public class UserAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (UserAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = UserAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new UserAnnotation(addr, UserAnnotation_Type.this);
  			   UserAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new UserAnnotation(addr, UserAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = UserAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("it.webscience.uima.annotations.UserAnnotation");
 
  /** @generated */
  final Feature casFeat_username;
  /** @generated */
  final int     casFeatCode_username;
  /** @generated */ 
  public String getUsername(int addr) {
        if (featOkTst && casFeat_username == null)
      jcas.throwFeatMissing("username", "it.webscience.uima.annotations.UserAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_username);
  }
  /** @generated */    
  public void setUsername(int addr, String v) {
        if (featOkTst && casFeat_username == null)
      jcas.throwFeatMissing("username", "it.webscience.uima.annotations.UserAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_username, v);}
    
  
 
  /** @generated */
  final Feature casFeat_email;
  /** @generated */
  final int     casFeatCode_email;
  /** @generated */ 
  public String getEmail(int addr) {
        if (featOkTst && casFeat_email == null)
      jcas.throwFeatMissing("email", "it.webscience.uima.annotations.UserAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_email);
  }
  /** @generated */    
  public void setEmail(int addr, String v) {
        if (featOkTst && casFeat_email == null)
      jcas.throwFeatMissing("email", "it.webscience.uima.annotations.UserAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_email, v);}
    
  
 
  /** @generated */
  final Feature casFeat_name;
  /** @generated */
  final int     casFeatCode_name;
  /** @generated */ 
  public String getName(int addr) {
        if (featOkTst && casFeat_name == null)
      jcas.throwFeatMissing("name", "it.webscience.uima.annotations.UserAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_name);
  }
  /** @generated */    
  public void setName(int addr, String v) {
        if (featOkTst && casFeat_name == null)
      jcas.throwFeatMissing("name", "it.webscience.uima.annotations.UserAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_name, v);}
    
  
 
  /** @generated */
  final Feature casFeat_dn;
  /** @generated */
  final int     casFeatCode_dn;
  /** @generated */ 
  public String getDn(int addr) {
        if (featOkTst && casFeat_dn == null)
      jcas.throwFeatMissing("dn", "it.webscience.uima.annotations.UserAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_dn);
  }
  /** @generated */    
  public void setDn(int addr, String v) {
        if (featOkTst && casFeat_dn == null)
      jcas.throwFeatMissing("dn", "it.webscience.uima.annotations.UserAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_dn, v);}
    
  
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "it.webscience.uima.annotations.UserAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "it.webscience.uima.annotations.UserAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ambiguousGroupId;
  /** @generated */
  final int     casFeatCode_ambiguousGroupId;
  /** @generated */ 
  public int getAmbiguousGroupId(int addr) {
        if (featOkTst && casFeat_ambiguousGroupId == null)
      jcas.throwFeatMissing("ambiguousGroupId", "it.webscience.uima.annotations.UserAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_ambiguousGroupId);
  }
  /** @generated */    
  public void setAmbiguousGroupId(int addr, int v) {
        if (featOkTst && casFeat_ambiguousGroupId == null)
      jcas.throwFeatMissing("ambiguousGroupId", "it.webscience.uima.annotations.UserAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_ambiguousGroupId, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public UserAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_username = jcas.getRequiredFeatureDE(casType, "username", "uima.cas.String", featOkTst);
    casFeatCode_username  = (null == casFeat_username) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_username).getCode();

 
    casFeat_email = jcas.getRequiredFeatureDE(casType, "email", "uima.cas.String", featOkTst);
    casFeatCode_email  = (null == casFeat_email) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_email).getCode();

 
    casFeat_name = jcas.getRequiredFeatureDE(casType, "name", "uima.cas.String", featOkTst);
    casFeatCode_name  = (null == casFeat_name) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_name).getCode();

 
    casFeat_dn = jcas.getRequiredFeatureDE(casType, "dn", "uima.cas.String", featOkTst);
    casFeatCode_dn  = (null == casFeat_dn) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_dn).getCode();

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_ambiguousGroupId = jcas.getRequiredFeatureDE(casType, "ambiguousGroupId", "uima.cas.Integer", featOkTst);
    casFeatCode_ambiguousGroupId  = (null == casFeat_ambiguousGroupId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ambiguousGroupId).getCode();

  }
}



    