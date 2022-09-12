/**
 * @author Aboubacar OUEDRAOGO 06/09/2022
 */
package un.asyeco.eco.client.rules; 

import static un.asyeco.eco.C_eCO.*;
import so.kernel.client.DesktopMain;
import so.kernel.core.BinderShortcut;
import so.kernel.core.ConnectionToServer;
import so.kernel.core.DataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.kernel.core.TransactionEvent;
import so.kernel.core.events.ServerEvent;
import so.swing.KOptionPane;
import un.asyeco.eco.D_eCO;
import un.kernel.util.AWClientTranslator;
import un.kernel.util.JasperPrintingToolClient;

//GVA <patch ID="#530 Release Order improvement" version="4.2.2" type="IMPROVEMENT" date="Feb 15, 2014" author="Leonardo Flores">
public class R_PrintEco extends Rule {

	private D_eCO doc;

	public R_PrintEco(D_eCO doc) {
		this.doc = doc;
	}

	protected void apply(KernelEvent kernelevent) {
		if (doc.ds(CO).de(NBR).getContent() == null || doc.ds(CO).ds(REG).de(SER).getContent() == null
				|| doc.ds(CO).ds(REG).de(DAT).getContent() == null) {
			KOptionPane.showMessageDialog(DesktopMain.sharedInstance(), lng("Warning: Certificate is not registered."), "Warning",
					KOptionPane.ERROR_MESSAGE);
			return;
		}
	    // 1. Get printing event
		int dlgId = JasperPrintingToolClient.startPrintDialog();
		byte[] zippedPDF = null;
		DataSet source = null;
		// 2. Request PrintOut PDF
		// 2.1 Check if transaction is active
		if (doc.getTransaction() != null && !doc.isDocumentCommited()){
		    TransactionEvent te = doc.applyMiddleEvent(DO_PRINT_PDF, new DataSet());
		    if (te.getException() == null && te.getResult() != null && te.getResult().de(PDF) != null)
		        zippedPDF = (byte[]) te.getResult().de(PDF).getContent();
		
		}else if ((source = (DataSet)kernelevent.getSource()) != null && source.getContent() != null && source.getName() != null){
            // 2.2 If transaction is not active, send direct event
            DataSet criteria = new DataSet();
            criteria.add((String)source.getName()).tryToSetContent(source.getContent());
            criteria.add(AWClientTranslator.LNG_DATA_NAME).copyFrom(doc.de(AWClientTranslator.LNG_DATA_NAME));
            criteria.add(AWClientTranslator.LCT_DATA_NAME).copyFrom(doc.de(AWClientTranslator.LCT_DATA_NAME));
            BinderShortcut bs = doc.getParentShortcut();
            ServerEvent se = new ServerEvent(criteria, DO_PRINT_PDF);
            try {
                se = ConnectionToServer.getConnection().applyEvent(bs.getBinderName(), bs.getShortcutId(),se);
                if ((source = (DataSet)se.getSource()) != null && ((DataSet)source).de(PDF) != null)
                    zippedPDF = (byte[]) ((DataSet)source).de(PDF).getContent();
            } catch (Exception e) {
                e.printStackTrace();
            } 
		}
		 // 3. Show printout
        JasperPrintingToolClient.endPrintDialog(dlgId);
        if (zippedPDF != null) JasperPrintingToolClient.showZippedPDFPrint(zippedPDF);
        else{
            KOptionPane.showMessageDialog(DesktopMain.sharedInstance(),
            //@formatter:off Used to prevent source format from Eclipse
            lng("ERROR: Exception thrown during the print creation on the server, please contact your administrator."),
            lng("ERROR on creating the report"), KOptionPane.ERROR_MESSAGE);
            //@formatter:on
        }
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
