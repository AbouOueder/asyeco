/**
 * UNCTAD
 * ASYCUDA PROJECT
 * 
 */ 
package un.asyeco.product.server;

import java.sql.Types;

import so.ems.convertor.BigInt2BytesConvertor;
import so.ems.server.tc.EMSConvertor;
import so.kernel.server.ConnectionManager;
import so.kernel.server.GCFServerBinder;
import so.kernel.server.GCFTableConnector;
import so.kernel.server.Server;
import so.kernel.server.TableConnector;
import un.asyeco.product.C_Product;



/**
 * Table Connector for Master Table of Certificate Document
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TC_Product extends GCFTableConnector implements C_Product {
	/**
 	 * Constructor for Master Table Table Connector of Certificate Document
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */ 	
	
	public TC_Product(GCFServerBinder serverBinder, ConnectionManager connectionManager) {

		super(serverBinder, connectionManager, Server.getString(S_Product.CO_PRODUCT_TAB));
//
		// Define the Instance Id field, column
		serverBinder.setInstanceIdField(this, INSTANCE_ID, INSTANCE_ID);
		
		add(ds(CMP).ds(PROD).de(COD), "CMP_PROD_COD", Types.VARCHAR);
		add(ds(CMP).ds(PROD).de(NAM), "CMP_PROD_NAM", Types.VARCHAR);	
		add(ds(CMP).ds(PROD).ds(CTY).de(COD), "CMP_PROD_CTY_COD", Types.VARCHAR);
		add(ds(CMP).ds(PROD).ds(CTY).de(DSC), "CMP_PROD_CTY_DSC", Types.VARCHAR);
		add(ds(CMP).ds(PROD).de(ADR), "CMP_PROD_ADR", Types.VARCHAR);
		add(ds(CMP).ds(PROD).de(ADR2), "CMP_PROD_AD2", Types.VARCHAR);
		add(ds(CMP).ds(PROD).de(TEL), "CMP_PROD_TEL", Types.VARCHAR);	
		add(ds(CMP).ds(PROD).de(EMAIL), "CMP_PROD_EMAIL", Types.VARCHAR);
		add(ds(CMP).ds(PROD).de(WSITE), "CMP_PROD_WSITE", Types.VARCHAR);
		add(ds(TOT).de(ITM), "TOT_ITM", Types.INTEGER);

		
//		// Scanned documents
//		add(de(FLP1), FLP1, Types.LONGVARBINARY);// File list panel #2
//		// Scanned documents
//
//		addConvertor(new EMSConvertor(BigInt2BytesConvertor.INSTANCE));
//		
		addSubTableConnector(new TC_ProductItm(this, connectionManager));
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