package un.asyeco.eco; 

import static so.kernel.core.KernelEventConstants.DATA_VERIFY;
import so.kernel.core.DataField;
import so.kernel.core.DataSet;
import so.kernel.core.KNumberedSubDataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.MessageDestination;
import so.kernel.core.Rule;
import so.kernel.core.TransactionEvent;
import un.globalConfig.util.GlobalConfigUtilities;
public class R_ChkTar extends Rule implements C_eCO {

    private static R_ChkTar sharedInstance = null;

    private R_ChkTar() {
    }
    
    public static final R_ChkTar sharedInstance() {
    	if (null == sharedInstance) 
    		sharedInstance = new R_ChkTar();
    	return sharedInstance;
	}

	public void apply(KernelEvent e) {

		DataField d = e.getDataField();
		D_eCO doc = (D_eCO) d.getDocument();

		if (!d.isEnabled()) {
			return;
		}

		if (OP_VIEW.equals(doc.getStartedOperation().getName()))
			return;

		if ("".equals(doc.getOperationClassName())) {
			return;
		}

		// ---- Direct Assessment Operations ----//
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
		//String tar_nb2 = itm.ds(TAR).ds(HSC).de(NB2).getString("");

		if (tar_nb1.length() != 10) {
			setError(md, lng("HS code incorrect"), md, e);
			return;
		}

		
		// If the commodity code exist, then we retrieve all the needed
		// information
		DataSet source = new DataSet();
		source.add(HS6_COD);
		source.add(TAR_PR1);
		source.de(HS6_COD).tryToSetContent(doc.ds(CO).ds(TAR).de(COD).getContentString().substring(0, 6));
		source.de(TAR_PR1).tryToSetContent(doc.ds(CO).ds(TAR).de(COD).getContentString().substring(6, 8));
		source.add(DAT);
		source.de(DAT).tryToSetContent(doc.de(WDE).getContent());
		source.add(TAR_PR2);
		if ("0".equals(np)) {
			source.de(TAR_PR2).tryToSetContent(null);
		} else {
			source.de(TAR_PR2).tryToSetContent(doc.ds(CO).ds(TAR).de(COD).getContentString().substring(8, 10));
		}

		// Apply the server rule SR_ChkTar
		//SAD Optimisation January 2011 - begin		
		TransactionEvent te = (TransactionEvent) doc.applyMiddleEvent(CHK_TAR, source);
	    //SAD Optimisation January 2011 - end		

		if (te.getException() != null || te.getResult() == null) {
			setError(md, lng("System error: Server exception in rule R_ChkTar"), md, e);
		} else {
			if (te.getResult().de(DSC) == null) {
				setError(md, lng("HS code incorrect"), md, e);
				doc.ds(CO).ds(SUP).de(COD).tryToSetContent(null);
			} else {
				DataSet res = te.getResult();
				doc.ds(CO).ds(TAR).de(DSC).tryToSetContent(res.de(DSC).getString(""));
				if (!"".equals(res.de(UM1).getString("").trim())) {
					doc.ds(CO).ds(SUP).de(COD).tryToSetContent(res.de(UM1).getString("").trim());
//					sup.at(1).de(QTY).setEnabled(true);
//					sup.at(1).de(COD).tryToSetContent(res.de(UM1).getString("").trim());
				} else doc.ds(CO).ds(SUP).de(COD).tryToSetContent(null);
			}
		}

	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}

}