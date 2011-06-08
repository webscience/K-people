

/* First created by JCasGen Thu Dec 09 13:26:14 CET 2010 */
package it.webscience.uima.annotations;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Feb 22 11:24:36 CET 2011
 * XML source: C:/WebScience/Progetti/K-People/OntologyController_UIMA/apache-uima/examples/descriptors/webscience/typeSystems/EventDataTypeSystem.xml
 * @generated */
public class DateAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DateAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DateAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DateAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DateAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DateAnnotation(JCas jcas, int begin, int end) {
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
    if (DateAnnotation_Type.featOkTst && ((DateAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "it.webscience.uima.annotations.DateAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DateAnnotation_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (DateAnnotation_Type.featOkTst && ((DateAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "it.webscience.uima.annotations.DateAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((DateAnnotation_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: timeInMillis

  /** getter for timeInMillis - gets 
   * @generated */
  public long getTimeInMillis() {
    if (DateAnnotation_Type.featOkTst && ((DateAnnotation_Type)jcasType).casFeat_timeInMillis == null)
      jcasType.jcas.throwFeatMissing("timeInMillis", "it.webscience.uima.annotations.DateAnnotation");
    return jcasType.ll_cas.ll_getLongValue(addr, ((DateAnnotation_Type)jcasType).casFeatCode_timeInMillis);}
    
  /** setter for timeInMillis - sets  
   * @generated */
  public void setTimeInMillis(long v) {
    if (DateAnnotation_Type.featOkTst && ((DateAnnotation_Type)jcasType).casFeat_timeInMillis == null)
      jcasType.jcas.throwFeatMissing("timeInMillis", "it.webscience.uima.annotations.DateAnnotation");
    jcasType.ll_cas.ll_setLongValue(addr, ((DateAnnotation_Type)jcasType).casFeatCode_timeInMillis, v);}    
  }

    