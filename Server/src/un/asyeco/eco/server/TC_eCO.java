/**
 * UNCTAD
 * ASYCUDA PROJECT
 * 
 */ 
package un.asyeco.eco.server;

import java.sql.Types;

import so.ems.convertor.BigInt2BytesConvertor;
import so.ems.server.tc.EMSConvertor;
import so.kernel.server.ConnectionManager;
import so.kernel.server.GCFServerBinder;
import so.kernel.server.GCFTableConnector;
import so.kernel.server.Server;
import so.kernel.server.TableConnector;
import un.asyeco.eco.C_eCO;



/**
 * Table Connector for Master Table of Certificate Document
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TC_eCO extends GCFTableConnector implements C_eCO{
	/**
 	 * Constructor for Master Table Table Connector of Certificate Document
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */ 	
	
	public TC_eCO(GCFServerBinder serverBinder, ConnectionManager connectionManager) {

		super(serverBinder, connectionManager, Server.getString(S_eCO.CO_TAB));

		// Define the Instance Id field, column
		serverBinder.setInstanceIdField(this, INSTANCE_ID, INSTANCE_ID);

	
		
		add(ds(CO).de(NBR), "CO_NBR", Types.VARCHAR); // Certificate Serial Number
		add(ds(CO).ds(REG).de(SER), "CO_REG_SER", Types.VARCHAR); // Certificate Register Serial char
		add(ds(CO).ds(REG).de(DAT), "CO_REG_DAT", Types.DATE); // Certificate Register date
		add(ds(CO).ds(REG).de(YER), "CO_REG_YER", Types.VARCHAR); // Certificate Register Year
		
		add(ds(CO).ds(SUB).de(YER), "CO_SUB_YER", Types.VARCHAR); // Certificate Submit Year
		add(ds(CO).ds(SUB).de(SER), "CO_SUB_SER", Types.VARCHAR); // Certificate Submit Serial char
		add(ds(CO).ds(SUB).de(NBR), "CO_SUB_NBR", Types.VARCHAR); // Certificate Submit Serial Number
		add(ds(CO).ds(SUB).de(DAT), "CO_SUB_DAT", Types.DATE); // Certificate Submit date

		add(ds(CO).de(CRN), "CO_CRN", Types.VARCHAR); // Certificate unique Reference Number
		add(ds(CO).ds(EXPIRED).de(DAT), "CO_EXPIRED_DAT", Types.DATE); // Certificate expired date
		add(ds(CO).ds(CTY).de(COD), "CO_CTY_COD", Types.VARCHAR);
		add(ds(CO).ds(CTY).de(DSC), "CO_CTY_DSC", Types.VARCHAR);
		add(ds(CO).ds(CMP).ds(PROD).de(COD), "CO_CMP_PROD_COD", Types.VARCHAR);
		add(ds(CO).ds(CMP).ds(PROD).de(NAM), "CO_CMP_PROD_NAM", Types.VARCHAR);	
		add(ds(CO).ds(CMP).ds(PROD).de(CTY), "CO_CMP_PROD_CTY", Types.VARCHAR);	
		add(ds(CO).ds(CMP).ds(PROD).de(ADR), "CO_CMP_PROD_ADR", Types.VARCHAR);	
		add(ds(CO).ds(CMP).ds(PROD).de(TEL), "CO_CMP_PROD_TEL", Types.VARCHAR);	
		add(ds(CO).ds(CMP).ds(PROD).de(EMAIL), "CO_CMP_PROD_EMAIL", Types.VARCHAR);
		add(ds(CO).ds(CMP).ds(CONS).de(NAM), "CO_CMP_CONS_NAM", Types.VARCHAR);	
		add(ds(CO).ds(CMP).ds(CONS).de(CTY), "CO_CMP_CONS_CTY", Types.VARCHAR);	
		add(ds(CO).ds(CMP).ds(CONS).de(ADR), "CO_CMP_CONS_ADR", Types.VARCHAR);	
		add(ds(CO).ds(CMP).ds(CONS).de(TEL), "CO_CMP_CONS_TEL", Types.VARCHAR);
		add(ds(CO).ds(CMP).ds(CONS).de(EMAIL), "CO_CMP_CONS_EMAIL", Types.VARCHAR);	
		add(ds(CO).ds(CRITERIA).de(OP),      "CO_CRITERIA_OP",  Types.BOOLEAN);	
		add(ds(CO).ds(CRITERIA).de(SW),      "CO_CRITERIA_SW",  Types.BOOLEAN);	
		add(ds(CO).ds(CRITERIA).ds(TAR).de(CHG), "CO_CRITERIA_TAR_CHG",  Types.BOOLEAN);	
		add(ds(CO).ds(CRITERIA).ds(PVA).de(FLG), "CO_CRITERIA_PVA_FLG",  Types.BOOLEAN);	
		add(ds(CO).ds(CRITERIA).ds(PVA).de(RAT), "CO_CRITERIA_PVA_RAT",  Types.DOUBLE);	
		add(ds(CO).ds(PCK).de(NBR),               "CO_PCK_NBR", Types.INTEGER);	
		add(ds(CO).ds(PCK).de(COD),               "CO_PCK_COD", Types.VARCHAR);	
		add(ds(CO).ds(PCK).de(DSC),               "CO_PCK_DSC", Types.VARCHAR);	
		add(ds(CO).ds(PCK).de(MRK), 			  "CO_PCK_MRK", Types.VARCHAR);	
		add(ds(CO).ds(TAR).de(COD),  			  "CO_TAR_COD", Types.VARCHAR);	
		add(ds(CO).ds(TAR).de(DSC), 			  "CO_TAR_DSC", Types.VARCHAR);	
		add(ds(CO).ds(GDS).de(DSC), 			  "CO_GDS_DSC", Types.VARCHAR);	
		add(ds(CO).ds(PRODUCT).de(NBR), 		  "CO_PRODUCT_NBR", Types.VARCHAR);	
		add(ds(CO).ds(WGT).de(GRS), 			  "CO_WGT_GRS", Types.DOUBLE);	
		add(ds(CO).ds(SUP).de(COD), 			  "CO_SUP_COD", Types.VARCHAR);	
		add(ds(CO).ds(SUP).de(DSC), 			  "CO_SUP_DSC", Types.VARCHAR);	
		add(ds(CO).ds(INV).de(AMT), 			  "CO_INV_AMT", Types.DOUBLE);	
		add(ds(CO).ds(CUR).de(COD), 			  "CO_CUR_COD", Types.VARCHAR);	
		add(ds(CO).ds(CMP).ds(EXP).de(NAM), 	  "CO_CMP_EXP_NAM", Types.VARCHAR);	
		add(ds(CO).ds(CMP).ds(EXP).de(CTY), 	  "CO_CMP_EXP_CTY", Types.VARCHAR);	
		add(ds(CO).ds(CMP).ds(EXP).ds(SIGN).de(DAT), "CO_CMP_EXP_SIGN_DAT", Types.DATE);	
		add(ds(CO).ds(AUTH).de(NAM), 			   "CO_AUTH_NAM", Types.VARCHAR);	
		add(ds(CO).ds(AUTH).de(CTY), 			   "CO_AUTH_CTY", Types.VARCHAR);	
		add(ds(CO).ds(AUTH).ds(SIGN).de(DAT), 	   "CO_AUTH_SIGN_DAT", Types.DATE);	
		add(ds(CO).ds(DAU).de(MOD), 			   "CO_DAU_MOD", Types.VARCHAR);	
		add(ds(CO).ds(CUO).de(COD), 			   "CO_CUO_COD", Types.VARCHAR);	
		add(ds(CO).ds(CUO).de(NAM), 			   "CO_CUO_NAM", Types.VARCHAR);	
		add(ds(CO).ds(DAU).ds(REG).de(NBR), 	   "CO_DAU_REG_NBR", Types.VARCHAR);	
		add(ds(CO).ds(DAU).ds(REG).de(DAT), 	   "CO_DAU_REG_DAT", Types.DATE);	
		add(ds(DPA).ds(EXA).de(NAM), 			   "DPA_EXA_NAM", Types.VARCHAR);	
		add(ds(DPA).ds(EXA).de(COD), 			   "DPA_EXA_COD", Types.VARCHAR);	
		add(ds(DPA).ds(EXA).de(CTY), 			   "DPA_EXA_CTY", Types.VARCHAR);	
		add(ds(DPA).ds(EXA).ds(SIGN).de(DAT), 	   "DPA_EXA_SIGN_DAT", Types.DATE);	
		add(ds(DPA).ds(CUO).de(COD), 			   "DPA_CUO_COD", Types.VARCHAR);	
		add(ds(DPA).ds(CUO).de(NAM), 				"DPA_CUO_NAM", Types.VARCHAR);	
		add(ds(DPA).ds(CUO).de(ADR), 				"DPA_CUO_ADR", Types.VARCHAR);	
		add(ds(DEST).ds(EXA).de(NAM),  				"DEST_EXA_NAM", Types.VARCHAR);	
		add(ds(DEST).ds(EXA).de(COD),  				"DEST_EXA_COD", Types.VARCHAR);	
		add(ds(DEST).ds(EXA).de(CTY),  				"DEST_EXA_CTY", Types.VARCHAR);	
		add(ds(DEST).ds(EXA).ds(SIGN).de(DAT),  	"DEST_EXA_SIGN_DAT", Types.DATE);	
		add(ds(DPA).ds(CTL).ds(EXA).de(COD), 		"DPA_CTL_EXA_COD", Types.VARCHAR);	
		add(ds(DPA).ds(CTL).ds(EXA).de(NAM),  		"DPA_CTL_EXA_NAM" , Types.VARCHAR);			
		add(ds(DPA).ds(CTL).ds(EXA).de(CTY),  		"DPA_CTL_EXA_CTY", Types.VARCHAR);	
		add(ds(DPA).ds(CTL).ds(EXA).ds(SIGN).de(DAT),  	"DPA_CTL_EXA_SIGN_DAT", Types.DATE);	
		add(ds(DPA).ds(CTL).ds(RSLT).de(CORR),  	"DPA_CTL_RSLT_CORRECT", Types.BOOLEAN);	
		add(ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT), "DPA_CTL_RSLT_NOTCORRECT", Types.BOOLEAN);	

		
		// Scanned documents
		add(de(FLP1), FLP1, Types.LONGVARBINARY);// File list panel #2
		// Scanned documents

		addConvertor(new EMSConvertor(BigInt2BytesConvertor.INSTANCE));
		
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