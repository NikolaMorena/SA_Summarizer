
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
public class TokenAnnotation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TokenAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TokenAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TokenAnnotation(addr, TokenAnnotation_Type.this);
  			   TokenAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TokenAnnotation(addr, TokenAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TokenAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ai.socrates.summarizer.types.uima.TokenAnnotation");
 
  /** @generated */
  final Feature casFeat_tokenType;
  /** @generated */
  final int     casFeatCode_tokenType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTokenType(int addr) {
        if (featOkTst && casFeat_tokenType == null)
      jcas.throwFeatMissing("tokenType", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_tokenType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTokenType(int addr, String v) {
        if (featOkTst && casFeat_tokenType == null)
      jcas.throwFeatMissing("tokenType", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_tokenType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lemma;
  /** @generated */
  final int     casFeatCode_lemma;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getLemma(int addr) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lemma);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLemma(int addr, String v) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_lemma, v);}
    
  
 
  /** @generated */
  final Feature casFeat_isStopWord;
  /** @generated */
  final int     casFeatCode_isStopWord;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getIsStopWord(int addr) {
        if (featOkTst && casFeat_isStopWord == null)
      jcas.throwFeatMissing("isStopWord", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_isStopWord);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIsStopWord(int addr, boolean v) {
        if (featOkTst && casFeat_isStopWord == null)
      jcas.throwFeatMissing("isStopWord", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_isStopWord, v);}
    
  
 
  /** @generated */
  final Feature casFeat_isSearchStopWord;
  /** @generated */
  final int     casFeatCode_isSearchStopWord;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getIsSearchStopWord(int addr) {
        if (featOkTst && casFeat_isSearchStopWord == null)
      jcas.throwFeatMissing("isSearchStopWord", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_isSearchStopWord);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIsSearchStopWord(int addr, boolean v) {
        if (featOkTst && casFeat_isSearchStopWord == null)
      jcas.throwFeatMissing("isSearchStopWord", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_isSearchStopWord, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public TokenAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_tokenType = jcas.getRequiredFeatureDE(casType, "tokenType", "uima.cas.String", featOkTst);
    casFeatCode_tokenType  = (null == casFeat_tokenType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tokenType).getCode();

 
    casFeat_lemma = jcas.getRequiredFeatureDE(casType, "lemma", "uima.cas.String", featOkTst);
    casFeatCode_lemma  = (null == casFeat_lemma) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemma).getCode();

 
    casFeat_isStopWord = jcas.getRequiredFeatureDE(casType, "isStopWord", "uima.cas.Boolean", featOkTst);
    casFeatCode_isStopWord  = (null == casFeat_isStopWord) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_isStopWord).getCode();

 
    casFeat_isSearchStopWord = jcas.getRequiredFeatureDE(casType, "isSearchStopWord", "uima.cas.Boolean", featOkTst);
    casFeatCode_isSearchStopWord  = (null == casFeat_isSearchStopWord) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_isSearchStopWord).getCode();

  }
}



    