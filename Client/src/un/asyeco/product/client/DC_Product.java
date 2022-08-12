/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Calling of Client business rules
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.product.client;



import static so.kernel.core.KernelEventConstants.DATA_VERIFY;
import so.kernel.client.ClientDocument;
import so.kernel.core.Data;
import so.kernel.core.DataField;
import so.kernel.core.DataSet;
import so.kernel.core.Document;
import so.kernel.core.KNumberedSubDataSet;
import so.kernel.core.KernelEventConstants;
import so.kernel.core.Rule;
import so.kernel.core.client.rules.KR_Export;
import so.kernel.core.client.rules.KR_Import;
import so.kernel.core.rules.KR_DataMandatory;

import un.asyeco.eco.client.rules.R_EndOfTransaction;
import un.asyeco.eco.C_eCO;
import un.asyeco.eco.D_eCO;
import un.kernel.core.rules.KR_HTConnectorFactory;
import un.kernel.core.rules.KR_HTSetDateFactory;
import un.asyeco.eco.client.rules.VR_Done;
import un.asyeco.product.C_Product;
import un.asyeco.product.D_Product;


/**
 * Client Document for null of Certificate Document
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DC_Product extends ClientDocument implements C_Product {
	/**
 	 * Parameterless constructor required by desktop persistence
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */
 	 
	public DC_Product() {
		super();
	}
	

	/**
 	 * Initialization of client rules at LoadingClientDocument
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */ 	 
	void initRules(D_Product doc) {


		//document.ds(EXP).de(COD).addRule(KR_DataMandatory.sharedInstance(), KernelEventConstants.DATA_VERIFY);		
		
	}//initRules
	
	public void define_ReferenceDataRule(D_Product doc, VD_Product vd ) {
		// *****************Customs Office*********************//
	
		Data targetsCountry[] = new Data[] { doc.ds(CTY).de(DSC) };
		String targetsNameCountry[] = new String[] { CTY_DSC };
		Rule rule2 = KR_HTConnectorFactory.getHTConnectorRule(doc, CTY_TAB, 0, CTY_COD, targetsCountry, targetsNameCountry);
		doc.ds(CTY).de(COD).addRule(rule2, DATA_VERIFY);
		
		Data targetsCountry21[] = new Data[] { doc.ds(CTY).de(COD) };
		String targetsNameCountry21[] = new String[] { CTY_COD };
		Rule rule21 = KR_HTConnectorFactory.getHTConnectorRule(doc, CTY_TAB, 3, CTY_DSC, targetsCountry21, targetsNameCountry21);
		doc.ds(CTY).de(DSC).addRule(rule21, DATA_VERIFY);
		
		
		Data targetsPackage[] = new Data[] { doc.ds(PCK).de(DSC) };
		String targetsDscPck[] = new String[] { PKG_DSC };
		Rule ruleTarget3 = KR_HTConnectorFactory.getHTConnectorRule(doc, PKG_TAB, 0, PKG_COD, targetsPackage, targetsDscPck);		
		doc.ds(PCK).de(COD).addRule(ruleTarget3, DATA_VERIFY);

		
		Data targetsUom[] = new Data[] { doc.ds(CO).ds(SUP).de(DSC) };
		String targetsDscUom[] = new String[] { UOM_DSC };
		Rule ruleTarget4 = KR_HTConnectorFactory.getHTConnectorRule(doc, UOM_TAB, 0, UOM_COD, targetsUom, targetsDscUom);		
		doc.ds(SUP).de(COD).addRule(ruleTarget4, DATA_VERIFY);

		
		Data targetsAtd[] = new Data[] { doc.ds(SCAN).de(NAM) };
		String targetsDscAtd[] = new String[] { ATD_DSC };
		Rule ruleTarget5 = KR_HTConnectorFactory.getHTConnectorRule(doc, ATD_TAB, 0, ATD_COD, targetsAtd, targetsDscAtd);		
		doc.ds(SCAN).de(COD).addRule(ruleTarget5, DATA_VERIFY);

		
		Data targetsProd[] = new Data[] { doc.ds(CMP).ds(PROD).de(NAM), doc.ds(CMP).ds(PROD).de(ADR), doc.ds(CMP).ds(PROD).de(TEL), doc.ds(CMP).ds(PROD).de(EMAIL) };
		String targetsInfoProd[] = new String[] { PROD_NAM, PROD_ADR, PROD_TEL, PROD_EMAIL };
		Rule ruleTarget6 = KR_HTConnectorFactory.getHTConnectorRule(doc, PROD_TAB, 0, PROD_COD, targetsProd, targetsInfoProd);		
		doc.ds(CO).ds(CMP).ds(PROD).de(COD).addRule(ruleTarget6, DATA_VERIFY);

		
		
	}

	
	public static void define_ReferenceDataRulesItem(D_Product doc) {

        
    }

	

	/**
 	 * Initialization of client rules at LoadingVisualDocument
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */	
	void initRules(D_Product doc, VD_Product vd) {
		define_ReferenceDataRule(doc, vd );	
		// Display end of transaction information dialog
		doc.addRule(new VR_Done(doc, vd), KernelEventConstants.OPERATION_DONE);

		//doc.addRule(new R_EndOfTransaction(vd), KernelEventConstants.OPERATION_DONE);
	
	}//initRules




	
}