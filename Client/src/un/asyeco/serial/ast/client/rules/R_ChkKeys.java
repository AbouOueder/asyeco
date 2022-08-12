package un.asyeco.serial.ast.client.rules;

import static so.kernel.core.KernelEventConstants.DATA_VERIFY;
import so.kernel.core.DataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.MessageDestination;
import so.kernel.core.Rule;
import so.kernel.core.TransactionEvent;
import so.kernel.core.rules.DocumentRead_Item;
import un.asyeco.serial.ast.C_Ast;
import un.asyeco.serial.ast.D_Ast;

public class R_ChkKeys extends Rule implements C_Ast {
	private transient D_Ast doc;

	public R_ChkKeys(D_Ast doc) {
		super();
		this.doc = doc;
	}

	public void apply(KernelEvent e) {

		if (doc.getOperationClassName().equals(OC_NEW)) {

			// Need to ensure that there is no other document with the same keys

			// Retrieve the keys of the document instance to open.
			DataSet source = new DataSet();
			source.add(YER); // Year
			source.add(DTI);
			DataSet cty = source.seg(CTY);
			cty.add(COD); // Country code
			//DataSet sit = source.seg(SIT);
			//sit.add(COD); // Customs site code
			source.de(YER).tryToSetContent(doc.de(YER).getContent());
			//source.de(DTI).tryToSetContent(doc.de(DTI).getContent());
			source.ds(CTY).de(COD).tryToSetContent(doc.ds(CTY).de(COD).getContent());
			source.add(CHR);
			source.de(CHR).tryToSetContent(doc.de(CHR).getContent());

			// Ask the server for the GCF key
			TransactionEvent te = doc.applyMiddleEvent(CHK_KEYS, source);
			DataSet dest = te.getResult();
			if (dest == null) {
				return;
			}
			DocumentRead_Item item = (DocumentRead_Item) dest.getContent();
			if (item == null) {
				return;
			}

			DataSet keys = item.getDataSet();

			if (keys != null) {
				MessageDestination md = getList(doc.ds(CTY).de(COD), doc.de(YER), doc.de(CHR));
				setError(md, lng("Document keys are not unique"), DATA_VERIFY);
				e.consume();
				return;
			}
		}
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
