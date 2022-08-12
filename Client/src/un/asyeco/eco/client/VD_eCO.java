/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Visual Document of Certificate of Origin
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.eco.client;

import so.kernel.core.Data;
import un.adtcommons.client.visual.KVisualDocument;
import un.asyeco.eco.C_eCO;
import un.asyeco.eco.D_eCO;


/**
 * Visual Document for null of Certificate Document
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class VD_eCO extends KVisualDocument implements C_eCO {
	/**
 	 * Parameterless constructor required by desktop persistence
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */
	public VD_eCO() {
		super();
		
	}

	/**
	 * Method for initialising Certificate visual forms
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */
	public void initializeForms() {
		addForm(new VF_eCO()); 
		addForm(new VF_ScannedDocs()); 

		
		
	}
	protected void initRules()
    {
		super.initRules();
		D_eCO doc =(D_eCO) getDocument();
		
    }
	

	private static String lng(String property){
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
	private static final long serialVersionUID = 1L;
}