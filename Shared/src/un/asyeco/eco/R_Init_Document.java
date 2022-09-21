package un.asyeco.eco;

import static so.kernel.core.KernelEventConstants.GUI_DOCUMENT_INIT;

import java.text.SimpleDateFormat;

import so.kernel.core.DataSet;
import so.kernel.core.KNumberedSubDataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.kernel.core.TransactionEvent;
import un.asyeco.sharedUtils.ServerDate;

public class R_Init_Document extends Rule implements C_eCO {
	
	
	public void apply(KernelEvent e) {
		D_eCO doc = (D_eCO) e.getSource();
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		ServerDate s_date = null;
		// Set working date
		if (!"".equals(doc.getOperationClassName())|| !"".equals(doc.getStartedOperation())) {
			try {
				s_date = new ServerDate();
				s_date.initServerDate(doc, e);
				doc.de(WDE).tryToSetContent(s_date);
				doc.de(WDE).changeOriginalContent();
			} catch (Exception exc) {
			}
			if (doc.getOperationClassName().equals(OC_NEW) || doc.getStartedOperation().getName().equals(OP_SUBMIT) 
					|| doc.getStartedOperation().getName().equals(OP_MODIFY_REJECT)){
				try {
					s_date = new ServerDate();
					s_date.initServerDate(doc, e);
					doc.de(WDE).tryToSetContent(s_date);
					doc.de(WDE).changeOriginalContent();
					
					
					doc.ds(CO).ds(AUTH).de(NAM).setEnabled(false);
					doc.ds(CO).ds(AUTH).de(CTY).setEnabled(false);
					doc.ds(CO).ds(AUTH).ds(SIGN).de(DAT).setEnabled(false);

					
			        doc.ds(CO).ds(CUO).de(COD).setEnabled(false);
			        doc.ds(CO).ds(CUO).de(NAM).setEnabled(false);
			        doc.ds(CO).ds(DAU).de(MOD).setEnabled(false);
			        doc.ds(CO).ds(DAU).ds(REG).de(NBR).setEnabled(false);
			        doc.ds(CO).ds(DAU).ds(REG).de(DAT).setEnabled(false);    
			        doc.ds(DPA).ds(EXA).de(COD).setEnabled(false); 
			        doc.ds(DPA).ds(EXA).de(NAM).setEnabled(false);
			        doc.ds(DPA).ds(EXA).de(CTY).setEnabled(false);  
			        doc.ds(DPA).ds(EXA).ds(SIGN).de(DAT).setEnabled(false);
			        
			    	// 12. Request for verification
			        doc.ds(DPA).ds(CUO).de(COD).setEnabled(false); 
			        doc.ds(DPA).ds(CUO).de(NAM).setEnabled(false);
			        doc.ds(DPA).ds(CUO).de(ADR).setEnabled(false);
			        doc.ds(DEST).ds(EXA).de(COD).setEnabled(false);
			        doc.ds(DEST).ds(EXA).de(NAM).setEnabled(false);
			        doc.ds(DEST).ds(EXA).de(CTY).setEnabled(false);
			        doc.ds(DEST).ds(EXA).ds(SIGN).de(DAT).setEnabled(false);

			              		        
			    	// 13. Verification
			        doc.ds(DPA).ds(CTL).ds(EXA).de(COD).setEnabled(false);
			        doc.ds(DPA).ds(CTL).ds(EXA).de(NAM).setEnabled(false);
			        doc.ds(DPA).ds(CTL).ds(EXA).de(CTY).setEnabled(false);
			        doc.ds(DPA).ds(CTL).ds(EXA).ds(SIGN).de(DAT).setEnabled(false);
			        
			        doc.ds(DPA).ds(CTL).ds(RSLT).de(CORR).setEnabled(false); 
			        doc.ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT).setEnabled(false);

					
					
					
//			        doc.ds(CO).ds(CTY).de(COD).setEnabled(true);      
//			              			        
//			        // 1. Producer informations
//			        doc.ds(CO).ds(CMP).ds(PROD).de(COD).setEnabled(true); 
//			        
//			        // 2. Consignee
//			        doc.ds(CO).ds(CMP).ds(CONS).de(NAM).setEnabled(true); 
//			        doc.ds(CO).ds(CMP).ds(CONS).de(ADR).setEnabled(true); 
//			        doc.ds(CO).ds(CMP).ds(CONS).de(TEL).setEnabled(true); 
//			        doc.ds(CO).ds(CMP).ds(CONS).de(EMAIL).setEnabled(true); 
//			        
//			        
//			        // 3. Criteria determining origin (1)   
//			        doc.ds(CO).ds(CRITERIA).de(OP).setEnabled(true); 
//			        doc.ds(CO).ds(CRITERIA).de(SW).setEnabled(true); 
//			        doc.ds(CO).ds(CRITERIA).ds(TAR).de(CHG).setEnabled(true); 
//			        doc.ds(CO).ds(CRITERIA).ds(PVA).de(FLG).setEnabled(true); 
//			              
//			        doc.ds(CO).ds(CRITERIA).ds(PVA).de(RAT).setEnabled(true); 
//			        
//			        doc.ds(CO).ds(CRITERIA).ds(CEDEAO).de(FLG).setEnabled(true); 
//			        doc.ds(CO).ds(CRITERIA).ds(UEMOA).de(FLG).setEnabled(true); 
//
//			        
//			    	// 4. Quantity, nature, of goods and number of packages
//			        doc.ds(CO).ds(PCK).de(NBR).setEnabled(true); 
//			       
//			        doc.ds(CO).ds(PCK).de(COD).setEnabled(true); 
//			        doc.ds(CO).ds(PCK).de(MRK).setEnabled(true); 
//			        
//			    	// 5. Tariff and statistical nomenclature       
//			        doc.ds(CO).ds(TAR).de(COD).setEnabled(true); 
//			            
//			    	// 6. Approval no of goods
//			        doc.ds(CO).ds(PRODUCT).de(NBR).setEnabled(true); 
//			        			        
//			    	// 7. Gross weight or other measure
//
//			        doc.ds(CO).ds(WGT).de(GRS).setEnabled(true); 
//			        doc.ds(CO).ds(SUP).de(COD).setEnabled(true); 			     
//			        
//			    	// 8. Invoice value      
//			        doc.ds(CO).ds(INV).de(AMT).setEnabled(true); 
//			        doc.ds(CO).ds(CUR).de(COD).setEnabled(true); 
//			        
//			    	// 9. Declaration by exporter      
//			        doc.ds(CO).ds(CMP).ds(EXP).de(NAM).setEnabled(true); 
//			        doc.ds(CO).ds(CMP).ds(EXP).de(CTY).setEnabled(true); 
//			        doc.ds(CO).ds(CMP).ds(EXP).ds(SIGN).de(DAT).setEnabled(true); 
//			      			        			      			        
//			    	// 12. Request for verification
//			        addFacetText(Dpa_Cuo_Cod, doc.ds(DPA).ds(CUO).de(COD), "UN5"); 
//			        addFacetText(Dpa_Cuo_Nam, doc.ds(DPA).ds(CUO).de(NAM), "UN75"); 
//			        addFacetText(Dpa_Cuo_Adr, doc.ds(DPA).ds(CUO).de(ADR), "UN75");
//			        addFacetText(Dest_Exa_Cod, doc.ds(DEST).ds(EXA).de(COD), "UN17");        
//			        addFacetText(Dest_Exa_Nam, doc.ds(DEST).ds(EXA).de(NAM), "UN80");
//			        addFacetText(Dest_Exa_Cty, doc.ds(DEST).ds(EXA).de(CTY), "UN75");
//			        addFacetDate(Dest_Exa_Sign_Dat, doc.ds(DEST).ds(EXA).ds(SIGN).de(DAT), "Date");        
			        



					
				} catch (Exception exc) {
				}				
			} 
			
			if (doc.getOperationClassName().equals(OC_AUTH_VALIDATE) ){
				try {
			        doc.ds(CO).ds(CUO).de(COD).setEnabled(false);
			        doc.ds(CO).ds(CUO).de(NAM).setEnabled(false);
			        doc.ds(CO).ds(DAU).de(MOD).setEnabled(false);
			        doc.ds(CO).ds(DAU).ds(REG).de(NBR).setEnabled(false);
			        doc.ds(CO).ds(DAU).ds(REG).de(DAT).setEnabled(false);    
			        doc.ds(DPA).ds(EXA).de(COD).setEnabled(false); 
			        doc.ds(DPA).ds(EXA).de(NAM).setEnabled(false);
			        doc.ds(DPA).ds(EXA).de(CTY).setEnabled(false);  
			        doc.ds(DPA).ds(EXA).ds(SIGN).de(DAT).setEnabled(false);
			        
			    	// 12. Request for verification
			        doc.ds(DPA).ds(CUO).de(COD).setEnabled(false); 
			        doc.ds(DPA).ds(CUO).de(NAM).setEnabled(false);
			        doc.ds(DPA).ds(CUO).de(ADR).setEnabled(false);
			        doc.ds(DEST).ds(EXA).de(COD).setEnabled(false);
			        doc.ds(DEST).ds(EXA).de(NAM).setEnabled(false);
			        doc.ds(DEST).ds(EXA).de(CTY).setEnabled(false);
			        doc.ds(DEST).ds(EXA).ds(SIGN).de(DAT).setEnabled(false);

			              		        
			    	// 13. Verification
			        doc.ds(DPA).ds(CTL).ds(EXA).de(COD).setEnabled(false);
			        doc.ds(DPA).ds(CTL).ds(EXA).de(NAM).setEnabled(false);
			        doc.ds(DPA).ds(CTL).ds(EXA).de(CTY).setEnabled(false);
			        doc.ds(DPA).ds(CTL).ds(EXA).ds(SIGN).de(DAT).setEnabled(false);
			        
			        doc.ds(DPA).ds(CTL).ds(RSLT).de(CORR).setEnabled(false); 
			        doc.ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT).setEnabled(false);

					
				
				} catch (Exception exc) {
				}				
			}
			
