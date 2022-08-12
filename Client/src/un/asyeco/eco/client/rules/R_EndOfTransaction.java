package un.asyeco.eco.client.rules;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import so.kernel.client.DesktopMain;
import so.kernel.client.KEndOfTransactionDialog;
import so.kernel.client.KVisualDocument;
import so.kernel.core.DataField;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.kernel.core.events.CommitedServerEvent;
import so.kernel.core.events.DoCloseEvent;
import so.kernel.core.events.SendMailEvent;
import so.kernel.core.events.StartOperationEvent;

import un.asyeco.eco.C_eCO;
import un.asyeco.eco.D_eCO;
import un.asyeco.eco.client.VD_eCO;
import un.kernel.client.KEndOfTransactionDialogDisplayer;
import un.kernel.client.KOuterPage;

public class R_EndOfTransaction extends Rule implements C_eCO {

	// This rules displays the end of transaction dialog, and execute user's
	// selection.

	private D_eCO doc;

	private VD_eCO vd;

	public R_EndOfTransaction(VD_eCO vd) {
		this.vd = vd;
		this.doc = (D_eCO) vd.getDocument();
	}

	public void apply(KernelEvent e) {

		so.kernel.core.events.OperationDoneEvent ev = (so.kernel.core.events.OperationDoneEvent) e;
//		Date rcpDat = (Date) bag.ds(BAG).ds(IDE).ds(REG).de(DAT).getContent();
//		String strRcpDat = "";
//		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		if (rcpDat != null) {
//			strRcpDat = formatter.format(rcpDat);
//		}

		String message = ev.getFinalAction().getHumanName() + " " + 
				lng("is done.") + "\n" + 
				lng("Certicate Document ref:") + " "
				+  doc.ds(CO).de(NBR).getString("") ;
		KEndOfTransactionDialog dlg = KEndOfTransactionDialogDisplayer.createKEndOfTransactionDialog(DesktopMain.sharedInstance(), message, null);		
		// Print selections
		JCheckBox printDocument = new JCheckBox(lng("Print Certificate Document"));
		dlg.addSelectionComponent(printDocument);
		JCheckBox printDocumentCondensed = new JCheckBox(lng("Print Certificate Document, condensed"));
		dlg.addSelectionComponent(printDocumentCondensed);

		// e-mail selection
		KOuterPage mailPanel = new KOuterPage(vd);
		mailPanel.setLayout(new BorderLayout(15, 0));
		JCheckBox sendTo = new JCheckBox(lng("e-Mail to:"));
		mailPanel.add(BorderLayout.WEST, sendTo);

		// to review, destinee list

		JComboBox destinee = mailPanel.getComboBoxPool().getKComboBox();
		mailPanel.add(BorderLayout.CENTER, destinee);
		DataField fld_destinee = mailPanel.tmp("fld_destinee");
		mailPanel.addFacet(destinee, fld_destinee, KVisualDocument.fmt_ANY35(), "history_email_recievers");

		dlg.addSelectionComponent(mailPanel);

		//		dlg.show();
		KEndOfTransactionDialogDisplayer.showKEndOfTransaction(dlg);

		int option = dlg.getOption();

		// Selection commands

		// If print document selection, then request the document printout
		if (printDocument.isSelected()) {
			//doc.fire(new KernelEvent(ev.getServerEvent().getCurrentDataSet().ds(EVENT_RESULT_DATASET).de(INSTANCE_ID), DO_PRINT));
		}

		// If print advice selection, then request the advice printout
		if (printDocumentCondensed.isSelected()) {
			//bag.fire(new KernelEvent(bag, DO_PRINTALT));
		}

		// If mail "send to" selection, then send the mail
		if (sendTo.isSelected()) {

			// System.out.println("option " + option + " sendTo " +
			// sendTo.isSelected() + " " + destinee.getSelectedItem());
			SendMailEvent sme = new SendMailEvent(doc);
			sme.addTo(fld_destinee.getString(""));
			sme.setSubject(lng("Subject"));
			sme.setContent(lng("Body"));
			if (ev.getServerEvent() instanceof CommitedServerEvent) {
				sme.addAttachment(doc, (CommitedServerEvent) ev.getServerEvent());
			} else {
				sme.addAttachment(doc);
			}
			doc.fire(sme);
		}

		// If option is VIEW, then leave the document on desktop in view mode
		if (option == KEndOfTransactionDialog.VIEW) {

		}
		// If option is FORWARD, then continue to work on the document
		if (option == KEndOfTransactionDialog.FORWARD) {
			//cashDucOthIn.fire(new DoCloseEvent(ev.getDocument()));
			String ope = OC_NEW;
			doc.fire(new StartOperationEvent(doc, "un.asyeco.eco.D_eCO", ope));
		}
		if (option == KEndOfTransactionDialog.YES || option == KEndOfTransactionDialog.FORWARD) {

			doc.fire(new DoCloseEvent(ev.getDocument()));
		}

	}

	/**
	 * Retrieves a property string in the current working language.
	 */
	public static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}

}
