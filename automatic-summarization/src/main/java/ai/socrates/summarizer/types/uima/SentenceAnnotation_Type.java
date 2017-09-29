
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
public class SentenceAnnotation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SentenceAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SentenceAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SentenceAnnotation(addr, SentenceAnnotation_Type.this);
  			   SentenceAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SentenceAnnotation(addr, SentenceAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SentenceAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ai.socrates.summarizer.types.uima.SentenceAnnotation");
 
  /** @generated */
  final Feature casFeat_topTreebankTag;
  /** @generated */
  final int     casFeatCode_topTreebankTag;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTopTreebankTag(int addr) {
        if (featOkTst && casFeat_topTreebankTag == null)
      jcas.throwFeatMissing("topTreebankTag", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_topTreebankTag);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTopTreebankTag(int addr, String v) {
        if (featOkTst && casFeat_topTreebankTag == null)
      jcas.throwFeatMissing("topTreebankTag", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_topTreebankTag, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentenceType;
  /** @generated */
  final int     casFeatCode_sentenceType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSentenceType(int addr) {
        if (featOkTst && casFeat_sentenceType == null)
      jcas.throwFeatMissing("sentenceType", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sentenceType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentenceType(int addr, String v) {
        if (featOkTst && casFeat_sentenceType == null)
      jcas.throwFeatMissing("sentenceType", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_sentenceType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_seqNumber;
  /** @generated */
  final int     casFeatCode_seqNumber;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSeqNumber(int addr) {
        if (featOkTst && casFeat_seqNumber == null)
      jcas.throwFeatMissing("seqNumber", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_seqNumber);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSeqNumber(int addr, int v) {
        if (featOkTst && casFeat_seqNumber == null)
      jcas.throwFeatMissing("seqNumber", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_seqNumber, v);}
    
  
 
  /** @generated */
  final Feature casFeat_cumulativeScore;
  /** @generated */
  final int     casFeatCode_cumulativeScore;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public float getCumulativeScore(int addr) {
        if (featOkTst && casFeat_cumulativeScore == null)
      jcas.throwFeatMissing("cumulativeScore", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_cumulativeScore);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCumulativeScore(int addr, float v) {
        if (featOkTst && casFeat_cumulativeScore == null)
      jcas.throwFeatMissing("cumulativeScore", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    ll_cas.ll_setFloatValue(addr, casFeatCode_cumulativeScore, v);}
    
  
 
  /** @generated */
  final Feature casFeat_selectedForSummary;
  /** @generated */
  final int     casFeatCode_selectedForSummary;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getSelectedForSummary(int addr) {
        if (featOkTst && casFeat_selectedForSummary == null)
      jcas.throwFeatMissing("selectedForSummary", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_selectedForSummary);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSelectedForSummary(int addr, boolean v) {
        if (featOkTst && casFeat_selectedForSummary == null)
      jcas.throwFeatMissing("selectedForSummary", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_selectedForSummary, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public SentenceAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_topTreebankTag = jcas.getRequiredFeatureDE(casType, "topTreebankTag", "uima.cas.String", featOkTst);
    casFeatCode_topTreebankTag  = (null == casFeat_topTreebankTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_topTreebankTag).getCode();

 
    casFeat_sentenceType = jcas.getRequiredFeatureDE(casType, "sentenceType", "uima.cas.String", featOkTst);
    casFeatCode_sentenceType  = (null == casFeat_sentenceType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentenceType).getCode();

 
    casFeat_seqNumber = jcas.getRequiredFeatureDE(casType, "seqNumber", "uima.cas.Integer", featOkTst);
    casFeatCode_seqNumber  = (null == casFeat_seqNumber) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_seqNumber).getCode();

 
    casFeat_cumulativeScore = jcas.getRequiredFeatureDE(casType, "cumulativeScore", "uima.cas.Float", featOkTst);
    casFeatCode_cumulativeScore  = (null == casFeat_cumulativeScore) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cumulativeScore).getCode();

 
    casFeat_selectedForSummary = jcas.getRequiredFeatureDE(casType, "selectedForSummary", "uima.cas.Boolean", featOkTst);
    casFeatCode_selectedForSummary  = (null == casFeat_selectedForSummary) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_selectedForSummary).getCode();

  }
}



    