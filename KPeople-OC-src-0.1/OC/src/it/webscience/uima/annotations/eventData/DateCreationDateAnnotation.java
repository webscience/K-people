

/* First created by JCasGen Thu Dec 09 13:26:31 CET 2010 */
package it.webscience.uima.annotations.eventData;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import it.webscience.uima.annotations.DateAnnotation;


/** 
 * Updated by JCasGen Tue Feb 22 11:24:36 CET 2011
 * XML source: C:/WebScience/Progetti/K-People/OntologyController_UIMA/apache-uima/examples/descriptors/webscience/typeSystems/EventDataTypeSystem.xml
 * @generated */
public class DateCreationDateAnnotation extends DateAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DateCreationDateAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DateCreationDateAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DateCreationDateAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DateCreationDateAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DateCreationDateAnnotation(JCas jcas, int begin, int end) {
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
     
}

    