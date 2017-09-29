

/* First created by JCasGen Thu Sep 07 10:34:31 CEST 2017 */
package ai.socrates.summarizer.types.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Sep 13 12:35:36 CEST 2017
 * XML source: C:/Users/nikola/git/automatic-summarization/src/main/resources/descriptors/TypeSystem.xml
 * @generated */
public class SentencesChunkAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SentencesChunkAnnotation.class);
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
  protected SentencesChunkAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public SentencesChunkAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public SentencesChunkAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public SentencesChunkAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: sentencesCount

  /** getter for sentencesCount - gets 
   * @generated
   * @return value of the feature 
   */
  public int getSentencesCount() {
    if (SentencesChunkAnnotation_Type.featOkTst && ((SentencesChunkAnnotation_Type)jcasType).casFeat_sentencesCount == null)
      jcasType.jcas.throwFeatMissing("sentencesCount", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SentencesChunkAnnotation_Type)jcasType).casFeatCode_sentencesCount);}
    
  /** setter for sentencesCount - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentencesCount(int v) {
    if (SentencesChunkAnnotation_Type.featOkTst && ((SentencesChunkAnnotation_Type)jcasType).casFeat_sentencesCount == null)
      jcasType.jcas.throwFeatMissing("sentencesCount", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((SentencesChunkAnnotation_Type)jcasType).casFeatCode_sentencesCount, v);}    
   
    
  //*--------------*
  //* Feature: seqNumber

  /** getter for seqNumber - gets 
   * @generated
   * @return value of the feature 
   */
  public int getSeqNumber() {
    if (SentencesChunkAnnotation_Type.featOkTst && ((SentencesChunkAnnotation_Type)jcasType).casFeat_seqNumber == null)
      jcasType.jcas.throwFeatMissing("seqNumber", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SentencesChunkAnnotation_Type)jcasType).casFeatCode_seqNumber);}
    
  /** setter for seqNumber - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSeqNumber(int v) {
    if (SentencesChunkAnnotation_Type.featOkTst && ((SentencesChunkAnnotation_Type)jcasType).casFeat_seqNumber == null)
      jcasType.jcas.throwFeatMissing("seqNumber", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((SentencesChunkAnnotation_Type)jcasType).casFeatCode_seqNumber, v);}    
   
    
  //*--------------*
  //* Feature: cumulativeScore

  /** getter for cumulativeScore - gets 
   * @generated
   * @return value of the feature 
   */
  public float getCumulativeScore() {
    if (SentencesChunkAnnotation_Type.featOkTst && ((SentencesChunkAnnotation_Type)jcasType).casFeat_cumulativeScore == null)
      jcasType.jcas.throwFeatMissing("cumulativeScore", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((SentencesChunkAnnotation_Type)jcasType).casFeatCode_cumulativeScore);}
    
  /** setter for cumulativeScore - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCumulativeScore(float v) {
    if (SentencesChunkAnnotation_Type.featOkTst && ((SentencesChunkAnnotation_Type)jcasType).casFeat_cumulativeScore == null)
      jcasType.jcas.throwFeatMissing("cumulativeScore", "ai.socrates.summarizer.types.uima.SentencesChunkAnnotation");
    jcasType.ll_cas.ll_setFloatValue(addr, ((SentencesChunkAnnotation_Type)jcasType).casFeatCode_cumulativeScore, v);}    
  }

    