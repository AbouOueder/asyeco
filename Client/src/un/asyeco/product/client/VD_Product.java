/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Visual Document of Certificate of Origin
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.product.client;

import so.kernel.core.Data;
import un.adtcommons.client.visual.KVisualDocument;
import un.asyeco.product.C_Product;
import un.asyeco.product.D_Product;


/**
 * Visual Document for null of Certificate Document
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class VD_Product extends KVisualDocument implements C_Product {
	/**
 	 * Parameterless constructor required by desktop persistence
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */
	public VD_Product() {
		super();
		
	}

	/**
	 * Method for initialising Certificate visual forms
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */
	public void initializeForms() {
		addForm(new VF_Product()); 
		addForm(new VF_ProductItem()); 
		//addForm(new VF_ScannedDocs()); 

		
		
	}
	protected void initRules()
    {
		super.initRules();
		D_Product doc =(D_Product) getDocument();
		
    }
	

	private static String lng(String property){
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
	private static final long serialVersionUID = 1L;
}