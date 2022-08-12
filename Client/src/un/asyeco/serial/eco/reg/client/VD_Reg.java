package un.asyeco.serial.eco.reg.client;

import so.kernel.client.KLookupModel;
import so.kernel.client.Zoomable;
import un.adtcommons.client.visual.KVisualDocument;
import un.asyeco.serial.eco.reg.C_Reg;

/**
 * Visual document class that acts as a container for the forms
 * 
 * @author Aboubacar Ouedraogo
 */
public class VD_Reg extends KVisualDocument implements C_Reg, Zoomable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** @serial */
	public KLookupModel CUO_COD_lookup = new KLookupModel(CUO_COD, 7);

	/** @serial */
	public KLookupModel CUO_NAM_lookup = new KLookupModel(CUO_NAM, 35);
	
	/** @serial */
	public KLookupModel CTY_COD_lookup = new KLookupModel(CTY_COD, 5);

	/** @serial */
	public KLookupModel CTY_DSC_lookup = new KLookupModel(CTY_DSC, 75);


	/**
	 * Constructor - sets a title for the visual document and an icon for the
	 * internal frame in which it is shown.
	 */
	public VD_Reg() {
		super();
		setTitle(lng("Certificate of Origine numbering"));

		CUO_COD_lookup.addColumn(CUO_COD, 50);
		CUO_COD_lookup.addColumn(CUO_NAM, 200);

		CUO_NAM_lookup.addColumn(CUO_NAM, 200);
		
		CTY_COD_lookup.addColumn(CTY_COD, 50);
		CTY_COD_lookup.addColumn(CTY_DSC, 200);

		CTY_DSC_lookup.addColumn(CTY_DSC, 200);

	}
	
	@Override
	public boolean isTabbedPaneAlwaysVisible(){
		return false;
	}

	/**
	 * Method for initialising the visual forms of the document.
	 */
	public void initializeForms() {

		VF_Reg vf_Reg = new VF_Reg(); 
		addForm(vf_Reg);

	}

	/**
	 * Retrieves a property string in the current working language.
	 * 
	 */
	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
