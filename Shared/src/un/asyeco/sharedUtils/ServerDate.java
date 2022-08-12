package un.asyeco.sharedUtils;

import java.util.Calendar;

import so.kernel.core.DataSet;
import so.kernel.core.Document;
import so.kernel.core.KernelEvent;
import so.kernel.core.TransactionEvent;
import so.util.calendar.DateValue;
import un.asyeco.eco.C_eCO;


public class ServerDate extends DateValue  implements C_eCO {

	private static final long serialVersionUID = 1L;
	public boolean dateAndTime = false;

	public ServerDate() {
		super(0);
	}
	public ServerDate(String dateAndTime) {
		super(0);
		this.dateAndTime = true;
	}

	public void initServerDate(Document doc, KernelEvent e) {
		DateValue server_Date_;
		if (e == null) {
			e = new KernelEvent(REC_DAT);
		}
		if (doc.getTransaction() == null) {
			Calendar cal = Calendar.getInstance();
			super.setTime(cal.getTime().getTime());
			return;
		}
		TransactionEvent te_date1 = doc
				.applyMiddleEvent(REC_DAT, new DataSet());

		if (te_date1.getException() != null || te_date1.getResult() == null
				|| te_date1.getResult().de(WDE) == null) {
			Calendar cal = Calendar.getInstance();
			super.setTime(cal.getTime().getTime());
			return;
		} else {
			server_Date_ = (DateValue) te_date1.getResult().de(WDE)
					.getContent();
			super.setTime(server_Date_.getTime());
		}
	}
}
