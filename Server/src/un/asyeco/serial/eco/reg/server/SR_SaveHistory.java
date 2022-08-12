package un.asyeco.serial.eco.reg.server;

import so.kernel.core.KernelEvent;
import so.kernel.server.ServerBinder;
import so.kernel.server.ServerRule;
import so.kernel.server.ThreadEnvironment;
import so.kernel.server.Variables;
import un.asyeco.serial.eco.reg.C_Reg;

public class SR_SaveHistory extends ServerRule implements C_Reg {

	public SR_SaveHistory(ServerBinder binder) {
		super(binder);
	}

	protected void apply(KernelEvent e) {
		ThreadEnvironment te = ThreadEnvironment.get();
		Variables var = te.getKernelVariables();
		var.setBoolean("tc.saveHistory", false);
	}
}
