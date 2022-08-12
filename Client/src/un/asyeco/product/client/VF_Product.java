/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Visual Form of Certificate of Origin
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.product.client;

import so.kernel.client.VisualForm;
import un.asyeco.eco.C_eCO;
import un.asyeco.product.C_Product;


/**
 * Visual Document for  of 
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated NO
 */
public class VF_Product extends VisualForm implements C_Product {


	
	/**
 	 * Parameterless constructor required by desktop persistence. Also sets the name of the form
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated NO
 	 */
	public VF_Product() {
		super(lng("Certificate"));
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
	protected void initializePages() {
		// TODO Auto-generated method stub
		addPage(new VP_Product());
	}
	
}