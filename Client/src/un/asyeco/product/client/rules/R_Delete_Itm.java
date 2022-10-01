package un.asyeco.product.client.rules;

import java.text.MessageFormat;

import so.kernel.client.DesktopMain;
import so.kernel.client.KVisualDynamicForm;
import so.kernel.client.KVisualPage;
import so.kernel.core.DataField;
import so.kernel.core.KDocument;
import so.kernel.core.KNumberedSubDataSet;
import so.kernel.core.KNumberedSubDocument;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.swing.KOptionPane;
import so.util.DebugOutput;
import un.asyeco.product.C_Product;
import un.asyeco.product.DS_ProductItm;
import un.asyeco.product.D_Product;
import un.asyeco.product.client.VD_Product;
import un.asyeco.product.client.VF_ProductItem;
import un.asyeco.product.client.VP_ProductItem;



/**
 * Handle a "delete child" visible event, requests user confirmation, and eventually fire the CHILD_DEL event to delete the current child.
 */
public class R_Delete_Itm extends Rule implements C_Product {
	private VD_Product vd;

	public R_Delete_Itm(VD_Product vd) {
		this.vd = vd;
	}

	public void apply(KernelEvent e) {
		
//		// ask user confirmation to delete contact
//		//MessageFormat formater = new MessageFormat("Do you want to delete this page?");
//		int answer = KOptionPane.showConfirmDialog(DesktopMain.sharedInstance(), "Do you want to delete this page?", "Confirmation",
//				KOptionPane.YES_NO_OPTION, KOptionPane.QUESTION_MESSAGE, null);
//
//		if (answer == KOptionPane.YES_OPTION) {
//			KDocument doc = (KDocument)e.getData();
//			VF_ProductItem vf = (VF_ProductItem)vd.getSelectedForm();
//			KVisualPage vp = (KVisualPage)vf.getSelectedPage();
//		
//			KNumberedSubDocument itm = (KNumberedSubDocument)vp.getAttachedDataSet();
//			int i = itm.getRankOfDocument();
//			//int[] nb = vf.prepareDeletePage((KNumberedSubDataSet)doc.ds(ITM), i);
//			//if (nb[0] >= 0){
//				doc.fire(new KernelEvent(itm, ACT_ITM_DEL));
//			//	if (!"".equals(itm.de(HS1).getString(""))){
//			//		nb[0]++;
//			//	}
//			//	if (itm.ds(PCK).de(NBR).getInt(0) != 0){
//			//		nb[1]= nb[1]+ itm.ds(PCK).de(NBR).getInt(0);
//			//	}
//			//	DataField totalItm = doc.ds(TOT).de(ITM);
//			//	totalItm.setInteger(totalItm.getInt(0) - nb[0]);
//			//	DataField totalPkg = doc.ds(TOT).de(PKG);
//			//	totalPkg.setInteger(totalPkg.getInt(0) - nb[1]);
//			}
//		}

	

		
		

		if (e.getData() instanceof D_Product) {
			D_Product prod = (D_Product) e.getData();

			if (prod != vd.getDocument()) {
				DebugOutput.error("The rule has been added to a doc that is not attached to given skin");
				return;
			}
			VF_ProductItem vf = (VF_ProductItem) vd.getSelectedForm();
			VP_ProductItem vp = (VP_ProductItem) vf.getSelectedPage();

			DS_ProductItm child = (DS_ProductItm) vp.getAttachedDataSet();
			KNumberedSubDocument itm = (KNumberedSubDocument)vp.getAttachedDataSet();
			KNumberedSubDataSet itm_sds = (KNumberedSubDataSet) prod.ds(ITM); 
			// there is no current child to delete
			if (child == null) return;

			String name = child.ds(TAR).de(COD).getContentString(); // New
			// empty
			// child
			//if (name == null || name.isEmpty() || name.equalsIgnoreCase("")) {
			//	doDelete(prod, child);
			//	return;
			//}

			// ask user confirmation to delete child
			MessageFormat formater = new MessageFormat(lng("Do you want to delete this page?, {0}?"));
			int answer = KOptionPane
					.showConfirmDialog(DesktopMain.sharedInstance(), formater.format(new Object[] { name }), lng("Confirmation"), KOptionPane.YES_NO_OPTION, KOptionPane.QUESTION_MESSAGE, null);
			if (answer == KOptionPane.YES_OPTION) {
				doDelete(prod, child);
				int totItms = itm_sds.countDocuments();
				int oldTot = prod.ds(TOT).de(ITM).getInt(0);
				if (totItms == oldTot)  prod.ds(TOT).de(ITM).setInteger(oldTot-1);
				else  prod.ds(TOT).de(ITM).setInteger(totItms);
				
				prod.ds(TOT).de(ITM).changeOriginalContent();
			} 
		}
	}

	private void doDelete(D_Product prod, DS_ProductItm child) {
		prod.fire(new KernelEvent(child, ACT_ITM_DEL));
		// delete current child
	}

	public static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
