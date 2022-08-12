package un.asyeco.eco;

import so.kernel.core.Document;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;

public class R_CheckBoxGroup extends Rule implements C_eCO {

	public void apply(KernelEvent e) {

		Document doc = e.getData().getDocument();

		if (e.getDataField() == doc.ds(CO).ds(CRITERIA).de(OP)) {
			if (doc.ds(CO).ds(CRITERIA).de(OP).getBoolean(false)) {				
			    doc.ds(CO).ds(CRITERIA).de(SW).tryToSetContent(false);
			    doc.ds(CO).ds(CRITERIA).ds(TAR).de(CHG).tryToSetContent(false);
			    doc.ds(CO).ds(CRITERIA).ds(PVA).de(FLG).tryToSetContent(false);
			    doc.ds(CO).ds(CRITERIA).de(SW).changeOriginalContent();
			    doc.ds(CO).ds(CRITERIA).ds(TAR).changeOriginalContent();
			    doc.ds(CO).ds(CRITERIA).ds(PVA).de(FLG).changeOriginalContent();
			}

		} else if (e.getDataField() == doc.ds(CO).ds(CRITERIA).de(SW)) {
			if (doc.ds(CO).ds(CRITERIA).de(SW).getBoolean(false)) {
				doc.ds(CO).ds(CRITERIA).de(OP).tryToSetContent(false);				
				doc.ds(CO).ds(CRITERIA).de(OP).changeOriginalContent();				
			}

		} else if (e.getDataField() == doc.ds(CO).ds(CRITERIA).ds(TAR).de(CHG)){
			if (doc.ds(CO).ds(CRITERIA).ds(TAR).de(CHG).getBoolean(false)) {
				doc.ds(CO).ds(CRITERIA).de(OP).tryToSetContent(false);
				doc.ds(CO).ds(CRITERIA).de(SW).tryToSetContent(true);
				doc.ds(CO).ds(CRITERIA).ds(PVA).de(FLG).tryToSetContent(false);
				doc.ds(CO).ds(CRITERIA).de(OP).changeOriginalContent();
				doc.ds(CO).ds(CRITERIA).de(SW).changeOriginalContent();
				doc.ds(CO).ds(CRITERIA).ds(PVA).de(FLG).changeOriginalContent();
			}

		} else if (e.getDataField() == doc.ds(CO).ds(CRITERIA).ds(PVA).de(FLG)){
			if (doc.ds(CO).ds(CRITERIA).ds(PVA).de(FLG).getBoolean(false)) {
				doc.ds(CO).ds(CRITERIA).de(OP).tryToSetContent(false);
				doc.ds(CO).ds(CRITERIA).de(SW).tryToSetContent(true);
				doc.ds(CO).ds(CRITERIA).ds(TAR).de(CHG).tryToSetContent(false);				
				doc.ds(CO).ds(CRITERIA).de(OP).changeOriginalContent();
				doc.ds(CO).ds(CRITERIA).de(SW).changeOriginalContent();
				doc.ds(CO).ds(CRITERIA).ds(TAR).de(CHG).changeOriginalContent();
			}

		} else if (e.getDataField() == doc.ds(DPA).ds(CTL).ds(RSLT).de(CORR)){
			if (doc.ds(DPA).ds(CTL).ds(RSLT).de(CORR).getBoolean(false)) {
				doc.ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT).tryToSetContent(false);		
				doc.ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT).changeOriginalContent();
			}
		} else if (e.getDataField() == doc.ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT)){
			if (doc.ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT).getBoolean(false)) {
				doc.ds(DPA).ds(CTL).ds(RSLT).de(CORR).tryToSetContent(false);		
				doc.ds(DPA).ds(CTL).ds(RSLT).de(CORR).changeOriginalContent();
			}
		}

	}

}
