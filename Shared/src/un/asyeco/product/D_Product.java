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
public final class D_Product extends KDocument implements C_Product {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6424299664568044900L;

	public D_Product() {
		super("Product");
	}

	/**
	 * Definition of the Data Model
	 * 
	 */
	public void define_DataModel() {
		key(INSTANCE_ID);

		AWClientTranslator.setLocale(this);
		
		add(WDE);
		//add(LNG);

		DataSet ide = seg(IDE);		
		DataSet expired = ide.seg(EXPIRED);
		expired.add(DAT);
		
		DataSet cty = seg(CTY);
		cty.add(COD);
		cty.add(DSC);
		
		DataSet cmp = seg(CMP);
		DataSet prod = cmp.seg(PROD);
		prod.add(COD);
		prod.add(NAM);
		prod.add(CTY);
		prod.add(ADR);
		prod.add(ADR2);
		prod.add(TEL);
		prod.add(EMAIL);
		prod.add(WSITE);
		
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
        ds(CTY).de(COD).setHumanName(lng("Country code of Certificate of origin")); 
        ds(CTY).de(DSC).setHumanName(lng("Country name of Certificate of origin"));       
          
        // 1. Producer informations
        ds(CMP).ds(PROD).de(COD).setHumanName(lng("Registration N° of producer"));   
        ds(CMP).ds(PROD).de(NAM).setHumanName(lng("Name of producer"));   
        ds(CMP).ds(PROD).de(ADR).setHumanName(lng("Address of producer"));     
        ds(CMP).ds(PROD).de(ADR2).setHumanName(lng("Postal code of producer"));  
        ds(CMP).ds(PROD).de(TEL).setHumanName(lng("Telephone of producer"));         
        ds(CMP).ds(PROD).de(EMAIL).setHumanName(lng("Email")); 
        ds(CMP).ds(PROD).de(WSITE).setHumanName(lng("Web site address")); 
        
	}

	/*
	 * Definition of the Finder model
	 */
	public void define_FinderModel() {
		//define_Finder(F_eCO.finder);
		
	}

	public void define_DocumentRules() {


	}
	


	/**
	 * Definition of the Reference Model
	 * 
	 */
	public void define_ReferenceModel() {
		// define_Ref(CUO_TAB);
	}

	public void define_CrossImplHTModel() {
		
		defineHistorizedTable(CUO_TAB, CUO_TAB_EVENT);
		defineHistorizedTable(CTY_TAB, CTY_TAB_EVENT);
		defineHistorizedTable(UOM_TAB, UOM_TAB_EVENT);
		defineHistorizedTable(PKG_TAB, PKG_TAB_EVENT);
		defineHistorizedTable(TAR_TAB, TAR_TAB_EVENT);
		defineHistorizedTable(CUR_TAB, CUR_TAB_EVENT);
		defineHistorizedTable(ATD_TAB, ATD_TAB_EVENT);
		defineHistorizedTable(PROD_TAB, PROD_TAB_EVENT);

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
//		// *****************Customs Office*********************//
//		Rule ruleDateOffice = KR_HTSetDateFactory.getSetDateRule(this, CUO_TAB,	new DataField());
//		de(WDE).addRule(ruleDateOffice, DATA_VERIFY);
//
//		// *****************Country*********************//
//		Rule rule2 = KR_HTSetDateFactory.getSetDateRule(this, CTY_TAB,	new DataField());
//		de(WDE).addRule(rule2, DATA_VERIFY);
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
//		ds(CO).ds(TAR).de(COD).setAttachedFinder("TarTab", "TARTABcode", "National Tariff"); // National
//		// tariff
//		ds(TAR).de(COD).getAttachedFinder().addRule(new R_Init_Finder_Historized(de(WDE)));
//		ds(TAR).de(COD).getAttachedFinder().addRule(new R_FillHscTmp(ds(CO).ds(TAR)));
//		ds(TAR).de(COD).addRule(new R_FillCommCode(ds(CO).ds(TAR)), DATA_CHANGED);
//		

		// Business logic groups
		define_MandatoryDataRule();
		define_ReferenceDataRule();
		define_AttachedFinderRule();
		define_DocumentRules();
		//define_MultiItemManagementRule() ;
	}

	public void define_ReferenceDataRule() {

	}

	public void define_MandatoryDataRule() {
		
      
        ds(CTY).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);        
         
        // 1. Producer informations
        ds(CMP).ds(PROD).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);          
        
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

	@Override
	public TransactionEvent applyMiddleEvent(int id, DataSet data) {

		if (data != null && data.de(AWClientTranslator.LNG_DATA_NAME) == null && de(AWClientTranslator.LNG_DATA_NAME) != null) {
			data.add(AWClientTranslator.LNG_DATA_NAME).copyFrom(de(AWClientTranslator.LNG_DATA_NAME));
			data.add(AWClientTranslator.LCT_DATA_NAME).copyFrom(de(AWClientTranslator.LCT_DATA_NAME));
		}
		//		DebugOutput.print("ApplyMiddleEvent Event ID : " + id);
		return super.applyMiddleEvent(id, data);
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
	
}
