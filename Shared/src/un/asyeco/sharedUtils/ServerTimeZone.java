package un.asyeco.sharedUtils;

import java.util.TimeZone;

import so.kernel.core.Document;
import so.kernel.core.KernelEvent;
import un.asyeco.eco.C_eCO;
import un.kernel.util.AWUtil;

public class ServerTimeZone implements C_eCO {

	public TimeZone tz;

	public ServerTimeZone(Document doc, KernelEvent e) {
		if (e == null) {
			e = new KernelEvent(REC_TMZ);
			tz = TimeZone.getTimeZone(AWUtil.GMT0);
		}
		if (doc == null) {
			tz = TimeZone.getTimeZone(AWUtil.GMT0);
		}
		if (doc.getTransaction() == null) {
			tz = TimeZone.getTimeZone(AWUtil.GMT0);
			return;
		}

		tz = TimeZone.getTimeZone(AWUtil.GMT0);
	}

}
