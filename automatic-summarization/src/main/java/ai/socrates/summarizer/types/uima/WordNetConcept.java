

/* First created by JCasGen Thu Aug 24 14:00:20 CEST 2017 */
package ai.socrates.summarizer.types.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Sep 13 12:35:36 CEST 2017
 * XML source: C:/Users/nikola/git/automatic-summarization/src/main/resources/descriptors/TypeSystem.xml
 * @generated */
public class WordNetConcept extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(WordNetConcept.class);
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
  protected WordNetConcept() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public WordNetConcept(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public WordNetConcept(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public WordNetConcept(JCas jcas, int begin, int end) {
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
    if (WordNetConcept_Type.featOkTst && ((WordNetConcept_Type)jcasType).casFeat_domains == null)
      jcasType.jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.WordNetConcept");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((WordNetConcept_Type)jcasType).casFeatCode_domains)));}
    
  /** setter for domains - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDomains(StringArray v) {
    if (WordNetConcept_Type.featOkTst && ((WordNetConcept_Type)jcasType).casFeat_domains == null)
      jcasType.jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.WordNetConcept");
    jcasType.ll_cas.ll_setRefValue(addr, ((WordNetConcept_Type)jcasType).casFeatCode_domains, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for domains - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getDomains(int i) {
    if (WordNetConcept_Type.featOkTst && ((WordNetConcept_Type)jcasType).casFeat_domains == null)
      jcasType.jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.WordNetConcept");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((WordNetConcept_Type)jcasType).casFeatCode_domains), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((WordNetConcept_Type)jcasType).casFeatCode_domains), i);}

  /** indexed setter for domains - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setDomains(int i, String v) { 
    if (WordNetConcept_Type.featOkTst && ((WordNetConcept_Type)jcasType).casFeat_domains == null)
      jcasType.jcas.throwFeatMissing("domains", "ai.socrates.summarizer.types.uima.WordNetConcept");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((WordNetConcept_Type)jcasType).casFeatCode_domains), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((WordNetConcept_Type)jcasType).casFeatCode_domains), i, v);}
  }

    