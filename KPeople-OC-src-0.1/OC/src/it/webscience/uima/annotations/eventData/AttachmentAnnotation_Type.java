
/* First created by JCasGen Thu Dec 09 13:26:30 CET 2010 */
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
public class AttachmentAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AttachmentAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AttachmentAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AttachmentAnnotation(addr, AttachmentAnnotation_Type.this);
  			   AttachmentAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AttachmentAnnotation(addr, AttachmentAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = AttachmentAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("it.webscience.uima.annotations.eventData.AttachmentAnnotation");
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_attachmentType;
  /** @generated */
  final int     casFeatCode_attachmentType;
  /** @generated */ 
  public String getAttachmentType(int addr) {
        if (featOkTst && casFeat_attachmentType == null)
      jcas.throwFeatMissing("attachmentType", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_attachmentType);
  }
  /** @generated */    
  public void setAttachmentType(int addr, String v) {
        if (featOkTst && casFeat_attachmentType == null)
      jcas.throwFeatMissing("attachmentType", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_attachmentType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_attachmentData;
  /** @generated */
  final int     casFeatCode_attachmentData;
  /** @generated */ 
  public String getAttachmentData(int addr) {
        if (featOkTst && casFeat_attachmentData == null)
      jcas.throwFeatMissing("attachmentData", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_attachmentData);
  }
  /** @generated */    
  public void setAttachmentData(int addr, String v) {
        if (featOkTst && casFeat_attachmentData == null)
      jcas.throwFeatMissing("attachmentData", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_attachmentData, v);}
    
  
 
  /** @generated */
  final Feature casFeat_attachmentName;
  /** @generated */
  final int     casFeatCode_attachmentName;
  /** @generated */ 
  public String getAttachmentName(int addr) {
        if (featOkTst && casFeat_attachmentName == null)
      jcas.throwFeatMissing("attachmentName", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_attachmentName);
  }
  /** @generated */    
  public void setAttachmentName(int addr, String v) {
        if (featOkTst && casFeat_attachmentName == null)
      jcas.throwFeatMissing("attachmentName", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_attachmentName, v);}
    
  
 
  /** @generated */
  final Feature casFeat_hashcode;
  /** @generated */
  final int     casFeatCode_hashcode;
  /** @generated */ 
  public String getHashcode(int addr) {
        if (featOkTst && casFeat_hashcode == null)
      jcas.throwFeatMissing("hashcode", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_hashcode);
  }
  /** @generated */    
  public void setHashcode(int addr, String v) {
        if (featOkTst && casFeat_hashcode == null)
      jcas.throwFeatMissing("hashcode", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_hashcode, v);}
    
  
 
  /** @generated */
  final Feature casFeat_urlAttachment;
  /** @generated */
  final int     casFeatCode_urlAttachment;
  /** @generated */ 
  public String getUrlAttachment(int addr) {
        if (featOkTst && casFeat_urlAttachment == null)
      jcas.throwFeatMissing("urlAttachment", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_urlAttachment);
  }
  /** @generated */    
  public void setUrlAttachment(int addr, String v) {
        if (featOkTst && casFeat_urlAttachment == null)
      jcas.throwFeatMissing("urlAttachment", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_urlAttachment, v);}
    
  
 
  /** @generated */
  final Feature casFeat_author;
  /** @generated */
  final int     casFeatCode_author;
  /** @generated */ 
  public String getAuthor(int addr) {
        if (featOkTst && casFeat_author == null)
      jcas.throwFeatMissing("author", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_author);
  }
  /** @generated */    
  public void setAuthor(int addr, String v) {
        if (featOkTst && casFeat_author == null)
      jcas.throwFeatMissing("author", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_author, v);}
    
  
 
  /** @generated */
  final Feature casFeat_creationDate;
  /** @generated */
  final int     casFeatCode_creationDate;
  /** @generated */ 
  public String getCreationDate(int addr) {
        if (featOkTst && casFeat_creationDate == null)
      jcas.throwFeatMissing("creationDate", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_creationDate);
  }
  /** @generated */    
  public void setCreationDate(int addr, String v) {
        if (featOkTst && casFeat_creationDate == null)
      jcas.throwFeatMissing("creationDate", "it.webscience.uima.annotations.eventData.AttachmentAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_creationDate, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public AttachmentAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_attachmentType = jcas.getRequiredFeatureDE(casType, "attachmentType", "uima.cas.String", featOkTst);
    casFeatCode_attachmentType  = (null == casFeat_attachmentType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_attachmentType).getCode();

 
    casFeat_attachmentData = jcas.getRequiredFeatureDE(casType, "attachmentData", "uima.cas.String", featOkTst);
    casFeatCode_attachmentData  = (null == casFeat_attachmentData) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_attachmentData).getCode();

 
    casFeat_attachmentName = jcas.getRequiredFeatureDE(casType, "attachmentName", "uima.cas.String", featOkTst);
    casFeatCode_attachmentName  = (null == casFeat_attachmentName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_attachmentName).getCode();

 
    casFeat_hashcode = jcas.getRequiredFeatureDE(casType, "hashcode", "uima.cas.String", featOkTst);
    casFeatCode_hashcode  = (null == casFeat_hashcode) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_hashcode).getCode();

 
    casFeat_urlAttachment = jcas.getRequiredFeatureDE(casType, "urlAttachment", "uima.cas.String", featOkTst);
    casFeatCode_urlAttachment  = (null == casFeat_urlAttachment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_urlAttachment).getCode();

 
    casFeat_author = jcas.getRequiredFeatureDE(casType, "author", "uima.cas.String", featOkTst);
    casFeatCode_author  = (null == casFeat_author) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_author).getCode();

 
    casFeat_creationDate = jcas.getRequiredFeatureDE(casType, "creationDate", "uima.cas.String", featOkTst);
    casFeatCode_creationDate  = (null == casFeat_creationDate) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_creationDate).getCode();

  }
}



    