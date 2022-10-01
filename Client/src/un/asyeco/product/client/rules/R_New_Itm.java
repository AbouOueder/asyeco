/**
 * UNCTAD
 * ASYCUDA PROJECT
 * 
 */ 
package un.asyeco.product.client.rules;
import so.kernel.core.KDocument;
import so.kernel.core.KNumberedSubDataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import un.asyeco.product.C_Product;
import un.asyeco.product.D_Product;

/**
 * Client Rule of Certificate Document
 * <!-- begin-user-doc -->
 * Creates a new Item Page	 
 * <!-- end-user-doc -->
 * @generated
 */
public class R_New_Itm extends Rule implements C_Product {

	/**
	 * Default constructor  
 	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
 	 * @generated
	 */
	public R_New_Itm(){
	}


	/**
	 * This method does the job for R_Page_New 
 	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->		 
	 * @see so.kernel.core.Rule#apply(so.kernel.core.KernelEvent)		 
 	 * @generated NO
	 */		 
	@Override		
	protected void apply(KernelEvent e) {
		
		if (e.getData() instanceof D_Product) {
			D_Product prod = (D_Product) e.getData();
			KNumberedSubDataSet itm_sds = (KNumberedSubDataSet) prod.ds(ITM); 

			prod.fire(new KernelEvent(ACT_ITM_NEW));
			int totItms = itm_sds.countDocuments();
			int oldTot = prod.ds(TOT).de(ITM).getInt(0);
			if (totItms == oldTot)  prod.ds(TOT).de(ITM).setInteger(oldTot+1);
			else  prod.ds(TOT).de(ITM).setInteger(totItms);
			
			prod.ds(TOT).de(ITM).changeOriginalContent();

		}
	}


	/**
	 * Returns translated string
	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
	 * @generated 
	 */	
	private static String lng(String property){
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}

}