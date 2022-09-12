package un.asyeco.eco.client.rules;

import java.text.MessageFormat;

import so.kernel.client.DesktopMain;
import so.kernel.core.DataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.kernel.core.events.PrepareDocumentToSendEvent;
import so.swing.KOptionPane;
import un.asyeco.eco.C_eCO;


public class R_PrintConfirmation extends Rule implements C_eCO {

	public void apply(KernelEvent e) {

		PrepareDocumentToSendEvent ev = (PrepareDocumentToSendEvent) e;
		int op = ev.getOperation().getID();

		DataSet doc = null;

		if (op == OI_PRINT) {
			doc = (DataSet) ev.getSource();
		}

		if (doc != null) {
			// ask confirmation
			MessageFormat formater = new MessageFormat(lng("Do you want to print the certificate : {0}? please confirm..."));

//			int answer = KOptionPane.showConfirmDialog(DesktopMain.sharedInstance(), formater.format(new Object[] { doc.ds(IDE).de(YEA).getString("")
//					+ " " + doc.ds(IDE).de(SER).getString("") + " " + doc.ds(IDE).de(NBR).getString("") }), lng("Confirmation"),
//					KOptionPane.OK_CANCEL_OPTION, KOptionPane.QUESTION_MESSAGE, null);

			int answer = KOptionPane.showConfirmDialog(DesktopMain.sharedInstance(), formater.format(new Object[] { }), lng("Confirmation"),
					KOptionPane.OK_CANCEL_OPTION, KOptionPane.QUESTION_MESSAGE, null);

			if (answer == 0) {
				// do it!
				// doc.fire(new KernelEvent(doc, DO_PRINT));
				// INFO Vincent 17/2/2009 unifying receipt print.
				doc.fire(new KernelEvent(doc, DO_PRINT_PDF));
				return;
			}
			// GVA <patch ID="Improvement #368 - Unified Printouts and Taxes Breakdown" version="4.2.2" type="Modification" date="Oct 11, 2013" author="Leonardo Flores">
			// Stop event or new GCF record will be created
			else e.consume();
		    // </patch ID="Improvement #368 - Unified Printouts and Taxes Breakdown">
		}
	}

	/**
	 * Retrieves a property string in the current working language.
	 */
	public static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
