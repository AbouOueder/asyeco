package un.asyeco.serial.ast.client.rules;

import so.kernel.client.DesktopMain;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import un.asyeco.serial.ast.C_Ast;
import un.asyeco.serial.ast.D_Ast;


public class R_SetLocale extends Rule implements C_Ast {

	public void apply(KernelEvent e) {
		D_Ast doc = (D_Ast) e.getSource();

		if (doc != null && DesktopMain.currentLocale() != null && DesktopMain.currentLocale().getLanguage() != null) {
			doc.de(LNG).tryToSetContent(DesktopMain.currentLocale().getLanguage());
		} else {
			System.out.println(" DesktopMain.currentLocale is Null");
		}
	}
}
