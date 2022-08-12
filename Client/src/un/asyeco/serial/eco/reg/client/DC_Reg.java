//   $Header: /home/asycuda/home/cvsroot/nattrs/Client/src/un/nattrs/serial/t1/reg/client/DC_Reg.java,v 1.8 2012-07-03 15:21:43 kazan Exp $

package un.asyeco.serial.eco.reg.client;

import so.kernel.client.ClientDocument;
import so.kernel.core.KernelEventConstants;
import so.kernel.core.Rule;
import so.kernel.core.client.rules.KR_EndOfTransaction;
import un.asyeco.serial.eco.reg.C_Reg;
import un.asyeco.serial.eco.reg.D_Reg;
import un.asyeco.serial.eco.reg.client.rules.R_ChkKeys;
import un.asyeco.serial.eco.reg.client.rules.R_ChkLanguage;
import un.asyeco.serial.eco.reg.client.rules.R_RegDeleteConfirmation;
import un.asyeco.serial.eco.reg.client.rules.R_SetLocale;
import un.kernel.core.rules.KR_HTConnectorFactory;


public class DC_Reg extends ClientDocument implements C_Reg {

	void initRules(D_Reg doc) {

		doc.addRule(new R_ChkKeys(doc), KernelEventConstants.DOCUMENT_VERIFY);

		// Delete declares, confirmation
		doc.addRule(new R_RegDeleteConfirmation(), KernelEventConstants.FINAL_ACTION_REQUESTED);

		// Display end of transaction standard information dialog
		doc.addRule(new KR_EndOfTransaction(KR_EndOfTransaction.CLOSE), KernelEventConstants.OPERATION_DONE);
		doc.addRule(new R_SetLocale(), KernelEventConstants.DOCUMENT_INIT);
	}

	void initRules(D_Reg doc, VD_Reg vd) {

		String language = vd.getLocale().getLanguage();
		// String languageSuffix = (language.equalsIgnoreCase("ar")) ? "_ar" :
		// "";
		// ETH - lng ServerTranslator 09.2009
		doc.addRule(new R_ChkLanguage(vd, doc), KernelEventConstants.GUI_DOCUMENT_INIT);

		String languageSuffix = "";
		String cuo_tab = CUO_TAB.concat(languageSuffix);
		String cty_tab = CTY_TAB;

		// Customs site
		// ds(SIT).addRule(new KR_Reference_IdName(ds(SIT), ref(CUO_TAB),
		// CUO_COD, CUO_NAM,"Unknown Customs site"), DATA_VERIFY);
		Rule ruleTarget1 = KR_HTConnectorFactory.getHTConnectorRule(doc, cuo_tab, 0, CUO_COD, doc.ds(SIT).de(NAM), CUO_NAM);
		doc.ds(SIT).de(COD).addRule(ruleTarget1, KernelEventConstants.DATA_VERIFY);

		Rule ruleTarget2 = KR_HTConnectorFactory.getHTConnectorRule(doc, cuo_tab, 3, CUO_NAM, doc.ds(SIT).de(COD), CUO_COD);
		doc.ds(SIT).de(NAM).addRule(ruleTarget2, KernelEventConstants.DATA_VERIFY);
		
		Rule ruleTarget3 = KR_HTConnectorFactory.getHTConnectorRule(doc, cty_tab, 0, CTY_COD, doc.ds(CTY).de(DSC), CTY_DSC);
		doc.ds(CTY).de(COD).addRule(ruleTarget3, KernelEventConstants.DATA_VERIFY);

		Rule ruleTarget4 = KR_HTConnectorFactory.getHTConnectorRule(doc, cty_tab, 3, CTY_DSC, doc.ds(CTY).de(COD), CTY_COD);
		doc.ds(CTY).de(DSC).addRule(ruleTarget4, KernelEventConstants.DATA_VERIFY);

		
	}
}
