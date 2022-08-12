package un.asyeco.serverUtils;

import java.util.TimeZone;

import so.kernel.core.DataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.events.ServerEvent;
import so.kernel.server.ServerBinder;
import so.kernel.server.ServerRule;
import un.asyeco.eco.C_eCO;
import un.kernel.util.AWUtil;

public class SR_SendTimeZone extends ServerRule implements C_eCO {

	public SR_SendTimeZone(ServerBinder serverBinder) {
		super(serverBinder);
	}

	protected void apply(KernelEvent e) {

		DataSet result = null;

		TimeZone time_zone = TimeZone.getTimeZone(AWUtil.GMT0);
		ServerEvent se = (ServerEvent) e;
		result = se.getDestination();

		result.add(TMZ);
		result.de(TMZ).tryToSetContent(time_zone);
	}

}
