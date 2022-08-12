package un.asyeco.serial.ast.client.rules;

import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import un.asyeco.serial.ast.C_Ast;
import un.asyeco.serial.ast.D_Ast;
import un.asyeco.serial.ast.client.VD_Ast;

public class R_ChkLanguage extends Rule implements C_Ast {
	private VD_Ast vd;

	private D_Ast doc;

	public R_ChkLanguage(VD_Ast vd, D_Ast doc) {
		this.vd = vd;
		this.doc = doc;
	}

	public void apply(KernelEvent e) {

		doc.de(LNG).tryToSetContent(vd.getLocale().getLanguage());
		doc.de(LNG).changeOriginalContent();

	}

}
