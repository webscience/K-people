

/* First created by JCasGen Thu Dec 09 13:26:38 CET 2010 */
package it.webscience.uima.annotations;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Feb 22 11:24:35 CET 2011
 * XML source: C:/WebScience/Progetti/K-People/OntologyController_UIMA/apache-uima/examples/descriptors/webscience/typeSystems/OcStorageTypeSystem.xml
 * @generated */
public class OcStorageEventAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(OcStorageEventAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected OcStorageEventAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public OcStorageEventAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public OcStorageEventAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public OcStorageEventAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: eventNodeRefId

  /** getter for eventNodeRefId - gets 
   * @generated */
  public String getEventNodeRefId() {
    if (OcStorageEventAnnotation_Type.featOkTst && ((OcStorageEventAnnotation_Type)jcasType).casFeat_eventNodeRefId == null)
      jcasType.jcas.throwFeatMissing("eventNodeRefId", "it.webscience.uima.annotations.OcStorageEventAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OcStorageEventAnnotation_Type)jcasType).casFeatCode_eventNodeRefId);}
    
  /** setter for eventNodeRefId - sets  
   * @generated */
  public void setEventNodeRefId(String v) {
    if (OcStorageEventAnnotation_Type.featOkTst && ((OcStorageEventAnnotation_Type)jcasType).casFeat_eventNodeRefId == null)
      jcasType.jcas.throwFeatMissing("eventNodeRefId", "it.webscience.uima.annotations.OcStorageEventAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((OcStorageEventAnnotation_Type)jcasType).casFeatCode_eventNodeRefId, v);}    
   
    
  //*--------------*
  //* Feature: eventUrl

  /** getter for eventUrl - gets 
   * @generated */
  public String getEventUrl() {
    if (OcStorageEventAnnotation_Type.featOkTst && ((OcStorageEventAnnotation_Type)jcasType).casFeat_eventUrl == null)
      jcasType.jcas.throwFeatMissing("eventUrl", "it.webscience.uima.annotations.OcStorageEventAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OcStorageEventAnnotation_Type)jcasType).casFeatCode_eventUrl);}
    
  /** setter for eventUrl - sets  
   * @generated */
  public void setEventUrl(String v) {
    if (OcStorageEventAnnotation_Type.featOkTst && ((OcStorageEventAnnotation_Type)jcasType).casFeat_eventUrl == null)
      jcasType.jcas.throwFeatMissing("eventUrl", "it.webscience.uima.annotations.OcStorageEventAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((OcStorageEventAnnotation_Type)jcasType).casFeatCode_eventUrl, v);}    
  }

    