package un.asyeco.serial.eco.reg.server;

import so.kernel.core.DataSet;
import so.kernel.server.ServerBinder;
import so.kernel.server.rules.KSR_DocumentRead;
import un.asyeco.serial.eco.reg.C_Reg;

public class SR_ChkKeys extends KSR_DocumentRead implements C_Reg {

	public SR_ChkKeys(ServerBinder binder) {
		super(binder, KSR_DocumentRead.ERROR | KSR_DocumentRead.DOCUMENT_FOUND | KSR_DocumentRead.NON_KEYS_DATA, "", "Serial_reg",
				new Object[] { ST_REGISTERED });
	}

	protected void prepareBeforeRead(DataSet source, DataSet key) {
		key.add(YER);
		DataSet cty = key.seg(CTY);
		cty.add(COD);
		
//		DataSet sit = key.seg(SIT);
//		sit.add(COD);

		key.de(YER).copyFrom(source.de(YER));
		key.ds(CTY).de(COD).copyFrom(source.ds(CTY).de(COD));
	}

	protected DataSet prepareAfterRead(DataSet source) {
		return source.ds("keys");
	}
}