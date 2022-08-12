//
//   $Header: /home/asycuda/home/cvsroot/asybrk/Shared/src/un/broker/serial/ast/R_Init_Document.java,v 1.5 2012-07-03 07:37:24 kazan Exp $

package un.asyeco.serial.ast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import un.asyeco.sharedUtils.ServerDate;


/**
 * 
 */
public class R_Init_Document extends Rule implements C_Ast {

	public void apply(KernelEvent e) {

		D_Ast ast = (D_Ast) e.getSource();
		ast.ds(SER).de(CUR).setEnabled(false);

		// Set working date
		if (!"".equals(ast.getOperationClassName())) {
			try {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				ServerDate s_date = new ServerDate();
				s_date.initServerDate(ast, e);
				ast.de(WDE).tryToSetContent(formatter.parse(formatter.format(s_date)));
				ast.de(WDE).changeOriginalContent();
			} catch (Exception exc) {
			}
		}

		if (ast.getOperationClassName().equals(OC_AMEND)) {

			ast.setEnabled(false);
			ast.ds(SER).de(INI).setEnabled(true);
		}

	}

}