			if (doc.getStartedOperation().getName().equals(OP_VALIDATE) ){
				try {
					
					doc.ds(CO).ds(CTY).de(COD).setEnabled(false);      
					  			        
					// 1. Producer informations
					doc.ds(CO).ds(CMP).ds(PROD).de(COD).setEnabled(false); 
					
					// 2. Consignee
					doc.ds(CO).ds(CMP).ds(CONS).de(NAM).setEnabled(false); 
					doc.ds(CO).ds(CMP).ds(CONS).de(ADR).setEnabled(false); 
					doc.ds(CO).ds(CMP).ds(CONS).de(TEL).setEnabled(false); 
					doc.ds(CO).ds(CMP).ds(CONS).de(EMAIL).setEnabled(false); 
					
					
					// 3. Criteria determining origin (1)   
					doc.ds(CO).ds(CRITERIA).de(OP).setEnabled(false); 
					doc.ds(CO).ds(CRITERIA).de(SW).setEnabled(false); 
					doc.ds(CO).ds(CRITERIA).ds(TAR).de(CHG).setEnabled(false); 
					doc.ds(CO).ds(CRITERIA).ds(PVA).de(FLG).setEnabled(false); 
					  
					doc.ds(CO).ds(CRITERIA).ds(PVA).de(RAT).setEnabled(false); 
					
					doc.ds(CO).ds(CRITERIA).ds(CEDEAO).de(FLG).setEnabled(false); 
					doc.ds(CO).ds(CRITERIA).ds(UEMOA).de(FLG).setEnabled(false); 
					
					
					// 4. Quantity, nature, of goods and number of packages
					doc.ds(CO).ds(PCK).de(NBR).setEnabled(false); 
					
					doc.ds(CO).ds(PCK).de(COD).setEnabled(false); 
					doc.ds(CO).ds(PCK).de(MRK).setEnabled(false); 
					
					// 5. Tariff and statistical nomenclature       
					doc.ds(CO).ds(TAR).de(COD).setEnabled(false); 
					
					// 6. Approval no of goods
					doc.ds(CO).ds(PRODUCT).de(NBR).setEnabled(false); 
							        
					// 7. Gross weight or other measure
					
					doc.ds(CO).ds(WGT).de(GRS).setEnabled(false); 
					doc.ds(CO).ds(SUP).de(COD).setEnabled(false); 			     
					
					// 8. Invoice value      
					doc.ds(CO).ds(INV).de(AMT).setEnabled(false); 
					doc.ds(CO).ds(CUR).de(COD).setEnabled(false); 
					
					// 9. Declaration by exporter      
					doc.ds(CO).ds(CMP).ds(EXP).de(NAM).setEnabled(false); 
					doc.ds(CO).ds(CMP).ds(EXP).de(CTY).setEnabled(false); 
					doc.ds(CO).ds(CMP).ds(EXP).ds(SIGN).de(DAT).setEnabled(false); 
					
					//10.
					doc.ds(CO).ds(AUTH).de(NAM).setEnabled(false);
					doc.ds(CO).ds(AUTH).de(CTY).setEnabled(false);
					
			    	// 12. Request for verification
			        doc.ds(DPA).ds(CUO).de(COD).setEnabled(false); 
			        doc.ds(DPA).ds(CUO).de(NAM).setEnabled(false);
			        doc.ds(DPA).ds(CUO).de(ADR).setEnabled(false);
			        doc.ds(DEST).ds(EXA).de(COD).setEnabled(false);
			        doc.ds(DEST).ds(EXA).de(NAM).setEnabled(false);
			        doc.ds(DEST).ds(EXA).de(CTY).setEnabled(false);
			        doc.ds(DEST).ds(EXA).ds(SIGN).de(DAT).setEnabled(false);
					
			    	// 11. Certification by customs 
//			        doc.ds(CO).ds(CUO).de(COD).setEnabled(true);
//			        doc.ds(CO).ds(CUO).de(NAM).setEnabled(true);
//			        doc.ds(CO).ds(DAU).de(MOD).setEnabled(true);
//			        doc.ds(CO).ds(DAU).ds(REG).de(NBR).setEnabled(true);
//			        doc.ds(CO).ds(DAU).ds(REG).de(DAT).setEnabled(true);    
//			        doc.ds(DPA).ds(EXA).de(COD).setEnabled(true); 
//			        doc.ds(DPA).ds(EXA).de(NAM).setEnabled(true);
//			        doc.ds(DPA).ds(EXA).de(CTY).setEnabled(true);  
//			        doc.ds(DPA).ds(EXA).ds(SIGN).de(DAT).setEnabled(true);
//			              		        
//			    	// 13. Verification
//			        doc.ds(DPA).ds(CTL).ds(EXA).de(COD).setEnabled(true);
//			        doc.ds(DPA).ds(CTL).ds(EXA).de(NAM).setEnabled(true);
//			        doc.ds(DPA).ds(CTL).ds(EXA).de(CTY).setEnabled(true);
//			        doc.ds(DPA).ds(CTL).ds(EXA).ds(SIGN).de(DAT).setEnabled(true);
//			        
//			        doc.ds(DPA).ds(CTL).ds(RSLT).de(CORR).setEnabled(true); 
//			        doc.ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT).setEnabled(true);

				
				} catch (Exception exc) {
				}				
			} //end if
		}
	}

}
