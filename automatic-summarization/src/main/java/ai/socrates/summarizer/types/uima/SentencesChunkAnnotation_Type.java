
/* First created by JCasGen Thu Sep 07 10:34:31 CEST 2017 */
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
public class SentencesChunkAnnotation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SentencesChunkAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SentencesChunkAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SentencesChunkAnnotation(addr, SentencesChunkAnnotation_Type.this);
  			   SentencesChunkAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SentencesChunkAnnotation(addr, SentencesChunkAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SentencesChunkAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
 
  /** @generated */
  final Feature casFeat_sentencesCount;
  /** @generated */
  final int     casFeatCode_sentencesCount;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentencesCount(int addr) {
        if (featOkTst && casFeat_sentencesCount == null)
      jcas.throwFeatMissing("sentencesCount", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_sentencesCount);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentencesCount(int addr, int v) {
        if (featOkTst && casFeat_sentencesCount == null)
      jcas.throwFeatMissing("sentencesCount", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_sentencesCount, v);}
    
  
 
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
      jcas.throwFeatMissing("seqNumber", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_seqNumber);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSeqNumber(int addr, int v) {
        if (featOkTst && casFeat_seqNumber == null)
      jcas.throwFeatMissing("seqNumber", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
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
      jcas.throwFeatMissing("cumulativeScore", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_cumulativeScore);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCumulativeScore(int addr, float v) {
        if (featOkTst && casFeat_cumulativeScore == null)
      jcas.throwFeatMissing("cumulativeScore", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    ll_cas.ll_setFloatValue(addr, casFeatCode_cumulativeScore, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public SentencesChunkAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sentencesCount = jcas.getRequiredFeatureDE(casType, "sentencesCount", "uima.cas.Integer", featOkTst);
    casFeatCode_sentencesCount  = (null == casFeat_sentencesCount) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentencesCount).getCode();

 
    casFeat_seqNumber = jcas.getRequiredFeatureDE(casType, "seqNumber", "uima.cas.Integer", featOkTst);
    casFeatCode_seqNumber  = (null == casFeat_seqNumber) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_seqNumber).getCode();

 
    casFeat_cumulativeScore = jcas.getRequiredFeatureDE(casType, "cumulativeScore", "uima.cas.Float", featOkTst);
    casFeatCode_cumulativeScore  = (null == casFeat_cumulativeScore) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cumulativeScore).getCode();

  }
}



    