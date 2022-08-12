package un.asyeco.serial.eco.submit.client.rules;

import so.kernel.client.DesktopMain;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import un.asyeco.serial.eco.submit.C_Sub;
import un.asyeco.serial.eco.submit.D_Sub;


public class R_SetLocale extends Rule implements C_Sub {

	public void apply(KernelEvent e) {
		D_Sub doc = (D_Sub) e.getSource();

		if (doc != null && DesktopMain.currentLocale() != null && DesktopMain.currentLocale().getLanguage() != null) {
			doc.de(LNG).tryToSetContent(DesktopMain.currentLocale().getLanguage());
		} else {
			System.out.println(" DesktopMain.currentLocale is Null");
		}
	}
}
