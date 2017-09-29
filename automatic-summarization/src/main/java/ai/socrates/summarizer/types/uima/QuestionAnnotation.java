

/* First created by JCasGen Thu Aug 24 14:00:19 CEST 2017 */
package ai.socrates.summarizer.types.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Wed Sep 13 12:35:36 CEST 2017
 * XML source: C:/Users/nikola/git/automatic-summarization/src/main/resources/descriptors/TypeSystem.xml
 * @generated */
public class QuestionAnnotation extends SentenceAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(QuestionAnnotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected QuestionAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public QuestionAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public QuestionAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public QuestionAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: questionType

  /** getter for questionType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getQuestionType() {
    if (QuestionAnnotation_Type.featOkTst && ((QuestionAnnotation_Type)jcasType).casFeat_questionType == null)
      jcasType.jcas.throwFeatMissing("questionType", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((QuestionAnnotation_Type)jcasType).casFeatCode_questionType);}
    
  /** setter for questionType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestionType(String v) {
    if (QuestionAnnotation_Type.featOkTst && ((QuestionAnnotation_Type)jcasType).casFeat_questionType == null)
      jcasType.jcas.throwFeatMissing("questionType", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((QuestionAnnotation_Type)jcasType).casFeatCode_questionType, v);}    
   
    
  //*--------------*
  //* Feature: answerClass

  /** getter for answerClass - gets 
   * @generated
   * @return value of the feature 
   */
  public String getAnswerClass() {
    if (QuestionAnnotation_Type.featOkTst && ((QuestionAnnotation_Type)jcasType).casFeat_answerClass == null)
      jcasType.jcas.throwFeatMissing("answerClass", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((QuestionAnnotation_Type)jcasType).casFeatCode_answerClass);}
    
  /** setter for answerClass - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswerClass(String v) {
    if (QuestionAnnotation_Type.featOkTst && ((QuestionAnnotation_Type)jcasType).casFeat_answerClass == null)
      jcasType.jcas.throwFeatMissing("answerClass", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((QuestionAnnotation_Type)jcasType).casFeatCode_answerClass, v);}    
   
    
  //*--------------*
  //* Feature: factuality

  /** getter for factuality - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFactuality() {
    if (QuestionAnnotation_Type.featOkTst && ((QuestionAnnotation_Type)jcasType).casFeat_factuality == null)
      jcasType.jcas.throwFeatMissing("factuality", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((QuestionAnnotation_Type)jcasType).casFeatCode_factuality);}
    
  /** setter for factuality - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFactuality(String v) {
    if (QuestionAnnotation_Type.featOkTst && ((QuestionAnnotation_Type)jcasType).casFeat_factuality == null)
      jcasType.jcas.throwFeatMissing("factuality", "ai.socrates.summarizer.types.uima.QuestionAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((QuestionAnnotation_Type)jcasType).casFeatCode_factuality, v);}    
  }

    