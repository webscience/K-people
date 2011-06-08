

/* First created by JCasGen Thu Dec 09 13:26:31 CET 2010 */
package it.webscience.uima.annotations.eventData;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Feb 22 11:24:36 CET 2011
 * XML source: C:/WebScience/Progetti/K-People/OntologyController_UIMA/apache-uima/examples/descriptors/webscience/typeSystems/EventDataTypeSystem.xml
 * @generated */
public class BodyAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(BodyAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected BodyAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public BodyAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public BodyAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public BodyAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (BodyAnnotation_Type.featOkTst && ((BodyAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "it.webscience.uima.annotations.eventData.BodyAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BodyAnnotation_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (BodyAnnotation_Type.featOkTst && ((BodyAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "it.webscience.uima.annotations.eventData.BodyAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((BodyAnnotation_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: bodyType

  /** getter for bodyType - gets 
   * @generated */
  public String getBodyType() {
    if (BodyAnnotation_Type.featOkTst && ((BodyAnnotation_Type)jcasType).casFeat_bodyType == null)
      jcasType.jcas.throwFeatMissing("bodyType", "it.webscience.uima.annotations.eventData.BodyAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BodyAnnotation_Type)jcasType).casFeatCode_bodyType);}
    
  /** setter for bodyType - sets  
   * @generated */
  public void setBodyType(String v) {
    if (BodyAnnotation_Type.featOkTst && ((BodyAnnotation_Type)jcasType).casFeat_bodyType == null)
      jcasType.jcas.throwFeatMissing("bodyType", "it.webscience.uima.annotations.eventData.BodyAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((BodyAnnotation_Type)jcasType).casFeatCode_bodyType, v);}    
  }

    