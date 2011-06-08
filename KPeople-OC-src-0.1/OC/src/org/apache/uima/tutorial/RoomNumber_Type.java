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

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Oct 20 17:43:06 CEST 2010
 * @generated */
public class RoomNumber_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (RoomNumber_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = RoomNumber_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new RoomNumber(addr, RoomNumber_Type.this);
  			   RoomNumber_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new RoomNumber(addr, RoomNumber_Type.this);
  	  }
    };

  /** @generated */
  public final static int typeIndexID = RoomNumber.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.tutorial.RoomNumber");

  /** @generated */
  final Feature casFeat_building;

  /** @generated */
  final int casFeatCode_building;

  /** @generated */
  public String getBuilding(int addr) {
        if (featOkTst && casFeat_building == null)
      jcas.throwFeatMissing("building", "org.apache.uima.tutorial.RoomNumber");
    return ll_cas.ll_getStringValue(addr, casFeatCode_building);
  }
  /** @generated */
  public void setBuilding(int addr, String v) {
        if (featOkTst && casFeat_building == null)
      jcas.throwFeatMissing("building", "org.apache.uima.tutorial.RoomNumber");
    ll_cas.ll_setStringValue(addr, casFeatCode_building, v);}
    
  
 
  /** @generated */
  final Feature casFeat_eeee;
  /** @generated */
  final int     casFeatCode_eeee;
  /** @generated */ 
  public String getEeee(int addr) {
        if (featOkTst && casFeat_eeee == null)
      jcas.throwFeatMissing("eeee", "org.apache.uima.tutorial.RoomNumber");
    return ll_cas.ll_getStringValue(addr, casFeatCode_eeee);
  }
  /** @generated */    
  public void setEeee(int addr, String v) {
        if (featOkTst && casFeat_eeee == null)
      jcas.throwFeatMissing("eeee", "org.apache.uima.tutorial.RoomNumber");
    ll_cas.ll_setStringValue(addr, casFeatCode_eeee, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Tedt1510;
  /** @generated */
  final int     casFeatCode_Tedt1510;
  /** @generated */ 
  public boolean getTedt1510(int addr) {
        if (featOkTst && casFeat_Tedt1510 == null)
      jcas.throwFeatMissing("Tedt1510", "org.apache.uima.tutorial.RoomNumber");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_Tedt1510);
  }
  /** @generated */    
  public void setTedt1510(int addr, boolean v) {
        if (featOkTst && casFeat_Tedt1510 == null)
      jcas.throwFeatMissing("Tedt1510", "org.apache.uima.tutorial.RoomNumber");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_Tedt1510, v);}
    
  
 
  /** @generated */
  final Feature casFeat_test;
  /** @generated */
  final int     casFeatCode_test;
  /** @generated */ 
  public double getTest(int addr) {
        if (featOkTst && casFeat_test == null)
      jcas.throwFeatMissing("test", "org.apache.uima.tutorial.RoomNumber");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_test);
  }
  /** @generated */    
  public void setTest(int addr, double v) {
        if (featOkTst && casFeat_test == null)
      jcas.throwFeatMissing("test", "org.apache.uima.tutorial.RoomNumber");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_test, v);}
    
  



  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public RoomNumber_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_building = jcas.getRequiredFeatureDE(casType, "building", "uima.cas.String", featOkTst);
    casFeatCode_building  = (null == casFeat_building) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_building).getCode();

 
    casFeat_eeee = jcas.getRequiredFeatureDE(casType, "eeee", "uima.cas.String", featOkTst);
    casFeatCode_eeee  = (null == casFeat_eeee) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_eeee).getCode();

 
    casFeat_Tedt1510 = jcas.getRequiredFeatureDE(casType, "Tedt1510", "uima.cas.Boolean", featOkTst);
    casFeatCode_Tedt1510  = (null == casFeat_Tedt1510) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Tedt1510).getCode();

 
    casFeat_test = jcas.getRequiredFeatureDE(casType, "test", "uima.cas.Double", featOkTst);
    casFeatCode_test  = (null == casFeat_test) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_test).getCode();

  }
}
