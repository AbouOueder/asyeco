/**
 * UNCTAD
 * ASYCUDA PROJECT
 * 
 */ 
package un.asyeco.product.server;


import java.sql.Types;

import so.kernel.core.KNumberedSubDocument;
import so.kernel.server.ConnectionManager;
import so.kernel.server.KNumberedSubTableConnector;
import so.kernel.server.Server;
import so.kernel.server.TableConnector;
import un.asyeco.product.C_Product;
import un.asyeco.product.DS_ProductItm;



/**
 * Table Connector for null of Certificate Document
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TC_ProductItm extends KNumberedSubTableConnector implements C_Product {
	/**
 	 * Constructor for null Table Connector of Certificate Document
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */
	
	
		

		
	public TC_ProductItm(TableConnector tc_Product, ConnectionManager connectionManager) {

		super(tc_Product.getServerBinder(), connectionManager, Server.getString(S_Product.CO_PRODUCT_ITM_TAB), ITM, new DS_ProductItm());
//	
//	//public TC_Itm(ServerBinder serverBinder, TableConnector[] ancestors, ConnectionManager connectionManager){
//		//super(serverBinder, connectionManager, Server.getString(S_Certificate.UN_CER_ITM_TAB), ITM, new DS_NumberedItem());		
		add(de(KNumberedSubDocument.RNK), "KEY_ITM_NBR", Types.INTEGER); // Rank
    	// 6. Approval no of goods
		add(ds(PRODUCT).de(NBR), "PRODUCT_NBR", Types.CHAR );   
        
        // 5. Tariff and statistical nomenclature  
        add(ds(TAR).de(COD), "TAR_COD", Types.CHAR);  
        add(ds(TAR).de(DSC), "TAR_DSC", Types.CHAR); 
        add(ds(GDS).de(DSC), "GDS_DSC", Types.CHAR); 
        
        add(ds(CRITERIA).ds(OP).de(FLG), "CRITERIA_OP_FLG", Types.BOOLEAN);  
        add(ds(CRITERIA).ds(SW).de(FLG), "CRITERIA_SW_FLG", Types.BOOLEAN);  
        add(ds(CRITERIA).ds(TAR).ds(CHG).de(FLG), "CRITERIA_TAR_CHG_FLG", Types.BOOLEAN);  
        add(ds(CRITERIA).ds(PVA).de(FLG), "CRITERIA_PVA_FLG", Types.BOOLEAN);  
              
        add(ds(CRITERIA).ds(PVA).de(RAT), "CRITERIA_PVA_RAT", Types.DOUBLE);     
        
    	     
    
              
		// Define key columns
		key(KNumberedSubDocument.RNK);
		key(INSTANCE_ID, tc_Product);
//		//key(INSTANCE_ID, ancestors[0]); // Key from master TableConnector
//		
//
		setParticipateInSearch(true);		
	}	

	/**
 	 * Appends a TableConnector to the array of TableConnector ancestors by creating a new array. 
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @returns the new array with connector appended
 	 * @generated
 	 */
	private TableConnector[] addAncestor(TableConnector[] ancestors, TableConnector conector){
		TableConnector[] newAncestors = new TableConnector[ancestors.length + 1];
		System.arraycopy(ancestors, 0, newAncestors, 0, ancestors.length);
		newAncestors[ancestors.length] = this;
		return newAncestors;
	}		
}