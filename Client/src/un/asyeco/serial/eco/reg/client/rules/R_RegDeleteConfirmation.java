package un.asyeco.serial.eco.reg.client.rules;

import java.text.MessageFormat;

import so.kernel.client.DesktopMain;
import so.kernel.core.DataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.kernel.core.events.PrepareDocumentToSendEvent;
import so.swing.KOptionPane;
import un.asyeco.serial.eco.reg.C_Reg;

/**
 * Request user confirmation on Delete final actions.
 * 
 * The trigger event is a FINAL_ACTION_REQUESTED event.
 * 
 */
public class R_RegDeleteConfirmation extends Rule implements C_Reg {

	public void apply(KernelEvent e) {

		PrepareDocumentToSendEvent ev = (PrepareDocumentToSendEvent) e;
		int op = ev.getOperation().getID();

		DataSet dec = null;

		if (op == OI_DELETE) {
			dec = ev.getFinderSelectedDocument();
		}

		if (dec != null) {
			String data = dec.de(YER).getString("") + " " + dec.ds(CTY).de(COD).getString("") + " " + dec.de(CHR).getString("");
			// ask confirmation
			MessageFormat formater = new MessageFormat(lng("Do you want to delete this serial: {0}? please confirm..."));

			int answer = KOptionPane.showConfirmDialog(DesktopMain.sharedInstance(), formater.format(new Object[] { data }), lng("Confirmation"),
					KOptionPane.OK_CANCEL_OPTION, KOptionPane.QUESTION_MESSAGE, null);
			if (answer != KOptionPane.OK_OPTION) {
				// don't do it!
				ev.consume();
				return;
			}
		}
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}

}
