package un.asyeco.serial.eco.reg.client.rules;

import so.kernel.client.DesktopMain;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import un.asyeco.serial.eco.reg.C_Reg;
import un.asyeco.serial.eco.reg.D_Reg;


public class R_SetLocale extends Rule implements C_Reg {

	public void apply(KernelEvent e) {
		D_Reg doc = (D_Reg) e.getSource();

		if (doc != null && DesktopMain.currentLocale() != null && DesktopMain.currentLocale().getLanguage() != null) {
			doc.de(LNG).tryToSetContent(DesktopMain.currentLocale().getLanguage());
		} else {
			System.out.println(" DesktopMain.currentLocale is Null");
		}
	}
}
