

/* First created by JCasGen Thu Aug 24 14:00:19 CEST 2017 */
package ai.socrates.summarizer.types.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Sep 13 12:35:36 CEST 2017
 * XML source: C:/Users/nikola/git/automatic-summarization/src/main/resources/descriptors/TypeSystem.xml
 * @generated */
public class SentenceAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SentenceAnnotation.class);
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
  protected SentenceAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public SentenceAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public SentenceAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public SentenceAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: topTreebankTag

  /** getter for topTreebankTag - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTopTreebankTag() {
    if (SentenceAnnotation_Type.featOkTst && ((SentenceAnnotation_Type)jcasType).casFeat_topTreebankTag == null)
      jcasType.jcas.throwFeatMissing("topTreebankTag", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SentenceAnnotation_Type)jcasType).casFeatCode_topTreebankTag);}
    
  /** setter for topTreebankTag - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTopTreebankTag(String v) {
    if (SentenceAnnotation_Type.featOkTst && ((SentenceAnnotation_Type)jcasType).casFeat_topTreebankTag == null)
      jcasType.jcas.throwFeatMissing("topTreebankTag", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SentenceAnnotation_Type)jcasType).casFeatCode_topTreebankTag, v);}    
   
    
  //*--------------*
  //* Feature: sentenceType

  /** getter for sentenceType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSentenceType() {
    if (SentenceAnnotation_Type.featOkTst && ((SentenceAnnotation_Type)jcasType).casFeat_sentenceType == null)
      jcasType.jcas.throwFeatMissing("sentenceType", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SentenceAnnotation_Type)jcasType).casFeatCode_sentenceType);}
    
  /** setter for sentenceType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceType(String v) {
    if (SentenceAnnotation_Type.featOkTst && ((SentenceAnnotation_Type)jcasType).casFeat_sentenceType == null)
      jcasType.jcas.throwFeatMissing("sentenceType", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SentenceAnnotation_Type)jcasType).casFeatCode_sentenceType, v);}    
   
    
  //*--------------*
  //* Feature: seqNumber

  /** getter for seqNumber - gets 
   * @generated
   * @return value of the feature 
   */
  public int getSeqNumber() {
    if (SentenceAnnotation_Type.featOkTst && ((SentenceAnnotation_Type)jcasType).casFeat_seqNumber == null)
      jcasType.jcas.throwFeatMissing("seqNumber", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SentenceAnnotation_Type)jcasType).casFeatCode_seqNumber);}
    
  /** setter for seqNumber - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSeqNumber(int v) {
    if (SentenceAnnotation_Type.featOkTst && ((SentenceAnnotation_Type)jcasType).casFeat_seqNumber == null)
      jcasType.jcas.throwFeatMissing("seqNumber", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((SentenceAnnotation_Type)jcasType).casFeatCode_seqNumber, v);}    
   
    
  //*--------------*
  //* Feature: cumulativeScore

  /** getter for cumulativeScore - gets 
   * @generated
   * @return value of the feature 
   */
  public float getCumulativeScore() {
    if (SentenceAnnotation_Type.featOkTst && ((SentenceAnnotation_Type)jcasType).casFeat_cumulativeScore == null)
      jcasType.jcas.throwFeatMissing("cumulativeScore", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((SentenceAnnotation_Type)jcasType).casFeatCode_cumulativeScore);}
    
  /** setter for cumulativeScore - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCumulativeScore(float v) {
    if (SentenceAnnotation_Type.featOkTst && ((SentenceAnnotation_Type)jcasType).casFeat_cumulativeScore == null)
      jcasType.jcas.throwFeatMissing("cumulativeScore", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    jcasType.ll_cas.ll_setFloatValue(addr, ((SentenceAnnotation_Type)jcasType).casFeatCode_cumulativeScore, v);}    
   
    
  //*--------------*
  //* Feature: selectedForSummary

  /** getter for selectedForSummary - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getSelectedForSummary() {
    if (SentenceAnnotation_Type.featOkTst && ((SentenceAnnotation_Type)jcasType).casFeat_selectedForSummary == null)
      jcasType.jcas.throwFeatMissing("selectedForSummary", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((SentenceAnnotation_Type)jcasType).casFeatCode_selectedForSummary);}
    
  /** setter for selectedForSummary - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSelectedForSummary(boolean v) {
    if (SentenceAnnotation_Type.featOkTst && ((SentenceAnnotation_Type)jcasType).casFeat_selectedForSummary == null)
      jcasType.jcas.throwFeatMissing("selectedForSummary", "ai.socrates.summarizer.types.uima.SentenceAnnotation");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((SentenceAnnotation_Type)jcasType).casFeatCode_selectedForSummary, v);}    
  }

    