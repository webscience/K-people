

/* First created by JCasGen Thu Dec 09 13:26:14 CET 2010 */
package it.webscience.uima.annotations;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


import org.apache.uima.jcas.cas.IntegerList;


/** 
 * Updated by JCasGen Tue Feb 22 11:24:36 CET 2011
 * XML source: C:/WebScience/Progetti/K-People/OntologyController_UIMA/apache-uima/examples/descriptors/webscience/typeSystems/EventDataTypeSystem.xml
 * @generated */
public class UserAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(UserAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected UserAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public UserAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public UserAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public UserAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: username

  /** getter for username - gets 
   * @generated */
  public String getUsername() {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_username == null)
      jcasType.jcas.throwFeatMissing("username", "it.webscience.uima.annotations.UserAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_username);}
    
  /** setter for username - sets  
   * @generated */
  public void setUsername(String v) {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_username == null)
      jcasType.jcas.throwFeatMissing("username", "it.webscience.uima.annotations.UserAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_username, v);}    
   
    
  //*--------------*
  //* Feature: email

  /** getter for email - gets 
   * @generated */
  public String getEmail() {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_email == null)
      jcasType.jcas.throwFeatMissing("email", "it.webscience.uima.annotations.UserAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_email);}
    
  /** setter for email - sets  
   * @generated */
  public void setEmail(String v) {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_email == null)
      jcasType.jcas.throwFeatMissing("email", "it.webscience.uima.annotations.UserAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_email, v);}    
   
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated */
  public String getName() {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "it.webscience.uima.annotations.UserAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated */
  public void setName(String v) {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "it.webscience.uima.annotations.UserAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: dn

  /** getter for dn - gets 
   * @generated */
  public String getDn() {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_dn == null)
      jcasType.jcas.throwFeatMissing("dn", "it.webscience.uima.annotations.UserAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_dn);}
    
  /** setter for dn - sets  
   * @generated */
  public void setDn(String v) {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_dn == null)
      jcasType.jcas.throwFeatMissing("dn", "it.webscience.uima.annotations.UserAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_dn, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "it.webscience.uima.annotations.UserAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "it.webscience.uima.annotations.UserAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: ambiguousGroupId

  /** getter for ambiguousGroupId - gets 
   * @generated */
  public int getAmbiguousGroupId() {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_ambiguousGroupId == null)
      jcasType.jcas.throwFeatMissing("ambiguousGroupId", "it.webscience.uima.annotations.UserAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_ambiguousGroupId);}
    
  /** setter for ambiguousGroupId - sets  
   * @generated */
  public void setAmbiguousGroupId(int v) {
    if (UserAnnotation_Type.featOkTst && ((UserAnnotation_Type)jcasType).casFeat_ambiguousGroupId == null)
      jcasType.jcas.throwFeatMissing("ambiguousGroupId", "it.webscience.uima.annotations.UserAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((UserAnnotation_Type)jcasType).casFeatCode_ambiguousGroupId, v);}    
  }

    