package un.asyeco.serial.eco.submit.server;

import so.kernel.core.KernelEvent;
import so.kernel.server.ServerBinder;
import so.kernel.server.ServerRule;
import so.kernel.server.ThreadEnvironment;
import so.kernel.server.Variables;
import un.asyeco.serial.eco.submit.C_Sub;

public class SR_SaveHistory extends ServerRule implements C_Sub {

	public SR_SaveHistory(ServerBinder binder) {
		super(binder);
	}

	protected void apply(KernelEvent e) {
		ThreadEnvironment te = ThreadEnvironment.get();
		Variables var = te.getKernelVariables();
		var.setBoolean("tc.saveHistory", false);
	}
}
