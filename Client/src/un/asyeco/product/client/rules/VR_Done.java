package un.asyeco.product.client.rules;

//import javax.swing.filechooser.*;
import java.awt.BorderLayout;
import java.util.Calendar;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import so.kernel.client.DesktopMain;
import so.kernel.client.KEndOfTransactionDialog;
import so.kernel.client.KVisualDocument;
import so.kernel.core.DataField;
import so.kernel.core.DataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.Operation;
import so.kernel.core.Rule;
import so.kernel.core.events.CommitedServerEvent;
import so.kernel.core.events.DoCloseEvent;
import so.kernel.core.events.OperationDoneEvent;
import so.kernel.core.events.SendMailEvent;
import so.kernel.core.events.StartOperationEvent;
import so.swing.KOptionPane;
import un.asyeco.product.C_Product;
import un.asyeco.product.D_Product;
import un.asyeco.product.client.VD_Product;
import un.asyeco.sharedUtils.ServerDateTime;
import un.kernel.client.KEndOfTransactionDialogDisplayer;
import un.kernel.client.KOuterPage;



public class VR_Done extends Rule implements C_Product {

	D_Product doc;

	VD_Product vd;

	public VR_Done(D_Product doc, VD_Product vd) {
		this.doc = doc;
		this.vd = vd;
	}

