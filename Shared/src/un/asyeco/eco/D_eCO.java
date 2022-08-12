/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Document Data Model of Certificate of Origin
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.eco;

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
public final class D_eCO extends KDocument implements C_eCO {

	private static final long serialVersionUID = -6377970141963325697L;


	public D_eCO() {
		super("Certificate of Origin");
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

		DataSet co = seg(CO);
		co.add(NBR);
		co.seg(REG);
		co.ds(REG).add(DAT);
		co.ds(REG).add(YER);
		co.ds(REG).add(SER);
		co.add(CRN);
		
		co.seg(SUB);
		co.ds(SUB).add(YER);
		co.ds(SUB).add(SER);
		co.ds(SUB).add(NBR);
		co.ds(SUB).add(DAT);
		
		
		
		
		DataSet expired = co.seg(EXPIRED);
		expired.add(DAT);
		
		DataSet cty = co.seg(CTY);
		cty.add(COD);
		cty.add(DSC);
		
		DataSet cmp = co.seg(CMP);
		DataSet prod = cmp.seg(PROD);
		prod.add(COD);
		prod.add(NAM);
		prod.add(CTY);
		prod.add(ADR);
		prod.add(TEL);
		prod.add(EMAIL);
		
		DataSet cons = cmp.seg(CONS);
		cons.add(NAM);
		cons.add(CTY);
		cons.add(ADR);
		cons.add(TEL);
		cons.add(EMAIL);
		
		DataSet criteria = co.seg(CRITERIA);
		criteria.add(OP);
		criteria.add(SW);
		
		DataSet tar = criteria.seg(TAR);
		tar.add(CHG);
		
		DataSet pva = criteria.seg(PVA);
		pva.add(FLG);
		pva.add(RAT);
		
		DataSet  pck = co.seg(PCK);
		pck.add(NBR);
		pck.add(COD);
		pck.add(DSC);
		pck.add(MRK);
		
		DataSet tarr = co.seg(TAR);
		tarr.add(COD);
		tarr.add(DSC);
		tarr.add(TMP);
		tarr.add(DSCTMP);
		
		DataSet gds = co.seg(GDS);
		gds.add(DSC);
		
		DataSet product = co.seg(PRODUCT);
		product.add(NBR);
		
		DataSet wgt = co.seg(WGT);
		wgt.add(GRS);
		
		DataSet sup = co.seg(SUP);
		sup.add(COD);
		sup.add(DSC);
		
		DataSet inv = co.seg(INV);
		inv.add(AMT);
		
		DataSet cur = co.seg(CUR);
		cur.add(COD);
		
		DataSet exp = cmp.seg(EXP);
		exp.add(NAM);
		exp.add(CTY);

		DataSet sign = exp.seg(SIGN);
		sign.add(DAT);
		
		DataSet auth = co.seg(AUTH);
		auth.add(NAM);
		auth.add(CTY);
		auth.seg(SIGN);
		auth.ds(SIGN).add(DAT);
		
		DataSet cuo = co.seg(CUO);
		cuo.add(COD);
		cuo.add(NAM);
		
		DataSet dau = co.seg(DAU);
		dau.add(MOD);
		dau.seg(REG);
		dau.ds(REG).add(NBR);
		dau.ds(REG).add(DAT);
		
		DataSet dpa = seg(DPA);
		DataSet exa = dpa.seg(EXA);
		exa.add(COD);
		exa.add(NAM);
		exa.add(CTY);
		exa.seg(SIGN);
		exa.ds(SIGN).add(DAT);
	
		dpa.seg(CUO);
		dpa.ds(CUO).add(COD);
		dpa.ds(CUO).add(NAM);
		dpa.ds(CUO).add(ADR);
		
		DataSet dest = seg(DEST);
		dest.seg(EXA);
		dest.ds(EXA).add(COD);
		dest.ds(EXA).add(NAM);
		dest.ds(EXA).add(CTY);
		dest.ds(EXA).seg(SIGN);
		dest.ds(EXA).ds(SIGN).add(DAT);
		
		DataSet ctl = dpa.seg(CTL);
		ctl.seg(EXA);
		ctl.ds(EXA).add(COD);
		ctl.ds(EXA).add(NAM);
		ctl.ds(EXA).add(CTY);
		ctl.ds(EXA).seg(SIGN);
		ctl.ds(EXA).ds(SIGN).add(DAT);
		ctl.seg(RSLT);
		ctl.ds(RSLT).add(CORR);
		ctl.ds(RSLT).add(NOTCORRECT);
		
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
        ds(CO).ds(CTY).de(COD).setHumanName(lng("Country code of Certificate of origin")); 
        ds(CO).ds(CTY).de(DSC).setHumanName(lng("Country name of Certificate of origin"));       
        
        ds(CO).de(NBR).setHumanName(lng("Number of Certificate of origin"));  
        ds(CO).ds(REG).de(DAT).setHumanName(lng("Register date of Certificate"));  
        ds(CO).ds(REG).de(YER).setHumanName(lng("Register year of Certificate")); 
        ds(CO).ds(REG).de(SER).setHumanName(lng("Register serial")); 
        ds(CO).ds(EXPIRED).de(DAT).setHumanName(lng("Expiration date of Certificate"));
        
        ds(CO).ds(SUB).de(NBR).setHumanName(lng("Submission Number of Certificate of origin"));  
        ds(CO).ds(SUB).de(DAT).setHumanName(lng("Submission date of Certificate"));  
        ds(CO).ds(SUB).de(YER).setHumanName(lng("Submission year of Certificate"));  
        ds(CO).ds(SUB).de(DAT).setHumanName(lng("Submission date of Certificate"));
        
        // 1. Producer informations
        ds(CO).ds(CMP).ds(PROD).de(COD).setHumanName(lng("Registration N° of producer"));   
        ds(CO).ds(CMP).ds(PROD).de(NAM).setHumanName(lng("Name of producer"));   
        ds(CO).ds(CMP).ds(PROD).de(ADR).setHumanName(lng("Address of producer"));       
        ds(CO).ds(CMP).ds(PROD).de(TEL).setHumanName(lng("Telephone of producer"));         
        ds(CO).ds(CMP).ds(PROD).de(EMAIL).setHumanName(lng("Email")); 
        
        // 2. Consignee
        ds(CO).ds(CMP).ds(CONS).de(NAM).setHumanName(lng("Name"));       
        ds(CO).ds(CMP).ds(CONS).de(ADR).setHumanName(lng("Address"));       
        ds(CO).ds(CMP).ds(CONS).de(TEL).setHumanName(lng("Telephone"));      
        ds(CO).ds(CMP).ds(CONS).de(EMAIL).setHumanName(lng("Email")); 
        
        
        // 3. Criteria determining origin (1)   
        ds(CO).ds(CRITERIA).de(OP).setHumanName(lng("Goods wholly produced in the Community")); 
        ds(CO).ds(CRITERIA).de(SW).setHumanName(lng("Goods sufficiently processed or worked")); 
        ds(CO).ds(CRITERIA).ds(TAR).de(CHG).setHumanName(lng("According to the criterion of changing of tariff position")); 
        ds(CO).ds(CRITERIA).ds(PVA).de(FLG).setHumanName(lng("According to the criterion of value added")); 
              
        ds(CO).ds(CRITERIA).ds(PVA).de(RAT).setHumanName(lng("Rate"));   
        
    	// 4. Quantity, nature, of goods and number of packages
        ds(CO).ds(PCK).de(NBR).setHumanName(lng("Quantity of packages")); 
        ds(CO).ds(PCK).de(COD).setHumanName(lng("Kind of packages code")); 
        ds(CO).ds(PCK).de(DSC).setHumanName(lng("Kind of packages name"));       
        ds(CO).ds(PCK).de(MRK).setHumanName(lng("Mark of goods")); 
        
    	// 5. Tariff and statistical nomenclature       
        ds(CO).ds(TAR).de(COD).setHumanName(lng("Tariff code on 10 digits")); 
        ds(CO).ds(TAR).de(DSC).setHumanName(lng("Commercial description of goods"));       
              
    	// 6. Approval no of goods
        ds(CO).ds(PRODUCT).de(NBR).setHumanName(lng("Number of approval goods"));     
        
    	// 7. Gross weight or other measure

        ds(CO).ds(WGT).de(GRS).setHumanName(lng("Gross mass (Kgs)")); 
        ds(CO).ds(SUP).de(COD).setHumanName(lng("Unit of measure code")); 
        ds(CO).ds(SUP).de(DSC).setHumanName(lng("Unit of measure description")); 
                
    	// 8. Invoice value      
        ds(CO).ds(INV).de(AMT).setHumanName(lng("Invoice Amount"));    
        ds(CO).ds(CUR).de(COD).setHumanName(lng("Currency code")); 
        
    	// 9. Declaration by exporter      
        ds(CO).ds(CMP).ds(EXP).de(NAM).setHumanName(lng("Name of exporter")); 
        ds(CO).ds(CMP).ds(EXP).de(CTY).setHumanName(lng("Place of signature of exporter")); 
        ds(CO).ds(CMP).ds(EXP).ds(SIGN).de(DAT).setHumanName(lng("Date of signature of exporter")); 
 
    	// 10. Certification by the appropriate authority
        ds(CO).ds(AUTH).de(NAM).setHumanName(lng("Name of Appropriate Authority")); 
        ds(CO).ds(AUTH).de(CTY).setHumanName(lng("Place of Appropriate Authority")); 
        ds(CO).ds(AUTH).ds(SIGN).de(DAT).setHumanName(lng("Date of signature")); 

    	// 11. Certification by customs 
        ds(CO).ds(CUO).de(COD).setHumanName(lng("Customs office code")); 
        ds(CO).ds(CUO).de(NAM).setHumanName(lng("Customs office name")); 
        ds(CO).ds(DAU).de(MOD).setHumanName(lng("Model of DAU")); 
        ds(CO).ds(DAU).ds(REG).de(NBR).setHumanName(lng("Register number of DAU")); 
        ds(CO).ds(DAU).ds(REG).de(DAT).setHumanName(lng("Register date of DAU")); 
        ds(DPA).ds(EXA).de(COD).setHumanName(lng("Examiner Code")); 
        ds(DPA).ds(EXA).de(NAM).setHumanName(lng("Examiner Name")); 
        ds(DPA).ds(EXA).de(CTY).setHumanName(lng("Place of Examiner")); 
        ds(DPA).ds(EXA).ds(SIGN).de(DAT).setHumanName(lng("Date of signature")); 
        
        
    	// 12. Request for verification
        ds(DPA).ds(CUO).de(COD).setHumanName(lng("Code of departure office")); 
        ds(DPA).ds(CUO).de(NAM).setHumanName(lng("Name of departure office")); 
        ds(DPA).ds(CUO).de(ADR).setHumanName(lng("Address of departure office")); 
        ds(DEST).ds(EXA).de(COD).setHumanName(lng("Examiner code"));      
        ds(DEST).ds(EXA).de(NAM).setHumanName(lng("Examiner name")); 
        ds(DEST).ds(EXA).de(CTY).setHumanName(lng("Place of signature")); 
        ds(DEST).ds(EXA).ds(SIGN).de(DAT).setHumanName(lng("Date of signature")); 
        
    	// 13. Verification
        ds(DPA).ds(CTL).ds(EXA).de(COD).setHumanName(lng("Examiner code")); 
        ds(DPA).ds(CTL).ds(EXA).de(NAM).setHumanName(lng("Examiner name")); 
        ds(DPA).ds(CTL).ds(EXA).de(CTY).setHumanName(lng("Place of signature")); 
        ds(DPA).ds(CTL).ds(EXA).ds(SIGN).de(DAT).setHumanName(lng("Date of signature")); 
        
        ds(DPA).ds(CTL).ds(RSLT).de(CORR).setHumanName(lng("Has been issued by this office "
        		+ "and that the particulars are correct - Flag")); 
        ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT).setHumanName(lng("Does not satisfy the "
        		+ "conditions of authenticity and accuracy - Flag")); 
		


	}

	/*
	 * Definition of the Finder model
	 */
	public void define_FinderModel() {
		define_Finder(F_eCO.finder);
		
	}

	public void define_DocumentRules() {
		ds(CO).ds(TAR).de(COD).addRule(R_ChkTar.sharedInstance(), DATA_CHANGED);
		ds(CO).ds(TAR).de(COD).addRule(R_ChkTar.sharedInstance(), DATA_VERIFY);

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

		addRule(new R_Init_Document(), DOCUMENT_INIT);
		//addRule(new R_Init_Document(), GUI_DOCUMENT_INIT);
		de(WDE).tryToSetContent(new Date());
		
		ds(CO).ds(CRITERIA).de(OP).addRule(new R_CheckBoxGroup(), DATA_VERIFY);		
		ds(CO).ds(CRITERIA).de(SW).addRule(new R_CheckBoxGroup(), DATA_VERIFY);
		ds(CO).ds(CRITERIA).ds(TAR).addRule(new R_CheckBoxGroup(), DATA_VERIFY);
		ds(CO).ds(CRITERIA).ds(PVA).addRule(new R_CheckBoxGroup(), DATA_VERIFY);
		ds(DPA).ds(CTL).ds(RSLT).de(CORR).addRule(new R_CheckBoxGroup(), DATA_VERIFY);
		ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT).addRule(new R_CheckBoxGroup(), DATA_VERIFY);
		
		// *****************Customs Office*********************//
		Rule ruleDateOffice = KR_HTSetDateFactory.getSetDateRule(this, CUO_TAB,	new DataField());
		de(WDE).addRule(ruleDateOffice, DATA_VERIFY);

		// *****************Country*********************//
		Rule rule2 = KR_HTSetDateFactory.getSetDateRule(this, CTY_TAB,	new DataField());
		de(WDE).addRule(rule2, DATA_VERIFY);

		// *****************Unit of Measure*********************//
		Rule rule3 = KR_HTSetDateFactory.getSetDateRule(this, UOM_TAB,	new DataField());
		de(WDE).addRule(rule3, DATA_VERIFY);

		// *****************Kind of Package*********************//
		Rule rule4 = KR_HTSetDateFactory.getSetDateRule(this, PKG_TAB, new DataField());
		de(WDE).addRule(rule4, DATA_VERIFY);
		
		// *****************Tariff*********************//
		Rule rule5 = KR_HTSetDateFactory.getSetDateRule(this, TAR_TAB, new DataField());
		de(WDE).addRule(rule5, DATA_VERIFY);

		// *****************Currency*********************//
		Rule rule6 = KR_HTSetDateFactory.getSetDateRule(this, CUR_TAB, new DataField());
		de(WDE).addRule(rule6, DATA_VERIFY);
		
		// *****************Attached Documents*********************//
		Rule rule7 = KR_HTSetDateFactory.getSetDateRule(this, ATD_TAB, new DataField());
		de(WDE).addRule(rule7, DATA_VERIFY);
		
		// *****************Producers*********************//
		Rule rule8 = KR_HTSetDateFactory.getSetDateRule(this, PROD_TAB, new DataField());
		de(WDE).addRule(rule8, DATA_VERIFY);



		ds(CO).ds(TAR).de(COD).setAttachedFinder("TarTab", "TARTABcode", "National Tariff"); // National
		// tariff
		ds(CO).ds(TAR).de(COD).getAttachedFinder().addRule(new R_Init_Finder_Historized(de(WDE)));
		ds(CO).ds(TAR).de(COD).getAttachedFinder().addRule(new R_FillHscTmp(ds(CO).ds(TAR)));
		ds(CO).ds(TAR).de(COD).addRule(new R_FillCommCode(ds(CO).ds(TAR)), DATA_CHANGED);
		

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
		
      
        ds(CO).ds(CTY).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);        
         
        // 1. Producer informations
        ds(CO).ds(CMP).ds(PROD).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);          
        // 2. Consignee
        ds(CO).ds(CMP).ds(CONS).de(NAM).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);        
        ds(CO).ds(CMP).ds(CONS).de(ADR).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);        
        ds(CO).ds(CMP).ds(CONS).de(TEL).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);        
        ds(CO).ds(CMP).ds(CONS).de(EMAIL).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);                 
    	// 4. Quantity, nature, of goods and number of packages
        ds(CO).ds(PCK).de(NBR).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);       
        ds(CO).ds(PCK).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);
        ds(CO).ds(PCK).de(MRK).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);       
    	// 5. Tariff and statistical nomenclature       
        ds(CO).ds(TAR).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY); 
        ds(CO).ds(TAR).de(DSC).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);               
    	// 6. Approval no of goods
        ds(CO).ds(PRODUCT).de(NBR).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);                
    	// 7. Gross weight or other measure
        ds(CO).ds(WGT).de(GRS).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY); 
        ds(CO).ds(SUP).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);
    	// 8. Invoice value      
        ds(CO).ds(INV).de(AMT).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);   
        ds(CO).ds(CUR).de(COD).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);       
    	// 9. Declaration by exporter      
        ds(CO).ds(CMP).ds(EXP).de(NAM).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY); 
        ds(CO).ds(CMP).ds(EXP).de(CTY).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);
        ds(CO).ds(CMP).ds(EXP).ds(SIGN).de(DAT).addRule(KR_DataMandatory.sharedInstance(),	DATA_VERIFY);
      
        
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
