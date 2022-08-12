package un.asyeco.eco; 

 
import static so.kernel.core.KernelEventConstants.DATA_VERIFY;


import java.util.Date;
import java.util.Iterator;

import so.kernel.core.DataField;
import so.kernel.core.HTVerifier.Row;
import so.kernel.core.KNumberedSubDataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.MessageDestination;
import so.kernel.core.Rule;
import un.asyref.UNTARTABView.C_UNTARTABView;
import un.globalConfig.util.GlobalConfigUtilities;
import un.kernel.core.ArefHTCompatible;
import un.kernel.core.KDocument;
import un.kernel.core.VerifierDelegator;
public class R_ChkTarLocal extends Rule implements C_eCO {

    private static R_ChkTarLocal sharedInstance = null;

    private static  String np = GlobalConfigUtilities.getProperty("Client.tar.config.NP");
    private boolean isAref = false;
    private R_ChkTarLocal() {
    }
    
    public static final R_ChkTarLocal sharedInstance() {
    	if (null == sharedInstance) 
    		sharedInstance = new R_ChkTarLocal();
    	return sharedInstance;
	}

	public void apply(KernelEvent e) {

		DataField d = e.getDataField();
		D_eCO doc = (D_eCO) d.getDocument();
		this.isAref = KDocument.isUsingAref(doc);
		if (!d.isEnabled()) {
			return;
		}

		if (OP_VIEW.equals(doc.getStartedOperation().getName()))
			return;

		if ("".equals(doc.getOperationClassName())) {
			return;
		}

		// ---- Direct Assessment Operations ----//
		// if (OC_ASSESS_REGISTER_DIRECT.equals(doc.getOperationClassName()) ||
		// OC_ASSESS_FROM_SELECTED_STANDBY.equals(doc.getOperationClassName())
//		if (OC_RELEASE_ORDER.equals(doc.getOperationClassName()) || OC_PRINT_RELEASE_ORDER.equals(doc.getOperationClassName())
//				|| OC_CLEAR.equals(doc.getOperationClassName()) || OC_CLEAR_SEL.equals(doc.getOperationClassName())
//				|| OC_REROUTE_TO_QUERY.equals(doc.getOperationClassName()) || OC_MANUAL_EXAMINER_ASSIGNMENT.equals(doc.getOperationClassName())
//				|| OC_CANCEL.equals(doc.getOperationClassName())) {
//			return;
//		}

		// If ALL fields (NB1 and NB2) are empty, no action is required
		if (doc.ds(CO).ds(TAR).de(COD).getContent() == null)  {
			return;
		}

		MessageDestination md = doc.ds(CO).ds(TAR).de(COD);

		//ArefHTCompatible verifier = VerifierDelegator.getVerifier(doc);
		String np = GlobalConfigUtilities.getProperty("Client.tar.config.NP");
		if (np == null) {
			setError(md, lng("Configuration is not set correctly, please contact your administrator"), md, e);
			return;
		}
		boolean exist = false;
		String tar_nb1 = doc.ds(CO).ds(TAR).de(COD).getString("");
		
		if (tar_nb1.length() != 10) {
			setError(md, lng("HS code incorrect"), md, e);
			return;
		}
		// If the commodity code exist, then we retrieve all the needed
		// information

		ArefHTCompatible verifier = VerifierDelegator.getVerifier(doc);
		Date wde = (Date)doc.de(WDE).getContent();
		String tar_pr2=doc.ds(CO).ds(TAR).de(COD).getContentString().substring(8, 10);
		
		 
		if (("2".equals(np) && tar_pr2.length() < 2)   ||
			("3".equals(np) && tar_pr2.length() < 3)   ) {
			   setError(md, lng("HS code incorrect, HS2 mandatory"), md, e);
				return;
			}
		String[] colName= null;
		Object[] colValue= null;
		if (!"0".equals(np)){
			colName = new String[]{HS6_COD, TAR_PR1, TAR_PR2};
			colValue= new Object[]{doc.ds(CO).ds(TAR).de(COD).getContentString().substring(0, 6),
					doc.ds(CO).ds(TAR).de(COD).getContentString().substring(6, 8), tar_pr2};
		}else{
			colName = new String[]{HS6_COD, TAR_PR1};
			colValue= new Object[]{doc.ds(CO).ds(TAR).de(COD).getContentString().substring(0, 6),
					doc.ds(CO).ds(TAR).de(COD).getContentString().substring(6, 8)};

		}

		Iterator it = verifier.find(TAR_TAB, wde, colName, colValue);
		String dsc   = null ;
		String uom1  = null;
		String uom2  = null;
		String uom3  = null;
		
		if (it!= null && it.hasNext())
			
		 {
			Object rw = it.next();
			 dsc   = (String)getColumn(rw,C_UNTARTABView.TAR_DSC);
			 uom1  = (String)getColumn(rw,C_UNTARTABView.UOM_COD1);
			 uom2  = (String)getColumn(rw,C_UNTARTABView.UOM_COD2);
			 uom3  = (String)getColumn(rw,C_UNTARTABView.UOM_COD3);
			 
			 doc.ds(CO).ds(TAR).de(DSC).tryToSetContent(dsc);
			 
//				if (uom1!=null && !"".equals(uom1)) {
//					sup.at(1).de(COD).setEnabled(false);
//					sup.at(1).de(QTY).setEnabled(true);
//					sup.at(1).de(COD).tryToSetContent(uom1.trim());
//				} 			
		 }	else{
			 setError(md, lng("HS code incorrect"), md,e);
				
		 }
		if (it != null) verifier.closeIterator(it);
			
		
	    
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
	 private Object getColumn(Object rowIn, String column){
	    	if (isAref){
	    		so.util.Row row = (so.util.Row)rowIn;
	    		return row.get(column);
	    	}else{
	    		Row row = (Row)rowIn;
	    		return row.get(column);
	    	}
	    }
}