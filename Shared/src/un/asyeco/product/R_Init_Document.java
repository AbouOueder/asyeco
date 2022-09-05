package un.asyeco.product;

import static so.kernel.core.KernelEventConstants.GUI_DOCUMENT_INIT;

import java.text.SimpleDateFormat;

import so.kernel.core.DataSet;
import so.kernel.core.KNumberedSubDataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.kernel.core.TransactionEvent;
import un.asyeco.sharedUtils.ServerDate;

public class R_Init_Document extends Rule implements C_Product {
	
	public void apply(KernelEvent e) {
		D_Product doc = (D_Product) e.getSource();
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		ServerDate s_date = null;
		// Set working date
		if (!"".equals(doc.getOperationClassName())|| !"".equals(doc.getStartedOperation())) {
			try {
				s_date = new ServerDate();
				s_date.initServerDate(doc, e);
				doc.de(WDE).tryToSetContent(s_date);
				doc.de(WDE).changeOriginalContent();
			} catch (Exception exc) {
			}
			if (doc.getStartedOperation().getName().equals(OC_NEW) ){
				try {
					s_date = new ServerDate();
					s_date.initServerDate(doc, e);
					doc.de(WDE).tryToSetContent(s_date);
					doc.de(WDE).changeOriginalContent();
				} catch (Exception exc) {
				}				
			}
		}
	}

}
