

/* First created by JCasGen Thu Aug 24 14:00:20 CEST 2017 */
package ai.socrates.summarizer.types.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Sep 13 12:35:36 CEST 2017
 * XML source: C:/Users/nikola/git/automatic-summarization/src/main/resources/descriptors/TypeSystem.xml
 * @generated */
public class TokenAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TokenAnnotation.class);
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
  protected TokenAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public TokenAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public TokenAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public TokenAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: tokenType

  /** getter for tokenType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTokenType() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_tokenType == null)
      jcasType.jcas.throwFeatMissing("tokenType", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_tokenType);}
    
  /** setter for tokenType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTokenType(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_tokenType == null)
      jcasType.jcas.throwFeatMissing("tokenType", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_tokenType, v);}    
   
    
  //*--------------*
  //* Feature: lemma

  /** getter for lemma - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLemma() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLemma(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_lemma, v);}    
   
    
  //*--------------*
  //* Feature: isStopWord

  /** getter for isStopWord - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getIsStopWord() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_isStopWord == null)
      jcasType.jcas.throwFeatMissing("isStopWord", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_isStopWord);}
    
  /** setter for isStopWord - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setIsStopWord(boolean v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_isStopWord == null)
      jcasType.jcas.throwFeatMissing("isStopWord", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_isStopWord, v);}    
   
    
  //*--------------*
  //* Feature: isSearchStopWord

  /** getter for isSearchStopWord - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getIsSearchStopWord() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_isSearchStopWord == null)
      jcasType.jcas.throwFeatMissing("isSearchStopWord", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_isSearchStopWord);}
    
  /** setter for isSearchStopWord - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setIsSearchStopWord(boolean v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_isSearchStopWord == null)
      jcasType.jcas.throwFeatMissing("isSearchStopWord", "ai.socrates.summarizer.types.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_isSearchStopWord, v);}    
  }

    