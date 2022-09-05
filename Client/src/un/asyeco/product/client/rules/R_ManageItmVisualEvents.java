package un.asyeco.product.client.rules;

import so.kernel.client.VisualForm;
import so.kernel.core.Document;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.kernel.core.events.client.GUIFormGainFocusEvent;
import un.asyeco.product.C_Product;
import un.asyeco.product.client.VF_ProductItem;



public class R_ManageItmVisualEvents extends Rule implements C_Product {

	public void apply(KernelEvent e) {

		GUIFormGainFocusEvent ev = (GUIFormGainFocusEvent) e;
		Document doc = (Document) ev.getSource();
		VisualForm vf = ev.getForm();

		if (vf instanceof VF_ProductItem) {
			doc.setEnabledOperationEvent(MEN_ITM_NEW, true);
			doc.setEnabledOperationEvent(MEN_ITM_DEL, true);
		} else {
			doc.setEnabledOperationEvent(MEN_ITM_NEW, false);
			doc.setEnabledOperationEvent(MEN_ITM_DEL, false);
		}
	}
}