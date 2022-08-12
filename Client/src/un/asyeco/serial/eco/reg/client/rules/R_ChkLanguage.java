package un.asyeco.serial.eco.reg.client.rules;

import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import un.asyeco.serial.eco.reg.C_Reg;
import un.asyeco.serial.eco.reg.D_Reg;
import un.asyeco.serial.eco.reg.client.VD_Reg;

public class R_ChkLanguage extends Rule implements C_Reg {
	private VD_Reg vd;

	private D_Reg doc;

	public R_ChkLanguage(VD_Reg vd, D_Reg doc) {
		this.vd = vd;
		this.doc = doc;
	}

	public void apply(KernelEvent e) {

		doc.de(LNG).tryToSetContent(vd.getLocale().getLanguage());
		doc.de(LNG).changeOriginalContent();

	}

}
