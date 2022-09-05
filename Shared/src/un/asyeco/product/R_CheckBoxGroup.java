package un.asyeco.product;

import so.kernel.core.DataField;
import so.kernel.core.DataSet;
import so.kernel.core.Document;
import so.kernel.core.KNumberedSubDocument;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.util.DebugOutput;
import un.asyeco.product.C_Product;
import un.asyeco.product.DS_ProductItm;
import un.asyeco.product.D_Product;


public class R_CheckBoxGroup extends Rule implements C_Product {
	
//	private VD_Product vd;
//	
//	
//
//	public R_CheckBoxGroup(VD_Product vd) {
//		this.vd = vd;
//	}



	public void apply(KernelEvent e) {
		
		DataField d = e.getDataField();
		D_Product prod = (D_Product) d.getDocument();	
		DS_ProductItm doc = (DS_ProductItm) d.getSubDocument();

		if (prod instanceof D_Product) {	
			
			if (e.getDataField() == doc.ds(CRITERIA).ds(OP).de(FLG)) {
				if (doc.ds(CRITERIA).ds(OP).de(FLG).getBoolean(false)) {				
				    doc.ds(CRITERIA).ds(SW).de(FLG).tryToSetContent(false);
				    doc.ds(CRITERIA).ds(TAR).ds(CHG).de(FLG).tryToSetContent(false);
				    doc.ds(CRITERIA).ds(PVA).de(FLG).tryToSetContent(false);
				    doc.ds(CRITERIA).ds(PVA).de(RAT).setEnabled(false);
				    doc.ds(CRITERIA).ds(SW).de(FLG).changeOriginalContent();
				    doc.ds(CRITERIA).ds(TAR).ds(CHG).de(FLG).changeOriginalContent();
				    doc.ds(CRITERIA).ds(PVA).de(FLG).changeOriginalContent();
				    doc.ds(CRITERIA).ds(PVA).de(RAT).tryToSetContentNull();
				}

			} else if (e.getDataField() == doc.ds(CRITERIA).ds(SW).de(FLG)) {
				if (doc.ds(CRITERIA).ds(SW).de(FLG).getBoolean(false)) {
					doc.ds(CRITERIA).ds(OP).de(FLG).tryToSetContent(false);				
					doc.ds(CRITERIA).ds(OP).de(FLG).changeOriginalContent();				
				} 
			} else if (e.getDataField() == doc.ds(CRITERIA).ds(TAR).ds(CHG).de(FLG)){
				if (doc.ds(CRITERIA).ds(TAR).ds(CHG).de(FLG).getBoolean(false)) {
					doc.ds(CRITERIA).ds(OP).de(FLG).tryToSetContent(false);
					doc.ds(CRITERIA).ds(SW).de(FLG).tryToSetContent(true);
					doc.ds(CRITERIA).ds(PVA).de(FLG).tryToSetContent(false);
					doc.ds(CRITERIA).ds(PVA).de(RAT).setEnabled(false);
					doc.ds(CRITERIA).ds(OP).de(FLG).changeOriginalContent();
					doc.ds(CRITERIA).ds(SW).de(FLG).changeOriginalContent();
					doc.ds(CRITERIA).ds(PVA).de(FLG).changeOriginalContent();
					doc.ds(CRITERIA).ds(PVA).de(RAT).tryToSetContentNull();
				} 

			} else if (e.getDataField() == doc.ds(CRITERIA).ds(PVA).de(FLG)){
				if (doc.ds(CRITERIA).ds(PVA).de(FLG).getBoolean(false)) {
					doc.ds(CRITERIA).ds(OP).de(FLG).tryToSetContent(false);
					doc.ds(CRITERIA).ds(SW).de(FLG).tryToSetContent(true);
					doc.ds(CRITERIA).ds(PVA).de(RAT).setEnabled(true);
					doc.ds(CRITERIA).ds(TAR).ds(CHG).de(FLG).tryToSetContent(false);				
					doc.ds(CRITERIA).ds(OP).de(FLG).changeOriginalContent();
					doc.ds(CRITERIA).ds(SW).de(FLG).changeOriginalContent();
					doc.ds(CRITERIA).ds(TAR).ds(CHG).de(FLG).changeOriginalContent();
				} 
			} 
		}


	}

}
