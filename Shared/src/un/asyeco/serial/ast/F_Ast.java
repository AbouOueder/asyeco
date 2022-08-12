//
//   $Header: /home/asycuda/home/cvsroot/asybrk/Shared/src/un/broker/serial/ast/F_Ast.java,v 1.7 2012-07-03 07:37:24 kazan Exp $

package un.asyeco.serial.ast;

import so.kernel.Global;
import so.kernel.core.FinderInfo;


/**
 * Container class for the wrok in progress document.
 */
public final class F_Ast extends FinderInfo implements C_Ast {

	public static final F_Ast finder = new F_Ast();

	protected void define_FinderModel() {

		// Size
		addFinder_SearchFld(de(YER), lng("Year"), Global.INTEGER_TYPE);
		addFinder_SearchFld(ds(CTY).de(COD), lng("Country, code"), Global.STRING_TYPE);
		addFinder_SearchFld(ds(CTY).de(DSC), lng("Country, name"), Global.STRING_TYPE);
		addFinder_SearchFld(ds(SIT).de(COD), lng("Customs office code"), Global.STRING_TYPE);
		addFinder_SearchFld(ds(SIT).de(NAM), lng("Customs office name"), Global.STRING_TYPE);
		addFinder_SearchFld(de(CHR), lng("Serial"), Global.STRING_TYPE);

		addFinder_ViewFld(de(YER), lng("Year"));
		
		addFinder_ViewFld(ds(CTY).de(COD), lng("Country, code"));
		addFinder_ViewFld(ds(CTY).de(DSC), lng("Country, name"));

		
		addFinder_ViewFld(ds(SIT).de(COD), lng("Customs office code"));
		addFinder_ViewFld(ds(SIT).de(NAM), lng("Customs office name"));
		addFinder_ViewFld(de(CHR), lng("Serial"));

		setDefaultSort(getList(de(YER), ds(SIT).de(COD)), UP);
	}

	private static so.i18n.IntlObj lng(String key) {
		return new so.i18n.IntlObj("un.asyeco", key);
	}
}
