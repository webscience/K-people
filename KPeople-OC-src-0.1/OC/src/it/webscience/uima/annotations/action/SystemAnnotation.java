

/* First created by JCasGen Thu Dec 09 13:26:14 CET 2010 */
package it.webscience.uima.annotations.action;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Feb 21 11:38:30 CET 2011
 * XML source: C:/WebScience/Progetti/K-People/OntologyController_UIMA/apache-uima/examples/descriptors/webscience/analysisEngines/ocAnalysisEngineDescriptor.xml
 * @generated */
public class SystemAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SystemAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SystemAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SystemAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SystemAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SystemAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: systemId

  /** getter for systemId - gets 
   * @generated */
  public String getSystemId() {
    if (SystemAnnotation_Type.featOkTst && ((SystemAnnotation_Type)jcasType).casFeat_systemId == null)
      jcasType.jcas.throwFeatMissing("systemId", "it.webscience.uima.annotations.action.SystemAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SystemAnnotation_Type)jcasType).casFeatCode_systemId);}
    
  /** setter for systemId - sets  
   * @generated */
  public void setSystemId(String v) {
    if (SystemAnnotation_Type.featOkTst && ((SystemAnnotation_Type)jcasType).casFeat_systemId == null)
      jcasType.jcas.throwFeatMissing("systemId", "it.webscience.uima.annotations.action.SystemAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SystemAnnotation_Type)jcasType).casFeatCode_systemId, v);}    
   
    
  //*--------------*
  //* Feature: systemType

  /** getter for systemType - gets 
   * @generated */
  public String getSystemType() {
    if (SystemAnnotation_Type.featOkTst && ((SystemAnnotation_Type)jcasType).casFeat_systemType == null)
      jcasType.jcas.throwFeatMissing("systemType", "it.webscience.uima.annotations.action.SystemAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SystemAnnotation_Type)jcasType).casFeatCode_systemType);}
    
  /** setter for systemType - sets  
   * @generated */
  public void setSystemType(String v) {
    if (SystemAnnotation_Type.featOkTst && ((SystemAnnotation_Type)jcasType).casFeat_systemType == null)
      jcasType.jcas.throwFeatMissing("systemType", "it.webscience.uima.annotations.action.SystemAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SystemAnnotation_Type)jcasType).casFeatCode_systemType, v);}    
  }

    