

/* First created by JCasGen Tue Feb 22 11:24:35 CET 2011 */
package it.webscience.uima.annotations;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Feb 22 11:24:35 CET 2011
 * XML source: C:/WebScience/Progetti/K-People/OntologyController_UIMA/apache-uima/examples/descriptors/webscience/typeSystems/OcStorageTypeSystem.xml
 * @generated */
public class OcStorageAttachmentAnnotation_OLD extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(OcStorageAttachmentAnnotation_OLD.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected OcStorageAttachmentAnnotation_OLD() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public OcStorageAttachmentAnnotation_OLD(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public OcStorageAttachmentAnnotation_OLD(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public OcStorageAttachmentAnnotation_OLD(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: idAttachment

  /** getter for idAttachment - gets 
   * @generated */
  public String getIdAttachment() {
    if (OcStorageAttachmentAnnotation_OLD_Type.featOkTst && ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeat_idAttachment == null)
      jcasType.jcas.throwFeatMissing("idAttachment", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeatCode_idAttachment);}
    
  /** setter for idAttachment - sets  
   * @generated */
  public void setIdAttachment(String v) {
    if (OcStorageAttachmentAnnotation_OLD_Type.featOkTst && ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeat_idAttachment == null)
      jcasType.jcas.throwFeatMissing("idAttachment", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    jcasType.ll_cas.ll_setStringValue(addr, ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeatCode_idAttachment, v);}    
   
    
  //*--------------*
  //* Feature: urlAttachment

  /** getter for urlAttachment - gets 
   * @generated */
  public String getUrlAttachment() {
    if (OcStorageAttachmentAnnotation_OLD_Type.featOkTst && ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeat_urlAttachment == null)
      jcasType.jcas.throwFeatMissing("urlAttachment", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeatCode_urlAttachment);}
    
  /** setter for urlAttachment - sets  
   * @generated */
  public void setUrlAttachment(String v) {
    if (OcStorageAttachmentAnnotation_OLD_Type.featOkTst && ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeat_urlAttachment == null)
      jcasType.jcas.throwFeatMissing("urlAttachment", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    jcasType.ll_cas.ll_setStringValue(addr, ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeatCode_urlAttachment, v);}    
   
    
  //*--------------*
  //* Feature: author

  /** getter for author - gets 
   * @generated */
  public String getAuthor() {
    if (OcStorageAttachmentAnnotation_OLD_Type.featOkTst && ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeat_author == null)
      jcasType.jcas.throwFeatMissing("author", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeatCode_author);}
    
  /** setter for author - sets  
   * @generated */
  public void setAuthor(String v) {
    if (OcStorageAttachmentAnnotation_OLD_Type.featOkTst && ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeat_author == null)
      jcasType.jcas.throwFeatMissing("author", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    jcasType.ll_cas.ll_setStringValue(addr, ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeatCode_author, v);}    
   
    
  //*--------------*
  //* Feature: creationDate

  /** getter for creationDate - gets 
   * @generated */
  public String getCreationDate() {
    if (OcStorageAttachmentAnnotation_OLD_Type.featOkTst && ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeat_creationDate == null)
      jcasType.jcas.throwFeatMissing("creationDate", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeatCode_creationDate);}
    
  /** setter for creationDate - sets  
   * @generated */
  public void setCreationDate(String v) {
    if (OcStorageAttachmentAnnotation_OLD_Type.featOkTst && ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeat_creationDate == null)
      jcasType.jcas.throwFeatMissing("creationDate", "it.webscience.uima.annotations.OcStorageAttachmentAnnotation_OLD");
    jcasType.ll_cas.ll_setStringValue(addr, ((OcStorageAttachmentAnnotation_OLD_Type)jcasType).casFeatCode_creationDate, v);}    
  }

    