	public void apply(KernelEvent e) {

		OperationDoneEvent ev = (OperationDoneEvent) e;
		String message = null;
		String arg = null;
		Operation op = ev.getFinalAction();

		DataSet set = ev.getServerEvent().getDataSet();
		ServerDateTime s_date = new ServerDateTime();
		s_date.initServerDateTime(doc, e);

		if (set.de(Warning_msg) != null && set.de(Warning_msg).getContentString() != null)
			KOptionPane.showMessageDialog(DesktopMain.sharedInstance(), set.de(Warning_msg).getContentString());

		if ((op.getID() == OI_CREATE) ) { // GVA <patch ID="Integration of regional transit" version="4.2.2" type="modification" date="Nov 27, 2013" author="ahmed" />

			//DataField serialNbrField = set.de(RESULT_REGISTERED_SERIAL_NBR);
			//DataField serialCharField = set.de(RESULT_REGISTERED_SERIAL_CHAR);
			//DataField crnField = set.de(RESULT_REGISTERED_CRN);

			//OG05122009 BarCode
			//co.ds(IDE).de(BAR).tryToSetContent(set.de(BAR).getContent());
//			doc.ds(CO).de(NBR).tryToSetContent(serialNbrField.getContent());
//			doc.ds(CO).ds(REG).de(SER).tryToSetContent(serialCharField.getContent());
//			doc.ds(CO).ds(REG).de(DAT).tryToSetContent(s_date);
//			doc.ds(CO).de(CRN).tryToSetContent(crnField.getContent());
			int curYear = Calendar.getInstance().get(Calendar.YEAR);
			// GVA <patch ID="Bug #699 T1 operations" type="modification" date="Oct 01, 2014" author="JD">
			arg = lng("Registration is done") ;

//			arg = lng("Registration is done") + " # " + serialCharField.getContent() +" " + serialNbrField.getContent() + " " + curYear + " " + doc.ds(CO).ds(CTY).de(COD).getContent() +
//					  " " + doc.ds(CO).ds(REG).de(DAT).getContent() + "\n" + "CRN : " + crnField.getContent();
			// GVA <patch ID="Bug #699 T1 operations"/>
			message = lng(arg, new Object[] {  curYear});
			// KOptionPane.showMessageDialog(DesktopMain.sharedInstance(),
			// message);
		} 
//		else if ((op.getID() == OI_SUBMIT) ) { // GVA <patch ID="Integration of regional transit" version="4.2.2" type="modification" date="Nov 27, 2013" author="ahmed" />
//
//			DataField serialNbrField = set.de(RESULT_SUBMIT_SERIAL_NBR);
//			DataField serialCharField = set.de(RESULT_SUBMIT_SERIAL_CHAR);
//			//DataField crnField = set.de(RESULT_REGISTERED_CRN);
//
//			//OG05122009 BarCode
//			//co.ds(IDE).de(BAR).tryToSetContent(set.de(BAR).getContent());
//			doc.ds(CO).ds(SUB).de(NBR).tryToSetContent(serialNbrField.getContent());
//			doc.ds(CO).ds(SUB).de(SER).tryToSetContent(serialCharField.getContent());
//			doc.ds(CO).ds(SUB).de(DAT).tryToSetContent(s_date);
//			
//			//doc.ds(CO).de(CRN).tryToSetContent(crnField.getContent());
//			int curYear = Calendar.getInstance().get(Calendar.YEAR);
//			doc.ds(CO).ds(SUB).de(YER).tryToSetContent(curYear);
//			// GVA <patch ID="Bug #699 T1 operations" type="modification" date="Oct 01, 2014" author="JD">
//			arg = lng("Registration is done") + " # " + serialCharField.getContent() +" " + serialNbrField.getContent() + " " + curYear + " " + doc.ds(CO).ds(CTY).de(COD).getContent() +
//					  " " + doc.ds(CO).ds(SUB).de(DAT).getContent() ;
//			// GVA <patch ID="Bug #699 T1 operations"/>
//			message = lng(arg, new Object[] { serialNbrField.getContent() });
//			// KOptionPane.showMessageDialog(DesktopMain.sharedInstance(),
//			// message);
//		}
			
		else {
			message = lng("{0} done.", new Object[] { op.getHumanName() });
			// KOptionPane.showMessageDialog(DesktopMain.sharedInstance(),
			// message);

		}

		int type = KEndOfTransactionDialog.YES + KEndOfTransactionDialog.VIEW + KEndOfTransactionDialog.PRINT + KEndOfTransactionDialog.HELP;

		KEndOfTransactionDialog dlg;
		if ((op.getID() == OI_ACCEPT) ) {
	        // GVA <patch ID="Bug #268, T1 confirmation box does not have the background image as the SAD" version="4.2.2" type="modification" date="Aug 29, 2013" author="Jaouhar"/>
			dlg =  KEndOfTransactionDialogDisplayer.createKEndOfTransactionDialog(DesktopMain.sharedInstance(), message, type /*+ KEndOfTransactionDialog.FORWARD*/);
		} else {

			dlg = KEndOfTransactionDialogDisplayer.createKEndOfTransactionDialog(DesktopMain.sharedInstance(), message, type);
	        // GVA </patch>
		}

		// Print selections
		JCheckBox printDocument = new JCheckBox(lng("Print Producer and Products document, customised"));
		dlg.addSelectionComponent(printDocument);
		JCheckBox printDocumentCondensed = new JCheckBox(lng("Print Hardcopy"));
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

        // GVA <patch ID="Bug #268, T1 confirmation box does not have the background image as the SAD" version="4.2.2" type="modification" date="Aug 29, 2013" author="Jaouhar"/>
		//dlg.show();
		KEndOfTransactionDialogDisplayer.showKEndOfTransaction(dlg);
        // GVA <patch ID="Bug #268, T1 confirmation box does not have the background image as the SAD"/>

		int option = dlg.getOption();

		// Selection commands

		// If print document selection, then request the document printout
		if (printDocument.isSelected()) {

			//doc.fire(new KernelEvent(doc, DO_PRINT_CUSTOMISED));
		}

		// If print advice selection, then request the advice printout
		if (printDocumentCondensed.isSelected()) {

			//doc.fire(new KernelEvent(doc, DO_PRINTALT));
		}

		// If mail "send to" selection, then send the mail
		if (sendTo.isSelected()) {

			// System.out.println("option " + option + " sendTo " +
			// sendTo.isSelected() + " " + destinee.getSelectedItem());
			SendMailEvent sme = new SendMailEvent(doc);
			sme.addTo(fld_destinee.getString(""));
			sme.setSubject("Subject");
			sme.setContent("Body");
			if (ev.getServerEvent() instanceof CommitedServerEvent) {
				sme.addAttachment(doc, (CommitedServerEvent) ev.getServerEvent());
			} else {
				sme.addAttachment(doc);
			}
			doc.fire(sme);
		}

		// Option, execution and end of dialog

		// If option is YES, then close the document
		if (option == KEndOfTransactionDialog.YES) {

			doc.fire(new DoCloseEvent(ev.getDocument()));
		}

		// If option is VIEW, then leave the document on desktop in view mode
		if (option == KEndOfTransactionDialog.VIEW) {

		}

		// If option is FORWARD, then continue to work on the document
		if (option == KEndOfTransactionDialog.FORWARD) {

			if ((op.getID() == OI_CREATE) ) {

				doc.fire(new DoCloseEvent(ev.getDocument()));
				doc.fire(new StartOperationEvent(doc, "un.asyeco.Product.D_Product", OC_NEW));
			}
		}

		// so.kernel.core.events.OperationDoneEvent ev =
		// (so.kernel.core.events.OperationDoneEvent)e;
		// t1.fire(new DoCloseEvent(ev.getDocument()));
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}

	private static String lng(String pattern, Object value) {
		return so.i18n.IntlFormat.createMessage("un.asyeco", pattern, value);
	}

}
