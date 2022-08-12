package un.asyeco.eco;

import so.kernel.core.DataField;
import so.kernel.core.FindItemStructure;
import so.kernel.core.FinderInfo;
import so.kernel.core.KernelEvent;
import so.kernel.core.RuleInterface;
import so.kernel.core.events.PrepareAttachedFinderEvent;
import so.kernel.core.types.DateType;

/**
 * Initialize the attached finder
 * 
 * @author UNCTAD
 */
public class R_Init_Finder_Historized implements RuleInterface, C_eCO {
	private DataField df;

	public R_Init_Finder_Historized(DataField df) {
		this.df = df;
	}

	public void execute(KernelEvent kernelevent) {
		if (kernelevent instanceof PrepareAttachedFinderEvent) {
			PrepareAttachedFinderEvent pafe = (PrepareAttachedFinderEvent) kernelevent;
			FinderInfo fi = pafe.getFinderInfo();

			FinderInfo.SearchableInfo si = fi.getSearchFldInfo(FinderInfo.ds(DAT).de(BEG));
			si.getFindItemStructure().setFirstValue(df.getContent());
			si.getFindItemStructure().setOperation(DateType.LE);

			si = fi.getSearchFldInfo(FinderInfo.ds(DAT).de(END));
			si.getFindItemStructure().setFirstValue(df.getContent());
			si.getFindItemStructure().setOperation(DateType.GE);

			FindItemStructure fis = new FindItemStructure(si.getFindItemStructure());
			fis.setOperation(DateType.EMPTY);

			si.getFindItemStructure().setOrStatement(fis);
		}
	}
}