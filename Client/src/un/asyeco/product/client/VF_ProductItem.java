/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Visual Form of Product
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.product.client;

import so.kernel.client.KVisualFormWithNumberedPages;
import so.kernel.client.KVisualPage;
import so.kernel.client.VisualForm;
import so.kernel.core.KNumberedSubDataSet;
import un.asyeco.product.C_Product;


/**
 * Visual Document for  of 
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated NO
 */
public class VF_ProductItem extends KVisualFormWithNumberedPages implements C_Product {


	
	/**
 	 * Parameterless constructor required by desktop persistence. Also sets the name of the form
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated NO
 	 */
	public VF_ProductItem() {
		super(lng("Product items"), ITM, ACT_ITM_NEW, ACT_ITM_DEL);
	}	




	private static final long serialVersionUID = 1L;
	/**
	 * Returns translated string
	 * <!-- begin-user-doc -->
	 * Returns translated string
 	 * <!-- end-user-doc -->
	 * @generated 
	 */
	@SuppressWarnings("unused")
	private static String lng(String property){
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}


	@Override
	public KVisualPage getVisualPage(int pageNumber) {
		// TODO Auto-generated method stub
		return new VP_ProductItem();
	}
	
	/**
	 * Method for initialising Certificate visual forms
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated NO
 	 */

	
}