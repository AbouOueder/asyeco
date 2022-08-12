package un.asyeco.serial.ast.server;

import so.kernel.core.KernelEvent;
import so.kernel.server.ServerBinder;
import so.kernel.server.ServerRule;
import so.kernel.server.ThreadEnvironment;
import so.kernel.server.Variables;
import un.asyeco.serial.ast.C_Ast;

public class SR_SaveHistory extends ServerRule implements C_Ast {

	public SR_SaveHistory(ServerBinder binder) {
		super(binder);
	}

	protected void apply(KernelEvent e) {
		ThreadEnvironment te = ThreadEnvironment.get();
		Variables var = te.getKernelVariables();
		var.setBoolean("tc.saveHistory", false);
	}
}
