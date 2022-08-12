package un.asyeco.eco;

import so.kernel.core.DataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;

public class R_FillCommCode extends Rule implements C_eCO {

	private DataSet df1;

	public R_FillCommCode(DataSet df1) {
		this.df1 = df1;
	}

	protected void apply(KernelEvent kernelevent) {
		if (df1.de(TMP).getString("").equals(""))
			return;
		df1.de(COD).tryToSetContent(df1.de(TMP).getContentString());
		//df1.de(DSC).tryToSetContent(df1.de(DSCTMP).getContentString());
		//df1.de(DSC).changeOriginalContent();
		df1.de(TMP).tryToSetContent(null);
		//df1.de(DSCTMP).tryToSetContent(null);
	}
}
