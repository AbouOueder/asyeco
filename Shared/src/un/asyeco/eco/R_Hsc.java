//   $Header: /home/asycuda/home/cvsroot/nattrs/Shared/src/un/nattrs/t1/R_Hsc.java,v 1.7 2012-07-03 15:21:43 kazan Exp $

package un.asyeco.eco;

import so.kernel.core.DataField;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.util.DebugOutput;

public class R_Hsc extends Rule implements C_eCO {

	// Checks that hsc code is composed of more than 4 digits

	public void apply(KernelEvent e) {

		DataField d = e.getDataField();
		if (!d.isEnabled()) {
			return;
		}

		int hsc = d.getInt(0);

		if (d.getHumanName() != null) {
			DebugOutput.print("+++ R_Hsc apply on " + d.getHumanName());
		}

		if ((d.getString("").length() != 4) && (d.getString("").length() != 6) && (d.getString("").length() != 8) && (d.getString("").length() != 10)
				&& (d.getString("").length() != 0)) {

			setError(d, " " + lng("HSC Code must have 4, 6, 8 or 10 digits"), e);
		}
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
