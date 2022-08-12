package un.asyeco.serial.eco.submit;

import static so.kernel.core.KernelEventConstants.DATA_VERIFY;
import static so.kernel.core.KernelEventConstants.DOCUMENT_INIT;
import so.kernel.Global;
import so.kernel.core.DataField;
import so.kernel.core.DataSet;
import so.kernel.core.FinderInfo;
import so.kernel.core.Rule;
import so.kernel.core.rules.KR_ValidYear;
import un.kernel.core.KDocument;
import un.kernel.core.rules.KR_HTSetDateFactory;

/**
 * Container class for the workk in progress document.
 */
public class D_Sub extends KDocument implements C_Sub {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7400968975975647255L;

	/**
	 * Constructor - Declares the document name.
	 */
	public D_Sub() {
		super("Reg");
	}

	/**
	 * Definition of the Data Model
	 */
	public void define_DataModel() {

		// Define data model

		add(WDE).setDateType(); // Working date
		add(TMZ); // Time zone
		add(LNG);
		add(YER); // Year
		DataSet sit = seg(SIT); // Customs site
		sit.add(COD); // Customs site, code
		sit.add(NAM); // Customs site, name
		
		DataSet cty = seg(CTY); // Country
		cty.add(COD); // Country, code
		cty.add(DSC); // Country, name

		add(CHR); // Char serial (letter or digit)

		DataSet ser = seg(SER); // Serial number
		ser.add(INI); // Initial number
		ser.add(CUR); // Current number

	}

	/*
	 * Definition of the Finder model
	 * 
	 */

	public void define_FinderModel() {

		setFinderInfo(new D_Sub.FinderInfoImpl());

	}

	/**
	 * Definition of the Reference Model
	 */
	public void define_ReferenceModel() {

		// define_Ref(CUO_TAB);
	}

	public void define_CrossImplHTModel() {
		defineHistorizedTable(CUO_TAB, CUO_TAB_EVENT);
		defineHistorizedTable(CTY_TAB, CTY_TAB_EVENT);
		// defineHistorizedTable(CUO_TAB_ar, CUO_TAB_EVENT_ar);
	}

	/**
	 * Definition of the Client Business Rules
	 */
	public void define_ClientBusinessRule() {

		// Document initialization
		addRule(new R_Init_Document(), DOCUMENT_INIT);

		Rule ruleDate1 = KR_HTSetDateFactory.getSetDateRule(this, CUO_TAB, new DataField());
		de(WDE).addRule(ruleDate1, DATA_VERIFY);

		
		Rule ruleDate2 = KR_HTSetDateFactory.getSetDateRule(this, CTY_TAB, new DataField());
		de(WDE).addRule(ruleDate2, DATA_VERIFY);

		
		// ruleDate1 = KR_HTSetDateFactory.getSetDateRule(this, CUO_TAB_ar, new DataField());
		// de(WDE).addRule(ruleDate1, DATA_VERIFY);

		// Valid year
		de(YER).addRule(new KR_ValidYear(), DATA_VERIFY);

		// Set the current serial value
		ds(SER).de(INI).addRule(new R_SetBase(), DATA_VERIFY);

	}

	public static class FinderInfoImpl extends FinderInfo {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void define_FinderModel() {

			addFinder_SearchFld(de(YER), lng("Year"), Global.INTEGER_TYPE);
			
			addFinder_SearchFld(ds(CTY).de(COD), lng("Country, code"), Global.STRING_TYPE);
			addFinder_SearchFld(ds(CTY).de(DSC), lng("Country, name"), Global.STRING_TYPE);

			addFinder_SearchFld(ds(SIT).de(COD), lng("Customs site, code"), Global.STRING_TYPE);
			addFinder_SearchFld(ds(SIT).de(NAM), lng("Customs site, name"), Global.STRING_TYPE);
			addFinder_SearchFld(de(CHR), lng("Serial"), Global.STRING_TYPE);

			addFinder_ViewFld(de(YER), lng("Year"));
			
			addFinder_ViewFld(ds(CTY).de(COD), lng("Country, code"));
			addFinder_ViewFld(ds(CTY).de(DSC), lng("Country, name"));
			
			addFinder_ViewFld(ds(SIT).de(COD), lng("Customs site, code"));
			addFinder_ViewFld(ds(SIT).de(NAM), lng("Customs site, name"));
			addFinder_ViewFld(de(CHR), lng("Serial"));

			setDefaultSort(getList(de(YER), ds(CTY).de(COD)), UP);

		}
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
