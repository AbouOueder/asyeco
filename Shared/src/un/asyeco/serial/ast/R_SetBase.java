//
//   $Header: /home/asycuda/home/cvsroot/asybrk/Shared/src/un/broker/serial/ast/R_SetBase.java,v 1.5 2010-06-08 13:36:42 john Exp $

package un.asyeco.serial.ast;

import so.kernel.core.DataField;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;


/**
 */
public class R_SetBase extends Rule implements C_Ast {

	public void apply(KernelEvent e) {

		DataField ini = e.getDataField();
		D_Ast ass = (D_Ast) ini.getDocument();
		if (ass.getOperationClassName().equals(OC_NEW)) {
			ini.getParent().de(CUR).tryToSetContent(ini.getContent());
			ini.getParent().de(CUR).changeOriginalContent();
		}
	}

}
