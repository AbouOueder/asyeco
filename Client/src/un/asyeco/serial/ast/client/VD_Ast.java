//
//   $Header: /home/asycuda/home/cvsroot/asybrk/Client/src/un/broker/serial/ast/client/VD_Ast.java,v 1.8 2011-01-17 13:54:45 bolognesi Exp $

package un.asyeco.serial.ast.client;

import so.kernel.client.KLookupModel;
import so.kernel.client.Zoomable;
import un.adtcommons.client.visual.KVisualDocument;
import un.asyeco.serial.ast.C_Ast;



 

/**
 * Visual document class that acts as a container for the forms
 */
public class VD_Ast extends KVisualDocument implements C_Ast, Zoomable {

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
	public VD_Ast() {
		super();
		setTitle(lng("Declaration numbering"));

		CUO_COD_lookup.addColumn(CUO_COD, 50);
		CUO_COD_lookup.addColumn(CUO_NAM, 200);

		CUO_NAM_lookup.addColumn(CUO_NAM, 200);
		
		CTY_COD_lookup.addColumn(CTY_COD, 50);
		CTY_COD_lookup.addColumn(CTY_DSC, 200);

		CTY_DSC_lookup.addColumn(CTY_DSC, 200);
		



	}

	/**
	 * Method for initialising the visual forms of the document.
	 */
	public void initializeForms() {

		VF_Ast vf_Ast = new VF_Ast(); // Tariff note
		addForm(vf_Ast);

	}
	
	@Override
	public boolean isTabbedPaneAlwaysVisible() {
		return false;
	}

	/**
	 * Retrieves a property string in the current working language.
	 * 
	 */
	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
