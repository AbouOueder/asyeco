//
//   $Header: /home/asycuda/home/cvsroot/asybrk/Client/src/un/broker/serial/ast/client/DC_Ast.java,v 1.7 2012-07-03 07:32:10 kazan Exp $

package un.asyeco.serial.ast.client;

import so.kernel.client.ClientDocument;
import so.kernel.core.KernelEventConstants;
import so.kernel.core.Rule;
import so.kernel.core.client.rules.KR_EndOfTransaction;
import un.asyeco.serial.ast.C_Ast;
import un.asyeco.serial.ast.D_Ast;
import un.asyeco.serial.ast.client.rules.R_AstDeleteConfirmation;
import un.asyeco.serial.ast.client.rules.R_ChkKeys;
import un.kernel.core.rules.KR_HTConnectorFactory;

public class DC_Ast extends ClientDocument implements C_Ast {

	void initRules(D_Ast doc) {

		doc.addRule(new R_ChkKeys(doc), KernelEventConstants.DOCUMENT_VERIFY);

		// Delete requested, confirmation
		doc.addRule(new R_AstDeleteConfirmation(), KernelEventConstants.FINAL_ACTION_REQUESTED);

		// Display end of transaction standard information dialog
		doc.addRule(new KR_EndOfTransaction(KR_EndOfTransaction.CLOSE), KernelEventConstants.OPERATION_DONE);

	}

	void initRules(D_Ast doc, VD_Ast vd) {

		String language = vd.getLocale().getLanguage();
		// String languageSuffix = (language.equalsIgnoreCase("ar")) ? "_ar" :
		// "";
		String languageSuffix = "";
		String cuo_tab = CUO_TAB.concat(languageSuffix);
		String cty_tab = CTY_TAB.concat(languageSuffix);


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
