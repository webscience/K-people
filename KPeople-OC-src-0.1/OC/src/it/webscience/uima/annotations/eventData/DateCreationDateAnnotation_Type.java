
/* First created by JCasGen Thu Dec 09 13:26:31 CET 2010 */
package it.webscience.uima.annotations.eventData;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import it.webscience.uima.annotations.DateAnnotation_Type;

/** 
 * Updated by JCasGen Tue Feb 22 11:24:36 CET 2011
 * @generated */
public class DateCreationDateAnnotation_Type extends DateAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DateCreationDateAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DateCreationDateAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DateCreationDateAnnotation(addr, DateCreationDateAnnotation_Type.this);
  			   DateCreationDateAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DateCreationDateAnnotation(addr, DateCreationDateAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DateCreationDateAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("it.webscience.uima.annotations.eventData.DateCreationDateAnnotation");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DateCreationDateAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    