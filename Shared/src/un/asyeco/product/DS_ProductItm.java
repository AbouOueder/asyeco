/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Document Data Model of Certificate of Origin
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.product;

import static so.kernel.core.KernelEventConstants.DATA_CHANGED;
import static so.kernel.core.KernelEventConstants.DATA_VERIFY;
import static so.kernel.core.KernelEventConstants.DOCUMENT_INIT;
import java.util.Date;

import so.kernel.core.Data;
import so.kernel.core.DataField;
import so.kernel.core.DataSet;
import so.kernel.core.KNumberedSubDocument;
import so.kernel.core.Rule;
import so.kernel.core.TransactionEvent;
import so.kernel.core.interfaces.KDocumentInterface;
import so.kernel.core.rules.KR_DataMandatory;
import un.kernel.core.KDocument;
import un.kernel.core.rules.KR_HTConnectorFactory;
import un.kernel.core.rules.KR_HTSetDateFactory;
import un.kernel.util.AWClientTranslator;


/**
 * Class for the Document Data Model
 */
public final class DS_ProductItm extends KNumberedSubDocument implements C_Product {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6424299664568044900L;

	public DS_ProductItm() {
		super();
	}

	/**
	 * Definition of the Data Model
	 * 
	 */
	public void define_DataModel() {
			
		DataSet criteria = seg(CRITERIA);
		criteria.seg(OP).add(FLG);
		criteria.seg(SW).add(FLG);
		
		DataSet tar = criteria.seg(TAR);
		tar.seg(CHG).add(FLG);
		
		DataSet pva = criteria.seg(PVA);
		pva.add(FLG);
		pva.add(RAT);
		
		DataSet  pck = seg(PCK);
		pck.add(COD);
		pck.add(DSC);
		pck.add(MRK);
		
		DataSet tarr = seg(TAR);
		tarr.add(COD);
		tarr.add(DSC);
		tarr.add(TMP);
		tarr.add(DSCTMP);
		
		DataSet gds = seg(GDS);
		gds.add(DSC);
			
		DataSet sup = seg(SUP);
		sup.add(COD);
		sup.add(DSC);
					
		DataSet scan = seg(SCAN);		
		scan.add(COD);
		scan.add(NAM);
		scan.add(LST); 
		add(FLP1);
		

		define_DataInformation();
		
		// End of the definition of the Data Model
	}

	public void define_DataInformation() {
		setHumanName("");
		
	       //Header
             
        // 3. Criteria determining origin (1)   
        ds(CRITERIA).ds(OP).de(FLG).setHumanName(lng("Goods wholly produced in the Community")); 
        ds(CRITERIA).ds(SW).de(FLG).setHumanName(lng("Goods sufficiently processed or worked")); 
        ds(CRITERIA).ds(TAR).ds(CHG).de(FLG).setHumanName(lng("According to the criterion of changing of tariff position")); 
        ds(CRITERIA).ds(PVA).de(FLG).setHumanName(lng("According to the criterion of value added")); 
              
        ds(CRITERIA).ds(PVA).de(RAT).setHumanName(lng("Rate"));   
        
    	// 4. Quantity, nature, of goods and number of packages 
        ds(PCK).de(COD).setHumanName(lng("Kind of packages code")); 
        ds(PCK).de(DSC).setHumanName(lng("Kind of packages name"));       
        ds(PCK).de(MRK).setHumanName(lng("Mark of goods")); 
        
    	// 5. Tariff and statistical nomenclature       
        ds(TAR).de(COD).setHumanName(lng("Tariff code on 10 digits")); 
        ds(TAR).de(DSC).setHumanName(lng("Commercial description of goods"));       
              
    	// 6. Approval no of goods
        ds(PRODUCT).de(NBR).setHumanName(lng("Number of approval goods"));     
        
    	// 7. Gross weight or other measure
        ds(SUP).de(COD).setHumanName(lng("Unit of measure code")); 
        ds(SUP).de(DSC).setHumanName(lng("Unit of measure description")); 
                
	}

	/*
	 * Definition of the Finder model
	 */
	public void define_FinderModel() {
		//define_Finder(F_eCO.finder);
		
	}

	public void define_DocumentRules() {
		//ds(TAR).de(COD).addRule(R_ChkTar.sharedInstance(), DATA_CHANGED);
		//ds(TAR).de(COD).addRule(R_ChkTar.sharedInstance(), DATA_VERIFY);

	}
	


	/**
	 * Definition of the Reference Model
	 * 
	 */
	public void define_ReferenceModel() {
		// define_Ref(CUO_TAB);
	}

