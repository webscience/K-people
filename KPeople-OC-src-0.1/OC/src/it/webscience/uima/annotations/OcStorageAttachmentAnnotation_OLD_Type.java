
/* First created by JCasGen Tue Feb 22 11:24:35 CET 2011 */
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
public class OcStorageAttachmentAnnotation_OLD_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (OcStorageAttachmentAnnotation_OLD_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = OcStorageAttachmentAnnotation_OLD_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new OcStorageAttachmentAnnotation_OLD(addr, OcStorageAttachmentAnnotation_OLD_Type.this);
  			   OcStorageAttachmentAnnotation_OLD_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new OcStorageAttachmentAnnotation_OLD(addr, OcStorageAttachmentAnnotation_OLD_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = OcStorageAttachmentAnnotation_OLD.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
 
  /** @generated */
  final Feature casFeat_idAttachment;
  /** @generated */
  final int     casFeatCode_idAttachment;
  /** @generated */ 
  public String getIdAttachment(int addr) {
        if (featOkTst && casFeat_idAttachment == null)
      jcas.throwFeatMissing("idAttachment", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    return ll_cas.ll_getStringValue(addr, casFeatCode_idAttachment);
  }
  /** @generated */    
  public void setIdAttachment(int addr, String v) {
        if (featOkTst && casFeat_idAttachment == null)
      jcas.throwFeatMissing("idAttachment", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    ll_cas.ll_setStringValue(addr, casFeatCode_idAttachment, v);}
    
  
 
  /** @generated */
  final Feature casFeat_urlAttachment;
  /** @generated */
  final int     casFeatCode_urlAttachment;
  /** @generated */ 
  public String getUrlAttachment(int addr) {
        if (featOkTst && casFeat_urlAttachment == null)
      jcas.throwFeatMissing("urlAttachment", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    return ll_cas.ll_getStringValue(addr, casFeatCode_urlAttachment);
  }
  /** @generated */    
  public void setUrlAttachment(int addr, String v) {
        if (featOkTst && casFeat_urlAttachment == null)
      jcas.throwFeatMissing("urlAttachment", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    ll_cas.ll_setStringValue(addr, casFeatCode_urlAttachment, v);}
    
  
 
  /** @generated */
  final Feature casFeat_author;
  /** @generated */
  final int     casFeatCode_author;
  /** @generated */ 
  public String getAuthor(int addr) {
        if (featOkTst && casFeat_author == null)
      jcas.throwFeatMissing("author", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    return ll_cas.ll_getStringValue(addr, casFeatCode_author);
  }
  /** @generated */    
  public void setAuthor(int addr, String v) {
        if (featOkTst && casFeat_author == null)
      jcas.throwFeatMissing("author", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    ll_cas.ll_setStringValue(addr, casFeatCode_author, v);}
    
  
 
  /** @generated */
  final Feature casFeat_creationDate;
  /** @generated */
  final int     casFeatCode_creationDate;
  /** @generated */ 
  public String getCreationDate(int addr) {
        if (featOkTst && casFeat_creationDate == null)
      jcas.throwFeatMissing("creationDate", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    return ll_cas.ll_getStringValue(addr, casFeatCode_creationDate);
  }
  /** @generated */    
  public void setCreationDate(int addr, String v) {
        if (featOkTst && casFeat_creationDate == null)
      jcas.throwFeatMissing("creationDate", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    ll_cas.ll_setStringValue(addr, casFeatCode_creationDate, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public OcStorageAttachmentAnnotation_OLD_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_idAttachment = jcas.getRequiredFeatureDE(casType, "idAttachment", "uima.cas.String", featOkTst);
    casFeatCode_idAttachment  = (null == casFeat_idAttachment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_idAttachment).getCode();

 
    casFeat_urlAttachment = jcas.getRequiredFeatureDE(casType, "urlAttachment", "uima.cas.String", featOkTst);
    casFeatCode_urlAttachment  = (null == casFeat_urlAttachment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_urlAttachment).getCode();

 
    casFeat_author = jcas.getRequiredFeatureDE(casType, "author", "uima.cas.String", featOkTst);
    casFeatCode_author  = (null == casFeat_author) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_author).getCode();

 
    casFeat_creationDate = jcas.getRequiredFeatureDE(casType, "creationDate", "uima.cas.String", featOkTst);
    casFeatCode_creationDate  = (null == casFeat_creationDate) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_creationDate).getCode();

  }
}



    