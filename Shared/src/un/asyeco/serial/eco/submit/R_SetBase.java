package un.asyeco.serial.eco.submit;

import so.kernel.core.DataField;
import so.kernel.core.KernelEvent;
import so.kernel.core.Rule;

/**
 * 
 */
public class R_SetBase extends Rule implements C_Sub {

	public void apply(KernelEvent e) {

		DataField ini = e.getDataField();
		ini.getParent().de(CUR).tryToSetContent(ini.getContent());
		ini.getParent().de(CUR).changeOriginalContent();
	}

}
