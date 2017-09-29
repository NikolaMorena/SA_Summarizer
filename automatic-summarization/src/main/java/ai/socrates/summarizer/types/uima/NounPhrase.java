

/* First created by JCasGen Thu Aug 24 14:00:19 CEST 2017 */
package ai.socrates.summarizer.types.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Sep 13 12:35:35 CEST 2017
 * XML source: C:/Users/nikola/git/automatic-summarization/src/main/resources/descriptors/TypeSystem.xml
 * @generated */
public class NounPhrase extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NounPhrase.class);
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
  protected NounPhrase() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NounPhrase(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NounPhrase(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NounPhrase(JCas jcas, int begin, int end) {
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
  //* Feature: domains

  /** getter for domains - gets 
   * @generated
   * @return value of the feature 
   */
  public StringArray getDomains() {
    if (NounPhrase_Type.featOkTst && ((NounPhrase_Type)jcasType).casFeat_domains == null)
      jcasType.jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.NounPhrase");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((NounPhrase_Type)jcasType).casFeatCode_domains)));}
    
  /** setter for domains - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDomains(StringArray v) {
    if (NounPhrase_Type.featOkTst && ((NounPhrase_Type)jcasType).casFeat_domains == null)
      jcasType.jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.NounPhrase");
    jcasType.ll_cas.ll_setRefValue(addr, ((NounPhrase_Type)jcasType).casFeatCode_domains, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for domains - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getDomains(int i) {
    if (NounPhrase_Type.featOkTst && ((NounPhrase_Type)jcasType).casFeat_domains == null)
      jcasType.jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.NounPhrase");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((NounPhrase_Type)jcasType).casFeatCode_domains), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((NounPhrase_Type)jcasType).casFeatCode_domains), i);}

  /** indexed setter for domains - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setDomains(int i, String v) { 
    if (NounPhrase_Type.featOkTst && ((NounPhrase_Type)jcasType).casFeat_domains == null)
      jcasType.jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.NounPhrase");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((NounPhrase_Type)jcasType).casFeatCode_domains), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((NounPhrase_Type)jcasType).casFeatCode_domains), i, v);}
  }

    