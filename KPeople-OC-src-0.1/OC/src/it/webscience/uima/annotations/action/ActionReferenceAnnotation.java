

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
public class ActionReferenceAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ActionReferenceAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ActionReferenceAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ActionReferenceAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ActionReferenceAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ActionReferenceAnnotation(JCas jcas, int begin, int end) {
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
    if (ActionReferenceAnnotation_Type.featOkTst && ((ActionReferenceAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "it.webscience.uima.annotations.action.ActionReferenceAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ActionReferenceAnnotation_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (ActionReferenceAnnotation_Type.featOkTst && ((ActionReferenceAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "it.webscience.uima.annotations.action.ActionReferenceAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ActionReferenceAnnotation_Type)jcasType).casFeatCode_value, v);}    
  }

    