/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.tutorial;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

/** 
 * Updated by JCasGen Wed Oct 20 17:43:06 CEST 2010
 * XML source: C:/WebScience/Progetti/K-People/OntologyController_UIMA/apache-uima/examples/descriptors/tutorial/ex1/TutorialTypeSystem.xml
 * @generated */
public class RoomNumber extends Annotation {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(RoomNumber.class);

  /**
   * @generated
   * @ordered
   */
  public final static int type = typeIndexID;

  /** @generated */
  public int getTypeIndexID() {return typeIndexID;}
 
  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected RoomNumber() {}
    
  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public RoomNumber(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public RoomNumber(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  public RoomNumber(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }

  /** <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {
  }

  // *--------------*
  // * Feature: building

  /**
   * getter for building - gets Building containing this room
   * 
   * @generated
   */
  public String getBuilding() {
    if (RoomNumber_Type.featOkTst && ((RoomNumber_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "org.apache.uima.tutorial.RoomNumber");
    return jcasType.ll_cas.ll_getStringValue(addr, ((RoomNumber_Type)jcasType).casFeatCode_building);}
    
  /**
   * setter for building - sets Building containing this room
   * 
   * @generated
   */
  public void setBuilding(String v) {
    if (RoomNumber_Type.featOkTst && ((RoomNumber_Type)jcasType).casFeat_building == null)
      jcasType.jcas.throwFeatMissing("building", "org.apache.uima.tutorial.RoomNumber");
    jcasType.ll_cas.ll_setStringValue(addr, ((RoomNumber_Type)jcasType).casFeatCode_building, v);}    
   
    
  //*--------------*
  //* Feature: eeee

  /** getter for eeee - gets 
   * @generated */
  public String getEeee() {
    if (RoomNumber_Type.featOkTst && ((RoomNumber_Type)jcasType).casFeat_eeee == null)
      jcasType.jcas.throwFeatMissing("eeee", "org.apache.uima.tutorial.RoomNumber");
    return jcasType.ll_cas.ll_getStringValue(addr, ((RoomNumber_Type)jcasType).casFeatCode_eeee);}
    
  /** setter for eeee - sets  
   * @generated */
  public void setEeee(String v) {
    if (RoomNumber_Type.featOkTst && ((RoomNumber_Type)jcasType).casFeat_eeee == null)
      jcasType.jcas.throwFeatMissing("eeee", "org.apache.uima.tutorial.RoomNumber");
    jcasType.ll_cas.ll_setStringValue(addr, ((RoomNumber_Type)jcasType).casFeatCode_eeee, v);}    
   
    
  //*--------------*
  //* Feature: Tedt1510

  /** getter for Tedt1510 - gets 
   * @generated */
  public boolean getTedt1510() {
    if (RoomNumber_Type.featOkTst && ((RoomNumber_Type)jcasType).casFeat_Tedt1510 == null)
      jcasType.jcas.throwFeatMissing("Tedt1510", "org.apache.uima.tutorial.RoomNumber");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((RoomNumber_Type)jcasType).casFeatCode_Tedt1510);}
    
  /** setter for Tedt1510 - sets  
   * @generated */
  public void setTedt1510(boolean v) {
    if (RoomNumber_Type.featOkTst && ((RoomNumber_Type)jcasType).casFeat_Tedt1510 == null)
      jcasType.jcas.throwFeatMissing("Tedt1510", "org.apache.uima.tutorial.RoomNumber");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((RoomNumber_Type)jcasType).casFeatCode_Tedt1510, v);}    
   
    
  //*--------------*
  //* Feature: test

  /** getter for test - gets 
   * @generated */
  public double getTest() {
    if (RoomNumber_Type.featOkTst && ((RoomNumber_Type)jcasType).casFeat_test == null)
      jcasType.jcas.throwFeatMissing("test", "org.apache.uima.tutorial.RoomNumber");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((RoomNumber_Type)jcasType).casFeatCode_test);}
    
  /** setter for test - sets  
   * @generated */
  public void setTest(double v) {
    if (RoomNumber_Type.featOkTst && ((RoomNumber_Type)jcasType).casFeat_test == null)
      jcasType.jcas.throwFeatMissing("test", "org.apache.uima.tutorial.RoomNumber");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((RoomNumber_Type)jcasType).casFeatCode_test, v);}    
  }
