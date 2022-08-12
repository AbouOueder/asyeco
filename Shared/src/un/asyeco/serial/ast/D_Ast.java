//   $Header: /home/asycuda/home/cvsroot/asybrk/Shared/src/un/broker/serial/ast/D_Ast.java,v 1.9 2012-07-03 07:37:24 kazan Exp $

package un.asyeco.serial.ast;

import static so.kernel.core.KernelEventConstants.DATA_VERIFY;
import static so.kernel.core.KernelEventConstants.DOCUMENT_INIT;
import so.kernel.core.DataField;
import so.kernel.core.DataSet;
import so.kernel.core.Rule;
import so.kernel.core.rules.KR_ValidYear;

import un.kernel.core.KDocument;
import un.kernel.core.rules.KR_HTSetDateFactory;
/**
 * Container class for the wrok in progress document.
 */
public class D_Ast extends KDocument implements C_Ast {

	/**
	 * Constructor - Declares the document name.
	 * 
	 */
	public D_Ast() {
		super("Ast");
	}

	/**
	 * Definition of the Data Model
	 * 
	 */
	public void define_DataModel() {

		// Define data model
		add(WDE);
		add(TMZ);
		add(YER); // Year
		DataSet sit = seg(SIT); // Customs site
		sit.add(COD); // Customs site, code
		sit.add(NAM); // Customs site, name
		add(DTI); // Direct Trade Input ( True/False)
		add(CHR); // Char serial (letter or digit)
		
		DataSet cty = seg(CTY); // Country
		cty.add(COD); // Country, code
		cty.add(DSC); // Country, name

		DataSet ser = seg(SER); // Serial number
		ser.add(INI); // Initial number
		ser.add(CUR); // Current number

	}

	/*
	 * Definition of the Finder model
	 * 
	 */
	public void define_FinderModel() {

		define_Finder(F_Ast.finder);
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
	}

	/**
	 * Definition of the Client Business Rules
	 * 
	 */
	public void define_ClientBusinessRule() {
		Rule ruleDate1 = KR_HTSetDateFactory.getSetDateRule(this, CUO_TAB, new DataField());
		de(WDE).addRule(ruleDate1, DATA_VERIFY);
		// ruleDate1 = KR_HTSetDateFactory.getSetDateRule(this, CUO_TAB_ar, new DataField());
		// de(WDE).addRule(ruleDate1, DATA_VERIFY);
		
		Rule ruleDate2 = KR_HTSetDateFactory.getSetDateRule(this, CTY_TAB, new DataField());
		de(WDE).addRule(ruleDate2, DATA_VERIFY);

		// Document initialization
		addRule(new R_Init_Document(), DOCUMENT_INIT);

		// Valid year
		de(YER).addRule(new KR_ValidYear(), DATA_VERIFY);

		// Set the current serial value
		ds(SER).de(INI).addRule(new R_SetBase(), DATA_VERIFY);
	}
}
