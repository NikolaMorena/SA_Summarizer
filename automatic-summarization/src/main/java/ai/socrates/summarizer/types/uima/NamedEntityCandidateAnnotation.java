

/* First created by JCasGen Thu Aug 24 14:00:19 CEST 2017 */
package ai.socrates.summarizer.types.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Sep 13 12:35:35 CEST 2017
 * XML source: C:/Users/nikola/git/automatic-summarization/src/main/resources/descriptors/TypeSystem.xml
 * @generated */
public class NamedEntityCandidateAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NamedEntityCandidateAnnotation.class);
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
  protected NamedEntityCandidateAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NamedEntityCandidateAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NamedEntityCandidateAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NamedEntityCandidateAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: entityType

  /** getter for entityType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getEntityType() {
    if (NamedEntityCandidateAnnotation_Type.featOkTst && ((NamedEntityCandidateAnnotation_Type)jcasType).casFeat_entityType == null)
      jcasType.jcas.throwFeatMissing("entityType", "ai.socrates.summarizer.types.uima.NamedEntityCandidateAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntityCandidateAnnotation_Type)jcasType).casFeatCode_entityType);}
    
  /** setter for entityType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEntityType(String v) {
    if (NamedEntityCandidateAnnotation_Type.featOkTst && ((NamedEntityCandidateAnnotation_Type)jcasType).casFeat_entityType == null)
      jcasType.jcas.throwFeatMissing("entityType", "ai.socrates.summarizer.types.uima.NamedEntityCandidateAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntityCandidateAnnotation_Type)jcasType).casFeatCode_entityType, v);}    
   
    
  //*--------------*
  //* Feature: detectionSource

  /** getter for detectionSource - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDetectionSource() {
    if (NamedEntityCandidateAnnotation_Type.featOkTst && ((NamedEntityCandidateAnnotation_Type)jcasType).casFeat_detectionSource == null)
      jcasType.jcas.throwFeatMissing("detectionSource", "ai.socrates.summarizer.types.uima.NamedEntityCandidateAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntityCandidateAnnotation_Type)jcasType).casFeatCode_detectionSource);}
    
  /** setter for detectionSource - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDetectionSource(String v) {
    if (NamedEntityCandidateAnnotation_Type.featOkTst && ((NamedEntityCandidateAnnotation_Type)jcasType).casFeat_detectionSource == null)
      jcasType.jcas.throwFeatMissing("detectionSource", "ai.socrates.summarizer.types.uima.NamedEntityCandidateAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntityCandidateAnnotation_Type)jcasType).casFeatCode_detectionSource, v);}    
  }

    