	public void define_CrossImplHTModel() {
		
//		defineHistorizedTable(UOM_TAB, UOM_TAB_EVENT);
//		defineHistorizedTable(PKG_TAB, PKG_TAB_EVENT);
//		defineHistorizedTable(TAR_TAB, TAR_TAB_EVENT);
//		defineHistorizedTable(ATD_TAB, ATD_TAB_EVENT);
//		defineHistorizedTable(PROD_TAB, PROD_TAB_EVENT);

	}

	/**
	 * Definition of the Client Business Rules
	 * 
	 */
	public void define_ClientBusinessRule() {

//		addRule(new R_Init_Document(), DOCUMENT_INIT);
//		//addRule(new R_Init_Document(), GUI_DOCUMENT_INIT);
//		de(WDE).tryToSetContent(new Date());
//		
//		ds(CRITERIA).de(OP).addRule(new R_CheckBoxGroup(), DATA_VERIFY);		
//		ds(CRITERIA).de(SW).addRule(new R_CheckBoxGroup(), DATA_VERIFY);
//		ds(CRITERIA).ds(TAR).addRule(new R_CheckBoxGroup(), DATA_VERIFY);
//		ds(CRITERIA).ds(PVA).addRule(new R_CheckBoxGroup(), DATA_VERIFY);
//
//		
//
//		// *****************Unit of Measure*********************//
//		Rule rule3 = KR_HTSetDateFactory.getSetDateRule(this, UOM_TAB,	new DataField());
//		de(WDE).addRule(rule3, DATA_VERIFY);
//
//		// *****************Kind of Package*********************//
//		Rule rule4 = KR_HTSetDateFactory.getSetDateRule(this, PKG_TAB, new DataField());
//		de(WDE).addRule(rule4, DATA_VERIFY);
//		
//		// *****************Tariff*********************//
//		Rule rule5 = KR_HTSetDateFactory.getSetDateRule(this, TAR_TAB, new DataField());
//		de(WDE).addRule(rule5, DATA_VERIFY);
//
//		// *****************Currency*********************//
//		Rule rule6 = KR_HTSetDateFactory.getSetDateRule(this, CUR_TAB, new DataField());
//		de(WDE).addRule(rule6, DATA_VERIFY);
//		
//		// *****************Attached Documents*********************//
//		Rule rule7 = KR_HTSetDateFactory.getSetDateRule(this, ATD_TAB, new DataField());
//		de(WDE).addRule(rule7, DATA_VERIFY);
//		
//		// *****************Producers*********************//
//		Rule rule8 = KR_HTSetDateFactory.getSetDateRule(this, PROD_TAB, new DataField());
//		de(WDE).addRule(rule8, DATA_VERIFY);
//
//
//
//		ds(TAR).de(COD).setAttachedFinder("TarTab", "TARTABcode", "National Tariff"); // National
//		// tariff
//		ds(TAR).de(COD).getAttachedFinder().addRule(new R_Init_Finder_Historized(de(WDE)));
//		ds(TAR).de(COD).getAttachedFinder().addRule(new R_FillHscTmp(ds(CO).ds(TAR)));
//		ds(TAR).de(COD).addRule(new R_FillCommCode(ds(CO).ds(TAR)), DATA_CHANGED);
//		
//
//		// Business logic groups
//		define_MandatoryDataRule();
//		define_ReferenceDataRule();
//		define_AttachedFinderRule();
//		define_DocumentRules();
//		//define_MultiItemManagementRule() ;
	}

	public void define_ReferenceDataRule() {

	}

	public void define_MandatoryDataRule() {
		
      
    	// 4. Quantity, nature, of goods and number of packages       
        ds(PCK).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);
        ds(PCK).de(MRK).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);       
    	// 5. Tariff and statistical nomenclature       
        ds(TAR).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY); 
        ds(TAR).de(DSC).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);               
    	// 6. Approval no of goods
        //ds(PRODUCT).de(NBR).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);                
    	// 7. Gross weight or other measure 
        ds(SUP).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);        
	}

	public void define_AttachedFinderRule() {

	//	ds(BAG).ds(CMP).de(COD).setAttachedFinder("AsyRefUNCMPTABView",	"cmpcode", lng("Company")); // Company

	}

	/*public void define_MultiItemManagementRule() {

		// Manage the item (ITM) sub-document
		KR_NumberedSubDocumentManager rule = new KR_NumberedSubDocumentManager(ds(ITM), ACT_ITM_NEW, ACT_ITM_DEL);
		addRule(rule, ACT_ITM_NEW);
		addRule(rule, ACT_ITM_DEL);
	}*/


	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
