
/* First created by JCasGen Thu Aug 24 14:00:19 CEST 2017 */
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

/** 
 * Updated by JCasGen Wed Sep 13 12:35:36 CEST 2017
 * @generated */
public class QuestionAnnotation_Type extends SentenceAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (QuestionAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = QuestionAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new QuestionAnnotation(addr, QuestionAnnotation_Type.this);
  			   QuestionAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new QuestionAnnotation(addr, QuestionAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = QuestionAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ai.socrates.summarizer.types.uima.QuestionAnnotation");
 
  /** @generated */
  final Feature casFeat_questionType;
  /** @generated */
  final int     casFeatCode_questionType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getQuestionType(int addr) {
        if (featOkTst && casFeat_questionType == null)
      jcas.throwFeatMissing("questionType", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_questionType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuestionType(int addr, String v) {
        if (featOkTst && casFeat_questionType == null)
      jcas.throwFeatMissing("questionType", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_questionType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answerClass;
  /** @generated */
  final int     casFeatCode_answerClass;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnswerClass(int addr) {
        if (featOkTst && casFeat_answerClass == null)
      jcas.throwFeatMissing("answerClass", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_answerClass);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswerClass(int addr, String v) {
        if (featOkTst && casFeat_answerClass == null)
      jcas.throwFeatMissing("answerClass", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_answerClass, v);}
    
  
 
  /** @generated */
  final Feature casFeat_factuality;
  /** @generated */
  final int     casFeatCode_factuality;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getFactuality(int addr) {
        if (featOkTst && casFeat_factuality == null)
      jcas.throwFeatMissing("factuality", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_factuality);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFactuality(int addr, String v) {
        if (featOkTst && casFeat_factuality == null)
      jcas.throwFeatMissing("factuality", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_factuality, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public QuestionAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_questionType = jcas.getRequiredFeatureDE(casType, "questionType", "uima.cas.String", featOkTst);
    casFeatCode_questionType  = (null == casFeat_questionType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_questionType).getCode();

 
    casFeat_answerClass = jcas.getRequiredFeatureDE(casType, "answerClass", "uima.cas.String", featOkTst);
    casFeatCode_answerClass  = (null == casFeat_answerClass) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answerClass).getCode();

 
    casFeat_factuality = jcas.getRequiredFeatureDE(casType, "factuality", "uima.cas.String", featOkTst);
    casFeatCode_factuality  = (null == casFeat_factuality) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_factuality).getCode();

  }
}



    