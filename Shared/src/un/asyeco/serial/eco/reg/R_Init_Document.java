package un.asyeco.serial.eco.reg;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import un.asyeco.sharedUtils.ServerDate;

/**
 * 
 */
public class R_Init_Document extends Rule implements C_Reg {

	public void apply(KernelEvent e) {

		D_Reg reg = (D_Reg) e.getSource();
		reg.ds(SER).de(CUR).setEnabled(false);

		// Set working date
		if (!"".equals(reg.getOperationClassName())) {
			try {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				ServerDate s_date = new ServerDate();
				s_date.initServerDate(reg, e);
				reg.de(WDE).tryToSetContent(formatter.parse(formatter.format(s_date)));
				reg.de(WDE).changeOriginalContent();
			} catch (Exception exc) {
			}
		}

		if (reg.getOperationClassName().equals(OC_AMEND)) {

			reg.setEnabled(false);
			reg.ds(SER).de(INI).setEnabled(true);
		}
	}

}
