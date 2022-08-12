package un.asyeco.serial.eco.submit.client.rules;

import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import un.asyeco.serial.eco.submit.C_Sub;
import un.asyeco.serial.eco.submit.D_Sub;
import un.asyeco.serial.eco.submit.client.VD_Sub;

public class R_ChkLanguage extends Rule implements C_Sub {
	private VD_Sub vd;

	private D_Sub doc;

	public R_ChkLanguage(VD_Sub vd, D_Sub doc) {
		this.vd = vd;
		this.doc = doc;
	}

	public void apply(KernelEvent e) {

		doc.de(LNG).tryToSetContent(vd.getLocale().getLanguage());
		doc.de(LNG).changeOriginalContent();

	}

}
