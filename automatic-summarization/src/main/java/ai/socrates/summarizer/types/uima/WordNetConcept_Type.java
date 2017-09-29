
/* First created by JCasGen Thu Aug 24 14:00:20 CEST 2017 */
package ai.socrates.summarizer.types.uima;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Sep 13 12:35:36 CEST 2017
 * @generated */
public class WordNetConcept_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (WordNetConcept_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = WordNetConcept_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new WordNetConcept(addr, WordNetConcept_Type.this);
  			   WordNetConcept_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new WordNetConcept(addr, WordNetConcept_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = WordNetConcept.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ai.socrates.summarizer.types.uima.WordNetConcept");
 
  /** @generated */
  final Feature casFeat_domains;
  /** @generated */
  final int     casFeatCode_domains;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getDomains(int addr) {
        if (featOkTst && casFeat_domains == null)
      jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.WordNetConcept");
    return ll_cas.ll_getRefValue(addr, casFeatCode_domains);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDomains(int addr, int v) {
        if (featOkTst && casFeat_domains == null)
      jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.WordNetConcept");
    ll_cas.ll_setRefValue(addr, casFeatCode_domains, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public String getDomains(int addr, int i) {
        if (featOkTst && casFeat_domains == null)
      jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.WordNetConcept");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_domains), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_domains), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_domains), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setDomains(int addr, int i, String v) {
        if (featOkTst && casFeat_domains == null)
      jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.WordNetConcept");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_domains), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_domains), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_domains), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public WordNetConcept_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_domains = jcas.getRequiredFeatureDE(casType, "domains", "uima.cas.StringArray", featOkTst);
    casFeatCode_domains  = (null == casFeat_domains) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_domains).getCode();

  }
}



    