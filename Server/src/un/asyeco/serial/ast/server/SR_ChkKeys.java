package un.asyeco.serial.ast.server;

import so.kernel.core.DataSet;
import so.kernel.server.ServerBinder;
import so.kernel.server.rules.KSR_DocumentRead;
import un.asyeco.serial.ast.C_Ast;

public class SR_ChkKeys extends KSR_DocumentRead implements C_Ast {

	public SR_ChkKeys(ServerBinder binder) {
		super(binder, KSR_DocumentRead.ERROR | KSR_DocumentRead.DOCUMENT_FOUND | KSR_DocumentRead.NON_KEYS_DATA, "", "Serial_ast",
				new Object[] { ST_REGISTERED });
	}

	protected void prepareBeforeRead(DataSet source, DataSet key) {
		key.add(YER);
		//key.add(DTI);
		DataSet cty = key.seg(CTY);
		cty.add(COD);
		key.add(CHR);

		key.de(YER).copyFrom(source.de(YER));
		//key.de(DTI).copyFrom(source.de(DTI));
		key.ds(CTY).de(COD).copyFrom(source.ds(CTY).de(COD));
		key.de(CHR).copyFrom(source.de(CHR));
	}

	protected DataSet prepareAfterRead(DataSet source) {
		return source.ds("keys");
	}
}