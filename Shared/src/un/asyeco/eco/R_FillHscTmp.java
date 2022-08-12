package un.asyeco.eco;

import so.kernel.core.DataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;
import so.kernel.core.events.AttachedFinderImportEvent;
import so.kernel.core.interfaces.DocumentInterface;

public class R_FillHscTmp extends Rule implements C_eCO {

	private DataSet ds;

	public R_FillHscTmp(DataSet ds) {
		this.ds = ds;
	}

	protected void apply(KernelEvent kernelevent) {
		if (kernelevent instanceof AttachedFinderImportEvent) {
			DataSet resultDoc = ((AttachedFinderImportEvent) kernelevent).getResutlDoc();
			DataSet resultDoc_normal = resultDoc.ds(DocumentInterface.NORMAL_ID);
			String comm_code = resultDoc.ds(DocumentInterface.NORMAL_ID).de(HS6_COD).getContentString()
					+ resultDoc_normal.de(TAR_PR1).getContentString()
					+ resultDoc_normal.de(TAR_PR2).getContentString();
			//String commDsc = resultDoc_normal.de("TAR_DSC").getContentString();
			ds.de(TMP).tryToSetContent(comm_code);
			//ds.de(DSCTMP).tryToSetContent(commDsc);
		}

	}
}
