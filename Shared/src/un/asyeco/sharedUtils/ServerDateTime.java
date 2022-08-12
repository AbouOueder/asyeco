package un.asyeco.sharedUtils;

import java.util.Calendar;
import java.util.Date;

import so.kernel.core.DataSet;
import so.kernel.core.Document;
import so.kernel.core.KernelEvent;
import so.kernel.core.TransactionEvent;
import un.asyeco.eco.C_eCO;

public class ServerDateTime extends Date implements C_eCO {

	public ServerDateTime() {
		super();
	}

	public void initServerDateTime(Document doc, KernelEvent e) {
		Date server_Date_;
		if (e == null) {
			e = new KernelEvent(REC_DAT_TIM);
		}
		if (doc.getTransaction() == null) {
			Calendar cal = Calendar.getInstance();
			super.setTime(cal.getTime().getTime());
			return;
		}
		TransactionEvent te_date1 = doc.applyMiddleEvent(REC_DAT_TIM, new DataSet());

		if (te_date1.getException() != null || te_date1.getResult() == null || te_date1.getResult().de(DAT) == null) {
			Calendar cal = Calendar.getInstance();
			super.setTime(cal.getTime().getTime());
			return;
		} else {
			server_Date_ = (Date) te_date1.getResult().de(DAT).getContent();
			super.setTime(server_Date_.getTime());

		}
	}

}
